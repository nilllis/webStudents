package entity;

/**
 * Created by пк on 06.08.2015.
 */
public class Discipline {
    private int id;
    private String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    @Override
    public String toString() {
        return "Discipline " + "id=" + id + " >>> " + name;
    }
}
