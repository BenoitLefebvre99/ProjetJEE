import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.*;
import java.sql.*;

@WebServlet("/traitementAjoutMagasin")
public class traitementAjoutMagasin extends HttpServlet {
        public void doPost(HttpServletRequest req, HttpServletResponse res) 
            throws ServletException, IOException {

                PrintWriter out = res.getWriter();

                try {
                    //CONNEXION A LA BASE DE DONNEES.
                    Class.forName("org.postgresql.Driver");
                    bdd c = new bdd();
                    Connection conn = DriverManager.getConnection(c.getUrl(), c.getLogin(), c.getPassword());

                    //AJOUT DU MAGASIN DANS LA BDD
                    String query = "INSERT INTO magasin VALUES ('', "+
                        req.getParameter("inputNomMagasin") + ","+
                        req.getParameter("inputGerant")+","+
                        req.getParameter("inputAdresse")+","+
                        req.getParameter("inputRemarque")+",0);";
                    Statement stat = conn.createStatement();
                    stat.executeUpdate(query);
                    stat.close();

                    //ON REDIRIGE VERS LA PAGE DU FORMULAIRE AVEC SUCCES
                    res.sendRedirect(req.getContextPath()+"/contAjoMagasin#success");
                }catch(Exception e){
                    //ON REDIRIGE VERS LA PAGE DU FORMULAIRE AVEC ERREUR
                    res.sendRedirect(req.getContextPath()+"/contAjoMagasin#error");
                }
            }
}