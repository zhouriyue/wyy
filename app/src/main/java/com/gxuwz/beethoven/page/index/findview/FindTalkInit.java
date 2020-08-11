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
import com.gxuwz.beethoven.adapter.find.TalkAdapter;
import com.gxuwz.beethoven.adapter.find.TalkItem;

import java.util.ArrayList;
import java.util.List;

/**
 * “精选说说”初始化模块
 */
public class FindTalkInit {
    View FindView;
    List<TalkItem> talkList;
    GridView talkGridView;

    public FindTalkInit(View findView) {
        FindView = findView;
    }

    public void init(LayoutInflater layoutInflater, WindowManager windowManager, Context context){
        findByIdAndNew();
        setData();
        setGridView(layoutInflater,windowManager,context);
    }

    /**设置GirdView参数，绑定数据*/
    private void setGridView(LayoutInflater layoutInflater, WindowManager windowManager, Context context) {
        int size = talkList.size();
        int width = 300;
        int length = 450;
        DisplayMetrics dm = new DisplayMetrics();
        windowManager.getDefaultDisplay().getMetrics(dm);
        int gridviewWidth = 20;//水平间距
        int itemWidth = width;//列表项宽

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                (size)*length+length/2, LinearLayout.LayoutParams.FILL_PARENT);

        talkGridView.setLayoutParams(params); // 设置GirdView布局参数,横向布局的关键
        talkGridView.setColumnWidth(itemWidth); // 设置列表项宽
        talkGridView.setHorizontalSpacing(gridviewWidth); // 设置列表项水平间距
        talkGridView.setStretchMode(GridView.NO_STRETCH);
        talkGridView.setSelector(new ColorDrawable(Color.TRANSPARENT));
        talkGridView.setNumColumns(size); // 设置列数量=列表集合数

        TalkAdapter adapter = new TalkAdapter(context,
                talkList,layoutInflater);
        talkGridView.setAdapter(adapter);
    }

    /**设置数据*/
    private void setData() {
        TalkItem talkItem = new TalkItem();
        talkItem.setDiagonal("talk_diagonal");
        talkItem.setGivelikeNumber(5534);
        talkItem.setTalkTitle("台上她就是Queen 她一出来");
        talkList.add(talkItem);

        talkItem = new TalkItem();
        talkItem.setDiagonal("talk_diagonal");
        talkItem.setGivelikeNumber(5534);
        talkItem.setTalkTitle("台上她就是Queen 她一出来");
        talkList.add(talkItem);

        talkItem = new TalkItem();
        talkItem.setDiagonal("talk_diagonal");
        talkItem.setGivelikeNumber(5534);
        talkItem.setTalkTitle("台上她就是Queen 她一出来");
        talkList.add(talkItem);

        talkItem = new TalkItem();
        talkItem.setDiagonal("talk_diagonal");
        talkItem.setGivelikeNumber(5534);
        talkItem.setTalkTitle("台上她就是Queen 她一出来");
        talkList.add(talkItem);

        talkItem = new TalkItem();
        talkItem.setDiagonal("talk_diagonal");
        talkItem.setGivelikeNumber(5534);
        talkItem.setTalkTitle("台上她就是Queen 她一出来");
        talkList.add(talkItem);
    }

    public void findByIdAndNew(){
        talkGridView = FindView.findViewById(R.id.talk_grid_view);
        talkList = new ArrayList<TalkItem>();
    }
}
