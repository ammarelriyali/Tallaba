package com.web.app.Tallaba.ViewModel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.web.app.Tallaba.Model.Semester;
import com.web.app.Tallaba.Model.Subject;
import com.web.app.Tallaba.api.RetrofitBuilder;

import java.util.ArrayList;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.core.SingleObserver;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class GpaVM extends ViewModel {
    public MutableLiveData mld =new MutableLiveData();
    public void getdata(){
        ArrayList<Subject> li=new ArrayList<>();
        li.add(new Subject("math","CS919","B",3,  80.0));
        li.add(new Subject("programming","CS9","A+",3,  99.0));
        li.add(new Subject("logic design","CS919","D+",3,  82.0));
        li.add(new Subject("math","CS91229","D",3,  77.12));
        li.add(new Subject("math","CS919","C",3,  83.12));
        li.add(new Subject("math","CS919","B+",3,  83.01));

        ArrayList<Semester> list=new ArrayList<>();
        list.add(new Semester("2020-2021 Fist","A+",3.22,3.11,li));
        list.add(new Semester("2020-2021 second","C+",2.22,3.11,li));
        list.add(new Semester("2018-2019 Fist","B+",2.09,2.11,li));
        list.add(new Semester("2019-2020 Fist","D+",3.66,1.11,li));
        mld.setValue(list);
    }
    public void getGpa(String idUser){
        @NonNull Single<ArrayList<Semester>> observable=  RetrofitBuilder.getBuldier().ModelCallGpa(idUser)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());

        observable.subscribe(new SingleObserver<ArrayList<Semester>>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            @Override
            public void onSuccess(@NonNull ArrayList<Semester> models) {
                mld.setValue(models);
            }

            @Override
            public void onError(@NonNull Throwable e) {

            }
        });

    }
}
