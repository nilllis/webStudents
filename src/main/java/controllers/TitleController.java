package controllers;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by пк on 22.07.2015.
 */
public class TitleController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("pageInsertingIntoTemplate", "/WEB-INF/jsp/title.jsp");
        req.setAttribute("pageTitle", "title");
        req.getRequestDispatcher("/WEB-INF/jsp/templates/pageTpl.jsp").forward(req, resp);
    }
}
