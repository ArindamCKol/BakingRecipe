package com.gmail.kol.c.arindam.bakingrecipe;

import android.os.AsyncTask;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    private String url = "https://d17h27t6h515a5.cloudfront.net/topher/2017/May/59121517_baking/baking.json";
    RecipeListFragment recipeListFragment;
    FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        new DownloadRecipeJSON().execute(url);

        fragmentManager = getSupportFragmentManager();
        recipeListFragment = new RecipeListFragment();




    }

    //async thread to downlod json from url
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
                recipeListFragment.setTempText(null);
            } else {
                recipeListFragment.setErrorText(null);
                recipeListFragment.setTempText(jsonString);
            }

            fragmentManager.beginTransaction()
                    .add(R.id.recipe_list_fragment,recipeListFragment)
                    .commit();
        }
    }
}
