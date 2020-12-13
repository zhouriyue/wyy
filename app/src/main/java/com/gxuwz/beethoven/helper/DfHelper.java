package com.gxuwz.beethoven.helper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DfHelper extends SQLiteOpenHelper {

    public DfHelper(Context context) {
        super(context,"dfmusic.db",null,21);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //用户表
        db.execSQL("CREATE TABLE sys_user(" +
                "user_id INTEGER(11) PRIMARY KEY," +
                "user_name VARCHAR(30)," +
                "nick_name VARCHAR(50)," +
                "email VARCHAR(50)," +
                "phonenumber VARCHAR(11)," +
                "sex CHAR(1)," +
                "avatar VARCHAR(100)," +
                "create_time VARCHAR(50))");
        //用户详情表
        db.execSQL("CREATE TABLE user_detail(" +
                "user_id INTEGER(11) PRIMARY KEY," +
                "detail VARCHAR(255)," +
                "birthday VARCHAR(50)," +
                "address VARCHAR(255)," +
                "per_bg VARCHAR(255)," +
                "is_member INTEGER(1))");
        //歌单表
        db.execSQL("CREATE TABLE songlist(" +
                "sl_id INTEGER(11) PRIMARY KEY," +
                "sl_name VARCHAR(50)," +
                "cover_picture VARCHAR(255)," +
                "sl_title VARCHAR(50)," +
                "play_number INTEGER(8)," +
                "song_number INTEGER(8)," +
                "col_number INTEGER(8)," +
                "comments_number INTEGER(8)," +
                "share_number INTEGER(8)," +
                "create_by_id INTEGER(11)," +
                "detail VARCHAR(255)," +
                "is_album INTEGER(1)," +
                "sin_id INTEGER(11)," +
                "is_public INTEGER(1))");
        //歌单--歌曲联系表
        db.execSQL("CREATE TABLE songlist_song(" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "sl_id INTEGER(11)," +
                "song_id INTEGER(11))");
        //歌单歌曲表
        db.execSQL("CREATE TABLE song(" +
                "song_id INTEGER(11) PRIMARY KEY," +
                "song_name VARCHAR(50)," +
                "cover_picture VARCHAR(255)," +
                "duration INTEGER(11)," +
                "issuing_date VARCHAR(50)," +
                "mv_url VARCHAR(255)," +
                "is_charge INTEGER(1)," +
                "is_copyright INTEGER(1)," +
                "is_single INTEGER(1)," +
                "standard_url VARCHAR(255)," +
                "hq_url VARCHAR(255)," +
                "sq_url VARCHAR(255)," +
                "wit_pre_url VARCHAR(255)," +
                "timbre_type INTEGER(1)," +
                "lyr_url VARCHAR(255)," +
                "lyr_id INTEGER(11))");
        //歌手
        db.execSQL("CREATE TABLE singer(" +
                "sin_id  INTEGER(11) PRIMARY KEY," +
                "sin_name VARCHAR(50)," +
                "nickname VARCHAR(50)," +
                "sex INTEGER(1)," +
                "year INTEGER(3)," +
                "sin_type INTEGER(1)," +
                "area VARCHAR(255)," +
                "song_number INTEGER(8)," +
                "follower_number INTEGER(8)," +
                "album_number INTEGER(8)," +
                "mv_number INTEGER(8)," +
                "detail VARCHAR(255)," +
                "influence_power VARCHAR(255)," +
                "cert_info VARCHAR(255)," +
                "early_exp VARCHAR(255)," +
                "performing_exp VARCHAR(255))");
        //歌手--歌曲联系表
        db.execSQL("CREATE TABLE song_singer(" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "song_id INTEGER(11)," +
                "sin_id INTEGER(11))");
        //播放列表
        db.execSQL("CREATE TABLE play_list(" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "sl_id INTEGER(11)," +
                "song_id INTEGER(11)," +
                "play_grade INTEGER(8) DEFAULT 0)");
        //歌曲
        db.execSQL("CREATE TABLE lyric(" +
                "lyr_id INTEGER PRIMARY KEY," +
                "lyr_name VARCHAR(50)," +
                "lyr_url VARCHAR(255)," +
                "issuing_date VARCHAR(50))");
        //本地歌曲
        db.execSQL("CREATE TABLE local_song(" +
                "song_id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "song_name VARCHAR(50)," +
                "singer_name VARCHAR(50)," +
                "cover_picture VARCHAR(255)," +
                "duration INTEGER(11)," +
                "issuing_date VARCHAR(50)," +
                "song_url VARCHAR(255)," +
                "lyr_url VARCHAR(255))");
        //最近播放
        db.execSQL("CREATE TABLE late_play(" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "sl_id INTEGER(11)," +
                "song_id INTEGER(11)," +
                "play_date VARCHAR(50))");
        //操作
        db.execSQL("CREATE TABLE operate(" +
                "id INTEGER PRIMARY KEY," +
                "o_id VARCHAR(50)," +
                "o_name VARCHAR(50)," +
                "icon VARCHAR(255)," +
                "type INTEGER(2))");
        //操作联系表
        db.execSQL("CREATE TABLE type_operate(" +
                "t_id INTEGER PRIMARY KEY," +
                "type INTEGER(11)," +
                "id INTEGER(11))");
        //会员表
        db.execSQL("CREATE TABLE member(" +
                "m_id INTEGER PRIMARY KEY," +
                "valid_day INTEGER(11)," +
                "m_grade INTEGER(3)," +
                "user_id INTEGER(11)," +
                "enroll_date VARCHAR(50))");
        //购买记录表
        db.execSQL("CREATE TABLE purchase_record(" +
                "id INTEGER PRIMARY KEY," +
                "user_id INTEGER(11)," +
                "sm_id INTEGER(11)," +
                "is_series INTEGER(1)," +
                "buy_date VARCHAR(50))");
        //套餐表
        db.execSQL("CREATE TABLE set_meal(" +
                "sm_id INTEGER PRIMARY KEY," +
                "sm_name VARCHAR(50)," +
                "current_prices double(8,3)," +
                "cost_price double(8,3)," +
                "recharge_month INTEGER(8)," +
                "detail VARCHAR(50))");
        //下载列表
        db.execSQL("CREATE TABLE loaddown(url VARCHAR(255) PRIMARY KEY,length INTEGER,start INTEGER,now INTEGER)");
        //断点续传
        db.execSQL(SQL_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL(SQL_DROP);
        sqLiteDatabase.execSQL(SQL_CREATE);
    }

    public static DfHelper getInstance(Context context) {
        synchronized (DfHelper.class) {
            if (dbHelper == null) {
                synchronized (DfHelper.class) {
                    dbHelper = new DfHelper(context);
                }
            }
        }
        return dbHelper;
    }

    private static DfHelper dbHelper = null;
    private static final String SQL_CREATE = "create table thread_info(_id integer primary key autoincrement," +
            "thread_id integer,file_name VARCHAR(50),url text,start integer,ends integer,finished integer,down_type integer)";
    private static final String SQL_DROP = "drop table if exists thread_info";
}
