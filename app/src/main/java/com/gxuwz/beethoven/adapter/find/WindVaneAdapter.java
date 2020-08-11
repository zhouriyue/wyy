package com.gxuwz.beethoven.adapter.find;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.gxuwz.beethoven.R;
import com.gxuwz.beethoven.model.entity.HotVane;
import com.gxuwz.beethoven.model.entity.Music;
import com.gxuwz.beethoven.model.entity.WindMusic;
import com.gxuwz.beethoven.util.BlurUtil;
import com.gxuwz.beethoven.util.HttpUtil;
import com.gxuwz.beethoven.util.MergeImage;

import java.util.List;

public class WindVaneAdapter extends BaseAdapter {
    Context context;
    List<HotVane> list;
    LayoutInflater layoutInflater;
    public WindVaneAdapter(Context context, List<HotVane> list, LayoutInflater layoutInflater) {
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
        convertView = layoutInflater.inflate(R.layout.activity_find_wind_vane_item, null);
        ImageView hotWindDiagonal = convertView.findViewById(R.id.hot_wind_diagonal);

        ImageView windDiagonalOne = convertView.findViewById(R.id.wind_diagonal_one);
        TextView positionOne = convertView.findViewById(R.id.position_one);
        TextView windSongNameOne = convertView.findViewById(R.id.wind_songName_one);
        TextView singerOne = convertView.findViewById(R.id.singer_one);
        TextView statusOne = convertView.findViewById(R.id.status_one);

        ImageView windDiagonalTwo = convertView.findViewById(R.id.wind_diagonal_two);
        TextView positionTwo = convertView.findViewById(R.id.position_two);
        TextView windSongNameTwo = convertView.findViewById(R.id.wind_songName_two);
        TextView singerTwo = convertView.findViewById(R.id.singer_two);
        TextView statusTwo = convertView.findViewById(R.id.status_two);

        ImageView windDiagonalThree = convertView.findViewById(R.id.wind_diagonal_three);
        TextView positionThree = convertView.findViewById(R.id.position_three);
        TextView windSongNameThree = convertView.findViewById(R.id.wind_songName_three);
        TextView singerThree = convertView.findViewById(R.id.singer_three);
        TextView statusThree = convertView.findViewById(R.id.status_three);

        HotVane hotVane = list.get(position);
        int index = 0;
        //风向标封面
        hotWindDiagonal.setImageBitmap(MergeImage.roundedCustom(HttpUtil.getRes(hotVane.getDiagonal(),context)));

        List<WindMusic> windMusic = hotVane.getWindMusic();
        WindMusic windMusic1 = windMusic.get(index);
        windDiagonalOne.setImageBitmap(MergeImage.roundedCustom(HttpUtil.getRes(windMusic1.getMusic().getSongPicUrl(),context)));
        positionOne.setText(""+(index+1));
        windSongNameOne.setText(windMusic1.getMusic().getMusicName());
        singerOne.setText(windMusic1.getMusic().getSinger());
        statusOne.setText(windMusic1.getStatus());

        windMusic = hotVane.getWindMusic();
        windMusic1 = windMusic.get(++index);
        windDiagonalTwo.setImageBitmap(MergeImage.roundedCustom(HttpUtil.getRes(windMusic1.getMusic().getSongPicUrl(),context)));
        positionTwo.setText(""+(index+1));
        windSongNameTwo.setText(windMusic1.getMusic().getMusicName());
        singerTwo.setText(windMusic1.getMusic().getSinger());
        statusTwo.setText(windMusic1.getStatus());

        windMusic = hotVane.getWindMusic();
        windMusic1 = windMusic.get(++index);
        //windDiagonalThree.setImageBitmap(MergeImage.roundedCustom(HttpUtil.getRes(windMusic1.getMusic().getSongPicUrl(),context)));
        positionThree.setText(""+(index+1));
        windSongNameThree.setText(windMusic1.getMusic().getMusicName());
        singerThree.setText(windMusic1.getMusic().getSinger());
        statusThree.setText(windMusic1.getStatus());
        return convertView;
    }
}
