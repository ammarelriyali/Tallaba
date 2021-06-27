package com.web.app.Tallaba.Model;

public class Lecture {

    private boolean isHeader ;
    private String courses,day,date,startTime,endTime,hall,name;
    private int IdDay;

    public Lecture(String courses, String day, String startTime, String endTime, String hall, String name, int idDay) {
        this.courses = courses;
        this.day = day;
        this.startTime = startTime;
        this.endTime = endTime;
        this.hall = hall;
        this.name = name;
        IdDay = idDay;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Lecture(String courses, String date, String startTime, String endTime, String hall) {

        this.courses = courses;
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
        this.hall = hall;

    }

    public Lecture(String name, String courses, String day, String date, String startTime, String endTime, String hall, int idDay) {
        this.name=name;
        this.courses = courses;
        this.day = day;
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
        this.hall = hall;
        IdDay = idDay;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getHall() {
        return hall;
    }

    public void setHall(String hall) {
        this.hall = hall;
    }

    public int getIdDay() {
        return IdDay;
    }

    public void setIdDay(int idDay) {
        IdDay = idDay;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }



    public boolean isHeader() {
        return isHeader;
    }

    public void setHeader(boolean header) {
        isHeader = header;
    }

    public String getCourses() {
        return courses;
    }

    public void setCourses(String courses) {
        this.courses = courses;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }
}
