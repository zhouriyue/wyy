package com.gxuwz.beethoven.page.index;

import android.graphics.drawable.ColorDrawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.PopupWindow;

import com.gxuwz.beethoven.R;

/**
 * 主页头部菜单
 */
public class MainMenuPopupWindow {
    WindowManager wm;
    LayoutInflater layoutInflater;
    PopupWindow popupWindow;

    public MainMenuPopupWindow(WindowManager wm, LayoutInflater layoutInflater) {
        this.wm = wm;
        this.layoutInflater = layoutInflater;
    }

    public void showAtLocation(View view){
        getPopupWindow();
        popupWindow.showAtLocation(view, Gravity.LEFT,0,0);
    }

    protected  void  initPopupWindow(){
        int width = (int) (wm.getDefaultDisplay().getWidth()*0.8);
        final View popipWindow_view = layoutInflater.inflate(R.layout.activity_left_menu,null,false);
        popupWindow = new PopupWindow(popipWindow_view,width, ViewGroup.LayoutParams.MATCH_PARENT,true);
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
