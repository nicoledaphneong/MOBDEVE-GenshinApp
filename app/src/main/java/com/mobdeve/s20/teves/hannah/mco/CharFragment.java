package com.mobdeve.s20.teves.hannah.mco;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class CharFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        RecyclerView recyclerView;
        CharAdapter adapter;

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_char, container, false);

        // Initialize RecyclerView
        recyclerView = view.findViewById(R.id.charRecyclerView);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 2);
        // Set LayoutManager
        recyclerView.setLayoutManager(gridLayoutManager);

        // Set Adapter with the data
        List<CharData> characterDataList = CharData.getCharacterData();
        adapter = new CharAdapter(characterDataList, false);
        recyclerView.setAdapter(adapter);

        return view;
    }
}
