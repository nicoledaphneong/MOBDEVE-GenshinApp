package com.mobdeve.s20.teves.hannah.mco;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class HomeFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        RecyclerView recyclerView;
        HomePageAdapter adapter;

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        // Initialize RecyclerView
        recyclerView = view.findViewById(R.id.dailyTaskRecyclerView);

        // Set LayoutManager
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        // Set Adapter with the data
        List<HomeDailiesData> dailyTasks = HomeDailiesData.getData();
        adapter = new HomePageAdapter(dailyTasks);
        recyclerView.setAdapter(adapter);

        // Initialize FloatingActionButton
        FloatingActionButton fab = view.findViewById(R.id.addFab);
        fab.setOnClickListener(fabView -> {
            showModalDialog();
        });

        return view;
    }

    private void showModalDialog() {
        // Create an AlertDialog builder
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("Add Task");

        // Inflate a custom view for the dialog
        View dialogView = LayoutInflater.from(getContext()).inflate(R.layout.dialog_add_task, null);
        builder.setView(dialogView);

        // Set up dialog buttons
        builder.setPositiveButton("Add", (dialog, which) -> {
            // Handle the positive button click (e.g., add a task)
            Toast.makeText(getContext(), "Task added!", Toast.LENGTH_SHORT).show();
        });

        builder.setNegativeButton("Cancel", (dialog, which) -> {
            dialog.dismiss();
        });

        // Show the dialog
        builder.create().show();
    }
}