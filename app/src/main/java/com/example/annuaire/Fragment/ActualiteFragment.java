package com.example.annuaire.Fragment;

import static com.example.annuaire.model.general.getIdEntreprise;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.annuaire.R;
import com.example.annuaire.controller.GeneralMethode;
import com.example.annuaire.controller.actualiteAdapter;
import com.example.annuaire.model.actualite;

import java.util.ArrayList;
import java.util.Arrays;

public class ActualiteFragment extends Fragment {


    RecyclerView.Adapter adapter;

    RecyclerView ActualityView;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_actualite, container, false);
        ActualityView = view.findViewById(R.id.Actuality);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(view.getContext(), LinearLayoutManager.VERTICAL, false);


        ActualityView.setLayoutManager(linearLayoutManager);
        ArrayList<actualite> actualites =  GeneralMethode.selectFromTable(actualite.class, "actualite",
                Arrays.asList("titre","description","image")," id_societe="+getIdEntreprise()+"");



        EditText search = view.findViewById(R.id.search);
        GeneralMethode.viderRecheche(search);


        GeneralMethode.LoadAdapterContent(search,actualites, ActualityView, list ->{
            return new actualiteAdapter(list);});



        return view;
    }




}
