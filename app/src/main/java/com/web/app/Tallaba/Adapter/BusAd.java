package com.web.app.Tallaba.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.web.app.Tallaba.Model.Bus;
import com.web.app.Tallaba.R;

import java.util.ArrayList;

public class BusAd extends RecyclerView.Adapter<BusAd.MyHolderBus> {
    private ArrayList<Bus> list;

    public BusAd(ArrayList<Bus> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public MyHolderBus onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_bus,parent,false);
        return new MyHolderBus(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolderBus holder, int position) {
        Bus bus=list.get(position);
        holder.startTime.setText(bus.getStartTime());
        holder.endTime .setText(bus.getEndTime());
        holder.direction.setText(bus.getDirection());
        holder.alarm.setTag(R.id.key_hour,bus.getHour());
        holder.alarm.setTag(R.id.key_munit,bus.getMunit());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    static class MyHolderBus extends RecyclerView.ViewHolder {
        private TextView direction,startTime,endTime;
        private ImageView alarm;
        public MyHolderBus(@NonNull View itemView) {
            super(itemView);
            direction = itemView.findViewById(R.id.bus_destination);
            endTime = itemView.findViewById(R.id.bus_end_time);
            startTime = itemView.findViewById(R.id.bus_start_time);
            alarm= itemView.findViewById(R.id.bus_alarm);

        }
    }

}
