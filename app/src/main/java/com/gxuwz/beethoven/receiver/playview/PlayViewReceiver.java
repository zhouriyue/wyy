package com.gxuwz.beethoven.receiver.playview;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.widget.ImageView;
import android.widget.TextView;

import com.gxuwz.beethoven.receiver.IndexBottomBarReceiver;
import com.gxuwz.beethoven.util.HttpUtil;
import com.gxuwz.beethoven.util.Player;

public class PlayViewReceiver extends BroadcastReceiver {

    public final static String ACTION = "playview";

    TextView songNameTV,singerNameTv;
    ImageView playingPlay;

    public PlayViewReceiver(TextView songName, TextView singerName, ImageView playingPlay) {
        this.songNameTV = songName;
        this.singerNameTv = singerName;
        this.playingPlay = playingPlay;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("data",Context.MODE_PRIVATE);
        String songName = sharedPreferences.getString("songName",null);
        String singerName = sharedPreferences.getString("singerName",null);
        playingPlay.setImageBitmap(HttpUtil.getRes("icon_stop_playview",context));
        songNameTV.setText(songName);
        singerNameTv.setText(singerName);
    }

    /**
     * 发生广播
     * @param context
     */
    public static void sendBroadcast(Context context){
        Intent indexBottomBar = new Intent(PlayViewReceiver.ACTION);
        context.sendBroadcast(indexBottomBar);
    }
}
