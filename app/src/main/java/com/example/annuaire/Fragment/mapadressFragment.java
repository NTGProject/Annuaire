package com.example.annuaire.Fragment;

import static com.example.annuaire.model.general.getEntreprise;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.annuaire.R;
import com.example.annuaire.controller.GeneralMethode;
import com.example.annuaire.model.Personnel;
import com.example.annuaire.model.entreprise;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.function.Function;


public class mapadressFragment extends Fragment {

    ArrayList<entreprise> entreprise;
    ArrayList<Personnel> personel;
    FloatingActionButton FABCall;
    FloatingActionButton FABMail;
    FloatingActionButton FABweb;
    FloatingActionButton FABmap;
    @SuppressLint("SuspiciousIndentation")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_mapadress, container, false);

        entreprise = GeneralMethode.selectFromTable(entreprise.class, "entreprise",
                Arrays.asList("id", "nom","email","telephone","adresse","website","id_directeur","description")," nom='"+getEntreprise()+"'");



        TextView enomEntreprise=view.findViewById(R.id.textView10);
        enomEntreprise.setText("Entreprise "+ entreprise.get(0).getNom());

        TextView phone=view.findViewById(R.id.textView12);
        phone.setText(entreprise.get(0).getTelephone());
        FABCall = view.findViewById(R.id.FABCall);
        GeneralMethode.ActionCall(FABCall, view, entreprise,null, new Function<entreprise, String>() {
            @Override
            public String apply(entreprise entr) {
                return entreprise.get(0).getTelephone();
            }
        });


        TextView mail=view.findViewById(R.id.mail);
        mail.setText(entreprise.get(0).getEmail());
        FABMail = view.findViewById(R.id.FABmail);
        GeneralMethode.mailingAction(FABMail, view, entreprise, null, new Function<entreprise, String>() {
            @Override
            public String apply(entreprise entr) {
                return entreprise.get(0).getEmail();
            }
        });



        TextView website=view.findViewById(R.id.web);
        website.setText(entreprise.get(0).getWebsite());
        FABweb= view.findViewById(R.id.FABweb);
        FABweb.setOnClickListener(views -> {
            if(!website.getText().toString().equals("")) {
                Uri uri = Uri.parse("http://"+website.getText().toString());
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });





        TextView adresse=view.findViewById(R.id.map);
        adresse.setText(entreprise.get(0).getAdresse());
        FABmap = view.findViewById(R.id.FABmap);
        FABmap.setOnClickListener(views -> {
            if(!website.getText().toString().equals(""))
                openMapInBrowser(adresse.getText().toString());
          });



        TextView director=view.findViewById(R.id.director);
        Integer id_director=entreprise.get(0).getIdDirecteur();
        personel = GeneralMethode.selectFromTable(Personnel.class, "personnel",
                Arrays.asList("nom", "prenom"),"id_person="+id_director);
        if(personel.size()!=0)
        director.setText(personel.get(0).getNom()+" "+personel.get(0).getPrenom());


        return view;

    }


    private void openMapInBrowser(String address) {
        String mapUrl = "https://www.google.com/maps/search/?api=1&query=" + Uri.encode(address);
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(mapUrl));
        intent.setPackage("com.android.chrome"); // Optional: Specify a specific browser package
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        try {
            startActivity(intent);
        } catch (Exception e) {
            Toast.makeText(getContext(), "Failed to open Google Maps", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
    }
}