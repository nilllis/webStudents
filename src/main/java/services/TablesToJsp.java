package services;

import db.DBServices;
import entity.Mark;

import java.util.ArrayList;

/**
 * Created by пк on 16.08.2015.
 */
public class TablesToJsp {

    public static String allMarksInSemesterTable(int idStudent) {
        StringBuffer result = new StringBuffer();
        if (idStudent <= 0) {
            return result.toString();
        }
        DBServices services = new DBServices();
        ArrayList<Mark> marks = services.getMarksByStudent(idStudent);

        result.append("<table class=\"table_student_marks\">");
        result.append("<tr style=\"background:#CCC;\">");
        result.append("<td align=\"left\" width=\"75%\">Дисциплина</td>");
        result.append("<td align=\"left\">Оценка</td>");
        result.append("</tr>");

        for (Mark mark : marks) {
            result.append("<tr class=\"row\" id=\"" + mark.getIdSemester() + "\">");
            result.append("<td align=\"left\" width=\"75%\">" + mark.getNameDiscipline() + "</td>");
            result.append("<td align=\"left\">" + mark.getValue() + "</td>");
        }
        result.append("</tr></table>");

        return result.toString();
    }

}