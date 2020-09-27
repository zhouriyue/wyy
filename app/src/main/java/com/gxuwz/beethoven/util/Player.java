package com.gxuwz.beethoven.util;

import java.io.IOException;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnBufferingUpdateListener;
import android.media.MediaPlayer.OnCompletionListener;
import android.media.MediaPlayer.OnPreparedListener;
import android.os.Handler;
import android.util.Log;
import android.widget.SeekBar;
import android.widget.TextView;

import com.gxuwz.beethoven.component.lyc.LycicView;
import com.gxuwz.beethoven.dao.PlayListDao;
import com.gxuwz.beethoven.model.entity.PlayList;
import com.gxuwz.beethoven.page.index.playlistview.CurrentPlayView;
import com.gxuwz.beethoven.receiver.IndexBottomBarReceiver;
import com.gxuwz.beethoven.receiver.playview.PlayViewReceiver;
import com.gxuwz.beethoven.util.lyc.LrcMusic;

public class Player implements OnBufferingUpdateListener, OnCompletionListener, OnPreparedListener {

    public static MediaPlayer mediaPlayer; // 媒体播放器
    public static SeekBar seekBar; // 拖动条
    public static LycicView lyriscView;
    public static List<Long> lyriscTimes;
    public static TextView currentAndMaxTime;
    private Timer mTimer = new Timer(); // 计时器
    public static boolean isPlayer = false;

    // 初始化播放器
    public Player() {
        super();
        try {
            mediaPlayer = new MediaPlayer();
            mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);// 设置媒体流类型
            mediaPlayer.setOnBufferingUpdateListener(this);
            mediaPlayer.setOnPreparedListener(this);
        } catch (Exception e) {
            e.printStackTrace();
        }
        // 每一秒触发一次
        mTimer.schedule(timerTask, 0, 1000);
    }

    // 计时器
    TimerTask timerTask = new TimerTask() {

        @Override
        public void run() {
            if (mediaPlayer == null)
                return;
            if (mediaPlayer.isPlaying()) {
                handler.sendEmptyMessage(0); // 发送消息
            }
        }
    };

    /**
     * 上一首
     */
    public static void lastOne(Context context) {
        PlayList playList = null;
        PlayListDao playListDao = new PlayListDao(context);
        SharedPreferences sharedPreferences = context.getSharedPreferences("data", Context.MODE_PRIVATE);
        int id = sharedPreferences.getInt("id", -1);
        List<Integer> idList = playListDao.findAllId();
        int index = idList.indexOf(id);
        if (index != -1) {
            if (index - 1 <= 0) {
                index = idList.size() - 1;
            } else {
                index -= 1;
            }
            playList = playListDao.findById(idList.get(index));
        }
        saveSharedPreferences(sharedPreferences, playList);
        sendBroadcast(context, 1, playList);
        IndexBottomBarReceiver.sendBroadcast(IndexBottomBarReceiver.FLAT_PLAY, context);
        PlayViewReceiver.sendBroadcast(context);
        Player.isPlayer = true;
    }

    public static void playCurrent(Context context) {
        PlayList playList = new PlayList();
        SharedPreferences sharedPreferences = context.getSharedPreferences("data", Context.MODE_PRIVATE);
        playList.setId(sharedPreferences.getInt("id", -1));
        playList.setSongName(sharedPreferences.getString("songName", null));
        playList.setSingerName(sharedPreferences.getString("singerName", null));
        playList.setSongTime(sharedPreferences.getInt("songTime", -1));
        playList.setNetworkUri(sharedPreferences.getString("networkUri", null));
        playList.setLocalUri(sharedPreferences.getString("localUri", null));
        playList.setSongListUri(sharedPreferences.getString("localUri", null));
        sendBroadcast(context, playList);
    }

    /**
     * 下一首
     */
    public static void nextOne(Context context) {
        PlayList playList = null;
        PlayListDao playListDao = new PlayListDao(context);
        SharedPreferences sharedPreferences = context.getSharedPreferences("data", Context.MODE_PRIVATE);
        int id = sharedPreferences.getInt("id", -1);
        List<Integer> idList = playListDao.findAllId();
        int index = idList.indexOf(id);
        if (index != -1) {
            if (index + 1 >= idList.size()) {
                index = 0;
            } else {
                index += 1;
            }
        }
        playList = playListDao.findById(idList.get(index));
        saveSharedPreferences(sharedPreferences, playList);
        sendBroadcast(context, 1, playList);
        IndexBottomBarReceiver.sendBroadcast(IndexBottomBarReceiver.FLAT_PLAY, context);
        PlayViewReceiver.sendBroadcast(context);
        Player.isPlayer = true;
    }

    public static void sendBroadcast(Context context, int position, PlayList playList) {
        Intent intent = new Intent("CTL_ACTION");
        intent.putExtra("controller", 2);
        intent.putExtra("playList", playList);
        intent.putExtra("position", position);
        context.sendBroadcast(intent);
    }

    public static void sendBroadcast(Context context, PlayList playList) {
        Intent intent = new Intent("CTL_ACTION");
        intent.putExtra("controller", 2);
        intent.putExtra("playList", playList);
        context.sendBroadcast(intent);
    }

    public static void freshPlayList(Context context) {
        Intent intent = new Intent(CurrentPlayView.FreshPlayListReceiver.ACTION);
        context.sendBroadcast(intent);
    }

    public static void saveSharedPreferences(SharedPreferences sharedPreferences, PlayList playList) {
        /**
         * 设置播放歌曲的歌名、歌手、歌曲
         */
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("id", playList.getId());
        editor.putString("songName", playList.getSongName());
        editor.putString("singerName", playList.getSingerName());
        editor.putInt("songTime", playList.getSongTime());
        editor.putString("localUri", playList.getLocalUri());
        editor.putString("networkUri", playList.getNetworkUri());
        editor.putString("songListUri", playList.getSongListUri());
        /**
         * playStatus==1表示播放
         * playStatus==0表示停止
         */
        editor.putString("playStatus", "1");
        editor.commit();
    }

    @SuppressLint("HandlerLeak")
    Handler handler = new Handler() {
        public void handleMessage(android.os.Message msg) {
            int position = mediaPlayer.getCurrentPosition();
            int duration = mediaPlayer.getDuration();
            if (duration > 0) {
                // 计算进度（获取进度条最大刻度*当前音乐播放位置 / 当前音乐时长）
                long currentTime = 0;
                long totalTime = 0;
                if (seekBar != null) {
                    long pos = seekBar.getMax() * position / duration;
                    seekBar.setProgress((int) pos);
                    currentTime = position / 1000;
                    totalTime = duration / 1000;
                    currentAndMaxTime.setText(DateUtil.sToDate((int)currentTime) + "/" + DateUtil.sToDate((int)totalTime));
                }
                /**
                 * 歌词显示
                 */
                if (lyriscView != null&&lyriscTimes!=null) {
                    int lryIndex = lyriscTimes.indexOf(currentTime);
                    if (lryIndex != -1) {
                        lyriscView.scrollToIndex(lryIndex);
                    }
                }
            }
        }

        ;
    };

    /**
     * 播放
     */
    public void play() {
        isPlayer = true;
        mediaPlayer.start();
    }

    /**
     * @param url url地址
     */
    public void playUrl(String url) {
        try {
            mediaPlayer.reset();
            mediaPlayer.setDataSource(url); // 设置数据源
            mediaPlayer.prepare(); // prepare自动播放
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (SecurityException e) {
            e.printStackTrace();
        } catch (IllegalStateException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // 暂停
    public void pause() {
        mediaPlayer.pause();
    }

    // 停止
    public void stop() {
        if (mediaPlayer != null) {
            mediaPlayer.stop();
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }

    public void start() {
        if (mediaPlayer != null) {
            mediaPlayer.start();
        }
    }

    // 播放准备
    @Override
    public void onPrepared(MediaPlayer mp) {
        mp.start();
    }

    // 播放完成
    @Override
    public void onCompletion(MediaPlayer mp) {

    }

    /**
     * 缓冲更新
     */
    @Override
    public void onBufferingUpdate(MediaPlayer mp, int percent) {
    }

}