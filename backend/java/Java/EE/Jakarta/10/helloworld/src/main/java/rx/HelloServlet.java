package rx;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(urlPatterns="/hello")
public class HelloServlet extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        PrintWriter writer = resp.getWriter();
        writer.println("Hello World, first servlet welcome u in light of Head First Servlet :)");
        writer.println("My current Servlet instance hash is " + this.hashCode());
        writer.println("My current running thread ID is " + Thread.currentThread().getId() + " and its hashcode is " + Thread.currentThread().hashCode());
        writer.println("remote port is " + req.getRemotePort());
        writer.println("server port is " + req.getServerPort());
        writer.println("local port is " + req.getLocalPort());

    }
}
