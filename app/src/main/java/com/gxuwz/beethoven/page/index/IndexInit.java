package com.gxuwz.beethoven.page.index;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import androidx.viewpager.widget.ViewPager;

import com.gxuwz.beethoven.R;

import java.util.ArrayList;

public class IndexInit {
    Context context;

    LayoutInflater inflater;
    TextView[] textViews;
    ViewPager viewPager;
    TextView my;
    TextView find;
    TextView cloud;
    TextView video;
    ArrayList<View> pageview;

    View View_My;
    View view_find;
    View view_cloud;
    View view_video;

    private void init() {
        View_My = inflater.inflate(R.layout.activity_my, null);
        view_find = inflater.inflate(R.layout.activity_find, null);
        view_cloud = inflater.inflate(R.layout.activity_cloud, null);
        view_video = inflater.inflate(R.layout.activity_video, null);

        pageview =new ArrayList<View>();
        //添加想要切换的界面
        pageview.add(View_My);
        pageview.add(view_find);
        pageview.add(view_cloud);
        pageview.add(view_video);

        textViews = new TextView[pageview.size()];
    }

}
