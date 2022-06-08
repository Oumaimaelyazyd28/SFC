package model;

public class Paiement {
        private String cin;
        private String membre;
        private String mois;
        private String frais;
        private String verse;
        private String reste;

    public Paiement(String cin, String membre, String mois, String frais, String verse, String reste) {
        this.cin = cin;
        this.membre = membre;
        this.mois = mois;
        this.frais = frais;
        this.verse = verse;
        this.reste = reste;
    }

    public String getCin() {
        return cin;
    }

    public void setCin(String cin) {
        this.cin = cin;
    }

    public String getMembre() {
        return membre;
    }

    public void setMembre(String membre) {
        this.membre = membre;
    }

    public String getMois() {
        return mois;
    }

    public void setMois(String mois) {
        this.mois = mois;
    }

    public String getFrais() {
        return frais;
    }

    public void setFrais(String frais) {
        this.frais = frais;
    }

    public String getVerse() {
        return verse;
    }

    public void setVerse(String verse) {
        this.verse = verse;
    }

    public String getReste() {
        return reste;
    }

    public void setReste(String reste) {
        this.reste = reste;
    }
}
