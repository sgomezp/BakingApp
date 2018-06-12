package com.example.android.bakingapp.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.android.bakingapp.R;
import com.example.android.bakingapp.model.Step;
import com.example.android.bakingapp.ui.DetailsActivitySteps;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import timber.log.Timber;

public class StepAdapter extends RecyclerView.Adapter<StepAdapter.ViewHolder> {

    private List<Step> mStepList;
    private Context mContext;
    private int mGridLayout;


    public StepAdapter(List<Step> steps,
                       int gridLayout, Context context) {

        mStepList = steps;
        mGridLayout = gridLayout;
        mContext = context;
    }


    @NonNull
    @Override
    public StepAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(mGridLayout, parent, false);
        return new StepAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull StepAdapter.ViewHolder holder, int position) {

        Timber.d("Position: " + position);
        Step steps = mStepList.get(position);
        int id_step = steps.getId();
        String shortDescription = steps.getShortDescription();
        String description = steps.getDescription();
        String videoUrl = steps.getVideoURL();
        String thumbnailUrl = steps.getThumbnailURL();

        // Setters Ingredients fields
        holder.tvShortDescription.setText(shortDescription);

    }

    @Override
    public int getItemCount() {
        if (mStepList == null) {
            return 0;
        } else {
            return mStepList.size();
        }
    }


    public void updateRecyclerSteps(List<Step> newStep) {
        mStepList = newStep;
        notifyDataSetChanged();
    }


    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        View gridLayout;

        @BindView(R.id.short_description)
        TextView tvShortDescription;


        public ViewHolder(View itemView) {
            super(itemView);
            gridLayout = itemView;
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(this);

        }

        /**
         * Called when a view has been clicked.
         *
         * @param view The view that was clicked.
         */
        @Override
        public void onClick(View view) {
            // gets item position
            int position = getAdapterPosition();
            // Check if an item was deleted, but user clicked it before the UI removed it
            // We can access the data within the views
            if (position != RecyclerView.NO_POSITION) {
                Intent intent = new Intent(mContext.getApplicationContext(), DetailsActivitySteps.class);
                //Intent intent = new Intent(mContext, DetailActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_MULTIPLE_TASK);
                //intent.putExtra("position", position);
                intent.putExtra("position", position);
                mContext.getApplicationContext().startActivity(intent);
                //mContext.startActivity(intent);

            }

        }


    }


}
