package com.web.app.Tallaba.Model;

public class User {
    private String Password ,confirmPassword,username,level,code,department,name;
    private int idUser;

    public User(String level, String code, String department, String name) {
        this.level = level;
        this.code = code;
        this.department = department;
        this.name = name;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public User(String password, String confirmPassword, String username, int idUser) {
        Password = password;
        this.confirmPassword = confirmPassword;
        this.username = username;
        this.idUser = idUser;
    }

    public User(String password, String confirmPassword, String username) {
        Password = password;
        this.confirmPassword = confirmPassword;
        this.username = username;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
