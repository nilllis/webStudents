package controllers;

import db.DBServices;
import entity.Semester;
import entity.Student;
import services.LabelsToJsp;
import services.TablesToJsp;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

/**
 * Created by пк on 25.07.2015.
 */
public class StudentsPageController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/setCurrentRoleOfUser").include(req, resp);

        DBServices services = new DBServices();
        ArrayList<Student> allStudents = services.getAllStudents();
        req.setAttribute("allStudentsTable", allStudentsTable(allStudents, Integer.parseInt(req.getAttribute("currentRole").toString())));
        req.setAttribute("pageInsertingIntoTemplate", "/WEB-INF/jsp/students.jsp");
        req.setAttribute("pageTitle", "students");
        req.getRequestDispatcher("/WEB-INF/jsp/templates/pageTpl.jsp").forward(req, resp);
    }

    private String allStudentsTable(ArrayList<Student> allStudents, int currentRole) {
        StringBuffer result = new StringBuffer();
        SimpleDateFormat sdf = new SimpleDateFormat("d/MM/yyyy");
        result.append("<table class=\"table_students_data\" style=\"background:none;\">");
        for (Student student : allStudents) {
            result.append("<tr><td width=\"6%\">");
            result.append("<input type=\"checkbox\" name=\"id_stud\"  value=\"" + student.getId() + "\">");
            result.append("</td>");
            result.append("<td align=\"left\" width=\"35%\">" + student.getLastName() + "</td>");
            result.append("<td align=\"left\" width=\"35%\">" + student.getName() + "</td>");
            result.append("<td align=\"left\" width=\"12%\">" + student.getGroupName() + "</td>");
            result.append("<td align=\"left\" width=\"12%\">" + sdf.format(student.getDate()) + "</td>");
            result.append("</tr>");
        }
        result.append("</table>");
        return result.toString();
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        if (req.getParameter("create_student") != null) {
            req.setAttribute("variantOfJsp", 1);
            req.setAttribute("pageInsertingIntoTemplate", "/WEB-INF/jsp/createOrModifyStudent.jsp");
            req.setAttribute("pageTitle", "create new student");
            req.getRequestDispatcher("/WEB-INF/jsp/templates/pageTpl.jsp").forward(req, resp);
            return;
        }

        if (req.getParameter("create_new_student_action") != null) {
            Student student = new Student();
            req.setAttribute("statusOperation", "");

            student.setName(req.getParameter("newStudent_name"));
            req.setAttribute("newStudent_name_old", req.getParameter("newStudent_name"));

            student.setLastName(req.getParameter("newStudent_lastName"));
            req.setAttribute("newStudent_lastName_old", req.getParameter("newStudent_lastName"));

            student.setGroupName(req.getParameter("newStudent_groupName"));
            req.setAttribute("newStudent_groupName_old", req.getParameter("newStudent_groupName"));

            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            try {
                req.setAttribute("newStudent_date_old", req.getParameter("newStudent_date"));
                student.setDate(sdf.parse(req.getParameter("newStudent_date")).getTime());
                DBServices services = new DBServices();
                services.addNewStudent(student);
            } catch (ParseException e) {
                req.setAttribute("statusOperation", req.getAttribute("statusOperation") + "Неверно введена дата поступления");
                req.setAttribute("variantOfJsp", 1);
                req.setAttribute("pageInsertingIntoTemplate", "/WEB-INF/jsp/createOrModifyStudent.jsp");
                req.setAttribute("pageTitle", "create new student");
                req.getRequestDispatcher("/WEB-INF/jsp/templates/pageTpl.jsp").forward(req, resp);
                return;
            }
            resp.sendRedirect("/students");
            return;
        }


        if (req.getParameter("modify_student") != null) {
            if (req.getParameter("id_stud") != null) {
                DBServices services = new DBServices();
                Student modifyingStudent = services.getStudentById(Integer.parseInt(req.getParameter("id_stud")));

                req.setAttribute("newStudent_id_old", modifyingStudent.getId());
                req.setAttribute("newStudent_name_old", modifyingStudent.getName());
                req.setAttribute("newStudent_lastName_old", modifyingStudent.getLastName());
                req.setAttribute("newStudent_groupName_old", modifyingStudent.getGroupName());
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                req.setAttribute("newStudent_date_old", sdf.format(modifyingStudent.getDate()));

                req.setAttribute("variantOfJsp", 2);
                req.setAttribute("pageInsertingIntoTemplate", "/WEB-INF/jsp/createOrModifyStudent.jsp");
                req.setAttribute("pageTitle", "modify student");
                req.getRequestDispatcher("/WEB-INF/jsp/templates/pageTpl.jsp").forward(req, resp);
                return;
            }
            resp.sendRedirect("/students");
            return;
        }

        if (req.getParameter("modify_student_action") != null) {
            Student modifyingStudent = new Student();
            req.setAttribute("statusOperation", "");

            modifyingStudent.setId(Integer.parseInt(req.getParameter("newStudent_id")));
            req.setAttribute("newStudent_id_old", req.getParameter("newStudent_id"));

            modifyingStudent.setName(req.getParameter("newStudent_name"));
            req.setAttribute("newStudent_name_old", req.getParameter("newStudent_name"));

            modifyingStudent.setLastName(req.getParameter("newStudent_lastName"));
            req.setAttribute("newStudent_lastName_old", req.getParameter("newStudent_lastName"));

            modifyingStudent.setGroupName(req.getParameter("newStudent_groupName"));
            req.setAttribute("newStudent_groupName_old", req.getParameter("newStudent_groupName"));

            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            try {
                req.setAttribute("newStudent_date_old", req.getParameter("newStudent_date"));
                modifyingStudent.setDate(sdf.parse(req.getParameter("newStudent_date")).getTime());
            } catch (ParseException e) {
                req.setAttribute("statusOperation", req.getAttribute("statusOperation") + "Неверно введена дата поступления");
                req.setAttribute("variantOfJsp", 2);
                req.setAttribute("pageInsertingIntoTemplate", "/WEB-INF/jsp/createOrModifyStudent.jsp");
                req.setAttribute("pageTitle", "modify student");
                req.getRequestDispatcher("/WEB-INF/jsp/templates/pageTpl.jsp").forward(req, resp);
                return;
            }

            DBServices services = new DBServices();
            services.modifyStudent(modifyingStudent);
            resp.sendRedirect("/students");
            return;
        }


        if (req.getParameter("delete_student") != null) {
            String idStud[] = req.getParameterValues("id_stud");
            if (idStud != null) {
                DBServices services = new DBServices();
                for (String id : idStud) {
                    services.deleteStudent(Integer.parseInt(id));
                }
            }
            resp.sendRedirect("/students");
            return;
        }


        if (req.getParameter("students_marks") != null) {
            DBServices services = new DBServices();
            Student student;
            try {
                student = services.getStudentById(Integer.parseInt(req.getParameter("id_stud")));
                req.setAttribute("student_id", student.getId());
                req.setAttribute("student_name", student.getName());
                req.setAttribute("student_last_name", student.getLastName());
                req.setAttribute("student_group", student.getGroupName());
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                req.setAttribute("student_date", sdf.format(student.getDate()));
            } catch (Exception e) {
                resp.sendRedirect("/students");
                return;
            }

            req.setAttribute("allMarksInSemesterTable", TablesToJsp.allMarksInSemesterTable(student.getId()));

            ArrayList<Semester> allSemesters = services.getAllSemesters();
            req.setAttribute("allSemestersLabel", LabelsToJsp.allSemestersLabel(allSemesters, 1));

            req.setAttribute("pageInsertingIntoTemplate", "/WEB-INF/jsp/studentsMarks.jsp");
            req.setAttribute("pageTitle", "students marks");
            req.getRequestDispatcher("/WEB-INF/jsp/templates/pageTpl.jsp").forward(req, resp);
            return;
        }
    }


}
