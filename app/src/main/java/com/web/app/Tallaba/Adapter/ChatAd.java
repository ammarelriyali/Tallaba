package com.web.app.Tallaba.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.web.app.Tallaba.Model.CoursesContent;
import com.web.app.Tallaba.R;


import java.util.ArrayList;

public class ChatAd extends RecyclerView.Adapter<ChatAd.MyHolderChat> {
    ArrayList<CoursesContent> list;

    public ChatAd(ArrayList<CoursesContent> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public MyHolderChat onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_chat,parent,false);

        return new MyHolderChat(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolderChat holder, int position) {
        CoursesContent content= list.get(position);
        holder.date.setText(content.getDate());
        holder.content.setText(content.getContent());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    static class MyHolderChat extends RecyclerView.ViewHolder{
        TextView content,date;
        public MyHolderChat(@NonNull View itemView) {
            super(itemView);
            content=itemView.findViewById(R.id.chat_content);
            date=itemView.findViewById(R.id.chat_date);

        }
    }
}
