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
import com.gxuwz.beethoven.adapter.find.LikeAfternoonAdapter;
import com.gxuwz.beethoven.adapter.find.LikeAfternoonItem;
import com.gxuwz.beethoven.util.WindowPixels;

import java.util.ArrayList;
import java.util.List;

public class FindLikeAfternoonInit {
    View FindView;
    List<LikeAfternoonItem> likeAfternoonList;
    GridView likeAfternoonGridView;

    public FindLikeAfternoonInit(View findView) {
        FindView = findView;
    }

    public void init(LayoutInflater layoutInflater, WindowManager windowManager, Context context){
        findByIdAndNew();
        setData();
        setGridView(layoutInflater,windowManager,context);
    }

    /**设置GirdView参数，绑定数据*/
    private void setGridView(LayoutInflater layoutInflater, WindowManager windowManager, Context context) {
        int size = likeAfternoonList.size();
        WindowPixels windowPixels = new WindowPixels(windowManager);
        float density = windowPixels.getDensity();
        int width = (int) (100*density);
        int length = (int) (130*density);
        DisplayMetrics dm = new DisplayMetrics();
        windowManager.getDefaultDisplay().getMetrics(dm);
        int gridviewWidth = 20;//水平间距
        int itemWidth = width;//列表项宽

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                (int) ((size)*itemWidth+40*density), length);

        likeAfternoonGridView.setLayoutParams(params); // 设置GirdView布局参数,横向布局的关键
        likeAfternoonGridView.setColumnWidth(itemWidth); // 设置列表项宽
        likeAfternoonGridView.setHorizontalSpacing(gridviewWidth); // 设置列表项水平间距
        likeAfternoonGridView.setStretchMode(GridView.NO_STRETCH);
        likeAfternoonGridView.setSelector(new ColorDrawable(Color.TRANSPARENT));
        likeAfternoonGridView.setNumColumns(size); // 设置列数量=列表集合数

        LikeAfternoonAdapter adapter = new LikeAfternoonAdapter(context,
                likeAfternoonList,layoutInflater);
        likeAfternoonGridView.setAdapter(adapter);
    }

    /**设置数据*/
    private void setData() {
        LikeAfternoonItem item = new LikeAfternoonItem();
        item.setPlayNumber(1838);
        item.setEnglishTitle("Relax time");
        item.setChinaTitle("洗澡时听得歌");
        item.setTitle("浴池里听歌吹泡泡");
        item.setDiagonal("youth");
        likeAfternoonList.add(item);

        item = new LikeAfternoonItem();
        item.setPlayNumber(1838);
        item.setEnglishTitle("Relax time");
        item.setChinaTitle("洗澡时听得歌");
        item.setTitle("浴池里听歌吹泡泡");
        item.setDiagonal("youth");
        likeAfternoonList.add(item);

        item = new LikeAfternoonItem();
        item.setPlayNumber(1838);
        item.setEnglishTitle("Relax time");
        item.setChinaTitle("洗澡时听得歌");
        item.setTitle("浴池里听歌吹泡泡");
        item.setDiagonal("youth");
        likeAfternoonList.add(item);

        item = new LikeAfternoonItem();
        item.setPlayNumber(1838);
        item.setEnglishTitle("Relax time");
        item.setChinaTitle("洗澡时听得歌");
        item.setTitle("浴池里听歌吹泡泡");
        item.setDiagonal("youth");
        likeAfternoonList.add(item);

        item = new LikeAfternoonItem();
        item.setPlayNumber(1838);
        item.setEnglishTitle("Relax time");
        item.setChinaTitle("洗澡时听得歌");
        item.setTitle("浴池里听歌吹泡泡");
        item.setDiagonal("youth");
        likeAfternoonList.add(item);
    }

    public void findByIdAndNew(){
        likeAfternoonGridView = FindView.findViewById(R.id.like_afternoon_gridview);
        likeAfternoonList = new ArrayList<LikeAfternoonItem>();
    }
}
