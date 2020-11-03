package com.gxuwz.beethoven.page.fragment.playview;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
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
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.gxuwz.beethoven.R;
import com.gxuwz.beethoven.adapter.PagerCustomAdapter;
import com.gxuwz.beethoven.dao.LocalSongDao;
import com.gxuwz.beethoven.dao.SingerDao;
import com.gxuwz.beethoven.dao.SongDao;
import com.gxuwz.beethoven.model.entity.current.LocalSong;
import com.gxuwz.beethoven.model.entity.current.Singer;
import com.gxuwz.beethoven.model.entity.current.Song;
import com.gxuwz.beethoven.receiver.IndexBottomBarReceiver;
import com.gxuwz.beethoven.receiver.playview.PlayViewReceiver;
import com.gxuwz.beethoven.service.MusicService;
import com.gxuwz.beethoven.util.BlurUtil;
import com.gxuwz.beethoven.util.HttpUtil;
import com.gxuwz.beethoven.util.MergeImage;
import com.gxuwz.beethoven.util.Player;
import com.gxuwz.beethoven.util.staticdata.StaticHttp;

import java.util.ArrayList;
import java.util.List;

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
    SongDao songDao;
    SingerDao singerDao;
    LocalSongDao localSongDao;

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
        sharedPreferences = getSharedPreferences("data",MODE_PRIVATE);
        songDao = new SongDao(this);
        singerDao = new SingerDao(this);
        localSongDao = new LocalSongDao(this);
        init(this);
    }

    private void init(Context context) {
        dataInit();
        messShow();
        lyricShowView = new LyricShowView(context,views.get(1));
        sendPlayBroadcast();
    }

    /**
     * 发送广播
     */
    public void sendPlayBroadcast(){
        playViewReceiver = new PlayViewReceiver(songNameTV, singerNameTV, playingPlay,bg,disc,lyricShowView);
        IntentFilter intentFilter = new IntentFilter(PlayViewReceiver.ACTION);
        registerReceiver(playViewReceiver, intentFilter);
    }

    public void messShow(){
        Long slId = sharedPreferences.getLong("slId",-1);
        Long songId = sharedPreferences.getLong("songId",-1);
        if(slId!=-1) {
            System.out.println("slId:"+slId);
            Song song = songDao.findById(songId);
            if(song!=null) {
                String url = StaticHttp.STATIC_BASEURL+song.getCoverPicture();
                Glide.with(context)
                        .asBitmap()
                        .diskCacheStrategy(DiskCacheStrategy.RESOURCE)//缓存
                        .load(url)
                        .centerCrop()
                        .into(new SimpleTarget<Bitmap>() {
                            @Override
                            public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {
                                bg.setImageBitmap(BlurUtil.doBlur(resource, 10, 30));
                                disc.setImageBitmap(MergeImage.mergeThumbnailBitmap(HttpUtil.getRes("dibian", context), resource));
                            }
                        });
                songNameTV.setText(song.getSongName());
                List<Singer> singerList = singerDao.findSongSiner(song.getSongId());
                String singrs = "";
                if(singerList!=null&&singerList.size()!=0) {
                    singrs += singerList.get(0).getSinName();
                    for(int i = 1;i < singerList.size();i++) {
                        singrs += singerList.get(i).getSinName();
                    }
                } else {
                    singrs = "未知艺术家";
                }
                singerNameTV.setText(singrs);
            }
        } else {
            LocalSong localSong = localSongDao.findBySongId(songId);
            Glide.with(context)
                    .asBitmap()
                    .diskCacheStrategy(DiskCacheStrategy.RESOURCE)//缓存
                    .load(StaticHttp.DEFALUT_SONGLIST_COVERPICTURE)
                    .centerCrop()
                    .into(new SimpleTarget<Bitmap>() {
                        @Override
                        public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {
                            bg.setImageBitmap(BlurUtil.doBlur(resource, 10, 30));
                            disc.setImageBitmap(MergeImage.mergeThumbnailBitmap(HttpUtil.getRes("dibian", context), resource));
                        }
                    });
            songNameTV.setText(localSong.getSongName());
            singerNameTV.setText(localSong.getSingerName());
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
        Player.setSeekBarChange();
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
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(playViewReceiver);
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
                    intent.putExtra(MusicService.CONTROLLER, MusicService.STOP);
                    indexBottomBar.putExtra(IndexBottomBarReceiver.CONTROLLER, IndexBottomBarReceiver.FLAT_STOP);
                } else {
                    playingPlay.setImageBitmap(HttpUtil.getRes("icon_stop_playview", context));
                    intent.putExtra(MusicService.CONTROLLER, MusicService.PLAY);
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
