package com.web.app.Tallaba.Model;

import java.util.ArrayList;

public class Post {
    private String content,name,date,photo;
    private int imageCount,idPost,idUser,countLike,countComment;
    private boolean isInstructor,isLiked;
    private ArrayList<Comment> list = new ArrayList<>();


    public ArrayList<Comment> getList() {
        return list;
    }

    public void setList(ArrayList<Comment> list) {
        this.list = list;
    }

    public Post(ArrayList<Comment> list, String content, String name, String date, String photo, int imageCount, int idPost, int idUser, int countLike, boolean isInstructor, boolean isLiked) {
        this.list = list;
        this.content = content;
        this.name = name;
        this.date = date;
        this.photo = photo;
        this.imageCount = imageCount;
        this.idPost = idPost;
        this.idUser = idUser;
        this.countLike = countLike;
        this.isInstructor = isInstructor;
        this.isLiked = isLiked;
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

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public int getImageCount() {
        return imageCount;
    }

    public void setImageCount(int imageCount) {
        this.imageCount = imageCount;
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

    public int getCountLike() {
        return countLike;
    }

    public void setCountLike(int countLike) {
        this.countLike = countLike;
    }

    public boolean isInstructor() {
        return isInstructor;
    }

    public void setInstructor(boolean instructor) {
        isInstructor = instructor;
    }

    public boolean isLiked() {
        return isLiked;
    }

    public void setLiked(boolean liked) {
        isLiked = liked;
    }

    public int getCountComment() {
        return countComment;
    }

    public void setCountComment(int countComment) {
        this.countComment = countComment;
    }

    public Post(String content, String name, String date, String photo, int imageCount, int idPost, int idUser, int countLike, int countComment, boolean isInstructor, boolean isLiked) {
        this.content = content;
        this.name = name;
        this.date = date;
        this.photo = photo;
        this.imageCount = imageCount;
        this.idPost = idPost;
        this.idUser = idUser;
        this.countLike = countLike;
        this.isInstructor = isInstructor;
        this.isLiked = isLiked;
        this.countComment=countComment;
    }
}
