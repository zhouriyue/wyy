package com.gxuwz.beethoven.adapter.find;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.gxuwz.beethoven.R;
import com.gxuwz.beethoven.model.entity.find.CloudCircle;
import com.gxuwz.beethoven.util.HttpUtil;
import com.gxuwz.beethoven.util.MergeImage;

import java.util.List;

public class CloudCircleAdapter extends BaseAdapter {
    Context context;
    List<CloudCircle> list;
    LayoutInflater layoutInflater;
    public CloudCircleAdapter(Context context, List<CloudCircle> list, LayoutInflater layoutInflater) {
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
        convertView = layoutInflater.inflate(R.layout.activity_find_cloud_circle_item, null);
        ImageView choiceRoomDiagonal = convertView.findViewById(R.id.choice_room_diagonal);
        TextView cloudPlayNumber = convertView.findViewById(R.id.cloud_circle_play_number);
        TextView cloudCircleTitle = convertView.findViewById(R.id.cloud_circle_title);
        TextView cloudCircleSubTitle = convertView.findViewById(R.id.cloud_circle_subtitle);
        CloudCircle cloudCircle = list.get(position);
        choiceRoomDiagonal.setImageBitmap(MergeImage.roundedCustomDB(HttpUtil.getRes(cloudCircle.getCloudCircleDiagonal(),context),200,190));
        cloudPlayNumber.setText(cloudCircle.getPlayNumber()+"万");
        cloudCircleTitle.setText(cloudCircle.getTitle());
        cloudCircleSubTitle.setText(cloudCircle.getSubTitle());
        return convertView;
    }
}
