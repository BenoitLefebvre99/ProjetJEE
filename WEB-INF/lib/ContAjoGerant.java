import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.*;
import java.sql.*;
import org.unbescape.html.HtmlEscape;
@WebServlet("/ContAjoGerant")
public class ContAjoGerant extends HttpServlet {
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

        //PARTIE SPECIFIQUE A LA PAGE COURANTE
        out.println("<div class=\"contenu\" id=\"contAjoGerant\">");
        out.println("<h1>Ajouter un nouveau gérant</h1>");

        //AFFICHAGE DES EVENTUELLES ALERTES
        String alert = HtmlEscape.unescapeHtml(include.getContent(FichiersInclude.ALERT));
        out.println(alert);

        //AFFICHAGE DU FORMULAIRE
        out.println("<div class=\"shadow p-3 mb-5 bg-white rounded\">");
        out.println("<div class=\"card\" >");
        out.println("<div class=\"card-body\">");

        Gerant g = new Gerant(0,"","",0,"");

        out.println("<form method=\"POST\" action=\"TraitementAjoutGerant\">");
        out.println("<div class=\"form-group\">");
        out.println("<div class=\"form-row\">");

        try {
            //CONNEXION A LA BASE DE DONNEES.
            Class.forName("org.postgresql.Driver");
            BDD c = new BDD();
            Connection conn = DriverManager.getConnection(c.getUrl(), c.getLogin(), c.getPassword());

            String sql;
            Statement stat;
            ResultSet rs;

            try {

                sql = "SELECT * FROM gerant WHERE id_gerant = " + req.getParameter("id") + " ;";
                stat = conn.createStatement();
                rs = stat.executeQuery(sql);

                while (rs.next()) {
                    g = new Gerant(rs.getInt("id_gerant"),
                            rs.getString("nom_gerant"),
                            rs.getString("prenom_gerant"),
                            rs.getInt("id_statut"),
                            rs.getString("remarques_gerant"));
                }
            }catch(Exception e){
                out.println("erreur de récupération");
            }

        //INPUT NOM GERANT
        out.println("<div class=\"form-group col-md-4\">");
        out.println("<label for=\"inputNomGerant\">Nom</label>");
        out.println("<input type=\"input\" class=\"form-control\" id=\"inputNomGerant\" name=\"inputNomGerant\" placeholder=\"Nom\" value=\""+g.getNom()+"\">");
        out.println("</div>");

        //INPUT PRENOM GERANT
        out.println("<div class=\"form-group col-md-4\">");
        out.println("<label for=\"inputPrenomGerant\">Prénom</label>");
        out.println("<input type=\"input\" class=\"form-control\" id=\"inputPrenomGerant\" name=\"inputPrenomGerant\" placeholder=\"Prénom\" value=\""+g.getPrenom()+"\">");
        out.println("</div>");

        //INPUT DE SELECTION DU STATUT
        out.println("<div class=\"form-group col-md-4\">");
        out.println("<label for=\"inputStatut\">Statut</label>");
        out.println("<select id=\"inputStatut\" name=\"inputStatut\" class=\"form-control\" >");
            //RECUPERATION DE LA LISTE DES STATUTS
            try {

                //ON RECUPERE LA LISTE
                sql = "SELECT * "+
                        "FROM statut;";
                stat = conn.createStatement();
                rs = stat.executeQuery(sql);
                while(rs.next()){
                    if(rs.getInt("id_statut") == g.getId_statut()) out.println("<option value=\""+rs.getInt("id_statut")+"\" selected>"+rs.getString("nom_statut")+"</option>");
                    else out.println("<option value=\""+rs.getInt("id_statut")+"\">"+rs.getString("nom_statut")+"</option>");                }
                stat.close();
            }catch(Exception e){
                out.println(e.getMessage());
            }
        out.println("</select>");
        out.println("</div>");
        out.println("</div>");
        out.println("</div>");

        //INPUT DES REMARQUES SUR LE GERANT
        out.println("<div class=\"form-group\">");
        out.println("<label for=\"inputRemarque\">Remarques</label>");
        out.println("<textarea class=\"form-control\" id=\"inputRemarque\" name=\"inputRemarque\" rows=\"5\" placeholder=\"Remarques...\">"+g.getRemarques()+"</textarea>");
        out.println("</div>");

        //BOUTON SUBMIT
        out.println(" <button type=\"submit\" class=\"btn btn-success\" style=\"float: right;\">Créer</button>");

        }catch(Exception e){
            out.println(e.getMessage());
        }

        out.println("</form>");
        out.println("</div>");
        out.println("</div>");
        out.println("</div>");
        out.println("</div>");

        String cookies = HtmlEscape.unescapeHtml(include.getContent(FichiersInclude.COOKIES));
        out.println(cookies);

        out.println("</body>");
        out.println("</html>");

        out.close();
    }
}