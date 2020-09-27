package com.gxuwz.beethoven.page.index.myview.localmusic.songle;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.view.View;

import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.gxuwz.beethoven.R;
import com.gxuwz.beethoven.adapter.my.LocalMusicAdapter;
import com.gxuwz.beethoven.model.entity.Music;
import com.gxuwz.beethoven.util.MusicUtils;

import java.util.ArrayList;
import java.util.List;

public class SingleViewInit {

    SharedPreferences sharedPreferences;
    View SingleView;
    RecyclerView songListMusic;
    List<Music> list;

    public SingleViewInit(View singleView,SharedPreferences sharedPreferences) {
        SingleView = singleView;
        this.sharedPreferences = sharedPreferences;
    }

    public void init(Context context, Activity activity){
        findViewByIdAll();
        /**
         * 获取本地音乐
         */
        MusicUtils.getSDCardRWPermission(activity);

        list = new ArrayList<>();
        //把扫描到的音乐赋值给list
        list = MusicUtils.getMusicData(context);
        /**
         * 显示本地音乐
         */
        songListMusic.setLayoutManager(new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL));
        songListMusic.setAdapter(new LocalMusicAdapter(context,list,sharedPreferences));
    }

    public void findViewByIdAll(){
        songListMusic = SingleView.findViewById(R.id.single_rv);
    }
}
