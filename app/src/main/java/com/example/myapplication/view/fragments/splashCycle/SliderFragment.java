package com.example.myapplication.view.fragments.splashCycle;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;

import com.example.myapplication.R;
import com.example.myapplication.adapters.SliderAdapter;
import com.example.myapplication.view.activities.UserActivity;
import com.google.android.material.tabs.TabLayout;

public class SliderFragment extends Fragment {

    ViewPager mViewPager;
    SliderAdapter mSliderAdapter;
    TabLayout mTabLayout ;
    ImageView sliderNext , sliderButton , sliderDone;

    int[] sliderTexts =  {R.string.slider_text_1
            , R.string.slider_text_2
            ,R.string.slider_text_3};

    int [] sliderImages= {R.drawable.slider1
            ,R.drawable.slider2,
            R.drawable.slider1};

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view =  inflater.inflate(R.layout.fragment_slider, container, false);
        mTabLayout = view.findViewById(R.id.slider_tab_layout);
        mViewPager = view.findViewById(R.id.slider_view_pager);
        sliderNext = view.findViewById(R.id.slider_next);
        sliderDone = view.findViewById(R.id.slider_done);
        sliderButton = view.findViewById(R.id.slider_button);

        displayStatusBar();
        setViewPager();
        pagerSetting(view);

        return view;
    }


    private void displayStatusBar(){
        getActivity().getWindow().clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }

    private void setViewPager(){
        mSliderAdapter = new SliderAdapter(getActivity(), sliderImages, sliderTexts);
        mViewPager.setAdapter(mSliderAdapter);
        mTabLayout.setupWithViewPager(mViewPager);

    }

    private void pagerSetting(final View view){

        //Before swiping set go button to go to second tab
        goToTab(view, 0);
        mTabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener(){
            @Override
            public void onTabSelected(TabLayout.Tab tab){
                final int position = tab.getPosition();

                goToTab(view, position);

                if(position == 2){
                    sliderNext.setVisibility(View.INVISIBLE);
                    sliderDone.setVisibility(View.VISIBLE);
                    sliderButton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent intent = new Intent(getActivity(), UserActivity.class);
                            getActivity().startActivity(intent);
                        }
                    });
                }
                else{
                    sliderNext.setVisibility(View.VISIBLE);
                    sliderDone.setVisibility(View.INVISIBLE);
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    private void goToTab(View view , final int position){
        view.findViewById(R.id.slider_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mTabLayout.getTabAt(position+ 1).select();
            }
        });
    }

}
