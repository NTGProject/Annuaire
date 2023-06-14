package com.example.annuaire.controller;


import static com.example.annuaire.model.general.getIdEntreprise;

import android.text.SpannableStringBuilder;

import com.example.annuaire.R;
import com.example.annuaire.model.Departement;
import com.example.annuaire.model.Direction;
import com.example.annuaire.model.Personnel;
import com.example.annuaire.model.Service;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ServiceAdapter extends GeneralAdaptar<Service> {

    ArrayList<Service> services;
    private  RecyclerViewInterface recyclerViewInterface;

    public ServiceAdapter(ArrayList<Service> services, RecyclerViewInterface recyclerViewInterface) {
        super(services, recyclerViewInterface);
        this.recyclerViewInterface = recyclerViewInterface;


    }


    @Override
    protected void bindData(GeneralAdaptar.ViewHolder holder, int position, Service item) {
        holder.titre.setText(String.valueOf(item.getIntitule()+": "));

    }

    @Override
    public int getLayoutId() {
        return R.layout.viewservices;
    }

    @Override
    protected String getEmail(Service item) {
        return item.getEmail();
    }


    protected String getCall(Service item) {
        return item.getTelephone();
    }

    @Override
    protected void onClickTo(int p) {
        recyclerViewInterface.onListDesPersonnelsClick(p);
    }

    @Override
    public String getMoreDetails(Service services) {


        Integer id_departement=services.getId_departement();
        Integer id_chef=services.getChef_service();


        List intitule=  Arrays.asList( "intitule");
        List nom_preno=  Arrays.asList( "nom","prenom");

        ArrayList<Departement>  departements = GeneralMethode.selectFromTable(Departement.class, "departement",
                Arrays.asList( "intitule","id_direction")," id="+id_departement+"");

        ArrayList<Personnel>  personels = GeneralMethode.selectFromTable(Personnel.class, "personnel",
                nom_preno," id_person="+id_chef+"");


        SpannableStringBuilder item=GeneralMethode.showTableDataInDialog(Arrays.asList("intitule","email","telephone"), services, Service.class);


        if(personels.size()!=0) item.append("Chef Service : ").append(personels.get(0).getNom()+" "+personels.get(0).getPrenom()).append("\n");
        if(departements.size()!=0) {
            item.append("Département : ").append(departements.get(0).getIntitule()).append("\n");
            Integer id_direction=departements.get(0).getId_direction();
            ArrayList<Direction>  directions = GeneralMethode.selectFromTable(Direction.class, "direction",
                    intitule," id="+id_direction+"");
            if(directions.size()!=0) item.append("Direction : ").append(directions.get(0).getIntitule()).append("\n");

        }

        return  item.toString();
    }


}
