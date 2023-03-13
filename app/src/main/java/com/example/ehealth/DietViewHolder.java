package com.example.ehealth;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class DietViewHolder extends RecyclerView.ViewHolder {
    TextView Name,Description;
    public DietViewHolder(@NonNull View itemView) {
        super(itemView);
        Name = itemView.findViewById(R.id.nutrient_name);
        Description = itemView.findViewById(R.id.nutrient_description);
    }
}
