package com.example.android.bakingapp.ui;

import android.os.Bundle;
import android.os.Parcelable;
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

import timber.log.Timber;

public class DetailActivity extends AppCompatActivity implements MainFragment.OnStepClickListener {

    // Track whether to display a two-pane or single-pane UI
    // A single-pane display refers to phone screens, and two-pane to larger tablet screens

    private Boolean mTabletMode = false;
    public static Recipe mRecipes = new Recipe();
    public static ArrayList<Step> mStepList;
    public static List<Ingredient> mIngredientList;
    int position;
    private Bundle dataRecipe;
    //public IngredientStepAdapter mStepAdapter;
    //public RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);


        Bundle data = getIntent().getExtras();
        position = data.getInt("position");
        //mIngredientList = data.getParcelable("mRecipeList");
        mRecipes = data.getParcelable("mRecipeList");
        Timber.d("mRecipeList: " + mRecipes);
        //mRecipeList =

        if (getIntent().getExtras() != null) {
            dataRecipe = getIntent().getExtras();
            mRecipes = dataRecipe.getParcelable(Constants.INTENT_KEY_SELECTED_RECIPE);
        }


        if (findViewById(R.id.container) != null) {
            mTabletMode = true;
            DetailFragment detailFragment = new DetailFragment();
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager
                    .beginTransaction()
                    .replace(R.id.container, detailFragment)
                    .commit();
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
        Timber.d("replace");

        Bundle args = new Bundle();
        args.putInt("position", position);
        args.putParcelable("mRecipeList", (Parcelable) mRecipes);
        DetailFragment detailFragment = new DetailFragment();
        detailFragment.setArguments(args);
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, detailFragment)
                .commit();
    }


}
