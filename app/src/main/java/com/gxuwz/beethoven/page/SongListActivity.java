package com.gxuwz.beethoven.page;

import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.gxuwz.beethoven.R;
import com.gxuwz.beethoven.hanlder.SongListsMusicHandler;
import com.gxuwz.beethoven.model.entity.SongList;
import com.gxuwz.beethoven.model.entity.SysUser;
import com.gxuwz.beethoven.service.MusicService;
import com.gxuwz.beethoven.util.HttpUtil;

import java.io.Serializable;

public class SongListActivity extends Activity {

    RecyclerView songListMusic;
    SongListsMusicHandler songListsMusicHandler;
    TextView songListName,username;
    ImageView perPic,songListUrl,toBack;
    public static final String CTL_ACTION = "CTL_ACTION";
    public static final String UPDATE_ACTION = "UPDATE_ACTION";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_song_list);
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        SongList songList = (SongList) bundle.getSerializable("songList");
        SysUser sysUser = (SysUser) bundle.getSerializable("sysUser");
        songListName = findViewById(R.id.song_list_name);
        username = findViewById(R.id.username);
        perPic = findViewById(R.id.per_pic);
        songListUrl = findViewById(R.id.song_list_url);
        toBack = findViewById(R.id.to_back);
        toBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        songListName.setText(songList.getSongListId());
        username.setText(sysUser.getUserName());
        final Handler perPicViewHandle = new Handler() {
            public void handleMessage(android.os.Message msg) {
                perPic.setImageBitmap((Bitmap) msg.obj);
            };
        };
        new Thread(){
            @Override
            public void run() {
                Bitmap imageDate = HttpUtil.getImage(sysUser.getPerPic());
                Message msg = new Message();
                msg.obj = imageDate;
                perPicViewHandle.sendMessage(msg);
            }
        }.start();
        final Handler songListUrlHandle = new Handler() {
            public void handleMessage(android.os.Message msg) {
                songListUrl.setImageBitmap((Bitmap) msg.obj);
            };
        };

        new Thread(){
            @Override
            public void run() {
                Bitmap imageDate = HttpUtil.getImage(songList.getSongListUrl());
                Message msg = new Message();
                msg.obj = imageDate;
                songListUrlHandle.sendMessage(msg);
            }
        }.start();
        songListsMusicHandler = new SongListsMusicHandler();
        songListsMusicHandler.setContext(SongListActivity.this);
        songListsMusicHandler.setSongListMusic(findViewById(R.id.song_list_music));
        HttpUtil.get(songList.getSongListMusic(),songListsMusicHandler);

        Intent music = new Intent(this, MusicService.class);
        startService(music);
    }

}
