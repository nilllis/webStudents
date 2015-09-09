package controllers;

import java.lang.Integer;

import constants.Constants;
import db.DBServices;
import entity.Role;
import entity.User;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Iterator;

/**
 * Created by пк on 20.07.2015.
 */
public class LoginController extends HttpServlet {
    private static final Logger LOG = Logger.getLogger(User.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        DBServices services = new DBServices();
        req.setAttribute("allRoles", services.getAllRoles());
        req.getRequestDispatcher("/WEB-INF/jsp/login.jsp").forward(req, resp);
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        DBServices services = new DBServices();
        req.setAttribute("allRoles", services.getAllRoles());

        if (username.length() == 0 || password.length() == 0) {
            LOG.info("false attempt >> empty field");
            req.setAttribute("errorAttempt", "empty field");
            req.getRequestDispatcher("/WEB-INF/jsp/login.jsp").forward(req, resp);
            return;
        }


        if (!services.userNameIsRegistered(username)) {
            LOG.info("false attempt >> unknown username");
            req.setAttribute("errorAttempt", "unknown username");
            req.getRequestDispatcher("/WEB-INF/jsp/login.jsp").forward(req, resp);
            return;
        }
        Role role = new Role(Integer.parseInt(req.getParameter("role")));

        User registeredUser = services.getUser(username);
        if (!registeredUser.getPass().equals(password)) {
            LOG.info("false attempt for userName \'" + username + "\' >> wrong password");
            req.setAttribute("errorAttempt", "wrong password");
            req.getRequestDispatcher("/WEB-INF/jsp/login.jsp").forward(req, resp);
            return;
        }

        boolean roleRight = false;
        for (Iterator<Role> iterator = registeredUser.getRoles().iterator(); iterator.hasNext(); ) {
            if (iterator.next().getId() == role.getId()) {
                roleRight = true;
                req.getSession().setAttribute(Constants.CURRENT_ROLE_NUMBER, role.getId());
                req.getSession().setAttribute(Constants.CURRENT_ROLE_NAME, role.getRoleLangRu());
                break;
            }
        }
        if (!roleRight) {
            LOG.info("false attempt for userName \'" + username + "\' >> wrong role");
            req.setAttribute("errorAttempt", "wrong role");
            req.getRequestDispatcher("/WEB-INF/jsp/login.jsp").forward(req, resp);
            return;
        }

        resp.sendRedirect("/title");
    }
}
