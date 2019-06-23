package com.gmail.kol.c.arindam.bakingrecipe.Fragments;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.gmail.kol.c.arindam.bakingrecipe.R;
import com.gmail.kol.c.arindam.bakingrecipe.Model.Recipe;
import com.gmail.kol.c.arindam.bakingrecipe.Helper.RecipeListAdapter;

import java.util.List;

//Fragment to hold recipe list
/**
 * A simple {@link Fragment} subclass.
 */
public class RecipeListFragment extends Fragment implements RecipeListAdapter.RecipeClickListener {
    private String errorText;
    private List<Recipe> recipeList;
    public OnRecipeItemSelected recipeItemSelected;

    public RecipeListFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        recipeItemSelected = (OnRecipeItemSelected) context;
    }

    //interface to communicate with activity
    public interface OnRecipeItemSelected{
        void onRecipeSelected(Recipe recipe);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View rootView = inflater.inflate(R.layout.fragment_recipe_list, container, false);

        TextView errorTextView = rootView.findViewById(R.id.error_text);
        RecyclerView recipeListView = rootView.findViewById(R.id.recipe_list_rv);

        RecipeListAdapter listAdapter = new RecipeListAdapter(this);
        recipeListView.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false));
        recipeListView.setAdapter(listAdapter);

        if (recipeList != null) {
            errorTextView.setVisibility(View.GONE);
            recipeListView.setVisibility(View.VISIBLE);
            listAdapter.setRecipeList(recipeList);
        } else {
            errorTextView.setVisibility(View.VISIBLE);
            recipeListView.setVisibility(View.GONE);
            errorTextView.setText(errorText);
        }
        return rootView;
    }

    //to get error text & recipe list from main activity
    public void setErrorText (String errorString) {errorText = errorString;}
    public void setRecipeList (List<Recipe> recipes) { recipeList = recipes;}

    //on item click method in adapter interface class
    @Override
    public void onClick(Recipe recipe) {
        recipeItemSelected.onRecipeSelected(recipe);
    }
}
