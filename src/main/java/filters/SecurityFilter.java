package filters;

import constants.Constants;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by пк on 03.08.2015.
 */
public class SecurityFilter implements Filter {
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        String uri = req.getRequestURI();


        if (uri.indexOf("/resources/") != -1) {
            chain.doFilter(req, res);
            System.out.println(">>>>>" + uri); // TODO delete
            return;
        }

        if (uri.endsWith("/login")) {
            chain.doFilter(req, res);
            return;
        }

//        if (req.getSession().getAttribute(Constants.CURRENT_ROLE_NUMBER) == null) {
//            res.sendRedirect("/login");
//            return;
//        }

        chain.doFilter(req, res);
    }

    public void destroy() {

    }
}
