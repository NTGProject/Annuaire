package com.example.annuaire;

import static com.example.annuaire.model.general.getEntreprise;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.annuaire.controller.DataBaseConnection;
import com.example.annuaire.controller.GeneralMethode;
import com.example.annuaire.model.entreprise;
import com.example.annuaire.model.general;
import com.example.annuaire.model.societe;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {
    Connection connection = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        setContentView(R.layout.acceuil);





        TextView con = findViewById(R.id.begin);
        con.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DataBaseConnection db = new DataBaseConnection();
                connection = db.connect();
                general.setEntreprise("enie");

                if(connection==null){
                    Toast.makeText(getApplicationContext(), "Failed to connect to the database", Toast.LENGTH_SHORT).show();

                }else {

                    ArrayList<entreprise> id_enie=GeneralMethode.selectFromTable(entreprise.class, "entreprise", Arrays.asList("id"),
                            " nom='" + getEntreprise() + "'");

                    if(id_enie.size()!=0) {
                        general.setIdEntreprise(id_enie.get(0).getId());
                        int count_societe=GeneralMethode.selectFromTable(societe.class, "societe", Arrays.asList("id"),
                                " id_entreprise='" + general.getIdEntreprise() + "'").size();

                        Intent intent;
                        System.out.println(count_societe);
                        if(count_societe==1) {
                            intent = new Intent(MainActivity.this, menu_app.class);
                        }
                        else{
                            intent = new Intent(MainActivity.this, Liste_Societes.class);

                        }
                        startActivity(intent);

                    }
                    else{
                        Toast.makeText(getApplicationContext(), "Your entreprise doesn't exist", Toast.LENGTH_SHORT).show();

                    }
                }

            }
        });

    }
}