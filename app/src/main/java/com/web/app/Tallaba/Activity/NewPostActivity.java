package com.web.app.Tallaba.Activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import com.web.app.Tallaba.Adapter.RecyclerNewPostAd;
import com.web.app.Tallaba.Model.Post;
import com.web.app.Tallaba.R;

import java.util.ArrayList;

public class NewPostActivity extends AppCompatActivity {

    private static final int PICK_IMAGE = 28;
    private ArrayList<Uri> uri =new ArrayList<>();
    private RecyclerNewPostAd adapter;
    private RecyclerView recyclerView;
    private EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_post);
        recyclerView= findViewById(R.id.recyclerpost);
        editText=findViewById(R.id.post_add_content);
        getPost();
        setDataRec();
    }
    void setDataRec(){
        recyclerView.setLayoutManager(new LinearLayoutManager(this,RecyclerView.HORIZONTAL,false));
        adapter=new RecyclerNewPostAd(uri);
        recyclerView.setAdapter(adapter);
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

    public void removeImage(View view) {
        String pos=view.getTag(R.id.key_image_remove).toString();
        uri.remove(Integer.parseInt(pos));
        adapter.notifyDataSetChanged(uri);


    }

    public void choseImage(View view) {
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
        startActivityForResult(Intent.createChooser(intent,"Select Picture"), PICK_IMAGE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE && resultCode == Activity.RESULT_OK) {
                if (data.getClipData() != null) {
                    int count = data.getClipData().getItemCount();
                    for (int i = 0; i < count; i++) {
                        uri.add(data.getClipData().getItemAt(i).getUri());
                    }
                    adapter.notifyDataSetChanged(uri);
                }
        }
    }
    public void cancel(View view) {
        finish();
    }

    public void post(View view) {
        finish();
    }
    void getPost(){
        int postId = getIntent().getStringExtra(getString(R.string.post_id))==null?
                0:Integer.parseInt(getIntent().getStringExtra(getString(R.string.post_id)));
        if(postId!=0){
            Post post=new Post("content","amar","12-2-2012","https://homepages.cae.wisc.edu/~ece533/images/airplane.png",4,postId,3
        ,12,12,false,false);
        editText.setHint("Edit Post");
        editText.setText(post.getContent());
        uri.add(Uri.parse("https://homepages.cae.wisc.edu/~ece533/images/airplane.png"));
        uri.add(Uri.parse("https://homepages.cae.wisc.edu/~ece533/images/airplane.png"));
        uri.add(Uri.parse("https://homepages.cae.wisc.edu/~ece533/images/boat.png"));
        uri.add(Uri.parse("https://homepages.cae.wisc.edu/~ece533/images/airplane.png"));
        uri.add(Uri.parse("https://homepages.cae.wisc.edu/~ece533/images/boat.png"));
        uri.add(Uri.parse("https://homepages.cae.wisc.edu/~ece533/images/boat.png"));

            }
    }
}