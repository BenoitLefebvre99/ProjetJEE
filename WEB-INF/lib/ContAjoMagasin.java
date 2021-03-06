import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.*;
import java.sql.*;
import org.unbescape.html.HtmlEscape;
@WebServlet("/ContAjoMagasin")
public class ContAjoMagasin extends HttpServlet {
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
        out.println("<div class=\"contenu\" id=\"contAjoMagasin\">");
        out.println("<h1>Créer un nouveau Magasin</h1>");

        // Alertes à afficher au bon moment
        String alert = HtmlEscape.unescapeHtml(include.getContent(FichiersInclude.ALERT));
        out.println(alert);

        //Il faut créer le champ invisible id
        out.println("<div class=\"shadow p-3 mb-5 bg-white rounded\">");
        out.println("<div class=\"card\" >");
        out.println("<div class=\"card-body\">");

        Magasin m = new Magasin(0, "", 0, "", "");

        //FORMULAIRE
        out.println("<form method=\"POST\" action=\"TraitementAjoutMagasin\">");
        out.println("<div class=\"form-group\">");
        out.println("<div class=\"form-row\">");

            //AFFICHAGE DE LA LISTE DE SELECTION DE GERANT
            try {
                //CONNEXION A LA BASE DE DONNEES.
                Class.forName("org.postgresql.Driver");
                BDD c = new BDD();
                Connection conn = DriverManager.getConnection(c.getUrl(), c.getLogin(), c.getPassword());

                String sql;
                Statement stat;
                ResultSet rs;

                try {

                    sql = "SELECT * FROM magasin WHERE id = '" + req.getParameter("id") + "';";
                    stat = conn.createStatement();
                    rs = stat.executeQuery(sql);

                    while (rs.next()) {
                        m = new Magasin(rs.getInt("id"),
                                rs.getString("nom_magasin"),
                                rs.getInt("id_gerant"),
                                rs.getString("adresse_magasin"),
                                rs.getString("remarques_magasin"));
                    }
                }catch(Exception e){

                }

                //CHAMP NOM DE MAGASIN
                out.println("<div class=\"form-group col-md-8\">");
                out.println("<label for=\"inputNomMagasin\">Nom du Magasin</label>");
                out.println("<input type=\"input\" class=\"form-control\" id=\"inputNomMagasin\" name=\"inputNomMagasin\" placeholder=\"Nom du Magasin\" value=\""+m.getNom()+"\">");
                out.println("</div>");

                //SELECTION DU GERANT
                out.println("<div class=\"form-group col-md-4\">");
                out.println("<label for=\"inputGerant\">Gérant</label>");
                out.println("<select id=\"inputGerant\" class=\"form-control\" name=\"inputGerant\">");

                //ON RECUPERE LA LISTE
                sql = "SELECT * "+
                    "FROM gerant;";
                stat = conn.createStatement();
                rs = stat.executeQuery(sql);

                while(rs.next()){
                    if(rs.getInt("id_gerant") == m.getId_gerant()) out.println("<option value=\""+rs.getInt("id_gerant")+"\" selected>"+rs.getString("nom_gerant")+"</option>");
                    else out.println("<option value=\""+rs.getInt("id_gerant")+"\">"+rs.getString("nom_gerant")+"</option>");
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
        out.println(" <label for=\"inputAdresse\">Adresse du Magasin</label>");
        out.println("<textarea class=\"form-control\" id=\"inputAdresse\" name=\"inputAdresse\" rows=\"4\" placeholder=\"Adresse...\">"+m.getAdresse()+"</textarea>");
        out.println("</div>");

        //INPUT DES REMARQUES SUR LE MAGASIN
        out.println("<div class=\"form-group col-md-6\">");
        out.println("<label for=\"inputRemarque\">Remarques</label>");
        out.println("<textarea class=\"form-control\" id=\"inputRemarque\" name=\"inputRemarque\" rows=\"4\" placeholder=\"Remarques ...\">"+m.getRemarques()+"</textarea>");
        out.println("</div>");
        out.println("</div>");

        out.println("<input type=\"hidden\" value=\""+m.getId()+"\" />");

        //BOUTON SUBMIT
        out.println("<button type=\"submit\" class=\"btn btn-success\" >Créer</button>");

        out.println("</form>");
        out.println("</div>");
        out.println("</div>");
        out.println("</div>");
        out.println("</div>");

        //Traitement de la page des cookies
        String cookies = HtmlEscape.unescapeHtml(include.getContent(FichiersInclude.COOKIES));
        out.println(cookies);

        out.println("</body>");
        out.println("</html>");

        out.close();
    }
}