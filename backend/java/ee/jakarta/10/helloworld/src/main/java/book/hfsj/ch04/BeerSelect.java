package book.hfsj.ch04;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

/**
 * page 118 -
 */
@WebServlet(urlPatterns="/hfsj/beerselect")
public class BeerSelect extends HttpServlet {
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse resp)
            throws IOException {
        String colorParam = request.getParameter("color");
        try(var inputReader = request.getReader();
                var outputWriter = resp.getWriter()) {
            inputReader.lines().forEach(l -> outputWriter.write("line content from input stream: " + l + "\n"));
            outputWriter.write("\n query string is " + request.getQueryString() + "\n\n");
            printAllHeaders(request, outputWriter);
            outputWriter.write("\n");
            printAllParameters(request, outputWriter);
        }
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse resp)
            throws IOException {
        String colorParam = request.getParameter("color");
        try(var inputStream = request.getInputStream();
            var outputWriter = resp.getWriter()) {
            outputWriter.write("With GET input stream length is " + inputStream.readAllBytes().length + "\n");
            outputWriter.write("\n query string is " + request.getQueryString() + "\n\n");
            printAllHeaders(request, outputWriter);
            printAllParameters(request, outputWriter);
        }
    }
    private static void printAllHeaders(HttpServletRequest request, PrintWriter writer) {
        request.getHeaderNames().asIterator().forEachRemaining(headerName -> writer.write("header " + headerName + " has value " + request.getHeader(headerName) + "::::\n"));
    }
    private static void printAllParameters(HttpServletRequest request, PrintWriter writer) {
        request.getParameterNames().asIterator().forEachRemaining(param -> writer.write("Parameter " + param + " has value " + request.getParameter(param) + "\n"));
    }
}
