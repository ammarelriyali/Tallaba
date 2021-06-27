package com.web.app.Tallaba.Model;

public class Comment {
    private String content,name,date,image;
    private int idPost,idUser,idComment,countReply;
    private boolean isInstructor;

    public Comment(String content, String name, String date, String image, int idPost, int idUser, int idComment, boolean isInstructor) {
        this.content = content;
        this.name = name;
        this.date = date;
        this.image = image;
        this.idPost = idPost;
        this.idUser = idUser;
        this.idComment = idComment;
        this.isInstructor = isInstructor;
    }

    public Comment(String content, String name, String date, String image, int idPost, int idUser, int idComment, int countReply, boolean isInstructor) {
        this.content = content;
        this.name = name;
        this.date = date;
        this.image = image;
        this.idPost = idPost;
        this.idUser = idUser;
        this.idComment = idComment;
        this.countReply = countReply;
        this.isInstructor = isInstructor;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getIdPost() {
        return idPost;
    }

    public void setIdPost(int idPost) {
        this.idPost = idPost;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public int getIdComment() {
        return idComment;
    }

    public void setIdComment(int idComment) {
        this.idComment = idComment;
    }

    public int getCountReply() {
        return countReply;
    }

    public void setCountReply(int countReply) {
        this.countReply = countReply;
    }

    public boolean isInstructor() {
        return isInstructor;
    }

    public void setInstructor(boolean instructor) {
        isInstructor = instructor;
    }
}
