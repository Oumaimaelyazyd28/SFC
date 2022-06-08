package model;

public class Member extends Person{
    private String abonnement;
    private String dateInscription;

    public Member(String nom, String prenom, String cin, String naissance, String abonnement) {
        super(nom, prenom, cin, naissance);
        this.abonnement = abonnement;
    }


    public String getAbonnement() {
        return abonnement;
    }

    public void setAbonnement(String abonnement) {
        this.abonnement = abonnement;
    }

    public String getDateInscription() {
        return dateInscription;
    }

    public void setDateInscription(String dateInscription) {
        this.dateInscription = dateInscription;
    }

  /*  public void ajouterMembreDB(){
        DBinteraction.Connect();
        String sql = "INSERT INTO client(nom, prenom, cin, naissance, abonnement)"+"VALUES (?,?,?,?,?)";
        int addRows = DBinteraction.Maj(sql);
        PreparedStatement ps;

        if (addRows>0){
            setNom(ps.);
        }


    }*/

}
