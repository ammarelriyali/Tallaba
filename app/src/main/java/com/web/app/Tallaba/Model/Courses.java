package com.web.app.Tallaba.Model;

public class Courses {
    private String idCourses,name,instructor;
    private boolean notFileManager,notChat;

    public String getIdCourses() {
        return idCourses;
    }

    public void setIdCourses(String idCourses) {
        this.idCourses = idCourses;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getInstructor() {
        return instructor;
    }

    public void setInstructor(String instructor) {
        this.instructor = instructor;
    }

    public boolean isNotFileManager() {
        return notFileManager;
    }

    public void setNotFileManager(boolean notFileManager) {
        this.notFileManager = notFileManager;
    }

    public boolean isNotChat() {
        return notChat;
    }

    public void setNotChat(boolean notChat) {
        this.notChat = notChat;
    }

    public Courses(String idCourses, String name, String instructor, boolean notFileManager, boolean notChat) {
        this.idCourses = idCourses;
        this.name = name;
        this.instructor = instructor;
        this.notFileManager = notFileManager;
        this.notChat = notChat;
    }
}