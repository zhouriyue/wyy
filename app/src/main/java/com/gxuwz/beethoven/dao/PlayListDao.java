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

    /** 播放等级控制 **/
    public void updatePlayGrade(PlayList playList){
        int status = playList.getPlayGrade();
        SQLiteDatabase db = dfHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("select min(play_grade) min,max(play_grade) max from play_list",null);
        int min = 0,max = 0;
        if(cursor.getCount() != 0) {
            while (cursor.moveToNext()) {
                min = cursor.getInt(cursor.getColumnIndex("min"));
                max = cursor.getInt(cursor.getColumnIndex("max"));
            }
        }
        cursor.close();
        db.close();
        switch (max-min) {
            case 0:{
                playList.setPlayGrade(min+1);
            };break;
            case 1:{
                playList.setPlayGrade(max+1);
            };break;
            case 2:{
                playList.setPlayGrade(max);
            };break;
        }
        if(status==0){
            insert(playList);
        } else {
            update(playList);
        }
    }

    /** 获取播放等级为最小值得歌曲 **/
    public List<PlayList> findBGMin(){
        List<PlayList> playLists = new ArrayList<PlayList>();
        SQLiteDatabase db = dfHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from play_list " +
                "where play_grade = (select min(play_grade) from play_list)",null);
        if(cursor.getCount() != 0) {
            while (cursor.moveToNext()) {
                PlayList playList = new PlayList();
                playList.setId(cursor.getInt(cursor.getColumnIndex("id")));
                playList.setSlId(cursor.getLong(cursor.getColumnIndex("sl_id")));
                playList.setSongId(cursor.getLong(cursor.getColumnIndex("song_id")));
                playList.setPlayGrade(cursor.getInt(cursor.getColumnIndex("play_grade")));
                playLists.add(playList);
            }
        }
        cursor.close();
        db.close();
        return playLists;
    }

    /** 获取最大等级个数 */
    public int maxPGrade(){
        int maxGradeCount = 0;
        SQLiteDatabase db = dfHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("select count(*) count from play_list " +
                "where play_grade = (select max(play_grade) from play_list)",null);
        if(cursor.getCount() != 0) {
            while (cursor.moveToNext()) {
                maxGradeCount = cursor.getInt(cursor.getColumnIndex("max"));
            }
        }
        cursor.close();
        db.close();
        return maxGradeCount;
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
                playList.setPlayGrade(cursor.getInt(cursor.getColumnIndex("play_grade")));
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
        values.put("play_grade",playList.getPlayGrade());
        long id = db.insert("play_list",null,values);
        db.close();
    }

    public long update(PlayList playList){
        SQLiteDatabase db = dfHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("play_grade",playList.getPlayGrade());
        long id = db.update("play_list",values,"id=?",new String[]{playList.getId()+""});
        db.close();
        return id;
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
                playList.setPlayGrade(cursor.getInt(cursor.getColumnIndex("play_grade")));
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
                playList.setPlayGrade(cursor.getInt(cursor.getColumnIndex("play_grade")));
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
                playList.setPlayGrade(cursor.getInt(cursor.getColumnIndex("play_grade")));
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
            if(playList.getSongId()!=null) {
                sql += "and song_id=?";
                parameter += "," + playList.getSongId();
            }
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
