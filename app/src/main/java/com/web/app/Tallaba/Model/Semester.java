package com.web.app.Tallaba.Model;

import java.util.ArrayList;

public class Semester {
    private String name, mark;
    private float gpa, totalGpa;
    private ArrayList<Subject> list ;

    public Semester(String name, String mark, Double gpa, Double totalGpa, ArrayList<Subject> list) {
        this.name = name;
        this.mark = mark;
        this.gpa = gpa.floatValue();
        this.totalGpa = totalGpa.floatValue();
        this.list = list;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }

    public float getGpa() {
        return gpa;
    }

    public void setGpa(int gpa) {
        this.gpa = gpa;
    }

    public float getTotalGpa() {
        return totalGpa;
    }

    public void setTotalGpa(int totalGpa) {
        this.totalGpa = totalGpa;
    }

    public ArrayList<Subject> getList() {
        return list;
    }

    public void setList(ArrayList<Subject> list) {
        this.list = list;
    }
}
