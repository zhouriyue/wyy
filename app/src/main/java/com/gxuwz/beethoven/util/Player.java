package com.gxuwz.beethoven.util;

import java.io.IOException;
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
import android.widget.SeekBar;
import android.widget.TextView;

import com.gxuwz.beethoven.component.lyc.LycicView;
import com.gxuwz.beethoven.dao.PlayListDao;
import com.gxuwz.beethoven.model.entity.current.PlayList;
import com.gxuwz.beethoven.page.index.playlistview.CurrentPlayView;
import com.gxuwz.beethoven.receiver.IndexBottomBarReceiver;
import com.gxuwz.beethoven.receiver.playview.PlayViewReceiver;
import com.gxuwz.beethoven.service.MusicService;

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
        Long slId = sharedPreferences.getLong("slId", -1);
        Long songId = sharedPreferences.getLong("songId", -1);
        List<Integer> idList = playListDao.findAllId();
        playList = playListDao.findBySlIdAndSongId(slId,songId);
        int index = 0;
        if(playList!=null) {
            if (index != -1) {
                if (index - 1 <= 0) {
                    index = idList.size() - 1;
                } else {
                    index -= 1;
                }
            }
        } else {
            if(idList.size()!=0){
                index = 0;
            } else {
                return;
            }
        }
        playList = playListDao.findById(idList.get(index));
        //保存播放信息
        saveSharedPreferences(sharedPreferences, playList);
        MusicService.musicCtrl(context,MusicService.PRPLAY);
        IndexBottomBarReceiver.sendBroadcast(IndexBottomBarReceiver.FLAT_PLAY, context);
        PlayViewReceiver.sendBroadcast(context);
        Player.isPlayer = true;
    }

    /**
     * 下一首
     */
    public static void nextOne(Context context) {
        PlayList playList = null;
        PlayListDao playListDao = new PlayListDao(context);
        SharedPreferences sharedPreferences = context.getSharedPreferences("data", Context.MODE_PRIVATE);
        Long slId = sharedPreferences.getLong("slId", -1);
        Long songId = sharedPreferences.getLong("songId", -1);
        List<Integer> idList = playListDao.findAllId();
        playList = playListDao.findBySlIdAndSongId(slId,songId);
        int index = 0;
        if(playList!=null) {
            index = idList.indexOf(playList.getId());
            if (index != -1) {
                if (index + 1 >= idList.size()) {
                    index = 0;
                } else {
                    index += 1;
                }
            }
        } else {
            if(idList.size()!=0) {
                index = 0;
            } else {
                return;
            }
        }
        playList = playListDao.findById(idList.get(index));
        saveSharedPreferences(sharedPreferences, playList);
        //播放歌曲
        MusicService.musicCtrl(context,MusicService.PRPLAY);
        //底部bar广播
        IndexBottomBarReceiver.sendBroadcast(IndexBottomBarReceiver.FLAT_PLAY, context);
        //播放页广播
        PlayViewReceiver.sendBroadcast(context);
        Player.isPlayer = true;
    }

    public static void saveSharedPreferences(SharedPreferences sharedPreferences, PlayList playList) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putLong("slId", playList.getSlId());
        editor.putLong("songId", playList.getSongId());
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

    public static void setSeekBarChange(){
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            //当进度改变
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

            }
            //当开始拖动
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }
            //当拖动停止的时候调用
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                int duration = mediaPlayer.getDuration();
                float seekBarMax = seekBar.getMax();
                int pro = seekBar.getProgress();
                int position = (int) (pro/seekBarMax*duration);
                mediaPlayer.seekTo(position);
            }
        });
    }

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