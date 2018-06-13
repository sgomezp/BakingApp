package com.example.android.bakingapp.ui;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.android.bakingapp.R;
import com.example.android.bakingapp.model.Step;

import java.util.List;

public class StepFragment extends Fragment {

    public Step stepSelected = null;
    String mDescription;
    String mShortDescription;
    private List<Step> mSteps;

    // Mandatory Empty Constructor
    public StepFragment() {

    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        final View rootView = inflater.inflate(R.layout.fragment_details, container, false);

        TextView mTvShortDescription = rootView.findViewById(R.id.short_description);
        TextView mTvStepDescription = rootView.findViewById(R.id.tv_step_description);


        Bundle dataSteps = getArguments();
        stepSelected = dataSteps.getParcelable("dataSteps");
        mDescription = stepSelected.getDescription();
        mShortDescription = stepSelected.getShortDescription();


        mTvShortDescription.setText(mShortDescription);
        mTvStepDescription.setText(mDescription);

        return rootView;

    }
}
