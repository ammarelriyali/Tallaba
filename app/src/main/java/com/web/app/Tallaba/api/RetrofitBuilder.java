package com.web.app.Tallaba.api;

import com.web.app.Tallaba.Model.Bus;
import com.web.app.Tallaba.Model.Comment;
import com.web.app.Tallaba.Model.Courses;
import com.web.app.Tallaba.Model.CoursesContent;
import com.web.app.Tallaba.Model.Lecture;
import com.web.app.Tallaba.Model.Post;
import com.web.app.Tallaba.Model.Semester;
import com.web.app.Tallaba.Model.User;

import java.util.ArrayList;

import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory;
import io.reactivex.rxjava3.core.Single;
import okhttp3.MultipartBody;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.Multipart;

public class RetrofitBuilder {
    private static RetrofitBuilder buldier;
    Api api;

    public RetrofitBuilder() {
        Retrofit retrofit = new Retrofit.Builder().
                baseUrl("https://jsonplaceholder.typicode.com")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .build();
        api = retrofit.create(Api.class);
    }

    public static RetrofitBuilder getBuldier() {
        if (null == buldier) {
            buldier = new RetrofitBuilder();
        }
        return buldier;
    }


    public Single<ArrayList<Post>> ModelCallPosts(){
        return api.callPosts();
    }
    public Single<ArrayList<Bus>> ModelCallBus(){
        return api.callBus();
    }
    public Single<ArrayList<CoursesContent>> ModelCallChat(String id){
        return api.callChat(id);
    }
    public Single<ArrayList<CoursesContent>> ModelCallFileManager(String id){
        return api.callFileManager(id);
    }
    public Single<Post> ModelCallPost(String idPost){
        return api.callPost(idPost);
    }
//
    public Single<User> ModelCallLogin(String username, String password){
        return api.callLogin(username, password);
    }
    public Single<String> ModelCallLogout(String idUser){
        return api.callLogout(idUser);
    }
    public Single<String> ModelCallRegister(String idNational)
    {
        return api.callRegister(idNational);
    }
    public Single<User> ModelCallCreatePassword(String idNational,String password){
        return api.callCreatePassword(idNational,password);
    }
    public Single<User> ModelCallChangePassword(String idUser,String password){
        return api.callChangePassword(idUser, password);
    }
    public Single<User> ModelCallProfile(String idUser){
        return api.callProfile(idUser);
    }
    public Single<String> ModelCallBook(String idUser,String date){
        return api.callBook(idUser,date);
    }
    public Single<ArrayList<Comment>> ModelCallReply(String idUser ){
        return api.callReply(idUser);
    }
    public Single<ArrayList<Lecture>> ModelCallLecture(String idUser){
        return api.callLecture(idUser);
    } public Single<ArrayList<Lecture>> ModelCallSection(String idUser){
        return api.callSection(idUser);
    }
    public Single<ArrayList<Lecture>> ModelCallExam(String idUser){
        return api.callExam(idUser);
    }
    public Single<ArrayList<Courses>> ModelCallCourses(String idUser){
        return api.callCourses(idUser);
    }
    public Single<ArrayList<Semester>> ModelCallGpa(String idUser){
        return api.callGpa(idUser);
    }
    public Single<String> ModelCallAddComment(String idUser){
        return api.callAddComment(idUser);
    }  public Single<String> ModelCallAddReply(String idUser){
        return api.callAddReply(idUser);
    }
    public Single<String> ModelCallCreatePost(String idUser, String content, MultipartBody.Part file){
        return api.callCreatePost(idUser,content,file);
    }public Single<String> ModelCallLike(String idUser, String postId){
        return api.callLike(idUser,postId);
    }

}
