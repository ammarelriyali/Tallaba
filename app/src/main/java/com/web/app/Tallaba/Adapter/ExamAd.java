package com.web.app.Tallaba.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.web.app.Tallaba.Model.Lecture;
import com.web.app.Tallaba.R;

import java.util.ArrayList;

public class ExamAd  extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    ArrayList<Lecture> lectures;

    public ExamAd(ArrayList<Lecture> lectures) {
        this.lectures = lectures;
    }
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view;
        if (viewType== -1){
            view= LayoutInflater.from(parent.getContext()).
                    inflate(R.layout.item_lecture, parent,false);
             MyHeader myHeader = new  MyHeader(view);
            return myHeader;
        }
        else {
            view = LayoutInflater.from(parent.getContext()).
                    inflate(R.layout.item_lecture_child, parent, false);
             MyHolderExam myHolderLecture =new MyHolderExam(view);
            return myHolderLecture;
        }
    }



    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Lecture lecture= lectures.get(position);
        if(holder instanceof  MyHeader){
            MyHeader myHeader = (MyHeader) holder;
            myHeader.content.setText(lecture.getCourses());
            myHeader.hall.setText(lecture.getHall());
            myHeader.startTime.setText(lecture.getStartTime());
            myHeader.endTime.setText(lecture.getEndTime());
            myHeader.header.setText(lecture.getDate());
        }
        else {
            MyHolderExam holderLecture = (MyHolderExam) holder;
            holderLecture.content.setText(lecture.getCourses());
            holderLecture.hall.setText(lecture.getHall());
            holderLecture.startTime.setText(lecture.getStartTime());
            holderLecture.endTime.setText(lecture.getEndTime());
        }
    }



    @Override
    public int getItemCount() {

        return lectures.size();
    }


    static class MyHolderExam extends RecyclerView.ViewHolder{
        TextView content,hall,startTime,endTime;

        public MyHolderExam(@NonNull View itemView) {
            super(itemView);
            hall=itemView.findViewById(R.id.lecture_hall);
            startTime=itemView.findViewById(R.id.lecture_start_time);
            endTime=itemView.findViewById(R.id.lecture_end_time);
            content=itemView.findViewById(R.id.lecture_content);
        }
    }
    static class MyHeader extends RecyclerView.ViewHolder{
        TextView content, header,hall,startTime,endTime;
        public MyHeader(@NonNull View itemView) {
            super(itemView);
            header =itemView.findViewById(R.id.lecture_day_txt);
            hall=itemView.findViewById(R.id.lecture_hall_txt);
            startTime=itemView.findViewById(R.id.lecture_start_time_txt);
            endTime=itemView.findViewById(R.id.lecture_end_time_txt);
            content=itemView.findViewById(R.id.lecture_content_txt);
        }
    }


    @Override
    public int getItemViewType(int position) {
        Lecture lecture= lectures.get(position);

        if(lecture.isHeader())
            return -1;
        else
            return 1;

    }
}
