import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.*;
import java.sql.*;
import org.unbescape.html.HtmlEscape;
@WebServlet("/contAjoMagasin")
public class contAjoMagasin extends HttpServlet {
    public void service(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        contIntegrer include = new contIntegrer();
        res.setContentType("text/html");
        PrintWriter out = res.getWriter();
        //ON ENVOIE LA FORME PROPRE AU SITE ET DISPONIBLE SUR TOUTES LES PAGES
        out.println("<html>");
        String head = HtmlEscape.unescapeHtml(include.getContent(fichiersInclude.HEAD));
        out.println(head);
        out.println("<body>");
        String navbar = HtmlEscape.unescapeHtml(include.getContent(fichiersInclude.NAVBAR));
        out.println(navbar);
        String sidebar = HtmlEscape.unescapeHtml(include.getContent(fichiersInclude.SIDEBAR));
        out.println(sidebar);

        //ON ENVOIE LA PARTIE INTERNE ET SPECIFIQUE A LA PAGE
        out.println("<div class=\"contenu\" id=\"contAjoMagasin\">");
        out.println("<h1>Créer un nouveau magasin</h1>");

        // Alertes à afficher au bon moment
        String alert = HtmlEscape.unescapeHtml(include.getContent(fichiersInclude.ALERT));
        out.println(alert);

        //Il faut créer le champ invisible id
        out.println("<div class=\"shadow p-3 mb-5 bg-white rounded\">");
        out.println("<div class=\"card\" >");
        out.println("<div class=\"card-body\">");

        //FORMULAIRE
        out.println("<form method=\"POST\" action=\"/traitementAjoutMagasin\">");
        out.println("<div class=\"form-group\">");
        out.println("<div class=\"form-row\">");

        //CHAMP NOM DE MAGASIN
        out.println("<div class=\"form-group col-md-8\">");
        out.println("<label for=\"inputNomMagasin\">Nom du magasin</label>");
        out.println("<input type=\"input\" class=\"form-control\" id=\"inputNomMagasin\" name=\"inputNomMagasin\" placeholder=\"Nom du magasin\">");
        out.println("</div>");

        //SELECTION DU GERANT
        out.println("<div class=\"form-group col-md-4\">");
        out.println("<label for=\"inputGerant\">Gérant</label>");
        out.println("<select id=\"inputGerant\" class=\"form-control\" name=\"inputGerant\">");
            //AFFICHAGE DE LA LISTE DE SELECTION DE GERANT
            try {
                //CONNEXION A LA BASE DE DONNEES.
                Class.forName("org.postgresql.Driver");
                bdd c = new bdd();
                Connection conn = DriverManager.getConnection(c.getUrl(), c.getLogin(), c.getPassword());

                //ON RECUPERE LA LISTE
                String sql = "SELECT * "+
                    "FROM gerant;";
                 Statement stat = conn.createStatement();
                ResultSet rs = stat.executeQuery(sql);
                while(rs.next()){
                    out.println("<option value=\""+rs.getInt("id_gerant")+"\">"+rs.getString("nom_gerant")+"</option>");
                }
                stat.close();
            }catch(Exception e){
                out.println(e.getMessage());
            }
        out.println("</select>");
        out.println("</div>");
        out.println("</div>");
        out.println("</div>");


        //INPUT DE L'ADRESSE DU MAGASIN
        out.println("<div class=\"form-row\">");
        out.println("<div class=\"form-group col-md-6\">");
        out.println(" <label for=\"inputAdresse\">Adresse du magasin</label>");
        out.println("<textarea class=\"form-control\" id=\"inputAdresse\" name=\"inputAdresse\" rows=\"4\" placeholder=\"Adresse...\"></textarea>");
        out.println("</div>");

        //INPUT DES REMARQUES SUR LE MAGASIN
        out.println("<div class=\"form-group col-md-6\">");
        out.println("<label for=\"inputRemarque\">Remarques</label>");
        out.println("<textarea class=\"form-control\" id=\"inputRemarque\" name=\"inputRemarque\" rows=\"4\" placeholder=\"Remarques ...\"></textarea>");
        out.println("</div>");
        out.println("</div>");

        //BOUTON SUBMIT
        out.println("<button type=\"submit\" class=\"btn btn-success\" style=\"float: right;\">Créer</button>");

        out.println("</form>");
        out.println("</div>");
        out.println("</div>");
        out.println("</div>");
        out.println("</div>");

        //Traitement de la page des cookies
        String cookies = HtmlEscape.unescapeHtml(include.getContent(fichiersInclude.COOKIES));
        out.println(cookies);

        out.println("</body>");
        out.println("</html>");

        out.close();
    }
}