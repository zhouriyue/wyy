package com.gxuwz.beethoven.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.gxuwz.beethoven.helper.DfHelper;
import com.gxuwz.beethoven.model.entity.current.Songlist;
import com.gxuwz.beethoven.util.HttpUtil;
import com.gxuwz.beethoven.util.staticdata.StaticHttp;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import static androidx.constraintlayout.widget.Constraints.TAG;

public class SongListDao {

    DfHelper dfHelper;

    public SongListDao(Context context){
        dfHelper = new DfHelper(context);
    }

    public Songlist findById(Long slId){
        Songlist songlist = new Songlist();
        SQLiteDatabase db = dfHelper.getWritableDatabase();
        Cursor cursor = db.query("songlist",null,"sl_id=?",new String[]{slId+""},null,null,null);
        if(cursor.getCount()!=0) {
            while (cursor.moveToNext()){
                songlist.setSlId(cursor.getLong(cursor.getColumnIndex("sl_id")));
                songlist.setSlName(cursor.getString(cursor.getColumnIndex("sl_name")));
                songlist.setCoverPicture(cursor.getString(cursor.getColumnIndex("cover_picture")));
                songlist.setSlTitle(cursor.getString(cursor.getColumnIndex("sl_title")));
                songlist.setPlayNumber(cursor.getInt(cursor.getColumnIndex("play_number")));
                songlist.setSongNumber(cursor.getInt(cursor.getColumnIndex("song_number")));
                songlist.setColNumber(cursor.getInt(cursor.getColumnIndex("col_number")));
                songlist.setCommentsNumber(cursor.getInt(cursor.getColumnIndex("comments_number")));
                songlist.setShareNumber(cursor.getInt(cursor.getColumnIndex("share_number")));
                songlist.setCreateById(cursor.getLong(cursor.getColumnIndex("create_by_id")));
                songlist.setDetail(cursor.getString(cursor.getColumnIndex("detail")));
                songlist.setIsAlbum(cursor.getInt(cursor.getColumnIndex("is_album")));
                songlist.setSinId(cursor.getLong(cursor.getColumnIndex("sin_id")));
                songlist.setIsPublic(cursor.getInt(cursor.getColumnIndex("is_public")));
            }
        }
        cursor.close();
        db.close();
        return songlist;
    }

    public boolean isExsit(Long slId){
        boolean flag = false;
        SQLiteDatabase db = dfHelper.getWritableDatabase();
        Cursor cursor = db.query("songlist",null,"sl_id=?",new String[]{slId+""},null,null,null);
        if(cursor.getCount()!=0){
            flag = true;
        }
        cursor.close();
        db.close();
        return flag;
    }

    public long deleteSonglistSong(Long slId,Long songId){
        SQLiteDatabase db = dfHelper.getWritableDatabase();
        long id = db.delete("songlist_song","sl_id=? and song_id=?",new String[]{slId+"",songId+""});
        db.close();
        return id;
    }

    public long delete(Long slId){
        SQLiteDatabase db = dfHelper.getWritableDatabase();
        long staus = db.delete("songlist","sl_id=?",new String[]{slId+""});
        db.close();
        return staus;
    }

    public void deleteTable() {
        SQLiteDatabase database = dfHelper.getWritableDatabase();
        //database.execSQL("drop table song_list");
    }

    public List<Songlist> findUserSL(Long userId){
        List<Songlist> songlists = new ArrayList<Songlist>();
        SQLiteDatabase db = dfHelper.getWritableDatabase();
        System.out.println(userId);
        Cursor cursor = db.query("songlist",null,"create_by_id=?",new String[]{userId+""},null,null,null);
        if(cursor.getCount()!=0) {
            while (cursor.moveToNext()){
                Songlist songlist = new Songlist();
                songlist.setSlId(cursor.getLong(cursor.getColumnIndex("sl_id")));
                songlist.setSlName(cursor.getString(cursor.getColumnIndex("sl_name")));
                songlist.setCoverPicture(cursor.getString(cursor.getColumnIndex("cover_picture")));
                songlist.setSlTitle(cursor.getString(cursor.getColumnIndex("sl_title")));
                songlist.setPlayNumber(cursor.getInt(cursor.getColumnIndex("play_number")));
                songlist.setSongNumber(cursor.getInt(cursor.getColumnIndex("song_number")));
                songlist.setColNumber(cursor.getInt(cursor.getColumnIndex("col_number")));
                songlist.setCommentsNumber(cursor.getInt(cursor.getColumnIndex("comments_number")));
                songlist.setShareNumber(cursor.getInt(cursor.getColumnIndex("share_number")));
                songlist.setCreateById(cursor.getLong(cursor.getColumnIndex("create_by_id")));
                songlist.setDetail(cursor.getString(cursor.getColumnIndex("detail")));
                songlist.setIsAlbum(cursor.getInt(cursor.getColumnIndex("is_album")));
                songlist.setSinId(cursor.getLong(cursor.getColumnIndex("sin_id")));
                songlist.setIsPublic(cursor.getInt(cursor.getColumnIndex("is_public")));
                songlists.add(songlist);
            }
        }
        cursor.close();
        db.close();
        return songlists;
    }

    public long update(Songlist songlist){
        SQLiteDatabase db = dfHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("sl_name",songlist.getSlName());
        values.put("cover_picture",songlist.getCoverPicture());
        values.put("sl_title",songlist.getSlTitle());
        values.put("play_number",songlist.getPlayNumber());
        values.put("song_number",songlist.getSongNumber());
        values.put("col_number",songlist.getColNumber());
        values.put("comments_number",songlist.getCommentsNumber());
        values.put("share_number",songlist.getShareNumber());
        values.put("create_by_id",songlist.getCreateById());
        values.put("detail",songlist.getDetail());
        values.put("is_album",songlist.getIsAlbum());
        values.put("sin_id",songlist.getSinId());
        values.put("is_public",songlist.getIsPublic());
        long status = db.update("songlist",values,"sl_id=?",new String[]{songlist.getSlId()+""});
        db.close();
        return status;
    }

    public long insert(Songlist songlist){
        SQLiteDatabase db = dfHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("sl_id",songlist.getSlId());
        values.put("sl_name",songlist.getSlName());
        values.put("cover_picture",songlist.getCoverPicture());
        values.put("sl_title",songlist.getSlTitle());
        values.put("play_number",songlist.getPlayNumber());
        values.put("song_number",songlist.getSongNumber());
        values.put("col_number",songlist.getColNumber());
        values.put("comments_number",songlist.getCommentsNumber());
        values.put("share_number",songlist.getShareNumber());
        values.put("create_by_id",songlist.getCreateById());
        values.put("detail",songlist.getDetail());
        values.put("is_album",songlist.getIsAlbum());
        values.put("sin_id",songlist.getSinId());
        values.put("is_public",songlist.getIsPublic());
        long status = db.insert("songlist",null,values);
        db.close();
        return status;
    }

    public static void deleteRequest(Long slId,Long songId) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    String httpUrl = StaticHttp.BASEURL+StaticHttp.DELECT_SONGLIST_SONG;
                    URL url = new URL(httpUrl);
                    HttpURLConnection conn = (HttpURLConnection)url.openConnection();
                    conn.setReadTimeout(5000);
                    conn.setRequestMethod("DELETE");

                    //获得一个输出流，向服务器写入数据
                    String params = "slId="+slId+"&songId="+songId;
                    OutputStream outputStream = conn.getOutputStream();
                    outputStream.write(params.getBytes());
                    conn.getResponseCode();
                }catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        }).start();
    }
}
