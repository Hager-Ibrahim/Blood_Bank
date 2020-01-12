package com.example.myapplication.utilities;

import android.app.Activity;
import android.graphics.drawable.GradientDrawable;
import android.widget.ImageView;

import com.example.myapplication.R;

public class MostUsedMethods {


    public static void setShapeColor(ImageView view, Activity activity ,int color){
        GradientDrawable shape = (GradientDrawable) view.getDrawable();
        shape.setColor(activity.getResources().getColor(color));

    }
}
