package com.gxuwz.beethoven.adapter.find;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.gxuwz.beethoven.R;
import com.gxuwz.beethoven.util.HttpUtil;
import com.gxuwz.beethoven.util.MergeImage;

import java.util.List;

public class FindTelecastAdapter extends BaseAdapter {
    Context context;
    List<TelecastItem> list;
    LayoutInflater layoutInflater;
    public FindTelecastAdapter(Context context, List<TelecastItem> list, LayoutInflater layoutInflater) {
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
        convertView = layoutInflater.inflate(R.layout.activity_find_telecast_item, null);
        ImageView telecastDiagonal = convertView.findViewById(R.id.telecast_diagonal);
        TextView onLineNumber = convertView.findViewById(R.id.on_line_number);
        TextView type = convertView.findViewById(R.id.telecast_type);
        TextView title = convertView.findViewById(R.id.telecast_title);

        TelecastItem telecastItem = list.get(position);

        telecastDiagonal.setImageBitmap(MergeImage.roundedCustom(HttpUtil.getRes(telecastItem.getTelecastDisgonal(),context)));
        onLineNumber.setText(telecastItem.getOnLineNumber()+"·");
        type.setText(telecastItem.getType());
        title.setText(telecastItem.getTitle());
        return convertView;
    }
}
