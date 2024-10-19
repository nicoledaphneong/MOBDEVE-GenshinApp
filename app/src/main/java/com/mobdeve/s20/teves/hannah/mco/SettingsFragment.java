// SettingsFragment.java
package com.mobdeve.s20.teves.hannah.mco;

import android.app.AlertDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class SettingsFragment extends Fragment {

    private TextView weeklyReminderTime;
    private TextView serverRegion;
    private SharedPreferences sharedPreferences;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_settings, container, false);

        sharedPreferences = getContext().getSharedPreferences("SettingsPrefs", Context.MODE_PRIVATE);

        // Weekly Reminder Time
        View weeklyReminderTimeBlock = view.findViewById(R.id.weeklyReminderTimeBlock);
        TextView weeklyReminderTitle = weeklyReminderTimeBlock.findViewById(R.id.settingTitle);
        weeklyReminderTime = weeklyReminderTimeBlock.findViewById(R.id.settingParameter);

        weeklyReminderTitle.setText("Weekly Reminder");
        weeklyReminderTimeBlock.setOnClickListener(v -> showEditReminderDialog());

        // Server Region
        View serverRegionBlock = view.findViewById(R.id.serverRegionBlock);
        TextView serverRegionTitle = serverRegionBlock.findViewById(R.id.settingTitle);
        serverRegion = serverRegionBlock.findViewById(R.id.settingParameter);

        serverRegionTitle.setText("Server Region");
        serverRegionBlock.setOnClickListener(v -> showEditServerDialog());

        // Load saved settings
        loadSettings();

        return view;
    }

    private void showEditReminderDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("Edit Weekly Reminder");

        View dialogView = LayoutInflater.from(getContext()).inflate(R.layout.dialog_edit_reminder, null);
        builder.setView(dialogView);

        TimePicker timePicker = dialogView.findViewById(R.id.timePicker);
        Spinner daySpinner = dialogView.findViewById(R.id.daySpinner);

        builder.setPositiveButton("Save", (dialog, which) -> {
            int hour = timePicker.getHour();
            int minute = timePicker.getMinute();
            String day = daySpinner.getSelectedItem().toString();
            String time = String.format("%02d:%02d %s", (hour % 12 == 0) ? 12 : hour % 12, minute, (hour >= 12) ? "PM" : "AM");

            // Save the reminder time in SharedPreferences
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("reminderDay", day);
            editor.putString("reminderTime", time);
            editor.apply();

            weeklyReminderTime.setText(day + " - " + time);
        });

        builder.setNegativeButton("Cancel", (dialog, which) -> dialog.dismiss());

        builder.create().show();
    }

    private void showEditServerDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("Select Server Region");

        View dialogView = LayoutInflater.from(getContext()).inflate(R.layout.dialog_select_server, null);
        builder.setView(dialogView);

        Spinner serverSpinner = dialogView.findViewById(R.id.serverSpinner);

        builder.setPositiveButton("Save", (dialog, which) -> {
            String server = serverSpinner.getSelectedItem().toString();

            // Save the server region in SharedPreferences
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("serverRegion", server);
            editor.apply();

            serverRegion.setText(server);
        });

        builder.setNegativeButton("Cancel", (dialog, which) -> dialog.dismiss());

        builder.create().show();
    }

    private void loadSettings() {
        String day = sharedPreferences.getString("reminderDay", "Sunday");
        String time = sharedPreferences.getString("reminderTime", "12:00 PM");
        weeklyReminderTime.setText(day + " - " + time);

        String server = sharedPreferences.getString("serverRegion", "America");
        serverRegion.setText(server);
    }
}