package services;

import constants.Constants;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by пк on 10.08.2015.
 */
public class SetCurrentRoleOfUser extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int currentRole;
        try {
            currentRole = Integer.parseInt(req.getSession().getAttribute(Constants.CURRENT_ROLE_NUMBER).toString());
        } catch (NullPointerException e) {
            currentRole = 1; //TODO set currentRole = 0
        }
        req.setAttribute("currentRole", currentRole);
    }
}
