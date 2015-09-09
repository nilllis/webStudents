package entity;

import java.sql.Date;

/**
 * Created by пк on 26.07.2015.
 */
public class Student {
    private int id;
    private String name;
    private String lastName;
    private String groupName;
    private long date;

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

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public long getDate() {
        return date;
    }

    public void setDate(long date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Student >>" + "id = " + id + ", name = " + name + ", lastName = " + lastName + ", group = " + groupName +
                ", date = " + new Date(date).toString();
    }
}
