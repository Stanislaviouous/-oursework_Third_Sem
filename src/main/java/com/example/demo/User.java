package com.example.demo;

public class User {
    String nameAll;
    String email;
    String password, role;
    public void setNameAll(String nameAll) {
        this.nameAll = nameAll;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getNameAll() {
        return nameAll;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getRole() {
        return role;
    }

    public User(String nameAll, String email, String password, String role) {
        this.nameAll = nameAll;
        this.email = email;
        this.password = password;
        this.role = role;
    }
}
