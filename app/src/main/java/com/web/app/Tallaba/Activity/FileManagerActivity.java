 package com.web.app.Tallaba.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.DownloadManager;
import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import com.web.app.Tallaba.Adapter.FileManagerAd;
import com.web.app.Tallaba.Model.CoursesContent;
import com.web.app.Tallaba.R;
import com.web.app.Tallaba.ViewModel.FileManagerVM;

import java.util.ArrayList;

public class FileManagerActivity extends AppCompatActivity {
    SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_file_manager);
        sharedPreferences= getPreferences(MODE_PRIVATE);
        getFileManagerList();
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

    public void download(View view) {
        String uri = (String) view.getTag();
        DownloadManager.Request request= new DownloadManager.Request(Uri.parse(uri));
        request.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_WIFI|
                DownloadManager.Request.NETWORK_MOBILE);
        request.setTitle("Download");
        request.setDescription("Downloading file......");
        request.allowScanningByMediaScanner();
        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
        request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS,""+System.currentTimeMillis());
        DownloadManager manager = (DownloadManager) getSystemService(Context.DOWNLOAD_SERVICE);
        manager.enqueue(request);

    }

    void getFileManagerList() {
        FileManagerVM vm = new ViewModelProvider(this).get(FileManagerVM.class);
        vm.getFileManager(sharedPreferences.getString(getString(R.string.userID),"0"));
        vm.mld.observe(this, new Observer<ArrayList<CoursesContent>>() {
            @Override
            public void onChanged(ArrayList<CoursesContent> models) {
                RecyclerView recyclerView = findViewById(R.id.rec_filemanager);
                recyclerView.setLayoutManager(new LinearLayoutManager(FileManagerActivity.this));
                recyclerView.setAdapter(new FileManagerAd(models));
            }
        });
    }
}