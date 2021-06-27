
package com.web.app.Tallaba.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.PopupMenu;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.web.app.Tallaba.Adapter.CommentAd;
import com.web.app.Tallaba.Adapter.PostChildAd;
import com.web.app.Tallaba.Model.Post;
import com.web.app.Tallaba.R;
import com.web.app.Tallaba.ViewModel.PostVM;
import com.web.app.Tallaba.utils.MyLinearLayoutManager;
import com.web.app.Tallaba.utils.MyStaggeredGridLayoutManager;

import java.util.ArrayList;

public class PostActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    EditText comment;
    ImageView profile,dots,image;

    TextView name,date,isInstructor,content,iconCountLike;
    Post post;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);

        comment = findViewById(R.id.text_commnet_view_post);
        recyclerView = findViewById(R.id.recycler_image_view_post);
        profile= findViewById(R.id.post_image);

        image=findViewById(R.id.reply_image);
//        Glide.with(this).load("https://github.com/bumptech/glide").
//                into(image);
        dots=findViewById(R.id.post_dots);
        name=findViewById(R.id.post_name);
        date=findViewById(R.id.post_date);
        isInstructor=findViewById(R.id.post_is_instructor);
        content=findViewById(R.id.post_content);
        iconCountLike=findViewById(R.id.post_icon_count_like);
        String idPost = getIntent().getStringExtra(getString(R.string.post_id));
        if(!idPost.equals(null))
            getPost(idPost);
        requestFocusComment();
    }
    void passData(Post post){
        Glide.with(this).load(post.getPhoto()).into(profile);
        if(post.getIdUser()==2){
            dots.setVisibility(View.VISIBLE);
        }
        if(post.isInstructor()){
            isInstructor.setVisibility(View.VISIBLE);
        }
        if(post.isLiked())
        {
            iconCountLike.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_like, 0, 0, 0);
            iconCountLike.setTextColor(ContextCompat.getColor(PostActivity.this, R.color.blue));
        }
        name.setText(post.getName());
        date.setText(post.getDate());
        iconCountLike.setText(String.valueOf(post.getCountLike()));
        content.setText(post.getContent());
        setPhoto(post);
        setComment(post);
    }
    void setComment(Post post){
        RecyclerView recyclerView= findViewById(R.id.recycl_comment_post);
        recyclerView.setLayoutManager(new MyLinearLayoutManager(this));
        recyclerView.setAdapter(new CommentAd(post.getList()));
    }

    void setPhoto(Post post){
        int size=post.getImageCount();
        if(size==0){
            recyclerView.setVisibility(View.GONE);
        }
        if(size==2) {
            recyclerView.setLayoutManager(new GridLayoutManager(this,2));
        }
        else if(size==1) {

            recyclerView.setLayoutManager(new LinearLayoutManager(this));
        }
        else {
            MyStaggeredGridLayoutManager staggeredGridLayoutManager= new MyStaggeredGridLayoutManager(2, StaggeredGridLayoutManager.HORIZONTAL);
            recyclerView.setLayoutManager(staggeredGridLayoutManager);
        }
        ArrayList<String> list=new ArrayList<>();
        for (int i =0 ; i<post.getImageCount();i++){
            list.add("https://homepages.cae.wisc.edu/"+i);
        }
//        list.add("https://homepages.cae.wisc.edu/~ece533/images/baboon.png");
//        list.add("https://homepages.cae.wisc.edu/~ece533/images/boat.png");
//        list.add("https://homepages.cae.wisc.edu/~ece533/images/airplane.png");
//        list.add("https://homepages.cae.wisc.edu/~ece533/images/barbara.png");
//        list.add("https://homepages.cae.wisc.edu/~ece533/images/barbara.png");
        recyclerView.setAdapter(new PostChildAd(list));
    }

    void requestFocusComment(){
        String action =getIntent().getStringExtra(getString(R.string.Comment));
        if (action.equals("comment")){
            comment.requestFocus();
        }
    }
    void requestFocusComment(String txt){
        comment.setText(txt);
        if(!comment.hasFocus()) {
            comment.requestFocus();
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
        finish();
        return super.onOptionsItemSelected(item);
    }

    public void openImageView(View view) {
        Intent intent= new Intent(this,ViewImageActivity.class);
        intent.putExtra(getString(R.string.Key_Image_conut),post.getImageCount());
        startActivity(intent);
    }

    public void like(View view) {


        TextView  txt= (TextView)view;
        Drawable[] drawables = txt.getCompoundDrawables();
        Drawable leftCompoundDrawable = drawables[0];
        Drawable drawable=ContextCompat.getDrawable(this,R.drawable.ic_like_empty);

        boolean status =leftCompoundDrawable.getConstantState().equals(drawable != null ? drawable.getConstantState() : null);

        String text= iconCountLike.getText().toString();
        int  number= Integer.parseInt(text);
        if (status) {

            txt.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_like, 0, 0, 0);
            txt.setTextColor(ContextCompat.getColor(PostActivity.this, R.color.blue));
            txt.setText(String.valueOf(number+1));
        }
        else {
            txt.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_like_empty, 0, 0, 0);
            txt.setTextColor(ContextCompat.getColor(PostActivity.this, R.color.black));
            txt.setText(String.valueOf(number-1));

        }
    }

    public void openMenuComment(View view) {
        //Creating the instance of PopupMenu
        PopupMenu popup = new PopupMenu(PostActivity.this, view);
        //Inflating the Popup using xml file
        popup.getMenuInflater()
                .inflate(R.menu.popup, popup.getMenu());

        popup.setOnMenuItemClickListener(item -> {
            switch (item.getItemId()){
                case R.id.delete:
                    Toast.makeText(this, "delete"+view.getTag(R.id.key_comment_id), Toast.LENGTH_SHORT).show();
                    break;
                case R.id.edit:
                    requestFocusComment((String) view.getTag(R.id.key_comment_content));
                    break;
            }
            return true;
        });
        popup.show();
    }

    public void viewReplies(View view) {
        Intent intent = new Intent(PostActivity.this, ReplyActivity.class);
        intent.putExtra(getString(R.string.Comment), view.getTag().toString());        startActivity(intent);
    }

    public void reply(View view) {
        Intent intent = new Intent(PostActivity.this, ReplyActivity.class);
        intent.putExtra(getString(R.string.Reply), "reply");
        intent.putExtra(getString(R.string.Comment), view.getTag().toString());
        startActivity(intent);
    }

    public void openMenuPost(View view) {
        PopupMenu popup = new PopupMenu(PostActivity.this, view);
        //Inflating the Popup using xml file
        popup.getMenuInflater()
                .inflate(R.menu.popup, popup.getMenu());

        //registering popup with OnMenuItemClickListener
        popup.setOnMenuItemClickListener(item -> {
            Toast.makeText(
                    PostActivity.this,
                    "You Clicked : " + item.getTitle(),
                    Toast.LENGTH_SHORT
            ).show();
            return true;
        });
        popup.show();
    }

    public void openProfileUser(View view) {
        Intent intent = new Intent(this,ProfileUserActivity.class);
        intent.putExtra(getString(R.string.userID),view.getTag().toString());
        startActivity(intent);

    }
    void getPost(String idPost) {
        PostVM vm = new ViewModelProvider(this).get(PostVM.class);
        vm.getPost(idPost);
        vm.mld.observe(this, new Observer<Post>() {
            @Override
            public void onChanged(Post models) {
                passData(models);
                post=models;
            }
        });
    }
}