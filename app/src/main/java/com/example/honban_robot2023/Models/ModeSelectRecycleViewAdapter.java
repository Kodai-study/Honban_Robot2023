package com.example.honban_robot2023.Models;

import android.content.Context;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.honban_robot2023.R;

public class ModeSelectRecycleViewAdapter extends RecyclerView.Adapter<ModeSelectRecycleViewAdapter.ModeSelectCardViewHolder> {

    private Resources androidResource;

    private String[] modeTitles;
    private String[] modeExplanation;
    int imageResourceIds[] = new int[]{R.drawable.kekka, R.drawable.symbol048, R.drawable.img, R.drawable.stopwatch,R.drawable.graph01_circle};

    public ModeSelectRecycleViewAdapter(Resources androidResource) {
        this.androidResource = androidResource;
        modeTitles = androidResource.getStringArray(R.array.modeSelect_cardViewTitle);
        modeExplanation = androidResource.getStringArray(R.array.modeSelect_cardViewExplanation);
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
    }

    @Override
    public int getItemCount() {
        return modeTitles.length;
    }

    public class ModeSelectCardViewHolder extends RecyclerView.ViewHolder {
        TextView modeTitle;
        TextView modeExplanation;
        ImageButton modeSelectButton;

        protected void setButtonImage(int resourceId) {
            modeSelectButton.setImageResource(resourceId);
        }

        public ModeSelectCardViewHolder(@NonNull View itemView) {
            super(itemView);
            modeTitle = itemView.findViewById(R.id.textView_ModeSelectCardTitle);
            modeExplanation = itemView.findViewById(R.id.textView_ModeSelectCardExplanation);
            modeSelectButton = itemView.findViewById(R.id.imageButton_ModeSelectCardView);
        }
    }

}