package com.gxuwz.beethoven.page.fragment.playview;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;

import androidx.annotation.NonNull;

import com.gxuwz.beethoven.R;
import com.gxuwz.beethoven.component.lyc.LycicView;
import com.gxuwz.beethoven.dao.LyricDao;
import com.gxuwz.beethoven.dao.SongDao;
import com.gxuwz.beethoven.dao.ThreadDAO;
import com.gxuwz.beethoven.dao.ThreadDAOImpl;
import com.gxuwz.beethoven.model.entity.FileInfo;
import com.gxuwz.beethoven.model.entity.ThreadInfo;
import com.gxuwz.beethoven.model.entity.current.Lyric;
import com.gxuwz.beethoven.model.entity.current.Song;
import com.gxuwz.beethoven.service.DownloadService;
import com.gxuwz.beethoven.service.DownloadTask;
import com.gxuwz.beethoven.util.DateUtil;
import com.gxuwz.beethoven.util.HttpUtils;
import com.gxuwz.beethoven.util.Player;
import com.gxuwz.beethoven.util.lyc.LrcMusic;
import com.gxuwz.beethoven.util.lyc.Utils;
import com.gxuwz.beethoven.util.staticdata.StaticHttp;
import com.gxuwz.beethoven.util.string.URLEncoderHZ;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

public class LyricShowView {

    Context context;
    View lycShowView;
    LycicView view;
    private ArrayList<LrcMusic> lrcs;
    private ArrayList<String> list;
    private ArrayList<Long> list1;
    DownloadTask downloadTask;
    private int lrc_index;
    SharedPreferences sharedPreferences;
    SongDao songDao;
    LyricDao lyricDao;
    ThreadDAO mDao;

    public LyricShowView(Context context, View lycShowView) {
        this.context = context;
        sharedPreferences = context.getSharedPreferences("data", Context.MODE_PRIVATE);
        songDao = new SongDao(context);
        lyricDao = new LyricDao(context);
        mDao = new ThreadDAOImpl(context);
        this.lycShowView = lycShowView;
        this.view = lycShowView.findViewById(R.id.view);
        initEvents();
    }

    public void initEvents() {
        Long slId = sharedPreferences.getLong("slId",-1);
        Long songId = sharedPreferences.getLong("songId", -1);
        if(slId!=-1) {
            Song song = songDao.findById(songId);
            if (isLocalLyric(song)) {
                readLocalLyric(song);
            } else {
                readInternetLyric(song);
            }
            Player.lyriscView = view;
            Player.lyriscTimes = list1;
        }
    }

    public void readInternetLyric(Song song) {
        String url = "";
        if (song.getLyrUrl() != null) {
            url = StaticHttp.STATIC_BASEURL + song.getLyrUrl();
            Handler handler = new Handler(){
                @Override
                public void handleMessage(@NonNull Message msg) {
                    if(msg.what==1) {
                        readLocalLyric(song);
                    }
                }
            };
            HttpUtils.downFile(context,url,handler);
        }
    }

    public boolean isLocalLyric(Song song) {
        if(song.getLyrUrl()!=null) {
            String fileName = song.getLyrUrl().substring(song.getLyrUrl().lastIndexOf("/"));
            File file = new File(DownloadService.DOWNLOAD_PATH +"lrc"+ fileName);
            return file.exists();
        } else {
            return false;
        }
    }

    public void readLocalLyric(Song song) {
        String fileName = song.getLyrUrl().substring(song.getLyrUrl().lastIndexOf("/"));
        File file = new File(DownloadService.DOWNLOAD_PATH +"lrc"+ fileName);
        FileInputStream is = null;
        try {
            is = new FileInputStream(file);
            list = new ArrayList<String>();
            list1 = new ArrayList<>();
            lrcs = Utils.redLrc(is);
            for (int i = 0; i < lrcs.size(); i++) {
                list.add(lrcs.get(i).getLrc());
                list1.add((long) lrcs.get(i).getTime());
            }
            view.setLyricText(list, list1);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

}
