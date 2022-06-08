package model;

public class Coach extends Person{
    private String sport;
    private String telephone;


    public Coach(String nom, String prenom, String cin, String naissance,String telephone, String sport) {
        super(nom, prenom, cin, naissance);
        this.telephone = telephone;
        this.sport = sport;
    }

    public String getSport() {
        return sport;
    }

    public void setSport(String sport) {
        this.sport = sport;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }
}
