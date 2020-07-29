package com.gxuwz.beethoven.page.index.playview;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
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
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import com.gxuwz.beethoven.R;
import com.gxuwz.beethoven.receiver.PlayViewReceiver;
import com.gxuwz.beethoven.service.MusicService;
import com.gxuwz.beethoven.util.BlurUtil;
import com.gxuwz.beethoven.util.HttpUtil;
import com.gxuwz.beethoven.util.MergeImage;
import com.gxuwz.beethoven.util.Player;

public class ActivityPlayView extends AppCompatActivity implements View.OnClickListener {

    /**
     * 正在播放歌曲信息
     */
    SharedPreferences sharedPreferences;

    ImageView bg,disc,playingPlay;

    /**
     * 拖动条
     * 时间和当前进度
     */
    SeekBar musicSeekBar;
    TextView currentAndMaxTime;

    /**
     * 返回图片
     */
    ImageView toBack;
    PlayViewReceiver playViewReceiver;

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
         * 获取拖动条id
         * 获取当前进度和时间
         */
        musicSeekBar = findViewById(R.id.music_seek_bar);
        currentAndMaxTime = findViewById(R.id.current_and_max_time);
        Player.seekBar = musicSeekBar;
        Player.currentAndMaxTime = currentAndMaxTime;
        /**
         * 获取sharedPreferences对象
         */
        sharedPreferences = getSharedPreferences("data",MODE_PRIVATE);
        /**
         * 隐藏标题栏
         */
        getSupportActionBar().hide();
        bg = findViewById(R.id.bg);
        disc = findViewById(R.id.disc);
        playingPlay = findViewById(R.id.playing_play);
        bg.setImageBitmap(BlurUtil.doBlur(HttpUtil.getRes("p1",this), 10,5));
        disc.setImageBitmap(MergeImage.mergeThumbnailBitmap(HttpUtil.getRes("dibian",this),HttpUtil.getRes("p1",this)));
        if(Player.isPlayer==true) {
            MergeImage.playImage(disc);
        }
        init(this);
    }

    private void init(Context context){
        String songName = sharedPreferences.getString("songName",null);
        String singer = sharedPreferences.getString("singer",null);
        String songListUrlS = sharedPreferences.getString("songListUrl",null);
        if(songListUrlS!=null) {
            final Handler songListUrlImageHandler = new Handler() {
                public void handleMessage(android.os.Message songListBit) {
                    bg.setImageBitmap(BlurUtil.doBlur((Bitmap)songListBit.obj,10,5));
                    disc.setImageBitmap(MergeImage.mergeThumbnailBitmap(HttpUtil.getRes("dibian",context),(Bitmap)songListBit.obj));
                };
            };

            new Thread(){
                @Override
                public void run() {
                    Bitmap imageDate = HttpUtil.getImage(songListUrlS);
                    Message message = new Message();
                    message.obj = imageDate;
                    songListUrlImageHandler.sendMessage(message);
                }
            }.start();
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

        playViewReceiver = new PlayViewReceiver();
    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent();
        intent.setAction(MusicService.ACTION);
        switch (view.getId()) {
            case R.id.play_and_stop:{
                if(Player.isPlayer) {
                    intent.putExtra(MusicService.CONTROLLER,MusicService.CONTROLLER_FLAT0);
                }
            };break;
        }
    }
}
