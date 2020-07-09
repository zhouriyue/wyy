package com.gxuwz.beethoven.hanlder;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.gxuwz.beethoven.adapter.SongListMusicAdapter;
import com.gxuwz.beethoven.model.entity.SongList;
import com.gxuwz.beethoven.model.entity.SongListsMusic;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.List;

public class SongListsMusicHandler extends Handler {
    RecyclerView songListMusic;
    Context context;
    public void handleMessage(Message msg) {
        super.handleMessage(msg);
        //接收到message之后的处理过程
        if (msg.what == 1) {
            //获取子线程回传的数据
            Bundle bundle = msg.getData();
            String result = bundle.getString("result");

            String songListsMusics = null;
            //解析json
            try {
                JSONObject all_json = new JSONObject(result);
                String songListMusicsData = all_json.getJSONObject("_embedded").getString("songListMusics");

                Gson gson = new Gson();
                Type listtype = new TypeToken<List<SongListsMusic>>(){}.getType();
                List<SongListsMusic> songListsMusicList = gson.fromJson(songListMusicsData,listtype);
                songListMusic.setLayoutManager(new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL));
                songListMusic.setAdapter(new SongListMusicAdapter(context,songListsMusicList));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    public RecyclerView getSongListMusic() {
        return songListMusic;
    }

    public void setSongListMusic(RecyclerView songListMusic) {
        this.songListMusic = songListMusic;
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }
}
