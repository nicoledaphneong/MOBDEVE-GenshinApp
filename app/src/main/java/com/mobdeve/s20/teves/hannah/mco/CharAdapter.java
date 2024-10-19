package com.mobdeve.s20.teves.hannah.mco;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class CharAdapter extends RecyclerView.Adapter<CharViewHolder> {
    private List<CharData> characterDataList;
    private boolean isDetailView; // Flag to check if in detail view

    public CharAdapter(List<CharData> characterDataList, boolean isDetailView) {
        this.characterDataList = characterDataList;
        this.isDetailView = isDetailView; // Set if in detail view or not
    }

    @NonNull
    @Override
    public CharViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_char, parent, false);
        return new CharViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CharViewHolder holder, int position) {
        CharData charData = characterDataList.get(position);

        if (isDetailView) {
            holder.bindIndividualCharacter(charData);
        } else {
            holder.bindCharacter(charData);
            // Handle click event to navigate to IndivCharFragment
            holder.view.setOnClickListener(v -> {
                Fragment indivCharFragment = new IndivCharFragment();
                Bundle args = new Bundle();
                args.putString("CHAR_NAME", charData.name);
                indivCharFragment.setArguments(args);
                // Replace the fragment in the container
                ((AppCompatActivity) v.getContext()).getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container, indivCharFragment)
                        .addToBackStack(null)
                        .commit();
            });
        }
    }

    @Override
    public int getItemCount() {
        return characterDataList.size();
    }
}



