    package com.web.app.Tallaba.ViewModel;

import android.widget.TextView;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.web.app.Tallaba.Model.Bus;
import com.web.app.Tallaba.api.RetrofitBuilder;

import java.util.ArrayList;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.core.SingleObserver;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class BusVM extends ViewModel {
    public MutableLiveData mld =new MutableLiveData();
    public void getdata(){
        ArrayList<Bus> list = new ArrayList<>();
        list.add(new Bus("Haram","1:00 pm","1:30 pm",13,0));
        list.add(new Bus("Moneb","10:00 am","12:00 pm",10,0));
        list.add(new Bus("Rmsies","12:00 pm","12:30 pm",12,0));
        list.add(new Bus("Haram","2:00 pm","2:30 pm",14,0));
        list.add(new Bus("Haram","12:00 pm","01:30 pm",12,0));
        mld.setValue(list);
    }
    public void getBuses(){
        @NonNull Single<ArrayList<Bus>> observable=  RetrofitBuilder.getBuldier().ModelCallBus()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());

        observable.subscribe(new SingleObserver<ArrayList<Bus>>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            @Override
            public void onSuccess(@NonNull ArrayList<Bus> models) {
                mld.setValue(models);
            }

            @Override
            public void onError(@NonNull Throwable e) {

            }
        });


    }
}
