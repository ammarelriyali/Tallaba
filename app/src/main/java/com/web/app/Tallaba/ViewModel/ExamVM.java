package com.web.app.Tallaba.ViewModel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.web.app.Tallaba.Model.Lecture;
import com.web.app.Tallaba.api.RetrofitBuilder;

import java.util.ArrayList;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.core.SingleObserver;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class ExamVM extends ViewModel {
    public MutableLiveData mld =new MutableLiveData();
    public void getdata(){
        ArrayList<Lecture> list =new ArrayList<>();
        list.add(new Lecture("Math","20-1-2021","1:0 pm","2:0 pm","B102"));
        list.add(new Lecture("logic","21-1-2021","1:0 pm","2:0 pm","B102"));
        list.add(new Lecture("logic","21-1-2021","2:0 pm","3:0 pm","B102"));
        list.add(new Lecture("database","22-1-2021","12:0 pm","1:0 pm","B102"));
        list.add(new Lecture("Math","22-1-2021","2:0 pm","3:0 pm","B102"));
       mld.setValue(list);
    }
    public void getExam(String idUser){
        @NonNull Single<ArrayList<Lecture>> observable=  RetrofitBuilder.getBuldier().ModelCallExam(idUser)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());

        observable.subscribe(new SingleObserver<ArrayList<Lecture>>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            @Override
            public void onSuccess(@NonNull ArrayList<Lecture> models) {
                mld.setValue(models);
            }

            @Override
            public void onError(@NonNull Throwable e) {

            }
        });

    }
}
