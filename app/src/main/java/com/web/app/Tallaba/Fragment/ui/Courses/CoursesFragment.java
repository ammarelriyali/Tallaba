package com.web.app.Tallaba.Fragment.ui.Courses;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.web.app.Tallaba.Adapter.RecCoursesAd;

import com.web.app.Tallaba.R;
import com.web.app.Tallaba.Model.Courses;
import com.web.app.Tallaba.ViewModel.CoursesVm;

import java.util.ArrayList;


public class CoursesFragment extends Fragment {
    RecyclerView rec;
    SharedPreferences sharedPreferences;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_courses, container, false);
        sharedPreferences=getActivity().getPreferences(Context.MODE_PRIVATE);
        rec =root.findViewById(R.id.recyc_courese);
        rec.setLayoutManager(new LinearLayoutManager(getContext()));
        getCoursesList();

        rec.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (!recyclerView.canScrollVertically(1)) {
                    Toast.makeText(getContext(), "Last", Toast.LENGTH_LONG).show();

                }
            }
        });
       return root;
    }

    void getCoursesList() {
        CoursesVm vm = new ViewModelProvider(this).get(CoursesVm.class);
        vm.getCourses(    sharedPreferences.getString(getString(R.string.userID),"0"));
        vm.mld.observe((LifecycleOwner) getContext(), new Observer<ArrayList<Courses>>() {
            @Override
            public void onChanged(ArrayList<Courses> models) {
                rec.setAdapter(new RecCoursesAd(models));
            }
        });
    }




}