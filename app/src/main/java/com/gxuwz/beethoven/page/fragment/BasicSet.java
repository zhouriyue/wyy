package com.gxuwz.beethoven.page.fragment;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.gxuwz.beethoven.R;
import com.gxuwz.beethoven.dao.SingerDao;
import com.gxuwz.beethoven.dao.SongDao;
import com.gxuwz.beethoven.hanlder.SysUserHandler;
import com.gxuwz.beethoven.model.entity.current.Singer;
import com.gxuwz.beethoven.model.entity.current.Song;
import com.gxuwz.beethoven.page.fragment.search.SearchActivity;
import com.gxuwz.beethoven.page.fragment.playview.ActivityPlayView;
import com.gxuwz.beethoven.receiver.IndexBottomBarReceiver;
import com.gxuwz.beethoven.service.MusicService;
import com.gxuwz.beethoven.util.HttpUtil;
import com.gxuwz.beethoven.util.MergeImage;
import com.gxuwz.beethoven.util.Player;
import com.gxuwz.beethoven.util.staticdata.StaticHttp;

import java.util.List;

public class BasicSet {

    Activity activity;
    /**
     * 底部部分
     * perPicView：个人头像
     * playMusicView：播放页
     * playAndStop: 停止或播放按钮
     */
    protected ImageView perPicView, playMusicView, playPerPicView;
    protected ImageView playAndStop;
    protected TextView userNameView;
    protected SysUserHandler sysUserHandler;
    protected ImageView songListUrlImage;
    protected TextView songNameView;
    protected TextView singerView;
    SongDao songDao;
    SingerDao singerDao;
    /**
     * 头部
     */
    protected ImageView search;
    /**
     * 获取SharedPreferences
     */
    protected SharedPreferences sharedPreferences;

    public BasicSet(Activity activity) {
        this.activity = activity;
        songDao = new SongDao(activity);
        singerDao = new SingerDao(activity);
        init();
    }

    public void init() {
        findByIdAnd();
        initOnClick();
        initBottom();
        initReceiver();
        initBaseData();
    }

    /**
     * 级别3
     * 初始化基础数据
     */
    public void initBaseData() {
        sysUserHandler = new SysUserHandler();
        /**
         * 底部部分
         * 底部播放栏头像
         */
        playPerPicView.setImageBitmap(MergeImage.circleShow(HttpUtil.getRes("zhouriyue", activity)));
        sysUserHandler.setUserNameView(userNameView);
        sysUserHandler.setPerPicView(perPicView);
        //sysUserHandler.setSongList(songList);
    }

    public void initReceiver() {

    }

    /**
     * 级别3
     * 初始化底部播放栏
     */
    public void initBottom() {
        /**
         * 初始化底部播放栏信息
         */
        /*songListUrlImage = activity.findViewById(R.id.play_song_list_url);
        songNameView = activity.findViewById(R.id.song_name);
        singerView = activity.findViewById(R.id.singer);
        playAndStop = activity.findViewById(R.id.play_and_stop);*/
    }

    /**
     * 级别2
     * 设置点击事件
     */
    public void initOnClick() {
        playMusicView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(activity, ActivityPlayView.class);
                activity.startActivity(intent);
            }
        });
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(activity, SearchActivity.class);
                activity.startActivity(intent);
            }
        });
    }

    /**
     * 级别1
     *
     */
    public void findByIdAnd() {
        /**
         * 获取sharedPreferences
         */
        sharedPreferences = activity.getSharedPreferences("data", Context.MODE_PRIVATE);
        playPerPicView = activity.findViewById(R.id.play_song_list_url);
        playMusicView = activity.findViewById(R.id.play_song_list_url);
        search = activity.findViewById(R.id.search);
    }
}
