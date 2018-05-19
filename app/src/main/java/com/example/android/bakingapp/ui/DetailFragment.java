package com.example.android.bakingapp.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.android.bakingapp.R;

import butterknife.BindView;
import timber.log.Timber;

public class DetailFragment extends Fragment {

    public DetailFragment(){

    }

    @BindView(R.id.tv_step)
    TextView mtvStep;

    /*@BindView(R.id.playerview_recipe_video)
    SimpleExoPlayerView mPlayerRecipeVideo;

    @BindView(R.id.layout_video)
    LinearLayout mLayoutVideo;

    @BindView(R.id.layout_video_steps_details)
    LinearLayout mLayoutVideoStepsDetails;*/

    @Nullable
    @Override
    public View onCreateView(LayoutInflater  inflater, ViewGroup container, Bundle savedInstanceState){
        View rootView = inflater.inflate(R.layout.fragment_video_steps, container, false);
        if (getArguments() != null){
            String text = getArguments().getString("ARGUMENTS");
            Timber.d("text: " + text);
            mtvStep.setText(text);
        } else {
            Timber.d("This fragment has a null list of steps and videos");
        }

        //mtvStep.setText("Aqui van los videso y pasos de la receta");

        return rootView;
    }


}
