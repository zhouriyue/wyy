package com.gxuwz.beethoven.page.fragment.playlistview;

import android.content.Context;
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
    LatePlayView latePlayView;
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
        /**
         * “当前播放”页初始化
         */
        latePlayView = new LatePlayView(context,viewList.get(0));
        currentPlayView = new CurrentPlayView(context,viewList.get(1));
        initPager();
        initPopuWindow();
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
        viewPager.setOnPageChangeListener(new OnPageChangeLer());
        viewPager.setCurrentItem(1);
    }

    class OnPageChangeLer implements ViewPager.OnPageChangeListener {

        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            if(position==1) {
                currentPlayView.init();
            } else {
                latePlayView.init();
            }
        }

        @Override
        public void onPageSelected(int position) {
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    }
}
