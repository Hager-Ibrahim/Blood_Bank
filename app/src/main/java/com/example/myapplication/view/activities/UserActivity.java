package com.example.myapplication.view.activities;

import android.os.Bundle;

import com.example.myapplication.R;
import com.example.myapplication.view.fragments.LoginFragment;

public class UserActivity extends BasicActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        setDefaultLanguage("ar");
        openFragmentAsDefault(savedInstanceState, new LoginFragment(),R.id.user_fragment_container);
        transcriptStatusBar();
    }




}
