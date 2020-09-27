package com.gxuwz.beethoven.page.index.playlistview;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import androidx.viewpager.widget.ViewPager;
import com.gxuwz.beethoven.R;
import com.gxuwz.beethoven.adapter.PagerCustomAdapter;
import com.gxuwz.beethoven.page.index.PlayListPopupWindow;

import java.util.ArrayList;

public class PlayListView {

    Context context;

    View playListView;
    ViewPager viewPager;
    PagerCustomAdapter pagerCustomAdapter;
    ArrayList<View> viewList;

    CurrentPlayView currentPlayView;
    /**
     *  弹框模块
     */
    PlayListPopupWindow playListPopupWindow;
    ImageView clonePopwindow; //关闭弹框
    ImageView playListIV;

    public PlayListView(Context context,ImageView playListIV) {
        this.context = context;
        this.playListIV = playListIV;
        this.playListView = LayoutInflater.from(context).inflate(R.layout.play_list,null,false);
        this.viewList = new ArrayList<View>();
        this.viewList.add(LayoutInflater.from(context).inflate(R.layout.item_last,null));
        this.viewList.add(LayoutInflater.from(context).inflate(R.layout.item_play_list,null));
        init();
    }

    public void init(){
        viewPager = playListView.findViewById(R.id.play_list_rv);
        pagerCustomAdapter = new PagerCustomAdapter(viewList);
        initPager();
        initPopuWindow();
        /**
         * “当前播放”页初始化
         */
        currentPlayView = new CurrentPlayView(context,viewList.get(1));
    }

    /**
     * 弹框模块
     */
    public void initPopuWindow(){
        clonePopwindow = playListView.findViewById(R.id.clone_popwindow);
        playListPopupWindow = new PlayListPopupWindow(context,playListView);
        playListIV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playListPopupWindow.showAtLocation(v);
            }
        });
        clonePopwindow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playListPopupWindow.getPopupWindow().dismiss();
            }
        });
    }

    public void initPager(){
        viewPager.setAdapter(pagerCustomAdapter);
        viewPager.setCurrentItem(1);
    }
}
