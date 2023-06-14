package com.example.annuaire.model;

public class entreprise  {


    private int id;
    private String nom;
    private String description;
    private String website;
    private String email;
    private String telephone;
    private String adresse;
    private Integer id_directeur;


    public entreprise(){

    }
    /* public Direction(int id, String intitule, String email, String telephone, String adresse, int id_entreprise) {
         this.id = id;
         this.intitule = intitule;
         this.email = email;
         this.telephone = telephone;
         this.adresse = adresse;
         this.id_entreprise = id_entreprise;
     }
 */
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public Integer getIdDirecteur() {
        return id_directeur;
    }
    public void setIdDirecteur(Integer id_directeur) {
        this.id_directeur = id_directeur;
    }

    public String getNom() {
        return nom;
    }
    public void setNom(String nom) {
        this.nom = nom;
    }


    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    public String getWebsite() {
        return website;
    }
    public void setWebsite(String website) {
        this.website = website;
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

    public String getAdresse() {
        return adresse;
    }
    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }



}
