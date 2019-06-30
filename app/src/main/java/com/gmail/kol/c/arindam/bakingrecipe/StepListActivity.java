package com.gmail.kol.c.arindam.bakingrecipe;

import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.gmail.kol.c.arindam.bakingrecipe.Fragments.StepListFragment;
import com.gmail.kol.c.arindam.bakingrecipe.Model.Recipe;

public class StepListActivity extends AppCompatActivity implements StepListFragment.OnStepSelected {

    private Recipe mRecipe;
    public static final String POSITION = "position";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_step_list);

        Intent intent = getIntent();
        mRecipe = intent.getParcelableExtra(MainActivity.RECIPE_SELECTED);
        setTitle(mRecipe.getName());

        if(savedInstanceState == null) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            StepListFragment stepListFragment = new StepListFragment();

            stepListFragment.setCurrentRecipe(mRecipe);
            fragmentManager.beginTransaction()
                    .add(R.id.steps_container, stepListFragment)
                    .commit();
        }
    }

    @Override
    public void onStepClick(int position) {
        //if position is 0, i.e. ingredient item clicked, then show ingredient list
        if (position == 0) {
            Intent ingredientIntent = new Intent(this, IngredientListActivity.class);
            ingredientIntent.putExtra(MainActivity.RECIPE_SELECTED, mRecipe);
            startActivity(ingredientIntent);
        } else {
            //otherwise show step description / video
            Intent stepDetailIntent = new Intent(this, StepDetailActivity.class);
            stepDetailIntent.putExtra(MainActivity.RECIPE_SELECTED, mRecipe);
            stepDetailIntent.putExtra(POSITION, (position-1));
            startActivity(stepDetailIntent);
        }
    }
}
