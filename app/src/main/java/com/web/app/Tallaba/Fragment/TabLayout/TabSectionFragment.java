package com.web.app.Tallaba.Fragment.TabLayout;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.web.app.Tallaba.Adapter.RecLectureAd;
import com.web.app.Tallaba.Model.Lecture;
import com.web.app.Tallaba.R;
import com.web.app.Tallaba.ViewModel.SectionVM;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;

public class TabSectionFragment extends Fragment {
    RecyclerView recyclerView;
    SharedPreferences sharedPreferences;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root =inflater.inflate(R.layout.fragment_tab_section, container, false);
        recyclerView=root.findViewById(R.id.rec_section);
        sharedPreferences=getActivity().getPreferences(Context.MODE_PRIVATE);

        getSectionList();
        return root;

    }
    void getSectionList() {
        SectionVM vm = new ViewModelProvider(this).get(SectionVM.class);
        vm.getSection(sharedPreferences.getString(getString(R.string.userID),"0"));
        vm.mld.observe((LifecycleOwner) getContext(), new Observer<ArrayList<Lecture>>() {
            @Override
            public void onChanged(ArrayList<Lecture> models) {
                sortList(models);
            }
        });
    }
    void sortList(ArrayList<Lecture> list){
        Collections.sort(list,(Lecture o1, Lecture o2)->{
            int ageCompare = o1.getIdDay() - o2.getIdDay();
            DateFormat df = new SimpleDateFormat("hh:mm aa");
            int dateCompare =0;
            try {
                Date d1 = df.parse(o1.getStartTime());
                Date d2 = df.parse(o2.getStartTime());
                dateCompare=d1.compareTo(d2);
            } catch (ParseException e) {
                e.printStackTrace();
            }

            return (ageCompare==0)?dateCompare:ageCompare;
        });

        for (int i = 0; i < list.size(); i++) {
            if (i == 0)
                list.get(i).setHeader(true);
            else if (!list.get(i).getDay().equals(list.get(i - 1).getDay()))
                list.get(i).setHeader(true);
        }

        recyclerView.setAdapter(new RecLectureAd(list));
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

    }

}