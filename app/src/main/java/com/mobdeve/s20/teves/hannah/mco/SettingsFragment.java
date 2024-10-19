package com.mobdeve.s20.teves.hannah.mco;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TimePicker;

import androidx.fragment.app.Fragment;

public class SettingsFragment extends Fragment {
    private Spinner spinnerDayOfWeek;
    private TimePicker timePicker;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_settings, container, false);

        spinnerDayOfWeek = view.findViewById(R.id.spinner_day_of_week);
        timePicker = view.findViewById(R.id.time_picker);

        // Set up the spinner with days of the week
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getContext(),
                R.array.days_of_week, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerDayOfWeek.setAdapter(adapter);

        // Load saved preferences
        SharedPreferences preferences = getActivity().getPreferences(getContext().MODE_PRIVATE);
        int savedDay = preferences.getInt("day_of_week", 0);
        int savedHour = preferences.getInt("reminder_hour", 0);
        int savedMinute = preferences.getInt("reminder_minute", 0);

        spinnerDayOfWeek.setSelection(savedDay);
        timePicker.setHour(savedHour);
        timePicker.setMinute(savedMinute);

        // Save preferences when the user changes the settings
        view.findViewById(R.id.save_button).setOnClickListener(v -> {
            SharedPreferences.Editor editor = preferences.edit();
            editor.putInt("day_of_week", spinnerDayOfWeek.getSelectedItemPosition());
            editor.putInt("reminder_hour", timePicker.getHour());
            editor.putInt("reminder_minute", timePicker.getMinute());
            editor.apply();
        });

        return view;
    }
}