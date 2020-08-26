package com.gxuwz.beethoven.page.index.findview;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.GridView;
import android.widget.LinearLayout;

import com.gxuwz.beethoven.R;
import com.gxuwz.beethoven.adapter.find.WindVaneAdapter;
import com.gxuwz.beethoven.model.entity.find.HotVane;
import com.gxuwz.beethoven.model.entity.Music;
import com.gxuwz.beethoven.model.entity.find.WindMusic;
import com.gxuwz.beethoven.util.WindowPixels;

import java.util.ArrayList;
import java.util.List;

/**
 * “热歌风向标”模块
 */
public class FindHotVane {
    View FindView;
    List<HotVane> hotWindList;
    GridView windMusicsGrid;
    public FindHotVane(View findView) {
        FindView = findView;
    }

    public void init(LayoutInflater layoutInflater, WindowManager windowManager, Context context){
        findByIdAndNew();
        setData();
        setGridView(layoutInflater,windowManager,context);
    }

    /**设置GirdView参数，绑定数据*/
    private void setGridView(LayoutInflater layoutInflater, WindowManager windowManager, Context context) {
        int size = hotWindList.size();
        WindowPixels windowPixels = new WindowPixels(windowManager);
        float density = windowPixels.getDensity();
        int width = (int) (300*density);
        int height = (int) (210*density);
        DisplayMetrics dm = new DisplayMetrics();
        windowManager.getDefaultDisplay().getMetrics(dm);
        int gridviewWidth = 20;//水平间距
        int itemWidth = width;//列表项宽

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                (int) ((size)*width+40*density), height);

        windMusicsGrid.setLayoutParams(params); // 设置GirdView布局参数,横向布局的关键
        windMusicsGrid.setColumnWidth(itemWidth); // 设置列表项宽
        windMusicsGrid.setHorizontalSpacing(gridviewWidth); // 设置列表项水平间距
        windMusicsGrid.setStretchMode(GridView.NO_STRETCH);
        windMusicsGrid.setSelector(new ColorDrawable(Color.TRANSPARENT));
        windMusicsGrid.setNumColumns(size); // 设置列数量=列表集合数

        WindVaneAdapter adapter = new WindVaneAdapter(context,
                hotWindList,layoutInflater);
        windMusicsGrid.setAdapter(adapter);
    }

    /**设置数据*/
    private void setData() {
        /**
         * 第一个风向标
         */
        HotVane hotVane = new HotVane();
        hotVane.setClassType("硬地原创音乐榜");
        hotVane.setColor("#000000");
        hotVane.setDiagonal("wind_diagonal");

        List<WindMusic> windMusicList = new ArrayList<WindMusic>();
        //第一首歌
        WindMusic windMusic = new WindMusic();
        Music music = new Music();
        music.setMusicName("天外来物");
        music.setSinger("-薛之谦");
        music.setSongPicUrl("youth");
        windMusic.setMusic(music);
        windMusic.setStatus("新");
        windMusicList.add(windMusic);
        //第二首歌
        windMusic = new WindMusic();
        music = new Music();
        music.setMusicName("天外来物");
        music.setSinger("-薛之谦");
        music.setSongPicUrl("youth");
        windMusic.setMusic(music);
        windMusic.setStatus("新");
        windMusicList.add(windMusic);
        //第三首歌
        windMusic = new WindMusic();
        music = new Music();
        music.setMusicName("天外来物");
        music.setSinger("-薛之谦");
        music.setSongPicUrl("youth");
        windMusic.setMusic(music);
        windMusic.setStatus("新");
        windMusicList.add(windMusic);
        hotVane.setWindMusic(windMusicList);
        hotWindList.add(hotVane);

        /**
         * 第二个风向标
         */
        hotVane = new HotVane();
        hotVane.setClassType("硬地原创音乐榜");
        hotVane.setColor("#000000");
        hotVane.setDiagonal("wind_diagonal");

        windMusicList = new ArrayList<WindMusic>();
        //第一首歌
        windMusic = new WindMusic();
        music = new Music();
        music.setMusicName("天外来物");
        music.setSinger("-薛之谦");
        music.setSongPicUrl("youth");
        windMusic.setMusic(music);
        windMusic.setStatus("新");
        windMusicList.add(windMusic);
        //第二首歌
        windMusic = new WindMusic();
        music = new Music();
        music.setMusicName("天外来物");
        music.setSinger("-薛之谦");
        music.setSongPicUrl("youth");
        windMusic.setMusic(music);
        windMusic.setStatus("新");
        windMusicList.add(windMusic);
        //第三首歌
        windMusic = new WindMusic();
        music = new Music();
        music.setMusicName("天外来物");
        music.setSinger("-薛之谦");
        music.setSongPicUrl("youth");
        windMusic.setMusic(music);
        windMusic.setStatus("新");
        windMusicList.add(windMusic);
        hotVane.setWindMusic(windMusicList);
        hotWindList.add(hotVane);

        /**
         * 第三个风向标
         */
        hotVane = new HotVane();
        hotVane.setClassType("硬地原创音乐榜");
        hotVane.setColor("#000000");
        hotVane.setDiagonal("wind_diagonal");

        windMusicList = new ArrayList<WindMusic>();
        //第一首歌
        windMusic = new WindMusic();
        music = new Music();
        music.setMusicName("天外来物");
        music.setSinger("-薛之谦");
        music.setSongPicUrl("youth");
        windMusic.setMusic(music);
        windMusic.setStatus("新");
        windMusicList.add(windMusic);
        //第二首歌
        windMusic = new WindMusic();
        music = new Music();
        music.setMusicName("天外来物");
        music.setSinger("-薛之谦");
        music.setSongPicUrl("youth");
        windMusic.setMusic(music);
        windMusic.setStatus("新");
        windMusicList.add(windMusic);
        //第三首歌
        windMusic = new WindMusic();
        music = new Music();
        music.setMusicName("天外来物");
        music.setSinger("-薛之谦");
        music.setSongPicUrl("youth");
        windMusic.setMusic(music);
        windMusic.setStatus("新");
        windMusicList.add(windMusic);
        hotVane.setWindMusic(windMusicList);
        hotWindList.add(hotVane);
    }

    public void findByIdAndNew(){
        windMusicsGrid = FindView.findViewById(R.id.wind_vane_gridview);
        hotWindList = new ArrayList<HotVane>();
    }
}
