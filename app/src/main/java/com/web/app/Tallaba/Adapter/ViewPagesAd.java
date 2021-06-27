package com.web.app.Tallaba.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.bumptech.glide.Glide;
import com.web.app.Tallaba.R;

import java.util.ArrayList;

public class ViewPagesAd extends RecyclerView.Adapter<ViewPagesAd.MyViewHolder> {
    private ArrayList<String> list;
    public ViewPagesAd(ArrayList<String> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_viewpages, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Glide.with(holder.itemView.getContext()).load(list.get(position)).error(R.drawable.ic_launcher_background).into(holder.image);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        ImageView image;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            image=itemView.findViewById(R.id.myZoomageView);
        }
    }
}