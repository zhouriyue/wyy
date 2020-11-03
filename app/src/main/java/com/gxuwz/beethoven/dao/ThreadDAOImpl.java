package com.gxuwz.beethoven.dao;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.gxuwz.beethoven.helper.DfHelper;
import com.gxuwz.beethoven.model.entity.FileInfo;
import com.gxuwz.beethoven.model.entity.ThreadInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * 数据访问接口的实现
 */

public class ThreadDAOImpl implements ThreadDAO {

    private DfHelper mHelper = null;

    public ThreadDAOImpl(Context context) {
        mHelper = DfHelper.getInstance(context);
    }

    public List<FileInfo> findAllFileInfo(){
        List<FileInfo> fileInfos = new ArrayList<FileInfo>();
        SQLiteDatabase db = mHelper.getReadableDatabase();
        Cursor cursor = db.query("thread_info",null,null,null,null,null,null);
        if(cursor.getCount() != 0) {
            while (cursor.moveToNext()) {
                FileInfo fileInfo = new FileInfo();
                fileInfo.setId(cursor.getInt(cursor.getColumnIndex("thread_id")));
                fileInfo.setFileName(cursor.getString(cursor.getColumnIndex("file_name")));
                fileInfo.setUrl(cursor.getString(cursor.getColumnIndex("url")));
                fileInfo.setLength(cursor.getInt(cursor.getColumnIndex("ends")));
                fileInfo.setFinished(cursor.getInt(cursor.getColumnIndex("finished")));
                fileInfos.add(fileInfo);
            }
        }
        cursor.close();
        db.close();
        return fileInfos;
    }

    @Override
    public synchronized void insertThread(ThreadInfo threadInfo) {
        SQLiteDatabase db = mHelper.getWritableDatabase();
        db.execSQL(
                "insert into thread_info(thread_id,file_name,url,start,ends,finished,down_type) values(?,?,?,?,?,?,?)",
                new Object[]{threadInfo.getId(),threadInfo.getFileName(), threadInfo.getUrl(), threadInfo.getStart(),
                        threadInfo.getEnds(), threadInfo.getFinished(),threadInfo.getDownType()});
        db.close();
    }

    @Override
    public synchronized void deleteThread(String url) {
        SQLiteDatabase db = mHelper.getWritableDatabase();
        db.execSQL(
                "delete from thread_info where url = ?",
                new Object[]{url});
        db.close();
    }

    @Override
    public synchronized void updateThread(String url, int thread_id, int finished) {
        SQLiteDatabase db = mHelper.getWritableDatabase();
        db.execSQL(
                "update thread_info set finished = ? where url = ? and thread_id = ?",
                new Object[]{finished, url, thread_id});
        db.close();
    }

    @Override
    public List<ThreadInfo> getThreads(String url) {
        List<ThreadInfo> list = new ArrayList<>();
        SQLiteDatabase db = mHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from thread_info where url = ?",
                new String[]{url});
        while (cursor.moveToNext()) {
            ThreadInfo thread = new ThreadInfo();
            thread.setId(cursor.getInt(cursor.getColumnIndex("thread_id")));
            thread.setFileName(cursor.getString(cursor.getColumnIndex("file_name")));
            thread.setUrl(cursor.getString(cursor.getColumnIndex("url")));
            thread.setStart(cursor.getInt(cursor.getColumnIndex("start")));
            thread.setEnds(cursor.getInt(cursor.getColumnIndex("ends")));
            thread.setFinished(cursor.getInt(cursor.getColumnIndex("finished")));
            thread.setDownType(cursor.getInt(cursor.getColumnIndex("down_type")));
            list.add(thread);
        }
        cursor.close();
        db.close();
        return list;
    }

    @Override
    public boolean isExists(String url, int thread_id) {
        SQLiteDatabase db = mHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from thread_info where url = ? and thread_id = ?",
                new String[]{url, thread_id + ""});
        boolean exists = cursor.moveToNext();
        cursor.close();
        db.close();
        return exists;
    }
}
