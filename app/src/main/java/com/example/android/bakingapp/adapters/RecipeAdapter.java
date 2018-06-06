package com.example.android.bakingapp.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.android.bakingapp.R;
import com.example.android.bakingapp.model.Recipe;
import com.example.android.bakingapp.ui.DetailActivity;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import timber.log.Timber;

import static android.text.TextUtils.isEmpty;

public class RecipeAdapter  extends RecyclerView.Adapter<RecipeAdapter.ViewHolder>{


    private ArrayList<Recipe> mRecipeList;
    private Context mContext;
    private int mGridLayout;

    public RecipeAdapter(ArrayList<Recipe> recipes, int gridLayout, Context context){
        mRecipeList = recipes;
        mGridLayout = gridLayout;
        mContext = context;
    }


    @NonNull
    @Override
    public RecipeAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Create a new View
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.list_item_recipe, parent, false);
        return  new ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull RecipeAdapter.ViewHolder holder, int position) {

         Recipe recipe = mRecipeList.get(position);
         String recipeName = recipe.getName();
         int servings = recipe.getServings();
         String uriImage = recipe.getImage();

        Timber.d("UriImage: " + uriImage);

         // Set recipe name
         if (!isEmpty(recipeName)){
             holder.tvNameRecipe.setText(recipeName);
         }
         if (servings != 0){
             holder.tvServingsRecipe.setText (mContext.getString((R.string.servings_text) , servings));
         } else {
             holder.tvServingsRecipe.setText(R.string.servings_unavailable);
         }

        // set recipe image if available

        if (!isEmpty(uriImage)) {

            Picasso.with(mContext)
                    .load(uriImage)
                    .placeholder(R.drawable.ic_placeholder_cake_black)
                    .error(R.drawable.ic_placeholder_cake_black)
                    .into(holder.ivImageRecipe);
        } else {
             holder.ivImageRecipe.setImageResource(R.drawable.ic_placeholder_cake_black);
        }

    }

    // Replace the contents of a view (invoke by the layout manager)
    public void updateRecyclerData(ArrayList<Recipe> newRecipes){
        mRecipeList = newRecipes;
        notifyDataSetChanged();
    }


    // Return the size of your dataset (invoked by the layout manager)

    @Override
    public int getItemCount() {
        if (mRecipeList == null){
            return 0;
        }else {
            return mRecipeList.size();
        }
    }



    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        View gridLayout;

        @BindView(R.id.iv_image_recipe)
        ImageView ivImageRecipe;

        @BindView(R.id.tv_name_recipe)
        TextView tvNameRecipe;

        @BindView(R.id.tv_servings_recipe)
        TextView tvServingsRecipe;


        public ViewHolder(View itemView){
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
            Recipe recipe = mRecipeList.get(position);
            // Check if an item was deleted, but user clicked it before the UI removed it
            // We can access the data within the views
            if (position != RecyclerView.NO_POSITION){
                Intent intent = new Intent(mContext.getApplicationContext(), DetailActivity.class);
                //Intent intent = new Intent(mContext, DetailActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_MULTIPLE_TASK);
                //intent.putExtra("position", position);
                Timber.d("position que estoy pasando: " + position);

                // dataIngredients = new Bundle();
                //dataIngredients.putInt("position",position);
                //dataIngredients.putParcelable("mRecipeList", mRecipeList);


                intent.putExtra("position", position);

                Timber.d("mRecipelist que paso es: " + recipe);
                Timber.d("mRecipeList is: " + recipe.toString());
                intent.putExtra("mRecipeList", recipe);
                mContext.getApplicationContext().startActivity(intent);
                //mContext.startActivity(intent);

            }

        }






    }

}
