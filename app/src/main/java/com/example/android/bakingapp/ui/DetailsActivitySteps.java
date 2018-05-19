package com.example.android.bakingapp.ui;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

import com.example.android.bakingapp.R;

import timber.log.Timber;

public class DetailsActivitySteps extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_master);

       if (getIntent() != null){
             //Get EXTRA from intent  and attach to Fragment as Argument
           String text = getIntent().getStringExtra("EXTRA");
            Timber.d("DetailsActivitySteps, text: " + text);
            Bundle args = new Bundle();
            args.putString("ARGUMENTS", text);
            MainFragment mainFragment = new MainFragment();
            mainFragment.setArguments(args);
            // Add the fragment to its container using a FragmentManager and a Transaction
            FragmentManager fragmentManager = getSupportFragmentManager();

            fragmentManager
                    .beginTransaction()
                    .add(R.id.master_list_fragment, mainFragment)
                    .commit();
      }
    }


}