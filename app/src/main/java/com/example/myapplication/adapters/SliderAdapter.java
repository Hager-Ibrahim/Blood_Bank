package com.example.myapplication.adapters;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.example.myapplication.R;


public class SliderAdapter extends PagerAdapter {


    int[] sliderImages ;
    int[] sliderTexts;
    Context context;
    LayoutInflater mLayoutInflater;

    public SliderAdapter(Context context ,int[] sliderImages , int[] sliderTexts) {
        this.context = context;
        this.sliderImages = sliderImages;
        this.sliderTexts = sliderTexts;
        mLayoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return sliderImages.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {

        View itemView = mLayoutInflater.inflate(R.layout.item_slider,container,false);
        ImageView sliderImage = itemView.findViewById(R.id.slider_item_image);
        TextView sliderText = itemView.findViewById(R.id.slider_item_text);

        sliderImage.setImageResource(sliderImages[position]);
        sliderText.setText(sliderTexts[position]);

        container.addView(itemView);
        return itemView;
    }

    @Override

    public void destroyItem(ViewGroup container, int position, Object object) {

        container.removeView((LinearLayout) object);

    }
}
