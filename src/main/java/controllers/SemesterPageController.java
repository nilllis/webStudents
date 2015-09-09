package controllers;

import db.DBServices;
import entity.Discipline;
import entity.Semester;
import services.LabelsToJsp;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by пк on 07.08.2015.
 */
public class SemesterPageController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/setCurrentRoleOfUser").include(req, resp);

        DBServices services = new DBServices();
        ArrayList<Semester> allSemesters = services.getAllSemesters();
        int currentSemesterId = 0;
        if (allSemesters.size() > 0) {
            currentSemesterId = allSemesters.get(0).getId();
        }
        try {
            if (req.getParameter("semester") != null) {
                currentSemesterId = Integer.parseInt(req.getParameter("semester"));
            }
            boolean existSemester = false;
            for (Semester semester : allSemesters) {
                if (semester.getId() == currentSemesterId) {
                    existSemester = true;
                    req.setAttribute("semesterId", currentSemesterId);
                    req.setAttribute("semesterDuration", semester.getDuration());
                    req.getRequestDispatcher("/createSemesterTable").include(req, resp);
                    break;
                }
            }
            if (!existSemester) {
                req.setAttribute("errorMessage", "Неправильно указан семестр");
            }
        } catch (Exception e) {
            req.setAttribute("errorMessage", "Неправильно указан семестр");
        }


        req.setAttribute("allSemestersLabel", LabelsToJsp.allSemestersLabel(allSemesters, currentSemesterId));
        req.setAttribute("pageInsertingIntoTemplate", "/WEB-INF/jsp/semesters.jsp");
        req.setAttribute("pageTitle", "fucking semesters");
        req.getRequestDispatcher("/WEB-INF/jsp/templates/pageTpl.jsp").forward(req, resp);
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        if (req.getParameter("create_semester") != null) {
            req.setAttribute("variantOfJsp", 1);
            req.setAttribute("allDisciplineLabel", allDisciplineLabel(0));
            req.setAttribute("pageInsertingIntoTemplate", "/WEB-INF/jsp/createOrModifySemester.jsp");
            req.setAttribute("pageTitle", "create new semester");
            req.getRequestDispatcher("/WEB-INF/jsp/templates/pageTpl.jsp").forward(req, resp);
            return;
        }

        if (req.getParameter("create_new_semester_action") != null) {
            if (req.getParameter("semester_duration").length() != 0) {
                try {
                    int semesterDuration = Integer.parseInt(req.getParameter("semester_duration"));
                    String idDiscSemestr[] = req.getParameterValues("disciplines_label");
                    DBServices services = new DBServices();
                    services.addNewSemester(semesterDuration, idDiscSemestr);
                    resp.sendRedirect("/semesters");
                    return;
                } catch (Exception e) {
                    req.setAttribute("semester_duration_old", req.getParameter("semester_duration"));
                    req.setAttribute("statusOperation", "В поле 'Длительность' введено неправильное значение");
                }
            } else {
                req.setAttribute("statusOperation", "Поле 'Длительность' не заполнено");
            }

            req.setAttribute("variantOfJsp", 1);
            req.setAttribute("allDisciplineLabel", allDisciplineLabel(Integer.parseInt(req.getParameter("current_semester"))));
            req.setAttribute("pageInsertingIntoTemplate", "/WEB-INF/jsp/createOrModifySemester.jsp");
            req.setAttribute("pageTitle", "create new semester");
            req.getRequestDispatcher("/WEB-INF/jsp/templates/pageTpl.jsp").forward(req, resp);
        }


        if (req.getParameter("modify_semester") != null) {
            int currentSemesterId = Integer.parseInt(req.getParameter("current_semester"));

            req.setAttribute("semester_id_old", currentSemesterId);
            DBServices services = new DBServices();
            req.setAttribute("semester_duration_old", services.getSemesterById(currentSemesterId).getDuration());
            req.setAttribute("allDisciplineLabel", allDisciplineLabel(currentSemesterId));

            req.setAttribute("variantOfJsp", 2);
            req.setAttribute("pageInsertingIntoTemplate", "/WEB-INF/jsp/createOrModifySemester.jsp");
            req.setAttribute("pageTitle", "create new semester");
            req.getRequestDispatcher("/WEB-INF/jsp/templates/pageTpl.jsp").forward(req, resp);
            return;
        }


        if (req.getParameter("modify_semester_action") != null) {
            Semester semester = new Semester();
            req.setAttribute("statusOperation", "");

            int currentSemesterId = Integer.parseInt(req.getParameter("semester_id"));
            req.setAttribute("semester_id_old", req.getParameter("semester_id"));
            semester.setId(currentSemesterId);

            req.setAttribute("semester_duration_old", req.getParameter("semester_duration"));
            if (req.getParameter("semester_duration").length() == 0) {
                req.setAttribute("statusOperation", "Не заполнено поле 'Длительность'");
            }
            try {
                semester.setDuration(Integer.parseInt(req.getParameter("semester_duration")));
            } catch (Exception e) {
                req.setAttribute("statusOperation", req.getAttribute("statusOperation") + " В поле 'Длительность' должно быть число");
            }

            if (req.getAttribute("statusOperation").toString().length() == 0) {
                String idDiscInSemester[] = req.getParameterValues("disciplines_label");
                DBServices services = new DBServices();
                services.modifySemester(semester, idDiscInSemester);
                resp.sendRedirect("/semesters?semester=" + currentSemesterId);
                return;
            }


            req.setAttribute("allDisciplineLabel", allDisciplineLabel(currentSemesterId));
            req.setAttribute("variantOfJsp", 2);
            req.setAttribute("pageInsertingIntoTemplate", "/WEB-INF/jsp/createOrModifySemester.jsp");
            req.setAttribute("pageTitle", "create new semester");
            req.getRequestDispatcher("/WEB-INF/jsp/templates/pageTpl.jsp").forward(req, resp);
            return;
        }


        if (req.getParameter("delete_semester") != null) {
            DBServices services = new DBServices();
            services.deleteSemester(Integer.parseInt(req.getParameter("current_semester")));
            resp.sendRedirect("/semesters");
            return;
        }

    }

    private String allDisciplineLabel(int semesterId) {
        StringBuffer result = new StringBuffer();
        result.append("<select name=\"disciplines_label\" class=\"disciplines_input_label\" size=\"8\" multiple>");
        DBServices services = new DBServices();
        ArrayList<Discipline> disciplinesAll = services.getAllDisciplines();
        ArrayList<Discipline> disciplinesSelected = services.getDisciplinesInSemester(semesterId);
        for (Discipline discipline : disciplinesAll) {
            result.append("<option ");
            for (Discipline disciplineSelected : disciplinesSelected) {
                if (discipline.getId() == disciplineSelected.getId()) {
                    result.append(" selected ");
                    break;
                }
            }
            result.append("value=\"" + discipline.getId() + "\">" + discipline.getName() + "</option>");
        }
        result.append("</select>");
        return result.toString();
    }
}
