package com.example.annuaire.model;

public class Service {
    private int id;
    private String intitule;
    private String email;
    private String telephone;
    private Integer id_departement;
    private Integer id_chef_service;


    public Service() {}
    /*public Service(int id, String intitule, String chef_service, String email, String telephone, int id_departement) {
        this.id = id;
        this.intitule = intitule;
        this.chef_service = chef_service;
        this.email = email;
        this.telephone = telephone;
        this.id_departement = id_departement;
    }  */
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

    public Integer getChef_service() {
        return id_chef_service;
    }
    public void setChef_service(Integer id_chef_service) {
        this.id_chef_service = id_chef_service;
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

    public Integer getId_departement() {
        return id_departement;
    }
    public void setId_departement(Integer id_departement) {
        this.id_departement = id_departement;
    }
}
