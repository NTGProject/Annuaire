package com.example.annuaire.controller;



import android.text.SpannableStringBuilder;

import com.example.annuaire.R;
import com.example.annuaire.model.Departement;
import com.example.annuaire.model.Direction;
import com.example.annuaire.model.Personnel;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DepartementAdapter extends GeneralAdaptar<Departement> {

    private  RecyclerViewInterface recyclerViewInterface;

    public DepartementAdapter(ArrayList<Departement> departements, RecyclerViewInterface recyclerViewInterface) {
        super(departements, recyclerViewInterface);
        this.recyclerViewInterface = recyclerViewInterface;


    }


    @Override
    protected void bindData(GeneralAdaptar.ViewHolder holder, int position, Departement item) {
        holder.titre.setText(String.valueOf(item.getIntitule()+": "));

    }

    @Override
    public int getLayoutId() {
        return R.layout.viewdepartement;
    }

    @Override
    protected String getEmail(Departement item) {
        return item.getEmail();
    }


    protected String getCall(Departement item) {
        return item.getTelephone();
    }

    @Override
    protected void onClickTo(int p) {
        recyclerViewInterface.onListDesServicesClick(p);
    }

    @Override
    public String getMoreDetails(Departement departement) {


        SpannableStringBuilder item=GeneralMethode.showTableDataInDialog(Arrays.asList("intitule","email","telephone"), departement, Departement.class);

        List intitule=  Arrays.asList( "intitule");
        List nom_preno=  Arrays.asList( "nom","prenom");
        Integer chef_dep=departement.getChef_departement();
        Integer id_direction=departement.getId_direction();
        ArrayList<Personnel>  personels = GeneralMethode.selectFromTable(Personnel.class, "personnel",
                nom_preno," id_person="+chef_dep+"");

        ArrayList<Direction>  direction = GeneralMethode.selectFromTable(Direction.class, "direction",
                intitule," id="+id_direction+"");
        System.out.println(id_direction+" "+direction+" "+direction.size());
        if(personels.size()!=0) item.append("Chef Département : ").append(personels.get(0).getNom()+" "+personels.get(0).getPrenom()).append("\n");
        if(direction.size()!=0)   item.append("Direction : ").append(direction.get(0).getIntitule()).append("\n");




        return  item.toString();
    }


}
