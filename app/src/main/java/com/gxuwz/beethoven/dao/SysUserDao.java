package com.gxuwz.beethoven.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.gxuwz.beethoven.helper.DfHelper;
import com.gxuwz.beethoven.model.entity.current.SysUser;
import com.gxuwz.beethoven.util.DateUtil;

public class SysUserDao {

    DfHelper dfHelper;

    public SysUserDao(Context context) {
        this.dfHelper = new DfHelper(context);
    }

    public SysUser findById(Long userId){
        SysUser sysUser = null;
        SQLiteDatabase db = dfHelper.getReadableDatabase();
        Cursor cursor = db.query("sys_user",null,"user_id=?",new String[]{userId+""},null,null,null);
        if(cursor.getCount() != 0) {
            while (cursor.moveToNext()) {
                sysUser = new SysUser();
                sysUser.setUserId(cursor.getLong(cursor.getColumnIndex("user_id")));
                sysUser.setUserName(cursor.getString(cursor.getColumnIndex("user_name")));
                sysUser.setNickName(cursor.getString(cursor.getColumnIndex("nick_name")));
                sysUser.setEmail(cursor.getString(cursor.getColumnIndex("email")));
                sysUser.setPhonenumber(cursor.getString(cursor.getColumnIndex("phonenumber")));
                sysUser.setSex(cursor.getString(cursor.getColumnIndex("sex")));
                sysUser.setAvatar(cursor.getString(cursor.getColumnIndex("avatar")));
                sysUser.setCreateTime(DateUtil.parseString(cursor.getString(cursor.getColumnIndex("create_time"))));
            }
        }
        cursor.close();
        db.close();
        return sysUser;
    }

    public long insert(SysUser sysUser) {
        SQLiteDatabase db = dfHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("user_id",sysUser.getUserId());
        values.put("user_name",sysUser.getUserName());
        values.put("nick_name",sysUser.getNickName());
        values.put("email",sysUser.getEmail());
        values.put("phonenumber",sysUser.getPhonenumber());
        values.put("sex",sysUser.getSex());
        values.put("avatar",sysUser.getAvatar());
        values.put("create_time",DateUtil.simpleFormat(sysUser.getCreateTime()));
        long id = db.insert("sys_user",null,values);
        db.close();
        return id;
    }
}
