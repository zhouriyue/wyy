package com.gxuwz.beethoven.listener;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.viewpager.widget.ViewPager;

import com.gxuwz.beethoven.util.HttpUtils;

public class MainMenuPageChangeListener implements ViewPager.OnPageChangeListener {
    int pre;
    int currIndex;
    TextView[] textViews;
    ImageView myBg;
    ImageView leftMenu;
    ImageView search;
    Context context;

    public MainMenuPageChangeListener(TextView[] textViews, ImageView myBg, ImageView leftMenu,
                                      ImageView search,Context context) {
        this.textViews = textViews;
        this.myBg = myBg;
        this.leftMenu = leftMenu;
        this.search = search;
        this.context = context;
    }

    @Override
    public void onPageSelected(int arg0) {
        switch (arg0) {
            case 0:{
                for(int i = 0;i < textViews.length;i++) {
                    textViews[i].setTypeface(Typeface.defaultFromStyle(Typeface.NORMAL));
                    textViews[i].setTextColor(Color.parseColor("#ffffff"));
                }

                myBg.setImageBitmap(HttpUtils.getRes("my_bg",context));
                leftMenu.setImageBitmap(HttpUtils.getRes("view_my_list_whitk",context));
                search.setImageBitmap(HttpUtils.getRes("view_index_search_white",context));

                currIndex = arg0;
                textViews[currIndex].setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
                textViews[currIndex].setTextColor(Color.parseColor("#ffffff"));
            };break;
            default:{
                for(int i = 0;i < textViews.length;i++) {
                    textViews[i].setTypeface(Typeface.defaultFromStyle(Typeface.NORMAL));
                    textViews[i].setTextColor(Color.parseColor("#707070"));
                }

                myBg.setImageBitmap(HttpUtils.getRes("whick_bg",context));
                leftMenu.setImageBitmap(HttpUtils.getRes("view_index_list_black_515151",context));
                search.setImageBitmap(HttpUtils.getRes("view_index_search_black_515151",context));

                currIndex = arg0;
                textViews[currIndex].setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
                textViews[currIndex].setTextColor(Color.parseColor("#000000"));
            };break;
        }
    }

    @Override
    public void onPageScrolled(int arg0, float arg1, int arg2) {
    }

    @Override
    public void onPageScrollStateChanged(int arg0) {
    }
}
