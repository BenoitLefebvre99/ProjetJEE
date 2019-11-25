import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.*;
@WebServlet("/createCookie")
public class createCookie extends HttpServlet {
    public void service(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {

        Cookie miam = new Cookie("TermsAgreed", ":)");
        miam.setMaxAge(60*60*24);
        res.addCookie(miam);

        res.sendRedirect(req.getContextPath()+"/index");
    }
}
