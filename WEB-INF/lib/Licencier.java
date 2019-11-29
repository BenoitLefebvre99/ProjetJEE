import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.*;
import java.sql.*;

@WebServlet("/Licencier")
public class Licencier extends HttpServlet {
    public void doPost(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        PrintWriter out = res.getWriter();

        try {
            //CONNEXION A LA BASE DE DONNEES.
            Class.forName("org.postgresql.Driver");
            BDD c = new BDD();
            Connection conn = DriverManager.getConnection(c.getUrl(), c.getLogin(), c.getPassword());

            //AJOUT DU MAGASIN DANS LA BDD
            String query = "DELETE FROM gerant WHERE id_gerant = '"+req.getParameter("id")+"'";
            Statement stat = conn.createStatement();
            stat.executeUpdate(query);
            stat.close();

            //ON REDIRIGE VERS LA PAGE DU FORMULAIRE AVEC SUCCES
            res.sendRedirect(req.getContextPath()+"/ContGerant#success");
        }catch(Exception e){
            //ON REDIRIGE VERS LA PAGE DU FORMULAIRE AVEC ERREUR
            res.sendRedirect(req.getContextPath()+"/ContGerant#error");
        }
    }
}
