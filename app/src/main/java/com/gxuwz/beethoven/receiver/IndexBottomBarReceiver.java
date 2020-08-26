package com.gxuwz.beethoven.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.ImageView;

import com.gxuwz.beethoven.util.HttpUtil;

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
    ImageView playAndStop;

    public static void sendBroadcast(int controller,Context context){
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
        }
    }

    public ImageView getPlayAndStop() {
        return playAndStop;
    }

    public void setPlayAndStop(ImageView playAndStop) {
        this.playAndStop = playAndStop;
    }
}
