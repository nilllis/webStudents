package entity;

/**
 * Created by пк on 16.08.2015.
 */
public class Mark {
    private int idStudent;
    private int idDiscipline;
    private String nameDiscipline;
    private int idSemester;
    private int value;

    public int getIdStudent() {
        return idStudent;
    }

    public void setIdStudent(int idStudent) {
        this.idStudent = idStudent;
    }

    public int getIdDiscipline() {
        return idDiscipline;
    }

    public void setIdDiscipline(int idDiscipline) {
        this.idDiscipline = idDiscipline;
    }

    public String getNameDiscipline() {
        return nameDiscipline;
    }

    public void setNameDiscipline(String nameDiscipline) {
        this.nameDiscipline = nameDiscipline;
    }

    public int getIdSemester() {
        return idSemester;
    }

    public void setIdSemester(int idSemester) {
        this.idSemester = idSemester;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Mark{ idStudent=" + idStudent + ", Discipline(" + idDiscipline + ")=" + nameDiscipline +
                ", idSemester=" + idSemester + ", value=" + value + '}';
    }
}
