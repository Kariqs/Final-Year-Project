package com.example.ehealth;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class WorkoutViewHolder extends RecyclerView.ViewHolder {

    TextView name, description;
    ImageView image;

    public WorkoutViewHolder(@NonNull View itemView) {
        super(itemView);
        name = itemView.findViewById(R.id.name);
        description = itemView.findViewById(R.id.description);
        image = itemView.findViewById(R.id.image);
    }
}
