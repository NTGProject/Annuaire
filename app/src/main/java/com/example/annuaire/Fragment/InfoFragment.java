package com.example.annuaire.Fragment;

import android.graphics.text.LineBreaker;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.annuaire.R;

public class InfoFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_info, container, false);

        TextView desc = view.findViewById(R.id.desc);

        desc.setJustificationMode(LineBreaker.JUSTIFICATION_MODE_INTER_WORD);

        return view;
    }
}