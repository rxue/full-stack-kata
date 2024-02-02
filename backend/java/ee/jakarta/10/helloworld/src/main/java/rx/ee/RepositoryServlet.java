package rx.ee;

import jakarta.ejb.EJB;
import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import rx.ee.jpaentity.Portfolio;
import rx.ee.repository.SingletonRepository;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(urlPatterns="/repository")
public class RepositoryServlet extends HttpServlet {
    @Inject
    private SingletonRepository repo;
    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        try(PrintWriter writer = resp.getWriter()) {
            Portfolio pf = repo.findPortfolioById(1L);
            writer.println("Found portfolio with name: " + pf.getName());
        }
    }
}
