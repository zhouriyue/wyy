package com.gxuwz.beethoven.page.index;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Handler;
import android.os.Message;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;
import com.gxuwz.beethoven.R;
import com.gxuwz.beethoven.adapter.MainMenuAdapter;
import com.gxuwz.beethoven.hanlder.SysUserHandler;
import com.gxuwz.beethoven.listener.LeftMenuListener;
import com.gxuwz.beethoven.listener.MainMenuPageChangeListener;
import com.gxuwz.beethoven.listener.MainMenuTopClickListener;
import com.gxuwz.beethoven.page.index.playview.ActivityPlayView;
import com.gxuwz.beethoven.receiver.IndexBottomBarReceiver;
import com.gxuwz.beethoven.service.MusicService;
import com.gxuwz.beethoven.util.BlurUtil;
import com.gxuwz.beethoven.util.HttpUtil;
import com.gxuwz.beethoven.util.MergeImage;
import com.gxuwz.beethoven.util.Player;

import java.util.ArrayList;

public class IndexBase extends Activity {
    protected ViewPager viewPager;
    protected ArrayList<View> pageview;
    protected LayoutInflater inflater;
    protected TextView[] textViews;

    protected View MyView,VideoView,FindView,CloudView;
    /**
     * 底部部分
     * perPicView：个人头像
     * playMusicView：播放页
     * playAndStop: 停止或播放按钮
     */
    protected ImageView perPicView,playMusicView,myBg,playPerPicView;
    protected ImageView playAndStop;
    protected TextView userNameView;
    protected RecyclerView songList;
    protected IndexBottomBarReceiver indexBottomBarReceiver;
    protected MainMenuTopClickListener mainMenuTopClickListener;
    protected SysUserHandler sysUserHandler;
    protected ImageView songListUrlImage;
    protected TextView songNameView;
    protected TextView singerView;
    /**
     * 头部
     */
    protected ImageView leftMenu;
    protected ImageView search;
    /**
     * 获取SharedPreferences
     */
    protected SharedPreferences sharedPreferences;
    protected WindowManager windowManager;

    protected MainMenuAdapter mainMenuAdapter;

    public void init() {
        findByIdAndNew();

        //绑定适配器
        viewPager.setAdapter(mainMenuAdapter);
        //设置viewPager的初始界面为第一个界面
        viewPager.setCurrentItem(0);
        //添加切换界面的监听器
        viewPager.addOnPageChangeListener(new MainMenuPageChangeListener(textViews,myBg,leftMenu,search,this));
        //为了获取屏幕宽度，新建一个DisplayMetrics对象
        DisplayMetrics displayMetrics = new DisplayMetrics();
        //将当前窗口的一些信息放在DisplayMetrics类中
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);

        playMusicView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(IndexBase.this, ActivityPlayView.class);
                startActivity(intent);
            }
        });
        /**
         * 底部播放或停止按钮变化
         */
        textViews[0].setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
        textViews[0].setTextColor(Color.parseColor("#ffffff"));

        playAndStop.setOnClickListener(mainMenuTopClickListener);
        if(Player.isPlayer){
            playAndStop.setImageBitmap(HttpUtil.getRes("stop_bar",this));
        } else {
            playAndStop.setImageBitmap(HttpUtil.getRes("play1",this));
        }
        /**
         * “我的”页面背景图片
         */
        myBg.setImageBitmap(BlurUtil.doBlur(HttpUtil.getRes("my_bg",this), 10,5));
        leftMenu.setImageBitmap(HttpUtil.getRes("view_my_list_whitk",this));
        search.setImageBitmap(HttpUtil.getRes("view_index_search_white",this));
        /**
         * 底部部分
         * 底部播放栏头像
         */
        playPerPicView.setImageBitmap(MergeImage.circleShow(HttpUtil.getRes("zhouriyue",this)));
        sysUserHandler.setUserNameView(userNameView);
        sysUserHandler.setPerPicView(perPicView);
        sysUserHandler.setSongList(songList);
        /**
         * 注册底部广播接收者
         * 用于及时改变底部的变化
         */
        indexBottomBarReceiver = new IndexBottomBarReceiver();
        indexBottomBarReceiver.setPlayAndStop(playAndStop);
        IntentFilter indexBottomBar = new IntentFilter(IndexBottomBarReceiver.ACTION);
        registerReceiver(indexBottomBarReceiver,indexBottomBar);
        /**
         /**
         * 初始化音乐service
         */
        Intent music = new Intent(this, MusicService.class);
        startService(music);
        /**
         * 打开左侧菜单按钮
         */
        leftMenu.setOnClickListener(new LeftMenuListener((WindowManager) this.getSystemService(Context.WINDOW_SERVICE),inflater));
        initBottom();
    }

    public void initBottom(){
        /**
         * 初始化底部播放栏信息
         */
        String songName = sharedPreferences.getString("songName",null);
        String singer = sharedPreferences.getString("singer",null);
        String songListUrlS = sharedPreferences.getString("songListUrl",null);
        songNameView.setText(songName);
        singerView.setText(singer);
        if(songListUrlS!=null) {
            final Handler songListUrlImageHandler = new Handler() {
                public void handleMessage(android.os.Message songListBit) {
                    songListUrlImage.setImageBitmap(MergeImage.circleShow((Bitmap)songListBit.obj));
                };
            };

            new Thread(){
                @Override
                public void run() {
                    Bitmap imageDate = HttpUtil.getImage(songListUrlS);
                    Message message = new Message();
                    message.obj = imageDate;
                    songListUrlImageHandler.sendMessage(message);
                }
            }.start();
        }
    }

    public void findByIdAndNew(){
        /**
         * 获取sharedPreferences
         */
        sharedPreferences = getSharedPreferences("data",MODE_PRIVATE);
        inflater =getLayoutInflater();
        windowManager = getWindowManager();

        MyView = inflater.inflate(R.layout.activity_my, null);
        FindView = inflater.inflate(R.layout.activity_find, null);
        CloudView = inflater.inflate(R.layout.activity_cloud, null);
        VideoView = inflater.inflate(R.layout.activity_video, null);
        viewPager = (ViewPager) findViewById(R.id.viewPager);
        userNameView = MyView.findViewById(R.id.username);
        perPicView = MyView.findViewById(R.id.per_pic);
        songList = MyView.findViewById(R.id.rv3);
        myBg = findViewById(R.id.my_bg);
        playPerPicView = findViewById(R.id.play_song_list_url);
        playAndStop = findViewById(R.id.play_and_stop);
        playMusicView = findViewById(R.id.play_song_list_url);
        songListUrlImage = findViewById(R.id.play_song_list_url);
        songNameView = findViewById(R.id.song_name);
        singerView = findViewById(R.id.singer);
        leftMenu = findViewById(R.id.left_menu);
        search = findViewById(R.id.search);

        sysUserHandler = new SysUserHandler();
        pageview =new ArrayList<View>();
        pageview.add(MyView);
        pageview.add(FindView);
        pageview.add(CloudView);
        pageview.add(VideoView);
        textViews = new TextView[pageview.size()];
        textViews[0] = findViewById(R.id.my);
        textViews[1] = findViewById(R.id.find);
        textViews[2] = findViewById(R.id.cloud);
        textViews[3] = findViewById(R.id.video);
        mainMenuTopClickListener = new MainMenuTopClickListener(viewPager,this);
        for(int i = 0;i < textViews.length;i++){
            textViews[i].setOnClickListener(mainMenuTopClickListener);
        }
        mainMenuAdapter = new MainMenuAdapter(pageview);
    }
}
