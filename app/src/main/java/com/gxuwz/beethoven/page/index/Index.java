package com.gxuwz.beethoven.page.index;

import android.os.Build;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

import androidx.annotation.RequiresApi;

import com.gxuwz.beethoven.R;
import com.gxuwz.beethoven.page.index.cloudview.CloudViewInit;
import com.gxuwz.beethoven.page.index.findview.FindViewInit;
import com.gxuwz.beethoven.page.index.myview.MyViewInit;
import com.gxuwz.beethoven.page.fragment.playlistview.PlayListView;
import com.gxuwz.beethoven.page.index.videoview.VideoViewInit;

public class Index extends IndexBase{

    MyViewInit myViewInit;

    FindViewInit findViewInit;

    CloudViewInit cloudViewInit;

    VideoViewInit videoViewInit;

    PlayListView playListView;

    ImageView playListIV;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(getResources().getColor(R.color.black));
        }
        setContentView(R.layout.activity_index);
        /**
         * 隐藏标题栏
         * hide title box
         */
        getSupportActionBar().hide();
        init();
        initFunModel();
        /**
         * "我的"页面信息模块
         */
        myViewInit.init(windowManager);
        /**
         * “发现”页面模块
         */
        findViewInit.init(inflater);
        /**
         * “云村”页面模块
         */
        cloudViewInit.init();
        /**
         * “视频”页模块
         */
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            videoViewInit.init(Index.this);
        }
    }

    public void initFunModel(){
        myViewInit = new MyViewInit(songList,sharedPreferences,Index.this,MyView,sysUserHandler);
        findViewInit = new FindViewInit(FindView,windowManager,this);
        cloudViewInit = new CloudViewInit(CloudView,this,windowManager,inflater);
        videoViewInit = new VideoViewInit(VideoView,inflater,windowManager);
        playListIV = findViewById(R.id.play_list);
        playListView = new PlayListView(Index.this,playListIV);
    }
}
