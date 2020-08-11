package com.gxuwz.beethoven.listener.cloud;

import android.graphics.Color;
import android.graphics.Typeface;
import android.view.View;
import android.widget.TextView;

import androidx.viewpager.widget.ViewPager;

import java.util.ArrayList;

public class CloudMenuChangerListener implements ViewPager.OnPageChangeListener {
    TextView[] wordsTitle;

    public CloudMenuChangerListener(TextView[] wordsTitle) {
        this.wordsTitle = wordsTitle;
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        switch (position) {
            case 0:{
                wordsTitle[0].setTextColor(Color.parseColor("#ff3c3d"));
                wordsTitle[0].setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
                wordsTitle[1].setTextColor(Color.parseColor("#000000"));
                wordsTitle[1].setTypeface(Typeface.defaultFromStyle(Typeface.NORMAL));
            };break;
            case 1:{
                wordsTitle[0].setTextColor(Color.parseColor("#000000"));
                wordsTitle[0].setTypeface(Typeface.defaultFromStyle(Typeface.NORMAL));
                wordsTitle[1].setTextColor(Color.parseColor("#ff3c3d"));
                wordsTitle[1].setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
            };break;
        }
    }

    @Override
    public void onPageSelected(int position) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
