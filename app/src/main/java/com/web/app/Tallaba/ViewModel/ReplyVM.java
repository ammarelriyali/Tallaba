package com.web.app.Tallaba.ViewModel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.web.app.Tallaba.Model.Comment;
import com.web.app.Tallaba.api.RetrofitBuilder;

import java.util.ArrayList;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.core.SingleObserver;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class ReplyVM extends ViewModel {
    public MutableLiveData mld =new MutableLiveData();
    public void getdata(){
        ArrayList<Comment> list=new ArrayList<>();
        list.add(new Comment("String content", "String name", "String date", "https://homepages.cae.wisc.edu/~ece533/images/frymire.png", 2, 2, 2,true));
        list.add(new Comment("String content", "String name", "String date", "", 2, 2, 2,true));
        list.add(new Comment("String content", "String name", "String date", "", 2, 2, 2,false));
        list.add(new Comment("String content", "String name", "String date", "https://homepages.cae.wisc.edu/~ece533/images/frymire.png", 2, 2, 2,true));
        list.add(new Comment("String content", "String name", "String date", "https://homepages.cae.wisc.edu/~ece533/images/frymire.png", 2, 2, 2,true));
        list.add(new Comment("String content", "String name", "String date", "https://homepages.cae.wisc.edu/~ece533/images/frymire.png", 2, 2, 2,true));
        list.add(new Comment("String content", "String name", "String date", "https://homepages.cae.wisc.edu/~ece533/images/frymire.png", 2, 2, 2,true));
        mld.setValue(list);
    }
    public void getReply(String idComment){
        @NonNull Single<ArrayList<Comment>> observable=  RetrofitBuilder.getBuldier().ModelCallReply(idComment)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());

        observable.subscribe(new SingleObserver<ArrayList<Comment>>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            @Override
            public void onSuccess(@NonNull ArrayList<Comment> models) {
                mld.setValue(models);
            }

            @Override
            public void onError(@NonNull Throwable e) {

            }
        });

    }
}
