package com.gxuwz.beethoven.util;

import android.util.DisplayMetrics;
import android.view.WindowManager;

public class WindowPixels {
    private int width;
    private int height;
    private float density;
    private int densityDpi;
    private int screenWidth;
    private int screenHeight;
    private WindowManager windowManager;
    private DisplayMetrics dm;

    public WindowPixels(WindowManager windowManager) {
        this.windowManager = windowManager;
        this.dm = new DisplayMetrics();
        windowManager.getDefaultDisplay().getMetrics(dm);
        width = dm.widthPixels;         // 屏幕宽度（像素）
        height = dm.heightPixels;       // 屏幕高度（像素）
        density = dm.density;         // 屏幕密度（0.75 / 1.0 / 1.5）
        densityDpi = dm.densityDpi;     // 屏幕密度dpi（120 / 160 / 240）
        // 屏幕宽度算法:屏幕宽度（像素）/屏幕密度
        screenWidth = (int) (width / density);  // 屏幕宽度(dp)
        screenHeight = (int) (height / density);// 屏幕高度(dp)
    }

    public int[] getScreen(){
        int[] screen = new int[2];
        screen[0] = screenWidth;
        screen[1] = screenHeight;
        return screen;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public float getDensity() {
        return density;
    }

    public void setDensity(float density) {
        this.density = density;
    }

    public int getDensityDpi() {
        return densityDpi;
    }

    public void setDensityDpi(int densityDpi) {
        this.densityDpi = densityDpi;
    }

    public int getScreenWidth() {
        return screenWidth;
    }

    public void setScreenWidth(int screenWidth) {
        this.screenWidth = screenWidth;
    }

    public int getScreenHeight() {
        return screenHeight;
    }

    public void setScreenHeight(int screenHeight) {
        this.screenHeight = screenHeight;
    }

    public WindowManager getWindowManager() {
        return windowManager;
    }

    public void setWindowManager(WindowManager windowManager) {
        this.windowManager = windowManager;
    }

    public DisplayMetrics getDm() {
        return dm;
    }

    public void setDm(DisplayMetrics dm) {
        this.dm = dm;
    }
}
