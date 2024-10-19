package com.mobdeve.s20.teves.hannah.mco;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class HomePageAdapter extends RecyclerView.Adapter<HomeDailiesViewHolder> {
    List<HomeDailiesData> list;

    public HomePageAdapter(List<HomeDailiesData> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public HomeDailiesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_daily_task, parent, false);
        return new HomeDailiesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final HomeDailiesViewHolder holder, final int position) {
        holder.timeHolder.setText(list.get(position).time);
        holder.taskHolder.setText(list.get(position).task);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}