package com.example.annuaire.controller;



import android.text.SpannableStringBuilder;

import com.example.annuaire.R;
import com.example.annuaire.model.Departement;
import com.example.annuaire.model.Direction;
import com.example.annuaire.model.Personnel;
import com.example.annuaire.model.Service;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PersonnelAdapter extends GeneralAdaptar<Personnel> {

    ArrayList<Personnel> personnels;
    private  RecyclerViewInterface recyclerViewInterface;

    public PersonnelAdapter(ArrayList<Personnel> personnel, RecyclerViewInterface recyclerViewInterface) {
        super(personnel, recyclerViewInterface);
        this.recyclerViewInterface = recyclerViewInterface;


    }


    @Override
    protected void bindData(GeneralAdaptar.ViewHolder holder, int position, Personnel item) {
        holder.titre.setText(String.valueOf(item.getNom()+" "+item.getPrenom()));

    }

    @Override
    public int getLayoutId() {
        return R.layout.viewpersonne;
    }

    @Override
    protected String getEmail(Personnel item) {
        return item.getEmail();
    }


    protected String getCall(Personnel item) {
        return item.getTelephone();
    }

    @Override
    protected void onClickTo(int p) {
        recyclerViewInterface.onListDesPersonnelsClick(p);
    }

    @Override
    protected String getMoreDetails(Personnel personnel) {


        Integer id_direction=personnel.getId_direction();
        Integer id_departement=personnel.getId_departement();
        Integer id_service=personnel.getId_service();


        List intitule=  Arrays.asList( "intitule");
        ArrayList<Direction>  directions = GeneralMethode.selectFromTable(Direction.class, "direction",
                intitule," id="+id_direction+"");

        ArrayList<Departement>  departements = GeneralMethode.selectFromTable(Departement.class, "departement",
                intitule," id="+id_departement+"");

        ArrayList<Service> services = GeneralMethode.selectFromTable(Service.class, "service",
                intitule," id="+id_service+"");

        SpannableStringBuilder item=GeneralMethode.showTableDataInDialog(Arrays.asList("nom","prenom","telephone","email","date_naissance","date_recrutement","grade",
                "diplome"), personnel,Personnel.class);
        if(directions.size()!=0) item.append("Direction : ").append(directions.get(0).getIntitule()).append("\n");
        if(departements.size()!=0) item.append("Département : ").append(departements.get(0).getIntitule()).append("\n");
        if(services.size()!=0) item.append("Service : ").append(services.get(0).getIntitule()).append("\n");

        return  item.toString();
    }


}
