package com.example.android.bakingapp.ui;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.example.android.bakingapp.R;
import com.example.android.bakingapp.model.Ingredient;
import com.example.android.bakingapp.model.Recipe;
import com.example.android.bakingapp.model.Step;
import com.example.android.bakingapp.utils.Constants;

import java.util.List;

public class DetailActivity extends AppCompatActivity implements MainFragment.OnStepClickListener {

    // Track whether to display a two-pane or single-pane UI
    // A single-pane display refers to phone screens, and two-pane to larger tablet screens

    public static List<Step> mStepList;
    public static Recipe mRecipes;
    public static Step mSteps;
    public static List<Ingredient> mIngredientList;
    public Boolean mTabletMode = false;
    Bundle dataIngredients = new Bundle();
    public FragmentManager mFragmentManager;
    Bundle dataSteps = new Bundle();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        mRecipes = getIntent().getExtras().getParcelable(Constants.RECIPE_KEY);
        //mSteps = getIntent().getExtras().getParcelable(Constants.INTENT_KEY_SELECTED_STEP);
        mSteps = getIntent().getExtras().getParcelable(Constants.INTENT_KEY_SELECTED_STEP);



        dataIngredients.putParcelable("dataIngredients", mRecipes);
        dataSteps.putParcelable("dataSteps", mSteps);

        mFragmentManager = getSupportFragmentManager();

        if (savedInstanceState == null) {

            MainFragment mainFragment = new MainFragment();
            mainFragment.setArguments(dataIngredients);

            mFragmentManager
                    .beginTransaction()
                    .add(R.id.ingredients_framelayout, mainFragment)
                    .commit();

            if (findViewById(R.id.container) != null) {
                mTabletMode = true;
                /*Timber.d("estoy en modo table y dataStep es: " + dataSteps.toString());
                // Cambio DetailFragment por MainFragment. Ahora lo cambio por StepFragment
                StepFragment stepFragment = new StepFragment();
                stepFragment.setArguments(dataIngredients);

                mFragmentManager
                        .beginTransaction()
                        .add(R.id.container, stepFragment)
                        .commit();*/



            }
        }
    }

    // Define the behavior for OnRecipeSelected
    @Override
    public void onStepSelected(int position) {
        //Create a toast that display the position that was clicked
        Toast.makeText(this, "Position clicked = " + position, Toast.LENGTH_SHORT).show();

    }


    public boolean isTablet() {

        return mTabletMode;
    }

    public void replaceFragment() {

        DetailFragment detailFragment = new DetailFragment();
        detailFragment.setArguments(dataIngredients);
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, detailFragment)
                .commit();
    }


}
