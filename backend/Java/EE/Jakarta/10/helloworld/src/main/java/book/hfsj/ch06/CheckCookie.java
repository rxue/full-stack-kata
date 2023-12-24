package book.hfsj.ch06;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

/**
 *
 */
@WebServlet(urlPatterns="/hfsj/check_cookie")
public class CheckCookie extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse resp)
            throws ServletException, IOException {
        resp.setContentType("text/html");
        try(PrintWriter writer = resp.getWriter()) {
            Cookie[] cookies = request.getCookies();
            if (cookies != null) {
                for (Cookie cookie : cookies) {
                    System.out.println("cookie name " + cookie.getName());
                    writer.println("Current cookie with name " + cookie.getName() + " and value " + cookie.getValue());
                    writer.println("<br />");
                }
            } else writer.println("No cookie");
        }
    }

}
