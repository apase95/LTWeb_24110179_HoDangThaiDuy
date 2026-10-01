package vn.edu.ktqt.model;

import java.io.Serializable;

public class User_24110179 implements Serializable {
    private final int id;
    private final String email;
    private final String fullname;
    private final boolean admin;

    public User_24110179(int id, String email, String fullname, boolean admin) {
        this.id = id;
        this.email = email;
        this.fullname = fullname;
        this.admin = admin;
    }

    public int getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getFullname() {
        return fullname;
    }

    public boolean isAdmin() {
        return admin;
    }
}
