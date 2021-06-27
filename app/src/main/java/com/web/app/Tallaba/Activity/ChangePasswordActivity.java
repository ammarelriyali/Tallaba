package com.web.app.Tallaba.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
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

public class ChangePasswordActivity extends AppCompatActivity {
    private TextView changePasswordTxt,confirmTxt;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.password_chang_activity);
        changePasswordTxt=findViewById(R.id.text_password_change);
        confirmTxt=findViewById(R.id.text_confirm_password);
        sharedPreferences=getPreferences(MODE_PRIVATE);

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.backbutton, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        finish();
        return super.onOptionsItemSelected(item);
    }

    public void changePassword(View view) {
        if(changePasswordTxt.getText().toString().equals(confirmTxt.getText().toString()))
            changePassword(changePasswordTxt.getText().toString());
        else
        Toast.makeText(this, "password not equal", Toast.LENGTH_SHORT).show();
    }
    void changePassword(String password){

        @NonNull Single<User> observable=  RetrofitBuilder.getBuldier().ModelCallChangePassword(sharedPreferences.getString(getString(R.string.userID),"0"),password)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());

        observable.subscribe(new SingleObserver<User>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            public void onSuccess(@NonNull User models) {
                finish();

            }

            @Override
            public void onError(@NonNull Throwable e) {

            }
        });
    }
}