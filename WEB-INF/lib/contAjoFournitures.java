import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.*;
import java.sql.*;
import org.unbescape.html.HtmlEscape;
@WebServlet("/contAjoFournitures")
public class contAjoFournitures extends HttpServlet {
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
        String formAjoFournitures = HtmlEscape.unescapeHtml(include.getFormAjoFournitures());
        out.println(formAjoFournitures);


        String cookies = HtmlEscape.unescapeHtml(include.getCookies());
        out.println(cookies);

        out.println("</body>");
        out.println("</html>");

        out.close();
    }
}