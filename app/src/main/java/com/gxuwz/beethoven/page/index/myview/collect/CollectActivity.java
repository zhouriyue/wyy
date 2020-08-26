package com.gxuwz.beethoven.page.index.myview.collect;

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
import com.gxuwz.beethoven.page.index.myview.collect.anchor.AnchorViewInit;
import com.gxuwz.beethoven.page.index.myview.collect.singer.SingerViewInit;
import com.gxuwz.beethoven.page.index.myview.collect.specialcolumn.SpecialColumnInit;
import com.gxuwz.beethoven.page.index.myview.collect.video.VideoViewInit;
import com.gxuwz.beethoven.page.index.myview.collect.yunvillage.YunVillageViewInit;
import com.gxuwz.beethoven.page.index.myview.localmusic.album.AlbumViewInit;

import java.util.ArrayList;
import java.util.List;

public class CollectActivity extends AppCompatActivity {

    ViewPager collectViewPager;
    LinearLayout toBackLin;
    PagerCustomAdapter pagerCustomAdapter;

    Context context;
    LayoutInflater layoutInflater;

    View albumView,anchorView,singerView,videoView,yunvillageView,specialColumnView;
    ArrayList<View> sugPagers;

    AlbumViewInit albumViewInit;
    SingerViewInit singerViewInit;
    AnchorViewInit anchorViewInit;
    VideoViewInit videoViewInit;
    YunVillageViewInit yunVillageViewInit;
    SpecialColumnInit specialColumnInit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(getResources().getColor(R.color.black));
        }
        setContentView(R.layout.activity_my_collect);
        /**
         * 隐藏标题栏
         */
        getSupportActionBar().hide();
        findByIdAndNew();
        /**
         * 专辑页
         */
        albumViewInit.init(context);
        /**
         * 歌手页
         */
        singerViewInit.init(context);
        /**
         * 主播页
         */
        anchorViewInit.init(context);
        /**
         * 视频页
         */
        videoViewInit.init(context);
        /**
         * 云村页
         */
        yunVillageViewInit.init(context);
        /**
         * 专栏页
         */
        specialColumnInit.init(context);
    }

    public void findByIdAndNew(){
        context = CollectActivity.this;
        collectViewPager = findViewById(R.id.collect_menu);
        toBackLin = findViewById(R.id.to_back_lin);
        layoutInflater = getLayoutInflater();
        sugPagers = new ArrayList<View>();
        albumView = layoutInflater.inflate(R.layout.activity_my_collect_album,null);
        singerView = layoutInflater.inflate(R.layout.activity_my_collect_singer,null);
        anchorView = layoutInflater.inflate(R.layout.activity_my_collect_anchor,null);
        videoView = layoutInflater.inflate(R.layout.activity_my_collect_video,null);
        yunvillageView = layoutInflater.inflate(R.layout.activity_my_collect_yunvillage,null);
        specialColumnView = layoutInflater.inflate(R.layout.activity_my_collect_specialcolumn,null);
        sugPagers.add(albumView);
        sugPagers.add(singerView);
        sugPagers.add(anchorView);
        sugPagers.add(videoView);
        sugPagers.add(yunvillageView);
        sugPagers.add(specialColumnView);
        pagerCustomAdapter = new PagerCustomAdapter(sugPagers);
        albumViewInit = new AlbumViewInit(albumView);
        singerViewInit = new SingerViewInit(singerView);
        anchorViewInit = new AnchorViewInit(anchorView);
        videoViewInit = new VideoViewInit(videoView);
        yunVillageViewInit = new YunVillageViewInit(yunvillageView);
        specialColumnInit = new SpecialColumnInit(specialColumnView);
        toBackLin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        pagerInit();
    }

    public void pagerInit(){
        //绑定适配器
        collectViewPager.setAdapter(pagerCustomAdapter);
        //设置viewPager的初始界面为第一个界面
        collectViewPager.setCurrentItem(0);
        //添加切换界面的监听器
        collectViewPager.addOnPageChangeListener(new DownloadChangerListener());
        //为了获取屏幕宽度，新建一个DisplayMetrics对象
        DisplayMetrics displayMetrics = new DisplayMetrics();
        //将当前窗口的一些信息放在DisplayMetrics类中
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
    }
}
