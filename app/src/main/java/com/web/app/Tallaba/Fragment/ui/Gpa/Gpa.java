package com.web.app.Tallaba.Fragment.ui.Gpa;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.web.app.Tallaba.Activity.BusActivity;
import com.web.app.Tallaba.Activity.HomeActivity;
import com.web.app.Tallaba.Adapter.BusAd;
import com.web.app.Tallaba.Adapter.RecGpaAd;
import com.web.app.Tallaba.Model.Bus;
import com.web.app.Tallaba.Model.Semester;
import com.web.app.Tallaba.Model.Subject;
import com.web.app.Tallaba.R;
import com.web.app.Tallaba.ViewModel.BusVM;
import com.web.app.Tallaba.ViewModel.GpaVM;

import java.util.ArrayList;

public class Gpa extends Fragment {

    RecyclerView recyclerView;
    SharedPreferences sharedPreferences;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View view =inflater.inflate(R.layout.gpa_fragment, container, false);
        ((HomeActivity) requireActivity()).getSupportActionBar().show();
        recyclerView= view.findViewById(R.id.rec_gap);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        sharedPreferences=getActivity().getPreferences(Context.MODE_PRIVATE);

        getGpaList();

        return view;
    }


    void getGpaList() {
        GpaVM vm = new ViewModelProvider(this).get(GpaVM.class);
        vm.getGpa(    sharedPreferences.getString(getString(R.string.userID),"0")
        );
        vm.mld.observe((LifecycleOwner) getContext(), new Observer<ArrayList<Semester>>() {
            @Override
            public void onChanged(ArrayList<Semester> models) {
                recyclerView.setAdapter(new RecGpaAd(models));
            }
        });
    }

}