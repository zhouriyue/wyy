package com.gxuwz.beethoven.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.gxuwz.beethoven.helper.DfHelper;
import com.gxuwz.beethoven.model.entity.current.Singer;

import java.util.ArrayList;
import java.util.List;

public class SingerDao {
    DfHelper dfHelper;

    public SingerDao(Context context){
        dfHelper = new DfHelper(context);
    }

    public List<Singer> findSongSiner(Long songId) {
        List<Singer> singerList = new ArrayList<Singer>();
        SQLiteDatabase db = dfHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("select sr.sin_id sin_id, sr.sin_name sin_name, sr.nickname nickname," +
                " sr.sex sex, sr.year year, sr.sin_type sin_type, sr.area area, sr.song_number song_number," +
                "sr.follower_number follower_number, sr.album_number album_number, sr.mv_number mv_number, sr.detail detail, " +
                "sr.influence_power influence_power, sr.cert_info cert_info,sr.early_exp early_exp, sr.performing_exp performing_exp " +
                "from song_singer ssr,singer sr " +
                "where ssr.sin_id=sr.sin_id and ssr.song_id=?",new String[]{songId+""});
        if(cursor.getCount() != 0) {
            while (cursor.moveToNext()) {
                Singer singer = new Singer();
                singer.setSinId(cursor.getLong(cursor.getColumnIndex("sin_id")));
                singer.setSinName(cursor.getString(cursor.getColumnIndex("sin_name")));
                singer.setNickname(cursor.getString(cursor.getColumnIndex("nickname")));
                singer.setSex(cursor.getInt(cursor.getColumnIndex("sex")));
                singer.setYear(cursor.getInt(cursor.getColumnIndex("year")));
                singer.setSinType(cursor.getInt(cursor.getColumnIndex("sin_type")));
                singer.setArea(cursor.getString(cursor.getColumnIndex("area")));
                singer.setSongNumber(cursor.getInt(cursor.getColumnIndex("song_number")));
                singer.setFollowerNumber(cursor.getInt(cursor.getColumnIndex("follower_number")));
                singer.setAlbumNumber(cursor.getInt(cursor.getColumnIndex("album_number")));
                singer.setMvNumber(cursor.getInt(cursor.getColumnIndex("mv_number")));
                singer.setDetail(cursor.getString(cursor.getColumnIndex("detail")));
                singer.setInfluencePower(cursor.getString(cursor.getColumnIndex("influence_power")));
                singer.setCertInfo(cursor.getString(cursor.getColumnIndex("cert_info")));
                singer.setEarlyExp(cursor.getString(cursor.getColumnIndex("early_exp")));
                singer.setPerformingExp(cursor.getString(cursor.getColumnIndex("performing_exp")));
                singerList.add(singer);
            }
        }
        cursor.close();
        db.close();
        return singerList;
    }

    public long insertSongToSinger(Long songId,Long sinId) {
        SQLiteDatabase db = dfHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("song_id",songId);
        values.put("sin_id",sinId);
        long id = db.insert("song_singer",null,values);
        db.close();
        return id;
    }

    public boolean isSongToSinger(Long songId,Long sinId){
        boolean flag = false;
        SQLiteDatabase db = dfHelper.getReadableDatabase();
        Cursor cursor = db.query("song_singer",null,"song_id=? and sin_id=?",new String[]{songId+"",sinId+""},null,null,null);
        if(cursor.getCount() != 0) {
            flag = true;
        }
        cursor.close();
        db.close();
        return flag;
    }

    public Singer findById(long sinId){
        Singer singer = null;
        SQLiteDatabase db = dfHelper.getReadableDatabase();
        Cursor cursor = db.query("singer",null,"sin_id=?",new String[]{sinId+""},null,null,null);
        if(cursor.getCount() != 0) {
            while (cursor.moveToNext()) {
                singer = new Singer();
                singer.setSinId(cursor.getLong(cursor.getColumnIndex("sin_id")));
                singer.setSinId(cursor.getLong(cursor.getColumnIndex("sin_name")));
                singer.setSinId(cursor.getLong(cursor.getColumnIndex("nickname")));
                singer.setSinId(cursor.getLong(cursor.getColumnIndex("sex")));
                singer.setSinId(cursor.getLong(cursor.getColumnIndex("year")));
                singer.setSinId(cursor.getLong(cursor.getColumnIndex("sin_type")));
                singer.setSinId(cursor.getLong(cursor.getColumnIndex("area")));
                singer.setSinId(cursor.getLong(cursor.getColumnIndex("song_number")));
                singer.setSinId(cursor.getLong(cursor.getColumnIndex("follower_number")));
                singer.setSinId(cursor.getLong(cursor.getColumnIndex("album_number")));
                singer.setSinId(cursor.getLong(cursor.getColumnIndex("mv_number")));
                singer.setSinId(cursor.getLong(cursor.getColumnIndex("detail")));
                singer.setSinId(cursor.getLong(cursor.getColumnIndex("influence_power")));
                singer.setSinId(cursor.getLong(cursor.getColumnIndex("cert_info")));
                singer.setSinId(cursor.getLong(cursor.getColumnIndex("early_exp")));
                singer.setSinId(cursor.getLong(cursor.getColumnIndex("performing_exp")));
            }
        }
        cursor.close();
        db.close();
        return singer;
    }



    /**
     * 添加
     * @param singer
     */
    public long insert(Singer singer) {
        SQLiteDatabase db = dfHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("sin_id",singer.getSinId());
        values.put("sin_name",singer.getSinName());
        values.put("nickname",singer.getNickname());
        values.put("sex",singer.getSex());
        values.put("year",singer.getYear());
        values.put("sin_type",singer.getSinType());
        values.put("area",singer.getArea());
        values.put("song_number",singer.getSongNumber());
        values.put("follower_number",singer.getFollowerNumber());
        values.put("album_number",singer.getAlbumNumber());
        values.put("mv_number",singer.getMvNumber());
        values.put("detail",singer.getDetail());
        values.put("influence_power",singer.getInfluencePower());
        values.put("cert_info",singer.getCertInfo());
        values.put("early_exp",singer.getEarlyExp());
        values.put("performing_exp",singer.getPerformingExp());
        long id = db.insert("singer",null,values);
        db.close();
        return id;
    }

}
