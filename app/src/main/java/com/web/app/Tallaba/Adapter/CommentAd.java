package com.web.app.Tallaba.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.web.app.Tallaba.Model.Comment;
import com.web.app.Tallaba.R;

import java.util.ArrayList;

public class CommentAd extends RecyclerView.Adapter<CommentAd.MyHolder> {
    ArrayList<Comment> list;
    public CommentAd(ArrayList<Comment> list){
        this.list=list;
    }
    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_comment_post,parent,false);
        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder holder, int position) {
        Comment comment = list.get(position);
        if(comment.isInstructor()){
            holder.isInstructor.setVisibility(View.VISIBLE);
        }
        if(comment.getCountReply()==0){
            holder.textReply.setVisibility(View.GONE);
            holder.countReply.setVisibility(View.GONE);
        }
        if(comment.getIdUser()==2){
            holder.dots.setVisibility(View.VISIBLE);
            holder.dots.setTag(R.id.key_comment_id,comment.getIdComment());
            holder.dots.setTag(R.id.key_comment_content,comment.getContent());
        }

        holder.name.setText(comment.getName());
        holder.name.setTag(comment.getIdComment());
        holder.profile.setTag(comment.getIdComment());
        holder.date.setText(comment.getDate());
        holder.content.setText(comment.getContent());
        holder.countReply.setText("("+String.valueOf(comment.getCountReply())+")");
        holder.replyLayout.setTag(comment.getIdComment());
        holder.viewReplyLayout.setTag(comment.getIdComment());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    static class MyHolder extends RecyclerView.ViewHolder{
        ImageView profile,dots;
        TextView name,date,isInstructor,content,textReply,countReply;
        LinearLayout replyLayout,viewReplyLayout;
        public MyHolder(@NonNull View itemView) {
            super(itemView);
            replyLayout=itemView.findViewById(R.id.comment_Reply);
            viewReplyLayout=itemView.findViewById(R.id.comment_view_reply);
            name=itemView.findViewById(R.id.comment_name);
            date=itemView.findViewById(R.id.comment_date);
            isInstructor=itemView.findViewById(R.id.comment_is_instructor);
            content=itemView.findViewById(R.id.comment_content);
            textReply=itemView.findViewById(R.id.comment_text_reply);
            countReply=itemView.findViewById(R.id.comment_count_reply);
            profile=itemView.findViewById(R.id.comment_image);
            dots=itemView.findViewById(R.id.comment_dots);
        }
    }
}
