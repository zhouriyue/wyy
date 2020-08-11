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
import com.gxuwz.beethoven.adapter.find.ChoiceSongListAdapter;
import com.gxuwz.beethoven.adapter.find.ChoiceSongListItem;
import com.gxuwz.beethoven.adapter.find.CityItem;
import com.gxuwz.beethoven.adapter.find.FunListAdapter;

import java.util.ArrayList;
import java.util.List;

public class FindChoiceSongListInit {
    View FindView;
    List<ChoiceSongListItem> choiceSongListItemList;
    GridView choiceGridView;

    public FindChoiceSongListInit(View findView) {
        FindView = findView;
    }

    public void init(LayoutInflater layoutInflater,WindowManager windowManager,Context context){
        findByIdAndNew();
        setData();
        setGridView(layoutInflater,windowManager,context);
    }

    /**设置GirdView参数，绑定数据*/
    private void setGridView(LayoutInflater layoutInflater, WindowManager windowManager, Context context) {
        int size = choiceSongListItemList.size();
        int width = 300;
        int length = 350;
        DisplayMetrics dm = new DisplayMetrics();
        windowManager.getDefaultDisplay().getMetrics(dm);
        float density = dm.density;
        int gridviewWidth = 20;//水平间距
        int itemWidth = length;//列表项宽

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                (size)*width+width/2, length);

        choiceGridView.setLayoutParams(params); // 设置GirdView布局参数,横向布局的关键
        choiceGridView.setColumnWidth(itemWidth); // 设置列表项宽
        choiceGridView.setHorizontalSpacing(gridviewWidth); // 设置列表项水平间距
        choiceGridView.setStretchMode(GridView.NO_STRETCH);
        choiceGridView.setSelector(new ColorDrawable(Color.TRANSPARENT));
        choiceGridView.setNumColumns(size); // 设置列数量=列表集合数

        ChoiceSongListAdapter adapter = new ChoiceSongListAdapter(context,
                choiceSongListItemList,layoutInflater);
        choiceGridView.setAdapter(adapter);
    }

    /**设置数据*/
    private void setData() {
        ChoiceSongListItem item = new ChoiceSongListItem();
        item.setPlayNumber(100);
        item.setTitle("夏夜晚风|希望能有人温柔地接住");
        choiceSongListItemList.add(item);

        item = new ChoiceSongListItem();
        item.setPlayNumber(100);
        item.setTitle("夏夜晚风|希望能有人温柔地接住");
        choiceSongListItemList.add(item);

        item = new ChoiceSongListItem();
        item.setPlayNumber(100);
        item.setTitle("夏夜晚风|希望能有人温柔地接住");
        choiceSongListItemList.add(item);

        item = new ChoiceSongListItem();
        item.setPlayNumber(100);
        item.setTitle("夏夜晚风|希望能有人温柔地接住");
        choiceSongListItemList.add(item);

        item = new ChoiceSongListItem();
        item.setPlayNumber(100);
        item.setTitle("夏夜晚风|希望能有人温柔地接住");
        choiceSongListItemList.add(item);

        item = new ChoiceSongListItem();
        item.setPlayNumber(100);
        item.setTitle("夏夜晚风|希望能有人温柔地接住");
        choiceSongListItemList.add(item);
    }

    public void findByIdAndNew(){
        choiceGridView = FindView.findViewById(R.id.choice_song_list);
        choiceSongListItemList = new ArrayList<ChoiceSongListItem>();
    }
}
