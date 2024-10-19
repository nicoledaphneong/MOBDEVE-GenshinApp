package com.mobdeve.s20.teves.hannah.mco;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.List;

public class IndivWeaponFragment extends Fragment {

    private TextView nameHolder;
    private TextView refineMaterialHolder;
    private ImageView imgHolder;
    private Button btnClose;

    private ImageView refineArrow;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_weapon_indiv, container, false);

        // Initialize TextViews
        nameHolder = view.findViewById(R.id.weaponName);
        refineMaterialHolder = view.findViewById(R.id.refineMaterialHolder);
        imgHolder = view.findViewById(R.id.weaponImg);
        btnClose = view.findViewById(R.id.btn_close);

        // Initialize section title arrows
        refineArrow = view.findViewById(R.id.refineArrow);

        // Set OnClickListener for collapsible sections
        setUpCollapsibleSections();

        // Get character name from arguments
        Bundle args = getArguments();
        if (args != null) {
            String weaponName = args.getString("WEAPON_NAME");
            // Retrieve the character data based on the name
            WeaponData weaponData = getWeaponDataByName(weaponName);
            displayWeaponData(weaponData);
        }

        // Set OnClickListener for the button
        btnClose.setOnClickListener(v -> {
            WeaponFragment weaponFragment = new WeaponFragment();
            requireActivity().getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container, weaponFragment) // Replace with CharFragment
                    .commit(); // Commit the transaction
        });

        return view;
    }

    private void setUpCollapsibleSections() {
        // Set click listeners for each title to toggle visibility of the respective content and arrow icons
        refineArrow.setOnClickListener(v -> {
            toggleVisibility(refineMaterialHolder, refineArrow);
        });
    }

    private void toggleVisibility(TextView textView, ImageView arrow) {
        if (textView.getVisibility() == View.VISIBLE) {
            textView.setVisibility(View.GONE);
            arrow.setImageResource(R.drawable.ic_arrow_up); // Change to collapsed icon
        } else {
            textView.setVisibility(View.VISIBLE);
            arrow.setImageResource(R.drawable.ic_arrow_down); // Change to expanded icon
        }
    }

    private WeaponData getWeaponDataByName(String weaponName) {
        // This method should retrieve the weapon data based on the weapon name
        List<WeaponData> allWeaponData = WeaponData.getWeaponData();
        for (WeaponData data : allWeaponData) {
            if (data.name.equals(weaponName)) {
                return data;
            }
        }
        return null; // Return null if no character found
    }

    private void displayWeaponData(WeaponData weaponData) {
        if (weaponData != null) {
            nameHolder.setText(weaponData.name);
            imgHolder.setImageResource(weaponData.getWeaponImg());
            refineMaterialHolder.setText(weaponData.getRefineMaterials());
        }
    }
}
