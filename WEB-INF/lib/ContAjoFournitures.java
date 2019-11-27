import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.*;
import java.sql.*;
import java.util.ArrayList;

import org.unbescape.html.HtmlEscape;
@WebServlet("/ContAjoFournitures")
public class ContAjoFournitures extends HttpServlet {
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
        out.println("<div class=\"contenu\" id=\"contAjoFournitures\">");
        out.println("<h1>Acheter une ressource</h1>");

        //AFFICHAGE DE L'EVENTUELLE ALERTE
        String alert = HtmlEscape.unescapeHtml(include.getContent(FichiersInclude.ALERT));
        out.println(alert);

        //AFFICHAGE DU FORMULAIRE
        out.println("<div class=\"shadow p-3 mb-5 bg-white rounded\">");
        out.println("<div class=\"card\" >");
        out.println("<div class=\"card-body\">");
        out.println("<form method=\"POST\" action=\"/TraitementAjoutFournitures\">");

        //RECUPERATION DE TOUS LES MAGASINS
        ArrayList<Magasin> listMagasins = new ArrayList<Magasin>();;
        try{
            //CONNEXION A LA BASE DE DONNEES.
            Class.forName("org.postgresql.Driver");
            BDD c = new BDD();
            Connection conn = DriverManager.getConnection(c.getUrl(), c.getLogin(), c.getPassword());

            //ON RECUPERE TOUS LES MAGASINS
            String sql = "SELECT * "+
                    "FROM magasin;";
            Statement stat = conn.createStatement();
            ResultSet rs = stat.executeQuery(sql);
            Magasin tmp;

            while(rs.next()) {
                tmp = new Magasin(rs.getInt("id"),
                        rs.getString("nom_magasin"),
                        rs.getInt("id_gerant"),
                        rs.getString("adresse_magasin"),
                        rs.getString("remarques_magasin"));
                listMagasins.add(tmp);
            }
        }catch(Exception e){
            out.println(e.getMessage());
        }

        //RECUPERATION DE TOUTES LES FOURNITURES
        ArrayList<Fourniture> listFournitures = new ArrayList<Fourniture>();
        try{
            //CONNEXION A LA BASE DE DONNEES.
            Class.forName("org.postgresql.Driver");
            BDD c = new BDD();
            Connection conn = DriverManager.getConnection(c.getUrl(), c.getLogin(), c.getPassword());

            //ON RECUPERE TOUS LES MAGASINS
            String sql = "SELECT * "+
                    "FROM fourniture;";
            Statement stat = conn.createStatement();
            ResultSet rs = stat.executeQuery(sql);
            Fourniture tmp;

            while(rs.next()) {
                tmp = new Fourniture(rs.getInt("id_fourniture"),
                        rs.getString("nom_fourniture"),
                        rs.getInt("prix_unitaire_fourniture"),
                        false);
                listFournitures.add(tmp);
            }
        }catch(Exception e){
            out.println(e.getMessage());
        }

        // AFFICHAGE D'UNE LISTE PAR MAGASIN
        for(Magasin magEnCours: listMagasins){
            //ENTETE A CHAQUE MAGASIN
            //BOUTON CHOIX DU MAGASIN
            out.println("<div class=\"form-row\" id=\"mag"+magEnCours.getId()+"\">");
            out.println("<h1>"+magEnCours.getNom()+"</h1>");
            out.println("<div class=\"dropdown\" style=\"right: 11%;position: absolute;\">");
            out.println("<button class=\"btn btn-info btn-lg dropdown-toggle\" type=\"button\" id=\"dropdownMenuButton\" data-toggle=\"dropdown\" aria-haspopup=\"true\" aria-expanded=\"false\">");
            out.println("Tous les magasins");
            out.println("</button>");
            out.println("<div class=\"dropdown-menu\" aria-labelledby=\"dropdownMenuButton\">");
            //AFFICHAGE DE LA LISTE DE MAGASINS
            for(Magasin mag: listMagasins){
                out.println("<a class=\"dropdown-item\" href=\"#mag"+mag.getId()+"\">"+mag.getNom()+"</a>");
            }
            out.println("</div>");
            out.println("</div>");
            out.println("<button type=\"submit\" class=\"btn btn-lg btn-success\" style=\"right: 1%;position: absolute;\">Acheter</button>");
            out.println("</div>");

            //LISTE POUR CHAQUE MAGASIN
            //en-tete du tableau
            out.println("<table class=\"table table-striped\">");
            out.println("<thead>");
            out.println("<tr>");
            out.println("<th scope=\"col\">Nom</th>");
            out.println("<th scope=\"col\">Quantité en stock</th>");
            out.println("<th scope=\"col\">Quantité à acheter</th>");
            out.println("<th scope=\"col\">Prix Unitaire</th>");
            out.println("</tr>");
            out.println("</thead>");

            //corps du tableau
            try{
                out.println("<tbody>");
                for(Fourniture f: listFournitures){
                    out.println("<tr>");
                    out.println("<th scope=\"row\">"+f.getNom());
                    if(f.getRecent()) out.println("<span class=\"badge badge-secondary\">Nouveau</span>");
                    out.println("</th>");

                    //CONNEXION A LA BASE DE DONNEES.
                    Class.forName("org.postgresql.Driver");
                    BDD c = new BDD();
                    Connection conn = DriverManager.getConnection(c.getUrl(), c.getLogin(), c.getPassword());

                    //ON RECUPERE TOUS LES MAGASINS
                    String sql = "SELECT * "+
                            "FROM magasin_possede_fourniture " +
                            "WHERE id_fourniture = '"+f.getId()+"'" +
                            "AND id_magasin = '"+magEnCours.getId()+"';";
                    Statement stat = conn.createStatement();
                    ResultSet rs = stat.executeQuery(sql);

                    //ON TESTE SI LA REQUETE EST VIDE
                    if (rs.next() == false) {
                        out.println("<td>");
                        out.println("0");
                        out.println("</td>");

                        out.println("<td>");
                        out.println("<input type=\"number\" name=\"f"+magEnCours.getId()+f.getId()+"\" value =\"0\"/>");
                        out.println("</td>");

                        out.println("<td>");
                        out.println(f.getPrix_unitaire());
                        out.println("</td>");
                    }
                    else {
                        do {
                            out.println("<td>");
                            out.println(rs.getInt("quantite_stock"));
                            out.println("</td>");

                            out.println("<td>");
                            out.println("<input type=\"number\" name=\"f"+magEnCours.getId()+f.getId()+"\" value =\"0\"/>");
                            out.println("</td>");

                            out.println("<td>");
                            out.println(f.getPrix_unitaire());
                            out.println("</td>");
                        } while (rs.next());
                    }
                    out.println("</tr>");
                }
            }catch(Exception e){
                out.println(e.getMessage());
            }
            out.println("</tbody>");

            out.println("</table>");

        }

        out.println("</form>");
        out.println("</div>");
        out.println("</div>");
        out.println("</div>");
        out.println("</div>");

        //TRAITEMENT DU COOKIE TERMSAGREED
        String cookies = HtmlEscape.unescapeHtml(include.getContent(FichiersInclude.COOKIES));
        out.println(cookies);

        out.println("</body>");
        out.println("</html>");

        out.close();
    }
}