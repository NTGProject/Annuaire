package com.example.annuaire.Fragment;



import static com.example.annuaire.model.general.getIdEntreprise;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.annuaire.R;
import com.example.annuaire.controller.DataBaseConnection;
import com.example.annuaire.controller.DepartementAdapter;
import com.example.annuaire.controller.DirectionAdapter;
import com.example.annuaire.controller.GeneralMethode;
import com.example.annuaire.controller.OrganisationAdapter;
import com.example.annuaire.controller.PersonnelAdapter;
import com.example.annuaire.controller.RecyclerViewInterface;
import com.example.annuaire.controller.ServiceAdapter;
import com.example.annuaire.model.Departement;
import com.example.annuaire.model.Direction;
import com.example.annuaire.model.Organisation;
import com.example.annuaire.model.Personnel;
import com.example.annuaire.model.Service;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Arrays;


public class HomeFragment extends Fragment implements RecyclerViewInterface {
    Connection connection = null;

    OrganisationAdapter adapter2;
    RecyclerView recyclerView;
    RecyclerView recyclerView2;
    TextView liste;
    ArrayList<Direction> directions ;
    ArrayList<Departement> departements ;
    ArrayList<Service> services ;
    ArrayList<Personnel> personnels ;
    EditText search ;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,   Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        connection=DataBaseConnection.getInstance();
        liste = view.findViewById(R.id.liste);

        recyclerView = view.findViewById(R.id.RVDetails);
        recyclerView2 = view.findViewById(R.id.RVOrganisations);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(view.getContext(), LinearLayoutManager.VERTICAL, false);
        LinearLayoutManager linearLayoutManager2 = new LinearLayoutManager(view.getContext(), LinearLayoutManager.HORIZONTAL, false);

        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView2.setLayoutManager(linearLayoutManager2);

        ArrayList<Organisation> organisations = new ArrayList<>();
        organisations.add(new Organisation("Direction", "direction"));
        organisations.add(new Organisation("Département", "departement"));
        organisations.add(new Organisation("Service", "service"));
        organisations.add(new Organisation("Personnel", "personnel"));
        adapter2 = new OrganisationAdapter(organisations, this);
        recyclerView2.setAdapter(adapter2);

        liste.setText("Liste des Directions");

       directions = GeneralMethode.selectFromTable(Direction.class, "direction",
                Arrays.asList("id", "intitule","email","telephone","id_societe","id_directeur")," id_societe="+getIdEntreprise()+"");


        search = view.findViewById(R.id.search);
        GeneralMethode.viderRecheche(search);


        GeneralMethode.LoadAdapterContent(search,directions, recyclerView, list ->{
            return new DirectionAdapter(list,this);});






        return view;
    }





    @Override
    public void onItemClick(int position) {

        switch (position){
            case 0:
                GeneralMethode.LoadAdapterContent(search,directions, recyclerView, list ->{
                    return new DirectionAdapter(list,this);});
                break;
            case 1:
                departements = GeneralMethode.selectFromTable(Departement.class, "departement_societe",
                        Arrays.asList("id", "intitule","id_chef_dep","email","telephone","id_direction")," id_societe="+getIdEntreprise()+"");

                GeneralMethode.LoadAdapterContent(search,departements, recyclerView, list ->{
                    return new DepartementAdapter(list,this);});
                liste.setText("Liste des Département");

                break;
            case 2:
                services = GeneralMethode.selectFromTable(Service.class, "service_societe",
                        Arrays.asList("id", "intitule", "id_chef_service","email","telephone","id_departement")," id_societe="+getIdEntreprise()+"");

                GeneralMethode.LoadAdapterContent(search,services, recyclerView, list ->{
                    return new ServiceAdapter(list,this);});
                liste.setText("Liste des Services");

                break;
            case 3:
                personnels = GeneralMethode.selectFromTable(Personnel.class, "personnel_societe",
                        Arrays.asList("id_person", "nom","prenom","telephone","email","date_naissance","date_recrutement","grade",
                                "diplome","id_direction","id_service","id_departement")," id_societe="+getIdEntreprise()+"");
                GeneralMethode.LoadAdapterContent(search,personnels, recyclerView, list ->{
                    return new PersonnelAdapter(list,this);});
                liste.setText("Liste des Employées");

                break;
            default:
                break;
        }
    }

    @Override
    public void onListDesPersonnelsClick(int position) {
        int id_service_position = services.get(position).getId();
        personnels = GeneralMethode.selectFromTable(Personnel.class, "personnel_societe",
        Arrays.asList("id_person", "nom","prenom","telephone","email","date_naissance","date_recrutement","grade",
                        "diplome","id_direction","id_service","id_departement"),
                " id_societe="+getIdEntreprise()+" and id_service="+id_service_position);

        GeneralMethode.LoadAdapterContent(search,personnels, recyclerView, list ->{
            return new PersonnelAdapter(list,this);});

        adapter2.setPositionToChange(3);
        liste.setText("Employées de service "+services.get(position).getIntitule());
    }


    @Override
    public void onListDesServicesClick(int position) {
        int id_departement_position = departements.get(position).getId();
        services = GeneralMethode.selectFromTable(Service.class, "service_societe",
                Arrays.asList("id", "intitule", "id_chef_service","email","telephone","id_departement"),
                " id_societe="+getIdEntreprise()+" and id_departement ="+id_departement_position);

        GeneralMethode.LoadAdapterContent(search,services, recyclerView, list ->{
            return new ServiceAdapter(list,this);});
        adapter2.setPositionToChange(2);
        liste.setText("Services de département "+departements.get(position).getIntitule());
    }




    @Override
    public void onListDesDepartementsClick(int position) {
       int id_direction_position = directions.get(position).getId();
        departements = GeneralMethode.selectFromTable(Departement.class, "departement_societe",
                Arrays.asList("id", "intitule","id_chef_dep","email","telephone","id_direction"),
                " id_societe="+getIdEntreprise()+" and id_direction ="+id_direction_position);
        GeneralMethode.LoadAdapterContent(search,departements, recyclerView, list ->{
            return new DepartementAdapter(list,this);});
        adapter2.setPositionToChange(1);
        liste.setText("Départements de direction "+directions.get(position).getIntitule());
    }

}
