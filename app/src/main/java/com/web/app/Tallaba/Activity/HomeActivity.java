package com.web.app.Tallaba.Activity;

import android.Manifest;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import com.web.app.Tallaba.Model.Bus;
import com.web.app.Tallaba.Model.Profile;
import com.web.app.Tallaba.R;
import com.web.app.Tallaba.api.RetrofitBuilder;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.PopupMenu;
import androidx.core.content.ContextCompat;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.core.SingleObserver;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class HomeActivity extends AppCompatActivity {
    private static final int PERMISSION_CODE = 1000;
    SharedPreferences sharedPreferences;
    String idUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BottomNavigationView navView = findViewById(R.id.nav_view);
        AppBarConfiguration appBarConfiguration;
        appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications, R.id.navigation_Gpa, R.id.navigation_More)
                .build();

        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navView, navController);

        getPermission();
        sharedPreferences=getPreferences(MODE_PRIVATE);
        idUser=sharedPreferences.getString(getString(R.string.userID),"0");


    }
    void getPermission() {
    if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.M) {
        if(checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)== PackageManager.PERMISSION_DENIED){
            //set permissions
            String[] permission={Manifest.permission.WRITE_EXTERNAL_STORAGE,
                    Manifest.permission.CAMERA,Manifest.permission.ACCESS_NETWORK_STATE};
            //show popup
            requestPermissions(permission,PERMISSION_CODE);
        }
        else {
            //permission is granted
        }
    }
    else{
        //system is less than marshmallow

        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull @NotNull String[] permissions, @NonNull @NotNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode){
            case PERMISSION_CODE:
            if(grantResults.length>0&&grantResults[0]==PackageManager.PERMISSION_GRANTED){

            }
            else
                Toast.makeText(this, "cancel", Toast.LENGTH_SHORT).show();
        }
    }

    public void openViewPost(View view) {
        Intent intent = new Intent(this, PostActivity.class);
        intent.putExtra(getString(R.string.post_id), view.getTag().toString());
        startActivity(intent);
    }

    public void openImageView(View view) {
        startActivity(new Intent(this, ViewImageActivity.class));
    }

    public void openBusActivity(View view) {
        startActivity(new Intent(this, BusActivity.class));

    }

    public void openBooks(View view) {
        startActivity(new Intent(this, BooksActivity.class));

    }

    public void openProfile(View view) {
        startActivity(new Intent(this, ProfileActivity.class));
    }


    public void Logout(View view) {
        Single<String> observable=  RetrofitBuilder.getBuldier().ModelCallLogout(idUser)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());

        observable.subscribe(new SingleObserver<String>() {
            @Override
            public void onSubscribe(@io.reactivex.rxjava3.annotations.NonNull Disposable d) {

            }

            @Override
            public void onSuccess(@io.reactivex.rxjava3.annotations.NonNull String models) {

            }

            @Override
            public void onError(@io.reactivex.rxjava3.annotations.NonNull Throwable e) {

            }
        });


        startActivity(new Intent(this,LoginActivity.class));
        finish();
    }

    public void courses(View view) {
        switch (view.getId()) {
            case R.id.course_file_manager:
                startActivity(new Intent(this, FileManagerActivity.class));
                break;
            case R.id.course_chat:
                startActivity(new Intent(this, ChatActivity.class));
                break;
        }
    }

    public void comment(View view) {
        Intent intent = new Intent(this, PostActivity.class);
        intent.putExtra(getString(R.string.Comment), "comment");
        startActivity(intent);
    }

    public void scanQR(View view) {
        IntentIntegrator integrator = new IntentIntegrator(this);
        integrator.setOrientationLocked(false);
        integrator.setPrompt("Scan QR code");
        integrator.setBeepEnabled(false);
        integrator.setDesiredBarcodeFormats(IntentIntegrator.QR_CODE);
        integrator.initiateScan();

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if (result != null) {

            if (result.getContents() == null) {
                Toast.makeText(this, "Cancelled", Toast.LENGTH_LONG).show();
            } else {

                Toast.makeText(this, "Scanned : " + result.getContents(), Toast.LENGTH_LONG).show();
            }
        }
    }

    public void openProfileCommunity(View view) {
        Intent intent = new Intent(this,ProfileUserActivity.class);
        intent.putExtra(getString(R.string.userID),view.getTag().toString());
        startActivity(intent);
    }

    public void openChangePassword(View view) {
        startActivity(new Intent(this,ChangePasswordActivity.class));

    }
    public void likeRec(View view) {
        TextView  txt= (TextView)view;
        Drawable[] drawables = txt.getCompoundDrawables();
        Drawable leftCompoundDrawable = drawables[0];
        Drawable drawable=ContextCompat.getDrawable(this,R.drawable.ic_like_empty);

        boolean status =leftCompoundDrawable.getConstantState().equals(drawable != null ? drawable.getConstantState() : null);

        String text= txt.getText().toString();
        int  number= Integer.parseInt(text);
        if (status) {

            txt.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_like, 0, 0, 0);
            txt.setTextColor(ContextCompat.getColor(HomeActivity.this, R.color.blue));
            txt.setText(String.valueOf(number+1));
        }
        else {
            txt.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_like_empty, 0, 0, 0);
            txt.setTextColor(ContextCompat.getColor(HomeActivity.this, R.color.black));
            txt.setText(String.valueOf(number-1));

        }

        @NonNull Single<String> observable=  RetrofitBuilder.getBuldier().ModelCallLike(view.getTag(R.id.key_user_id).toString(),view.getTag(R.id.key_post_id).toString())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());

        observable.subscribe(new SingleObserver<String>() {
            @Override
            public void onSubscribe(@io.reactivex.rxjava3.annotations.NonNull Disposable d) {

            }

            @Override
            public void onSuccess(@io.reactivex.rxjava3.annotations.NonNull String models) {
            }

            @Override
            public void onError(@io.reactivex.rxjava3.annotations.NonNull Throwable e) {

            }
        });


    }



    public void openMenuPostRec(View view) {
        PopupMenu popup = new PopupMenu(HomeActivity.this, view);
        popup.getMenuInflater()
                .inflate(R.menu.popup, popup.getMenu());

        popup.setOnMenuItemClickListener(item -> {
            switch (item.getItemId()){
                case R.id.delete:
                    Toast.makeText(this, "delete"+view.getTag(R.id.key_reply_id), Toast.LENGTH_SHORT).show();
                    break;
                case R.id.edit:
                    Intent intent = new Intent(this,NewPostActivity.class);
                    intent.putExtra(getString(R.string.post_id),String.valueOf(view.getTag(R.id.key_post_id)));
                    startActivity(intent);
                    break;
            }
            return true;
        });
        popup.show();
    }
}