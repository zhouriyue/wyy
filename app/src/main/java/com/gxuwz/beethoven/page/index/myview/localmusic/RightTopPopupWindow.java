package com.gxuwz.beethoven.page.index.myview.localmusic;

import android.graphics.drawable.ColorDrawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.PopupWindow;

import com.gxuwz.beethoven.R;

public class RightTopPopupWindow {
    WindowManager wm;
    LayoutInflater layoutInflater;
    PopupWindow popupWindow;
    int width;
    int height;
    public RightTopPopupWindow(WindowManager wm, LayoutInflater layoutInflater) {
        this.wm = wm;
        this.layoutInflater = layoutInflater;
    }

    public void showAtLocation(View view){
        getPopupWindow();
        popupWindow.showAtLocation(view, Gravity.RIGHT,0,-((wm.getDefaultDisplay().getHeight()-height)/2));
    }

    protected  void  initPopupWindow(){
        width = (int) (wm.getDefaultDisplay().getWidth()*0.5);
        height = 600;
        final View popipWindow_view = layoutInflater.inflate(R.layout.local_music_pop,null,false);
        popupWindow = new PopupWindow(popipWindow_view,width, height,true);
        popupWindow.setBackgroundDrawable(new ColorDrawable(0));
    }
    private  void  getPopupWindow() {
        if (null != popupWindow) {
            popupWindow.dismiss();
            return;
        } else {
            initPopupWindow();
        }
    }
}
