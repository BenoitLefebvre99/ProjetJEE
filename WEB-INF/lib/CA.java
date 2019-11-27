import java.sql.*;

public class CA {
    private int chiffreAffaire;
    private int idMagasin;

    public CA(int magasin){
        this.chiffreAffaire = 0;
        this.idMagasin = magasin;
    }

    private void majChiffreAffaire(){
        this.chiffreAffaire=0;
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
                
                this.chiffreAffaire = Integer.parseInt(rs.getString(1));
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

    public int getChiffreAffaire(){
        this.majChiffreAffaire();
        return this.chiffreAffaire;
    }
}