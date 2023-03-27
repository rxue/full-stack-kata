package rx;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.io.PrintWriter;


@WebServlet(urlPatterns="/cookiemaxage")
public class CookieMaxAgeTestServlet extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest request , HttpServletResponse response )
            throws IOException, ServletException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        Cookie[] allCookies = request.getCookies();
        for (Cookie c : allCookies) {
            System.out.println("current cookie: " + c.getName() + " with max age " + c.getMaxAge());
        }
        out.println("Cookie test on setMaxAge");
    }
}
