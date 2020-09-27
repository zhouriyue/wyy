package com.gxuwz.beethoven.page.index.playview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Filter;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import com.gxuwz.beethoven.R;
import com.gxuwz.beethoven.adapter.PagerCustomAdapter;
import com.gxuwz.beethoven.receiver.IndexBottomBarReceiver;
import com.gxuwz.beethoven.receiver.playview.PlayViewReceiver;
import com.gxuwz.beethoven.service.MusicService;
import com.gxuwz.beethoven.util.BlurUtil;
import com.gxuwz.beethoven.util.HttpUtil;
import com.gxuwz.beethoven.util.MergeImage;
import com.gxuwz.beethoven.util.Player;

import java.util.ArrayList;

public class ActivityPlayView extends AppCompatActivity implements View.OnClickListener {

    Context context;

    /**
     * 正在播放歌曲信息
     */
    SharedPreferences sharedPreferences;


    ImageView bg, disc, playingPlay, playingLast, playingNext;

    /**
     * 拖动条
     * 时间和当前进度
     */
    SeekBar musicSeekBar;
    TextView currentAndMaxTime, songNameTV, singerNameTV;

    /**
     * 返回图片
     */
    ImageView toBack;
    PlayViewReceiver playViewReceiver;
    ObjectAnimator objectAnimator;

    /**
     * 中间播放显示
     */
    ViewPager viewPager;
    ArrayList<View> views;

    LyricShowView lyricShowView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(getResources().getColor(R.color.black));
        }
        setContentView(R.layout.activity_play_view);
        /**
         * 隐藏标题栏
         */
        getSupportActionBar().hide();
        init(this);
    }

    private void init(Context context) {
        dataInit();
        messShow();
        sendPlayBroadcast();
        lyricShowView = new LyricShowView(context,views.get(1));
    }

    /**
     * 发送广播
     */
    public void sendPlayBroadcast(){
        PlayViewReceiver playViewReceiver = new PlayViewReceiver(songNameTV, singerNameTV, playingPlay);
        IntentFilter intentFilter = new IntentFilter(PlayViewReceiver.ACTION);
        registerReceiver(playViewReceiver, intentFilter);
    }

    public void messShow(){
        String songName = sharedPreferences.getString("songName", null);
        String singer = sharedPreferences.getString("singer", null);
        String songListUrlS = sharedPreferences.getString("songListUrl", null);
        if (songListUrlS != null) {
            final Handler songListUrlImageHandler = new Handler() {
                public void handleMessage(android.os.Message songListBit) {
                    if(songListBit.obj==null) {
                        bg.setImageBitmap(BlurUtil.doBlur(HttpUtil.getRes("p1",context), 10, 5));
                    } else {
                        bg.setImageBitmap(BlurUtil.doBlur((Bitmap) songListBit.obj, 10, 5));
                    }
                    disc.setImageBitmap(MergeImage.mergeThumbnailBitmap(HttpUtil.getRes("dibian", context), (Bitmap) songListBit.obj));
                }

                ;
            };

            new Thread() {
                @Override
                public void run() {
                    Bitmap imageDate = HttpUtil.getImage(songListUrlS);
                    Message message = new Message();
                    message.obj = imageDate;
                    songListUrlImageHandler.sendMessage(message);
                }
            }.start();
        }
    }

    public void dataInit(){
        /**
         * 获取拖动条id
         * 获取当前进度和时间
         */
        context = ActivityPlayView.this;
        musicSeekBar = findViewById(R.id.music_seek_bar);
        currentAndMaxTime = findViewById(R.id.current_and_max_time);
        Player.seekBar = musicSeekBar;
        Player.currentAndMaxTime = currentAndMaxTime;
        /**
         * 获取sharedPreferences对象
         */
        sharedPreferences = getSharedPreferences("data", MODE_PRIVATE);
        String songName = sharedPreferences.getString("songName", null);
        String singerName = sharedPreferences.getString("songName", null);
        viewPager = findViewById(R.id.play_view_vp);
        views = new ArrayList<View>();
        views.add(LayoutInflater.from(context).inflate(R.layout.spin_disc,null));
        views.add(LayoutInflater.from(context).inflate(R.layout.lyric_show,null));
        bg = findViewById(R.id.bg);
        disc = views.get(0).findViewById(R.id.disc);
        initPager();
        songNameTV = findViewById(R.id.song_name);
        songNameTV.setText(songName);
        singerNameTV = findViewById(R.id.singer);
        singerNameTV.setText(singerName);
        playingLast = findViewById(R.id.playing_last);
        playingLast.setOnClickListener(this);
        playingPlay = findViewById(R.id.playing_play);
        if (Player.isPlayer) {
            playingPlay.setImageBitmap(HttpUtil.getRes("icon_stop_playview", context));
        } else {
            playingPlay.setImageBitmap(HttpUtil.getRes("play_whick", context));
        }
        playingPlay.setOnClickListener(this);
        playingNext = findViewById(R.id.playing_next);
        playingNext.setOnClickListener(this);
        bg.setImageBitmap(BlurUtil.doBlur(HttpUtil.getRes("p1", this), 10, 5));
        disc.setImageBitmap(MergeImage.mergeThumbnailBitmap(HttpUtil.getRes("dibian", this), HttpUtil.getRes("p1", this)));
        if (Player.isPlayer == true) {
            objectAnimator = MergeImage.playImage(disc);
        }
        /**
         * 头部按钮
         */
        toBack = findViewById(R.id.to_back);
        toBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    public void initPager(){
        viewPager.setAdapter(new PagerCustomAdapter(views));
        viewPager.setCurrentItem(0);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.playing_play: {
                /**
                 * 底部
                 * 播放或停止音乐
                 */
                Intent intent = new Intent();
                intent.setAction(MusicService.ACTION);
                Intent indexBottomBar = new Intent();
                indexBottomBar.setAction(IndexBottomBarReceiver.ACTION);
                if (Player.isPlayer) {
                    playingPlay.setImageBitmap(HttpUtil.getRes("play_whick", context));
                    intent.putExtra(MusicService.CONTROLLER, MusicService.CONTROLLER_FLAT0);
                    indexBottomBar.putExtra(IndexBottomBarReceiver.CONTROLLER, IndexBottomBarReceiver.FLAT_STOP);
                } else {
                    playingPlay.setImageBitmap(HttpUtil.getRes("icon_stop_playview", context));
                    intent.putExtra(MusicService.CONTROLLER, MusicService.CONTROLLER_FLAT1);
                    indexBottomBar.putExtra(IndexBottomBarReceiver.CONTROLLER, IndexBottomBarReceiver.FLAT_PLAY);
                }
                sendBroadcast(intent);
                sendBroadcast(indexBottomBar);
            }
            ;
            break;
            case R.id.playing_last: {
                Player.lastOne(context);
            }
            ;
            break;
            case R.id.playing_next: {
                Player.nextOne(context);
            };
            break;
        }
    }
}
