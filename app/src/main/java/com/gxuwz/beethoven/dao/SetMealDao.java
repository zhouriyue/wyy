package com.gxuwz.beethoven.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.gxuwz.beethoven.helper.DfHelper;
import com.gxuwz.beethoven.model.entity.current.SetMeal;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class SetMealDao {

    DfHelper dfHelper;

    public SetMealDao(Context context) {
        this.dfHelper = new DfHelper(context);
    }

    public List<SetMeal> findAll(){
        List<SetMeal> setMealList = new ArrayList<SetMeal>();
        SQLiteDatabase db = dfHelper.getReadableDatabase();
        Cursor cursor = db.query("set_meal",null,null,null,null,null,null);
        if(cursor.getCount() != 0) {
            while (cursor.moveToNext()) {
                SetMeal setMeal = new SetMeal();
                setMeal.setSmId(cursor.getLong(cursor.getColumnIndex("sm_id")));
                setMeal.setSmName(cursor.getString(cursor.getColumnIndex("sm_name")));
                setMeal.setCurrentPrices(cursor.getDouble(cursor.getColumnIndex("current_prices")));
                setMeal.setCostPrice(cursor.getDouble(cursor.getColumnIndex("cost_price")));
                setMeal.setRechargeMonth(cursor.getInt(cursor.getColumnIndex("recharge_month")));
                setMeal.setDetail(cursor.getString(cursor.getColumnIndex("detail")));
                setMealList.add(setMeal);
            }
        }
        cursor.close();
        db.close();
        return setMealList;
    }

    public boolean isExist(Long smId){
        boolean flag = false;
        SQLiteDatabase db = dfHelper.getReadableDatabase();
        Cursor cursor = db.query("set_meal",null,"sm_id=?",new String[]{smId+""},null,null,null);
        if(cursor.getCount() != 0) {
            flag = true;
        }
        cursor.close();
        db.close();
        return flag;
    }

    /**
     * 添加
     * @param setMeal
     */
    public long insert(SetMeal setMeal) {
        SQLiteDatabase db = dfHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("sm_id",setMeal.getSmId());
        values.put("sm_name",setMeal.getSmName());
        values.put("current_prices",setMeal.getCurrentPrices());
        values.put("cost_price",setMeal.getCostPrice());
        values.put("recharge_month",setMeal.getRechargeMonth());
        values.put("detail",setMeal.getDetail());
        long id = db.insert("set_meal",null,values);
        db.close();
        return id;
    }
}
