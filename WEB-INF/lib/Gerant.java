public class Gerant {
    private int id;
    private String nom;
    private String prenom;
    private int id_statut;
    private String remarques;

    public Gerant(int id, String nom, String prenom, int id_statut, String remarques){
        this.id =id;
        this.nom = nom;
        this.prenom = prenom;
        this.id_statut = id_statut;
        this.remarques = remarques;
    }

    public int getId(){
        return this.id;
    }
    public String getNom(){
        return this.nom;
    }
    public String getPrenom(){ return this.prenom;}
    public int getId_statut(){
        return this.id_statut;
    }
    public String getRemarques(){
        return this.remarques;
    }
}
