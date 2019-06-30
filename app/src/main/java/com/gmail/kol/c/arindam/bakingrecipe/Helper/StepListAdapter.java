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

//recycler view adapter for steps list + ingredient
public class StepListAdapter extends RecyclerView.Adapter <StepListAdapter.RecipeStepsViewHolder> {
    private List<Recipe.Step> steps;
    private StepClickListener stepClickListener;

    public StepListAdapter(StepClickListener listener) {
        stepClickListener = listener;
    }

    public class RecipeStepsViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView recipeStepDescription;
        public RecipeStepsViewHolder(@NonNull View itemView) {
            super(itemView);
            recipeStepDescription = itemView.findViewById(R.id.recipe_step_description);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            stepClickListener.onClick(getAdapterPosition());
        }
    }

    //interface for recycler view item click
    public interface StepClickListener {
        void onClick(int position);
    }

    @NonNull
    @Override
    public StepListAdapter.RecipeStepsViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        Context context = viewGroup.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.step_list_item,viewGroup,false);

        return new RecipeStepsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecipeStepsViewHolder viewHolder, int position) {
        if(position == 0) {
            //show ingredient as first item
            viewHolder.recipeStepDescription.setText("Ingredients");
        } else {
            //show steps description in other items
            viewHolder.recipeStepDescription.setText(steps.get(position-1).getShortDescription());
        }
    }

    @Override
    public int getItemCount() {
        return (steps.size()+1);
    }

    public void setSteps(List<Recipe.Step> stepList ) {
        steps = stepList;
    }
}
