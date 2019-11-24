import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.*;
import java.sql.*;
import org.unbescape.html.HtmlEscape;
@WebServlet("/contMagasins")
public class contMagasins extends HttpServlet {
    public void service(HttpServletRequest req, HttpServletResponse res)
        throws ServletException, IOException {
            contIntegrer include = new contIntegrer();
            res.setContentType("text/html");
            PrintWriter out = res.getWriter();
            //ON ENVOIE LA FORME PROPRE AU SITE ET DISPONIBLE SUR TOUTES LES PAGES
            out.println("<html>");
            String head = HtmlEscape.unescapeHtml(include.getHead());
            out.println(head);
            out.println("<body>");
            String navbar = HtmlEscape.unescapeHtml(include.getNavbar());
            out.println(navbar);
            String sidebar = HtmlEscape.unescapeHtml(include.getSidebar());
            out.println(sidebar);

            //ON ENVOIE LA PARTIE INTERNE ET SPECIFIQUE A LA PAGE
            out.println("<div class=\"contenu\" id=\"contMagasins\">");

            try {
                //CONNEXION A LA BASE DE DONNEES.
                Class.forName("org.postgresql.Driver");
                bdd c = new bdd();
                Connection conn = DriverManager.getConnection(c.getUrl(), c.getLogin(), c.getPassword());

                //ON RECUPERE LE CONTENU DES CARDS DANS LA BDD
                String sql = "SELECT * FROM magasin AS m INNER JOIN gerant AS g ON m.id_gerant = g.id_gerant ORDER BY id ASC;";
                Statement stat = conn.createStatement();
                ResultSet rs = stat.executeQuery(sql);
                String sqldeux;
                Statement statdeux;
                ResultSet rsdeux;
                String gerant="";

                //ON AFFICHE LES CARDS
                while(rs.next()){
                    out.println("<div id=\"cardstocks\">");
                    out.println("<div class=\"card\" style=\"width: 18rem;\">");
                    out.println("<div class=\"card-body\">");

                    out.println("<h5 class=\"card-title\">"+rs.getString("nom_magasin")+"</h5>");
                    out.println("<h6 class=\"card-subtitle mb-2 text-muted\">"+rs.getString("nom_gerant")+" " +rs.getString("prenom_gerant")+"</h6>");
                    out.println("<p class=\"card-text\">"+rs.getString("adresse_magasin")+"</p>");
                    out.println("<p class=\"card-text\">Valeur totale : "+rs.getInt("CA_magasin")+" €</p>");
                    out.println("<p class=\"card-text\">"+rs.getString("remarques_magasin")+"</p>");

                    out.println("<a href=\"contGerant.html\" class=\"btn btn-info\">Gérant</a>");
                    out.println("<a href=\"contAjoFournitures.html\" class=\"btn btn-info\">Stocks</a>");

                    out.println("</div>");
                    out.println("</div>");
                    out.println("</div>");
                }

                stat.close();
            }catch(Exception e){
                out.println(e.getMessage());
            }
            String cookies = HtmlEscape.unescapeHtml(include.getCookies());
            out.println(cookies);

            out.println("</body>");
            out.println("</html>");

            out.close();
        }
}