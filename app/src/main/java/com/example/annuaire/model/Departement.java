package com.example.annuaire.model;

public class Departement {
    private int id;
    private String intitule;
    private int id_chef_dep;
    private String email;
    private String telephone;
    private Integer id_direction;

    public Departement(){

    }
    /*public Departement(int id, String intitule, int chef_departement, String email, String telephone, int id_direction, int id_entreprise) {
        super(email,telephone);
        this.id = id;
        this.intitule = intitule;
        this.chef_departement = chef_departement;
        this.email = email;
        this.telephone = telephone;
        this.id_direction = id_direction;
        this.id_entreprise = id_entreprise;
    }*/

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIntitule() {
        return intitule;
    }

    public void setIntitule(String intitule) {
        this.intitule = intitule;
    }

    public int getChef_departement() {
        return id_chef_dep;
    }

    public void setChef_departement(int id_chef_departement) {
        this.id_chef_dep = id_chef_departement;
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelephone() {
        return telephone;
    }
    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public Integer getId_direction() {
        return id_direction;
    }
    public void setId_direction(Integer id_direction) {
        this.id_direction = id_direction;
    }


}
