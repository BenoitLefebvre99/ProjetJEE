import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.*;
import java.sql.*;
import org.unbescape.html.HtmlEscape;
@WebServlet("/ContGerant")
public class ContGerant extends HttpServlet {
    public void service(HttpServletRequest req, HttpServletResponse res)
        throws ServletException, IOException {
            ContIntegrer include = new ContIntegrer();
            res.setContentType("text/html");
            PrintWriter out = res.getWriter();
            //ON ENVOIE LA FORME PROPRE AU SITE ET DISPONIBLE SUR TOUTES LES PAGES
            out.println("<html>");
            String head = HtmlEscape.unescapeHtml(include.getContent(FichiersInclude.HEAD));
            out.println(head);
            out.println("<body>");
            String navbar = HtmlEscape.unescapeHtml(include.getContent(FichiersInclude.NAVBAR));
            out.println(navbar);
            String sidebar = HtmlEscape.unescapeHtml(include.getContent(FichiersInclude.SIDEBAR));
            out.println(sidebar);

            //ON ENVOIE LA PARTIE INTERNE ET SPECIFIQUE A LA PAGE
            out.println("<div class=\"contenu\" id=\"contGerant\">");

            try {
                //CONNEXION A LA BASE DE DONNEES.
                Class.forName("org.postgresql.Driver");
                BDD c = new BDD();
                Connection conn = DriverManager.getConnection(c.getUrl(), c.getLogin(), c.getPassword());

                //ON RECUPERE LE NOMBRE D'ENTREES DANS LA TABLE
                String sql = "SELECT COUNT(*) FROM gerant;";
                Statement stat = conn.createStatement();
                ResultSet rs = stat.executeQuery(sql);

                while(rs.next()){
                    out.println("<h1>Liste des gérants<span class=\"badge badge-light\">"+rs.getString(1)+"</span></h1>");
                }
                
                out.println("<div class=\"shadow p-3 mb-5 bg-white rounded\">");
                out.println("<div class=\"card\" >");
                out.println("<div class=\"card-body\">");
                out.println("<table class=\"table table-striped\">");
                out.println("<thead>");      
                out.println("<tr>");
                out.println("<th scope=\"col\">ID</th>");
                out.println("<th scope=\"col\">Nom</th>");
                out.println("<th scope=\"col\">Prénom</th>");
                out.println("<th scope=\"col\">Statut</th>");
                out.println("<th scope=\"col\">Remarques</th>");        
                out.println("<th scope=\"col\">Suppression</th>");
                out.println("</tr>");
                out.println("</thead>");  
                out.println("<tbody>"); 
                
                //ON RECUPERE LE CONTENU DU TABLEAU
                sql = "SELECT * FROM gerant ORDER BY id_gerant ASC;";
                rs = stat.executeQuery(sql);

                //ON AFFICHE LE TABLEAU
                while(rs.next()){
                    out.println("<tr>");
                    out.println("<th scope=\"row\">"+rs.getInt("id_gerant")+"</th>");
                    out.println("<td>"+rs.getString("nom_gerant")+"</td>");
                    out.println("<td>"+rs.getString("prenom_gerant")+"</td>");
                    out.println("<td>"+rs.getInt("id_statut")+"</td>");
                    out.println("<td>"+rs.getString("remarques_gerant")+"</td>");
                    out.println("<td><button type=\"button\" class=\"btn btn-danger\">Supprimer</button></td>");
                    out.println("</tr>");
                }
                out.println("</tbody>");
                stat.close();
            }catch(Exception e){
                out.println(e.getMessage());
            }
            String cookies = HtmlEscape.unescapeHtml(include.getContent(FichiersInclude.COOKIES));
            out.println(cookies);

            out.println("</body>");
            out.println("</html>");

            out.close();
        }
}