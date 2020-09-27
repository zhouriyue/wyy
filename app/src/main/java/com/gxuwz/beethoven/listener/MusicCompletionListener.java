package com.gxuwz.beethoven.listener;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;

import com.gxuwz.beethoven.dao.PlayListDao;
import com.gxuwz.beethoven.model.entity.PlayList;
import com.gxuwz.beethoven.receiver.IndexBottomBarReceiver;
import com.gxuwz.beethoven.util.Player;

import java.util.ArrayList;
import java.util.List;

public class MusicCompletionListener implements MediaPlayer.OnCompletionListener{

    Context context;
    SharedPreferences sharedPreferences;
    Intent intent;

    public MusicCompletionListener(Context context) {
        this.context = context;
    }

    @Override
    public void onCompletion(MediaPlayer mediaPlayer) {
        Player.nextOne(context);
    }

}
