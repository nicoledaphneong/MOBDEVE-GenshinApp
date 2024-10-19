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

public class IndivCharFragment extends Fragment {

    private TextView nameHolder;
    private TextView ascensionMaterialHolder;
    private TextView talentMaterialHolder;
    private TextView bestArtifactsHolder;
    private TextView bestWeaponsHolder;
    private TextView skillPriorityHolder;
    private ImageView imgHolder;
    private Button btnClose;

    // Arrow ImageViews
    private ImageView ascensionArrow;
    private ImageView talentArrow;
    private ImageView bestArtifactsArrow;
    private ImageView bestWeaponsArrow;
    private ImageView skillPriorityArrow;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_char_indiv, container, false);

        // Initialize TextViews
        nameHolder = view.findViewById(R.id.charName);
        imgHolder = view.findViewById(R.id.charImg);
        ascensionMaterialHolder = view.findViewById(R.id.ascensionMaterialHolder);
        talentMaterialHolder = view.findViewById(R.id.talentMaterialHolder);
        bestArtifactsHolder = view.findViewById(R.id.bestArtifactsHolder);
        bestWeaponsHolder = view.findViewById(R.id.bestWeaponsHolder);
        skillPriorityHolder = view.findViewById(R.id.skillPriorityHolder);
        btnClose = view.findViewById(R.id.btn_close);

        // Initialize section title arrows
        ascensionArrow = view.findViewById(R.id.ascensionArrow);
        talentArrow = view.findViewById(R.id.talentArrow);
        bestArtifactsArrow = view.findViewById(R.id.bestArtifactsArrow);
        bestWeaponsArrow = view.findViewById(R.id.bestWeaponsArrow);
        skillPriorityArrow = view.findViewById(R.id.skillPriorityArrow);

        // Set OnClickListener for collapsible sections
        setUpCollapsibleSections();

        // Get character name from arguments
        Bundle args = getArguments();
        if (args != null) {
            String charName = args.getString("CHAR_NAME");
            // Retrieve the character data based on the name
            CharData charData = getCharacterDataByName(charName);
            displayCharacterData(charData);
        }

        // Set OnClickListener for the button
        btnClose.setOnClickListener(v -> {
            // Create an instance of CharFragment
            CharFragment charFragment = new CharFragment();
            // Replace the current fragment with CharFragment
            requireActivity().getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container, charFragment) // Replace with CharFragment
                    .commit(); // Commit the transaction
        });

        return view;
    }

    private void setUpCollapsibleSections() {
        // Set click listeners for each title to toggle visibility of the respective content and arrow icons
        ascensionArrow.setOnClickListener(v -> {
            toggleVisibility(ascensionMaterialHolder, ascensionArrow);
        });

        talentArrow.setOnClickListener(v -> {
            toggleVisibility(talentMaterialHolder, talentArrow);
        });

        bestArtifactsArrow.setOnClickListener(v -> {
            toggleVisibility(bestArtifactsHolder, bestArtifactsArrow);
        });

        bestWeaponsArrow.setOnClickListener(v -> {
            toggleVisibility(bestWeaponsHolder, bestWeaponsArrow);
        });

        skillPriorityArrow.setOnClickListener(v -> {
            toggleVisibility(skillPriorityHolder, skillPriorityArrow);
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

    private CharData getCharacterDataByName(String charName) {
        // This method should retrieve the character data based on the character name
        List<CharData> allCharacterData = CharData.getCharacterData();
        for (CharData data : allCharacterData) {
            if (data.name.equals(charName)) {
                return data;
            }
        }
        return null;
    }

    private void displayCharacterData(CharData charData) {
        if (charData != null) {
            nameHolder.setText(charData.name);
            imgHolder.setImageResource(charData.getCharImg());
            ascensionMaterialHolder.setText(charData.getAscensionMaterials());
            talentMaterialHolder.setText(charData.getTalentMaterials());

            // Display best artifact sets
            StringBuilder artifactsBuilder = new StringBuilder();
            for (Object artifact : charData.bestArtifactSets) {
                artifactsBuilder.append(artifact.toString()).append("\n");
            }
            bestArtifactsHolder.setText(artifactsBuilder.toString());

            // Display best weapons
            StringBuilder weaponsBuilder = new StringBuilder();
            for (Object weapon : charData.bestWeapons) {
                weaponsBuilder.append(weapon.toString()).append("\n");
            }
            bestWeaponsHolder.setText(weaponsBuilder.toString());

            // Display skill priority
            StringBuilder skillPrioBuilder = new StringBuilder();
            for (Object skill : charData.skillPrio) {
                skillPrioBuilder.append(skill.toString()).append("\n");
            }
            skillPriorityHolder.setText(skillPrioBuilder.toString());
        }
    }
}
