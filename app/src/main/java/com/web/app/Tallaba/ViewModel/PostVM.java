package com.web.app.Tallaba.ViewModel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.web.app.Tallaba.Model.Comment;
import com.web.app.Tallaba.Model.Post;
import com.web.app.Tallaba.api.RetrofitBuilder;

import java.util.ArrayList;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.core.SingleObserver;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class PostVM extends ViewModel {
    public MutableLiveData mld =new MutableLiveData();

    public void getdata(){
        ArrayList<Comment> list=new ArrayList<>();
        list.add(new Comment("this is comment","amar elarby","12-2-1001","https://homepages.cae.wisc.edu/~ece533/images/baboon.png",2,1,1
                ,0,true));
        list.add(new Comment("this is comment2","amar elarby elryly","12-2-1001","https://homepages.cae.wisc.edu/~ece533/images/fruits.png",2,1,1
                ,12,true));
        list.add(new Comment("this is comment2","amar elarby elryly","12-2-1001","https://homepages.cae.wisc.edu/~ece533/images/fruits.png",2,2,1
                ,1,false));
        list.add(new Comment("this is comment2","amar elarby elryly","12-2-1001","https://homepages.cae.wisc.edu/~ece533/images/fruits.png",2,1,1
                ,45,false));
        mld.setValue(new Post(list,"Public-Domain Test Images for Homeworks and Projects\n","asmaa alarby ibreahm ","20-1-41"
                ,"https://homepages.cae.wisc.edu/~ece533/images/monarch.png",7,3,1,500
                ,true,true));

    }
        public void getPost(String idPost){
            @androidx.annotation.NonNull Single<Post> observable=  RetrofitBuilder.getBuldier().ModelCallPost(idPost)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread());

            observable.subscribe(new SingleObserver<Post>() {
                @Override
                public void onSubscribe(@io.reactivex.rxjava3.annotations.NonNull Disposable d) {

                }

                @Override
                public void onSuccess(@io.reactivex.rxjava3.annotations.NonNull Post models) {
                    mld.setValue(models);
                }

                @Override
                public void onError(@io.reactivex.rxjava3.annotations.NonNull Throwable e) {

                }
            });
        }


    }

