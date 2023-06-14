package com.example.annuaire;

import static com.example.annuaire.model.general.getIdEntreprise;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.res.ResourcesCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.example.annuaire.Fragment.ActualiteFragment;
import com.example.annuaire.Fragment.AdvancedFiltreFragment;
import com.example.annuaire.Fragment.AnnonceFragment;
import com.example.annuaire.Fragment.HomeFragment;
import com.example.annuaire.Fragment.InfoFragment;
import com.example.annuaire.Fragment.aboutusFragment;
import com.example.annuaire.Fragment.mapadressFragment;
import com.example.annuaire.controller.GeneralMethode;
import com.example.annuaire.model.general;
import com.example.annuaire.model.societe;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.Arrays;

public class menu_app extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_app);




        Toolbar toolbar = findViewById(R.id.toolbar); //Ignore red line errors
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        TextView mTitle = toolbar.findViewById(R.id.toolbar_title);
        mTitle.setText("");
        drawerLayout = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open_nav,
                R.string.close_nav);
        toggle.setDrawerIndicatorEnabled(false);
        Drawable drawable = ResourcesCompat.getDrawable(getResources(), R.drawable.menu,null);
        toggle.setHomeAsUpIndicator(drawable);

        toggle.setToolbarNavigationClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (drawerLayout.isDrawerVisible(GravityCompat.START)) {
                    drawerLayout.closeDrawer(GravityCompat.START);
                } else {
                    drawerLayout.openDrawer(GravityCompat.START);
                }
            }
        });

        toggle.syncState();

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new HomeFragment()).commit();
            navigationView.setCheckedItem(R.id.annuaire);

            View headerView = navigationView.getHeaderView(0);
            TextView headerTitle = headerView.findViewById(R.id.headermyannuaire);
            headerTitle.setText("L'annuire de "+general.getSociete());
        }


    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {



        switch (item.getItemId()) {

            case R.id.annuaire:
                 getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new HomeFragment()).commit();
                break;


            case R.id.actualite:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new ActualiteFragment()).commit();
                break;


            case R.id.annonce:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new AnnonceFragment()).commit();
                break;

            case R.id.info:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new InfoFragment()).commit();
                break;


            case R.id.aboutus:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new aboutusFragment()).commit();
                break;

            case R.id.map:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new mapadressFragment()).commit();
                break;


            case R.id.recherche:
                 getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new AdvancedFiltreFragment()).commit();
                break;


            case R.id.linkedIn:
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://"+ general.getLinked_in()));
                startActivity(intent);

                break;

            case R.id.facebook:
                 System.out.println("https://"+ general.getFacebook());
                Intent intent1 = new Intent(Intent.ACTION_VIEW, Uri.parse("https://"+ general.getFacebook()));
                startActivity(intent1);
                break;


            case R.id.instagram:
                Intent intent2 = new Intent(Intent.ACTION_VIEW, Uri.parse("https://"+ general.getInstagram()));
                startActivity(intent2);

                break;




        }

        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

}