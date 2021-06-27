package com.web.app.Tallaba.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.web.app.Tallaba.Model.Comment;
import com.web.app.Tallaba.R;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class ReplyAd extends RecyclerView.Adapter<ReplyAd.myHolder> {
    ArrayList<Comment> list;

    public ReplyAd(ArrayList<Comment> list) {
        this.list = list;
    }

    @NonNull
    @NotNull
    @Override
    public myHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_reply,parent,false);
        return new myHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull myHolder holder, int position) {
        Comment comment=list.get(position);
        Glide.with(holder.itemView.getContext()).load(comment.getImage()).error(R.drawable.ic_launcher_background).into(holder.image);
        if(comment.isInstructor())
            holder.isInstructor.setVisibility(View.VISIBLE);

        if(comment.getIdUser()==2){
            holder.dots.setVisibility(View.VISIBLE);
            holder.dots.setTag(R.id.key_reply_id,comment.getIdComment());
            holder.dots.setTag(R.id.key_reply_content,comment.getContent());
        }
        holder.name.setText(comment.getName());
        holder.date.setText(comment.getDate());
        holder.content.setText(comment.getContent());


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    static class myHolder extends RecyclerView.ViewHolder {
        private TextView content,name,date,isInstructor;
        private ImageView image,dots;
        public myHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            content=itemView.findViewById(R.id.reply_content);
            name=itemView.findViewById(R.id.reply_name);
            date=itemView.findViewById(R.id.reply_date);
            isInstructor=itemView.findViewById(R.id.reply_is_instructor);
            image=itemView.findViewById(R.id.reply_image);
            dots= itemView.findViewById(R.id.reply_dots);

        }
    }
}
