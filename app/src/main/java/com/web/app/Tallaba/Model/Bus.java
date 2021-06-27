package com.web.app.Tallaba.Model;

public class Bus {
    private String direction,startTime,endTime;
    private int hour,munit;

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
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

    public int getHour() {
        return hour;
    }

    public void setHour(int hour) {
        this.hour = hour;
    }

    public int getMunit() {

        return munit;
    }

    public void setMunit(int munit) {
        this.munit = munit;
    }

    public Bus(String direction, String startTime, String endTime, int hour, int munit) {
        this.direction = direction;
        this.startTime = startTime;
        this.endTime = endTime;
        this.hour = hour;
        this.munit = munit;
    }
}
