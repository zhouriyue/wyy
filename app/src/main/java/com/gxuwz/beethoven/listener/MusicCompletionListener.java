package com.gxuwz.beethoven.listener;

import android.content.Context;
import android.media.MediaPlayer;

import com.gxuwz.beethoven.receiver.IndexBottomBarReceiver;

public class MusicCompletionListener implements MediaPlayer.OnCompletionListener{

    Context context;

    public MusicCompletionListener(Context context) {
        this.context = context;
    }

    @Override
    public void onCompletion(MediaPlayer mediaPlayer) {
        IndexBottomBarReceiver.sendBroadcast(IndexBottomBarReceiver.FLAT_STOP,context);
    }
}
