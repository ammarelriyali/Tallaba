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

public class ChatVM extends ViewModel {
    public MutableLiveData mld =new MutableLiveData();
    public void getdata(){
        ArrayList<CoursesContent> list=new ArrayList<>();
        list.add(new CoursesContent("Material icons are delightful, beautifully crafted symbols for common actions and " +
                "items. Download on desktop to use them in your digital products for Android, iOS, and web.aterial icons " +
                "are delightful, beautifully crafted symbols for commo\n" +
                "            n actions and items. Download on desktop to use them in your digital products for" +
                " Android, iOS, and web.aterial icons are delightful, beautifully crafted symbols for common a" +
                "ctions and items. Download on desktop to use them in your digital products for Android" +
                ", iOS, and web.aterial icons are delightful, beautifully crafted symbols for common actions and items" +
                ". Download on desktop to use them in your digital products for Android, iOS, and web.aterial icons are" +
                " delightful, beautifully crafted symbols for common actions and items. Download on desktop to use them " +
                "in your digital products for Android, iOS, and web","20-10-2020",2));
        list.add(new CoursesContent("Material icons are delightful, beautifully crafted symbols for common actions and " +
                "items. Download on desktop to use them in your digital products for Android, iOS, and web.aterial icons " +
                "are delightful, beautifully crafted symbols for commo\n" +
                "     and items. Download on desktop to use them " +
                "in your digital products for Android, iOS, and web","20-10-2020",2));
        list.add(new CoursesContent("Material icons are delightful, beautifully crafted symbols for common actions and " +
                "items. Download on desktop to use them in your digital products for Android, iOS, and web.aterial icons " +
                "are delightful, beautifully crafted symbols for commo\n" +
                "            n actions and items. Download on desktop to use them in your digital products for" +
                " Android, iOS, and web.aterial icons are delightful, beautifully crafted symbols for common a" +
                "ctions and iteownload on desktop to use them " +
                "in your digital products for Android, iOS, and web","20-10-2020",2));
        list.add(new CoursesContent("Material icons are delightful, beautifully crafted symbols for common actions and " +
                "items. Download on desktop to use them in your digital products for Android, iOS, and web.aterial icons " +
                "are delightful, beautifully crafted symbols for commo\n" +
                "            n actions and items. Download on desktop to use them in your digital products for" +
                " Android, iOS, and web.aterial icons are delightful, beautifully crafted symbols for common a" +
                "ctions and items. Download on desktop to use them in your digital products for Android" +
                ", iOS, and web.aterial icons are delightful, beautifully crafted symbols for common actions and items" +
                ". Download on desktop to use them in your digital products for Android, iOS, and web.aterial icons are" +
                " delightful, beautifully crafted symbols for common actions and items. Download on desktop to use them " +
                "in your digital products for Android, iOS, and web","20-10-2020",2));
        mld.setValue(list);

    }

    public void getChat(String idUser){
        @NonNull Single<ArrayList<CoursesContent>> observable=  RetrofitBuilder.getBuldier().ModelCallChat(idUser)
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
