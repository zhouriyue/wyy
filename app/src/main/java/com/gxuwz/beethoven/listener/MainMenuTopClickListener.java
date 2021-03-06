package com.gxuwz.beethoven.listener;


import android.content.Context;
import android.content.Intent;
import android.view.View;

import androidx.viewpager.widget.ViewPager;

import com.gxuwz.beethoven.R;
import com.gxuwz.beethoven.receiver.IndexBottomBarReceiver;
import com.gxuwz.beethoven.service.MusicService;
import com.gxuwz.beethoven.util.Player;

public class MainMenuTopClickListener implements View.OnClickListener {
    ViewPager viewPager;
    Context context;

    public MainMenuTopClickListener(ViewPager viewPager, Context context) {
        this.viewPager = viewPager;
        this.context = context;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.my_menu:
                //点击"我的“时切换到第一页
                viewPager.setCurrentItem(0);
                break;
            case R.id.find_menu:
                //点击“发现”时切换的第二页
                viewPager.setCurrentItem(1);
                break;
            case R.id.cloud_menu:
                //点击“云村”时切换的第二页
                viewPager.setCurrentItem(2);
                break;
            case R.id.video_menu:
                //点击“云村”时切换的第二页
                viewPager.setCurrentItem(3);
                break;
        }

    }
}
