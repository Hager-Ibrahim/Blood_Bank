package com.example.myapplication.view.activities;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.myapplication.R;
import com.example.myapplication.view.fragments.homeCycle.HomeFragment;
import com.example.myapplication.view.fragments.homeCycle.NotificationFragment;
import com.example.myapplication.view.fragments.homeCycle.SettingFragment;
import com.example.myapplication.view.fragments.homeCycle.UserFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class HomeActivity extends BasicActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        setDefaultLanguage("ar");
        openFragmentAsDefault(savedInstanceState, new HomeFragment(),R.id.home_fragment_container);
        navigationListener();
    }


    public void navigationListener(){
        final BottomNavigationView mBottomNavigationView = findViewById(R.id.home_bottom_navigation);
        mBottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

                Fragment selectedFragment = null;

                switch (menuItem.getItemId()){
                    case R.id.nav_home:
                        selectedFragment = new HomeFragment();
                        break;
                    case R.id.nav_user:
                        selectedFragment = new UserFragment();
                        break;
                    case R.id.nav_notifications:
                        selectedFragment = new NotificationFragment();
                        break;
                    case R.id.nav_more:
                        selectedFragment = new SettingFragment();
                }

                getSupportFragmentManager().beginTransaction().replace(
                        R.id.home_fragment_container, selectedFragment).commit();

                return true;
            }
        });
    }

}
