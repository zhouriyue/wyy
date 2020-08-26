package com.gxuwz.beethoven.page.index.myview;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.GridView;
import android.widget.LinearLayout;

import androidx.recyclerview.widget.RecyclerView;

import com.gxuwz.beethoven.R;
import com.gxuwz.beethoven.adapter.find.LikeAfternoonAdapter;
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
    GridView musicAppGV;
    List<MusicApp> musicAppList;

    public MusicAppInit(View myView) {
        MyView = myView;
    }

    public void init(WindowManager windowManager, Context context){
        findByIdAndNew();
        setData();
        setGridView(windowManager,context);
    }

    public void setData(){
        MusicApp musicApp = new MusicApp();
        musicApp.setDiagonal("zhoushen1");
        musicApp.setIcon("icon_love");
        musicApp.setTypeIcon("play_whick_1");
        musicApp.setTitle("我喜欢的音乐");
        musicApp.setType("心动模式");
        musicApp.setTypeColor("#4Dffffff");
        musicAppList.add(musicApp);

        musicApp = new MusicApp();
        musicApp.setDiagonal("zhoushen1");
        musicApp.setIcon("icon_love");
        musicApp.setTypeIcon("play_whick_1");
        musicApp.setTitle("我喜欢的音乐");
        musicApp.setType("心动模式");
        musicApp.setTypeColor("#4Dffffff");
        musicAppList.add(musicApp);

        musicApp = new MusicApp();
        musicApp.setDiagonal("zhoushen1");
        musicApp.setIcon("icon_love");
        musicApp.setTypeIcon("play_whick_1");
        musicApp.setTitle("我喜欢的音乐");
        musicApp.setType("心动模式");
        musicApp.setTypeColor("#4Dffffff");
        musicAppList.add(musicApp);

        musicApp = new MusicApp();
        musicApp.setDiagonal("zhoushen1");
        musicApp.setIcon("icon_love");
        musicApp.setTypeIcon("play_whick_1");
        musicApp.setTitle("我喜欢的音乐");
        musicApp.setType("心动模式");
        musicApp.setTypeColor("#4Dffffff");
        musicAppList.add(musicApp);
    }

    /**设置GirdView参数，绑定数据*/
    private void setGridView(WindowManager windowManager, Context context) {
        int size = musicAppList.size();
        WindowPixels windowPixels = new WindowPixels(windowManager);
        float density = windowPixels.getDensity();
        int width = (int) (100*density);
        int length = (int) (125*density);
        DisplayMetrics dm = new DisplayMetrics();
        windowManager.getDefaultDisplay().getMetrics(dm);
        int gridviewWidth = (int) (8*density);//水平间距
        int itemWidth = width;//列表项宽

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                (int) (size*width+30*density), length);

        musicAppGV.setLayoutParams(params); // 设置GirdView布局参数,横向布局的关键
        musicAppGV.setColumnWidth(itemWidth); // 设置列表项宽
        musicAppGV.setHorizontalSpacing(gridviewWidth); // 设置列表项水平间距
        musicAppGV.setStretchMode(GridView.NO_STRETCH);
        musicAppGV.setSelector(new ColorDrawable(Color.TRANSPARENT));
        musicAppGV.setNumColumns(size); // 设置列数量=列表集合数

        MusicAppAdapter adapter = new MusicAppAdapter(context,
                musicAppList,windowManager);
        musicAppGV.setAdapter(adapter);
    }

    public void findByIdAndNew(){
        musicAppGV = MyView.findViewById(R.id.music_app_gv);
        musicAppList = new ArrayList<MusicApp>();
    }
}
