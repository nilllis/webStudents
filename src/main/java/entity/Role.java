package entity;

import db.DBServices;

/**
 * Created by пк on 22.07.2015.
 */
public class Role {
    private int id;
    private String role;
    private String roleLangRu;

    public Role() {
    }

    public Role(int idRole) {
        DBServices services = new DBServices();
        Role findingRole = services.getRole(idRole);
        if (findingRole != null) {
            this.id = idRole;
            this.role = findingRole.getRole();
            this.roleLangRu = findingRole.getRoleLangRu();
        }
    }

    public Role(String nameRole) {
        DBServices services = new DBServices();
        Role findingRole = services.getRole(nameRole);
        if (findingRole != null) {
            this.id = findingRole.getId();
            this.role = nameRole;
            this.roleLangRu = findingRole.getRoleLangRu();
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getRoleLangRu() {
        return roleLangRu;
    }

    public void setRoleLangRu(String roleLangRu) {
        this.roleLangRu = roleLangRu;
    }

    @Override
    public String toString() {
        return "Role >> id = " + id + "; role = " + role;
    }
}
