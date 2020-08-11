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

public class TalkAdapter extends BaseAdapter{
    Context context;
    List<TalkItem> list;
    LayoutInflater layoutInflater;

    public TalkAdapter(Context context, List<TalkItem> list,LayoutInflater layoutInflater) {
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
        convertView = layoutInflater.inflate(R.layout.activity_find_talk_item, null);
        ImageView talkDiagonal = convertView.findViewById(R.id.talk_diagonal);
        TextView talkPlayNumber = convertView.findViewById(R.id.talk_play_number);
        TextView talkTitle = convertView.findViewById(R.id.talk_title);

        TalkItem talkItem = list.get(position);
        talkDiagonal.setImageBitmap(MergeImage.roundedCustom(HttpUtil.getRes(talkItem.getDiagonal(),context)));
        talkPlayNumber.setText(talkItem.getGivelikeNumber()+"赞");
        talkTitle.setText(talkItem.getTalkTitle());
        return convertView;
    }
}
