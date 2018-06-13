package com.example.android.bakingapp.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.android.bakingapp.R;
import com.example.android.bakingapp.adapters.IngredientAdapter;
import com.example.android.bakingapp.adapters.StepAdapter;
import com.example.android.bakingapp.model.Ingredient;
import com.example.android.bakingapp.model.Recipe;
import com.example.android.bakingapp.model.Step;

import java.util.List;

import butterknife.BindString;

// Este Fragment es que incluye a la izquierda los ingredientes y pasos y a la derecha el video
// Y el layout es el activity_detail para tablets

public class DetailFragment extends Fragment {


    public StepAdapter mStepAdapter;
    public IngredientAdapter mIngredientAdapter;


    public Recipe recipeSelected = null;

    RecyclerView.LayoutManager layoutManager;
    RecyclerView.LayoutManager layoutManagerSteps;

    //@BindView(R.id.tv_ingredients)
    TextView mTvIngredients;

    //@BindView(R.id.rv_ingredients)
    RecyclerView recyclerViewIngredients;

    //@BindView(R.id.rv_ingredients_steps)
    RecyclerView recyclerViewSteps;

    @BindString(R.string.display_ingredient)
    String mDisplayIngredient;
    // Define a new interface OnStepClickListener that triggers a callback in the host activity
    // calls a method in the host activity named on RecipeSelected
    OnStepClickListener mCallback;
    private List<Ingredient> mIngredients;
    private List<Step> mSteps;



    public DetailFragment(){

    }

    /*@BindView(R.id.tv_step_description)
    TextView mtvStep;*/

    /*@BindView(R.id.playerview_recipe_video)
    SimpleExoPlayerView mPlayerRecipeVideo;

    @BindView(R.id.layout_video)
    LinearLayout mLayoutVideo;

    @BindView(R.id.layout_video_steps_details)
    LinearLayout mLayoutVideoStepsDetails;*/



    @Nullable
    @Override
    public View onCreateView(LayoutInflater  inflater, ViewGroup container, Bundle savedInstanceState){
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);
        //ButterKnife.bind(this,rootView);

        TextView mTvIngredients = rootView.findViewById(R.id.tv_ingredients);

        //recyclerViewIngredients = rootView.findViewById(R.id.rv_ingredients);
        Bundle dataIngredients = getArguments();
        recipeSelected = dataIngredients.getParcelable("dataIngredients");
        mIngredients = recipeSelected.getIngredients();

        StringBuilder ingredientString = new StringBuilder();

        for (Ingredient mIng : mIngredients) {
            String ingredientName = mIng.getIngredient();
            float ingredientQuantity = mIng.getQuantity();
            String ingredientMeasure = mIng.getMeasure();

            ingredientString.append(ingredientQuantity);
            ingredientString.append(" ");
            ingredientString.append(ingredientMeasure.toLowerCase());
            ingredientString.append(" ");
            ingredientString.append(ingredientName);
            ingredientString.append("\n");


        }
        mTvIngredients.setText(ingredientString);


        // Para los steps
        recyclerViewSteps = rootView.findViewById(R.id.rv_steps);
        mSteps = recipeSelected.getSteps();


        //displayRecipeIngredientsOld();
        displayRecipeSteps();

        // Este hay que ponerlo para los Steps. No para Ingredientes
        /*recyclerViewIngredients.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mTablet = ((DetailActivity) getActivity()).isTablet();
                if (!mTablet){
                    // This is phone mode, create an intent to launch DetailActivity. Attach EXTRA
                    //Intent intent = new Intent(getActivity(), DetailsActivitySteps.class);
                    Intent intent = new Intent(getActivity(), DetailsActivitySteps.class);
                    intent.putExtra("position", "this fragment es para los videos");
                    startActivity(intent);
                } else {
                    // This is table mode, tell DetailsActivity to replace the fragment in Container
                    ((DetailActivity) getActivity()).replaceFragment();

                }

            }
        });*/

        //mTvTextFragment.setText("Esta es la lista de recetas y steps");
        return rootView;

    }

    public void displayRecipeSteps() {
        layoutManagerSteps = new LinearLayoutManager(getActivity());
        //Set the adapter
        mStepAdapter = new StepAdapter(mSteps,
                R.layout.list_item_step, getContext());
        recyclerViewSteps.setAdapter(mStepAdapter);
        recyclerViewSteps.setLayoutManager(layoutManagerSteps);
        recyclerViewSteps.setHasFixedSize(true);
        mStepAdapter.updateRecyclerSteps(mSteps);


    }

    public interface OnStepClickListener {
        void onStepSelected(int position);
    }



}
