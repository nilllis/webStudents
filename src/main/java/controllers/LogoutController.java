package controllers;

import constants.Constants;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by пк on 10.08.2015.
 */
public class LogoutController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getSession().setAttribute(Constants.CURRENT_ROLE_NUMBER, null);
        req.getSession().setAttribute(Constants.CURRENT_ROLE_NAME, null);

        resp.sendRedirect("/login");
    }
}
