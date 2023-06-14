package com.example.annuaire.model;

import java.util.Date;

public class Personnel {
    private int id_person;
    private String nom;
    private String prenom;
    private String telephone;
    private String email;
    private Date date_naissance;
    private Date date_recrutement;
    private String grade;
    private String diplome;
    private Integer id_service=0;
    private Integer id_departement=0;


    private Integer id_entreprise;
    private Integer id_direction;
    public Personnel(){}
  /*  public Personnel(int id, String nom, String prenom, String telephone, String email, Date date_naissance, Date date_recrutement, String grade, String diplome, int id_service, int id_departement) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.telephone = telephone;
        this.email = email;
        this.date_naissance = date_naissance;
        this.date_recrutement = date_recrutement;
        this.grade = grade;
        this.diplome = diplome;
        this.id_service = id_service;
        this.id_departement = id_departement;
    }*/

    public int getId() {
        return id_person;
    }

    public void setId(int id) {
        this.id_person = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getDate_naissance() {
        return date_naissance;
    }

    public void setDate_naissance(Date date_naissance) {
        this.date_naissance = date_naissance;
    }

    public Date getDate_recrutement() {
        return date_recrutement;
    }

    public void setDate_recrutement(Date date_recrutement) {
        this.date_recrutement = date_recrutement;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getDiplome() {
        return diplome;
    }

    public void setDiplome(String diplome) {
        this.diplome = diplome;
    }

    public Integer getId_service() {
        return id_service;
    }

    public void setId_service(Integer id_service) {
        this.id_service = id_service;
    }

    public Integer getId_departement() {
        return id_departement;
    }

    public void setId_departement(Integer id_departement) {
        this.id_departement = id_departement;
    }


    public Integer getId_entreprise() {
        return id_entreprise;
    }

    public void setId_entreprise(Integer id_entreprise) {
        this.id_entreprise = id_entreprise;
    }



    public Integer getId_direction() {
        return id_direction;
    }

    public void setId_direction(Integer id_direction) {
        this.id_direction = id_direction;
    }
}
