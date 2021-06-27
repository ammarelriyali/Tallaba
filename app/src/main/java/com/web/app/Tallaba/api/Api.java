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

import io.reactivex.rxjava3.core.Single;
import okhttp3.MultipartBody;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;

public interface Api {
    @GET("/posts")
    Single<ArrayList<Post>> callPosts();

    @GET("/bus")
    Single<ArrayList<Bus>> callBus();
    @GET("/chat")
    Single<ArrayList<CoursesContent>> callChat(@Path("id")String idCourses);
    @GET("/filemanager")
    Single<ArrayList<CoursesContent>> callFileManager(@Path("id")String idCourses);
    @GET("/post")
    Single<Post> callPost(@Path("id")String idPost);

    @GET("/logout")
    Single<String> callLogout(@Path("id")String idUser);
    @GET("/register")
    Single<String> callRegister(@Path("id")String idNational);


    @GET("/profile")
    Single<User> callProfile(@Path("id")String idUser);
    @GET("/reply")
    Single<ArrayList<Comment>> callReply(@Path("id")String idComment);
    @GET("/lecture")
    Single<ArrayList<Lecture>> callLecture(@Path("id")String idUser);
    @GET("/section")
    Single<ArrayList<Lecture>> callSection(@Path("id")String idUser);
    @GET("/exam")
    Single<ArrayList<Lecture>> callExam(@Path("id")String idUser);
    @GET("/courses")
    Single<ArrayList<Courses>> callCourses(@Path("id")String idUser);
    @GET("/gpa")
    Single<ArrayList<Semester>> callGpa(@Path("id")String idUser);
    @POST("/addcomment")
    Single<String> callAddComment(@Path("id")String idUser);
    @POST("/addreply")
    Single<String> callAddReply(@Path("id")String idUser);
    @POST("/changepassword")
    Single<User> callChangePassword(@Path("id")String idUser,@Body String password);
    @POST("/login")
    Single<User> callLogin(@Path("id")String username,@Body String password);
    @POST("/createpost")
    Single<String> callCreatePost(@Path("id")String idUser,@Body String content,
                                @Part MultipartBody.Part file);
    @POST("/book")
    Single<String> callBook(@Path("id")String idUser,@Body String date);
    @POST("/createpassword")
    Single<User> callCreatePassword(@Path("id")String idNational,@Body String password);
    @POST("/like")
    Single<String> callLike(@Path("id")String idUser,@Body String postId);



}
