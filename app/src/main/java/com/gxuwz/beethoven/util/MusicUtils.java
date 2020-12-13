package com.gxuwz.beethoven.util;

import android.Manifest;
import android.app.Activity;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.media.MediaPlayer;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;
import android.provider.Settings;
import android.util.Log;
import android.widget.Toast;

import androidx.core.app.ActivityCompat;

import com.gxuwz.beethoven.R;
import com.gxuwz.beethoven.model.entity.Music;
import com.gxuwz.beethoven.model.entity.current.LocalSong;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static androidx.constraintlayout.widget.Constraints.TAG;

/**
 * 音乐工具类,
 */
public class MusicUtils {

    public static final int RINGTONE = 0;                   //铃声
    public static final int NOTIFICATION = 1;               //通知音
    public static final int ALARM = 2;                      //闹钟
    public static final int ALL = 3;                        //设置全部

    /**
     * 设置铃声
     *
     * @param context
     * @param path
     * @param id
     */
    public static String setRingtoneStr(Context context, String path, int id) {
        ContentValues cv = new ContentValues();
        Uri newUri = null;
        Uri uri = MediaStore.Audio.Media.getContentUriForPath(path);
// 查询音乐文件在媒体库是否存在
        Cursor cursor = context.getContentResolver().query(uri, null, MediaStore.MediaColumns.DATA + "=?", new String[]{path}, null);
        if (cursor.moveToFirst() && cursor.getCount() > 0) {
            String _id = cursor.getString(0);
            switch (id) {
                case RINGTONE:
                    cv.put(MediaStore.Audio.Media.IS_RINGTONE, true);
                    cv.put(MediaStore.Audio.Media.IS_NOTIFICATION, false);
                    cv.put(MediaStore.Audio.Media.IS_ALARM, false);
                    cv.put(MediaStore.Audio.Media.IS_MUSIC, false);
                    break;
                case NOTIFICATION:
                    cv.put(MediaStore.Audio.Media.IS_RINGTONE, false);
                    cv.put(MediaStore.Audio.Media.IS_NOTIFICATION, true);
                    cv.put(MediaStore.Audio.Media.IS_ALARM, false);
                    cv.put(MediaStore.Audio.Media.IS_MUSIC, false);
                    break;
                case ALARM:
                    cv.put(MediaStore.Audio.Media.IS_RINGTONE, false);
                    cv.put(MediaStore.Audio.Media.IS_NOTIFICATION, false);
                    cv.put(MediaStore.Audio.Media.IS_ALARM, true);
                    cv.put(MediaStore.Audio.Media.IS_MUSIC, false);
                    break;
                case ALL:
                    cv.put(MediaStore.Audio.Media.IS_RINGTONE, true);
                    cv.put(MediaStore.Audio.Media.IS_NOTIFICATION, true);
                    cv.put(MediaStore.Audio.Media.IS_ALARM, true);
                    cv.put(MediaStore.Audio.Media.IS_MUSIC, false);
                    break;
                default:
                    break;
            }
            // 把需要设为铃声的歌曲更新铃声库
            context.getContentResolver().update(uri, cv, MediaStore.MediaColumns.DATA + "=?", new String[]{path});
            newUri = ContentUris.withAppendedId(uri, Long.valueOf(_id));
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                //判断是否可以写入数据到系统
                if (!Settings.System.canWrite(context)) {
                    Intent i = new Intent(Settings.ACTION_MANAGE_WRITE_SETTINGS);
                    i.setData(Uri.parse("package:" + context.getPackageName()));
                    i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(i);
                } else {
                    // 一下为关键代码：
                    switch (id) {
                        case RINGTONE:
                            RingtoneManager.setActualDefaultRingtoneUri(context, RingtoneManager.TYPE_RINGTONE, newUri);
                            break;
                        case NOTIFICATION:
                            RingtoneManager.setActualDefaultRingtoneUri(context, RingtoneManager.TYPE_NOTIFICATION, newUri);
                            break;
                        case ALARM:
                            RingtoneManager.setActualDefaultRingtoneUri(context, RingtoneManager.TYPE_ALARM, newUri);
                            break;
                        case ALL:
                            RingtoneManager.setActualDefaultRingtoneUri(context, RingtoneManager.TYPE_ALL, newUri);
                            break;
                        default:
                            break;

                    }
                }
            }
        }
        //播放铃声
        Ringtone rt = RingtoneManager.getRingtone(context, newUri);
        return path;
    }


    /**
     * 设置铃声
     *
     * @param context
     * @param path
     * @param id
     */
    public static MediaPlayer setRingtoneImpl(Context context, String path, int id) {
        ContentValues cv = new ContentValues();
        Uri newUri = null;
        Uri uri = MediaStore.Audio.Media.getContentUriForPath(path);
// 查询音乐文件在媒体库是否存在
        Cursor cursor = context.getContentResolver().query(uri, null, MediaStore.MediaColumns.DATA + "=?", new String[]{path}, null);
        if (cursor.moveToFirst() && cursor.getCount() > 0) {
            String _id = cursor.getString(0);
            switch (id) {
                case RINGTONE:
                    cv.put(MediaStore.Audio.Media.IS_RINGTONE, true);
                    cv.put(MediaStore.Audio.Media.IS_NOTIFICATION, false);
                    cv.put(MediaStore.Audio.Media.IS_ALARM, false);
                    cv.put(MediaStore.Audio.Media.IS_MUSIC, false);
                    break;
                case NOTIFICATION:
                    cv.put(MediaStore.Audio.Media.IS_RINGTONE, false);
                    cv.put(MediaStore.Audio.Media.IS_NOTIFICATION, true);
                    cv.put(MediaStore.Audio.Media.IS_ALARM, false);
                    cv.put(MediaStore.Audio.Media.IS_MUSIC, false);
                    break;
                case ALARM:
                    cv.put(MediaStore.Audio.Media.IS_RINGTONE, false);
                    cv.put(MediaStore.Audio.Media.IS_NOTIFICATION, false);
                    cv.put(MediaStore.Audio.Media.IS_ALARM, true);
                    cv.put(MediaStore.Audio.Media.IS_MUSIC, false);
                    break;
                case ALL:
                    cv.put(MediaStore.Audio.Media.IS_RINGTONE, true);
                    cv.put(MediaStore.Audio.Media.IS_NOTIFICATION, true);
                    cv.put(MediaStore.Audio.Media.IS_ALARM, true);
                    cv.put(MediaStore.Audio.Media.IS_MUSIC, false);
                    break;
                default:
                    break;
            }
            // 把需要设为铃声的歌曲更新铃声库
            context.getContentResolver().update(uri, cv, MediaStore.MediaColumns.DATA + "=?", new String[]{path});
            newUri = ContentUris.withAppendedId(uri, Long.valueOf(_id));
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                //判断是否可以写入数据到系统
                if (!Settings.System.canWrite(context)) {
                    Intent i = new Intent(Settings.ACTION_MANAGE_WRITE_SETTINGS);
                    i.setData(Uri.parse("package:" + context.getPackageName()));
                    i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(i);
                } else {

                    // 一下为关键代码：
                    switch (id) {
                        case RINGTONE:
                            RingtoneManager.setActualDefaultRingtoneUri(context, RingtoneManager.TYPE_RINGTONE, newUri);
                            break;
                        case NOTIFICATION:
                            RingtoneManager.setActualDefaultRingtoneUri(context, RingtoneManager.TYPE_NOTIFICATION, newUri);
                            break;
                        case ALARM:
                            RingtoneManager.setActualDefaultRingtoneUri(context, RingtoneManager.TYPE_ALARM, newUri);
                            break;
                        case ALL:
                            RingtoneManager.setActualDefaultRingtoneUri(context, RingtoneManager.TYPE_ALL, newUri);
                            break;
                        default:
                            break;
                    }
                }
            }
        }
        //播放铃声
        Ringtone rt = RingtoneManager.getRingtone(context, newUri);
        MediaPlayer mediaPlayer = new MediaPlayer();
        if (rt != null) {
            mediaPlayer.reset();
            try {
                mediaPlayer.setDataSource(path); // 设置数据源
            } catch (IOException e) {
                e.printStackTrace();
            }
            return mediaPlayer;
        }
        return null;
    }

    /**
     * 扫描系统里面的音频文件，返回一个list集合
     */
    public static List<Music> getMusicData(Context context) {
        List<Music> list = new ArrayList<>();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            //判断是否可以写入数据到系统
            if (!Settings.System.canWrite(context)) {
                Intent i = new Intent(Settings.ACTION_MANAGE_WRITE_SETTINGS);
                i.setData(Uri.parse("package:" + context.getPackageName()));
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(i);
            } else {

                // 媒体库查询语句（写一个工具类MusicUtils）
                Cursor cursor = context.getContentResolver().query(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,
                        new String[]{
                                MediaStore.Audio.Media._ID,
                                MediaStore.Audio.Media.TITLE,
                                MediaStore.Audio.Media.DISPLAY_NAME,
                                MediaStore.Audio.Media.DURATION,
                                MediaStore.Audio.Media.ARTIST,
                                MediaStore.Audio.Media.DATA,
                                MediaStore.Audio.Media.SIZE},
                        null, null, MediaStore.Audio.Media.DATE_ADDED);

                if (cursor != null) {
                    while (cursor.moveToNext()) {
                        Music music = new Music();
                        String displayName = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.DISPLAY_NAME));
                        if (displayName.lastIndexOf(".") != -1) {
                            displayName = displayName.substring(0, displayName.lastIndexOf("."));
                        }
                        music.setSinger(cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.ARTIST)));
                        music.setSongUrl(cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.DATA)));
                        music.setDuration(cursor.getInt(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.DURATION)));
                        music.setSize(cursor.getLong(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.SIZE)));
                        if (music.getSize() > 1000 * 800) {
                            // 注释部分是切割标题，分离出歌曲名和歌手 （本地媒体库读取的歌曲信息不规范）
                            if (displayName.contains("-")) {
                                String[] str = displayName.split("-");
                                music.setSinger(str[0]);
                                music.setMusicName(str[1]);
                            } else {
                                music.setMusicName(cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.TITLE)));
                            }
                            list.add(music);
                        }
                    }
                    // 释放资源
                    cursor.close();
                }
            }
        }
        return list;
    }

    /**
     * 定义一个方法用来格式化获取到的时间
     */
    public static String formatTime(int time) {
        if (time / 1000 % 60 < 10) {
            return time / 1000 / 60 + ":0" + time / 1000 % 60;
        } else {
            return time / 1000 / 60 + ":" + time / 1000 % 60;
        }
    }

    public static void getSDCardRWPermission(Activity activity) {
        int permission = ActivityCompat.checkSelfPermission(activity,
                Manifest.permission.WRITE_EXTERNAL_STORAGE);

        if (permission != PackageManager.PERMISSION_GRANTED) {
            // We don't have permission so prompt the user
            ActivityCompat.requestPermissions(activity, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                    1);
        }
    }

}
