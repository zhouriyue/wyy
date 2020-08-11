package com.gxuwz.beethoven.page.index.findview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.gxuwz.beethoven.R;
import com.gxuwz.beethoven.adapter.find.CityItem;
import com.gxuwz.beethoven.adapter.find.FunListAdapter;

import java.util.ArrayList;
import java.util.List;

public class FindMenuGlideInit {
    View FindView;
    List<CityItem> cityList;
    GridView gridView;

    public FindMenuGlideInit(View FindView) {
        this.FindView = FindView;
    }

    public void init(LayoutInflater layoutInflater, WindowManager windowManager, Context context){
        findByIdAndNew();
        setData();
        setGridView(layoutInflater,windowManager,context);
    }

    /**设置GirdView参数，绑定数据*/
    private void setGridView(LayoutInflater layoutInflater,WindowManager windowManager,Context context) {
        int size = cityList.size();
        int width = 190;
        DisplayMetrics dm = new DisplayMetrics();
        windowManager.getDefaultDisplay().getMetrics(dm);
        int gridviewWidth = (int) 20;
        int itemWidth = (int) width;

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                width*size+width/2, LinearLayout.LayoutParams.FILL_PARENT);

        gridView.setLayoutParams(params); // 设置GirdView布局参数,横向布局的关键
        gridView.setColumnWidth(itemWidth); // 设置列表项宽
        gridView.setHorizontalSpacing(gridviewWidth); // 设置列表项水平间距
        gridView.setStretchMode(GridView.NO_STRETCH);
        gridView.setSelector(new ColorDrawable(Color.TRANSPARENT));
        gridView.setNumColumns(size); // 设置列数量=列表集合数

        FunListAdapter adapter = new FunListAdapter(context,
                cityList,layoutInflater);
        gridView.setAdapter(adapter);
    }

    /**设置数据*/
    private void setData() {
        cityList = new ArrayList<CityItem>();
        CityItem item = new CityItem();
        item.setCityName("每日推荐");
        cityList.add(item);
        item = new CityItem();
        item.setCityName("歌单");
        cityList.add(item);
        item = new CityItem();
        item.setCityName("排行榜");
        cityList.add(item);
        item = new CityItem();
        item.setCityName("电台");
        cityList.add(item);
        item = new CityItem();
        item.setCityName("直播");
        cityList.add(item);
        item = new CityItem();
        item.setCityName("火前留名");
        cityList.add(item);
    }

    public void findByIdAndNew() {
        gridView = (GridView) FindView.findViewById(R.id.grid);
    }
}
