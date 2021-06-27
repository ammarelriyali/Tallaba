package com.web.app.Tallaba.Model;

public class CoursesContent {
    //file manager and chat
    private String  content,date,path;
    private int idMessage,idFile;

    public CoursesContent(String content, String date, int idMessage) {
        this.content = content;
        this.date = date;
        this.idMessage = idMessage;
    }

    public CoursesContent(String content, String date, String path, int idFile) {
        this.content = content;
        this.date = date;
        this.path = path;
        this.idFile = idFile;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public int getIdMessage() {
        return idMessage;
    }

    public void setIdMessage(int idMessage) {
        this.idMessage = idMessage;
    }

    public int getIdFile() {
        return idFile;
    }

    public void setIdFile(int idFile) {
        this.idFile = idFile;
    }
}
