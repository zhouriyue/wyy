package com.gxuwz.beethoven.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.gxuwz.beethoven.model.entity.PlayList;
import com.gxuwz.beethoven.util.MyHelper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PlayListDao {

    MyHelper myHelper;

    public PlayListDao(Context context) {
        this.myHelper = new MyHelper(context);
    }

    /**
     * 获取所有
     * @return
     */
    public List<PlayList> findAll(){
        List<PlayList> playLists = new ArrayList<PlayList>();
        SQLiteDatabase db = myHelper.getReadableDatabase();
        Cursor cursor = db.query("play_list",null,null,null,null,null,null);
        if(cursor.getCount() != 0) {
            while (cursor.moveToNext()) {
                PlayList playList = new PlayList();
                playList.setId(cursor.getInt(cursor.getColumnIndex("id")));
                playList.setSongName(cursor.getString(cursor.getColumnIndex("song_name")));
                playList.setSingerName(cursor.getString(cursor.getColumnIndex("singer_name")));
                playList.setSongTime(cursor.getInt(cursor.getColumnIndex("song_time")));
                playList.setNetworkUri(cursor.getString(cursor.getColumnIndex("network_uri")));
                playList.setLocalUri(cursor.getString(cursor.getColumnIndex("local_uri")));
                playLists.add(playList);
            }
        }
        cursor.close();
        db.close();
        return playLists;
    }

    /**
     * 获取所有id
     * @return
     */
    public List<Integer> findAllId(){
        PlayList playList = new PlayList();
        List<Integer> idList = new ArrayList<Integer>();
        SQLiteDatabase db = myHelper.getReadableDatabase();
        Cursor cursor = db.query("play_list",null,null,null,null,null,null);
        if(cursor.getCount() != 0) {
            while (cursor.moveToNext()) {
                idList.add(cursor.getInt(cursor.getColumnIndex("id")));
            }
        }
        cursor.close();
        db.close();
        return idList;
    }

    /**
     * 添加
     * @param playList
     */
    public void insert(PlayList playList) {
        SQLiteDatabase db = myHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("song_name",playList.getSongName());
        values.put("singer_name",playList.getSingerName());
        values.put("song_time",playList.getSongTime());
        values.put("network_uri",playList.getNetworkUri());
        values.put("local_uri",playList.getLocalUri());
        long id = db.insert("play_list",null,values);
        db.close();
    }

    /**
     * 删除
     * @param id
     * @return
     */
    public int delete(Integer id) {
        SQLiteDatabase db = myHelper.getWritableDatabase();
        int number = db.delete("play_list","id=?",new String[]{id+""});
        return number;
    }

    public PlayList findById(int id){
        PlayList playList = new PlayList();
        SQLiteDatabase db = myHelper.getReadableDatabase();
        Cursor cursor = db.query("play_list",null,"id=?",new String[]{id+""},null,null,null);
        if(cursor.getCount() != 0) {
            while (cursor.moveToNext()) {
                playList.setId(cursor.getInt(cursor.getColumnIndex("id")));
                playList.setSongName(cursor.getString(cursor.getColumnIndex("song_name")));
                playList.setSingerName(cursor.getString(cursor.getColumnIndex("singer_name")));
                playList.setSongTime(cursor.getInt(cursor.getColumnIndex("song_time")));
                playList.setNetworkUri(cursor.getString(cursor.getColumnIndex("network_uri")));
                playList.setLocalUri(cursor.getString(cursor.getColumnIndex("local_uri")));
            }
        }
        cursor.close();
        db.close();
        return playList;
    }

    /**
     * 查询
     * @param playList
     * @return
     */
    public List<PlayList> find(PlayList playList) {
        List<PlayList> playListList = new ArrayList<PlayList>();
        SQLiteDatabase db = myHelper.getReadableDatabase();
        String sql = "";
        String parameter = "";
        if(playList.getSongName()!=null) {
            sql += "song_name=?";
            parameter += playList.getSongName();
        }
        if(playList.getSingerName()!=null) {
            sql += "and singer_name=?";
            parameter += "," + playList.getSingerName();
        }
        if(playList.getSongTime()!=null) {
            sql += "and song_time=?";
            parameter += "," + playList.getSongTime();
        }
        if(playList.getLocalUri()!=null) {
            sql += "and local_uri=?";
            parameter += "," + playList.getLocalUri();
        }
        if(playList.getNetworkUri()!=null) {
            sql += "and network_uri=?";
            parameter += "," + playList.getNetworkUri();
        }
        Cursor cursor = db.query("play_list",null,sql,parameter.split(","),null,null,null);
        if(cursor.getCount() != 0) {
            while (cursor.moveToNext()) {
                PlayList pl = new PlayList();
                pl.setId(cursor.getInt(cursor.getColumnIndex("id")));
                pl.setSongName(cursor.getString(cursor.getColumnIndex("song_name")));
                pl.setSingerName(cursor.getString(cursor.getColumnIndex("singer_name")));
                pl.setSongTime(cursor.getInt(cursor.getColumnIndex("song_time")));
                pl.setNetworkUri(cursor.getString(cursor.getColumnIndex("network_uri")));
                pl.setLocalUri(cursor.getString(cursor.getColumnIndex("local_uri")));
                playListList.add(pl);
            }
        }
        cursor.close();
        db.close();
        return playListList;
    }
}
