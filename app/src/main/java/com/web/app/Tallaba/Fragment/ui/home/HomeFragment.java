package com.web.app.Tallaba.Fragment.ui.home;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.card.MaterialCardView;
import com.web.app.Tallaba.Activity.HomeActivity;
import com.web.app.Tallaba.Activity.NewPostActivity;
import com.web.app.Tallaba.Activity.ProfileActivity;
import com.web.app.Tallaba.Adapter.RecPostAd;
import com.web.app.Tallaba.Model.Post;
import com.web.app.Tallaba.R;
import com.web.app.Tallaba.ViewModel.PostRecVM;

import java.util.ArrayList;

public class HomeFragment extends Fragment  {
    private RecyclerView recyclerView;
    MaterialCardView cardView;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_home, container, false);


        recyclerView=root.findViewById(R.id.recycler_home);
        cardView =root.findViewById(R.id.card_connection);

        if(!isOnline(root.getContext())){
            recyclerView.setVisibility(View.GONE);
            cardView.setVisibility(View.VISIBLE);
        }
        else {
            ((HomeActivity) requireActivity()).getSupportActionBar().show();
            setHasOptionsMenu(true);
            setPostList();
        }

        return root;
    }
    void setPostList(){
        PostRecVM vm = new ViewModelProvider(requireActivity()).get(PostRecVM.class);
        vm.getPosts();
        vm.mld.observe(getViewLifecycleOwner(), new Observer<ArrayList<Post>>() {
            @Override
            public void onChanged(ArrayList<Post> models) {
                RecPostAd adapterHome= new RecPostAd(models);
                recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                recyclerView.setAdapter(adapterHome);
            }
        });


    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.home_menu,menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.new_post_menu_item_home:
                startActivity(new Intent(getContext(), NewPostActivity.class));
                return true;
            case R.id.profile_menu_item_home:
                startActivity(new Intent(getContext(), ProfileActivity.class));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        if(!isOnline(getContext())){
            recyclerView.setVisibility(View.GONE);
            cardView.setVisibility(View.VISIBLE);
        }
        else {
            ((HomeActivity) requireActivity()).getSupportActionBar().show();
            setHasOptionsMenu(true);
            setPostList();

        }
    }

    public boolean isOnline(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        return (netInfo != null && netInfo.isConnected());
    }
}