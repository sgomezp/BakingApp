package com.example.android.bakingapp.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.android.bakingapp.R;

public class MainFragment extends Fragment {
    private MainActivity mParentActivity;
    private Boolean mTablet;

    // Empty Constructor
    public MainFragment(){}

    /*@BindView(R.id.tv_text_fragment_main)
    TextView mTvTextFragment = null;*/




    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){

       // mParentActivity = (MainActivity) getActivity();
        final View rootView = inflater.inflate(R.layout.fragment_main, container, false);
        TextView mTvTextFragment = (TextView) rootView.findViewById(R.id.tv_text_fragment_main);


        mTablet = ((DetailActivity) getActivity()).isTablet();

        if (!mTablet){
            // This is phone mode, create an intent to launch DetailActivity. Attach EXTRA
            //Intent intent = new Intent(getActivity(), DetailsActivitySteps.class);
            Intent intent = new Intent(getActivity(), DetailsActivitySteps.class);
            //intent.putExtra("EXTRA", "this fragment es para listado de ingredientes");
            startActivity(intent);
        } else {
            // This is table mode, tell DetailsActivity to replace the fragment in Container
            ((DetailActivity) getActivity()).replaceFragment();

        }
        mTvTextFragment.setText("Esta es la lista de recetas y steps");
        return rootView;

    }


}
