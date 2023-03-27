package book.hfsj.ch13;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
@WebFilter(urlPatterns= {"/gzip/*"})
public class CompressionFilter implements Filter {
    private FilterConfig filterConfig;
    private ServletContext ctx;
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        this.filterConfig = filterConfig;
        ctx = this.filterConfig.getServletContext();
        ctx.log(this.filterConfig.getFilterName() + " initialized.");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        response.setHeader("Content-disposition", "attachment; filename=test.gz");
        CompressionResponseWrapper responseWrapper = new CompressionResponseWrapper(response);
        filterChain.doFilter(servletRequest, responseWrapper);
        responseWrapper.commit();
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}
