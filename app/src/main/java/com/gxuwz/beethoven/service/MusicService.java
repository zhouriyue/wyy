package com.gxuwz.beethoven.service;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.IBinder;
import android.widget.ImageView;

import androidx.annotation.Nullable;

import com.gxuwz.beethoven.listener.MusicCompletionListener;
import com.gxuwz.beethoven.page.index.Index;
import com.gxuwz.beethoven.receiver.IndexBottomBarReceiver;
import com.gxuwz.beethoven.util.Player;

import java.io.IOException;

public class MusicService extends Service {
    /**
     * 广播标识
     */
    public static final String ACTION = "CTL_ACTION";
    /**
     * 控制信号
     */
    public static final String CONTROLLER = "controller";
    /**
     * 控制信息
     * 1表示播放
     * 0表示暂停
     */
    public static final int CONTROLLER_FLAT0 = 0;
    public static final int CONTROLLER_FLAT1 = 1;
    /**
     * 歌曲路径
     */
    public static final String SONGURL = "songUrl";
    /**
     * 歌曲在列表变化
     */
    public static final String POSITION = "position";
    Player player;
    MyReceiver serviceReceiver;
    public static boolean isRun = false;
    public static ImageView ptTagBack;
    public static int position;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        player = new Player();
        serviceReceiver = new MyReceiver();

        IntentFilter filter = new IntentFilter(ACTION);
        registerReceiver(serviceReceiver, filter);
        isRun = true;
        player.mediaPlayer.setOnCompletionListener(new MusicCompletionListener(MusicService.this));
    }

    public class MyReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            int control = intent.getIntExtra(CONTROLLER, -1);
            String songListUrl = intent.getStringExtra(SONGURL);
            int position = intent.getIntExtra(POSITION, -1);
            switch (control) {
                case 0:{
                    Player.isPlayer = false;
                    player.pause();
                };break;
                case 1:{
                    Player.isPlayer = true;
                    player.play();
                };break;
                case 2:{
                    Player.isPlayer = true;
                    prepareAndPlay(songListUrl);
                    player.play();
                };break;
            }
        }
    }

    private void prepareAndPlay(String music) {
        try {
            player.mediaPlayer.reset();
            player.mediaPlayer.setDataSource(music);
            player.mediaPlayer.prepare();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
