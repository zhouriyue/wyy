package com.gxuwz.beethoven.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.gxuwz.beethoven.helper.DfHelper;
import com.gxuwz.beethoven.model.entity.current.Operate;
import com.gxuwz.beethoven.model.entity.current.PlayList;
import com.gxuwz.beethoven.model.entity.current.TypeOperate;

import java.util.ArrayList;
import java.util.List;

public class OperateDao {

    DfHelper dfHelper;

    public OperateDao(Context context) {
        this.dfHelper = new DfHelper(context);
    }

    public List<Operate> findByType(int type){
        List<Operate> operates = new ArrayList<Operate>();
        SQLiteDatabase db = dfHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("select o.id, o.o_id,o.o_name, o.icon, tyo.type " +
                "from operate o,type_operate tyo where o.id = tyo.id and tyo.type=?",new String[]{type+""});
        if(cursor.getCount() != 0) {
            while (cursor.moveToNext()) {
                Operate operate = new Operate();
                operate.setId(cursor.getLong(cursor.getColumnIndex("id")));
                operate.setoId(cursor.getString(cursor.getColumnIndex("o_id")));
                operate.setoName(cursor.getString(cursor.getColumnIndex("o_name")));
                operate.setIcon(cursor.getString(cursor.getColumnIndex("icon")));
                operate.setType(cursor.getInt(cursor.getColumnIndex("type")));
                operates.add(operate);
            }
        }
        cursor.close();
        db.close();
        return operates;
    }

    public boolean isExistT(Long tId) {
        boolean flag = false;
        SQLiteDatabase db = dfHelper.getReadableDatabase();
        Cursor cursor = db.query("type_operate",null,"t_id=?",new String[]{tId+""},null,null,null);
        if(cursor.getCount() != 0) {
            flag = true;
        }
        cursor.close();
        db.close();
        return flag;
    }

    public boolean isExist(Long id) {
        boolean flag = false;
        SQLiteDatabase db = dfHelper.getReadableDatabase();
        Cursor cursor = db.query("operate",null,"id=?",new String[]{id+""},null,null,null);
        if(cursor.getCount() != 0) {
            flag = true;
        }
        cursor.close();
        db.close();
        return flag;
    }

    /**
     * 添加
     * @param typeOperate
     */
    public long insert(TypeOperate typeOperate) {
        SQLiteDatabase db = dfHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("t_id",typeOperate.gettId());
        values.put("type",typeOperate.getType());
        values.put("id",typeOperate.getId());
        long id = db.insert("type_operate",null,values);
        db.close();
        return id;
    }

    /**
     * 添加
     * @param operate
     */
    public long insert(Operate operate) {
        SQLiteDatabase db = dfHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("id",operate.getId());
        values.put("o_id",operate.getoId());
        values.put("o_name",operate.getoName());
        values.put("icon",operate.getIcon());
        values.put("type",operate.getType());
        long id = db.insert("operate",null,values);
        db.close();
        return id;
    }
}
