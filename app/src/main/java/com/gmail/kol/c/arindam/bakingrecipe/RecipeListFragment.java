package com.gmail.kol.c.arindam.bakingrecipe;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

//Fragment to hold recipe list
/**
 * A simple {@link Fragment} subclass.
 */
public class RecipeListFragment extends Fragment {
    private String errorText;
    private String tempText;

    public RecipeListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.fragment_recipe_list, container, false);

        TextView errorTextView = rootView.findViewById(R.id.error_text);
        TextView temp = rootView.findViewById(R.id.temp);

        if (errorText == null) {
            temp.setVisibility(View.VISIBLE);
            errorTextView.setVisibility(View.GONE);
            temp.setText(tempText);
        } else {
            temp.setVisibility(View.GONE);
            errorTextView.setVisibility(View.VISIBLE);
            errorTextView.setText(errorText);
        }
        return rootView;
    }
    public void setErrorText (String errorString) {errorText = errorString;}
    public void setTempText (String tempString) {tempText = tempString;}
}
