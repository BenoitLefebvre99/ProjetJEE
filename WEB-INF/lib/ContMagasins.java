import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.*;
import java.sql.*;
import org.unbescape.html.HtmlEscape;
import java.util.ArrayList;
@WebServlet("/ContMagasins")
public class ContMagasins extends HttpServlet {
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
            out.println("<div class=\"contenu\" id=\"contMagasins\">");
            out.println("<h1>Consulter les magasins</h1>");

            try {
                //CONNEXION A LA BASE DE DONNEES.
                Class.forName("org.postgresql.Driver");
                BDD c = new BDD();
                Connection conn = DriverManager.getConnection(c.getUrl(), c.getLogin(), c.getPassword());

                //ON RECUPERE LE NOMBRE DE MAGASINS CONTENUS PAR LA BDD
                ArrayList<CA> listCA = new ArrayList<CA>();
                String sql = "SELECT * "+
                    "FROM magasin;";
                Statement stat = conn.createStatement();
                ResultSet rs = stat.executeQuery(sql);
                while(rs.next()){
                    listCA.add(new CA(rs.getInt("id")));
                }
                
                //ON RECUPERE LE CONTENU DES CARDS DANS LA BDD
                sql = "SELECT * FROM magasin AS m INNER JOIN gerant AS g ON m.id_gerant = g.id_gerant "+
                    "ORDER BY id ASC;";
                stat = conn.createStatement();
                rs = stat.executeQuery(sql);
                String gerant="";

                //ON AFFICHE LES CARDS
                while(rs.next()){
                    out.println("<div id=\"cardstocks\">");
                    out.println("<div class=\"card\" style=\"width: 18rem;\">");
                    out.println("<div class=\"card-body\">");

                    out.println("<h5 class=\"card-title\">"+rs.getString("nom_magasin")+"</h5>");
                    out.println("<h6 class=\"card-subtitle mb-2 text-muted\">"+rs.getString("nom_gerant")+" " +rs.getString("prenom_gerant")+"</h6>");
                    out.println("<p class=\"card-text\">"+rs.getString("adresse_magasin")+"</p>");

                    for(int i=0; i<listCA.size(); i++){
                        if(listCA.get(i).isItMe(rs.getInt("id")))
                            out.println("<p class=\"card-text\">Valeur totale : "+listCA.get(i).getChiffreAffaire()+" euros.</p>");
                    }
                    
                    out.println("<p class=\"card-text\">"+rs.getString("remarques_magasin")+"</p>");

                    out.println("<a href=\"ContGerant.html\" class=\"btn btn-info\">Gérant</a>");
                    out.println("<a href=\"ContAjoFournitures.html\" class=\"btn btn-info\">Stocks</a>");

                    out.println("</div>");
                    out.println("</div>");
                    out.println("</div>");
                }

                stat.close();
            }catch(Exception e){
                out.println(e.getMessage());
            }
            out.println("</div>");
            String cookies = HtmlEscape.unescapeHtml(include.getContent(FichiersInclude.COOKIES));
            out.println(cookies);

            out.println("</body>");
            out.println("</html>");

            out.close();
        }
}