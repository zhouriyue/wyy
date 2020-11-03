package com.gxuwz.beethoven.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.gxuwz.beethoven.helper.DfHelper;
import com.gxuwz.beethoven.model.entity.current.Lyric;
import com.gxuwz.beethoven.util.DateUtil;

public class LyricDao {

    DfHelper dfHelper;

    public LyricDao(Context context) {
        this.dfHelper = new DfHelper(context);
    }

    public Lyric findById(Long lyrId){
        Lyric lyric = null;
        SQLiteDatabase db = dfHelper.getReadableDatabase();
        Cursor cursor = db.query("lyric",null,"lyr_id=?",new String[]{lyrId+""},null,null,null);
        if(cursor.getCount() != 0) {
            while (cursor.moveToNext()) {
                lyric = new Lyric();
                lyric.setLyrId(cursor.getLong(cursor.getColumnIndex("lyr_id")));
                lyric.setLyrName(cursor.getString(cursor.getColumnIndex("lyr_name")));
                lyric.setLyrUrl(cursor.getString(cursor.getColumnIndex("lyr_url")));
                lyric.setIssuingDate(DateUtil.parseString(cursor.getString(cursor.getColumnIndex("issuing_date"))));
            }
        }
        cursor.close();
        db.close();
        return lyric;
    }

    public long insert(Lyric lyric){
        SQLiteDatabase db = dfHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("lyr_id",lyric.getLyrId());
        values.put("lyr_name",lyric.getLyrName());
        values.put("lyr_url",lyric.getLyrUrl());
        values.put("issuing_date",DateUtil.simpleFormat(lyric.getIssuingDate()));
        long id = db.insert("lyric",null,values);
        db.close();
        return id;
    }
}
