package com.gxuwz.beethoven.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.gxuwz.beethoven.helper.DfHelper;
import com.gxuwz.beethoven.model.entity.LatePlay;
import com.gxuwz.beethoven.model.entity.current.LocalSong;
import com.gxuwz.beethoven.util.DateUtil;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class LatePlayDao {

    DfHelper dfHelper;

    public LatePlayDao(Context context) {
        this.dfHelper = new DfHelper(context);
    }

    public List<LatePlay> findAll() {
        List<LatePlay> latePlays = new ArrayList<LatePlay>();
        SQLiteDatabase db = dfHelper.getReadableDatabase();
        Cursor cursor = db.query("late_play",null,null,null,null,null,"play_date desc");
        if(cursor.getCount() != 0) {
            while (cursor.moveToNext()) {
                LatePlay latePlay = new LatePlay();
                latePlay.setId(cursor.getLong(cursor.getColumnIndex("id")));
                latePlay.setSlId(cursor.getLong(cursor.getColumnIndex("sl_id")));
                latePlay.setSongId(cursor.getLong(cursor.getColumnIndex("song_id")));
                latePlay.setPlayDate(DateUtil.parseString(cursor.getString(cursor.getColumnIndex("play_date"))));
                latePlays.add(latePlay);
            }
        }
        cursor.close();
        db.close();
        return latePlays;
    }

    public LatePlay findLatePlay(Long slId,Long songId) {
        LatePlay latePlay = null;
        SQLiteDatabase db = dfHelper.getReadableDatabase();
        Cursor cursor = db.query("late_play",null,"sl_id=? and song_id=?",new String[]{slId+"",songId+""},null,null,null);
        if(cursor.getCount() != 0) {
            while (cursor.moveToNext()) {
                latePlay = new LatePlay();
                latePlay.setId(cursor.getLong(cursor.getColumnIndex("id")));
                latePlay.setSlId(cursor.getLong(cursor.getColumnIndex("sl_id")));
                latePlay.setSongId(cursor.getLong(cursor.getColumnIndex("song_id")));
                latePlay.setPlayDate(DateUtil.parseString(cursor.getString(cursor.getColumnIndex("play_date"))));
            }
        }
        cursor.close();
        db.close();
        return latePlay;
    }

    public LatePlay findLatePlay(LatePlay latePlay) {
        if (latePlay==null) return null;
        SQLiteDatabase db = dfHelper.getReadableDatabase();
        Cursor cursor = db.query("late_play",null,"sl_id=? and song_id=?",new String[]{latePlay.getSlId()+"",latePlay.getSongId()+""},null,null,null);
        if(cursor.getCount() != 0) {
            while (cursor.moveToNext()) {
                latePlay = new LatePlay();
                latePlay.setId(cursor.getLong(cursor.getColumnIndex("id")));
                latePlay.setSlId(cursor.getLong(cursor.getColumnIndex("sl_id")));
                latePlay.setSongId(cursor.getLong(cursor.getColumnIndex("song_id")));
                latePlay.setPlayDate(DateUtil.parseString(cursor.getString(cursor.getColumnIndex("play_date"))));
            }
        }
        cursor.close();
        db.close();
        return latePlay;
    }

    public long update(LatePlay latePlay) {
        SQLiteDatabase db = dfHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("sl_id",latePlay.getSlId());
        values.put("song_id",latePlay.getSongId());
        values.put("play_date",DateUtil.simpleFormat(latePlay.getPlayDate()));
        long status = db.update("late_play",values,"sl_id=?",new String[]{latePlay.getId()+""});
        db.close();
        return status;
    }

    public long insert(Long slId, Long songId) {
        SQLiteDatabase db = dfHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("sl_id",slId);
        values.put("song_id",songId);
        values.put("play_date", DateUtil.simpleFormat(new Date()));
        long id = db.insert("late_play",null,values);
        db.close();
        return id;
    }

    public long insert(LatePlay latePlay) {
        SQLiteDatabase db = dfHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("sl_id",latePlay.getSlId());
        values.put("song_id",latePlay.getSongId());
        values.put("play_date", DateUtil.simpleFormat(latePlay.getPlayDate()));
        long id = db.insert("late_play",null,values);
        db.close();
        return id;
    }
}
