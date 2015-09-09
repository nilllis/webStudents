package db;

import constants.Constants;
import entity.*;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by пк on 21.07.2015.
 */
public class DBServices {
    private static final Logger LOG = Logger.getLogger(DBServices.class);
    private static List<DBConnection> connectionPool = new ArrayList<DBConnection>();

    public static int getConnectionPool() {
        return connectionPool.size();
    }

    public void init() {
        LOG.info("start initialization connections");
        try {
            for (int i = 0; i < Constants.CONNECTING_POOL_SIZE; i++) {
                createConnection();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void createConnection() {
        DBConnection connection = new DBConnection();
        connectionPool.add(connection);
        LOG.info("created new DBConnection-" + connection.getNumOfThisDBConnection());
    }

    public DBConnection getConnection() {
        if (connectionPool.isEmpty()) {
            createConnection();
        }
        return connectionPool.remove(0);
    }

    public void putConnection(DBConnection connection) {
        connectionPool.add(connection);
    }


    public ArrayList<Role> getAllRoles() {
        DBConnection connection = getConnection();
        ArrayList<Role> result = connection.getAllRoles();
        putConnection(connection);
        return result;
    }

    public Role getRole(int idRole) {
        DBConnection connection = getConnection();
        Role result = connection.getRole(idRole);
        putConnection(connection);
        return result;
    }

    public Role getRole(String nameRole) {
        DBConnection connection = getConnection();
        Role result = connection.getRole(nameRole);
        putConnection(connection);
        return result;
    }

    public User getUser(String userName) {
        DBConnection connection = getConnection();
        User result = connection.getUser(userName);
        putConnection(connection);
        return result;
    }


    public boolean userNameIsRegistered(String userName) {
        DBConnection connection = getConnection();
        boolean result = connection.userNameIsRegistered(userName);
        putConnection(connection);
        return result;
    }

    public ArrayList<Student> getAllStudents() {
        DBConnection connection = getConnection();
        ArrayList<Student> result = connection.getAllStudents();
        putConnection(connection);
        return result;
    }

    public boolean addNewStudent(Student newStudent) {
        DBConnection connection = getConnection();
        boolean result = connection.addNewStudent(newStudent);
        putConnection(connection);
        return result;
    }

    public Student getStudentById(int studentId) {
        DBConnection connection = getConnection();
        Student result = connection.getStudentById(studentId);
        putConnection(connection);
        return result;
    }

    public boolean modifyStudent(Student modifyingStudent) {
        DBConnection connection = getConnection();
        boolean result = connection.modifyStudent(modifyingStudent);
        putConnection(connection);
        return result;
    }

    public ArrayList<Mark> getMarksByStudent(int id_student){
        DBConnection connection = getConnection();
        ArrayList<Mark> result = connection.getMarksByStudent(id_student);
        putConnection(connection);
        return result;
    }

    public void deleteStudent(int studentId) {
        DBConnection connection = getConnection();
        connection.deleteStudent(studentId);
        putConnection(connection);
    }

    public ArrayList<Discipline> getAllDisciplines() {
        DBConnection connection = getConnection();
        ArrayList<Discipline> result = connection.getAllDisciplines();
        putConnection(connection);
        return result;
    }

    public Discipline getDisciplineById(int disciplineID) {
        DBConnection connection = getConnection();
        Discipline result = connection.getDisciplineById(disciplineID);
        putConnection(connection);
        return result;
    }

    public boolean addNewDiscipline(String disciplineName) {
        DBConnection connection = getConnection();
        boolean result = connection.addNewDiscipline(disciplineName);
        putConnection(connection);
        return result;
    }

    public boolean modifyDiscipline(Discipline modifyingDiscipline) {
        DBConnection connection = getConnection();
        boolean result = connection.modifyDiscipline(modifyingDiscipline);
        putConnection(connection);
        return result;
    }

    public void deleteDiscipline(int disciplineID) {
        DBConnection connection = getConnection();
        connection.deleteDiscipline(disciplineID);
        putConnection(connection);
    }

    public ArrayList<Semester> getAllSemesters() {
        DBConnection connection = getConnection();
        ArrayList<Semester> result = connection.getAllSemesters();
        putConnection(connection);
        return result;
    }

    public Semester getSemesterById(int semesterId){
        DBConnection connection = getConnection();
        Semester result = connection.getSemesterById(semesterId);
        putConnection(connection);
        return result;
    }

    public ArrayList<Discipline> getDisciplinesInSemester(int semesterId){
        DBConnection connection = getConnection();
        ArrayList<Discipline> result = connection.getDisciplinesInSemester(semesterId);
        putConnection(connection);
        return result;
    }

    public boolean addNewSemester(int semesterDuration, String[] idDiscplInSemester){
        DBConnection connection = getConnection();
        boolean result = connection.addNewSemester(semesterDuration, idDiscplInSemester);
        putConnection(connection);
        return result;
    }

    public void deleteSemester(int idSemester){
        DBConnection connection = getConnection();
        connection.deleteSemester(idSemester);
        putConnection(connection);
    }

    public void modifySemester(Semester semester, String idDiscInSemester[]){
        DBConnection connection = getConnection();
        connection.modifySemester(semester, idDiscInSemester);
        putConnection(connection);
    }

    public void restoreAllData(){
        DBConnection connection = getConnection();
        connection.restoreAllData();
        putConnection(connection);
    }

}
