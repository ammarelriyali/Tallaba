package com.web.app.Tallaba.Fragment.ui.Timetable;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.web.app.Tallaba.Fragment.TabLayout.TabExamFragment;
import com.web.app.Tallaba.Fragment.TabLayout.TabLectureFragment;
import com.web.app.Tallaba.Fragment.TabLayout.TabSectionFragment;
import com.web.app.Tallaba.R;

public class TimetableFragment extends Fragment {

    private String[] titles = new String[]{"Lecture", "section", "Exam"};

    ViewPager2 viewPager2;
    TabLayout tabLayout;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_timetable, container, false);
        viewPager2 = root.findViewById(R.id.view_pager_courses);
        tabLayout = root.findViewById(R.id.tab_layout_courses);




        init();


        return root;
    }



    private void init() {

        viewPager2.setAdapter(new VPFA(getActivity()));

        new TabLayoutMediator(tabLayout, viewPager2,
                (tab, position) -> tab.setText(titles[position])).attach();

    }

    private class VPFA extends FragmentStateAdapter {

        public VPFA(@NonNull FragmentActivity fragmentActivity) {
            super(fragmentActivity);
        }

        @NonNull
        @Override
        public Fragment createFragment(int position) {
            switch (position) {
                case 0:
                    return new TabLectureFragment();
                case 1:
                    return new TabSectionFragment();
                case 2:
                    return new TabExamFragment();
            }
            return new TabLectureFragment();
        }

        @Override
        public int getItemCount() {
            return titles.length;
        }
    }

}