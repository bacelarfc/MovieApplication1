package models;

import java.io.Serializable;

public class Actor implements Serializable {

    //Models.Actor class:
    // 2 parameters: name and role

    private String fullName;
    private String role;

    public Actor(String fullName, String role) {
        this.fullName = fullName;
        this.role = role;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "Full name: " + this.fullName + ", role: " + this.role;
    }
}
