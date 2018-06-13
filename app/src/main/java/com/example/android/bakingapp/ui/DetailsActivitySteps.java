package com.example.android.bakingapp.ui;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

import com.example.android.bakingapp.R;
import com.example.android.bakingapp.model.Recipe;
import com.example.android.bakingapp.model.Step;
import com.example.android.bakingapp.utils.Constants;

import timber.log.Timber;

public class DetailsActivitySteps extends AppCompatActivity {

    public static Recipe mRecipes;
    public static Step mSteps;
    public FragmentManager mFragmentManager;
    Bundle dataSteps = new Bundle();
    private Boolean mTabletMode = false;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_videos);

        //mRecipes = getIntent().getExtras().getParcelable(Constants.RECIPE_KEY);
        mSteps = getIntent().getExtras().getParcelable(Constants.INTENT_KEY_SELECTED_STEP);
        Timber.d("mSteps: " + mSteps.toString());


        dataSteps.putParcelable("dataSteps", mSteps);

        mFragmentManager = getSupportFragmentManager();

        if (savedInstanceState == null) {

            StepFragment stepFragment = new StepFragment();
            stepFragment.setArguments(dataSteps);

            mFragmentManager
                    .beginTransaction()
                    .add(R.id.video_framelayout, stepFragment)
                    .commit();

            /*if (findViewById(R.id.container) != null) {
                mTabletMode = true;
                DetailFragment detailFragment = new DetailFragment();
                detailFragment.setArguments(dataSteps);
                FragmentManager fragmentManager = getSupportFragmentManager();
                fragmentManager
                        .beginTransaction()
                        .add(R.id.container, detailFragment)
                        .commit();
            }*/
        }

      /* if (getIntent() != null){
             //Get EXTRA from intent  and attach to Fragment as Argument
           String text = getIntent().getStringExtra("EXTRA");
            Timber.d("DetailsActivitySteps, text: " + text);
            Bundle args = new Bundle();
            args.putString("ARGUMENTS", text);
            DetailFragment detailFragment = new DetailFragment();
            detailFragment.setArguments(args);
            // Add the fragment to its container using a FragmentManager and a Transaction
            FragmentManager fragmentManager = getSupportFragmentManager();

            fragmentManager
                    .beginTransaction()
                    .replace(R.id.container, detailFragment)
                    .commit();
      }*/
    }


}
