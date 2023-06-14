package com.example.annuaire.model;

public class general {
    private static String entreprise;
    private static Integer id_entreprise;



    private static String societe;
    private static Integer id_societe;


    public general() {


    }



    public static String getEntreprise() {
        return entreprise;
    }

    public static void setEntreprise(String e) {
        entreprise = e;
    }


    public static Integer getIdEntreprise() {
        return id_entreprise;
    }
    public static void setIdEntreprise(Integer e) {
        id_entreprise = e;
    }


    public static String getSociete() {
        return societe;
    }
    public static void setSociete(String e) {
        societe = e;
    }


    public static Integer getIdSociete() {
        return id_societe;
    }
    public static void setIdSociete(Integer e) {
        id_societe = e;
    }

    private static String facebook;
    private static String linked_in;
    private static String Instagram;


    public static String getFacebook() {
        return facebook;
    }
    public static void setFacebook(String e) {
        facebook = e;
    }


    public static String getLinked_in() {
        return linked_in;
    }
    public static void setLinked_in(String e) {
        linked_in = e;
    }


    public static String getInstagram() {
        return Instagram;
    }
    public static void setInstagram(String e) {
        Instagram = e;
    }

}
