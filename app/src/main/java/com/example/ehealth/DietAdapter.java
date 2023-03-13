package com.example.ehealth;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class DietAdapter extends RecyclerView.Adapter<DietViewHolder> {

    Context context;
    List<DietModel> nutrientList;

    public DietAdapter(Context context, List<DietModel> nutrientList) {
        this.context = context;
        this.nutrientList = nutrientList;
    }

    @NonNull
    @Override
    public DietViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new DietViewHolder(LayoutInflater.from(context).inflate(R.layout.diet_item,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull DietViewHolder holder, int position) {
        holder.Name.setText(nutrientList.get(position).getDietName());
        holder.Description.setText(nutrientList.get(position).getDietDescription());
    }

    @Override
    public int getItemCount() {
        return nutrientList.size();
    }
}
