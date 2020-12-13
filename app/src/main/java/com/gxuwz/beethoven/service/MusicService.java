package com.gxuwz.beethoven.service;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.widget.ImageView;

import androidx.annotation.Nullable;

import com.gxuwz.beethoven.dao.LocalSongDao;
import com.gxuwz.beethoven.dao.PlayListDao;
import com.gxuwz.beethoven.dao.SongDao;
import com.gxuwz.beethoven.listener.MusicCompletionListener;
import com.gxuwz.beethoven.model.entity.current.LocalSong;
import com.gxuwz.beethoven.model.entity.current.Song;
import com.gxuwz.beethoven.util.Player;
import com.gxuwz.beethoven.util.staticdata.StaticHttp;

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
    public static final int STOP = 0;
    public static final int PLAY = 1;
    public static final int PRPLAY = 2;
    /**
     * 歌曲路径
     */
    public static final String PLAYLIST = "playList";
    /**
     * 歌曲在列表变化
     */
    public static final String POSITION = "position";
    public static Player player;
    MyReceiver serviceReceiver;
    public static boolean isRun = false;
    public static ImageView ptTagBack;
    public static int position;
    SharedPreferences sharedPreferences;
    PlayListDao playListDao;
    SongDao songDao;
    LocalSongDao localSongDao;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        serviceReceiver = new MyReceiver();
        IntentFilter filter = new IntentFilter(ACTION);
        registerReceiver(serviceReceiver, filter);
        player = new Player();
        isRun = true;
        player.mediaPlayer.setOnCompletionListener(new MusicCompletionListener(MusicService.this));
    }

    public class MyReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            int control = intent.getIntExtra(CONTROLLER, -1);
            initData(context);
            Long slId = sharedPreferences.getLong("slId",-1);
            Long songId = sharedPreferences.getLong("songId",-1);
            String songUrl = "";
            System.out.println(slId+","+songId);
            if(slId!=-1) {
                Song song = songDao.findById(songId);
                songUrl = song.getStandardUrl();
                songUrl = StaticHttp.STATIC_BASEURL+songUrl;
            } else {
                LocalSong localSong = localSongDao.findBySongId(songId);
                if(localSong!=null)
                songUrl = localSong.getSongUrl();
            }
            switch (control) {
                //暂停
                case 0:{
                    Player.isPlayer = false;
                    player.pause();
                };break;
                //播放
                case 1:{
                    Player.isPlayer = true;
                    player.play();
                };break;
                //切换
                case 2:{
                    Player.isPlayer = true;
                    prepareAndPlay(songUrl);
                    player.play();
                };break;
            }
        }
    }

    private void initData(Context context){
        if(sharedPreferences==null) {
            sharedPreferences = context.getSharedPreferences("data",MODE_PRIVATE);
        }
        if(songDao==null) {
            songDao = new SongDao(context);
        }
        if(localSongDao==null) {
            localSongDao = new LocalSongDao(context);
        }
        if(playListDao==null) {
            playListDao = new PlayListDao(context);
        }
    }

    private void prepareAndPlay(String music) {
        try {
            if(player.mediaPlayer == null) {
                player.mediaPlayer = new MediaPlayer();
            }
            player.mediaPlayer.reset();
            player.mediaPlayer.setDataSource(music);
            player.mediaPlayer.prepare();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void musicCtrl(Context context,int ctrl){
        Intent intent = new Intent(ACTION);
        intent.putExtra(CONTROLLER, ctrl);
        context.sendBroadcast(intent);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unregisterReceiver(serviceReceiver);
    }
}
