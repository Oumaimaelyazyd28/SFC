package model;

public class Person {
    private String nom;
    private String prenom;
    private String cin;
    private String naissance;


    public Person(String nom, String prenom, String cin, String naissance) {
        this.nom = nom;
        this.prenom = prenom;
        this.cin = cin;
        this.naissance = naissance;
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

    public String getCin() {
        return cin;
    }

    public void setCin(String cin) {
        this.cin = cin;
    }

    public String getNaissance() {
        return naissance;
    }

    public void setNaissance(String naissance) {
        this.naissance = naissance;
    }
}
