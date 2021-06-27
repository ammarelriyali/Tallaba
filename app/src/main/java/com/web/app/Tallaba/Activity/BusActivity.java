package com.web.app.Tallaba.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.provider.AlarmClock;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import com.web.app.Tallaba.Adapter.BusAd;
import com.web.app.Tallaba.Model.Bus;
import com.web.app.Tallaba.R;
import com.web.app.Tallaba.ViewModel.BusVM;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class BusActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bus);
        getBusesList();
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

    @SuppressLint("QueryPermissionsNeeded")
    public void createAlarm(String message, int hour, int minutes) {
        Intent intent = new Intent(AlarmClock.ACTION_SET_ALARM)
                .putExtra(AlarmClock.EXTRA_MESSAGE, message)
                .putExtra(AlarmClock.EXTRA_HOUR, hour)
                .putExtra(AlarmClock.EXTRA_SKIP_UI, true)
                .putExtra(AlarmClock.EXTRA_MINUTES, minutes);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }

    public void sendTime(@NotNull View view) {
        int hour = (int) view.getTag(R.id.key_hour);
        int munit = (int) view.getTag(R.id.key_munit);
        if (munit == 0) {
            if (hour == 0)
                hour = 23;
            else {
                hour -= 1;
                munit = 55;
            }
        } else
            munit -= 5;

        createAlarm("Bus", hour, munit);
    }

    void getBusesList() {
        BusVM vm = new ViewModelProvider(this).get(BusVM.class);
        vm.getBuses();
        vm.mld.observe(this, new Observer<ArrayList<Bus>>() {
            @Override
            public void onChanged(ArrayList<Bus> models) {
                RecyclerView recyclerView = findViewById(R.id.rec_bus);
                recyclerView.setLayoutManager(new LinearLayoutManager(BusActivity.this));
                recyclerView.setAdapter(new BusAd(models));
            }
        });
    }
}