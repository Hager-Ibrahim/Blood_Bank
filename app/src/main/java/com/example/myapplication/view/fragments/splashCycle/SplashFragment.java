package com.example.myapplication.view.fragments.splashCycle;

import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.myapplication.R;
import com.example.myapplication.view.fragments.splashCycle.SliderFragment;

public class SplashFragment extends Fragment {

    private static int SPLASH_TIME = 4000;
    ImageView splashImage;
    TextView splashText;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_splash, container, false);
        splashImage = view.findViewById(R.id.splash_image);
        splashText = view.findViewById(R.id.splash_word);
        setAnimationAndHandler();
        return view;
    }

    private void setAnimationAndHandler(){

        Animation anim = AnimationUtils.loadAnimation(getActivity(), R.anim.splash_anim);
        splashImage.setAnimation(anim);
        splashText.startAnimation(anim);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

            getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.splash_fragment_container,
                            new SliderFragment()).commit();


            }
        }, SPLASH_TIME);
    }
}
