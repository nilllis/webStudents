package controllers;

import db.DBServices;
import entity.Discipline;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by пк on 06.08.2015.
 */
public class DisciplinesPageController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/setCurrentRoleOfUser").include(req, resp);

        DBServices services = new DBServices();
        req.setAttribute("allDisciplines", services.getAllDisciplines());
        req.setAttribute("pageInsertingIntoTemplate", "/WEB-INF/jsp/disciplines.jsp");
        req.setAttribute("pageTitle", "disciplines");
        req.getRequestDispatcher("/WEB-INF/jsp/templates/pageTpl.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getParameter("create_discipline") != null) {

            req.setAttribute("variantOfJsp", 1);
            req.setAttribute("pageInsertingIntoTemplate", "/WEB-INF/jsp/createOrModifyDiscipline.jsp");
            req.setAttribute("pageTitle", "create new discipline");
            req.getRequestDispatcher("/WEB-INF/jsp/templates/pageTpl.jsp").forward(req, resp);
            return;
        }


        if (req.getParameter("create_new_discipline_action") != null) {
            String disciplineName = req.getParameter("discipline_name");
            if (disciplineName.length() == 0) {
                req.setAttribute("statusOperation", "Поле 'Название' не заполнено");
            } else {
                DBServices services = new DBServices();
                services.addNewDiscipline(req.getParameter("discipline_name"));
                req.setAttribute("statusOperation", "Дисциплина '" + disciplineName + "' добавлена в список дисциплин");
            }
            req.setAttribute("variantOfJsp", 1);
            req.setAttribute("pageInsertingIntoTemplate", "/WEB-INF/jsp/createOrModifyDiscipline.jsp");
            req.setAttribute("pageTitle", "create new discipline");
            req.getRequestDispatcher("/WEB-INF/jsp/templates/pageTpl.jsp").forward(req, resp);
            return;
        }


        if (req.getParameter("modify_discipline") != null) {
            if (req.getParameter("id_discipline") != null) {
                DBServices services = new DBServices();
                int disciplineID = Integer.parseInt(req.getParameter("id_discipline"));
                Discipline disciplineToModify = services.getDisciplineById(disciplineID);

                req.setAttribute("discipline_Id_old", disciplineToModify.getId());
                req.setAttribute("discipline_name_old", disciplineToModify.getName());
                req.setAttribute("variantOfJsp", 2);
                req.setAttribute("pageInsertingIntoTemplate", "/WEB-INF/jsp/createOrModifyDiscipline.jsp");
                req.setAttribute("pageTitle", "modify discipline");
                req.getRequestDispatcher("/WEB-INF/jsp/templates/pageTpl.jsp").forward(req, resp);
                return;
            }
            resp.sendRedirect("/disciplines");
            return;
        }

        if (req.getParameter("modify_discipline_action") != null) {
            if (req.getParameter("discipline_name").length() == 0) {
                req.setAttribute("statusOperation", "Поле 'Название' не заполнено");
                req.setAttribute("discipline_Id_old", req.getParameter("discipline_Id"));
                req.setAttribute("discipline_name_old", req.getParameter("discipline_name"));
                req.setAttribute("variantOfJsp", 2);
                req.setAttribute("pageInsertingIntoTemplate", "/WEB-INF/jsp/createOrModifyDiscipline.jsp");
                req.setAttribute("pageTitle", "modify discipline");
                req.getRequestDispatcher("/WEB-INF/jsp/templates/pageTpl.jsp").forward(req, resp);
                return;
            } else {
                DBServices services = new DBServices();
                Discipline disciplineModifying = new Discipline();
                disciplineModifying.setName(req.getParameter("discipline_name"));
                disciplineModifying.setId(Integer.parseInt(req.getParameter("discipline_Id")));

                services.modifyDiscipline(disciplineModifying);
                resp.sendRedirect("/disciplines");
            }
            return;
        }


        if (req.getParameter("delete_discipline") != null) {
            if (req.getParameter("id_discipline") != null) {
                DBServices services = new DBServices();
                services.deleteDiscipline(Integer.parseInt(req.getParameter("id_discipline")));
            }
            resp.sendRedirect("/disciplines");
            return;
        }
    }
}
