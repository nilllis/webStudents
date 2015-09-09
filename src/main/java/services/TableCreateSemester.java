package services;

import db.DBServices;
import entity.Discipline;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by пк on 09.08.2015.
 */
public class TableCreateSemester extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        DBServices services = new DBServices();
        ArrayList<Discipline> disciplinesList = services.getDisciplinesInSemester(Integer.parseInt(req.getAttribute("semesterId").toString()));
        StringBuffer result = new StringBuffer();

        result.append("<table class=\"table_discipline_data\"><tr>");
        result.append("<td align=\"left\" width=\"85%\">Наименование дисциплины</td>");
        result.append("</tr></table>");

        result.append("<table class=\"table_discipline_data\" style=\"background:none;\">");
        for (Discipline discipline : disciplinesList) {
            result.append("<tr>");
            result.append("<td align=\"left\" width=\"85%\">" + discipline.getName() + "</td>");
            result.append("</tr>");
        }
        result.append("</table>");
        req.setAttribute("disciplinesListTable", result.toString());
    }
}
