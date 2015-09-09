package db;

import constants.Constants;
import entity.*;
import org.apache.log4j.Logger;

import java.sql.*;
import java.sql.Date;
import java.util.*;

/**
 * Created by пк on 21.07.2015.
 */
public class DBConnection {

    private static final Logger LOG = Logger.getLogger(DBConnection.class);
    private Connection connection = null;
    private ResultSet resultSet = null;
    private static int num = 0;

    private static PreparedStatement defaultPreparedStatement;
    private static PreparedStatement getRolesAll;
    private static PreparedStatement getRoleById;
    private static PreparedStatement getRoleByName;
    private static PreparedStatement userNameIsRegistered;
    private static PreparedStatement getUser;
    private static PreparedStatement getUserRoles;
    private static PreparedStatement getAllStudents;
    private static PreparedStatement addNewStudent;
    private static PreparedStatement getStudentById;
    private static PreparedStatement modifyStudent;
    private static PreparedStatement getMarksByStudent;
    private static PreparedStatement deleteStudent;
    private static PreparedStatement getAllDisciplines;
    private static PreparedStatement getDisciplineById;
    private static PreparedStatement addNewDiscipline;
    private static PreparedStatement modifyDiscipline;
    private static PreparedStatement deleteDiscipline;
    private static PreparedStatement getAllSemesters;
    private static PreparedStatement getSemesterById;
    private static PreparedStatement getDisciplinesInSemester;
    private static PreparedStatement getAllDisciplinesInSemester;
    private static PreparedStatement addNewSemester;
    private static PreparedStatement addNewSemesterInsertDisciplines;
    private static PreparedStatement deleteSemester;
    private static PreparedStatement modifySemester;
    private static PreparedStatement modifySemesterDeleteOldDiscipline;
    private static PreparedStatement modifySemesterUpdateOldDiscipline;


    public DBConnection() {
        try {
            num = ++num;
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            connection = DriverManager.getConnection(Constants.CONNECTING_URL);
            loadPreparedStatements();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void loadPreparedStatements() {
        try {
            if (defaultPreparedStatement == null) {
                LOG.info("loading PrepareStatements...");
                defaultPreparedStatement = connection.prepareStatement("SHOW TABLES FROM easy_it;");
                getRolesAll = connection.prepareStatement("SELECT * FROM roles");
                getRoleById = connection.prepareStatement("SELECT * FROM roles WHERE id=?");
                getRoleByName = connection.prepareStatement("SELECT * FROM roles WHERE role=?");
                getUser = connection.prepareStatement("SELECT * FROM users WHERE login COLLATE 'utf8_bin' = ?");
                getUserRoles = connection.prepareStatement("SELECT tr.id_role, t.role FROM users_roles tr JOIN roles t ON(tr.id_role = t.id) WHERE id_user = ?");
                userNameIsRegistered = connection.prepareStatement("SELECT login FROM users WHERE login COLLATE 'utf8_bin' = ?");
                getAllStudents = connection.prepareStatement("SELECT * FROM students WHERE active = 1 ORDER BY id DESC");
                addNewStudent = connection.prepareStatement("INSERT INTO students (name, last_name, group_name, date)" +
                        "VALUES (?, ?, ?, ?)");
                getStudentById = connection.prepareStatement("SELECT * FROM students WHERE id = ?");
                modifyStudent = connection.prepareStatement("UPDATE students SET name = ?, last_name = ?, group_name = ?," +
                        "date = ? WHERE id = ?");
                getMarksByStudent = connection.prepareStatement("SELECT b.id_discipline, c.name, b.id_semester, a.mark  " +
                        "FROM marks a " +
                        "JOIN easy_it.disciplines_semesters b ON a.id_discipline_semester = b.id " +
                        "JOIN easy_it.disciplines c ON b.id_discipline = c.id " +
                        "WHERE a.id_student = ?;");
                deleteStudent = connection.prepareStatement("UPDATE students SET active = 0 WHERE id = ?");
                getAllDisciplines = connection.prepareStatement("SELECT * FROM disciplines WHERE active = 1 ORDER BY id DESC");
                getDisciplineById = connection.prepareStatement("SELECT * FROM disciplines WHERE id = ?");
                addNewDiscipline = connection.prepareStatement("INSERT INTO disciplines (name) VALUES (?)");
                modifyDiscipline = connection.prepareStatement("UPDATE disciplines SET name = ? WHERE id = ?");
                deleteDiscipline = connection.prepareStatement("UPDATE disciplines SET active = 0 WHERE id = ?");
                getAllSemesters = connection.prepareStatement("SELECT * FROM semesters WHERE active = 1");
                getSemesterById = connection.prepareStatement("SELECT * FROM semesters WHERE id = ?");
                getDisciplinesInSemester = connection.prepareStatement("SELECT a.id, a.id_discipline, b.name FROM disciplines_semesters a " +
                        " JOIN disciplines b ON a.id_discipline = b.id WHERE a.id_semester = ? AND a.active = 1");
                getAllDisciplinesInSemester = connection.prepareStatement("SELECT a.id, a.id_discipline, b.name FROM disciplines_semesters a " +
                        " JOIN disciplines b ON a.id_discipline = b.id WHERE a.id_semester = ?");
                addNewSemester = connection.prepareStatement("INSERT INTO semesters (duration, name) VALUES (?, ?)");
                addNewSemesterInsertDisciplines = connection.prepareStatement("INSERT INTO disciplines_semesters " +
                        " (id_discipline, id_semester) VALUES (?, ?)");
                deleteSemester = connection.prepareStatement("UPDATE semesters SET active = 0 WHERE id = ?");
                modifySemester = connection.prepareStatement("UPDATE semesters SET duration = ? WHERE id = ?");
                modifySemesterDeleteOldDiscipline = connection.prepareStatement("UPDATE disciplines_semesters " +
                        " SET active = 0 WHERE id_semester = ?");
                modifySemesterUpdateOldDiscipline = connection.prepareStatement("UPDATE disciplines_semesters " +
                        " SET active = 1 WHERE id_discipline = ? AND id_semester = ?");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public int getNumOfThisDBConnection() {
        return num;
    }

    public ArrayList<Role> getAllRoles() {
        resultSet = null;
        ArrayList<Role> roles = null;
        try {
            resultSet = getRolesAll.executeQuery();
            roles = new ArrayList<Role>();
            while (resultSet.next()) {
                Role role = new Role();
                role.setId(resultSet.getInt("id"));
                role.setRole(resultSet.getString("role"));
                role.setRoleLangRu(resultSet.getString("role_lang_RU"));
                roles.add(role);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return roles;
    }

    public Role getRole(int id) {
        resultSet = null;
        Role result = null;
        try {
            getRoleById.setInt(1, id);
            resultSet = getRoleById.executeQuery();
            while (resultSet.next()) {
                result = new Role();
                result.setId(id);
                result.setRole(resultSet.getString("role"));
                result.setRoleLangRu(resultSet.getString("role_lang_RU"));
                break;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }


    /**
     * If nameRole not found in DataBase return null;
     */
    public Role getRole(String nameRole) {
        resultSet = null;
        Role result = null;
        try {
            getRoleByName.setString(1, nameRole);
            resultSet = getRoleByName.executeQuery();
            while (resultSet.next()) {
                result = new Role();
                result.setId(resultSet.getInt("id"));
                result.setRole(nameRole);
                result.setRoleLangRu(resultSet.getString("role_lang_RU"));
                break;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }


    public User getUser(String nameUser) {
        resultSet = null;
        User user = new User();
        try {
            getUser.setString(1, nameUser);
            resultSet = getUser.executeQuery();
            while (resultSet.next()) {
                user.setLogin(resultSet.getString("login"));
                user.setId(resultSet.getInt("id"));
                user.setPass(resultSet.getString("pass"));
            }
            if (user.getLogin() != null) {
                getUserRoles.setInt(1, user.getId());
                resultSet = getUserRoles.executeQuery();
                while (resultSet.next()) {
                    Role role = new Role(resultSet.getString("role"));
                    user.addRole(role);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    public boolean userNameIsRegistered(String userName) {
        resultSet = null;
        try {
            userNameIsRegistered.setString(1, userName);
            resultSet = userNameIsRegistered.executeQuery();
            while (resultSet.next()) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public ArrayList<Student> getAllStudents() {
        resultSet = null;
        ArrayList<Student> result = new ArrayList<Student>();
        try {
            resultSet = getAllStudents.executeQuery();
            while (resultSet.next()) {
                Student newStudent = new Student();
                newStudent.setId(resultSet.getInt("id"));
                newStudent.setName(resultSet.getString("name"));
                newStudent.setLastName(resultSet.getString("last_name"));
                newStudent.setGroupName(resultSet.getString("group_name"));
                newStudent.setDate(resultSet.getDate("date").getTime());
                result.add(newStudent);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * function also set numberID for newStudent, which was added into database
     */
    public boolean addNewStudent(Student newStudent) {
        if (newStudent.getName() == null || newStudent.getLastName() == null || newStudent.getGroupName() == null) {
            return false;
        }
        try {
            addNewStudent.setString(1, newStudent.getName());
            addNewStudent.setString(2, newStudent.getLastName());
            addNewStudent.setString(3, newStudent.getGroupName());
            addNewStudent.setDate(4, new Date(newStudent.getDate()));
            if (addNewStudent.executeUpdate() == 0) {
                return false;
            }
            resultSet = connection.createStatement().executeQuery("SELECT LAST_INSERT_ID()");
            while (resultSet.next()) {
                newStudent.setId(resultSet.getInt(1));
                break;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }


    public Student getStudentById(int studentId) {
        resultSet = null;
        Student result = null;
        if (studentId <= 0) {
            return result;
        }
        try {
            getStudentById.setInt(1, studentId);
            resultSet = getStudentById.executeQuery();
            while (resultSet.next()) {
                result = new Student();
                result.setId(resultSet.getInt("id"));
                result.setName(resultSet.getString("name"));
                result.setLastName(resultSet.getString("last_name"));
                result.setGroupName(resultSet.getString("group_name"));
                result.setDate(resultSet.getDate("date").getTime());
                break;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public boolean modifyStudent(Student modifyingStudent) {
        if (modifyingStudent == null || modifyingStudent.getId() <= 0) {
            return false;
        }
        try {
            modifyStudent.setString(1, modifyingStudent.getName());
            modifyStudent.setString(2, modifyingStudent.getLastName());
            modifyStudent.setString(3, modifyingStudent.getGroupName());
            modifyStudent.setDate(4, new Date(modifyingStudent.getDate()));
            modifyStudent.setInt(5, modifyingStudent.getId());
            if (modifyStudent.executeUpdate() == 0) {
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }


    public ArrayList<Mark> getMarksByStudent(int id_student) {
        resultSet = null;
        ArrayList<Mark> result = null;
        try {
            getMarksByStudent.setInt(1, id_student);
            resultSet = getMarksByStudent.executeQuery();
            result = new ArrayList<Mark>();
            while (resultSet.next()) {
                Mark mark = new Mark();
                mark.setIdStudent(id_student);
                mark.setIdDiscipline(resultSet.getInt("id_discipline"));
                mark.setNameDiscipline(resultSet.getString("name"));
                mark.setIdSemester(resultSet.getInt("id_semester"));
                mark.setValue(resultSet.getInt("mark"));
                result.add(mark);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public void deleteStudent(int studentId) {
        if (studentId <= 0) {
            return;
        }
        try {
            deleteStudent.setInt(1, studentId);
            deleteStudent.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public ArrayList<Discipline> getAllDisciplines() {
        resultSet = null;
        ArrayList<Discipline> result = new ArrayList<Discipline>();
        try {
            resultSet = getAllDisciplines.executeQuery();
            while (resultSet.next()) {
                Discipline discipline = new Discipline();
                discipline.setId(resultSet.getInt("id"));
                discipline.setName(resultSet.getString("name"));
                result.add(discipline);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public Discipline getDisciplineById(int disciplineID) {
        resultSet = null;
        Discipline result = null;
        if (disciplineID <= 0) {
            return result;
        }
        try {
            getDisciplineById.setInt(1, disciplineID);
            resultSet = getDisciplineById.executeQuery();
            while (resultSet.next()) {
                result = new Discipline();
                result.setId(disciplineID);
                result.setName(resultSet.getString("name"));
                break;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public boolean addNewDiscipline(String disciplineName) {
        if (disciplineName.length() == 0) {
            return false;
        }
        try {
            addNewDiscipline.setString(1, disciplineName);
            if (addNewDiscipline.executeUpdate() == 0) {
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }

    public boolean modifyDiscipline(Discipline modifyingDiscipline) {
        if (modifyingDiscipline == null || modifyingDiscipline.getId() <= 0) {
            return false;
        }
        try {
            modifyDiscipline.setString(1, modifyingDiscipline.getName());
            modifyDiscipline.setInt(2, modifyingDiscipline.getId());
            if (modifyDiscipline.executeUpdate() == 0) {
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }

    public void deleteDiscipline(int disciplineID) {
        if (disciplineID <= 0) {
            return;
        }
        try {
            deleteDiscipline.setInt(1, disciplineID);
            deleteDiscipline.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Semester> getAllSemesters() {
        resultSet = null;
        ArrayList<Semester> result = null;
        try {
            resultSet = getAllSemesters.executeQuery();
            result = new ArrayList<Semester>();
            while (resultSet.next()) {
                Semester semester = new Semester();
                semester.setId(resultSet.getInt("id"));
                semester.setDuration(resultSet.getInt("duration"));
                semester.setName(resultSet.getString("name"));
                result.add(semester);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public Semester getSemesterById(int semesterId) {
        resultSet = null;
        Semester result = null;
        try {
            getSemesterById.setInt(1, semesterId);
            resultSet = getSemesterById.executeQuery();
            while (resultSet.next()) {
                result = new Semester();
                result.setId(semesterId);
                result.setDuration(resultSet.getInt("duration"));
                result.setName(resultSet.getString("name"));
                break;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }


    public ArrayList<Discipline> getDisciplinesInSemester(int semesterId) {
        resultSet = null;
        ArrayList<Discipline> result = null;
        try {
            getDisciplinesInSemester.setInt(1, semesterId);
            resultSet = getDisciplinesInSemester.executeQuery();
            result = new ArrayList<Discipline>();
            while (resultSet.next()) {
                Discipline discipline = new Discipline();
                discipline.setId(resultSet.getInt("id_discipline"));
                discipline.setName(resultSet.getString("name"));
                result.add(discipline);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public ArrayList<Discipline> getAllDisciplinesInSemester(int semesterId) {
        resultSet = null;
        ArrayList<Discipline> result = null;
        try {
            getAllDisciplinesInSemester.setInt(1, semesterId);
            resultSet = getAllDisciplinesInSemester.executeQuery();
            result = new ArrayList<Discipline>();
            while (resultSet.next()) {
                Discipline discipline = new Discipline();
                discipline.setId(resultSet.getInt("id_discipline"));
                discipline.setName(resultSet.getString("name"));
                result.add(discipline);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }


    public boolean addNewSemester(int semesterDuration, String[] idDiscplInSemester) {
        if (semesterDuration < 0) {
            return false;
        }
        int insertedSemesterId = 0;
        try {
            addNewSemester.setInt(1, semesterDuration);
            addNewSemester.setString(2, "tempName");
            if (addNewSemester.executeUpdate() == 0) {
                return false;
            }
            resultSet = connection.createStatement().executeQuery("SELECT LAST_INSERT_ID()");
            while (resultSet.next()) {
                insertedSemesterId = resultSet.getInt(1);
                break;
            }
            PreparedStatement updateName = connection.prepareStatement("UPDATE semesters SET name = ? WHERE id = ?");
            updateName.setString(1, "Семестр " + insertedSemesterId);
            updateName.setInt(2, insertedSemesterId);
            updateName.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        if (idDiscplInSemester != null && idDiscplInSemester.length > 0 && insertedSemesterId > 0) {
            try {
                for (String idDiscpl : idDiscplInSemester) {
                    addNewSemesterInsertDisciplines.setInt(1, Integer.parseInt(idDiscpl));
                    addNewSemesterInsertDisciplines.setInt(2, insertedSemesterId);
                    if (addNewSemesterInsertDisciplines.executeUpdate() == 0) {
                        return false;
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return true;
    }

    public void deleteSemester(int idSemester) {
        if (idSemester <= 0) {
            return;
        }
        try {
            deleteSemester.setInt(1, idSemester);
            deleteSemester.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void modifySemester(Semester semester, String newDisciplines[]) {
        if (semester == null || semester.getId() <= 0) {
            return;
        }
        try {
            modifySemester.setInt(1, semester.getDuration());
            modifySemester.setInt(2, semester.getId());
            if (modifySemester.executeUpdate() == 0) {
                return;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        /** modifying disciplines to current semester */
        try {
            modifySemesterDeleteOldDiscipline.setInt(1, semester.getId());
            modifySemesterDeleteOldDiscipline.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        ArrayList<Discipline> allOldDisciplines = getAllDisciplinesInSemester(semester.getId());
        if (newDisciplines == null || newDisciplines.length == 0) {
            return;
        }
        for (String newDiscipline : newDisciplines) {
            boolean disciplineUpdated = false;
            if (allOldDisciplines != null) {
                for (Discipline discipline : allOldDisciplines) {
                    if (Integer.parseInt(newDiscipline) == discipline.getId()) {
                        try {
                            modifySemesterUpdateOldDiscipline.setInt(1, discipline.getId());
                            modifySemesterUpdateOldDiscipline.setInt(2, semester.getId());
                            modifySemesterUpdateOldDiscipline.executeUpdate();
                            disciplineUpdated = true;
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                        break;
                    }
                }
            }
            if (!disciplineUpdated) {
                try {
                    addNewSemesterInsertDisciplines.setInt(1, Integer.parseInt(newDiscipline));
                    addNewSemesterInsertDisciplines.setInt(2, semester.getId());
                    addNewSemesterInsertDisciplines.executeUpdate();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    public void restoreAllData() {
        try {
            connection.createStatement().executeUpdate("UPDATE students SET active = 1 WHERE id > 0");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
