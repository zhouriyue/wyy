package com.gxuwz.beethoven.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.gxuwz.beethoven.helper.DfHelper;
import com.gxuwz.beethoven.model.entity.LatePlay;
import com.gxuwz.beethoven.model.vo.UserDetailVo;
import com.gxuwz.beethoven.util.DateUtil;

public class UserDetailDao {

    DfHelper dfHelper;

    public UserDetailDao(Context context) {
        this.dfHelper = new DfHelper(context);
    }

    public boolean isExist(Long userId){
        boolean flag = false;
        SQLiteDatabase db = dfHelper.getReadableDatabase();
        Cursor cursor = db.query("user_detail",null,"user_id=?",new String[]{userId+""},null,null,null);
        if(cursor.getCount() != 0) {
            flag = true;
        }
        cursor.close();
        db.close();
        return flag;
    }

    public UserDetailVo findByUserId(Long userId){
        UserDetailVo userDetailVo = null;
        SQLiteDatabase db = dfHelper.getReadableDatabase();
        Cursor cursor = db.query("user_detail",null,"user_id=?",new String[]{userId+""},null,null,null);
        if(cursor.getCount() != 0) {
            while (cursor.moveToNext()) {
                userDetailVo = new UserDetailVo();
                userDetailVo.setUserId(cursor.getLong(cursor.getColumnIndex("user_id")));
                userDetailVo.setDetail(cursor.getString(cursor.getColumnIndex("detail")));
                userDetailVo.setBirthday(DateUtil.parseString(cursor.getString(cursor.getColumnIndex("birthday"))));
                userDetailVo.setAddress(cursor.getString(cursor.getColumnIndex("address")));
                userDetailVo.setPerBg(cursor.getString(cursor.getColumnIndex("per_bg")));
                userDetailVo.setIsMember(cursor.getInt(cursor.getColumnIndex("is_member")));
            }
        }
        cursor.close();
        db.close();
        return userDetailVo;
    }

    public long insert(UserDetailVo userDetailVo) {
        SQLiteDatabase db = dfHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("user_id",userDetailVo.getUserId());
        values.put("detail",userDetailVo.getDetail());
        values.put("birthday", DateUtil.simpleFormat(userDetailVo.getBirthday()));
        values.put("address", userDetailVo.getAddress());
        values.put("per_bg", userDetailVo.getPerBg());
        values.put("is_member", userDetailVo.getIsMember());
        long id = db.insert("user_detail",null,values);
        db.close();
        return id;
    }
}
