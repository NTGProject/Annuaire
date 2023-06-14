package com.example.annuaire.Fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.annuaire.R;
import com.example.annuaire.controller.DepartementAdapter;
import com.example.annuaire.controller.DirectionAdapter;
import com.example.annuaire.controller.GeneralMethode;
import com.example.annuaire.controller.PersonnelAdapter;
import com.example.annuaire.controller.ServiceAdapter;
import com.example.annuaire.model.Departement;
import com.example.annuaire.model.Direction;
import com.example.annuaire.model.Personnel;
import com.example.annuaire.model.Service;
import com.example.annuaire.model.SpinnerItem;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.function.Function;

import static com.example.annuaire.model.general.getIdEntreprise;


public class AdvancedFiltreFragment extends Fragment {

    RecyclerView recyclerView;
    TextView liste;


    Spinner direction;
    Spinner departement;

    Spinner service;
    ArrayList<Personnel> personnels ;
    EditText search ;
    int DirectionId=0;
    int ServiceId=0;
    int DepartementId=0;



    TextView serviceDetails;
    TextView depDetails;
    TextView directionDetails;

    ArrayList<Direction> Selecteddirections=new ArrayList<Direction>() ;
    ArrayList<Departement> SelectedDepartement=new ArrayList<Departement>() ;
    ArrayList<Departement> dep;
    ArrayList<Service>  services;

    ArrayList<Service> SelectedService=new ArrayList<Service>() ;


    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,   Bundle savedInstanceState) {
        // Inflate the layout for this fragment




        View view = inflater.inflate(R.layout.fragment_advanced_filtre, container, false);
        liste = view.findViewById(R.id.liste);
        direction = view.findViewById(R.id.direction);
        departement = view.findViewById(R.id.departement);
        service = view.findViewById(R.id.service);
        serviceDetails = view.findViewById(R.id.serviceDetails);
        depDetails = view.findViewById(R.id.depDetails);
        directionDetails = view.findViewById(R.id.directionDetails);
        search = view.findViewById(R.id.search);
        recyclerView = view.findViewById(R.id.personelReview);

        GeneralMethode.viderRecheche(search);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(view.getContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);




        ArrayList<Direction> directions = GeneralMethode.selectFromTable(Direction.class, "direction",
                Arrays.asList("id", "intitule","email","telephone","id_societe","id_directeur")," id_societe="+getIdEntreprise()+"");
        GeneralMethode.RemplirList(direction, directions, "Les Directions");


        GeneralMethode.RemplirListAfterSelection(direction,departement, "Les Départements",selectedDirectionId -> {
            DirectionId= selectedDirectionId;
            Selecteddirections.clear();
            String Clausewhere=" id_societe="+getIdEntreprise();
            if(selectedDirectionId!=0){
                Clausewhere+=" and id_direction="+selectedDirectionId;
                int id_dir=(int) direction.getSelectedItemId()-1;
                Selecteddirections.add(directions.get(id_dir));
            }
           dep = GeneralMethode.selectFromTable(Departement.class, "departement_societe",
                    Arrays.asList("id", "intitule","id_chef_dep","email","telephone","id_direction"),Clausewhere);
            actionRecherche();
            return dep;
        });



        GeneralMethode.RemplirListAfterSelection(departement,service, "Les Services",selectedDepId -> {
            DepartementId=selectedDepId;
            SelectedDepartement.clear();

            String Clausewhere=" id_societe="+getIdEntreprise();
            if(selectedDepId!=0){
                Clausewhere+=" and id_departement="+selectedDepId;
                int id_dep=(int) departement.getSelectedItemId()-1;
                SelectedDepartement.add(dep.get(id_dep));
            }

           services = GeneralMethode.selectFromTable(Service.class, "service_societe",
                    Arrays.asList("id", "intitule", "id_chef_service","email","telephone","id_departement"),Clausewhere);
            GeneralMethode.RemplirList(service, services, "Les Services"); // Call the method with the spinner, direction list, and the first element string

            actionRecherche();

            return services;
        });



        GeneralMethode.<SpinnerItem>actionSpinner(service, selectedDepId -> {
            SelectedService.clear();
            if(selectedDepId!=0){
                int id_dep=(int) service.getSelectedItemId()-1;
                SelectedService.add(services.get(id_dep));
            }
            ServiceId = selectedDepId;
            actionRecherche();
            return null;
        });


        GeneralMethode.ActionMoreDetailsAnnuaire(directionDetails,Selecteddirections, null, new Function<Direction, String>() {
            @Override
            public String apply(Direction item) {
                return new DirectionAdapter(Selecteddirections,null).getMoreDetails(item);
            }
        });



        GeneralMethode.ActionMoreDetailsAnnuaire(depDetails,SelectedDepartement, null, new Function<Departement, String>() {
            @Override
            public String apply(Departement item) {
                return new DepartementAdapter(SelectedDepartement,null).getMoreDetails(item);
            }
        });


        GeneralMethode.ActionMoreDetailsAnnuaire(serviceDetails,SelectedService, null, new Function<Service, String>() {
            @Override
            public String apply(Service item) {
                return new ServiceAdapter(SelectedService,null).getMoreDetails(item);
            }
        });

        liste.setText("Liste des Employées");


        return view;
    }

   public void actionRecherche(){
       String Clausewhere=" id_societe="+getIdEntreprise();
       if(DirectionId!=0){
           Clausewhere+=" and id_direction="+DirectionId;
       }
       if(DepartementId!=0){
           Clausewhere+=" and id_departement="+DepartementId;
       }
       if(ServiceId!=0){
           Clausewhere+=" and id_service="+ServiceId;
       }

       personnels = GeneralMethode.selectFromTable(Personnel.class, "personnel_societe",
               Arrays.asList("id_person", "nom","prenom","telephone","email","date_naissance","date_recrutement","grade",
                       "diplome","id_direction","id_service","id_departement"),Clausewhere);
       System.out.println(personnels.size());
       GeneralMethode.LoadAdapterContent(search,personnels, recyclerView, list ->{
           return new PersonnelAdapter(list,null);});
   }


}
