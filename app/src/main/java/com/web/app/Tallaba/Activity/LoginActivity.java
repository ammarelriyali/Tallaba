package com.web.app.Tallaba.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.web.app.Tallaba.Model.User;
import com.web.app.Tallaba.R;
import com.web.app.Tallaba.api.RetrofitBuilder;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.core.SingleObserver;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class LoginActivity extends AppCompatActivity {
    TextView username,password;
    SharedPreferences sharedPref ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        username=findViewById(R.id.username_text_input);
        password=findViewById(R.id.password_text_input);
        sharedPref = getSharedPreferences(
                getString(R.string.preference_file_key), Context.MODE_PRIVATE);
    }

    public void openHome(View view) {
        if(username.getText().equals(null)||password.getText().equals(null))
            Toast.makeText(this, "pleas enter username and password", Toast.LENGTH_SHORT).show();
        else
            login((String) username.getText(),(String)password.getText());
    }

    public void openRegister(View view) {
        startActivity(new Intent(this,RegisterActivity.class));
    }
    void login(String username,String password){
        @NonNull Single<User> observable=  RetrofitBuilder.getBuldier().ModelCallLogin(username,password)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());

        observable.subscribe(new SingleObserver<User>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            @Override
            public void onSuccess(@NonNull User models) {
                SharedPreferences.Editor editor = sharedPref.edit();
                editor.putInt(getString(R.string.userID), models.getIdUser());
                editor.apply();
                startActivity(new Intent(LoginActivity.this,HomeActivity.class));
                finish();

            }

            @Override
            public void onError(@NonNull Throwable e) {

            }
        });
    }
}