package com.mobdeve.s20.teves.hannah.mco;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private FloatingActionButton fab;  // Declare FAB globally
    private List<HomeDailiesData> dailyTasks;  // Assuming you have this data structure
    private HomePageAdapter adapter;  // Use your actual adapter class

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize the dailyTasks list with sample data
        dailyTasks = HomeDailiesData.getData();

        // Initialize the adapter with the dailyTasks list
        adapter = new HomePageAdapter(dailyTasks);

        fab = findViewById(R.id.addFab);  // Initialize FAB
        fab.setVisibility(View.GONE);  // Hide FAB by default

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment selectedFragment = null;

                int itemId = item.getItemId();

                if (itemId == R.id.nav_home) {
                    selectedFragment = new HomeFragment(dailyTasks, adapter);
                    fab.setVisibility(View.VISIBLE);  // Show FAB when on HomeFragment

                    fab.setOnClickListener(fabView -> {
                        showModalDialog();  // Show dialog when FAB is clicked
                    });
                } else {
                    fab.setVisibility(View.GONE);  // Hide FAB for other fragments

                    if (itemId == R.id.nav_char) {
                        selectedFragment = new CharFragment();
                    } else if (itemId == R.id.nav_weapon) {
                        selectedFragment = new WeaponFragment();
                    } else if (itemId == R.id.nav_settings) {
                        selectedFragment = new SettingsFragment();
                    }
                }

                // Replace the fragment in the container
                if (selectedFragment != null) {
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.fragment_container, selectedFragment)
                            .commit();
                }

                return true;
            }
        });

        // Set default fragment to HomeFragment and show the FAB
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new HomeFragment(dailyTasks, adapter)).commit();
        fab.setVisibility(View.VISIBLE);  // Show FAB for default HomeFragment
    }

    private void showModalDialog() {
        // Create an AlertDialog builder
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setTitle("Add Task");

        // Inflate a custom view for the dialog
        View dialogView = LayoutInflater.from(MainActivity.this).inflate(R.layout.dialog_add_task, null);
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
                Toast.makeText(MainActivity.this, "Task name cannot be empty!", Toast.LENGTH_SHORT).show();
            } else {
                int hour = timePicker.getHour();
                int minute = timePicker.getMinute();
                String time = String.format("%02d:%02d %s", (hour % 12 == 0) ? 12 : hour % 12, minute, (hour >= 12) ? "PM" : "AM");

                // Add the new task to the list
                HomeDailiesData.addTask(dailyTasks, time, taskName);

                // Notify the adapter of the data change
                adapter.notifyDataSetChanged();

                Toast.makeText(MainActivity.this, "Task added!", Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            }
        });
    }
}