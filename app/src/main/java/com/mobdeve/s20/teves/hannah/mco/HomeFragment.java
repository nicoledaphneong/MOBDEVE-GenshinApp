package com.mobdeve.s20.teves.hannah.mco;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.TimePicker;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class HomeFragment extends Fragment {
    private HomePageAdapter adapter;
    private List<HomeDailiesData> dailyTasks;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        RecyclerView recyclerView;
        // HomePageAdapter adapter;

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        // Initialize RecyclerView
        recyclerView = view.findViewById(R.id.dailyTaskRecyclerView);

        // Set LayoutManager
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        // Initialize the data list
        dailyTasks = HomeDailiesData.getData();

        // Set Adapter with the data
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

        // Get references to the input fields
        EditText taskNameInput = dialogView.findViewById(R.id.taskNameInput);
        TimePicker timePicker = dialogView.findViewById(R.id.timePicker);

        // Set up dialog buttons
        builder.setPositiveButton("Add", null);

        builder.setNegativeButton("Cancel", (dialog, which) -> {
            dialog.dismiss();
        });

        // Create and show the dialog
        AlertDialog dialog = builder.create();
        dialog.show();

        // Override the positive button click to add validation
        dialog.getButton(AlertDialog.BUTTON_POSITIVE).setOnClickListener(v -> {
            String taskName = taskNameInput.getText().toString().trim();
            if (taskName.isEmpty()) {
                Toast.makeText(getContext(), "Task name cannot be empty!", Toast.LENGTH_SHORT).show();
            } else {
                int hour = timePicker.getHour();
                int minute = timePicker.getMinute();
                String time = String.format("%02d:%02d %s", (hour % 12 == 0) ? 12 : hour % 12, minute, (hour >= 12) ? "PM" : "AM");

                // Add the new task to the list
                HomeDailiesData.addTask(dailyTasks, time, taskName);

                // Notify the adapter of the data change
                adapter.notifyDataSetChanged();

                Toast.makeText(getContext(), "Task added!", Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            }
        });
    }
}