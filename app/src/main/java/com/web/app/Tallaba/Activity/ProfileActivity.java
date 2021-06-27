package com.web.app.Tallaba.Activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.web.app.Tallaba.Model.User;
import com.web.app.Tallaba.R;
import com.web.app.Tallaba.api.RetrofitBuilder;

import java.io.InputStream;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.core.SingleObserver;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class ProfileActivity extends AppCompatActivity {
    private static final int PICK_IMAGE = 12;
    ImageView profileImage;
    TextView name,code,level,department;
    SharedPreferences sharedPref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        profileImage =findViewById(R.id.image_profile);
        name= findViewById(R.id.profile_name);
        code=findViewById(R.id.profile_code);
        level=findViewById(R.id.profile_level);
        department=findViewById(R.id.profile_department);
        sharedPref = getPreferences(Context.MODE_PRIVATE);
//        getUserData(sharedPref.getString(String.valueOf(R.string.userID),""));
        getUserData(sharedPref.getString(getString(R.string.userID),"0"));
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

    public void changeImage(View view) {
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            try {
                final Uri imageUri = data.getData();
                final InputStream imageStream = getContentResolver().openInputStream(imageUri);
                final Bitmap selectedImage = BitmapFactory.decodeStream(imageStream);
                profileImage.setImageBitmap(selectedImage);
            } catch (Exception e) {
                e.printStackTrace();
            }

        } else {
        }

    }


    public void logout(View view) {
//
            @NonNull Single<String> observable=  RetrofitBuilder.getBuldier().ModelCallLogout(sharedPref.getString(getString(R.string.userID),"0"))
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread());

            observable.subscribe(new SingleObserver<String>() {
                @Override
                public void onSubscribe(@NonNull Disposable d) {

                }

                @Override
                public void onSuccess(@NonNull String models) {
                    finish();

                }

                @Override
                public void onError(@NonNull Throwable e) {

                }
            });

    }

    public void openChangePassword(View view) {
        startActivity(new Intent(this,ChangePasswordActivity.class));

    }
    void getUserData(String idUser){
        @NonNull Single<User> observable=  RetrofitBuilder.getBuldier().ModelCallProfile(sharedPref.getString(getString(R.string.userID),"0"))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());

        observable.subscribe(new SingleObserver<User>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            @Override
            public void onSuccess(@NonNull User models) {
                name.setText(models.getName());
                code.setText(models.getCode());
                level.setText(models.getLevel());
                department.setText(models.getDepartment());
            }

            @Override
            public void onError(@NonNull Throwable e) {

            }
        });
    }
}