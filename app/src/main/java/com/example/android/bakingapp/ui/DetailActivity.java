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

import java.util.ArrayList;
import java.util.List;

public class DetailActivity extends AppCompatActivity implements MainFragment.OnStepClickListener {

    // Track whether to display a two-pane or single-pane UI
    // A single-pane display refers to phone screens, and two-pane to larger tablet screens

    private Boolean mTabletMode = false;
    public static Recipe mRecipes;
    public static ArrayList<Step> mStepList;
    public static List<Ingredient> mIngredientList;
    private FragmentManager mFragmentManager;
    Bundle dataIngredients = new Bundle();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        mRecipes = getIntent().getExtras().getParcelable(Constants.RECIPE_KEY);


        dataIngredients.putParcelable("dataIngredients", mRecipes);

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
                DetailFragment detailFragment = new DetailFragment();
                detailFragment.setArguments(dataIngredients);
                FragmentManager fragmentManager = getSupportFragmentManager();
                fragmentManager
                        .beginTransaction()
                        .add(R.id.container, detailFragment)
                        .commit();
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
