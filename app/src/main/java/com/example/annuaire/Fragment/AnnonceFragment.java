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
import com.example.annuaire.controller.annonceAdapter;
import com.example.annuaire.model.annonce;

import java.util.ArrayList;
import java.util.Arrays;

public class AnnonceFragment extends Fragment {



    RecyclerView AnnonceView;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_annonce, container, false);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(view.getContext(), LinearLayoutManager.VERTICAL, false);

        AnnonceView = view.findViewById(R.id.Annonce);
        AnnonceView.setLayoutManager(linearLayoutManager);

        ArrayList<annonce> annonce =  GeneralMethode.selectFromTable(annonce.class, "annonce",
                Arrays.asList("titre","description","image")," id_societe="+getIdEntreprise()+"");


        //////le champ de recherche
        EditText search = view.findViewById(R.id.search);
        GeneralMethode.viderRecheche(search);

        GeneralMethode.LoadAdapterContent(search,annonce, AnnonceView, list ->{
            return new annonceAdapter(list);});

        return view;
    }



}
