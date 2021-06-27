package com.web.app.Tallaba.Adapter;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.bumptech.glide.Glide;
import com.google.android.material.card.MaterialCardView;
import com.web.app.Tallaba.Model.Post;
import com.web.app.Tallaba.R;
import com.web.app.Tallaba.utils.MyStaggeredGridLayoutManager;

import java.util.ArrayList;


public class RecPostAd extends RecyclerView.Adapter<RecPostAd.MyHolderPost>  {
    ArrayList<Post> posts;



    public RecPostAd(ArrayList<Post> posts){
        this.posts=posts;

    }


    @NonNull
    @Override
    public MyHolderPost onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_post_rec,parent,false);
        return new MyHolderPost(view);
    }


    @Override
    public void onBindViewHolder(@NonNull MyHolderPost holder, int position) {
        Post post= posts.get(position);

        if(post.getContent().length()==0){
            holder.content.setVisibility(View.GONE);
        }

        if (post.getIdUser()==2){
            holder.dots.setVisibility(View.VISIBLE);
            holder.dots.setTag(R.id.key_post_id,post.getIdPost());
        }
        if(post.isInstructor()){
            holder.isInstructor.setVisibility(View.VISIBLE);
        }
        if(post.isLiked()){
            holder.iconCountLike.setTextColor(ContextCompat.getColor(holder.recyclerView.getContext(),R.color.blue));
            holder.iconCountLike.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_like, 0, 0, 0);
        }
        Glide.with(holder.recyclerView.getContext()).load(post.getPhoto()).error(R.drawable.ic_person).into(holder.image);
        holder.content.setText(post.getContent());
        holder.date.setText(post.getDate());
        holder.name.setText(post.getName());
        holder.name.setTag(post.getIdUser());
        holder.image.setTag(post.getIdUser());
        holder.iconCountLike.setText(String.valueOf(post.getCountLike()));
        holder.iconCountLike.setTag(R.id.key_post_id,post.getIdPost());
        holder.iconCountLike.setTag(R.id.key_user_id,post.getIdUser());
        holder.comment.setText(String.valueOf(post.getCountComment()));
        holder.content.setTag(post.getIdPost());
        holder.materialCardView.setTag(post.getIdPost());

//        int size=post.getImageCount();
        int size=2;

        switch (size) {
            case 0:
                holder.recyclerView.setVisibility(View.GONE);
                break;

            case 1:
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(holder.recyclerView.getContext());
                linearLayoutManager.setInitialPrefetchItemCount(size);
                holder.recyclerView.setLayoutManager(linearLayoutManager);
                break;
            case 2:
                GridLayoutManager gridLayoutManager = new GridLayoutManager(holder.recyclerView.getContext(), 2);
                gridLayoutManager.setInitialPrefetchItemCount(size);
                holder.recyclerView.setLayoutManager(gridLayoutManager);
                break;
            default:
                MyStaggeredGridLayoutManager myStaggeredGridLayoutManager = new MyStaggeredGridLayoutManager(2, StaggeredGridLayoutManager.HORIZONTAL);
                holder.recyclerView.setLayoutManager(myStaggeredGridLayoutManager);
        }
        ArrayList<String> list=new ArrayList<>();
        list.add("https://homepages.cae.wisc.edu/~ece533/images/baboon.png");
        list.add("https://homepages.cae.wisc.edu/~ece533/images/boat.png");

        PostChildAd child = new PostChildAd(list);
        holder.recyclerView.setAdapter(child);
        RecyclerView.RecycledViewPool  viewPool= new RecyclerView.RecycledViewPool();
        holder.recyclerView.setRecycledViewPool(viewPool);
    }


    @Override
    public int getItemCount() {
        return posts.size();
    }



    static class MyHolderPost extends RecyclerView.ViewHolder{
        private RecyclerView recyclerView;
        private TextView content,name,date,comment,isInstructor,iconCountLike;
        private ImageView image,dots;
        MaterialCardView materialCardView;

        public MyHolderPost(@NonNull View itemView) {
            super(itemView);

            materialCardView= itemView.findViewById(R.id.card_view_posts);
            recyclerView=itemView.findViewById(R.id.list_post_Photo_rec);
            content=itemView.findViewById(R.id.post_content_rec);
            date=itemView.findViewById(R.id.post_date_rec);
            image=itemView.findViewById(R.id.post_image_rec);
            name=itemView.findViewById(R.id.post_name_rec);
            iconCountLike=itemView.findViewById(R.id.post_icon_count_like_rec);
            isInstructor=itemView.findViewById(R.id.post_is_instructor_rec);
            comment= itemView.findViewById(R.id.post_comment_rec);
            dots= itemView.findViewById(R.id.post_dots_rec);


        }
    }
}
