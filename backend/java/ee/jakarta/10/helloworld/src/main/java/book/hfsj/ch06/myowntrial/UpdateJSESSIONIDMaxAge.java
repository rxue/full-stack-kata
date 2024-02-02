package book.hfsj.ch06.myowntrial;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(urlPatterns="/hfsj/trial/update_jsessionid_max_age")
public class UpdateJSESSIONIDMaxAge extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse resp)
            throws ServletException, IOException {
        resp.setContentType("text/html");
        try(PrintWriter writer = resp.getWriter()) {
            Cookie[] cookies = request.getCookies();
            if (cookies != null) {
                for (Cookie cookie : cookies) {
                    if ("JSESSIONID".equals(cookie.getName())) {
                        writer.println("Trying to update max age of JSESSIONID cookie");
                        writer.println("<br />");
                        cookie.setMaxAge(60*5);
                        resp.addCookie(cookie);
                        writer.println("Max age of JSESSIONID cookie updated to " + cookie.getMaxAge());
                    }
                }
            } else writer.println("No cookie");
        }
    }
}
