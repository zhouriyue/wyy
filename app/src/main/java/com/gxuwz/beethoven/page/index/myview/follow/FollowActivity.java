package com.gxuwz.beethoven.page.index.myview.follow;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;

import com.gxuwz.beethoven.R;
import com.gxuwz.beethoven.adapter.PagerCustomAdapter;
import com.gxuwz.beethoven.listener.my.download.DownloadChangerListener;
import com.gxuwz.beethoven.page.index.myview.follow.mv.MvViewInit;
import com.gxuwz.beethoven.page.index.myview.follow.song.SongViewInit;

import java.util.ArrayList;

public class FollowActivity extends AppCompatActivity {

    Context context;
    LayoutInflater layoutInflater;

    LinearLayout toBackLin;
    ViewPager followViewPager;
    View songView,mvView;
    PagerCustomAdapter pagerCustomAdapter;
    ArrayList<View> subPages;

    SongViewInit songViewInit;
    MvViewInit mvViewInit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(getResources().getColor(R.color.black));
        }
        setContentView(R.layout.my_follow);
        /**
         * 隐藏标题栏
         */
        getSupportActionBar().hide();
        findByIdAndNew();
        /**
         * 歌曲页
         */
        songViewInit.init(context);
        /**
         * mv页
         */
        mvViewInit.init(context);
    }

    public void findByIdAndNew(){
        context = FollowActivity.this;
        layoutInflater = getLayoutInflater();
        followViewPager = findViewById(R.id.follow_menu);
        subPages = new ArrayList<View>();
        songView = layoutInflater.inflate(R.layout.follow_song,null);
        mvView = layoutInflater.inflate(R.layout.follow_mv,null);
        subPages.add(songView);
        subPages.add(mvView);
        pagerCustomAdapter = new PagerCustomAdapter(subPages);
        songViewInit = new SongViewInit(songView);
        mvViewInit = new MvViewInit(mvView);
        toBackLin = findViewById(R.id.to_back_lin);
        toBackLin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        initPager();
    }

    private void initPager(){
        //绑定适配器
        followViewPager.setAdapter(pagerCustomAdapter);
        //设置viewPager的初始界面为第一个界面
        followViewPager.setCurrentItem(0);
        //添加切换界面的监听器
        followViewPager.addOnPageChangeListener(new DownloadChangerListener());
        //为了获取屏幕宽度，新建一个DisplayMetrics对象
        DisplayMetrics displayMetrics = new DisplayMetrics();
        //将当前窗口的一些信息放在DisplayMetrics类中
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
    }
}
