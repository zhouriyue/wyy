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

public class ChoiceSongListAdapter extends BaseAdapter {
    Context context;
    List<ChoiceSongListItem> list;
    LayoutInflater layoutInflater;
    public ChoiceSongListAdapter(Context context, List<ChoiceSongListItem> list,LayoutInflater layoutInflater) {
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
        convertView = layoutInflater.inflate(R.layout.activity_find_choice_grid_item, null);
        ImageView imageView = convertView.findViewById(R.id.choice_diagonal);
        TextView playNumber = (TextView) convertView.findViewById(R.id.choice_play_number_title);
        TextView title = (TextView) convertView.findViewById(R.id.choice_title);
        ChoiceSongListItem choiceSongListItem = list.get(position);
        imageView.setImageBitmap(MergeImage.roundedCustom(HttpUtil.getRes("youth",context)));
        playNumber.setText(choiceSongListItem.getPlayNumber()+"万");
        title.setText(choiceSongListItem.getTitle());
        return convertView;
    }
}
