package com.example.android.bakingapp.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.android.bakingapp.R;
import com.example.android.bakingapp.model.Ingredient;
import com.example.android.bakingapp.ui.DetailsActivitySteps;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import timber.log.Timber;

public class IngredientAdapter extends RecyclerView.Adapter<IngredientAdapter.ViewHolder> {

    private List<Ingredient> mIngredientList;
    private Context mContext;
    private int mGridLayout;


    public IngredientAdapter(List<Ingredient> ingredients,
                             int gridLayout, Context context) {

        mIngredientList = ingredients;
        mGridLayout = gridLayout;
        mContext = context;
    }


    @NonNull
    @Override
    public IngredientAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.fragment_main, parent, false);
        return new IngredientAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull IngredientAdapter.ViewHolder holder, int position) {

        Timber.d("Position Ingredient: " + position);
        Ingredient ingredients = mIngredientList.get(position);
        Timber.d("ingredients:" + ingredients.toString());
        String ingredientName = ingredients.getIngredient();
        Timber.d("Ingredients IngredientName: " + ingredientName);
        float quantity = ingredients.getQuantity();
        Timber.d("Ingredients quantity: " + quantity);
        String measure = ingredients.getMeasure();
        Timber.d("Ingredients measure: " + measure);

        // Settter Ingredients fields
        holder.tvIngredients.setText(ingredients.toString());
        /*holder.tvIngredientQuantity.setText(String.valueOf(quantity));
        holder.tvIngredientName.setText(ingredientName);
        holder.tvMeasureIngredient.setText(measure);*/


    }

    @Override
    public int getItemCount() {
        if (mIngredientList == null) {
            return 0;
        } else {
            return mIngredientList.size();
        }
    }


    public void updateRecyclerIngredients(List<Ingredient> newIngredients) {
        mIngredientList = newIngredients;
        notifyDataSetChanged();
    }


    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        View gridLayout;

        @BindView(R.id.tv_ingredients)
        TextView tvIngredients;

        /*@BindView(R.id.tv_ingredient_quantity)
        TextView tvIngredientQuantity;

        @BindView(R.id.tv_measure_ingredient)
        TextView tvMeasureIngredient;

        @BindView(R.id.tv_ingredient_name)
        TextView tvIngredientName;*/


        public ViewHolder(View itemView) {
            super(itemView);
            gridLayout = itemView;
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(this);

        }

        /**
         * Called when a view has been clicked.
         *
         * @param view The view that was clicked.
         */
        @Override
        public void onClick(View view) {
            // gets item position
            int position = getAdapterPosition();
            // Check if an item was deleted, but user clicked it before the UI removed it
            // We can access the data within the views
            if (position != RecyclerView.NO_POSITION) {
                Intent intent = new Intent(mContext.getApplicationContext(), DetailsActivitySteps.class);
                //Intent intent = new Intent(mContext, DetailActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_MULTIPLE_TASK);
                //intent.putExtra("position", position);
                intent.putExtra("position", position);
                mContext.getApplicationContext().startActivity(intent);
                //mContext.startActivity(intent);

            }

        }


    }


}
