package com.gmail.kol.c.arindam.bakingrecipe.Fragments;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gmail.kol.c.arindam.bakingrecipe.Helper.StepListAdapter;
import com.gmail.kol.c.arindam.bakingrecipe.Model.Recipe;
import com.gmail.kol.c.arindam.bakingrecipe.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class StepListFragment extends Fragment implements StepListAdapter.StepClickListener{
    private Recipe currentRecipe;
    public OnStepSelected onStepSelected;

    public StepListFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        onStepSelected = (OnStepSelected) context;
    }

    //interface to communicate with activity
    public interface OnStepSelected{
        void onStepClick(int position);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View rootView = inflater.inflate(R.layout.fragment_step_list, container, false);
        RecyclerView recipeStepsList = rootView.findViewById(R.id.recipe_steps_rv);
        StepListAdapter stepsAdapter = new StepListAdapter(this);

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

    @Override
    public void onClick(int position) {
        onStepSelected.onStepClick(position);
    }
}
