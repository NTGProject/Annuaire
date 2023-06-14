package com.example.annuaire.model;


public class actualite {
    private int id;

    private String titre;
    private String description;

    private  byte[] image;
    private int id_societe;

    public actualite() {


    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }



    public String getTitle() {
        return titre;
    }
    public void setTitle(String title) {
        this.titre = title;
    }

    public String getDesc() {
        return description;
    }
    public void setDesc(String desc) {
        this.description = desc;
    }


    public  byte[] getImg() {
        return image;
    }
    public void setImg( byte[] img) {
        this.image = img;
    }


    public int getId_societe() {
        return id_societe;
    }
    public void setId_societe(int id) {
        this.id_societe = id;
    }
}
