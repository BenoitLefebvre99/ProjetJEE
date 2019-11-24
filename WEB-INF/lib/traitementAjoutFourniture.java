import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.*;
import java.sql.*;

@WebServlet("/traitementAjoutFourniture")
public class traitementAjoutFourniture extends HttpServlet {
        public void doPost(HttpServletRequest req, HttpServletResponse res) 
            throws ServletException, IOException {

                PrintWriter out = res.getWriter();

                try {
                    //CONNEXION A LA BASE DE DONNEES.
                    Class.forName("org.postgresql.Driver");
                    bdd c = new bdd();
                    Connection conn = DriverManager.getConnection(c.getUrl(), c.getLogin(), c.getPassword());

                    //AJOUT DU MAGASIN DANS LA BDD
                    String query = "INSERT INTO magasin_possede_fourniture VALUES ('', "+
                        req.getParameter("inputNomGerant") + ","+
                        req.getParameter("inputPrenomGerant")+","+
                        req.getParameter("inputStatut")+","+
                        req.getParameter("inputRemarque")+");";
                    Statement stat = conn.createStatement();
                    stat.executeUpdate(query);
                    stat.close();

                    //ON REDIRIGE VERS LA PAGE DU FORMULAIRE AVEC SUCCES
                    res.sendRedirect(req.getContextPath()+"/contAjoFournitures#success");
                }catch(Exception e){
                    //ON REDIRIGE VERS LA PAGE DU FORMULAIRE AVEC ERREUR
                    res.sendRedirect(req.getContextPath()+"/contAjoFournitures#error");
                }
            }
}