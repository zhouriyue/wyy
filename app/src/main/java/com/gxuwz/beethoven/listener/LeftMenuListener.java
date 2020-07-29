package com.gxuwz.beethoven.listener;

import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;

import com.gxuwz.beethoven.page.index.MainMenuPopupWindow;

public class LeftMenuListener implements View.OnClickListener{

    MainMenuPopupWindow mainMenuPopupWindow;

    public LeftMenuListener(WindowManager wm, LayoutInflater layoutInflater) {
        mainMenuPopupWindow = new MainMenuPopupWindow(wm,layoutInflater);
    }

    @Override
    public void onClick(View view) {
        mainMenuPopupWindow.showAtLocation(view);
    }




}
