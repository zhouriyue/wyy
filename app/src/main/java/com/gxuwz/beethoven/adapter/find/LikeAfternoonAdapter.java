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

public class LikeAfternoonAdapter extends BaseAdapter {
    Context context;
    List<LikeAfternoonItem> list;
    LayoutInflater layoutInflater;

    public LikeAfternoonAdapter(Context context, List<LikeAfternoonItem> list,LayoutInflater layoutInflater) {
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
        convertView = layoutInflater.inflate(R.layout.activity_find_like_afternoon_item, null);

        ImageView likeAfternoon = convertView.findViewById(R.id.like_afternoon_diagonal);
        TextView likeAfternoonPlayNumber = convertView.findViewById(R.id.like_afternoon_play_number);
        TextView englishTitle = convertView.findViewById(R.id.like_afternoon_english_title);
        TextView chinaTitle = convertView.findViewById(R.id.like_afternoon_china_title);
        TextView mainTitle = convertView.findViewById(R.id.like_afternoon_main_title);

        LikeAfternoonItem likeAfternoonItem = list.get(position);
        likeAfternoon.setImageBitmap(MergeImage.roundedCustom(HttpUtil.getRes(likeAfternoonItem.getDiagonal(),context)));
        likeAfternoonPlayNumber.setText(likeAfternoonItem.getPlayNumber()+"");
        englishTitle.setText(likeAfternoonItem.getEnglishTitle());
        chinaTitle.setText(likeAfternoonItem.getChinaTitle());
        mainTitle.setText(likeAfternoonItem.getTitle());
        return convertView;
    }
}
