package com.gxuwz.beethoven.adapter.find;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.gxuwz.beethoven.R;
import com.gxuwz.beethoven.model.entity.Room;
import com.gxuwz.beethoven.util.HttpUtil;
import com.gxuwz.beethoven.util.MergeImage;

import java.util.List;

public class ChoiceRoomAdapter extends BaseAdapter {
    Context context;
    List<Room> list;
    LayoutInflater layoutInflater;
    public ChoiceRoomAdapter(Context context, List<Room> list, LayoutInflater layoutInflater) {
        this.list = list;
        this.context = context;
        this.layoutInflater = layoutInflater;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = layoutInflater.inflate(R.layout.activity_find_choice_room_item, null);
        ImageView choiceRoomDiagonal = convertView.findViewById(R.id.choice_room_diagonal);
        TextView choiceRoomType = convertView.findViewById(R.id.choice_room_type);
        TextView choiceRoomTitle = convertView.findViewById(R.id.choice_room_title);
        Room room = list.get(position);

        choiceRoomDiagonal.setImageBitmap(MergeImage.roundedCustom(HttpUtil.getRes(room.getDiagonal(),context)));
        choiceRoomType.setText(room.getType());
        choiceRoomTitle.setText(room.getTitle());
        return convertView;
    }
}
