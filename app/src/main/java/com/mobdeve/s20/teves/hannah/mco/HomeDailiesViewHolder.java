package com.mobdeve.s20.teves.hannah.mco;

import androidx.recyclerview.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

public class HomeDailiesViewHolder
    extends RecyclerView.ViewHolder {
        TextView timeHolder;
        TextView taskHolder;
        View view;

        HomeDailiesViewHolder(View itemView) {
            super(itemView);
            this.timeHolder = itemView.findViewById(R.id.timeHolder);
            this.taskHolder = itemView.findViewById(R.id.taskHolder);
            this.view = itemView;
        }

}