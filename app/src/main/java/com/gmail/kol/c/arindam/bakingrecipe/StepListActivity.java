package com.gmail.kol.c.arindam.bakingrecipe;

import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.gmail.kol.c.arindam.bakingrecipe.Fragments.StepListFragment;
import com.gmail.kol.c.arindam.bakingrecipe.Model.Recipe;

public class StepListActivity extends AppCompatActivity implements StepListFragment.OnStepSelected {

    private StepListFragment stepListFragment;
    private FragmentManager fragmentManager;
    private Recipe mRecipe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_step_list);

        Intent intent = getIntent();
        mRecipe = intent.getParcelableExtra(MainActivity.RECIPE_SELECTED);
        setTitle(mRecipe.getName());

        fragmentManager = getSupportFragmentManager();
        stepListFragment = new StepListFragment();

        stepListFragment.setCurrentRecipe(mRecipe);
        fragmentManager.beginTransaction()
                .add(R.id.steps_container, stepListFragment)
                .commit();

    }

    @Override
    public void onStepClick(int position) {
        if (position == 0) {
            Toast.makeText(this, "Ingredient", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(this, mRecipe.steps.get(position-1).getShortDescription(), Toast.LENGTH_LONG).show();
        }
    }
}
