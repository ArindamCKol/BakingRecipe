package com.gmail.kol.c.arindam.bakingrecipe.Helper;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.gmail.kol.c.arindam.bakingrecipe.Model.Recipe;
import com.gmail.kol.c.arindam.bakingrecipe.R;

import java.util.List;

public class RecipeStepsAdapter extends RecyclerView.Adapter <RecipeStepsAdapter.RecipeStepsViewHolder> {
    private List<Recipe.Step> steps;

    public class RecipeStepsViewHolder extends RecyclerView.ViewHolder {
        public TextView recipeStepDescription;
        public RecipeStepsViewHolder(@NonNull View itemView) {
            super(itemView);
            recipeStepDescription = itemView.findViewById(R.id.recipe_step_description);
        }
    }

    @NonNull
    @Override
    public RecipeStepsAdapter.RecipeStepsViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        Context context = viewGroup.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.recipe_steps_item,viewGroup,false);

        return new RecipeStepsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecipeStepsViewHolder viewHolder, int position) {
        viewHolder.recipeStepDescription.setText(steps.get(position).getShortDescription());
    }

    @Override
    public int getItemCount() {
        return steps.size();
    }

    public void setSteps(List<Recipe.Step> stepList ) {
        steps = stepList;
    }
}
