package com.gxuwz.beethoven.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.gxuwz.beethoven.helper.DfHelper;
import com.gxuwz.beethoven.model.entity.current.Member;
import com.gxuwz.beethoven.util.DateUtil;

import java.util.Date;

public class MemberDao {

    DfHelper dfHelper;

    public MemberDao(Context context) {
        this.dfHelper = new DfHelper(context);
    }

    public Member findById(long userId){
        Member member = null;
        SQLiteDatabase db = dfHelper.getReadableDatabase();
        Cursor cursor = db.query("member",null,"user_id=?",new String[]{userId+""},null,null,null);
        if(cursor.getCount() != 0) {
            while (cursor.moveToNext()) {
                member = new Member();
                member.setmId(cursor.getLong(cursor.getColumnIndex("m_id")));
                member.setValidDay(cursor.getLong(cursor.getColumnIndex("valid_day")));
                member.setmGrade(cursor.getInt(cursor.getColumnIndex("m_grade")));
                member.setUserId(cursor.getLong(cursor.getColumnIndex("user_id")));
                member.setEnrollDate(DateUtil.parseString(cursor.getString(cursor.getColumnIndex("enroll_date"))));
            }
        }
        cursor.close();
        db.close();
        return member;
    }

    public long update(Member member) {
        SQLiteDatabase db = dfHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("valid_day",member.getValidDay());
        values.put("m_grade", member.getmGrade());
        values.put("user_id", member.getUserId());
        values.put("enroll_date", DateUtil.simpleFormat(member.getEnrollDate()));
        long id = db.update("member",values,"m_id=?",new String[]{member.getmId()+""});
        db.close();
        return id;
    }

    public long insert(Member member) {
        SQLiteDatabase db = dfHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("m_id",member.getmId());
        values.put("valid_day",member.getValidDay());
        values.put("m_grade", member.getmGrade());
        values.put("user_id", member.getUserId());
        values.put("enroll_date", DateUtil.simpleFormat(member.getEnrollDate()));
        long id = db.insert("member",null,values);
        db.close();
        return id;
    }
}
