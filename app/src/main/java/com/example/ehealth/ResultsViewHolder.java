package com.example.ehealth;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ResultsViewHolder extends RecyclerView.ViewHolder {

    public TextView Description;
    public ImageView Image;

    public ResultsViewHolder(@NonNull View itemView) {
        super(itemView);
        Description = itemView.findViewById(R.id.view_description);
        Image = itemView.findViewById(R.id.view_image);
    }
}
