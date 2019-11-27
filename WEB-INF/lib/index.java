import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.*;

import org.unbescape.html.HtmlEscape;
@WebServlet("/index")
public class index extends HttpServlet {
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
            String index = HtmlEscape.unescapeHtml(include.getContent(FichiersInclude.INDEX));
            out.println(index);

            String cookies = HtmlEscape.unescapeHtml(include.getContent(FichiersInclude.COOKIES));
            out.println(cookies);

            out.println("</body>");
            out.println("</html>");

            out.close();
        }
}