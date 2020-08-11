package com.gxuwz.beethoven.page.index.myview;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.View;
import android.widget.ImageView;

import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.gxuwz.beethoven.R;
import com.gxuwz.beethoven.adapter.my.RecentBroadcastsAdapter;
import com.gxuwz.beethoven.conn.Http;
import com.gxuwz.beethoven.hanlder.SysUserHandler;
import com.gxuwz.beethoven.model.entity.SongList;
import com.gxuwz.beethoven.page.index.myview.localmusic.LocalMusicActivity;
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

    public MyViewInit(RecyclerView recentBroadcasts, SharedPreferences sharedPreferences, Context context, View myView, SysUserHandler sysUserHandler) {
        this.recentBroadcasts = recentBroadcasts;
        this.sharedPreferences = sharedPreferences;
        this.context = context;
        this.MyView = myView;
        this.sysUserHandler = sysUserHandler;
    }

    public void init(Context context){
        findByIdAll();
        /**
         * 最近播放
         */
        List<SongList> recentBroadcastsLists = new ArrayList<SongList>();
        //全部已播放
        SongList songListAll = new SongList();
        songListAll.setSongListUrl(sharedPreferences.getString("songListUrl",null));
        songListAll.setSongListName("全部已播放歌曲");
        songListAll.setTag("87首");
        recentBroadcastsLists.add(songListAll);

        SongList songListContinue = new SongList();
        songListContinue.setSongListUrl(sharedPreferences.getString("songListUrl",null));
        songListContinue.setSongListName("歌单："+sharedPreferences.getString("songName",null));
        songListContinue.setTag("继续播放");
        recentBroadcastsLists.add(songListContinue);
        recentBroadcasts = MyView.findViewById(R.id.recent_broadcasts);
        recentBroadcasts.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
        recentBroadcasts.setAdapter(new RecentBroadcastsAdapter(context,recentBroadcastsLists));

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

        sysUserHandler.setContext(context);
        HttpUtil.get(Http.HTTPURL,sysUserHandler);
    }

    public void findByIdAll(){
        localMusic = MyView.findViewById(R.id.local_music);
    }
}
