package com.example.myapplication.view.fragments.homeCycle;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableLayout;

import com.example.myapplication.R;
import com.example.myapplication.adapters.TabsPagerAdapter;
import com.google.android.material.tabs.TabLayout;

public class HomeFragment extends Fragment {

    ViewPager mViewPager;
    TabLayout mTabLayout;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_home, container, false);

        mViewPager = view.findViewById(R.id.home_view_pager);
        mTabLayout = view.findViewById(R.id.home_tab_layout);

        loadArticlesViewPager();

        return view;
    }

    private void loadArticlesViewPager(){
        TabsPagerAdapter mTabsAdapter = new TabsPagerAdapter(getContext() , getChildFragmentManager());
        mViewPager.setAdapter(mTabsAdapter);
        mTabLayout.setupWithViewPager(mViewPager);


    }



}
