package rx;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;

@WebServlet(urlPatterns="/download")
public class DownloadServlet extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse response)
            throws IOException {
        OutputStream output = response.getOutputStream();
        response.setHeader("Content-disposition", "attachment; filename=test.txt");

        try(InputStream inputs = getServletContext().getResourceAsStream("/resources/test.txt")) {
            output.write(inputs.readAllBytes());
        } catch(Exception e) {
            System.out.println("Exception:" + e);
        }
    }
}
