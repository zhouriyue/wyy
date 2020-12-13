package com.gxuwz.beethoven.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.gxuwz.beethoven.helper.DfHelper;
import com.gxuwz.beethoven.model.entity.current.Song;
import com.gxuwz.beethoven.util.DateUtil;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SongDao {

    DfHelper dfHelper;

    public SongDao(Context context){
        dfHelper = new DfHelper(context);
    }

    public Song findIndexSong(long slId) {
        SQLiteDatabase db = dfHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("select s.song_id song_id, s.song_name song_name,s.cover_picture cover_picture, s.duration duration, s.issuing_date issuing_date, s.mv_url mv_url, s.is_charge is_charge, s.is_copyright is_copyright, s.is_single is_single, s.standard_url standard_url, s.hq_url hq_url, s.sq_url sq_url, s.wit_pre_url wit_pre_url, s.lyr_id lyr_id " +
                "from songlist_song ssli,song s " +
                "where ssli.sl_id=? and ssli.song_id=s.song_id",new String[]{slId+""});
        if(cursor.getCount() != 0) {
            while (cursor.moveToNext()) {
                Song song = new Song();
                song.setSongId(cursor.getLong(cursor.getColumnIndex("song_id")));
                song.setSongName(cursor.getString(cursor.getColumnIndex("song_name")));
                song.setCoverPicture(cursor.getString(cursor.getColumnIndex("cover_picture")));
                song.setDuration(cursor.getLong(cursor.getColumnIndex("duration")));
                song.setIssuingDate(DateUtil.parseString(cursor.getString(cursor.getColumnIndex("issuing_date"))));
                song.setMvUrl(cursor.getString(cursor.getColumnIndex("mv_url")));
                song.setIsCharge(cursor.getInt(cursor.getColumnIndex("is_charge")));
                song.setIsCopyright(cursor.getInt(cursor.getColumnIndex("is_copyright")));
                song.setIsSingle(cursor.getInt(cursor.getColumnIndex("is_single")));
                song.setStandardUrl(cursor.getString(cursor.getColumnIndex("standard_url")));
                song.setHqUrl(cursor.getString(cursor.getColumnIndex("hq_url")));
                song.setSqUrl(cursor.getString(cursor.getColumnIndex("sq_url")));
                song.setWitPreUrl(cursor.getString(cursor.getColumnIndex("wit_pre_url")));
                song.setLyrId(cursor.getLong(cursor.getColumnIndex("lyr_id")));
                song.setTimbreType(cursor.getInt(cursor.getColumnIndex("timbre_type")));
                song.setLyrUrl(cursor.getString(cursor.getColumnIndex("lyr_url")));
                return song;
            }
        }
        cursor.close();
        db.close();
        return null;
    }

    /**
     * 获取歌单的所有歌曲
     * @param slId
     * @return
     */
    public List<Song> findSonglistSong(long slId) {
        List<Song> songList = new ArrayList<Song>();
        SQLiteDatabase db = dfHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("select s.song_id song_id, s.song_name song_name,s.cover_picture cover_picture, s.duration duration, s.issuing_date issuing_date, s.mv_url mv_url, s.is_charge is_charge, s.is_copyright is_copyright, s.is_single is_single, s.standard_url standard_url, s.hq_url hq_url, s.sq_url sq_url, s.wit_pre_url wit_pre_url, s.lyr_id lyr_id,s.lyr_url lyr_url,s.timbre_type timbre_type " +
                "from songlist_song ssli,song s " +
                "where ssli.sl_id=? and ssli.song_id=s.song_id",new String[]{slId+""});
        if(cursor.getCount() != 0) {
            while (cursor.moveToNext()) {
                Song song = new Song();
                song.setSongId(cursor.getLong(cursor.getColumnIndex("song_id")));
                song.setSongName(cursor.getString(cursor.getColumnIndex("song_name")));
                song.setCoverPicture(cursor.getString(cursor.getColumnIndex("cover_picture")));
                song.setDuration(cursor.getLong(cursor.getColumnIndex("duration")));
                song.setIssuingDate(DateUtil.parseString(cursor.getString(cursor.getColumnIndex("issuing_date"))));
                song.setMvUrl(cursor.getString(cursor.getColumnIndex("mv_url")));
                song.setIsCharge(cursor.getInt(cursor.getColumnIndex("is_charge")));
                song.setIsCopyright(cursor.getInt(cursor.getColumnIndex("is_copyright")));
                song.setIsSingle(cursor.getInt(cursor.getColumnIndex("is_single")));
                song.setStandardUrl(cursor.getString(cursor.getColumnIndex("standard_url")));
                song.setHqUrl(cursor.getString(cursor.getColumnIndex("hq_url")));
                song.setSqUrl(cursor.getString(cursor.getColumnIndex("sq_url")));
                song.setWitPreUrl(cursor.getString(cursor.getColumnIndex("wit_pre_url")));
                song.setLyrId(cursor.getLong(cursor.getColumnIndex("lyr_id")));
                song.setTimbreType(cursor.getInt(cursor.getColumnIndex("timbre_type")));
                song.setLyrUrl(cursor.getString(cursor.getColumnIndex("lyr_url")));
                songList.add(song);
            }
        }
        cursor.close();
        db.close();
        return songList;
    }

    public Song findById(long songId){
        Song song = null;
        SQLiteDatabase db = dfHelper.getReadableDatabase();
        Cursor cursor = db.query("song",null,"song_id=?",new String[]{songId+""},null,null,null);
        if(cursor.getCount() != 0) {
            while (cursor.moveToNext()) {
                song = new Song();
                song.setSongId(cursor.getLong(cursor.getColumnIndex("song_id")));
                song.setSongName(cursor.getString(cursor.getColumnIndex("song_name")));
                song.setCoverPicture(cursor.getString(cursor.getColumnIndex("cover_picture")));
                song.setDuration(cursor.getLong(cursor.getColumnIndex("duration")));
                song.setIssuingDate(DateUtil.parseString(cursor.getString(cursor.getColumnIndex("issuing_date"))));
                song.setMvUrl(cursor.getString(cursor.getColumnIndex("mv_url")));
                song.setIsCharge(cursor.getInt(cursor.getColumnIndex("is_charge")));
                song.setIsCopyright(cursor.getInt(cursor.getColumnIndex("is_copyright")));
                song.setIsSingle(cursor.getInt(cursor.getColumnIndex("is_single")));
                song.setStandardUrl(cursor.getString(cursor.getColumnIndex("standard_url")));
                song.setHqUrl(cursor.getString(cursor.getColumnIndex("hq_url")));
                song.setSqUrl(cursor.getString(cursor.getColumnIndex("sq_url")));
                song.setWitPreUrl(cursor.getString(cursor.getColumnIndex("wit_pre_url")));
                song.setLyrId(cursor.getLong(cursor.getColumnIndex("lyr_id")));
                song.setTimbreType(cursor.getInt(cursor.getColumnIndex("timbre_type")));
                song.setLyrUrl(cursor.getString(cursor.getColumnIndex("lyr_url")));
            }
        }
        cursor.close();
        db.close();
        return song;
    }

    /**
     * 增加歌单歌曲联系信息
     * @param slId
     * @param songId
     * @return
     */
    public long insertSlToSong(long slId,long songId){
        SQLiteDatabase db = dfHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("sl_id",slId);
        values.put("song_id",songId);
        long id = db.insert("songlist_song",null,values);
        db.close();
        return id;
    }

    public boolean isSlToSong(long slId,long songId) {
        boolean flag = false;
        SQLiteDatabase db = dfHelper.getReadableDatabase();
        Cursor cursor = db.query("songlist_song",null,"sl_id=? and song_id=?",new String[]{slId+"",songId+""},null,null,null);
        if(cursor.getCount() != 0) {
            flag = true;
        }
        cursor.close();
        db.close();
        return flag;
    }

    public long insert(Song song) {
        SQLiteDatabase db = dfHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("song_id",song.getSongId());
        values.put("song_name",song.getSongName());
        values.put("cover_picture",song.getCoverPicture());
        values.put("duration",song.getDuration());
        values.put("issuing_date", DateUtil.simpleFormat(song.getIssuingDate()));
        values.put("mv_url",song.getMvUrl());
        values.put("is_charge",song.getIsCharge());
        values.put("is_copyright",song.getIsCopyright());
        values.put("is_single",song.getIsSingle());
        values.put("standard_url",song.getStandardUrl());
        values.put("hq_url",song.getHqUrl());
        values.put("sq_url",song.getSqUrl());
        values.put("wit_pre_url",song.getWitPreUrl());
        values.put("lyr_id",song.getLyrId());
        values.put("timbre_type",song.getTimbreType());
        values.put("lyr_url",song.getLyrUrl());
        long id = db.insert("song",null,values);
        db.close();
        return id;
    }

    public long update(Song song) {
        SQLiteDatabase db = dfHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("song_id",song.getSongId());
        values.put("song_name",song.getSongName());
        values.put("cover_picture",song.getCoverPicture());
        values.put("duration",song.getDuration());
        values.put("issuing_date", DateUtil.simpleFormat(song.getIssuingDate()));
        values.put("mv_url",song.getMvUrl());
        values.put("is_charge",song.getIsCharge());
        values.put("is_copyright",song.getIsCopyright());
        values.put("is_single",song.getIsSingle());
        values.put("standard_url",song.getStandardUrl());
        values.put("hq_url",song.getHqUrl());
        values.put("sq_url",song.getSqUrl());
        values.put("wit_pre_url",song.getWitPreUrl());
        values.put("lyr_id",song.getLyrId());
        values.put("timbre_type",song.getTimbreType());
        values.put("lyr_url",song.getLyrUrl());
        long id = db.update("song",values,"song_id=?",new String[]{song.getSongId()+""});
        db.close();
        return id;
    }

    public List<Song> findAll(){
        List<Song> songList = new ArrayList<Song>();
        SQLiteDatabase db = dfHelper.getReadableDatabase();
        Cursor cursor = db.query("song",null,null,null,null,null,null);
        if(cursor.getCount() != 0) {
            while (cursor.moveToNext()) {
                Song song = new Song();
                song.setSongId(cursor.getLong(cursor.getColumnIndex("song_id")));
                song.setSongName(cursor.getString(cursor.getColumnIndex("song_name")));
                song.setCoverPicture(cursor.getString(cursor.getColumnIndex("cover_picture")));
                song.setDuration(cursor.getLong(cursor.getColumnIndex("duration")));
                song.setIssuingDate(DateUtil.parseString(cursor.getString(cursor.getColumnIndex("issuing_date"))));
                song.setMvUrl(cursor.getString(cursor.getColumnIndex("mv_url")));
                song.setIsCharge(cursor.getInt(cursor.getColumnIndex("is_charge")));
                song.setIsCopyright(cursor.getInt(cursor.getColumnIndex("is_copyright")));
                song.setIsSingle(cursor.getInt(cursor.getColumnIndex("is_single")));
                song.setStandardUrl(cursor.getString(cursor.getColumnIndex("standard_url")));
                song.setHqUrl(cursor.getString(cursor.getColumnIndex("hq_url")));
                song.setSqUrl(cursor.getString(cursor.getColumnIndex("sq_url")));
                song.setWitPreUrl(cursor.getString(cursor.getColumnIndex("wit_pre_url")));
                song.setLyrId(cursor.getLong(cursor.getColumnIndex("lyr_id")));
                song.setTimbreType(cursor.getInt(cursor.getColumnIndex("timbre_type")));
                song.setLyrUrl(cursor.getString(cursor.getColumnIndex("lyr_url")));
                songList.add(song);
            }
        }
        cursor.close();
        db.close();
        return songList;
    }

    public Date getDateOrNull(Cursor cursor, int columnIndex) {
        if (cursor.isNull(columnIndex))
            return null;
        return longToDate(cursor.getLong(columnIndex));
    }
    public Date longToDate(long secondsSinceEpoch) {
        final Date d = new Date();
        d.setTime(secondsSinceEpoch * 1000);
        return d;
    }
}
