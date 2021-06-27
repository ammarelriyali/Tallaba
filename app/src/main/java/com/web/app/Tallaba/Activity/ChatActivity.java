package com.web.app.Tallaba.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.web.app.Tallaba.Adapter.ChatAd;
import com.web.app.Tallaba.Model.CoursesContent;
import com.web.app.Tallaba.R;
import com.web.app.Tallaba.ViewModel.ChatVM;

import java.util.ArrayList;

public class ChatActivity extends AppCompatActivity {
    SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        sharedPreferences= getPreferences(MODE_PRIVATE);
        getChatList();
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
    void getChatList() {
        ChatVM vm = new ViewModelProvider(this).get(ChatVM.class);
        vm.getChat(sharedPreferences.getString(getString(R.string.userID),"0"));
        vm.mld.observe(this, new Observer<ArrayList<CoursesContent>>() {
            @Override
            public void onChanged(ArrayList<CoursesContent> models) {
                RecyclerView recyclerView = findViewById(R.id.rec_chat);
                recyclerView.setAdapter(new ChatAd(models));
                recyclerView.setLayoutManager(new LinearLayoutManager(ChatActivity.this));
            }
        });
    }

}