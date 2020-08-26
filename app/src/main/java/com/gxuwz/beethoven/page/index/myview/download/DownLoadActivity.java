package com.gxuwz.beethoven.page.index.myview.download;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

import com.gxuwz.beethoven.R;
import com.gxuwz.beethoven.adapter.PagerCustomAdapter;
import com.gxuwz.beethoven.listener.my.download.DownloadChangerListener;
import com.gxuwz.beethoven.listener.video.VideoChangerListener;
import com.gxuwz.beethoven.page.index.myview.localmusic.LocalMusicActivity;
import com.gxuwz.beethoven.page.index.myview.localmusic.songle.SingleViewInit;

import java.util.ArrayList;

public class DownLoadActivity extends AppCompatActivity {

    Context context;
    ViewPager videoPager;
    View single,radioStation,video,downloading;
    ImageView toBack;
    PagerCustomAdapter pagerCustomAdapter;
    ArrayList<View> subPages;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(getResources().getColor(R.color.black));
        }
        setContentView(R.layout.activity_my_download);
        /**
         * 隐藏标题栏
         */
        getSupportActionBar().hide();
        findByIdAndNew();

        /**
         * 初始化“单曲”页
         */
        new SingleViewInit(single).init(this, DownLoadActivity.this);
        /**
         * 初始化“电台节目”页
         */
    }

    public void findByIdAndNew(){
        videoPager = findViewById(R.id.download_menu);
        toBack = findViewById(R.id.to_back);
        context = DownLoadActivity.this;
        subPages = new ArrayList<View>();
        single = LayoutInflater.from(context).inflate(R.layout.activity_my_download_single,null);
        radioStation = LayoutInflater.from(context).inflate(R.layout.activity_my_download_radiostation,null);
        video = LayoutInflater.from(context).inflate(R.layout.activity_my_download_video,null);
        downloading = LayoutInflater.from(context).inflate(R.layout.activity_my_download_downloading,null);
        subPages.add(single);
        subPages.add(radioStation);
        subPages.add(video);
        subPages.add(downloading);
        pagerCustomAdapter = new PagerCustomAdapter(subPages);
        toBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        initPager();
    }

    public void initPager(){
        //绑定适配器
        videoPager.setAdapter(pagerCustomAdapter);
        //设置viewPager的初始界面为第一个界面
        videoPager.setCurrentItem(0);
        //添加切换界面的监听器
        videoPager.addOnPageChangeListener(new DownloadChangerListener());
        //为了获取屏幕宽度，新建一个DisplayMetrics对象
        DisplayMetrics displayMetrics = new DisplayMetrics();
        //将当前窗口的一些信息放在DisplayMetrics类中
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
    }
}
