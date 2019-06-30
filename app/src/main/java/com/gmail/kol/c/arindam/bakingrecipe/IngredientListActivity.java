package com.gmail.kol.c.arindam.bakingrecipe;

import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.gmail.kol.c.arindam.bakingrecipe.Fragments.IngredientListFragment;
import com.gmail.kol.c.arindam.bakingrecipe.Model.Recipe;

public class IngredientListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ingredient_list);

        //get recipe from step list activity
        Intent intent = getIntent();
        Recipe mRecipe = intent.getParcelableExtra(MainActivity.RECIPE_SELECTED);

        setTitle(mRecipe.getName());

        //only create fragment if it does not exist
        if(savedInstanceState == null) {
            IngredientListFragment ingredientListFragment = new IngredientListFragment();
            FragmentManager fragmentManager = getSupportFragmentManager();

            //attach fragment with container & send recipe
            ingredientListFragment.setCurrentRecipe(mRecipe);
            fragmentManager.beginTransaction()
                    .add(R.id.ingredient_container, ingredientListFragment)
                    .commit();
        }
    }
}
