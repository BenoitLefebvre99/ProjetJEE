import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.*;
import java.sql.*;
import org.unbescape.html.HtmlEscape;
@WebServlet("/contHelp")
public class contHelp extends HttpServlet {
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
        String help = HtmlEscape.unescapeHtml(include.getContent(fichiersInclude.HELP));
        out.println(help);


        String cookies = HtmlEscape.unescapeHtml(include.getContent(fichiersInclude.COOKIES));
        out.println(cookies);

        out.println("</body>");
        out.println("</html>");

        out.close();
    }
}