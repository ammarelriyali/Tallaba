package com.web.app.Tallaba.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.web.app.Tallaba.R;
import com.web.app.Tallaba.Model.Courses;

import java.util.ArrayList;

public class RecCoursesAd extends RecyclerView.Adapter<RecCoursesAd.MyHolder> {
    ArrayList<Courses> list;

    public RecCoursesAd(ArrayList<Courses> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_courses,parent,false);

        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder holder, int position) {
        Courses courses= list.get(position);
        if(courses.isNotFileManager())
            holder.dotsFileManager.setVisibility(View.VISIBLE);
        if(courses.isNotChat())
            holder.dotsChat.setVisibility(View.VISIBLE);
        holder.name.setText(courses.getName());
        holder.instructor.setText(courses.getInstructor());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class MyHolder extends RecyclerView.ViewHolder{
        TextView name,instructor;
        ImageView dotsFileManager,dotsChat;
        public MyHolder(@NonNull View itemView) {
            super(itemView);
           name= itemView.findViewById(R.id.course_name);
           instructor= itemView.findViewById(R.id.course_instructor);
           dotsChat=itemView.findViewById(R.id.dots_chat);
           dotsFileManager=itemView.findViewById(R.id.dots_file_manager);
        }
    }
}
