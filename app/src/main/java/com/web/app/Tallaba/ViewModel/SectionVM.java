package com.web.app.Tallaba.ViewModel;

import androidx.annotation.VisibleForTesting;
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

public class SectionVM  extends ViewModel {
    public MutableLiveData mld =new MutableLiveData();
    public void getdata(){
        ArrayList<Lecture> list =new ArrayList<>();
        list.add(new Lecture("Dr.ahmed","Data Stcrture","Wednesday"," ","2:0 pm","2:30 pm","B102",4));
        list.add(new Lecture("Dr.ahmed","Data Stcrture","Wednesday"," ","1:0 pm","2:0 pm","B102",4));
        list.add(new Lecture("Dr.ahmed ","Math","Sunday"," ","5:0 pm","6:0 pm","B102",2));
        list.add(new Lecture("Dr.ahmed","Data Stcrture","Wednesday"," ","3:0 pm","4:0 pm","B102",4));
        list.add(new Lecture("Dr.ahmed","Object-oriented ","Monday"," ","10:0 am","11:0 am","B102",3));
        list.add(new Lecture("Dr.ahmed","OOP","Monday"," ","11:0 pm","11:30 pm","B102",3));
        list.add(new Lecture("Dr.ahmed","Data Stcrture","Wednesday"," ","2:30 pm","3:0 pm","B102",4));
        list.add(new Lecture("Dr.ahmed","Data Stcrture","Wednesday"," ","12:0 pm","1:0 pm","B102",4));
        mld.setValue(list);
    }
    public void getSection(String idUser){
        @NonNull Single<ArrayList<Lecture>> observable=  RetrofitBuilder.getBuldier().ModelCallSection(idUser)
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
