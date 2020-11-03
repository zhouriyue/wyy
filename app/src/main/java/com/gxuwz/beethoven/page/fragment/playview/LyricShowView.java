package com.gxuwz.beethoven.page.fragment.playview;

import android.app.Service;
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
import com.gxuwz.beethoven.util.HttpUtil;
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
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
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
    Lyric lyric;

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
            lyric = lyricDao.findById(song.getLyrId());
            if (lyric == null) {
                Handler handler = new Handler() {
                    @Override
                    public void handleMessage(@NonNull Message msg) {
                        if (msg.what == 1) {
                            Bundle bundle = msg.getData();
                            String result = bundle.getString("result");
                            try {
                                JSONObject jsonObject = new JSONObject(result);
                                if (lyric == null) lyric = new Lyric();
                                lyric.setLyrId(jsonObject.getLong("lyrId"));
                                lyric.setLyrName(jsonObject.getString("lyrName"));
                                lyric.setLyrUrl(jsonObject.getString("lyrUrl"));
                                lyric.setIssuingDate(DateUtil.parseString(jsonObject.getString("issuingDate")));
                                lyricDao.insert(lyric);
                                readInternetLyric(lyric);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                };
                String lyrUrl = StaticHttp.BASEURL + StaticHttp.SELECT_LYRIC;
                lyrUrl += "?lyrId=" + song.getLyrId();
                HttpUtil.get(lyrUrl, handler);
            } else {
                if (isLocalLyric(lyric)) {
                    if(!isDownloading(lyric)) {
                        readLocalLyric(lyric);
                    }
                    else {
                        readInternetLyric(lyric);
                    }
                } else {
                    readInternetLyric(lyric);
                }
            }
            Player.lyriscView = view;
            Player.lyriscTimes = list1;
        }
    }

    public void readInternetLyric(Lyric lyric) {
        String url = "";
        try {
            if (lyric.getLyrUrl() != null)
                url = StaticHttp.STATIC_BASEURL + URLEncoderHZ.encodeUtf8(lyric.getLyrUrl());
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        FileInfo fileInfo = new FileInfo(0, url, lyric.getLyrName(), 12913518, 0);
        Handler handler = new Handler() {
            @Override
            public void handleMessage(@NonNull Message msg) {
                if (msg.what == 1) {
                    readLocalLyric(lyric);
                }
            }
        };
        downloadTask = new DownloadTask(context, fileInfo, handler);
        downloadTask.download();
    }

    public boolean isLocalLyric(Lyric lyric) {
        String path = lyric.getLyrUrl().split("/static/")[1];
        File file = new File(DownloadService.DOWNLOAD_PATH + path);
        return file.exists();
    }

    public void readLocalLyric(Lyric lyric) {
        String path = lyric.getLyrUrl().split("/static/")[1];
        File file = new File(DownloadService.DOWNLOAD_PATH + path);
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

    public boolean isDownloading(Lyric lyric) {
        String url = "";
        try {
            if (lyric.getLyrUrl() != null)
                url = StaticHttp.STATIC_BASEURL + URLEncoderHZ.encodeUtf8(lyric.getLyrUrl());
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        List<ThreadInfo> threadList = mDao.getThreads(url);
        if (threadList.size() != 0) {
            return true;
        } else {
            return false;
        }
    }
}
