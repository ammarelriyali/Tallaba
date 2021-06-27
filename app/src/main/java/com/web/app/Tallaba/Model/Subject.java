package com.web.app.Tallaba.Model;

public class Subject {
    private String name, code,mark;
    private int creditHours;
    private float degree;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }

    public int getCreditHours() {
        return creditHours;
    }

    public void setCreditHours(int creditHours) {
        this.creditHours = creditHours;
    }

    public float getDegree() {
        return degree;
    }

    public void setDegree(int degree) {
        this.degree = degree;
    }

    public Subject(String name, String code, String mark, int creditHours,Double degree) {
        this.name = name;
        this.code = code;
        this.mark = mark;
        this.creditHours = creditHours;
        this.degree = degree.floatValue();
    }
}
