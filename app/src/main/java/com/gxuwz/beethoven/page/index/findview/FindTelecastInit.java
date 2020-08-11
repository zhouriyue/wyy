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
import com.gxuwz.beethoven.adapter.find.FindTelecastAdapter;
import com.gxuwz.beethoven.adapter.find.TelecastItem;

import java.util.ArrayList;
import java.util.List;

/**
 * “直播”初始化模块
 */
public class FindTelecastInit {
    View FindView;
    List<TelecastItem> telecastList;
    GridView telecastGridView;

    public FindTelecastInit(View findView) {
        FindView = findView;
    }

    public void init(LayoutInflater layoutInflater, WindowManager windowManager, Context context){
        findByIdAndNew();
        setData();
        setGridView(layoutInflater,windowManager,context);
    }
    /**设置GirdView参数，绑定数据*/
    private void setGridView(LayoutInflater layoutInflater, WindowManager windowManager, Context context) {
        int size = telecastList.size();
        int width = 300;
        int length = 350;
        DisplayMetrics dm = new DisplayMetrics();
        windowManager.getDefaultDisplay().getMetrics(dm);
        float density = dm.density;
        int gridviewWidth = 20;//水平间距
        int itemWidth = width;//列表项宽

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                (size)*width+width/2, length);

        telecastGridView.setLayoutParams(params); // 设置GirdView布局参数,横向布局的关键
        telecastGridView.setColumnWidth(itemWidth); // 设置列表项宽
        telecastGridView.setHorizontalSpacing(gridviewWidth); // 设置列表项水平间距
        telecastGridView.setStretchMode(GridView.NO_STRETCH);
        telecastGridView.setSelector(new ColorDrawable(Color.TRANSPARENT));
        telecastGridView.setNumColumns(size); // 设置列数量=列表集合数

        FindTelecastAdapter adapter = new FindTelecastAdapter(context,
                telecastList,layoutInflater);
        telecastGridView.setAdapter(adapter);
    }

    /**设置数据*/
    private void setData() {
        TelecastItem telecastItem = new TelecastItem();
        telecastItem.setTelecastDisgonal("youth");
        telecastItem.setTitle("八月第一天，你好吗");
        telecastItem.setOnLineNumber(665);
        telecastItem.setType("尬聊");
        telecastList.add(telecastItem);

        telecastItem = new TelecastItem();
        telecastItem.setTelecastDisgonal("youth");
        telecastItem.setTitle("八月第一天，你好吗");
        telecastItem.setOnLineNumber(665);
        telecastItem.setType("尬聊");
        telecastList.add(telecastItem);

        telecastItem = new TelecastItem();
        telecastItem.setTelecastDisgonal("youth");
        telecastItem.setTitle("八月第一天，你好吗");
        telecastItem.setOnLineNumber(665);
        telecastItem.setType("尬聊");
        telecastList.add(telecastItem);

        telecastItem = new TelecastItem();
        telecastItem.setTelecastDisgonal("youth");
        telecastItem.setOnLineNumber(665);
        telecastItem.setType("尬聊");
        telecastItem.setTitle("萌新小林月底冲鸭");
        telecastList.add(telecastItem);

        telecastItem = new TelecastItem();
        telecastItem.setTelecastDisgonal("youth");
        telecastItem.setOnLineNumber(665);
        telecastItem.setType("尬聊");
        telecastItem.setTitle("萌新小林月底冲鸭");
        telecastList.add(telecastItem);
    }

    public void findByIdAndNew(){
        telecastGridView = FindView.findViewById(R.id.telecast_grid_view);
        telecastList = new ArrayList<TelecastItem>();
    }

}
