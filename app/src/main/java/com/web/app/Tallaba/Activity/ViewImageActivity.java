package com.web.app.Tallaba.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.web.app.Tallaba.Adapter.ViewPagesAd;
import com.web.app.Tallaba.R;

import java.util.ArrayList;

public class ViewImageActivity extends AppCompatActivity {
    ViewPager2 viewPager2;
    TabLayout tabLayout;
    ViewPagesAd viewPagesAd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_image);
        viewPager2=findViewById(R.id.viewPager2);
        tabLayout=findViewById(R.id.tab_layout);

        init(getIntent().getIntExtra(getString(R.string.Key_Image_conut),1));
    }
    void init(int count){

        ArrayList<String> list=new ArrayList<>();
        for (int i =0 ; i<count;i++){
            list.add("https://homepages.cae.wisc.edu/"+i);
        }
//        list.add("https://homepages.cae.wisc.edu/~ece533/images/baboon.png");
//        list.add("https://homepages.cae.wisc.edu/~ece533/images/boat.png");
//        list.add("https://homepages.cae.wisc.edu/~ece533/images/airplane.png");
//        list.add("https://homepages.cae.wisc.edu/~ece533/images/barbara.png");
//        list.add("https://homepages.cae.wisc.edu/~ece533/images/barbara.png");

        viewPagesAd = new ViewPagesAd(list);
        viewPager2.setOrientation(ViewPager2.ORIENTATION_HORIZONTAL);
        viewPager2.setAdapter(viewPagesAd);
        new TabLayoutMediator(tabLayout,viewPager2,(tab, position) -> {}).attach();

    }

}