package com.mobdeve.s20.teves.hannah.mco;

import androidx.recyclerview.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class CharViewHolder extends RecyclerView.ViewHolder {
    // TextView for character name
    TextView nameHolder;

    ImageView charImgView;

    View view;

    // Constructor
    public CharViewHolder(View itemView) {
        super(itemView);
        this.nameHolder = itemView.findViewById(R.id.charName);
        this.charImgView = itemView.findViewById(R.id.charImg);
        this.view = itemView;
    }

    // Method to bind character list data
    public void bindCharacter(CharData charData) {
        nameHolder.setText(charData.name);
        charImgView.setImageResource(charData.getCharImg());
    }

    // Method to bind individual character data
    public void bindIndividualCharacter(CharData charData) {
        nameHolder.setText(charData.name);
        charImgView.setImageResource(charData.getCharImg());
    }
}
