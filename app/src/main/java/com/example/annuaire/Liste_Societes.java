package com.example.annuaire;

import static com.example.annuaire.model.general.getIdEntreprise;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;

import com.example.annuaire.controller.GeneralMethode;
import com.example.annuaire.controller.SocieteAdapter;
import com.example.annuaire.model.societe;

import java.util.ArrayList;
import java.util.Arrays;

public class Liste_Societes  extends AppCompatActivity {


    RecyclerView SocietesView;



    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.liste_societes);
        SocietesView = findViewById(R.id.Societes);
        EditText search = findViewById(R.id.search);
        GeneralMethode.viderRecheche(search);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);


        SocietesView.setLayoutManager(linearLayoutManager);
        ArrayList<societe> societe =  GeneralMethode.selectFromTable(societe.class, "societe",
                Arrays.asList("id","label","image_soc", "facebook","instagram","linked_In")," id_entreprise="+getIdEntreprise()+"");





        GeneralMethode.LoadAdapterContent(search,societe, SocietesView, list ->{

            return new SocieteAdapter(list);


        });
    }




}
