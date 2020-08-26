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
import android.widget.TextView;
import com.gxuwz.beethoven.R;
import com.gxuwz.beethoven.adapter.find.NewDiscAdapter;
import com.gxuwz.beethoven.adapter.find.NewDiscItem;
import com.gxuwz.beethoven.adapter.find.RecommendLikeAdapter;
import com.gxuwz.beethoven.adapter.find.RecommendedListItem;
import com.gxuwz.beethoven.listener.NewSongDiscListener;
import com.gxuwz.beethoven.util.WindowPixels;

import java.util.ArrayList;
import java.util.List;

/**
 * “新片和新碟”初始化模块
 */
public class FindNewSongDisc {
    /**
     * “新歌”和“新碟”按钮
     */
    TextView newSongTV,newDiscTV;

    View FindView;
    List<RecommendedListItem> newSongList;
    List<NewDiscItem> newDiscList;
    GridView newSongGridView;
    GridView newDiscGridView;

    public FindNewSongDisc(View findView) {
        FindView = findView;
    }

    public void init(LayoutInflater layoutInflater, WindowManager windowManager, Context context){
        findByIdAndNew();
        setData();
        setGridView(layoutInflater,windowManager,context);
    }

    public void setData(){
        setSongData();
        setNewDiscData();
    }

    public void setNewDiscData(){
        NewDiscItem newDiscItem = new NewDiscItem();
        newDiscItem.setDiagonal("youth");
        newDiscItem.setDiscName("从未想过");
        newDiscItem.setSubtitle("");
        newDiscItem.setSinger("-周深");
        newDiscItem.setDetail("黄明昊首张专辑《18》独家上线");
        newDiscList.add(newDiscItem);

        newDiscItem = new NewDiscItem();
        newDiscItem.setDiagonal("youth");
        newDiscItem.setDiscName("从未想过");
        newDiscItem.setSubtitle("");
        newDiscItem.setSinger("-周深");
        newDiscItem.setDetail("黄明昊首张专辑《18》独家上线");
        newDiscList.add(newDiscItem);

        newDiscItem = new NewDiscItem();
        newDiscItem.setDiagonal("youth");
        newDiscItem.setDiscName("从未想过");
        newDiscItem.setSubtitle("");
        newDiscItem.setSinger("-周深");
        newDiscItem.setDetail("黄明昊首张专辑《18》独家上线");
        newDiscList.add(newDiscItem);

        newDiscItem = new NewDiscItem();
        newDiscItem.setDiagonal("youth");
        newDiscItem.setDiscName("从未想过");
        newDiscItem.setSubtitle("");
        newDiscItem.setSinger("-周深");
        newDiscItem.setDetail("黄明昊首张专辑《18》独家上线");
        newDiscList.add(newDiscItem);

        newDiscItem = new NewDiscItem();
        newDiscItem.setDiagonal("youth");
        newDiscItem.setDiscName("从未想过");
        newDiscItem.setSubtitle("");
        newDiscItem.setSinger("-周深");
        newDiscItem.setDetail("黄明昊首张专辑《18》独家上线");
        newDiscList.add(newDiscItem);

        newDiscItem = new NewDiscItem();
        newDiscItem.setDiagonal("youth");
        newDiscItem.setDiscName("从未想过");
        newDiscItem.setSubtitle("");
        newDiscItem.setSinger("-周深");
        newDiscItem.setDetail("黄明昊首张专辑《18》独家上线");
        newDiscList.add(newDiscItem);

        newDiscItem = new NewDiscItem();
        newDiscItem.setDiagonal("youth");
        newDiscItem.setDiscName("从未想过");
        newDiscItem.setSubtitle("");
        newDiscItem.setSinger("-周深");
        newDiscItem.setDetail("黄明昊首张专辑《18》独家上线");
        newDiscList.add(newDiscItem);

        newDiscItem = new NewDiscItem();
        newDiscItem.setDiagonal("youth");
        newDiscItem.setDiscName("从未想过");
        newDiscItem.setSubtitle("");
        newDiscItem.setSinger("-周深");
        newDiscItem.setDetail("黄明昊首张专辑《18》独家上线");
        newDiscList.add(newDiscItem);
    }

    public void setSongData(){
        RecommendedListItem item = new RecommendedListItem();
        item.setDiagonal("youth");
        item.setSongName("花样年华");
        item.setSinger("周深/李维");
        item.setDetail("心中排名 邓丽君王菲 周深 好妹妹");
        newSongList.add(item);

        item = new RecommendedListItem();
        item.setDiagonal("youth");
        item.setSongName("花样年华");
        item.setSinger("周深/李维");
        item.setDetail("心中排名 邓丽君王菲 周深 好妹妹");
        newSongList.add(item);

        item = new RecommendedListItem();
        item.setDiagonal("youth");
        item.setSongName("花样年华");
        item.setSinger("周深/李维");
        item.setDetail("心中排名 邓丽君王菲 周深 好妹妹");
        newSongList.add(item);

        item = new RecommendedListItem();
        item.setDiagonal("youth");
        item.setSongName("花样年华");
        item.setSinger("周深/李维");
        item.setDetail("心中排名 邓丽君王菲 周深 好妹妹");
        newSongList.add(item);

        item = new RecommendedListItem();
        item.setDiagonal("youth");
        item.setSongName("花样年华");
        item.setSinger("周深/李维");
        item.setDetail("心中排名 邓丽君王菲 周深 好妹妹");
        newSongList.add(item);

        item = new RecommendedListItem();
        item.setDiagonal("youth");
        item.setSongName("花样年华");
        item.setSinger("周深/李维");
        item.setDetail("心中排名 邓丽君王菲 周深 好妹妹");
        newSongList.add(item);

        item = new RecommendedListItem();
        item.setDiagonal("youth");
        item.setSongName("花样年华");
        item.setSinger("周深/李维");
        item.setDetail("心中排名 邓丽君王菲 周深 好妹妹");
        newSongList.add(item);

        item = new RecommendedListItem();
        item.setDiagonal("youth");
        item.setSongName("花样年华");
        item.setSinger("周深/李维");
        item.setDetail("心中排名 邓丽君王菲 周深 好妹妹");
        newSongList.add(item);
    }

    private void setGridView(LayoutInflater layoutInflater, WindowManager windowManager, Context context){
        setSongGrid(layoutInflater,windowManager,context);
        setDiscGrid(layoutInflater,windowManager,context);
    }

    /**设置GirdView参数，绑定数据*/
    private void setSongGrid(LayoutInflater layoutInflater, WindowManager windowManager, Context context) {
        int size = newSongList.size();
        WindowPixels windowPixels = new WindowPixels(windowManager);
        float density = windowPixels.getDensity();
        int width = (int) (300*density);
        int height = (int) (200*density);
        int gridviewWidth = 20;//水平间距
        int itemWidth = width;//列表项宽

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                (int) (size*width+40*density), height);

        newSongGridView.setLayoutParams(params); // 设置GirdView布局参数,横向布局的关键
        newSongGridView.setColumnWidth(itemWidth); // 设置列表项宽
        newSongGridView.setHorizontalSpacing(gridviewWidth); // 设置列表项水平间距
        newSongGridView.setStretchMode(GridView.NO_STRETCH);
        newSongGridView.setSelector(new ColorDrawable(Color.TRANSPARENT));
        newSongGridView.setNumColumns(size); // 设置列数量=列表集合数

        RecommendLikeAdapter adapter = new RecommendLikeAdapter(context,
                newSongList,layoutInflater);
        newSongGridView.setAdapter(adapter);
    }

    /**设置GirdView参数，绑定数据*/
    private void setDiscGrid(LayoutInflater layoutInflater, WindowManager windowManager, Context context) {
        int size = newDiscList.size();
        int width = 900;
        int length = 600;
        DisplayMetrics dm = new DisplayMetrics();
        windowManager.getDefaultDisplay().getMetrics(dm);
        float density = dm.density;
        int gridviewWidth = 20;//水平间距
        int itemWidth = width;//列表项宽

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                (size)*width+width/2, length);

        newDiscGridView.setLayoutParams(params); // 设置GirdView布局参数,横向布局的关键
        newDiscGridView.setColumnWidth(itemWidth); // 设置列表项宽
        newDiscGridView.setHorizontalSpacing(gridviewWidth); // 设置列表项水平间距
        newDiscGridView.setStretchMode(GridView.NO_STRETCH);
        newDiscGridView.setSelector(new ColorDrawable(Color.TRANSPARENT));
        newDiscGridView.setNumColumns(size); // 设置列数量=列表集合数

        NewDiscAdapter adapter = new NewDiscAdapter(context,
                newDiscList,layoutInflater);
        newDiscGridView.setAdapter(adapter);
    }

    public void findByIdAndNew(){
        newSongGridView = FindView.findViewById(R.id.new_song_grid_view);
        newDiscGridView = FindView.findViewById(R.id.new_disc_grid_view);
        newSongTV = FindView.findViewById(R.id.new_song_tv);
        newDiscTV = FindView.findViewById(R.id.new_disc_tv);
        newSongList = new ArrayList<RecommendedListItem>();
        newDiscList = new ArrayList<NewDiscItem>();
        NewSongDiscListener newSongDiscListener = new NewSongDiscListener(newSongTV,newDiscTV,newSongGridView,newDiscGridView);

        newDiscGridView.setVisibility(View.GONE);

        newSongTV.setOnClickListener(newSongDiscListener);
        newDiscTV.setOnClickListener(newSongDiscListener);
    }
}
