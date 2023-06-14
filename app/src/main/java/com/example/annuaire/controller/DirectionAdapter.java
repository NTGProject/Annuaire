package com.example.annuaire.controller;


import android.annotation.SuppressLint;
import android.text.SpannableStringBuilder;


import com.example.annuaire.R;
import com.example.annuaire.model.Direction;
import com.example.annuaire.model.Personnel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DirectionAdapter extends GeneralAdaptar<Direction> {

    private final RecyclerViewInterface recyclerViewInterface;

    public DirectionAdapter(ArrayList<Direction> directions, RecyclerViewInterface recyclerViewInterface) {
        super(directions, recyclerViewInterface);
        this.recyclerViewInterface = recyclerViewInterface;


    }


    @SuppressLint("SetTextI18n")
    @Override
    protected void bindData(GeneralAdaptar.ViewHolder holder, int position, Direction item) {
        holder.titre.setText(item.getIntitule() + ": ");

    }

    @Override
    public int getLayoutId() {
        return R.layout.viewdirection;
    }

    @Override
    protected String getEmail(Direction item) {
        return item.getEmail();
    }


    protected String getCall(Direction item) {
        return item.getTelephone();
    }

    @Override
    protected void onClickTo(int p) {
        recyclerViewInterface.onListDesDepartementsClick(p);
    }

    @Override
    public String getMoreDetails(Direction direction) {


        SpannableStringBuilder item=GeneralMethode.showTableDataInDialog(Arrays.asList("intitule","email","telephone"), direction, Direction.class);

        List<String> nom_preno=  Arrays.asList( "nom","prenom");

        Integer director=direction.getId_directeur();

        ArrayList<Personnel>  personels = GeneralMethode.selectFromTable(Personnel.class, "personnel",
                nom_preno," id_person="+director+"");

        if(personels.size()!=0) item.append("Directeur : ").append(personels.get(0).getNom()).append(" ").append(personels.get(0).getPrenom()).append("\n");




        return  item.toString();
    }



}
