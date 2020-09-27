package com.gxuwz.beethoven.util;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MyHelper extends SQLiteOpenHelper {

    public MyHelper(Context context) {
        super(context,"dfmusic.db",null,2);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE play_list(id INTEGER PRIMARY KEY AUTOINCREMENT,song_name VARCHAR(30),singer_name VARCHAR(30),song_time INTEGER,network_uri VARCHAR(255),local_uri VARCHAR(255))");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
