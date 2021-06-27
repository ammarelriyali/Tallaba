package com.web.app.Tallaba.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.PopupMenu;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.Toast;

import com.web.app.Tallaba.Adapter.BusAd;
import com.web.app.Tallaba.Adapter.ReplyAd;
import com.web.app.Tallaba.Model.Bus;
import com.web.app.Tallaba.Model.Comment;
import com.web.app.Tallaba.R;
import com.web.app.Tallaba.ViewModel.BusVM;
import com.web.app.Tallaba.ViewModel.ReplyVM;

import java.util.ArrayList;

public class ReplyActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    private EditText editText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reply);
        recyclerView= findViewById(R.id.rec_reply);
        editText = findViewById(R.id.text_reply_view_post);
        requestFocusComment();
        String idReply=getIntent().getStringExtra(getString(R.string.Comment));
        if (!idReply.equals(null))
            getReplyList(idReply);


    }
    public void openMenuReplay(View view) {
        PopupMenu popup = new PopupMenu(ReplyActivity.this, view);
        popup.getMenuInflater()
                .inflate(R.menu.popup, popup.getMenu());

        popup.setOnMenuItemClickListener(item -> {
            switch (item.getItemId()){
                case R.id.delete:
                    Toast.makeText(this, "delete"+view.getTag(R.id.key_reply_id), Toast.LENGTH_SHORT).show();
                    break;
                case R.id.edit:
                requestFocusComment((String) view.getTag(R.id.key_reply_content));
                break;
            }
            return true;
        });
        popup.show();
    }
    void requestFocusComment(){
        String action =getIntent().getStringExtra(getString(R.string.Reply));
        if(action!=null&&action.equals("reply")){
            editText.requestFocus();
        }
    }
    void requestFocusComment(String txt){
        editText.setText(txt);
        if(!editText.hasFocus()) {
            editText.requestFocus();
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0);
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
        InputMethodManager imm = (InputMethodManager)getSystemService(
                Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(editText.getWindowToken(), 0);
        finish();
        return super.onOptionsItemSelected(item);
    }



    public void openProfileUser(View view) {
        startActivity(new Intent(this,ProfileUserActivity.class));
    }
    void getReplyList(String idComment) {
        ReplyVM vm = new ViewModelProvider(this).get(ReplyVM.class);
        vm.getReply(idComment);
        vm.mld.observe(this, new Observer<ArrayList<Comment>>() {
            @Override
            public void onChanged(ArrayList<Comment> models) {
                recyclerView.setLayoutManager(new LinearLayoutManager(ReplyActivity.this));
                recyclerView.setAdapter(new ReplyAd(models));
                recyclerView.setHasFixedSize(true);

            }
        });
    }
}