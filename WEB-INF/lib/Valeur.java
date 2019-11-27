import java.sql.*;

public class Valeur {
    private int valeur;
    private int idMagasin;

    public Valeur(int magasin){
        this.valeur = 0;
        this.idMagasin = magasin;
    }

    private void majValeur(){
        this.valeur=0;
        try {
            //CONNEXION A LA BASE DE DONNEES.
            Class.forName("org.postgresql.Driver");
            BDD c = new BDD();
            Connection conn = DriverManager.getConnection(c.getUrl(), c.getLogin(), c.getPassword());


            String sql = "SELECT SUM(mpf.quantite_stock * f.prix_unitaire_fourniture)"+
                        " FROM magasin AS m, magasin_possede_fourniture AS mpf, fourniture AS f "+
                        " WHERE mpf.id_magasin = '"+this.idMagasin+"' "+
                        " AND mpf.id_magasin = m.id"+
                        " AND mpf.id_fourniture = f.id_fourniture;";

            Statement stat = conn.createStatement();
            ResultSet rs = stat.executeQuery(sql);

            while(rs.next()){

                this.valeur = Integer.parseInt(rs.getString(1));
            }

            stat.close();
            conn.close();
        }catch(Exception e){
                //this.chiffreAffaire= this.idMagasin;
        }
    }


    public boolean isItMe(int you){
        return this.idMagasin == you;
    }

    public int getValeur(){
        this.majValeur();
        return this.valeur;
    }
}