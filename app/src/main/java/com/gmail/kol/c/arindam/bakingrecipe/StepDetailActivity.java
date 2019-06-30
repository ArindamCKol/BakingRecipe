package com.gmail.kol.c.arindam.bakingrecipe;

import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.gmail.kol.c.arindam.bakingrecipe.Fragments.StepDetailFragment;
import com.gmail.kol.c.arindam.bakingrecipe.Model.Recipe;

public class StepDetailActivity extends AppCompatActivity {
    private int currentPosition;
    private Recipe currentRecipe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_step_detail);

        Intent intent = getIntent();
        currentRecipe = intent.getParcelableExtra(MainActivity.RECIPE_SELECTED);
        currentPosition = intent.getIntExtra(StepListActivity.POSITION,0);

        setTitle(currentRecipe.getName());

        if(savedInstanceState == null) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            StepDetailFragment stepDetailFragment = new StepDetailFragment();
            stepDetailFragment.setCurrentRecipe(currentRecipe);
            stepDetailFragment.setCurrentPosition(currentPosition);

            fragmentManager.beginTransaction()
                    .add(R.id.step_container,stepDetailFragment)
                    .commit();
        }
    }
}
