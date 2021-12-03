package com.example.cinematesdesktop.Model;

public class Admin {
    private String ID;
    private String password;

    public Admin(String ID, String password) {
        this.ID = ID;
        this.password = password;
    }

    public Admin() {
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Admin{" +
                "ID='" + ID + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
