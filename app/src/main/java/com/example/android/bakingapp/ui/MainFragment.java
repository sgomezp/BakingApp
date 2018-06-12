package com.example.android.bakingapp.ui;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
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

public class MainFragment extends Fragment {

    private MainActivity mParentActivity;
    //private Boolean mTablet;
    public StepAdapter mStepAdapter;
    public IngredientAdapter mIngredientAdapter;


    //private List<Step> mSteps;
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


    // Mandatory Empty Constructor
    public MainFragment(){}

    // Override  on Attach to make sure that the container activity has implemented the callback
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        // This makes sure that the host activity has impleented tha callback interface
        // If not, it throws an exception
        try {
            mCallback = (OnStepClickListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() +
                    "must implement OnRecipeClickListener");
        }
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        final View rootView = inflater.inflate(R.layout.fragment_main, container, false);
        //displayRecipeIngredients();


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



        //Resources res = getResources();
        //int numbersOfColumns = res.getInteger(R.integer.list_columns);

        // use a linearLayout manager
        //layoutManager = new GridLayoutManager(getContext(), numbersOfColumns);
        //layoutManager = new LinearLayoutManager(getContext());
        //recyclerView.setLayoutManager(layoutManager);


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

   /* public void displayRecipeIngredients() {
        layoutManager = new LinearLayoutManager(getActivity());
        //Set the adapter
        mIngredientAdapter = new IngredientAdapter(mIngredients,
                R.layout.list_item_ingredient, getContext());
        recyclerViewIngredients.setAdapter(mIngredientAdapter);
        recyclerViewIngredients.setLayoutManager(layoutManager);
        recyclerViewIngredients.setHasFixedSize(true);
        mIngredientAdapter.updateRecyclerIngredients(mIngredients);


    }*/


    public interface OnStepClickListener {
        void onStepSelected(int position);
    }





}
