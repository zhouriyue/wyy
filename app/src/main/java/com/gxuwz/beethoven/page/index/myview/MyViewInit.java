package com.gxuwz.beethoven.page.index.myview;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.gxuwz.beethoven.R;
import com.gxuwz.beethoven.adapter.my.RecentBroadcastsAdapter;
import com.gxuwz.beethoven.conn.Http;
import com.gxuwz.beethoven.hanlder.SysUserHandler;
import com.gxuwz.beethoven.model.entity.SongList;
import com.gxuwz.beethoven.page.index.myview.collect.CollectActivity;
import com.gxuwz.beethoven.page.index.myview.download.DownLoadActivity;
import com.gxuwz.beethoven.page.index.myview.follow.FollowActivity;
import com.gxuwz.beethoven.page.index.myview.localmusic.LocalMusicActivity;
import com.gxuwz.beethoven.page.index.myview.radiostation.RadioStationActivity;
import com.gxuwz.beethoven.util.HttpUtil;

import java.util.ArrayList;
import java.util.List;

public class MyViewInit {
    /**
     * 最近播放
     */
    RecyclerView recentBroadcasts;
    SharedPreferences sharedPreferences;
    Context context;
    View MyView;
    SysUserHandler sysUserHandler;
    /**
     * 本地图片
     */
    ImageView localMusic;
    SongList songListAll;
    List<SongList> recentBroadcastsLists;
    SongList songListContinue;

    /**
     * 下载、电台、我的收藏、关注模块
     */
    LinearLayout downloadLin,radioStationLin,collectLin,followLin;

    /**
     * “音乐应用”模块
     */
    MusicAppInit musicAppInit;

    public MyViewInit(RecyclerView recentBroadcasts, SharedPreferences sharedPreferences, Context context, View myView, SysUserHandler sysUserHandler) {
        this.recentBroadcasts = recentBroadcasts;
        this.sharedPreferences = sharedPreferences;
        this.context = context;
        this.MyView = myView;
        this.sysUserHandler = sysUserHandler;
    }

    public void init(WindowManager windowManager){
        findByIdAndNew();
        musicAppInit.init(windowManager,context);
    }

    public void findByIdAndNew(){
        localMusic = MyView.findViewById(R.id.local_music);
        recentBroadcasts = MyView.findViewById(R.id.recent_broadcasts);
        downloadLin = MyView.findViewById(R.id.download_lin);
        radioStationLin = MyView.findViewById(R.id.radio_station_lin);
        collectLin = MyView.findViewById(R.id.collect_lin);
        followLin = MyView.findViewById(R.id.follow_lin);
        /**
         * 最近播放
         */
        recentBroadcastsLists = new ArrayList<SongList>();
        //全部已播放
        songListAll = new SongList();
        songListContinue = new SongList();
        musicAppInit = new MusicAppInit(MyView);
        sysUserHandler.setContext(context);
        HttpUtil.get(Http.HTTPURL,sysUserHandler);
        /**
         * 跳转到本地音乐页面
         */
        localMusic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, LocalMusicActivity.class);
                context.startActivity(intent);
            }
        });
        /**
         * 跳转到下载页
         */
        downloadLin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, DownLoadActivity.class);
                context.startActivity(intent);
            }
        });
        /**
         * 跳转到电台页
         */
        radioStationLin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, RadioStationActivity.class);
                context.startActivity(intent);
            }
        });
        /**
         * 跳转到收藏页
         */
        collectLin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, CollectActivity.class);
                context.startActivity(intent);
            }
        });
        /**
         * 跳转到关注页
         */
        followLin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, FollowActivity.class);
                context.startActivity(intent);
            }
        });
        songListAll.setSongListUrl(sharedPreferences.getString("songListUrl",null));
        songListAll.setSongListName("全部已播歌曲");
        songListAll.setTag("87首");
        recentBroadcastsLists.add(songListAll);
        songListContinue.setSongListUrl(sharedPreferences.getString("songListUrl",null));
        songListContinue.setSongListName("歌单："+sharedPreferences.getString("songName",null));
        songListContinue.setTag("继续播放");
        recentBroadcastsLists.add(songListContinue);

        recentBroadcasts.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
        recentBroadcasts.setAdapter(new RecentBroadcastsAdapter(context,recentBroadcastsLists));
    }
}
