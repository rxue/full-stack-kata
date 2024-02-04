package rx.ee;

import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import rx.ee.bean.CDISingleton;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(urlPatterns="/countwithcdi")
public class CountWithCDIServlet extends HttpServlet {
    @Inject
    private CDISingleton cdiSingleton;
    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        try(PrintWriter writer = resp.getWriter()) {
            cdiSingleton.increment();
            writer.println("thread id of servlet: " + Thread.currentThread().getId());
        }}
}
