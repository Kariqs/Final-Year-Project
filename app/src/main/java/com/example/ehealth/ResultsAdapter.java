package com.example.ehealth;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class ResultsAdapter extends RecyclerView.Adapter<ResultsViewHolder> {
    private Context context;
    private List<Upload> uploadList;

    public ResultsAdapter(Context context, List<Upload> uploadList) {
        this.context = context;
        this.uploadList = uploadList;
    }

    @NonNull
    @Override
    public ResultsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.results_item,parent,false);
        return new ResultsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ResultsViewHolder holder, int position) {
     Upload upload = uploadList.get(position);
     holder.Description.setText(upload.getDescription());
        Picasso.get()
                .load(upload.getImageUrl())
                .fit()
                .centerCrop()
                .into(holder.Image);
    }

    @Override
    public int getItemCount() {
        return uploadList.size();
    }
}
