package com.gmail.kol.c.arindam.bakingrecipe;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.gmail.kol.c.arindam.bakingrecipe.Fragments.RecipeListFragment;
import com.gmail.kol.c.arindam.bakingrecipe.Model.Recipe;
import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;
import com.squareup.moshi.Types;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity implements RecipeListFragment.OnRecipeItemSelected{
    //url for recipes
    private String url = "https://d17h27t6h515a5.cloudfront.net/topher/2017/May/59121517_baking/baking.json";
    private RecipeListFragment recipeListFragment;
    private FragmentManager fragmentManager;
    public static final String RECIPE_SELECTED = "selected_recipe";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        new DownloadRecipeJSON().execute(url);

        //initialise fragment
        fragmentManager = getSupportFragmentManager();
        recipeListFragment = new RecipeListFragment();
    }

    //on recipe item selected open steps activity
    @Override
    public void onRecipeSelected(Recipe recipe) {
        Intent intent = new Intent(this, StepListActivity.class);
        intent.putExtra(RECIPE_SELECTED, recipe);
        startActivity(intent);
    }

    //async thread to download json from url using okhttp library
    private class DownloadRecipeJSON extends AsyncTask<String,Integer,String> {

        @Override
        protected String doInBackground(String... strings) {
            OkHttpClient client = new OkHttpClient();

            String jsonSting = null;
            Request request = new Request.Builder()
                    .url(strings[0])
                    .build();

            try (Response response = client.newCall(request).execute()) {
                jsonSting = response.body().string();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return jsonSting;
        }

        @Override
        protected void onPostExecute(String jsonString) {
            if(jsonString == null) {
                recipeListFragment.setErrorText(getString(R.string.network_error));
                recipeListFragment.setRecipeList(null);
            } else {
                recipeListFragment.setErrorText(null);
                List<Recipe> recipeList = getRecipesFromJSON(jsonString);
                recipeListFragment.setRecipeList(recipeList);
            }

            fragmentManager.beginTransaction()
                    .add(R.id.recipe_list_fragment,recipeListFragment)
                    .commit();
        }
    }

    //json string to list of recipe object using moshi library
    private List<Recipe> getRecipesFromJSON(String jsonString) {
        List<Recipe> recipes = null;

        Moshi moshi = new Moshi.Builder().build();
        Type type = Types.newParameterizedType(List.class, Recipe.class);
        JsonAdapter<List<Recipe>> jsonAdapter = moshi.adapter(type);
        try {
            recipes = jsonAdapter.fromJson(jsonString);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return recipes;
    }
}
