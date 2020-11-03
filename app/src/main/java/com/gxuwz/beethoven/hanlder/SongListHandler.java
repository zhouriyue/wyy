package com.gxuwz.beethoven.hanlder;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.gxuwz.beethoven.adapter.my.WaterFallAdapter;
import com.gxuwz.beethoven.dao.SongListDao;
import com.gxuwz.beethoven.model.entity.SongList;
import com.gxuwz.beethoven.model.entity.SysUser;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.List;

public class SongListHandler extends Handler {
    RecyclerView songList = null;
    Context context = null;
    SysUser sysUser = null;


    public void handleMessage(Message msg) {
        super.handleMessage(msg);
        //接收到message之后的处理过程
        if(msg.what ==1 ){
            //获取子线程回传的数据
            Bundle bundle = msg.getData();
            String result = bundle.getString("result");
            //解析json
            try {
                JSONObject all_json = new JSONObject(result);
                String songListStr = all_json.getJSONObject("_embedded").optString("songLists");
                Gson gson = new Gson();
                Type listtype = new TypeToken<List<SongList>>(){}.getType();
                List<SongList> songLists = gson.fromJson(songListStr,listtype);
                songList.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
                songList.setAdapter(new WaterFallAdapter(context,songLists,sysUser));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public RecyclerView getSongList() {
        return songList;
    }

    public void setSongList(RecyclerView songList) {
        this.songList = songList;
    }

    public SysUser getSysUser() {
        return sysUser;
    }

    public void setSysUser(SysUser sysUser) {
        this.sysUser = sysUser;
    }
}
