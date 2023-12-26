package book.hfsj.ch06.myowntrial;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.ZonedDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.OptionalInt;

@WebServlet(urlPatterns="/hfsj/myowntrial/add_cookies")
public class AddCookies extends HttpServlet {
    private static final String COOKIE_NAME_WITHOUT_PATH = "cookie_without_path";
    private static final String COOKIE_NAME_WITH_MAX_AGE = "cookie_with_max_age";
    private static final String COOKIE_NAME_WITHOUT_MAX_AGE = "cookie_without_max_age";

    private static Map<String,Boolean> cookieNameToExistence = new HashMap<>();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse resp)
            throws ServletException, IOException {
        resp.setContentType("text/html");
        cookieNameToExistence.put(COOKIE_NAME_WITHOUT_PATH, false);
        cookieNameToExistence.put(COOKIE_NAME_WITH_MAX_AGE, false);
        cookieNameToExistence.put(COOKIE_NAME_WITHOUT_MAX_AGE, false);
        try(PrintWriter writer = resp.getWriter()) {
            if (request.getCookies() != null) {
                for (Cookie cookie : request.getCookies()) {
                    final String cookieName = cookie.getName();
                    if (cookieNameToExistence.containsKey(cookieName)) cookieNameToExistence.put(cookieName, true);
                }
            }
            HttpSession session = request.getSession();
            final Optional<String> path = Optional.of(request.getContextPath());
            addCookieWithoutPath(COOKIE_NAME_WITHOUT_PATH, OptionalInt.of(60*2), resp, writer);
            addCookie(COOKIE_NAME_WITH_MAX_AGE, OptionalInt.of(60*2), path, resp, writer);
            addCookie(COOKIE_NAME_WITHOUT_MAX_AGE, OptionalInt.empty(), path, resp, writer);
        }
    }
    private static void addCookieWithoutPath(String name, OptionalInt maxAgeOpt, HttpServletResponse resp, PrintWriter writer) {
        addCookie(name, maxAgeOpt, Optional.empty(), resp, writer);
    }
    private static void addCookie(String name, OptionalInt maxAgeOpt, Optional<String> pathOpt, HttpServletResponse resp, PrintWriter writer) {
        if (!cookieNameToExistence.get(name)) {
            Cookie customCookie = new Cookie(name, "any");
            if (pathOpt.isPresent())
                customCookie.setPath(pathOpt.get());
            if (maxAgeOpt.isPresent())
                customCookie.setMaxAge(maxAgeOpt.getAsInt());
            writer.println("Added new cookie with max age " + customCookie.getMaxAge() + " at " + ZonedDateTime.now());
            resp.addCookie(customCookie);
        } else {
            writer.println("has cookie with name " + name);
        }
        writer.println("<br />");
    }
}
