package com.example.android.bakingapp.ui;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

import com.example.android.bakingapp.R;

import timber.log.Timber;

 public  class  DetailActivity extends AppCompatActivity {

     // Track whether to display a two-pane or single-pane UI
     // A single-pane display refers to phone screens, and two-pane to larger tablet screens

    private Boolean mTabletMode = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        if(findViewById(R.id.video_steps_container)!= null){
            mTabletMode = true;
            DetailFragment detailFragment = new DetailFragment();
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager
                    .beginTransaction()
                    .add(R.id.video_steps_container, detailFragment)
                    .commit();
        }
    }


    public boolean isTablet(){
        return mTabletMode;
    }

    public void replaceFragment(){
        Timber.d("replace");
        Bundle args = new Bundle();
        args.putString("ARGUMENTS","Create from DetailActivity");
        DetailFragment detailFragment = new DetailFragment();
        detailFragment.setArguments(args);
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.video_steps_container, detailFragment)
                .commit();
    }
}
