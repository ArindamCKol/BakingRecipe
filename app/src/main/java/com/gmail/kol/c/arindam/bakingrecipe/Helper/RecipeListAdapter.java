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

import java.util.ArrayList;
import java.util.List;

//adapter to show recipe item in recycler view
public class RecipeListAdapter extends RecyclerView.Adapter <RecipeListAdapter.RecipeListViewHolder> {
    private List<Recipe> recipes = new ArrayList<>();
    private RecipeClickListener recipeClickListener;
    public RecipeListAdapter(RecipeClickListener clickListener) {
        recipeClickListener = clickListener;
    }

    public class RecipeListViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView recipeItem;
        public RecipeListViewHolder(@NonNull View itemView) {
            super(itemView);
            recipeItem = itemView.findViewById(R.id.recipe_name_tv);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            Recipe currentRecipe = recipes.get(getAdapterPosition());
            recipeClickListener.onClick(currentRecipe);
        }
    }

    //interface for recycler view item click
    public interface RecipeClickListener {
        void onClick(Recipe recipe);
    }

    @NonNull
    @Override
    public RecipeListAdapter.RecipeListViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        Context context = viewGroup.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.recipe_list_item,viewGroup,false);

        return new RecipeListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecipeListViewHolder viewHolder, int position) {
        viewHolder.recipeItem.setText(recipes.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return recipes.size();
    }

    //get list of recipe
    public void setRecipeList(List<Recipe> recipeList) {
        recipes = recipeList;
        notifyDataSetChanged();
    }
}
