public class Magasin {
    private int id;
    private String nom;
    private int id_gerant;
    private String adresse;
    private String remarques;

    public Magasin(int id, String nom, int id_gerant, String adresse, String remarques){
        this.id =id;
        this.nom = nom;
        this.id_gerant = id_gerant;
        this.adresse = adresse;
        this.remarques = remarques;
    }

    public int getId(){
        return this.id;
    }
    public String getNom(){
        return this.nom;
    }
    public int getId_gerant(){
        return this.id_gerant;
    }
    public String getAdresse(){
        return this.adresse;
    }
    public String getRemarques(){
        return this.remarques;
    }
}
