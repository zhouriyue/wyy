package com.gxuwz.beethoven.model.entity.mlog;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.gxuwz.beethoven.helper.DfHelper;
import com.gxuwz.beethoven.model.entity.SysUser;

public class SysUserDao {

    DfHelper dfHelper;

    public SysUserDao(Context context) {
        dfHelper = new DfHelper(context);
    }

    public SysUser findByUserId(String userId){
        SysUser sysUser = null;
        SQLiteDatabase db = dfHelper.getWritableDatabase();
        Cursor cursor = db.query("sys_user",null,"user_id=?",new String[]{userId},null,null,null);
        if (cursor.getCount()!=0) {
            while (cursor.moveToNext()){
                sysUser = new SysUser();
                sysUser.setUserId(cursor.getString(cursor.getColumnIndex("user_id")));
                sysUser.setUserName(cursor.getString(cursor.getColumnIndex("user_name")));
                sysUser.setUserPwd(cursor.getString(cursor.getColumnIndex("user_pwd")));
                sysUser.setNickName(cursor.getString(cursor.getColumnIndex("nick_name")));
                sysUser.setRecommend(cursor.getString(cursor.getColumnIndex("recommend")));
                sysUser.setSex(cursor.getString(cursor.getColumnIndex("sex")));
                sysUser.setBirthday(cursor.getString(cursor.getColumnIndex("birthday")));
                sysUser.setIsMember(cursor.getInt(cursor.getColumnIndex("is_member")));
                sysUser.setGrade(cursor.getInt(cursor.getColumnIndex("grade")));
                sysUser.setMenberId(cursor.getInt(cursor.getColumnIndex("menber_id")));
                sysUser.setPerPic(cursor.getString(cursor.getColumnIndex("per_pic")));
                sysUser.setReallyId(cursor.getString(cursor.getColumnIndex("reallyId")));
            }
        }
        cursor.close();
        db.close();
        return sysUser;
    }

    public long update(SysUser sysUser){
        SQLiteDatabase db = dfHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("user_name",sysUser.getUserName());
        values.put("user_pwd",sysUser.getUserPwd());
        values.put("nick_name",sysUser.getNickName());
        values.put("recommend",sysUser.getRecommend());
        values.put("sex",sysUser.getSex());
        values.put("birthday",sysUser.getBirthday());
        values.put("is_member",sysUser.getIsMember());
        values.put("grade",sysUser.getGrade());
        values.put("menber_id",sysUser.getMenberId());
        values.put("per_pic",sysUser.getPerPic());
        values.put("reallyId",sysUser.getReallyId());
        long stauts = db.update("sys_user",values,"user_id=?",new String[]{sysUser.getUserId()});
        db.close();
        return stauts;
    }

    public int delete(String userId) {
        SQLiteDatabase db = dfHelper.getWritableDatabase();
        int number = db.delete("sys_user","user_id=?",new String[]{userId});
        db.close();
        return number;
    }

    public long insert(SysUser sysUser){
        SQLiteDatabase db = dfHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("user_id",sysUser.getUserId());
        values.put("user_name",sysUser.getUserName());
        values.put("user_pwd",sysUser.getUserPwd());
        values.put("nick_name",sysUser.getNickName());
        values.put("recommend",sysUser.getRecommend());
        values.put("sex",sysUser.getSex());
        values.put("birthday",sysUser.getBirthday());
        values.put("is_member",sysUser.getIsMember());
        values.put("grade",sysUser.getGrade());
        values.put("menber_id",sysUser.getMenberId());
        values.put("per_pic",sysUser.getPerPic());
        values.put("reallyId",sysUser.getReallyId());
        long stauts = db.insert("sys_user",null,values);
        db.close();
        return stauts;
    }
}
