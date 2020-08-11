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
import com.gxuwz.beethoven.adapter.find.CloudCircleAdapter;
import com.gxuwz.beethoven.adapter.find.FindTelecastAdapter;
import com.gxuwz.beethoven.adapter.find.TelecastItem;
import com.gxuwz.beethoven.model.entity.CloudCircle;

import java.util.ArrayList;
import java.util.List;

/**
 * “云圈”初始化模块
 */
public class FindCloudCircle {
    View FindView;
    List<CloudCircle> cloudCircleList;
    GridView cloudCircleGrid;

    public FindCloudCircle(View findView) {
        FindView = findView;
    }

    public void init(LayoutInflater layoutInflater, WindowManager windowManager, Context context){
        findByIdAndNew();
        setData();
        setGridView(layoutInflater,windowManager,context);
    }
    /**设置GirdView参数，绑定数据*/
    private void setGridView(LayoutInflater layoutInflater, WindowManager windowManager, Context context) {
        int size = cloudCircleList.size();
        int width = 475;
        int length = 500;
        DisplayMetrics dm = new DisplayMetrics();
        windowManager.getDefaultDisplay().getMetrics(dm);
        int gridviewWidth = 10;//水平间距
        int itemWidth = width;//列表项宽

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                (size*width+width/2), length);

        cloudCircleGrid.setLayoutParams(params); // 设置GirdView布局参数,横向布局的关键
        cloudCircleGrid.setColumnWidth(itemWidth); // 设置列表项宽
        cloudCircleGrid.setHorizontalSpacing(gridviewWidth); // 设置列表项水平间距
        cloudCircleGrid.setStretchMode(GridView.NO_STRETCH);
        cloudCircleGrid.setSelector(new ColorDrawable(Color.TRANSPARENT));
        cloudCircleGrid.setNumColumns(size); // 设置列数量=列表集合数

        CloudCircleAdapter adapter = new CloudCircleAdapter(context,
                cloudCircleList,layoutInflater);
        cloudCircleGrid.setAdapter(adapter);
    }

    /**设置数据*/
    private void setData() {
        CloudCircle cloudCircle = new CloudCircle();
        cloudCircle.setCloudCircleDiagonal("youth");
        cloudCircle.setPlayNumber(101.9);
        cloudCircle.setTitle("韩国K-POP资讯站");
        cloudCircle.setSubTitle("1.4万圈友已加入");
        cloudCircleList.add(cloudCircle);

        cloudCircle = new CloudCircle();
        cloudCircle.setCloudCircleDiagonal("youth");
        cloudCircle.setPlayNumber(101.9);
        cloudCircle.setTitle("韩国K-POP资讯站");
        cloudCircle.setSubTitle("1.4万圈友已加入");
        cloudCircleList.add(cloudCircle);

        cloudCircle = new CloudCircle();
        cloudCircle.setCloudCircleDiagonal("youth");
        cloudCircle.setPlayNumber(101.9);
        cloudCircle.setTitle("韩国K-POP资讯站");
        cloudCircle.setSubTitle("1.4万圈友已加入");
        cloudCircleList.add(cloudCircle);

        cloudCircle = new CloudCircle();
        cloudCircle.setCloudCircleDiagonal("youth");
        cloudCircle.setPlayNumber(101.9);
        cloudCircle.setTitle("韩国K-POP资讯站");
        cloudCircle.setSubTitle("1.4万圈友已加入");
        cloudCircleList.add(cloudCircle);

        cloudCircle = new CloudCircle();
        cloudCircle.setCloudCircleDiagonal("youth");
        cloudCircle.setPlayNumber(101.9);
        cloudCircle.setTitle("韩国K-POP资讯站");
        cloudCircle.setSubTitle("1.4万圈友已加入");
        cloudCircleList.add(cloudCircle);
    }

    public void findByIdAndNew(){
        cloudCircleGrid = FindView.findViewById(R.id.cloud_circle_gridview);
        cloudCircleList = new ArrayList<CloudCircle>();
    }
}
