package com.gxuwz.beethoven.adapter.video.official;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.gxuwz.beethoven.R;
import com.gxuwz.beethoven.model.entity.video.official.OfficialVideo;
import com.gxuwz.beethoven.util.HttpUtils;
import com.gxuwz.beethoven.util.MergeImage;
import com.gxuwz.beethoven.util.WindowPixels;

import java.util.List;

public class OfficialVideoAdapter extends RecyclerView.Adapter<OfficialVideoAdapter.OfficialVideoViewHolder> {

    Context context;
    List<OfficialVideo> officialVideoList;
    int itemWidth;
    int height;

    public OfficialVideoAdapter(Context context, List<OfficialVideo> officialVideoList) {
        this.context = context;
        this.officialVideoList = officialVideoList;
        itemWidth = (int) ((WindowPixels.WIDTH-10)/2);
        height = (int) (itemWidth/1.6);
    }

    @NonNull
    @Override
    public OfficialVideoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new OfficialVideoViewHolder(LayoutInflater.from(context).inflate(R.layout.subpage_official_item,null));
    }

    @Override
    public void onBindViewHolder(@NonNull OfficialVideoViewHolder holder, int position) {
        OfficialVideo officialVideo = officialVideoList.get(position);
        holder.diagonal.setImageBitmap(MergeImage.roundedCustomDB(HttpUtils.getRes(officialVideo.getDiagonal(),context),itemWidth,height));
        holder.time.setText(officialVideo.getTime());
        holder.title.setText(officialVideo.getTitle());
    }

    @Override
    public int getItemCount() {
        return officialVideoList.size();
    }

    public class OfficialVideoViewHolder extends RecyclerView.ViewHolder{

        ImageView diagonal;
        TextView time,title;

        public OfficialVideoViewHolder(@NonNull View itemView) {
            super(itemView);
            diagonal = itemView.findViewById(R.id.diagonal);
            time = itemView.findViewById(R.id.time);
            title = itemView.findViewById(R.id.title);
        }
    }
}
