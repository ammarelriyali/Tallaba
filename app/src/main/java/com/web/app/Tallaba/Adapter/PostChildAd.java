package com.web.app.Tallaba.Adapter;

import android.app.Activity;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.bumptech.glide.Glide;
import com.web.app.Tallaba.R;

import java.util.ArrayList;


public class PostChildAd extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    ArrayList<String>list;

    public PostChildAd(ArrayList<String> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        RecyclerView.ViewHolder viewHolder=null;
        View view;
        switch (viewType) {
            //2,3
            case 0:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_post_child_recycl, parent, false);
                viewHolder = new Holder(view);
                break;
            //1
            case 1:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_post_child_recycl, parent, false);
                Holder holder = new Holder(view);
                DisplayMetrics displaymetrics = new DisplayMetrics();
                ((Activity) parent.getContext()).getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
                holder.image.getLayoutParams().width = displaymetrics.widthPixels;
                viewHolder = holder;
                break;
            //3...
            case -1:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_post_child_recycl_footer, parent, false);
                viewHolder = new Footer(view);
                break;
        }
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        String imagePath=list.get(position);
        final StaggeredGridLayoutManager.LayoutParams layoutParams = new StaggeredGridLayoutManager.LayoutParams(
                        holder.itemView.getLayoutParams());
        layoutParams.setFullSpan(position==0);
        holder.itemView.setLayoutParams(layoutParams);

        if(holder instanceof  Holder) {
            ((Holder) holder).image.setTag(imagePath);
            Glide.with(holder.itemView.getContext()).load(imagePath).error(R.drawable.ic_launcher_background).into(((Holder) holder).image);
        }
        if(holder instanceof Footer){
            int number= list.size()-3;
            ((Footer) holder).countImage.setText("+"+number);
            Glide.with(holder.itemView.getContext()).load(imagePath).error(R.drawable.ic_launcher_background).into(((Footer) holder).image);
            ((Footer) holder).image.setTag(imagePath);
        }

    }

    @Override
    public int getItemCount() {
        if(list.size()>3)
            return 3;
        return list.size();
    }

    @Override
    public int getItemViewType(int position) {
        //size=4...
        if (position == 2 && list.size() > 3)
                return  -1;
        //size=1
        else if (list.size()==1)
            return 1;
        //size=2,3
        else
            return 0;
    }
    static class Holder extends RecyclerView.ViewHolder{
        private ImageView image;
        public Holder(@NonNull View itemView) {
            super(itemView);
            image= itemView.findViewById(R.id.image_item_recycler_home);
        }
    }
    static class Footer extends RecyclerView.ViewHolder{
        private ImageView image;
        private TextView countImage;
        public Footer(@NonNull View itemView) {
            super(itemView);
            countImage =itemView.findViewById(R.id.text_view_number_image_footer);
            image=itemView.findViewById(R.id.footer_image);
        }
    }
}

