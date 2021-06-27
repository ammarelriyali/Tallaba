package com.web.app.Tallaba.ViewModel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.web.app.Tallaba.Model.Post;
import com.web.app.Tallaba.api.RetrofitBuilder;

import java.util.ArrayList;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.core.SingleObserver;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class PostRecVM extends ViewModel {
    public MutableLiveData mld =new MutableLiveData();
    public void getdata(){
        ArrayList<Post> list = new ArrayList<>();
        list.add(new Post("","amar elarby ibrahim abdo elaryly","12-10-2020","",8,9,3,4000,499,false,false));
        list.add(new Post("article","amar","12-10-2020","https://homepages.cae.wisc.edu/~ece533/images/airplane.png",3,3,5,200000,121212,true,true));
        list.add(new Post("article","amar","12-10-2020","",2,9,2,2,32112,true,true));
        list.add(new Post("","amar","12-10-2020","",1,1,1,3,211,false,false));
        list.add(new Post("null","amar","12-10-2020","",0,2,2,23121131,3434,false,true));
        mld.setValue(list);
    }
    public void getPosts(){
        @NonNull Single<ArrayList<Post>> observable=  RetrofitBuilder.getBuldier().ModelCallPosts()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());

        observable.subscribe(new SingleObserver<ArrayList<Post>>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            @Override
            public void onSuccess(@NonNull ArrayList<Post> models) {
                mld.setValue(models);
            }

            @Override
            public void onError(@NonNull Throwable e) {

            }
        });

    }
}
