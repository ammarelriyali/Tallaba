package com.web.app.Tallaba.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.web.app.Tallaba.Model.CoursesContent;
import com.web.app.Tallaba.R;

import java.util.ArrayList;

public class FileManagerAd extends RecyclerView.Adapter<FileManagerAd.MyHolderFile> {
    private ArrayList<CoursesContent> list;

    public FileManagerAd(ArrayList<CoursesContent> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public MyHolderFile onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_filemanager,parent,false);
        return new MyHolderFile(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolderFile holder, int position) {
        CoursesContent content= list.get(position);
        holder.content.setText(content.getContent());
        holder.date.setText(content.getDate());
        holder.download.setTag(content.getPath());


    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    static class MyHolderFile extends RecyclerView.ViewHolder{
        private TextView content,date;
        private ImageView download;
        public MyHolderFile(@NonNull View itemView) {
            super(itemView);
            content= itemView.findViewById(R.id.file_name);
            date=itemView.findViewById(R.id.file_date);
            download=itemView.findViewById(R.id.file_download);


        }
    }
}
