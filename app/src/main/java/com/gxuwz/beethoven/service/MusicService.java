package com.gxuwz.beethoven.service;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.media.MediaPlayer;
import android.os.IBinder;
import androidx.annotation.Nullable;

import com.gxuwz.beethoven.page.Index;
import com.gxuwz.beethoven.page.SongListActivity;
import com.gxuwz.beethoven.util.Player;

import java.io.IOException;

public class MusicService extends Service {
    Player player;
    MyReceiver serviceReceiver;
    static boolean isRun = false;

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
        String action = "CTL_ACTION";
        IntentFilter filter = new IntentFilter(action);
        registerReceiver(serviceReceiver, filter);
        isRun = true;
    }

    public class MyReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            int control = intent.getIntExtra("controller", -1);
            String songListUrl = intent.getStringExtra("songlisturl");
            int position = intent.getIntExtra("position", -1);
            System.out.println(control+songListUrl);
            if(control==1) {
                Player.isPlayer = true;
                prepareAndPlay(songListUrl);
                player.mediaPlayer.start();
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
