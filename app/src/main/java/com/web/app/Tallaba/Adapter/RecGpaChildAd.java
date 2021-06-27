package com.web.app.Tallaba.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.web.app.Tallaba.Model.Subject;
import com.web.app.Tallaba.R;

import java.util.ArrayList;

public class RecGpaChildAd extends RecyclerView.Adapter<RecGpaChildAd.MyHolderGpaChild> {
    ArrayList<Subject> list;

    public RecGpaChildAd(ArrayList<Subject> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public MyHolderGpaChild onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_gpa_child,parent,false);
        return new MyHolderGpaChild(view) ;
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolderGpaChild holder, int position) {
        Subject subject= list.get(position);
        holder.name.setText(subject.getName());
        holder.mark.setText(subject.getMark());
        holder.code.setText(subject.getCode());
        holder.degree.setText(String.valueOf(subject.getDegree()));
        holder.creditHours.setText(String.valueOf(subject.getCreditHours()));

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    static class MyHolderGpaChild extends RecyclerView.ViewHolder{
        TextView name,creditHours,degree,code,mark;
        public MyHolderGpaChild(@NonNull View itemView) {
            super(itemView);
            name=itemView.findViewById(R.id.subject_name);
            code=itemView.findViewById(R.id.subject_code);
            creditHours=itemView.findViewById(R.id.subject_credit_hours);
            degree=itemView.findViewById(R.id.subject_degree);
            mark=itemView.findViewById(R.id.subject_mark);
        }
    }
}
