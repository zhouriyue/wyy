package com.gxuwz.beethoven.adapter.my.collect.video;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.gxuwz.beethoven.R;
import com.gxuwz.beethoven.model.entity.my.collect.Video;
import com.gxuwz.beethoven.util.HttpUtil;
import com.gxuwz.beethoven.util.MergeImage;

import java.util.List;

public class VideoAdapter  extends RecyclerView.Adapter<VideoAdapter.VideoViewHolder> {

    Context context;
    List<Video> videoList;

    public VideoAdapter(Context context, List<Video> videoList) {
        this.context = context;
        this.videoList = videoList;
    }

    @NonNull
    @Override
    public VideoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new VideoViewHolder(LayoutInflater.from(context).inflate(R.layout.collect_video_item,null));
    }

    @Override
    public void onBindViewHolder(@NonNull VideoViewHolder holder, int position) {
        Video video = videoList.get(position);
        holder.diagonal.setImageBitmap(MergeImage.roundedCustomDB(HttpUtil.getRes(video.getDiagonal(),context),150,90,5));
        holder.time.setText(video.getUploadTime());
        holder.playNumber.setText(video.getPlayNumber()+"万");
        holder.content.setText(video.getVideoName());
        holder.singer.setText(video.getUploadMan());
    }

    @Override
    public int getItemCount() {
        return videoList.size();
    }

    class VideoViewHolder extends RecyclerView.ViewHolder{

        ImageView diagonal;
        TextView playNumber,content,time,singer;

        public VideoViewHolder(@NonNull View itemView) {
            super(itemView);
            diagonal = itemView.findViewById(R.id.diagonal);
            playNumber = itemView.findViewById(R.id.play_number);
            content = itemView.findViewById(R.id.content);
            time = itemView.findViewById(R.id.time);
            singer = itemView.findViewById(R.id.singer);
        }
    }
}
