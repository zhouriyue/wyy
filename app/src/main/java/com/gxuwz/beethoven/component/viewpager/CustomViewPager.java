package com.gxuwz.beethoven.component.viewpager;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewpager.widget.ViewPager;

public class CustomViewPager extends ViewPager {

    public CustomViewPager(@NonNull Context context) {
        super(context);
    }

    public CustomViewPager(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onInterceptHoverEvent(MotionEvent event) {
        float preX = 0;
        boolean res = super.onInterceptTouchEvent(event);
        if(event.getAction() == MotionEvent.ACTION_DOWN) {
            preX = event.getX();
        } else {
            if( Math.abs(event.getX() - preX)> 4 ) {
                return true;
            } else {
                preX = event.getX();
            }
        }
        return res;
    }
}
