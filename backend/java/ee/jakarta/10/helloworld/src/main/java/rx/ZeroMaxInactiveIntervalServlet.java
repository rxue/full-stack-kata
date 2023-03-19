package rx;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;

/**
 * This is an Errata of Head First Servlet and JSP 2nd edition:
 *  Chapter 6: Conversational state: session management page 249
 *  Based on the Java EE API document, an interval value of zero or less indicates that the session should never timeout.
 *  =>
 *  No IllegalStateException will be thrown
 */
@WebServlet(urlPatterns="/zeroMaxInactiveInterval")
public class ZeroMaxInactiveIntervalServlet extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest request , HttpServletResponse response )
            throws IOException, ServletException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        HttpSession session = request.getSession();
        session.setAttribute( "foo" , "42" );
        session.setMaxInactiveInterval(0);
        String foo = (String)session.getAttribute("foo");

        if (session.isNew()) {
            System.out.println("This is a new session.");
        } else {
            System.out.println("Welcome back!");
        }
        out.print("Foo: " + foo);

    }
}
