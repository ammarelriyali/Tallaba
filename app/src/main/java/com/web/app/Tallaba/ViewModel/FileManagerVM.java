package com.web.app.Tallaba.ViewModel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.web.app.Tallaba.Model.CoursesContent;
import com.web.app.Tallaba.api.RetrofitBuilder;

import java.util.ArrayList;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.core.SingleObserver;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class FileManagerVM extends ViewModel {
    public MutableLiveData mld =new MutableLiveData();
    public void getdata(){
            ArrayList<CoursesContent> list= new ArrayList<>();
            list.add(new CoursesContent("math chapter 1","12-10-2019","https://homepages.cae.wisc.edu/~ece533/images/serrano.png",2));
            list.add(new CoursesContent("math chapter 1","12-10-2019","https://homepages.cae.wisc.edu/~ece533/images/tulips.png",2));
            list.add(new CoursesContent("math chapter 1","12-10-2019","https://homepages.cae.wisc.edu/~ece533/images/watch.png",2));

        mld.setValue(list);
    }
    public void getFileManager(String idUser){
        @NonNull Single<ArrayList<CoursesContent>> observable=  RetrofitBuilder.getBuldier().ModelCallFileManager(idUser)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());

        observable.subscribe(new SingleObserver<ArrayList<CoursesContent>>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            @Override
            public void onSuccess(@NonNull ArrayList<CoursesContent> models) {
                mld.setValue(models);
            }

            @Override
            public void onError(@NonNull Throwable e) {

            }
        });

    }
}
