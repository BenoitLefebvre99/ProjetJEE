public class fourniture {
    private int id;
    private String nom;
    private int prix_unitaire;
    private boolean recent;

    public fourniture(int id, String nom, int prix_unitaire, boolean recent){
        this.id = id;
        this.nom = nom;
        this.prix_unitaire = prix_unitaire;
        this.recent = recent;
    }

    public int getId(){
        return this.id;
    }
    public String getNom(){
        return this.nom;
    }
    public int getPrix_unitaire(){
        return this.prix_unitaire;
    }
    public boolean getRecent(){return this.recent}
}
