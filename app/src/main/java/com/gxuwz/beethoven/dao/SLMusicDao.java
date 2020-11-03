package com.gxuwz.beethoven.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.gxuwz.beethoven.helper.DfHelper;
import com.gxuwz.beethoven.model.entity.SongListsMusic;

import java.util.ArrayList;
import java.util.List;

public class SLMusicDao {

    DfHelper dfHelper;

    public SLMusicDao(Context context) {
        this.dfHelper = new DfHelper(context);
    }

    public long isExist(Integer id){
        long flag = 0;
        SQLiteDatabase db = dfHelper.getWritableDatabase();
        Cursor cursor = db.query("song_list_music",null,"id=?",new String[]{id+""},null,null,null);
        if(cursor.getCount()>0){
            flag = 1;
        }
        cursor.close();
        db.close();
        return flag;
    }

    public long update(SongListsMusic songListsMusic){
        SQLiteDatabase db = dfHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("song_list_id",songListsMusic.getSongListId());
        values.put("music_id",songListsMusic.getMusicId());
        values.put("singer_id",songListsMusic.getSingerId());
        values.put("music_name",songListsMusic.getMusicName());
        values.put("singer_name",songListsMusic.getSingerName());
        values.put("song_cache_path",songListsMusic.getSongCachePath());
        values.put("really_id",songListsMusic.getReallyId());
        long flag = db.update("song_list_music",values,"id=?",new String[]{songListsMusic.getId()+""});
        db.close();
        return flag;
    }

    public List<SongListsMusic> findBySongListId(String songListId){
        List<SongListsMusic> songListsMusicList = new ArrayList<SongListsMusic>();
        SQLiteDatabase db = dfHelper.getWritableDatabase();
        Cursor cursor = db.query("song_list_music",null,"song_list_id=?",new String[]{songListId},null,null,null);
        if(cursor.getCount()>0){
            while (cursor.moveToNext()){
                SongListsMusic songListsMusic = new SongListsMusic();
                songListsMusic.setId(cursor.getInt(cursor.getColumnIndex("id")));
                songListsMusic.setSongListId(cursor.getString(cursor.getColumnIndex("song_list_id")));
                songListsMusic.setMusicId(cursor.getString(cursor.getColumnIndex("music_id")));
                songListsMusic.setSingerId(cursor.getString(cursor.getColumnIndex("singer_id")));
                songListsMusic.setMusicName(cursor.getString(cursor.getColumnIndex("music_name")));
                songListsMusic.setSingerName(cursor.getString(cursor.getColumnIndex("singer_name")));
                songListsMusic.setSongCachePath(cursor.getString(cursor.getColumnIndex("song_cache_path")));
                songListsMusic.setReallyId(cursor.getInt(cursor.getColumnIndex("really_id")));
                songListsMusicList.add(songListsMusic);
            }
        }
        cursor.close();
        db.close();
        return songListsMusicList;
    }

    public SongListsMusic findById(Integer id){
        SongListsMusic songListsMusic = null;
        SQLiteDatabase db = dfHelper.getWritableDatabase();
        Cursor cursor = db.query("song_list_music",null,"id=?",new String[]{id+""},null,null,null);
        if(cursor.getCount()>0){
            while (cursor.moveToNext()){
                songListsMusic = new SongListsMusic();
                songListsMusic.setId(cursor.getInt(cursor.getColumnIndex("id")));
                songListsMusic.setSongListId(cursor.getString(cursor.getColumnIndex("song_list_id")));
                songListsMusic.setMusicId(cursor.getString(cursor.getColumnIndex("music_id")));
                songListsMusic.setSingerId(cursor.getString(cursor.getColumnIndex("singer_id")));
                songListsMusic.setMusicId(cursor.getString(cursor.getColumnIndex("music_name")));
                songListsMusic.setSingerName(cursor.getString(cursor.getColumnIndex("singer_name")));
                songListsMusic.setSongCachePath(cursor.getString(cursor.getColumnIndex("song_cache_path")));
                songListsMusic.setReallyId(cursor.getInt(cursor.getColumnIndex("really_id")));
            }
        }
        cursor.close();
        db.close();
        return songListsMusic;
    }

    public long insert(SongListsMusic songListsMusic){
        SQLiteDatabase db = dfHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("id",songListsMusic.getId());
        values.put("song_list_id",songListsMusic.getSongListId());
        values.put("music_id",songListsMusic.get_links().getMusic().getHref());
        values.put("singer_id",songListsMusic.get_links().getSinger().getHref());
        values.put("music_name",songListsMusic.getMusicName());
        values.put("singer_name",songListsMusic.getSingerName());
        values.put("song_cache_path",songListsMusic.getSongCachePath());
        values.put("really_id",songListsMusic.getReallyId());
        long flag = db.insert("song_list_music",null,values);
        db.close();
        return flag;
    }
}
