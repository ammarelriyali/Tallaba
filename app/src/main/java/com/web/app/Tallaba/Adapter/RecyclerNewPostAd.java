package com.web.app.Tallaba.Adapter;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.web.app.Tallaba.R;

import java.util.ArrayList;

public class RecyclerNewPostAd extends RecyclerView.Adapter<RecyclerNewPostAd.MyHolder> {
    ArrayList<Uri> list;
    Context context;

    public RecyclerNewPostAd(ArrayList<Uri> list){
        this.list=list;
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        context= parent.getContext();
        if (list.size() == 0||viewType==0) {
            view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_new_post_header,parent,false);
        } else {
            view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_new_post,parent,false);
        }
        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder holder, int position) {
    //الposition هيزيد واحد
        if (getItemViewType(position)!=0){
            holder.imageView.setTag(R.id.key_image_remove,position-1);
            Glide.with(context).load(list.get(position-1)).centerCrop().into(holder.imageView2);
        }
    }

    @Override
    public int getItemCount() {
        if (list.size() == 0) {
            return 1;
        } else {
            return list.size()+1;
        }
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    static class MyHolder extends RecyclerView.ViewHolder{
        ImageView imageView;
        ImageView imageView2;
        public MyHolder(@NonNull View itemView) {
            super(itemView);
            imageView=itemView.findViewById(R.id.image_remove_image_post);
            imageView2=itemView.findViewById(R.id.image_new_post);
        }
    }
    public void notifyDataSetChanged(ArrayList<Uri> list){
        this.list = list;
        notifyDataSetChanged();
    }
}
