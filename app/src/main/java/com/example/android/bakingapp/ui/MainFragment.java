package com.example.android.bakingapp.ui;

import android.content.Context;
import android.content.Intent;
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
import com.example.android.bakingapp.utils.ApiClient;
import com.example.android.bakingapp.utils.ApiInterface;

import java.nio.channels.NotYetConnectedException;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindString;
import butterknife.BindView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import timber.log.Timber;

import static com.example.android.bakingapp.ui.DetailActivity.mIngredientList;
import static com.example.android.bakingapp.ui.DetailActivity.mStepList;

public class MainFragment extends Fragment {

    private MainActivity mParentActivity;
    private Boolean mTablet;
    public StepAdapter mStepAdapter;
    public IngredientAdapter mIngredientAdapter;


    public List<Step> mSteps;
    public Recipe recipeSelected = null;

    RecyclerView.LayoutManager layoutManager;

    @BindView(R.id.tv_ingredients)
    TextView mTvIngredients;

    //@BindView(R.id.rv_ingredients)
    RecyclerView recyclerViewIngredients;

    @BindView(R.id.rv_ingredients_steps)
    RecyclerView recyclerView;

    @BindString(R.string.display_ingredient)
    String mDisplayIngredient;
    // Define a new interface OnStepClickListener that triggers a callback in the host activity
    // calls a method in the host activity named on RecipeSelected
    OnStepClickListener mCallback;
    private List<Ingredient> mIngredients;

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

        recyclerViewIngredients = rootView.findViewById(R.id.rv_ingredients);
        Bundle dataIngredients = getArguments();
        recipeSelected = dataIngredients.getParcelable("dataIngredients");
        mIngredients = recipeSelected.getIngredients();




        //Resources res = getResources();
        //int numbersOfColumns = res.getInteger(R.integer.list_columns);

        // use a linearLayout manager
        //layoutManager = new GridLayoutManager(getContext(), numbersOfColumns);
        //layoutManager = new LinearLayoutManager(getContext());
        //recyclerView.setLayoutManager(layoutManager);

        //Creates the adapter
        // This adapter takes in the context shows the ingredients and steps

        // Set the adapter to the RecyclerView

        displayRecipeIngredients();



        // Set a click listener on the RecyclerView and trigger the callback onRecipeSelected
        // when an item is clicked


        //Load recipe data
        //loadIngredientData();

        //loadStepData();


        //***************************

        //mTvIngredients = (TextView) rootView.findViewById(R.id.tv_ingredients);


        recyclerViewIngredients.setOnClickListener(new View.OnClickListener() {
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
        });

        //mTvTextFragment.setText("Esta es la lista de recetas y steps");
        return rootView;

    }

    private void displayRecipeIngredientsOld() {

        StringBuilder ingredientString = new StringBuilder();

        for (Ingredient ingredient : mIngredients) {
            ingredientString.append(
                    String.format(
                            mDisplayIngredient,
                            ingredient.getIngredient(),
                            Double.toString(ingredient.getQuantity()),
                            ingredient.getMeasure().toLowerCase()
                    )
            );
        }

        mTvIngredients.setText(ingredientString.toString());

    }

    public void displayRecipeSteps() {
        RecyclerView.LayoutManager layoutManagerSteps = new LinearLayoutManager(mParentActivity);
        recyclerView.setLayoutManager(layoutManagerSteps);

        //Set the adapter
        mStepAdapter = new StepAdapter(mStepList,
                R.layout.list_item_ingredient, getContext());
        recyclerView.setAdapter(mStepAdapter);
        mStepAdapter.updateRecyclerSteps(mSteps);


    }

    public void displayRecipeIngredients() {
        layoutManager = new LinearLayoutManager(getActivity());
        //Set the adapter
        mIngredientAdapter = new IngredientAdapter(mIngredients,
                R.layout.list_item_ingredient, getContext());
        recyclerViewIngredients.setAdapter(mIngredientAdapter);
        recyclerViewIngredients.setLayoutManager(layoutManager);
        recyclerViewIngredients.setHasFixedSize(true);
        mIngredientAdapter.updateRecyclerIngredients(mIngredients);


    }

    public void loadStepData() {

        try {

            ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
            Call<ArrayList<Step>> call = apiService.getSteps();

            call.enqueue(new Callback<ArrayList<Step>>() {
                @Override
                public void onResponse(Call<ArrayList<Step>> call, Response<ArrayList<Step>> response) {
                    int statusCode = response.code();

                    if (response.isSuccessful()) {
                        mStepList = response.body();

                        if (mStepAdapter == null) {
                            recyclerView.setAdapter(new StepAdapter(mStepList,
                                    R.layout.list_item_ingredient, getContext()));
                            recyclerView.setHasFixedSize(false);
                        } else {
                            mStepAdapter.updateRecyclerSteps(mStepList);
                            mStepAdapter.notifyDataSetChanged();
                        }

                    } else {
                        Timber.d("onResponse: StatusCode; " + statusCode);
                    }
                }

                @Override
                public void onFailure(Call<ArrayList<Step>> call, Throwable t) {
                    Timber.d("onResponse: Error en Failure; ");

                }
            });
        } catch (NotYetConnectedException e) {
            Timber.d("loadRecipeData: No connectivity: " + e.getMessage());
        }
    }

    public void loadIngredientData() {

        try {

            ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
            Call<ArrayList<Ingredient>> call = apiService.getIngredients();

            call.enqueue(new Callback<ArrayList<Ingredient>>() {
                @Override
                public void onResponse(Call<ArrayList<Ingredient>> call, Response<ArrayList<Ingredient>> response) {
                    int statusCode = response.code();

                    if (response.isSuccessful()) {
                        Timber.d("response was successful");
                        mIngredientList = response.body();

                        if (mIngredientAdapter == null) {
                            Timber.d("mIngredientAdapter is null: " + mIngredientAdapter);
                            recyclerView.setAdapter(new IngredientAdapter(
                                    mIngredientList, R.layout.list_item_ingredient, getContext()));
                            Timber.d("mIngredientAdapter is: " + mIngredientAdapter);
                            recyclerView.setHasFixedSize(false);
                        } else {
                            mIngredientAdapter.updateRecyclerIngredients(mIngredientList);
                            mIngredientAdapter.notifyDataSetChanged();
                        }

                    } else {
                        Timber.d("onResponse: StatusCode; " + statusCode);
                    }
                }

                @Override
                public void onFailure(Call<ArrayList<Ingredient>> call, Throwable t) {
                    Timber.d("onResponse: Error en Failure; ");

                }
            });
        } catch (NotYetConnectedException e) {
            Timber.d("loadRecipeData: No connectivity: " + e.getMessage());
        }
    }

    public interface OnStepClickListener {
        void onStepSelected(int position);
    }





}
