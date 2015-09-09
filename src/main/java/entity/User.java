package entity;

import java.util.LinkedHashSet;
import java.util.Set;

/**
 * Created by пк on 23.07.2015.
 */
public class User {
    private int id;
    private String login;
    private String pass;
    private Set<Role> roles = new LinkedHashSet<Role>();


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public void addRole(Role role) {
        roles.add(role);
    }

    @Override
    public String toString() {
        return "User >>> " + "id = " + id + "; login = " + login + "; pass = " + pass + " <<<\n roles = " + roles.toString();
    }

}
