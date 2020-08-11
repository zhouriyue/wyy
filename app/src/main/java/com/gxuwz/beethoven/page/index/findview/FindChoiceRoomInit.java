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
import com.gxuwz.beethoven.adapter.find.ChoiceRoomAdapter;
import com.gxuwz.beethoven.adapter.find.FunListAdapter;
import com.gxuwz.beethoven.model.entity.Room;

import java.util.ArrayList;
import java.util.List;

/**
 * “精选房间”初始化模块
 */
public class FindChoiceRoomInit {
    View FindView;
    List<Room> roomList;
    GridView roomgridView;

    public FindChoiceRoomInit(View findView) {
        FindView = findView;
    }

    public void init(LayoutInflater layoutInflater, WindowManager windowManager, Context context){
        findByIdAndNew();
        setData();
        setGridView(layoutInflater,windowManager,context);
    }

    /**设置GirdView参数，绑定数据*/
    private void setGridView(LayoutInflater layoutInflater, WindowManager windowManager, Context context) {
        int size = roomList.size();
        int width = 300;
        int height = 350;
        DisplayMetrics dm = new DisplayMetrics();
        windowManager.getDefaultDisplay().getMetrics(dm);
        int gridviewWidth = (int) 20;
        int itemWidth = (int) width;

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                (size+1)*190, LinearLayout.LayoutParams.FILL_PARENT);

        roomgridView.setLayoutParams(params); // 设置GirdView布局参数,横向布局的关键
        roomgridView.setColumnWidth(itemWidth); // 设置列表项宽
        roomgridView.setHorizontalSpacing(gridviewWidth); // 设置列表项水平间距
        roomgridView.setStretchMode(GridView.NO_STRETCH);
        roomgridView.setSelector(new ColorDrawable(Color.TRANSPARENT));
        roomgridView.setNumColumns(size); // 设置列数量=列表集合数

        ChoiceRoomAdapter adapter = new ChoiceRoomAdapter(context,
                roomList,layoutInflater);
        roomgridView.setAdapter(adapter);
    }

    /**设置数据*/
    private void setData() {
        Room room = new Room();
        room.setDiagonal("talk_diagonal");
        room.setType("闲聊唠嗑");
        room.setTitle("聊天吗");
        roomList.add(room);

        room = new Room();
        room.setDiagonal("talk_diagonal");
        room.setType("闲聊唠嗑");
        room.setTitle("聊天吗");
        roomList.add(room);

        room = new Room();
        room.setDiagonal("talk_diagonal");
        room.setType("闲聊唠嗑");
        room.setTitle("聊天吗");
        roomList.add(room);

        room = new Room();
        room.setDiagonal("talk_diagonal");
        room.setType("闲聊唠嗑");
        room.setTitle("聊天吗");
        roomList.add(room);

        room = new Room();
        room.setDiagonal("talk_diagonal");
        room.setType("闲聊唠嗑");
        room.setTitle("聊天吗");
        roomList.add(room);

        room = new Room();
        room.setDiagonal("talk_diagonal");
        room.setType("闲聊唠嗑");
        room.setTitle("聊天吗");
        roomList.add(room);

        room = new Room();
        room.setDiagonal("talk_diagonal");
        room.setType("闲聊唠嗑");
        room.setTitle("聊天吗");
        roomList.add(room);
    }

    public void findByIdAndNew(){
        roomgridView = FindView.findViewById(R.id.choice_room_grid_view);
        roomList = new ArrayList<Room>();
    }
}
