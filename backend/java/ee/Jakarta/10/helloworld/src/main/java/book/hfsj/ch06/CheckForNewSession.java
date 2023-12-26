package book.hfsj.ch06;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.io.PrintWriter;

/**
 *
 */
@WebServlet(urlPatterns="/hfsj/check_for_new_session")
public class CheckForNewSession extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse resp)
            throws ServletException, IOException {
        resp.setContentType("text/html");
        HttpSession session = request.getSession();
        try(PrintWriter writer = resp.getWriter()) {
            if (session.isNew()) {
                writer.println("This is a new session");
                writer.println("<br />");
            } else {
                writer.println("Welcome back");
                writer.println("<br />");
                for(Cookie cookie : request.getCookies()) {
                    writer.println("::::cookie with name " + cookie.getName() + " has max age " + cookie.getMaxAge());
                    writer.println("<br />");
                }
            }
            writer.println("Session has ID " + session.getId() + " max inactive interval is " + session.getMaxInactiveInterval());
        }
    }

}

