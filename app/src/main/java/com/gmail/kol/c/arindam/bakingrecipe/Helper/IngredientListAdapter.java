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

//recycler view adapter for list of ingredients
public class IngredientListAdapter extends RecyclerView.Adapter<IngredientListAdapter.IngredientViewHolder> {
    private List<Recipe.Ingredient> ingredientList;

    public class IngredientViewHolder extends RecyclerView.ViewHolder {
        TextView ingredientName;
        TextView ingredientQty;
        public IngredientViewHolder(@NonNull View itemView) {
            super(itemView);
            ingredientName = itemView.findViewById(R.id.ingredient_name);
            ingredientQty = itemView.findViewById(R.id.ingredient_qty);
        }
    }

    @NonNull
    @Override
    public IngredientListAdapter.IngredientViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        Context context = viewGroup.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.ingredient_list_item,viewGroup,false);

        return new IngredientViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull IngredientViewHolder viewHolder, int position) {
        viewHolder.ingredientName.setText(ingredientList.get(position).getIngredient());
        viewHolder.ingredientQty.setText(Float.toString(ingredientList.get(position).getQuantity()) + " " + ingredientList.get(position).getMeasure());
    }

    @Override
    public int getItemCount() {
        return ingredientList.size();
    }

    //get ingredients list
    public void setIngredients(List<Recipe.Ingredient> ingredients) {
        ingredientList = ingredients;
        notifyDataSetChanged();
    }
}
