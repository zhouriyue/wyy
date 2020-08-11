package com.gxuwz.beethoven.page.index.cloudview;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;
import androidx.viewpager.widget.ViewPager;
import com.gxuwz.beethoven.R;
import com.gxuwz.beethoven.adapter.cloud.CloudMenuAdapter;
import com.gxuwz.beethoven.listener.cloud.CloudMenuChangerListener;
import java.util.ArrayList;

/**
 * “云村”初始化模块
 */
public class CloudViewInit {
    View CloudView;
    Context context;
    ViewPager cloudPager;
    WindowManager windowManager;
    CloudMenuAdapter cloudMenuAdapter;
    ArrayList<View> pagerView;

    View plaza,follow;
    LayoutInflater layoutInflater;

    TextView plazaTitle,followTitle;
    TextView wordsTitle[];

    /**
     * "广场"菜单
     */
    PlazaViewInit plazaViewInit;
    /**
     * “关注”
     */
    FollowInit followInit;

    public CloudViewInit(View CloudView, Context context, WindowManager windowManager, LayoutInflater layoutInflater) {
        this.CloudView = CloudView;
        this.context = context;
        this.windowManager = windowManager;
        this.layoutInflater = layoutInflater;
    }

    public void init(){
        findByIdAndNew();
        viewPagerInit();
        /**
         * 广场页
         */
        plazaViewInit.init();
        /**
         * 关注
         */
        followInit.init();
    }

    public void findByIdAndNew(){
        cloudPager = CloudView.findViewById(R.id.cloud_viewpager);
        plaza = layoutInflater.inflate(R.layout.activity_cloud_plaza,null);
        follow = layoutInflater.inflate(R.layout.activity_cloud_follow,null);
        plazaTitle = CloudView.findViewById(R.id.plaza_title);
        followTitle = CloudView.findViewById(R.id.follow_title);
        wordsTitle = new TextView[2];
        wordsTitle[0]=plazaTitle;
        wordsTitle[1]=followTitle;
        pagerView = new ArrayList<View>();
        pagerView.add(plaza);
        pagerView.add(follow);
        cloudMenuAdapter = new CloudMenuAdapter(pagerView);
        plazaViewInit = new PlazaViewInit(context,plaza,windowManager);
        followInit = new FollowInit(follow,context);
    }

    public void viewPagerInit(){
        //绑定适配器
        cloudPager.setAdapter(cloudMenuAdapter);
        //设置viewPager的初始界面为第一个界面
        cloudPager.setCurrentItem(0);
        //添加切换界面的监听器
        cloudPager.addOnPageChangeListener(new CloudMenuChangerListener(wordsTitle));
        //为了获取屏幕宽度，新建一个DisplayMetrics对象
        DisplayMetrics displayMetrics = new DisplayMetrics();
        //将当前窗口的一些信息放在DisplayMetrics类中
        windowManager.getDefaultDisplay().getMetrics(displayMetrics);
    }
}
