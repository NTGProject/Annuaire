package com.example.annuaire.model;

public class societe  {


    private int id;
    private String label;
    private String description;
    private String website;

    private String email;
    private String telephone;
    private String adresse;
    private Integer id_directeur;
    private Integer id_entreprise;
    private  byte[] image_soc;

    private String linked_In;

    private String instagram;
    private  String facebook;


    public societe(){

    }

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

    public String getLabel() {
        return label;
    }
    public void setLabel(String label) {
        this.label = label;
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

    public int getId_entreprise() {
        return id_entreprise;
    }
    public void setId_entreprise(int id_entreprise) {
        this.id_entreprise = id_entreprise;
    }

    public  byte[] getImg() {
        return image_soc;
    }
    public void setImg( byte[] img) {
        this.image_soc = img;
    }


    public String getLinked_in() {
        return linked_In;
    }
    public void setlinked_in(String linked_id) {
        this.linked_In = linked_id;
    }


    public String getInstagram() {
        return instagram;
    }
    public void setInstagram(String instagram) {
        this.instagram = instagram;
    }



    public  String getFacebook() {
        return facebook;
    }
    public  void setFacebook(String facebooks) {
        facebook = facebooks;
    }
}
