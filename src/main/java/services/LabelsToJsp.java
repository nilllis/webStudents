package services;

import entity.Semester;

import java.util.ArrayList;

/**
 * Created by пк on 16.08.2015.
 */
public class LabelsToJsp {

    public static String allSemestersLabel(ArrayList<Semester> allSemesters, int selectedSemesterId) {
        StringBuffer result = new StringBuffer();
        result.append("<select name=\"semester\"  id=\"semester\" class=\"semester_input\">");
        for (Semester semester : allSemesters) {
            result.append("<option ");
            if (selectedSemesterId == semester.getId()) {
                result.append(" selected ");
            }
            result.append("value = \"" + semester.getId() + "\" >" + semester.getName() + "</option >");
        }
        result.append("</select >");
        return result.toString();
    }
}
