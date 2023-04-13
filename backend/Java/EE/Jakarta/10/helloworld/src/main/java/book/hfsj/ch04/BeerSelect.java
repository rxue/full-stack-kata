package book.hfsj.ch04;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

/**
 * page 118 -
 */
@WebServlet(urlPatterns="/hfsj/beerselect")
public class BeerSelect extends HttpServlet {
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse resp)
            throws ServletException, IOException {
        String colorParam = request.getParameter("color");
        try(var inputStream = request.getInputStream();
                var outputWriter = resp.getWriter()) {
            byte[] allBytes = inputStream.readAllBytes();
            outputWriter.write("length of all bytes from input stream is " + allBytes.length);
            outputWriter.write("color parameter value is " + colorParam);
        }
    }
}
