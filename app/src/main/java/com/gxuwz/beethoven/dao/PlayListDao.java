package com.gxuwz.beethoven.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.gxuwz.beethoven.helper.DfHelper;
import com.gxuwz.beethoven.model.entity.current.PlayList;

import java.util.ArrayList;
import java.util.List;

public class PlayListDao {

    DfHelper dfHelper;

    public PlayListDao(Context context) {
        this.dfHelper = new DfHelper(context);
    }

    /**
     * 获取所有
     * @return
     */
    public List<PlayList> findAll(){
        List<PlayList> playLists = new ArrayList<PlayList>();
        SQLiteDatabase db = dfHelper.getReadableDatabase();
        Cursor cursor = db.query("play_list",null,null,null,null,null,null);
        if(cursor.getCount() != 0) {
            while (cursor.moveToNext()) {
                PlayList playList = new PlayList();
                playList.setId(cursor.getInt(cursor.getColumnIndex("id")));
                playList.setSlId(cursor.getLong(cursor.getColumnIndex("sl_id")));
                playList.setSongId(cursor.getLong(cursor.getColumnIndex("song_id")));
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
        SQLiteDatabase db = dfHelper.getReadableDatabase();
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
        SQLiteDatabase db = dfHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("sl_id",playList.getSlId());
        values.put("song_id",playList.getSongId());
        long id = db.insert("play_list",null,values);
        db.close();
    }

    /**
     * 删除
     * @param id
     * @return
     */
    public int delete(Integer id) {
        SQLiteDatabase db = dfHelper.getWritableDatabase();
        int number = db.delete("play_list","id=?",new String[]{id+""});
        db.close();
        return number;
    }

    /**
     * 通过songId查询
     * @param songId
     * @return
     */
    public PlayList findBySlIdAndSongId(Long slId,Long songId){
        PlayList playList = null;
        SQLiteDatabase db = dfHelper.getReadableDatabase();
        Cursor cursor = db.query("play_list",null,"sl_id=? and song_id=?",new String[]{slId+"",songId+""},null,null,null);
        if(cursor.getCount() != 0) {
            while (cursor.moveToNext()) {
                playList = new PlayList();
                playList.setId(cursor.getInt(cursor.getColumnIndex("id")));
                playList.setSlId(cursor.getLong(cursor.getColumnIndex("sl_id")));
                playList.setSongId(cursor.getLong(cursor.getColumnIndex("song_id")));
            }
        }
        cursor.close();
        db.close();
        return playList;
    }

    public PlayList findById(int id){
        PlayList playList = null;
        SQLiteDatabase db = dfHelper.getReadableDatabase();
        Cursor cursor = db.query("play_list",null,"id=?",new String[]{id+""},null,null,null);
        if(cursor.getCount() != 0) {
            while (cursor.moveToNext()) {
                playList = new PlayList();
                playList.setId(cursor.getInt(cursor.getColumnIndex("id")));
                playList.setSlId(cursor.getLong(cursor.getColumnIndex("sl_id")));
                playList.setSongId(cursor.getLong(cursor.getColumnIndex("song_id")));
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
        SQLiteDatabase db = dfHelper.getReadableDatabase();
        String sql = "";
        String parameter = "";
        if(playList.getSlId()!=null) {
            sql += "sl_id=?";
            parameter += playList.getSlId();
        }
        if(playList.getSongId()!=null) {
            sql += "and song_id=?";
            parameter += "," + playList.getSongId();
        }
        Cursor cursor = db.query("play_list",null,sql,parameter.split(","),null,null,null);
        if(cursor.getCount() != 0) {
            while (cursor.moveToNext()) {
                PlayList pl = new PlayList();
                playList.setId(cursor.getInt(cursor.getColumnIndex("id")));
                playList.setSlId(cursor.getLong(cursor.getColumnIndex("sl_id")));
                playList.setSongId(cursor.getLong(cursor.getColumnIndex("song_id")));
                playListList.add(pl);
            }
        }
        cursor.close();
        db.close();
        return playListList;
    }

    /**
     * 查询
     * @param playList
     * @return
     */
    public boolean isExist(PlayList playList) {
        boolean flag = false;
        SQLiteDatabase db = dfHelper.getReadableDatabase();
        String sql = "";
        String parameter = "";
        if(playList.getSlId()!=null) {
            sql += "sl_id=?";
            parameter += playList.getSlId();
        }
        if(playList.getSongId()!=null) {
            sql += "and song_id=?";
            parameter += "," + playList.getSongId();
        }
        Cursor cursor = db.query("play_list",null,sql,parameter.split(","),null,null,null);
        if(cursor.getCount() != 0) {
            flag = true;
        }
        cursor.close();
        db.close();
        return flag;
    }
}
