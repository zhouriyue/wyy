package com.gxuwz.beethoven.adapter.find;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.gxuwz.beethoven.R;
import com.gxuwz.beethoven.conn.Http;
import com.gxuwz.beethoven.util.HttpUtil;
import com.gxuwz.beethoven.util.MergeImage;

import java.util.List;

public class RecommendLikeAdapter extends BaseAdapter {
    Context context;
    List<RecommendedListItem> list;
    LayoutInflater layoutInflater;
    public RecommendLikeAdapter(Context context, List<RecommendedListItem> list,LayoutInflater layoutInflater) {
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
        convertView = layoutInflater.inflate(R.layout.activity_find_recommend_like_item, null);
        ImageView diagonalOne = convertView.findViewById(R.id.diagonal_one);
        TextView recommendLikeSongnameOne = convertView.findViewById(R.id.recommend_like_songname_one);
        TextView recommendLikeSingerOne = convertView.findViewById(R.id.recommend_like_singer_one);
        TextView detailOne = convertView.findViewById(R.id.detail_one);

        ImageView diagonalTwo = convertView.findViewById(R.id.diagonal_two);
        TextView recommendLikeSongnameTwo = convertView.findViewById(R.id.recommend_like_songname_two);
        TextView recommendLikeSingerTwo = convertView.findViewById(R.id.recommend_like_singer_two);
        TextView detailTwo = convertView.findViewById(R.id.detail_two);

        ImageView diagonalThree = convertView.findViewById(R.id.diagonal_three);
        TextView recommendLikeSongnameThree = convertView.findViewById(R.id.recommend_like_songname_three);
        TextView recommendLikeSingerThree = convertView.findViewById(R.id.recommend_like_singer_three);
        TextView detailThree = convertView.findViewById(R.id.detail_three);


        RecommendedListItem recommendedListItem = list.get(position);
        diagonalOne.setImageBitmap(MergeImage.roundedCustom(HttpUtil.getRes(recommendedListItem.getDiagonal(),context)));
        recommendLikeSongnameOne.setText(recommendedListItem.getSongName());
        recommendLikeSingerOne.setText("-"+recommendedListItem.getSinger());
        detailOne.setText(recommendedListItem.getDetail());

        recommendedListItem = list.get(position+1);
        diagonalTwo.setImageBitmap(MergeImage.roundedCustom(HttpUtil.getRes(recommendedListItem.getDiagonal(),context)));
        recommendLikeSongnameTwo.setText(recommendedListItem.getSongName());
        recommendLikeSingerTwo.setText("-"+recommendedListItem.getSinger());
        detailTwo.setText(recommendedListItem.getDetail());

        recommendedListItem = list.get(position+2);
        diagonalThree.setImageBitmap(MergeImage.roundedCustom(HttpUtil.getRes(recommendedListItem.getDiagonal(),context)));
        recommendLikeSongnameThree.setText(recommendedListItem.getSongName());
        recommendLikeSingerThree.setText("-"+recommendedListItem.getSinger());
        detailThree.setText(recommendedListItem.getDetail());
        return convertView;
    }
}
