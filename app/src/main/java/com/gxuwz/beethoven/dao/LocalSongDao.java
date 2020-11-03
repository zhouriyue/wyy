package com.gxuwz.beethoven.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.gxuwz.beethoven.helper.DfHelper;
import com.gxuwz.beethoven.model.entity.Music;
import com.gxuwz.beethoven.model.entity.current.LocalSong;
import com.gxuwz.beethoven.util.DateUtil;

import java.util.ArrayList;
import java.util.List;

public class LocalSongDao {
    DfHelper dfHelper;

    public LocalSongDao(Context context) {
        this.dfHelper = new DfHelper(context);
    }

    public boolean isLocalUrl(String songUrl) {
        boolean flag = false;
        SQLiteDatabase db = dfHelper.getReadableDatabase();
        Cursor cursor = db.query("local_song",null,"song_url=?",new String[]{songUrl},null,null,null);
        if(cursor.getCount() != 0) {
            flag = true;
        }
        cursor.close();
        db.close();
        return flag;
    }

    public LocalSong findByUrl(String songUrl) {
        LocalSong localSong = null;
        SQLiteDatabase db = dfHelper.getReadableDatabase();
        Cursor cursor = db.query("local_song",null,"song_url=?",new String[]{songUrl},null,null,null);
        if(cursor.getCount() != 0) {
            while (cursor.moveToNext()) {
                localSong = new LocalSong();
                localSong.setSongId(cursor.getLong(cursor.getColumnIndex("song_id")));
                localSong.setSongName(cursor.getString(cursor.getColumnIndex("song_name")));
                localSong.setSingerName(cursor.getString(cursor.getColumnIndex("singer_name")));
                localSong.setCoverPicture(cursor.getString(cursor.getColumnIndex("cover_picture")));
                localSong.setDuration(cursor.getLong(cursor.getColumnIndex("duration")));
                localSong.setIssuingDate(DateUtil.parseString(cursor.getString(cursor.getColumnIndex("issuing_date"))));
                localSong.setSongUrl(cursor.getString(cursor.getColumnIndex("song_url")));
                localSong.setLyrUrl(cursor.getString(cursor.getColumnIndex("lyr_url")));
            }
        }
        cursor.close();
        db.close();
        return localSong;
    }

    public LocalSong findBySongId(Long songId) {
        LocalSong localSong = null;
        SQLiteDatabase db = dfHelper.getReadableDatabase();
        Cursor cursor = db.query("local_song",null,"song_id=?",new String[]{songId+""},null,null,null);
        if(cursor.getCount() != 0) {
            while (cursor.moveToNext()) {
                localSong = new LocalSong();
                localSong.setSongId(cursor.getLong(cursor.getColumnIndex("song_id")));
                localSong.setSongName(cursor.getString(cursor.getColumnIndex("song_name")));
                localSong.setSingerName(cursor.getString(cursor.getColumnIndex("singer_name")));
                localSong.setCoverPicture(cursor.getString(cursor.getColumnIndex("cover_picture")));
                localSong.setDuration(cursor.getLong(cursor.getColumnIndex("duration")));
                localSong.setIssuingDate(DateUtil.parseString(cursor.getString(cursor.getColumnIndex("issuing_date"))));
                localSong.setSongUrl(cursor.getString(cursor.getColumnIndex("song_url")));
                localSong.setLyrUrl(cursor.getString(cursor.getColumnIndex("lyr_url")));
            }
        }
        cursor.close();
        db.close();
        return localSong;
    }

    public List<LocalSong> findAll() {
        List<LocalSong> localSongs = new ArrayList<LocalSong>();
        SQLiteDatabase db = dfHelper.getReadableDatabase();
        Cursor cursor = db.query("local_song",null,null,null,null,null,null);
        if(cursor.getCount() != 0) {
            while (cursor.moveToNext()) {
                LocalSong localSong = new LocalSong();
                localSong.setSongId(cursor.getLong(cursor.getColumnIndex("song_id")));
                localSong.setSongName(cursor.getString(cursor.getColumnIndex("song_name")));
                localSong.setSingerName(cursor.getString(cursor.getColumnIndex("singer_name")));
                localSong.setCoverPicture(cursor.getString(cursor.getColumnIndex("cover_picture")));
                localSong.setDuration(cursor.getLong(cursor.getColumnIndex("duration")));
                localSong.setIssuingDate(DateUtil.parseString(cursor.getString(cursor.getColumnIndex("issuing_date"))));
                localSong.setSongUrl(cursor.getString(cursor.getColumnIndex("song_url")));
                localSong.setLyrUrl(cursor.getString(cursor.getColumnIndex("lyr_url")));
                localSongs.add(localSong);
            }
        }
        cursor.close();
        db.close();
        return localSongs;
    }

    public long insert(Music music) {
        SQLiteDatabase db = dfHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("song_name",music.getMusicName());
        values.put("singer_name",music.getSinger());
        values.put("cover_picture",music.getSongPicUrl());
        values.put("duration",music.getDuration());
        values.put("song_url",music.getSongUrl());
        long id = db.insert("local_song",null,values);
        db.close();
        return id;
    }

    public long insert(LocalSong localSong) {
        SQLiteDatabase db = dfHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("song_id",localSong.getSongId());
        values.put("song_name",localSong.getSongName());
        values.put("singer_name",localSong.getSingerName());
        values.put("cover_picture",localSong.getCoverPicture());
        values.put("duration",localSong.getDuration());
        values.put("issuing_date", DateUtil.simpleFormat(localSong.getIssuingDate()));
        values.put("song_url",localSong.getSongUrl());
        values.put("lyr_url",localSong.getLyrUrl());
        long id = db.insert("local_song",null,values);
        db.close();
        return id;
    }
}
