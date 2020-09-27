package com.gxuwz.beethoven.component.recycler;

import android.content.Context;
import android.util.AttributeSet;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import java.lang.reflect.Field;

public class RecyclerViewCustom extends RecyclerView {
    public RecyclerViewCustom(@NonNull Context context) {
        super(context);
    }

    public RecyclerViewCustom(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public RecyclerViewCustom(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    /**
     * 改变Recycler的滑动速度
     * @param recyclerView
     * @param velocity      //滑动速度默认是8000dp
     */
    public static void setMaxFlingVelocity(RecyclerView recyclerView, int velocity){
        try{
            Field field = recyclerView.getClass().getDeclaredField("mMaxFlingVelocity");
            field.setAccessible(true);
            field.set(recyclerView, velocity);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
