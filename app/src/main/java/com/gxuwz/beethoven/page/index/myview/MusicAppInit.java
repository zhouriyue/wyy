package com.gxuwz.beethoven.page.index.myview;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.WindowManager;
import android.widget.GridView;
import android.widget.LinearLayout;

import com.gxuwz.beethoven.R;
import com.gxuwz.beethoven.adapter.my.MusicAppAdapter;
import com.gxuwz.beethoven.model.entity.musicapp.MusicApp;
import com.gxuwz.beethoven.util.WindowPixels;

import java.util.ArrayList;
import java.util.List;

/**
 * 音乐应用
 */
public class MusicAppInit {
    View MyView;
    List<MusicApp> musicAppList;

    public MusicAppInit(View myView) {
        MyView = myView;
    }

    public void init(WindowManager windowManager, Context context){
        findByIdAndNew();
    }

    public void findByIdAndNew(){
        musicAppList = new ArrayList<MusicApp>();
    }
}
