package model;

public class Sport {
    private String sport;
    private String salle;
    //private String coach;
    //private String type;

    /*public Sport(String sport, String type, String coach, String salle) {
        this.sport = sport;
        this.type = type;
        this.coach = coach;
        this.salle = salle;
    }*/

    public Sport(String sport, String salle) {
        this.sport = sport;
        this.salle = salle;
    }

    public String getSport() {
        return sport;
    }

    public void setSport(String sport) {
        this.sport = sport;
    }

    public String getSalle() {
        return salle;
    }

    public void setSalle(String salle) {
        this.salle = salle;
    }
}
