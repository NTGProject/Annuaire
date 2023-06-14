package com.example.annuaire.model;

public class Direction {
    private int id;
    private String intitule;
    private String email;
    private String telephone;
    private Integer id_societe;
    private Integer id_directeur;


    public Direction(){

    }

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



    public Integer getId_societe() {
        return id_societe;
    }
    public void setId_societe(Integer id_entreprise) {
        this.id_societe = id_societe;
    }


    public Integer getId_directeur() {
        return id_directeur;
    }
    public void setId_directeur(Integer id_directeur) {
        this.id_directeur = id_directeur;
    }
}
