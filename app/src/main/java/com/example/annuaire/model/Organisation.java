package com.example.annuaire.model;

public class Organisation {
    private String titre;
    private String image;

    public Organisation(String titre, String image) {
        this.titre = titre;
        this.image = image;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }
}
