package com.gmail.kol.c.arindam.bakingrecipe;

import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.gmail.kol.c.arindam.bakingrecipe.Fragments.RecipeStepsFrgment;
import com.gmail.kol.c.arindam.bakingrecipe.Model.Recipe;

public class RecipeSteps extends AppCompatActivity {

    private RecipeStepsFrgment recipeStepsFrgment;
    private FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_steps);

        Intent intent = getIntent();
        Recipe mRecipe = intent.getParcelableExtra(MainActivity.RECIPE_SELECTED);
        setTitle(mRecipe.getName());

        fragmentManager = getSupportFragmentManager();
        recipeStepsFrgment = new RecipeStepsFrgment();

        recipeStepsFrgment.setCurrentRecipe(mRecipe);
        fragmentManager.beginTransaction()
                .add(R.id.steps_container,recipeStepsFrgment)
                .commit();

    }
}
