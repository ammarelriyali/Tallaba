package com.web.app.Tallaba.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;

import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;
import com.web.app.Tallaba.R;
import com.web.app.Tallaba.api.RetrofitBuilder;

import java.util.ArrayList;
import java.util.Calendar;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.core.SingleObserver;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class BooksActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {
    private DatePickerDialog dpd;
    private TextView dateTxt,numberTxt;
    private Button bookingBtn;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_books);
        dateTxt=findViewById(R.id.ticket_date);
        numberTxt=findViewById(R.id.ticket_number);
        bookingBtn=findViewById(R.id.booking);
        sharedPreferences =getPreferences(MODE_PRIVATE);


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

    void setSelectableDays() {
        Calendar now = Calendar.getInstance();
         dpd = DatePickerDialog.newInstance(
                BooksActivity.this,
                now.get(Calendar.YEAR), // Initial year selection
                now.get(Calendar.MONTH), // Initial month selection
                now.get(Calendar.DAY_OF_MONTH) // Inital day selection
        );
        dpd.setMinDate(Calendar.getInstance(now.getTimeZone()));
        dpd.setAccentColor(getColor(R.color.blue));
        dpd.setOkText("pick");
        ArrayList<Calendar> weekdays = new ArrayList<>();

        for (int i = 0; i < 14; i++) {
            if (now.get(Calendar.DAY_OF_WEEK) != Calendar.FRIDAY && now.get(Calendar.DAY_OF_YEAR)!=181) {

                Calendar d = (Calendar) now.clone();
                weekdays.add(d);
            }
            now.add(Calendar.DATE, 1);
        }
        Calendar[] weekdayDays = weekdays.toArray(new Calendar[weekdays.size()]);
        dpd.setSelectableDays(weekdayDays);

        dpd.setOnCancelListener(dialog -> {
            Log.d("DatePickerDialog", "Dialog was cancelled");
            dpd = null;
        });

        dpd.show(getSupportFragmentManager(), "Datepickerdialog");
    }

    @Override
    public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {
        String date = " "+dayOfMonth+"/"+(monthOfYear+1)+"/"+year;
        String idUser=sharedPreferences.getString(getString(R.string.userID),"0");
        bookingTicket(idUser,date);
        dateTxt.setText(date);
        numberTxt.setText("122");
        bookingBtn.setText("Cancel");
        bookingBtn.setTag("cancel");
    }

    public void pickDay(View view) {
        if(view.getTag()=="cancel")
            finish();
        else
            setSelectableDays();

    }
    void bookingTicket(String idUser,String date){
        @NonNull Single<String> observable=  RetrofitBuilder.getBuldier().ModelCallBook(idUser, date)
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
