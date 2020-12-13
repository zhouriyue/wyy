package com.gxuwz.beethoven.page.fragment.my.songlist;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;

import com.gxuwz.beethoven.page.fragment.search.SaveToSonglistPw;
import com.gxuwz.beethoven.util.WindowPixels;

public class LoadDownPopuWindow {

    Context context;

    public PopupWindow popupWindow;

    View singListView;

    public LoadDownPopuWindow(Context context, View singListView) {
        this.context = context;
        this.singListView = singListView;
        int scopeY1 = 0;
        int scopeY2 = (int) (WindowPixels.HEIGHT-400);
        int height = (int) (WindowPixels.HEIGHT);
        popupWindow = new PopupWindow(singListView, ViewGroup.LayoutParams.MATCH_PARENT,height,true);
        popupWindow.setBackgroundDrawable(new ColorDrawable(0));
        this.singListView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(event.getY()>scopeY1&&event.getY()<scopeY2){
                    popupWindow.dismiss();
                }
                return false;
            }
        });
    }

    public void showAtLocation(View view){
        showPopupWindow();
        popupWindow.showAtLocation(view, Gravity.BOTTOM,0,0);
    }

    public void showPopupWindow(){
        if(popupWindow!=null) {
            popupWindow.dismiss();
        }
    }

    public PopupWindow getPopupWindow() {
        return popupWindow;
    }

    public void setPopupWindow(PopupWindow popupWindow) {
        this.popupWindow = popupWindow;
    }

    public View getSingListView() {
        return singListView;
    }

    public void setSingListView(View singListView) {
        this.singListView = singListView;
    }
}
