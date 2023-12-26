package rx;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.zip.GZIPOutputStream;

@WebServlet(urlPatterns="/gzip/download")
public class GZipDownloadServlet extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse response)
            throws IOException {
        OutputStream output = response.getOutputStream();
        try(InputStream inputs = getServletContext().getResourceAsStream("/resources/test.txt")) {
            System.out.println("OutputStream " + (output instanceof GZIPOutputStream));
            output.write(inputs.readAllBytes());
        } catch(Exception e) {
            System.out.println("Exception:" + e);
        }
    }
}
