package com.gxuwz.beethoven.adapter.my.localmusic;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import java.util.List;

public class LocalMusicTopMenuAdapter extends PagerAdapter {

    List<View> pageview;

    public LocalMusicTopMenuAdapter() {
    }

    public LocalMusicTopMenuAdapter(List<View> pageview) {
        this.pageview = pageview;
    }

    @Override
    //获取当前窗体界面数
    public int getCount() {
        // TODO Auto-generated method stub
        return pageview.size();
    }

    @Override
    //判断是否由对象生成界面
    public boolean isViewFromObject(View arg0, Object arg1) {
        // TODO Auto-generated method stub
        return arg0==arg1;
    }
    //使从ViewGroup中移出当前View
    public void destroyItem(View arg0, int arg1, Object arg2) {
        ((ViewPager) arg0).removeView(pageview.get(arg1));
    }

    //返回一个对象，这个对象表明了PagerAdapter适配器选择哪个对象放在当前的ViewPager中
    public Object instantiateItem(View arg0, int arg1){
        ((ViewPager)arg0).addView(pageview.get(arg1));
        return pageview.get(arg1);
    }
}
