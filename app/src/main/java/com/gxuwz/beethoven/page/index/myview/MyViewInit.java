package com.gxuwz.beethoven.page.index.myview;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;

import androidx.recyclerview.widget.RecyclerView;

import com.gxuwz.beethoven.hanlder.SysUserHandler;
import com.gxuwz.beethoven.model.entity.SongList;

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
     * 本地
     */
    LinearLayout localMusicLin;
    SongList songListAll;
    List<SongList> recentBroadcastsLists;
    SongList songListContinue;



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

        //recentBroadcasts = MyView.findViewById(R.id.recent_broadcasts);
        /**
         * 最近播放
         */
        /*recentBroadcastsLists = new ArrayList<SongList>();
        //全部已播放
        songListAll = new SongList();
        songListContinue = new SongList();
        musicAppInit = new MusicAppInit(MyView);
        sysUserHandler.setContext(context);
        HttpUtils.get(StaticHttp.HTTPURL,sysUserHandler);
        songListAll.setSongListUrl(sharedPreferences.getString("songListUrl",null));
        songListAll.setSongListName("全部已播歌曲");
        songListAll.setTag("87首");
        recentBroadcastsLists.add(songListAll);
        songListContinue.setSongListUrl(sharedPreferences.getString("songListUrl",null));
        songListContinue.setSongListName("歌单："+sharedPreferences.getString("songName",null));
        songListContinue.setTag("继续播放");
        recentBroadcastsLists.add(songListContinue);

        recentBroadcasts.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
        recentBroadcasts.setAdapter(new RecentBroadcastsAdapter(context,recentBroadcastsLists));*/
    }
}
