package com.gxuwz.beethoven.adapter.video;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.gxuwz.beethoven.R;
import com.gxuwz.beethoven.model.entity.video.VideoBox;
import com.gxuwz.beethoven.util.HttpUtil;
import com.gxuwz.beethoven.util.MergeImage;
import com.gxuwz.beethoven.util.WindowPixels;

import java.util.List;

public class VideoBoxAdapter extends RecyclerView.Adapter<VideoBoxAdapter.VideoBoxViewHolder> {

    List<VideoBox> videoBoxList;
    Context context;

    public VideoBoxAdapter(List<VideoBox> videoBoxList, Context context) {
        this.videoBoxList = videoBoxList;
        this.context = context;
    }

    @NonNull
    @Override
    public VideoBoxViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new VideoBoxViewHolder(LayoutInflater.from(context).inflate(R.layout.subpage_viewbox_item,null));
    }

    @Override
    public void onBindViewHolder(@NonNull VideoBoxViewHolder holder, int position) {
        VideoBox videoBox = videoBoxList.get(position);
        holder.diagonal.setImageBitmap(MergeImage.roundedCustomDB(HttpUtil.getRes(videoBox.getDiagonal(),context),
                (int) (WindowPixels.WIDTH-10),
                (int) (WindowPixels.WIDTH*0.5)));
        holder.tag.setText(videoBox.getTag());
        holder.playNumber.setText(videoBox.getPlayNumber()+"");
        holder.playTime.setText(videoBox.getPlayTime());
        holder.messNumber.setText(videoBox.getMessNumber()+"");
        holder.giveLike.setText(videoBox.getGetLike()+"");
        holder.title.setText(videoBox.getTitle());
    }

    @Override
    public int getItemCount() {
        return videoBoxList.size();
    }

    public class VideoBoxViewHolder extends RecyclerView.ViewHolder {

        ImageView diagonal;
        TextView tag,playNumber,playTime,title,giveLike,messNumber;
        public VideoBoxViewHolder(@NonNull View itemView) {
            super(itemView);
            diagonal = itemView.findViewById(R.id.diagonal);
            tag = itemView.findViewById(R.id.tag);
            playNumber = itemView.findViewById(R.id.play_number);
            playTime = itemView.findViewById(R.id.play_time);
            title = itemView.findViewById(R.id.title);
            giveLike = itemView.findViewById(R.id.give_like);
            messNumber = itemView.findViewById(R.id.mess_number);
        }
    }
}
