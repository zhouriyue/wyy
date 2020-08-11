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

import java.util.ArrayList;
import java.util.List;

public class FindRecommendLikeInit {
    View FindView;
    List<RecommendedListItem> recommendedList;
    GridView recommendedListGridView;

    public FindRecommendLikeInit(View findView) {
        FindView = findView;
    }

    public void init(LayoutInflater layoutInflater, WindowManager windowManager, Context context){
        findByIdAndNew();
        setData();
        setGridView(layoutInflater,windowManager,context);
    }
    /**设置GirdView参数，绑定数据*/
    private void setGridView(LayoutInflater layoutInflater, WindowManager windowManager, Context context) {
        int size = recommendedList.size();
        int width = 900;
        int length = 600;
        DisplayMetrics dm = new DisplayMetrics();
        windowManager.getDefaultDisplay().getMetrics(dm);
        float density = dm.density;
        int gridviewWidth = 20;//水平间距
        int itemWidth = width;//列表项宽

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                (size)*width+width/2, length);

        recommendedListGridView.setLayoutParams(params); // 设置GirdView布局参数,横向布局的关键
        recommendedListGridView.setColumnWidth(itemWidth); // 设置列表项宽
        recommendedListGridView.setHorizontalSpacing(gridviewWidth); // 设置列表项水平间距
        recommendedListGridView.setStretchMode(GridView.NO_STRETCH);
        recommendedListGridView.setSelector(new ColorDrawable(Color.TRANSPARENT));
        recommendedListGridView.setNumColumns(size); // 设置列数量=列表集合数

        RecommendLikeAdapter adapter = new RecommendLikeAdapter(context,
                recommendedList,layoutInflater);
        recommendedListGridView.setAdapter(adapter);
    }

    /**设置数据*/
    private void setData() {
        RecommendedListItem item = new RecommendedListItem();
        item.setDiagonal("youth");
        item.setSongName("花样年华");
        item.setSinger("周深/李维");
        item.setDetail("心中排名 邓丽君王菲 周深 好妹妹");
        recommendedList.add(item);

        item = new RecommendedListItem();
        item.setDiagonal("youth");
        item.setSongName("花样年华");
        item.setSinger("周深/李维");
        item.setDetail("心中排名 邓丽君王菲 周深 好妹妹");
        recommendedList.add(item);

        item = new RecommendedListItem();
        item.setDiagonal("youth");
        item.setSongName("花样年华");
        item.setSinger("周深/李维");
        item.setDetail("心中排名 邓丽君王菲 周深 好妹妹");
        recommendedList.add(item);

        item = new RecommendedListItem();
        item.setDiagonal("youth");
        item.setSongName("花样年华");
        item.setSinger("周深/李维");
        item.setDetail("心中排名 邓丽君王菲 周深 好妹妹");
        recommendedList.add(item);

        item = new RecommendedListItem();
        item.setDiagonal("youth");
        item.setSongName("花样年华");
        item.setSinger("周深/李维");
        item.setDetail("心中排名 邓丽君王菲 周深 好妹妹");
        recommendedList.add(item);

        item = new RecommendedListItem();
        item.setDiagonal("youth");
        item.setSongName("花样年华");
        item.setSinger("周深/李维");
        item.setDetail("心中排名 邓丽君王菲 周深 好妹妹");
        recommendedList.add(item);

        item = new RecommendedListItem();
        item.setDiagonal("youth");
        item.setSongName("花样年华");
        item.setSinger("周深/李维");
        item.setDetail("心中排名 邓丽君王菲 周深 好妹妹");
        recommendedList.add(item);

        item = new RecommendedListItem();
        item.setDiagonal("youth");
        item.setSongName("花样年华");
        item.setSinger("周深/李维");
        item.setDetail("心中排名 邓丽君王菲 周深 好妹妹");
        recommendedList.add(item);

        item = new RecommendedListItem();
        item.setDiagonal("youth");
        item.setSongName("花样年华");
        item.setSinger("周深/李维");
        item.setDetail("心中排名 邓丽君王菲 周深 好妹妹");
        recommendedList.add(item);

        item = new RecommendedListItem();
        item.setDiagonal("youth");
        item.setSongName("花样年华");
        item.setSinger("周深/李维");
        item.setDetail("心中排名 邓丽君王菲 周深 好妹妹");
        recommendedList.add(item);

        item = new RecommendedListItem();
        item.setDiagonal("youth");
        item.setSongName("花样年华");
        item.setSinger("周深/李维");
        item.setDetail("心中排名 邓丽君王菲 周深 好妹妹");
        recommendedList.add(item);

        item = new RecommendedListItem();
        item.setDiagonal("youth");
        item.setSongName("花样年华");
        item.setSinger("周深/李维");
        item.setDetail("心中排名 邓丽君王菲 周深 好妹妹");
        recommendedList.add(item);
    }

    public void findByIdAndNew(){
        recommendedListGridView = FindView.findViewById(R.id.recommend_like_gridview);
        recommendedList = new ArrayList<RecommendedListItem>();
    }
}
