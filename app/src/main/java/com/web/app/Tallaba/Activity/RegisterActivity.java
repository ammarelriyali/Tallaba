package com.web.app.Tallaba.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
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

public class RegisterActivity extends AppCompatActivity {
    private TextView nationalID,password,confirmPassword;
    private LinearLayout layoutPassword,layoutNational;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        nationalID= findViewById(R.id.national_id_text_input);
        password= findViewById(R.id.new_password_text_input);
        confirmPassword= findViewById(R.id.confirm_new_password_text_input);
        layoutPassword=findViewById(R.id.linear_confirm_password);
        layoutNational=findViewById(R.id.linear_NationalID);
        layoutPassword.setVisibility(View.GONE);
    }

    public void sendNationalID(View view) {
        if(nationalID.getText().toString().equals(null))
            Toast.makeText(this, "pleas enter your national number", Toast.LENGTH_SHORT).show();
        else {
        layoutNational.setVisibility(View.GONE);
        layoutPassword.setVisibility(View.VISIBLE);
            @NonNull Single<String> observable=  RetrofitBuilder.getBuldier().ModelCallRegister(nationalID.getText().toString())
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread());

            observable.subscribe(new SingleObserver<String>() {
                @Override
                public void onSubscribe(@NonNull Disposable d) {

                }

                @Override
                public void onSuccess(@NonNull String models) {

                }

                @Override
                public void onError(@NonNull Throwable e) {

                }
            });
    }
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

    public void confirmPassword(View view) {
        if(confirmPassword.getText().toString().equals(null) || password.getText().toString().equals(null)||password.getText().toString().equals(confirmPassword.getText().toString()))
            Toast.makeText(this, " password is not equal", Toast.LENGTH_SHORT).show();
        @NonNull Single<User> observable=  RetrofitBuilder.getBuldier().ModelCallCreatePassword(nationalID.getText().toString(),confirmPassword.getText().toString())
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread());

            observable.subscribe(new SingleObserver<User>() {
                @Override
                public void onSubscribe(@NonNull Disposable d) {

                }

                @Override
                public void onSuccess(@NonNull User models) {
                    finish();

                }

                @Override
                public void onError(@NonNull Throwable e) {

                }
            });

    }
}