package com.gxuwz.beethoven.page.fragment.my.songlist;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.gxuwz.beethoven.R;
import com.gxuwz.beethoven.adapter.my.songlist.SDLPopWinAdapter;
import com.gxuwz.beethoven.adapter.popwind.SonglistOperAdapter;
import com.gxuwz.beethoven.model.entity.current.Operate;
import com.gxuwz.beethoven.model.entity.current.Singer;
import com.gxuwz.beethoven.model.entity.current.Song;
import com.gxuwz.beethoven.model.entity.current.Songlist;
import com.gxuwz.beethoven.page.fragment.search.SaveToSonglistPw;
import com.gxuwz.beethoven.util.HttpUtils;
import com.gxuwz.beethoven.util.MergeImage;
import com.gxuwz.beethoven.util.staticdata.StaticHttp;

import java.lang.reflect.Type;
import java.util.List;

public class LoadDownView {

    /**
     * 父类的context
     */
    Context context;
    LoadDownPopuWindow loadDownPopuWindow;
    SaveToSonglistPw saveToSonglistPw;
    View slLoadDownView;
    RecyclerView slMoreRv;

    ImageView img;
    TextView songName,singerName;
    LinearLayout backLin;

    View view;
    Song song;
    public LoadDownView(Context context) {
        this.context = context;
        this.slLoadDownView = LayoutInflater.from(context).inflate(R.layout.sl_loaddown_popupwindow,null);
        findByIdAll();
        initOnClick();
        this.loadDownPopuWindow = new LoadDownPopuWindow(context,slLoadDownView);
        this.saveToSonglistPw = new SaveToSonglistPw(context);
        this.slMoreRv.setLayoutManager(new StaggeredGridLayoutManager(1,StaggeredGridLayoutManager.VERTICAL));
    }

    public void initView(List<Operate> operates, View view,Long slId, Song song){
        this.song = song;
        String url = StaticHttp.STATIC_BASEURL+song.getCoverPicture();
        MergeImage.showGlideImg(context,url,img);
        songName.setText(song.getSongName());
        initSinger(singerName);
        if(song!=null) {
            SDLPopWinAdapter sdlPopWinAdapter = new SDLPopWinAdapter(context,operates,saveToSonglistPw,view,slId,song);
            sdlPopWinAdapter.setLdPopupWindow(loadDownPopuWindow.popupWindow);
            slMoreRv.setAdapter(sdlPopWinAdapter);
        }
        this.view = view;
        loadDownPopuWindow.showAtLocation(this.view);
    }

    public void initView(List<Operate> operates, View view, Song song){
        this.song = song;
        String url = StaticHttp.STATIC_BASEURL+song.getCoverPicture();
        MergeImage.showGlideImg(context,url,img);
        songName.setText(song.getSongName());
        initSinger(singerName);
        if(song!=null) {
            SDLPopWinAdapter sdlPopWinAdapter = new SDLPopWinAdapter(context,operates,saveToSonglistPw,view,song);
            sdlPopWinAdapter.setLdPopupWindow(loadDownPopuWindow.popupWindow);
            slMoreRv.setAdapter(sdlPopWinAdapter);
        } else {
            SDLPopWinAdapter sdlPopWinAdapter = new SDLPopWinAdapter(context,operates,saveToSonglistPw,view);
            sdlPopWinAdapter.setLdPopupWindow(loadDownPopuWindow.popupWindow);
            slMoreRv.setAdapter(sdlPopWinAdapter);
        }

        this.view = view;
        loadDownPopuWindow.showAtLocation(this.view);
    }

    public void initView(List<Operate> operates, View view, Songlist songlist){
        if(songlist.getCoverPicture()!=null) {
            MergeImage.showGlideImg(context,StaticHttp.STATIC_BASEURL+songlist.getCoverPicture(),img);
        } else {
            MergeImage.showGlideImg(context,StaticHttp.DEFALUT_SONGLIST_COVERPICTURE,img);
        }
        songName.setText(songlist.getSlName());
        SonglistOperAdapter songlistOperAdapter = new SonglistOperAdapter(context,operates,songlist);
        slMoreRv.setAdapter(songlistOperAdapter);
        this.view = view;
        loadDownPopuWindow.showAtLocation(this.view);
    }

    /**
     * 获取歌手并显示
     */
    public void initSinger(TextView textView){
        Handler handler = new Handler(){
            @Override
            public void handleMessage(@NonNull Message msg) {
                if(msg.what==1) {
                    Bundle bundle = msg.getData();
                    String result = bundle.getString("result");
                    Gson gson = new Gson();
                    Type listtype = new TypeToken<List<Singer>>(){}.getType();
                    List<Singer> singerList = gson.fromJson(result,listtype);
                    String nameAndSingerName = song.getSongName();
                    if(singerList!=null&&singerList.size()!=0) {
                        nameAndSingerName += "-"+singerList.get(0).getSinName();
                        for (int i = 1;i < singerList.size();i++) {
                            nameAndSingerName += "/"+singerList.get(i).getSinName();
                        }
                    }
                    textView.setText(nameAndSingerName);
                }

            }
        };
        String url = StaticHttp.BASEURL + StaticHttp.SELECT_SINGER;
        url += "?songId="+song.getSongId();
        HttpUtils.get(url,handler);
    }

    public void initOnClick(){
        backLin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadDownPopuWindow.getPopupWindow().dismiss();
            }
        });
    }

    public void findByIdAll(){
        this.backLin = this.slLoadDownView.findViewById(R.id.back_lin);
        this.slMoreRv = this.slLoadDownView.findViewById(R.id.sl_more_rv);
        this.img = this.slLoadDownView.findViewById(R.id.img);
        this.songName = this.slLoadDownView.findViewById(R.id.song_name);
        this.singerName = this.slLoadDownView.findViewById(R.id.singer_name);
    }

    public LoadDownPopuWindow getLoadDownPopuWindow() {
        return loadDownPopuWindow;
    }

    public void setLoadDownPopuWindow(LoadDownPopuWindow loadDownPopuWindow) {
        this.loadDownPopuWindow = loadDownPopuWindow;
    }
}
