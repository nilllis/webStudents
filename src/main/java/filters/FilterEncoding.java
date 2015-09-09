package filters;

import org.apache.log4j.Logger;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by пк on 22.07.2015.
 */
public class FilterEncoding implements Filter {
    private static final Logger LOG = Logger.getLogger(FilterEncoding.class);

    public void init(FilterConfig filterConfig) throws ServletException {
        LOG.info("init FilterEncoding");
    }

    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;
        String uri = req.getRequestURI();

        if (uri.indexOf("/resources/") != -1) {
            filterChain.doFilter(req, resp);
            return;
        }

        req.setCharacterEncoding("UTF-8");
        filterChain.doFilter(req, resp);
    }

    public void destroy() {

    }
}
