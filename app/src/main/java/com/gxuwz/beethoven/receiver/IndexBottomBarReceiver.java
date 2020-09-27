package com.gxuwz.beethoven.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.gxuwz.beethoven.util.BlurUtil;
import com.gxuwz.beethoven.util.HttpUtil;
import com.gxuwz.beethoven.util.MergeImage;

/**
 * Index Activity组件
 * 底部部分信息变化
 */
public class IndexBottomBarReceiver extends BroadcastReceiver {
    /**
     * 0表示暂停状态，按钮显示为play1
     * 1表示播放状态，按钮显示为stop
     */
    public static final int FLAT_PLAY = 1;
    public static final int FLAT_STOP = 0;
    public static final String CONTROLLER = "controller";
    /**
     * 广播接收唯一标识
     */
    public static final String ACTION = "INDEXBOTTOMBAR";

    SharedPreferences sharedPreferences;
    /**
     * playSongListRrl：底部头像
     * songName: 歌曲名
     * singer: 歌手
     * playAndStop：播放按钮
     */
    ImageView imgUriIV;
    TextView songNameTV,singerTv;
    ImageView playAndStop;

    public IndexBottomBarReceiver(ImageView imgUriIV, TextView songNameTV, TextView singer) {
        this.imgUriIV = imgUriIV;
        this.songNameTV = songNameTV;
        this.singerTv = singer;
    }

    public static void sendBroadcast(int controller, Context context){
        Intent indexBottomBar = new Intent(IndexBottomBarReceiver.ACTION);
        indexBottomBar.putExtra(IndexBottomBarReceiver.CONTROLLER,controller);
        context.sendBroadcast(indexBottomBar);
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        int controller = intent.getIntExtra(CONTROLLER,-1);
        if(controller==0) {
            playAndStop.setImageBitmap(HttpUtil.getRes("icon_play1",context));
        } else {
            playAndStop.setImageBitmap(HttpUtil.getRes("stop_bar",context));
            sharedPreferences = context.getSharedPreferences("data",Context.MODE_PRIVATE);
            String songListUri = sharedPreferences.getString("songListUri",null);
            String songName = sharedPreferences.getString("songName",null);
            String singerName = sharedPreferences.getString("singerName",null);
            songNameTV.setText(songName);
            singerTv.setText(singerName);
            final Handler imgUriHandler = new Handler(){
                @Override
                public void handleMessage(@NonNull Message msg) {
                    Bitmap img = (Bitmap) msg.obj;
                    if(msg.obj==null){
                        imgUriIV.setImageBitmap(MergeImage.circleShow(HttpUtil.getRes("icon_singer_default",context)));
                    } else {
                        imgUriIV.setImageBitmap(MergeImage.circleShow(img));
                    }
                }
            };
            HttpUtil.getImage(songListUri,imgUriHandler);
        }
    }

    public ImageView getPlayAndStop() {
        return playAndStop;
    }

    public void setPlayAndStop(ImageView playAndStop) {
        this.playAndStop = playAndStop;
    }
}
