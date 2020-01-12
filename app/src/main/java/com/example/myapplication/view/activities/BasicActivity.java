package com.example.myapplication.view.activities;

import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.WindowManager;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.myapplication.R;
import com.example.myapplication.view.fragments.SplashFragment;

import java.util.Locale;

public class BasicActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }


    public void setDefaultLanguage(String lang){
        Resources res = this.getResources();
        DisplayMetrics dm = res.getDisplayMetrics();
        Configuration conf = res.getConfiguration();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            conf.setLocale(new Locale(lang)); // API 17+ only.
        } else {
            conf.locale = new Locale(lang);
        }
        res.updateConfiguration(conf, dm);
    }
    public void setDefaultLangWithoutChangingDirec(String lang) {
        Locale myLocale = new Locale(lang);
        Resources res = getResources();
        DisplayMetrics dm = res.getDisplayMetrics();
        Configuration conf = res.getConfiguration();
        conf.locale = myLocale;
        res.updateConfiguration(conf, dm);
    }

    public void transcriptStatusBar(){
        getWindow().setFlags(
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        );
    }

    public void openFragmentAsDefault(Object savedInstanceState, Fragment fragment , int containerId){

        //If the user rotate the device, it will also open splash fragment
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(containerId,
                    fragment).commit();
        }
    }


}
