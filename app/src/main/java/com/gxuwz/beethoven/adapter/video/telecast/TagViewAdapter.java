package com.gxuwz.beethoven.adapter.video.telecast;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.gxuwz.beethoven.R;
import com.gxuwz.beethoven.model.entity.video.TagVideo;
import com.gxuwz.beethoven.util.HttpUtils;
import com.gxuwz.beethoven.util.MergeImage;
import com.gxuwz.beethoven.util.WindowPixels;

import java.util.List;

public class TagViewAdapter extends RecyclerView.Adapter<TagViewAdapter.TagViewViewHolder>{

    Context context;
    List<TagVideo> tagVideoList;

    WindowPixels windowPixels;

    int itemWidth;
    int height;

    public TagViewAdapter(Context context, List<TagVideo> tagVideoList, WindowManager windowManager) {
        this.context = context;
        this.tagVideoList = tagVideoList;
        windowPixels = new WindowPixels(windowManager);
        itemWidth = windowPixels.getScreenWidth()/2;
        height = (int) (itemWidth*1.2);
    }

    @NonNull
    @Override
    public TagViewViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new TagViewViewHolder(LayoutInflater.from(context).inflate(R.layout.subpage_telecast_tagview_item,null));
    }

    @Override
    public void onBindViewHolder(@NonNull TagViewViewHolder holder, int position) {
        TagVideo tagVideo = tagVideoList.get(position);
        holder.diagonal.setImageBitmap(MergeImage.roundedCustomDB(HttpUtils.getRes(tagVideo.getDiagonal(),context),itemWidth, height));
        holder.playNumber.setText(tagVideo.getPlayNumber()+"");
        holder.type.setText(tagVideo.getType());
        holder.time.setText(tagVideo.getTime());
        holder.username.setText("周日月");
        holder.title.setText(tagVideo.getTitile());
    }

    @Override
    public int getItemCount() {
        return tagVideoList.size();
    }

    public class TagViewViewHolder extends RecyclerView.ViewHolder{

        ImageView diagonal;
        TextView time,type,playNumber,title,username;
        RelativeLayout relativeLayout;

        public TagViewViewHolder(@NonNull View itemView) {
            super(itemView);
            diagonal = itemView.findViewById(R.id.diagonal);
            time = itemView.findViewById(R.id.time);
            type = itemView.findViewById(R.id.type);
            playNumber = itemView.findViewById(R.id.play_number);
            title = itemView.findViewById(R.id.title);
            username = itemView.findViewById(R.id.username);
            relativeLayout = itemView.findViewById(R.id.rl);
        }
    }
}
