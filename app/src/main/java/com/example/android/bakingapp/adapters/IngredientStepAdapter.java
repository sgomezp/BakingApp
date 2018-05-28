package com.example.android.bakingapp.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.android.bakingapp.R;
import com.example.android.bakingapp.model.Ingredient;
import com.example.android.bakingapp.model.Recipe;
import com.example.android.bakingapp.model.Step;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class IngredientStepAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private final int VIEW_TYPE_INGREDIENTS = 0;
    private final int VIEW_TYPE_STEPS = 1;

    private Recipe mRecipes;
    private ArrayList<Step> mStepList;
    private ArrayList<Ingredient> mIngredientList;
    private Context mContext;
    private int mGridLayout;


    public IngredientStepAdapter(Recipe recipes, ArrayList<Step> steps, ArrayList<Ingredient> ingredients,
                                 int gridLayout, Context context) {
        mRecipes = recipes;
        mStepList = steps;
        mIngredientList = ingredients;
        mGridLayout = gridLayout;
        mContext = context;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return VIEW_TYPE_INGREDIENTS;
        }

        if (position == 1) {
            return VIEW_TYPE_STEPS;
        }

        return -1;
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        if (viewType == VIEW_TYPE_INGREDIENTS) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.list_item_ingredient, parent, false);
            return new ViewHolderIngredient(view);
        } else if (viewType == VIEW_TYPE_STEPS) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.list_item_step, parent, false);
            return new ViewHolderStep(view);
        }

        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        switch (holder.getItemViewType()) {
            case VIEW_TYPE_INGREDIENTS:
                ViewHolderIngredient viewHolderIngredient = (ViewHolderIngredient) holder;
                viewHolderIngredient.bindViews(position);
                break;
            case VIEW_TYPE_STEPS:
                ViewHolderStep viewHolderStep = (ViewHolderStep) holder;
                viewHolderStep.bindViews(position);

        }

    }

    @Override
    public int getItemCount() {
        return 1 + mIngredientList.size() + mStepList.size();
    }

    // Replace the contents of a view (invoke by the layout manager)
    public void updateRecyclerSteps(ArrayList<Step> newSteps) {
        mStepList = newSteps;
        notifyDataSetChanged();
    }

    public void updateRecyclerIngredients(ArrayList<Ingredient> newIngredients) {
        mIngredientList = newIngredients;
        notifyDataSetChanged();
    }

    public class ViewHolderIngredient extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_ingredient_quantity)
        TextView tvIngredientQuantity;

        @BindView(R.id.tv_measure_ingredient)
        TextView tvMeasureIngredient;

        @BindView(R.id.tv_ingredient_name)
        TextView tvIngredientName;


        public ViewHolderIngredient(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

        }

        public void bindViews(int position) {
            Ingredient ingredients = mIngredientList.get(position);
            float quantity = ingredients.getQuantity();
            String measure = ingredients.getMeasure();
            String ingredientName = ingredients.getIngredient();

            tvIngredientQuantity.setText(String.valueOf(quantity));
            tvIngredientName.setText(ingredientName);
            tvMeasureIngredient.setText(measure);

        }
    }

    public class ViewHolderStep extends RecyclerView.ViewHolder {

        @BindView(R.id.short_description)
        TextView tvShortDescription;


        public ViewHolderStep(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

        }

        public void bindViews(int position) {
            Step steps = mStepList.get(position);
            String shortDescription = steps.getShortDescription();
            tvShortDescription.setText(shortDescription);

        }

    }


}
