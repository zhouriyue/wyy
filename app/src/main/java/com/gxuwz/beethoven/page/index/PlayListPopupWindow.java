package com.gxuwz.beethoven.page.index;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;

import com.gxuwz.beethoven.util.WindowPixels;

public class PlayListPopupWindow {

    Context context;

    PopupWindow popupWindow;

    View playListView;

    public PlayListPopupWindow(Context context,View plMainView) {
        this.context = context;
        this.playListView = plMainView;
    }

    public void showAtLocation(View view){
        showPopupWindow();
        popupWindow.showAtLocation(view, Gravity.BOTTOM,0,0);
    }

    public void initPopupWindow(){
        int height = (int) (WindowPixels.HEIGHT);
        popupWindow = new PopupWindow(playListView,ViewGroup.LayoutParams.MATCH_PARENT,height,true);
        popupWindow.setBackgroundDrawable(new ColorDrawable(0));
    }

    private  void  showPopupWindow() {
        if (null != popupWindow) {
            popupWindow.dismiss();
            return;
        } else {
            initPopupWindow();
        }
    }

    public PopupWindow getPopupWindow() {
        return popupWindow;
    }

    public void setPopupWindow(PopupWindow popupWindow) {
        this.popupWindow = popupWindow;
    }

    public View getPlayListView() {
        return playListView;
    }

    public void setPlayListView(View playListView) {
        this.playListView = playListView;
    }
}
