package com.gmail.kol.c.arindam.bakingrecipe.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.gmail.kol.c.arindam.bakingrecipe.Helper.RecipeStepsAdapter;
import com.gmail.kol.c.arindam.bakingrecipe.Model.Recipe;
import com.gmail.kol.c.arindam.bakingrecipe.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class RecipeStepsFrgment extends Fragment {
    private Recipe currentRecipe;

    public RecipeStepsFrgment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View rootView = inflater.inflate(R.layout.fragment_recipe_steps, container, false);
//        TextView ingredientTextView = rootView.findViewById(R.id.ingredient_tv);
        RecyclerView recipeStepsList = rootView.findViewById(R.id.recipe_steps_rv);
        RecipeStepsAdapter stepsAdapter = new RecipeStepsAdapter();

//        ingredientTextView.setText("Ingredients");
        recipeStepsList.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false));
        recipeStepsList.setAdapter(stepsAdapter);
        if(currentRecipe.steps!=null) {
            stepsAdapter.setSteps(currentRecipe.steps);
        }
        return rootView;
    }
    public void setCurrentRecipe (Recipe recipe) {
        currentRecipe = recipe;
    }
}
