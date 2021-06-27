package com.web.app.Tallaba.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.web.app.Tallaba.Model.Semester;
import com.web.app.Tallaba.R;

import java.util.ArrayList;

public class RecGpaAd extends RecyclerView.Adapter<RecGpaAd.MyHolderGpa> {
ArrayList<Semester> list ;

    public RecGpaAd(ArrayList<Semester> list) {
        this.list = list;
    }

    private RecyclerView.RecycledViewPool  viewPool= new RecyclerView.RecycledViewPool();

    @NonNull
    @Override
    public MyHolderGpa onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_gpa,parent,false);
        return new MyHolderGpa(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolderGpa holder, int position) {
        Semester semester = list.get(position);
        holder.name.setText(semester.getName());
        holder.mark.setText(semester.getMark());
        holder.gpa.setText(String.valueOf(semester.getGpa()));
        holder.totalGpa.setText(String.valueOf(semester.getTotalGpa()));
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(holder.rec.getContext()
                ,RecyclerView.HORIZONTAL,false);
        linearLayoutManager.setInitialPrefetchItemCount(semester.getList().size());
        holder.rec.setLayoutManager(linearLayoutManager);
        holder.rec.setAdapter(new RecGpaChildAd(semester.getList()));
        holder.rec.setRecycledViewPool(viewPool);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class MyHolderGpa extends RecyclerView.ViewHolder {
        TextView name,gpa,totalGpa,mark;
        RecyclerView rec;
        public MyHolderGpa(@NonNull View itemView) {
            super(itemView);
            rec =itemView.findViewById(R.id.rec_gpa_child);
            name= itemView.findViewById(R.id.semester_name);
            gpa= itemView.findViewById(R.id.semester_gpa);
            totalGpa=itemView.findViewById(R.id.semester_total_gpa);
            mark= itemView.findViewById(R.id.semester_mark);
        }
    }
}