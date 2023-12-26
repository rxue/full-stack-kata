package rx;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

@WebServlet(urlPatterns="/fullStuff/*")
public class DirectoryMatchServlet extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse response)
            throws IOException {
        var out = response.getWriter();
        out.print("This is Servlet, which could match /fullStuff also");
    }
}
