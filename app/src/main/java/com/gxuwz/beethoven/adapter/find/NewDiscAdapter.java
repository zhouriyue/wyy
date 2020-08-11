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

public class NewDiscAdapter extends BaseAdapter {
    Context context;
    List<NewDiscItem> list;
    LayoutInflater layoutInflater;
    public NewDiscAdapter(Context context, List<NewDiscItem> list,LayoutInflater layoutInflater) {
        this.list = list;
        this.context = context;
        this.layoutInflater = layoutInflater;
    }

    @Override
    public int getCount() {
        return list.size()/3;
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
        convertView = layoutInflater.inflate(R.layout.activity_find_new_disc_item, null);
        ImageView discDiagonalOne = convertView.findViewById(R.id.disc_diagonal_one);
        TextView discSongNameOne = convertView.findViewById(R.id.disc_songname_one);
        TextView discSingerOne = convertView.findViewById(R.id.disc_singer_one);
        TextView discDetailOne = convertView.findViewById(R.id.disc_detail_one);

        ImageView discDiagonalTwo = convertView.findViewById(R.id.disc_diagonal_two);
        TextView discSongNameTwo = convertView.findViewById(R.id.disc_songname_two);
        TextView discSingerTwo = convertView.findViewById(R.id.disc_singer_two);
        TextView discDetailTwo = convertView.findViewById(R.id.disc_detail_two);

        ImageView discDiagonalThree = convertView.findViewById(R.id.disc_diagonal_three);
        TextView discSongNameThree = convertView.findViewById(R.id.disc_songname_three);
        TextView discSingerThree = convertView.findViewById(R.id.disc_singer_three);
        TextView discDetailThree = convertView.findViewById(R.id.disc_detail_three);

        NewDiscItem newDiscItem = list.get(position);
        discDiagonalOne.setImageBitmap(MergeImage.roundedCustom(HttpUtil.getRes(newDiscItem.getDiagonal(),context)));
        discSongNameOne.setText(newDiscItem.getDiscName());
        discSingerOne.setText(newDiscItem.getSinger());
        discDetailOne.setText(newDiscItem.getDetail());

        newDiscItem = list.get(position+1);
        discDiagonalTwo.setImageBitmap(MergeImage.roundedCustom(HttpUtil.getRes(newDiscItem.getDiagonal(),context)));
        discSongNameTwo.setText(newDiscItem.getDiscName());
        discSingerTwo.setText(newDiscItem.getSinger());
        discDetailTwo.setText(newDiscItem.getDetail());

        newDiscItem = list.get(position+2);
        discDiagonalThree.setImageBitmap(MergeImage.roundedCustom(HttpUtil.getRes(newDiscItem.getDiagonal(),context)));
        discSongNameThree.setText(newDiscItem.getDiscName());
        discSingerThree.setText(newDiscItem.getSinger());
        discDetailThree.setText(newDiscItem.getDetail());
        return convertView;
    }
}
