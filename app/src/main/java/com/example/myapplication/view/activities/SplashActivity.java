package com.example.myapplication.view.activities;

import android.os.Bundle;

import com.example.myapplication.R;
import com.example.myapplication.view.fragments.SplashFragment;

public class SplashActivity extends BasicActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        setDefaultLangWithoutChangingDirec("ar");
        openFragmentAsDefault(savedInstanceState, new SplashFragment(), R.id.splash_fragment_container);
        transcriptStatusBar();
    }



}
