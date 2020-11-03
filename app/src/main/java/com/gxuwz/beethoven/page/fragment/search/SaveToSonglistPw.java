package com.gxuwz.beethoven.page.fragment.search;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.PopupWindow;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.gxuwz.beethoven.R;
import com.gxuwz.beethoven.adapter.search.SaveSLAdapter;
import com.gxuwz.beethoven.dao.SongListDao;
import com.gxuwz.beethoven.model.entity.SongList;
import com.gxuwz.beethoven.model.entity.current.Song;
import com.gxuwz.beethoven.model.entity.current.Songlist;
import com.gxuwz.beethoven.page.fragment.my.songlist.LoadDownPopuWindow;
import com.gxuwz.beethoven.util.HttpUtil;
import com.gxuwz.beethoven.util.WindowPixels;
import com.gxuwz.beethoven.util.staticdata.StaticHttp;

import java.lang.reflect.Type;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

public class SaveToSonglistPw {

    Context context;
    public PopupWindow popupWindow;
    PopupWindow ldPopupWindow;
    View saveToSonglist;
    RecyclerView saveSonglistRv;
    SaveSLAdapter saveSLAdapter;
    List<Songlist> songlists;
    SharedPreferences sharedPreferences;
    SongListDao songListDao;

    public SaveToSonglistPw(Context context) {
        this.context = context;
        this.songListDao = new SongListDao(context);
        this.saveToSonglist = LayoutInflater.from(context).inflate(R.layout.save_to_songlist,null);
        this.saveSonglistRv = this.saveToSonglist.findViewById(R.id.save_songlist_rv);
        this.sharedPreferences = context.getSharedPreferences("data", Context.MODE_PRIVATE);
        int x1 = (int) (15*WindowPixels.DENSITY);
        int x2 = (int) ((WindowPixels.WIDTH-15)*WindowPixels.DENSITY);
        int y1 = (int) ((WindowPixels.HEIGHT-215)*WindowPixels.DENSITY);
        int y2 = y1+(int)(400*WindowPixels.DENSITY);
        this.saveToSonglist.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(event.getX()<x1||event.getX()>x2||event.getY()<y1||event.getY()>y2) {
                    if(popupWindow!=null){
                        popupWindow.dismiss();
                    }
                }
                return false;
            }
        });
    }

    public void showAtLocation(View view,long songId){
        getPopupWindow();
        popupWindow.showAtLocation(view, Gravity.LEFT,0,0);
        Long userId = sharedPreferences.getLong("userId",-1);
        songlists = songListDao.findUserSL(userId);
        if(saveSLAdapter==null){
            saveSonglistRv.setLayoutManager(new StaggeredGridLayoutManager(1,StaggeredGridLayoutManager.VERTICAL));
            saveSLAdapter = new SaveSLAdapter(context,songlists,songId);
            saveSLAdapter.setSaveSlPopupWindow(popupWindow);
            saveSonglistRv.setAdapter(saveSLAdapter);
        } else {
            saveSonglistRv.removeAllViews();
            saveSLAdapter.updateData(songlists);
        }
    }

    protected  void  initPopupWindow(){
        int width = (int) (WindowPixels.WIDTH*WindowPixels.DENSITY);
        popupWindow = new PopupWindow(saveToSonglist,width, ViewGroup.LayoutParams.MATCH_PARENT,true);
        popupWindow.setBackgroundDrawable(new ColorDrawable(0));
    }
    private  void  getPopupWindow() {
        if (null != popupWindow) {
            popupWindow.dismiss();
            return;
        } else {
            initPopupWindow();
        }
    }

    public PopupWindow getLdPopupWindow() {
        return ldPopupWindow;
    }

    public void setLdPopupWindow(PopupWindow ldPopupWindow) {
        this.ldPopupWindow = ldPopupWindow;
    }
}
