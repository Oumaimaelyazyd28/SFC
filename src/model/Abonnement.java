package model;

public class Abonnement {
    private String abonnement;
    private String Sport;
    private String type ;
    private String heure;
    private String prix;

    public Abonnement(String abonnement, String sport, String type, String heure, String prix) {
        this.abonnement = abonnement;
        Sport = sport;
        this.type = type;
        this.heure = heure;
        this.prix = prix;
    }

    public String getAbonnement() {
        return abonnement;
    }

    public void setAbonnement(String abonnement) {
        this.abonnement = abonnement;
    }

    public String getSport() {
        return Sport;
    }

    public void setSport(String sport) {
        Sport = sport;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getHeure() {
        return heure;
    }

    public void setHeure(String heure) {
        this.heure = heure;
    }

    public String getPrix() {
        return prix;
    }

    public void setPrix(String prix) {
        this.prix = prix;
    }
}
