package rx;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;


@WebServlet(urlPatterns="/timeoutSession")
public class TimeoutSessionServlet extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest request , HttpServletResponse response )
            throws IOException, ServletException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        HttpSession session = request.getSession();
        var seconds = 30;
        out.println("session to with ID " + session.getId() + " timeout after " + seconds + " seconds");
        session.setMaxInactiveInterval(seconds);
    }
}
