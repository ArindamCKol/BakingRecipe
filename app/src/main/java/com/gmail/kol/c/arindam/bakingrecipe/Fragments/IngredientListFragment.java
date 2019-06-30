package com.gmail.kol.c.arindam.bakingrecipe.Fragments;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gmail.kol.c.arindam.bakingrecipe.Helper.IngredientListAdapter;
import com.gmail.kol.c.arindam.bakingrecipe.MainActivity;
import com.gmail.kol.c.arindam.bakingrecipe.Model.Recipe;
import com.gmail.kol.c.arindam.bakingrecipe.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class IngredientListFragment extends Fragment {

    private Recipe currentRecipe;

    public IngredientListFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        //load saved state
        if(savedInstanceState != null) {
            currentRecipe = savedInstanceState.getParcelable(MainActivity.RECIPE_SELECTED);
        }

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_ingredient_list, container, false);

        // create recycler view & adapter
        RecyclerView ingredientList = view.findViewById(R.id.ingredient_list);
        IngredientListAdapter adapter = new IngredientListAdapter();

        ingredientList.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false));
        ingredientList.setAdapter(adapter);
        adapter.setIngredients(currentRecipe.ingredients);

        return view;
    }

    //get recipe from activity
    public void setCurrentRecipe (Recipe recipe) {
        currentRecipe = recipe;
    }

    //save current state of the fragment
    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        outState.putParcelable(MainActivity.RECIPE_SELECTED,currentRecipe);
        super.onSaveInstanceState(outState);
    }
}
