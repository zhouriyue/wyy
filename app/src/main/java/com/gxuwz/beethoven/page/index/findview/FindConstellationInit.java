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
import com.gxuwz.beethoven.adapter.find.RecommendLikeAdapter;
import com.gxuwz.beethoven.adapter.find.RecommendedListItem;
import com.gxuwz.beethoven.adapter.find.TelecastItem;
import com.gxuwz.beethoven.util.WindowPixels;

import java.util.ArrayList;
import java.util.List;

/**
 * “星座推荐”模块
 */
public class FindConstellationInit {
    View FindView;
    List<RecommendedListItem> constellationList;
    GridView constellationGridView;

    public FindConstellationInit(View findView) {
        FindView = findView;
    }

    public void init(LayoutInflater layoutInflater, WindowManager windowManager, Context context){
        findByIdAndNew();
        setData();
        setGridView(layoutInflater,windowManager,context);
    }
    /**设置GirdView参数，绑定数据*/
    private void setGridView(LayoutInflater layoutInflater, WindowManager windowManager, Context context) {
        int size = constellationList.size();
        WindowPixels windowPixels = new WindowPixels(windowManager);
        float density = windowPixels.getDensity();
        int width = (int) (300*density);
        int length = (int) (200*density);
        int gridviewWidth = 20;//水平间距
        int itemWidth = width;//列表项宽
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                (int) ((size)*width+40*density), length);
        constellationGridView.setLayoutParams(params); // 设置GirdView布局参数,横向布局的关键
        constellationGridView.setColumnWidth(itemWidth); // 设置列表项宽
        constellationGridView.setHorizontalSpacing(gridviewWidth); // 设置列表项水平间距
        constellationGridView.setStretchMode(GridView.NO_STRETCH);
        constellationGridView.setSelector(new ColorDrawable(Color.TRANSPARENT));
        constellationGridView.setNumColumns(size); // 设置列数量=列表集合数

        RecommendLikeAdapter adapter = new RecommendLikeAdapter(context,
                constellationList,layoutInflater);
        constellationGridView.setAdapter(adapter);
    }

    /**设置数据*/
    private void setData() {
        RecommendedListItem item = new RecommendedListItem();
        item.setDiagonal("youth");
        item.setSongName("花样年华");
        item.setSinger("周深/李维");
        item.setDetail("心中排名 邓丽君王菲 周深 好妹妹");
        constellationList.add(item);

        item = new RecommendedListItem();
        item.setDiagonal("youth");
        item.setSongName("花样年华");
        item.setSinger("周深/李维");
        item.setDetail("心中排名 邓丽君王菲 周深 好妹妹");
        constellationList.add(item);

        item = new RecommendedListItem();
        item.setDiagonal("youth");
        item.setSongName("花样年华");
        item.setSinger("周深/李维");
        item.setDetail("心中排名 邓丽君王菲 周深 好妹妹");
        constellationList.add(item);

        item = new RecommendedListItem();
        item.setDiagonal("youth");
        item.setSongName("花样年华");
        item.setSinger("周深/李维");
        item.setDetail("心中排名 邓丽君王菲 周深 好妹妹");
        constellationList.add(item);

        item = new RecommendedListItem();
        item.setDiagonal("youth");
        item.setSongName("花样年华");
        item.setSinger("周深/李维");
        item.setDetail("心中排名 邓丽君王菲 周深 好妹妹");
        constellationList.add(item);

        item = new RecommendedListItem();
        item.setDiagonal("youth");
        item.setSongName("花样年华");
        item.setSinger("周深/李维");
        item.setDetail("心中排名 邓丽君王菲 周深 好妹妹");
        constellationList.add(item);

        item = new RecommendedListItem();
        item.setDiagonal("youth");
        item.setSongName("花样年华");
        item.setSinger("周深/李维");
        item.setDetail("心中排名 邓丽君王菲 周深 好妹妹");
        constellationList.add(item);

        item = new RecommendedListItem();
        item.setDiagonal("youth");
        item.setSongName("花样年华");
        item.setSinger("周深/李维");
        item.setDetail("心中排名 邓丽君王菲 周深 好妹妹");
        constellationList.add(item);

        item = new RecommendedListItem();
        item.setDiagonal("youth");
        item.setSongName("花样年华");
        item.setSinger("周深/李维");
        item.setDetail("心中排名 邓丽君王菲 周深 好妹妹");
        constellationList.add(item);

        item = new RecommendedListItem();
        item.setDiagonal("youth");
        item.setSongName("花样年华");
        item.setSinger("周深/李维");
        item.setDetail("心中排名 邓丽君王菲 周深 好妹妹");
        constellationList.add(item);

        item = new RecommendedListItem();
        item.setDiagonal("youth");
        item.setSongName("花样年华");
        item.setSinger("周深/李维");
        item.setDetail("心中排名 邓丽君王菲 周深 好妹妹");
        constellationList.add(item);

        item = new RecommendedListItem();
        item.setDiagonal("youth");
        item.setSongName("花样年华");
        item.setSinger("周深/李维");
        item.setDetail("心中排名 邓丽君王菲 周深 好妹妹");
        constellationList.add(item);
    }

    public void findByIdAndNew(){
        constellationGridView = FindView.findViewById(R.id.constellation_gridview);
        constellationList = new ArrayList<RecommendedListItem>();
    }
}
