import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.*;
import java.sql.*;
import org.unbescape.html.HtmlEscape;
@WebServlet("/contFournitures")
public class contFournitures extends HttpServlet {
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
            out.println("<div class=\"contenu\" id=\"contFournitures\" >");
            out.println("<h1>Afficher les stocks <div class=\"dropdown\" style=\"float:right;\">");

            out.println("<button class=\"btn btn-info btn-lg dropdown-toggle\" type=\"button\" id=\"dropdownMenuButton\" data-toggle=\"dropdown\" aria-haspopup=\"true\" aria-expanded=\"false\">");
            out.println("Tous les magasins");
            out.println("</button>");
            out.println("<div class=\"dropdown-menu\" aria-labelledby=\"dropdownMenuButton\">");
            out.println("<a class=\"dropdown-item\" href=\"#\">Magasin 1</a>");
            out.println("<a class=\"dropdown-item\" href=\"#\">Magasin 2</a>");
            out.println("<a class=\"dropdown-item\" href=\"#\">Magasin 3</a>");
            out.println("<a class=\"dropdown-item\" href=\"#\">Ajouter un Magasin</a>");
            out.println("</div>");
            out.println("</div></h1>");

            try {
                //CONNEXION A LA BASE DE DONNEES.
                Class.forName("org.postgresql.Driver");
                bdd c = new bdd();
                Connection conn = DriverManager.getConnection(c.getUrl(), c.getLogin(), c.getPassword());

                //ON RECUPERE LE CONTENU DES CARDS DANS LA BDD
                String sql = "SELECT * " +
                        "FROM magasin_possede_fourniture AS mpf, fourniture AS f, magasin AS m " +
                        "WHERE f.id_fourniture = mpf.id_fourniture  " +
                        "AND m.id = mpf.id_magasin;";
                Statement stat = conn.createStatement();
                ResultSet rs = stat.executeQuery(sql);

                int idMagasin=-1;
                //ON AFFICHE LES CARDS
                while(rs.next()){
                    if(rs.getInt("id_magasin") != idMagasin){
                        out.println("<h2>"+rs.getString("nom_magasin")+"</h2>");
                        idMagasin = rs.getInt("id_magasin");
                    }
                    out.println("<div id=\"cardstocks\">");
                    out.println("<div class=\"card\" style=\"width: 18rem;\">");
                    out.println("<div class=\"card-body\">");
                    out.println("<h5 class=\"card-title\">"+rs.getString("nom_fourniture")+"</h5>");
                    out.println("<h6 class=\"card-subtitle mb-2 text-muted\">Valeur : "+ rs.getInt("prix_unitaire_fourniture")*rs.getInt("quantite_stock")+" €</h6>");
                    out.println("<hr>");
                    out.println("<p class=\"card-text\">Quantité : "+rs.getInt("quantite_stock")+"</p>");
                    out.println("<p class=\"card-text\">Prix Unitaire : "+rs.getInt("prix_unitaire_fourniture")+" €</p>");
                    out.println("<a href=\"#contAjoFournitures\" class=\"btn btn-info\">Recommander</a>");
                    out.println("<a href=\"#contAjoFournitures\" class=\"btn btn-info\">Vendre</a>");
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