package com.example.honban_robot2023.Models;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.honban_robot2023.R;
import com.example.honban_robot2023.ResultTable_Activity;
import com.example.honban_robot2023.StatisticsTable_Activity;
import com.example.honban_robot2023.Test.PieChartSample_Activity;
import com.example.honban_robot2023.TimeIntervalsTable_Activity;
import com.example.honban_robot2023.UtilizationTable_Activity;

public class ModeSelectRecycleViewAdapter extends RecyclerView.Adapter<ModeSelectRecycleViewAdapter.ModeSelectCardViewHolder> {

    private Resources androidResource;

    private String[] modeTitles;
    private String[] modeExplanation;

    private Context activityContext;
    int imageResourceIds[] = new int[]{R.drawable.kekka, R.drawable.symbol048, R.drawable.img, R.drawable.stopwatch, R.drawable.graph01_circle};

    Class[] transitionList = new Class[]{ResultTable_Activity.class, StatisticsTable_Activity.class,
            TimeIntervalsTable_Activity.class, UtilizationTable_Activity.class, PieChartSample_Activity.class};

    public ModeSelectRecycleViewAdapter(Resources androidResource, Context activityContext) {
        this.androidResource = androidResource;
        modeTitles = androidResource.getStringArray(R.array.modeSelect_cardViewTitle);
        modeExplanation = androidResource.getStringArray(R.array.modeSelect_cardViewExplanation);
        this.activityContext = activityContext;
    }

    @NonNull
    @Override
    public ModeSelectCardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewmode_cardview, parent, false);
        return new ModeSelectCardViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ModeSelectCardViewHolder holder, int position) {
        holder.modeTitle.setText(modeTitles[position]);
        holder.modeExplanation.setText(modeExplanation[position]);
        holder.setButtonImage(imageResourceIds[position]);
        holder.modeSelectButton.setOnClickListener(view -> {
            activityContext.startActivity(new Intent(activityContext, transitionList[position]));
        });
        holder.itemView.setOnClickListener(view -> {
            activityContext.startActivity(new Intent(activityContext, transitionList[position]));
        });
    }

    @Override
    public int getItemCount() {
        return modeTitles.length;
    }

    public class ModeSelectCardViewHolder extends RecyclerView.ViewHolder {
        TextView modeTitle;
        TextView modeExplanation;
        ImageButton modeSelectButton;

        View itemView;

        private void setButtonImage(int resourceId) {
            modeSelectButton.setImageResource(resourceId);
        }

        public ModeSelectCardViewHolder(@NonNull View itemView) {
            super(itemView);
            this.itemView = itemView;
            modeTitle = itemView.findViewById(R.id.textView_ModeSelectCardTitle);
            modeExplanation = itemView.findViewById(R.id.textView_ModeSelectCardExplanation);
            modeSelectButton = itemView.findViewById(R.id.imageButton_ModeSelectCardView);
        }
    }

}