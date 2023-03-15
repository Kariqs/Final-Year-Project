package com.example.ehealth;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class WorkoutAdapter extends RecyclerView.Adapter<WorkoutViewHolder> {

    Context context;
    List<WorkoutModel> workOutList;

    public WorkoutAdapter(Context context, List<WorkoutModel> workOutList) {
        this.context = context;
        this.workOutList = workOutList;
    }

    @NonNull
    @Override
    public WorkoutViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new WorkoutViewHolder(LayoutInflater.from(context).inflate(R.layout.workout_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull WorkoutViewHolder holder, int position) {
        holder.name.setText(workOutList.get(position).getName());
        holder.description.setText(workOutList.get(position).getDescription());
        holder.image.setImageResource(workOutList.get(position).getImage());
    }

    @Override
    public int getItemCount() {
        return workOutList.size();
    }
}
