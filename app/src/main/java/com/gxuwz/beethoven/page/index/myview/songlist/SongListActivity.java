package com.gxuwz.beethoven.page.index.myview.songlist;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.gxuwz.beethoven.R;
import com.gxuwz.beethoven.hanlder.SongListsMusicHandler;
import com.gxuwz.beethoven.model.entity.SongList;
import com.gxuwz.beethoven.model.entity.SysUser;
import com.gxuwz.beethoven.util.BlurUtil;
import com.gxuwz.beethoven.util.HttpUtil;
import com.gxuwz.beethoven.util.MergeImage;

public class SongListActivity extends AppCompatActivity {

    /**
     * 正在播放歌曲信息
     */
    SharedPreferences sharedPreferences;
    /**
     * 显示用户所有歌单
     */
    RecyclerView songListMusic;
    /**
     * 歌单歌曲获取组件
     */
    SongListsMusicHandler songListsMusicHandler;
    /**
     * songListName: 歌单名
     * username：用户名
     * totalMusic：歌单歌曲数目
     */
    TextView songListName,username,totalMusic;
    /**
     * perPic: 个人头像
     * songListUrl：歌单封面图片
     * toBack：返回按钮
     */
    ImageView perPic,songListUrl,toBack,songListBg;
    /**
     * 歌单封面
     */
    public static Bitmap songListImage;
    /**
     * 广播识别标志
     */
    public static final String CTL_ACTION = "CTL_ACTION";
    public static final String UPDATE_ACTION = "UPDATE_ACTION";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(getResources().getColor(R.color.black));
        }
        setContentView(R.layout.activity_song_list);
        /**
         * 隐藏标题栏
         * hide title box
         */
        getSupportActionBar().hide();
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        SongList songList = (SongList) bundle.getSerializable("songList");
        SysUser sysUser = (SysUser) bundle.getSerializable("sysUser");
        songListName = findViewById(R.id.song_list_name);
        username = findViewById(R.id.username);
        totalMusic = findViewById(R.id.total_music);
        perPic = findViewById(R.id.per_pic);
        songListUrl = findViewById(R.id.song_list_url);
        toBack = findViewById(R.id.to_back);
        /**
         * 获取sharedPreferences对象
         */
        sharedPreferences = getSharedPreferences("data",MODE_PRIVATE);
        /**
         * 歌单背景
         */
        songListBg = findViewById(R.id.song_list_bg);
        toBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        songListName.setText(songList.getSongListName());
        username.setText(sysUser.getUserName());
        final Handler perPicViewHandle = new Handler() {
            public void handleMessage(android.os.Message msg) {
                perPic.setImageBitmap(MergeImage.circleShow((Bitmap) msg.obj));
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
                songListImage = (Bitmap) msg.obj;
                songListUrl.setImageBitmap(songListImage);
                songListBg.setImageBitmap(BlurUtil.doBlur((Bitmap)msg.obj,3,50));
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
        songListsMusicHandler = new SongListsMusicHandler(songList.getSongListUrl());
        songListsMusicHandler.setContext(SongListActivity.this);
        songListsMusicHandler.setSongListMusic(findViewById(R.id.song_list_music));
        songListsMusicHandler.setTotalMusic(totalMusic);
        songListsMusicHandler.setSharedPreferences(sharedPreferences);
        songListsMusicHandler.setSongList(songList);
        HttpUtil.get(songList.getSongListMusic(),songListsMusicHandler);
    }

}
