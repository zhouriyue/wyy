package com.gxuwz.beethoven.page.fragment.my.ring;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.media.MediaPlayer;
import android.media.Ringtone;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;

import com.gxuwz.beethoven.R;
import com.gxuwz.beethoven.model.entity.current.LocalSong;
import com.gxuwz.beethoven.util.MergeImage;
import com.gxuwz.beethoven.util.MusicUtils;
import com.gxuwz.beethoven.util.WindowPixels;

import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

/**
 * 铃声试听弹框
 */
public class RingTestPW {

    Context context;

    PopupWindow popupWindow;

    View ringTestView;

    Timer timer;

    MediaPlayer mediaPlayer;

    SeekBar seekBar;

    public RingTestPW(Context context) {
        this.context = context;
    }

    // 计时器
    class MyTimerTask extends TimerTask {
        @Override
        public void run() {
            if(mediaPlayer.isPlaying()) {
                handler.sendEmptyMessage(0); // 发送消息
            }
        }
    };

    Handler handler = new Handler(){
        @RequiresApi(api = Build.VERSION_CODES.P)
        @Override
        public void handleMessage(@NonNull Message msg) {
            int position = mediaPlayer.getCurrentPosition();
            int duration = mediaPlayer.getDuration();
            if (duration > 0) {
                // 计算进度（获取进度条最大刻度*当前音乐播放位置 / 当前音乐时长）
                if (seekBar != null) {
                    long pos = seekBar.getMax() * position / duration;
                    seekBar.setProgress((int) pos);
                }
            }
        }
    };

    public void showAtLocation(View view,LocalSong localSong){
        getPopupWindow(localSong);
        popupWindow.showAtLocation(view, Gravity.LEFT,0,0);
        timer = new Timer();
        timer.schedule(new MyTimerTask(),0,1000);
        try {
            if(mediaPlayer!=null) {
                String url = MusicUtils.setRingtoneStr(context,localSong.getSongUrl(),MusicUtils.RINGTONE);
                mediaPlayer.reset();
                mediaPlayer.setDataSource(url);
                mediaPlayer.prepare();
                mediaPlayer.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void ringListererTest(LocalSong localSong){
        TextView songName = ringTestView.findViewById(R.id.song_name);
        songName.setText(localSong.getSongName());
        seekBar = ringTestView.findViewById(R.id.seebar);
        ImageView ctrl = ringTestView.findViewById(R.id.ctrl);
        mediaPlayer = MusicUtils.setRingtoneImpl(context,localSong.getSongUrl(),MusicUtils.RINGTONE);
        try {
            mediaPlayer.prepare();
            mediaPlayer.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
        MergeImage.showGlideImgDb(context,R.drawable.stop1,ctrl,1);
        ctrl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mediaPlayer.isPlaying()) {
                    mediaPlayer.pause();
                    MergeImage.showGlideImgDb(context,R.drawable.play3,ctrl,1);
                } else {
                    mediaPlayer.start();
                    MergeImage.showGlideImgDb(context,R.drawable.stop1,ctrl,1);
                }
            }
        });

    }

    protected  void  initPopupWindow(LocalSong localSong){
        int width = (int) (WindowPixels.WIDTH*WindowPixels.DENSITY)+3;
        ringTestView = LayoutInflater.from(context).inflate(R.layout.ring_test,null);
        popupWindow = new PopupWindow(ringTestView,width, ViewGroup.LayoutParams.MATCH_PARENT,true);
        popupWindow.setBackgroundDrawable(new ColorDrawable(0));
        LinearLayout box = ringTestView.findViewById(R.id.box);
        int y1 = (int) ((WindowPixels.WIDTH - 80)/2*WindowPixels.DENSITY);
        int y2 = (int) (y1 + 80*WindowPixels.DENSITY);
        ringTestView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(event.getY()<y1||event.getY()>y2) {
                    timer.cancel();
                    timer = null;
                    mediaPlayer.stop();
                    popupWindow.dismiss();
                }
                return false;
            }
        });
        ringListererTest(localSong);
    }

    private  void  getPopupWindow(LocalSong localSong) {
        if (null != popupWindow) {
            popupWindow.dismiss();
            return;
        } else {
            initPopupWindow(localSong);
        }
    }

}
