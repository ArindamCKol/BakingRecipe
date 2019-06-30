package com.gmail.kol.c.arindam.bakingrecipe.Fragments;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.gmail.kol.c.arindam.bakingrecipe.MainActivity;
import com.gmail.kol.c.arindam.bakingrecipe.Model.Recipe;
import com.gmail.kol.c.arindam.bakingrecipe.R;
import com.gmail.kol.c.arindam.bakingrecipe.StepListActivity;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class StepDetailFragment extends Fragment implements View.OnClickListener {

    private TextView description;
    private Button nextButton;
    private Button prevButton;
    private Recipe currentRecipe;

    private int currentPosition;

    public StepDetailFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //load saved state
        if(savedInstanceState!=null) {
            currentPosition = savedInstanceState.getInt(StepListActivity.POSITION);
            currentRecipe = savedInstanceState.getParcelable(MainActivity.RECIPE_SELECTED);
        }

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_recipe_detail, container, false);

        description = view.findViewById(R.id.description);
        nextButton = view.findViewById(R.id.next_button);
        prevButton = view.findViewById(R.id.prev_button);

        //set up on click listener to buttons
        nextButton.setOnClickListener(this);
        prevButton.setOnClickListener(this);

        //show steps details
        showStep();

        return view;
    }

    //get current recipe from activity
    public void setCurrentRecipe(Recipe recipe) {
        currentRecipe = recipe;
    }

    //get current position from activity
    public void setCurrentPosition(int i) {
        currentPosition = i;
    }

    //on button click increase / decrease current position & show details
    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.next_button :
                if(currentPosition<(currentRecipe.steps.size()-1)) {currentPosition++;}
                break;
            case R.id.prev_button :
                if(currentPosition>0) {currentPosition--;}
                break;
            default: break;
        }
        showStep();
    }

    //show step description & video
    public void showStep() {

        description.setText(currentRecipe.steps.get(currentPosition).getDescription());

    }

    //save current state of the fragment
    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        outState.putInt(StepListActivity.POSITION,currentPosition);
        outState.putParcelable(MainActivity.RECIPE_SELECTED,currentRecipe);
        super.onSaveInstanceState(outState);
    }
}
