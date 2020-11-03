package com.gxuwz.beethoven.hanlder;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.gxuwz.beethoven.adapter.my.SongListMusicAdapter;
import com.gxuwz.beethoven.dao.SLMusicDao;
import com.gxuwz.beethoven.model.entity.SongList;
import com.gxuwz.beethoven.model.entity.SongListsMusic;
import com.gxuwz.beethoven.model.entity.my.songlist.SLMore;
import com.gxuwz.beethoven.page.fragment.my.songlist.LoadDownView;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class SongListsMusicHandler extends Handler {
    /**
     * 歌单音乐显示
     */
    RecyclerView songListMusic;
    /**
     * 歌单歌曲总页数
     */
    TextView totalMusic;
    Context context;
    /**
     * 获取SharedPreferences对象
     */
    SharedPreferences sharedPreferences;
    /**
     * 歌单对象
     */
    SongList songList;
    String songListUrl;

    LoadDownView loadDownView;
    List<SongListsMusic> songListsMusicList;
    SLMusicDao slMusicDao;

    public SongListsMusicHandler(String songListUrl,Context context) {
        this.songListUrl = songListUrl;
        this.context = context;
        slMusicDao = new SLMusicDao(context);
    }

    public void handleMessage(Message msg) {
        super.handleMessage(msg);
        //接收到message之后的处理过程
        if (msg.what == 1) {
            //获取子线程回传的数据
            Bundle bundle = msg.getData();
            String result = bundle.getString("result");
            //解析json
            try {
                anaParamter(result);
                setSLMore(songListsMusicList);
                setView();
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    public void setView(){
        totalMusic.setText("（共"+songListsMusicList.size()+"首）");
        songListMusic.setLayoutManager(new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL));
        SongListMusicAdapter songListMusicAdapter = new SongListMusicAdapter(context,songListsMusicList,sharedPreferences,songListUrl);
        songListMusicAdapter.setLoadDownView(loadDownView);
        songListMusic.setAdapter(songListMusicAdapter);
    }

    public void anaParamter(String result) throws JSONException {
        JSONObject all_json = new JSONObject(result);
        String songListMusicsData = all_json.getJSONObject("_embedded").getString("songListMusics");
        Gson gson = new Gson();
        Type listtype = new TypeToken<List<SongListsMusic>>(){}.getType();
        songListsMusicList = gson.fromJson(songListMusicsData,listtype);
        for(int i = 0;i < songListsMusicList.size();i++){
            SongListsMusic slMusic = songListsMusicList.get(i);
            System.out.println(slMusic.toString());
            slMusic.setId(slMusic.getReallyId());

            if(slMusicDao.isExist(slMusic.getId())==0){
                slMusicDao.insert(slMusic);
            } else {
                slMusicDao.update(slMusic);
            }
        }
    }

    public void setSLMore(List<SongListsMusic> songListsMusicList){
        for(int i = 0;i < songListsMusicList.size();i++) {
            List<SLMore> slMores = new ArrayList<SLMore>();
            SLMore slMore = new SLMore();
            slMore.setIcon("play2");
            slMore.setName("下一首播放");
            slMore.setType(1);
            slMores.add(slMore);
            slMore = new SLMore();
            slMore.setIcon("play2");
            slMore.setName("收藏到歌单");
            slMore.setType(2);
            slMores.add(slMore);
            slMore = new SLMore();
            slMore.setIcon("play2");
            slMore.setName("下载");
            slMore.setType(3);
            slMores.add(slMore);
            slMore = new SLMore();
            slMore.setIcon("play2");
            slMore.setName("评论");
            slMore.setType(4);
            slMores.add(slMore);
            slMore = new SLMore();
            slMore.setIcon("play2");
            slMore.setName("分享");
            slMore.setType(5);
            slMores.add(slMore);
            songListsMusicList.get(i).setSlMoreList(slMores);
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

    public TextView getTotalMusic() {
        return totalMusic;
    }

    public void setTotalMusic(TextView totalMusic) {
        this.totalMusic = totalMusic;
    }

    public SharedPreferences getSharedPreferences() {
        return sharedPreferences;
    }

    public void setSharedPreferences(SharedPreferences sharedPreferences) {
        this.sharedPreferences = sharedPreferences;
    }

    public SongList getSongList() {
        return songList;
    }

    public void setSongList(SongList songList) {
        this.songList = songList;
    }

    public LoadDownView getLoadDownView() {
        return loadDownView;
    }

    public void setLoadDownView(LoadDownView loadDownView) {
        this.loadDownView = loadDownView;
    }
}
