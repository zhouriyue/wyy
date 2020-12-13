package com.gxuwz.beethoven.adapter.find;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.gxuwz.beethoven.R;
import com.gxuwz.beethoven.model.entity.mlog.ImageWordMlog;
import com.gxuwz.beethoven.model.entity.mlog.Mlog;
import com.gxuwz.beethoven.model.entity.mlog.VideoMlog;
import com.gxuwz.beethoven.page.index.findview.imgtexttalk.ITTalkActivity;
import com.gxuwz.beethoven.util.MergeImage;

import java.util.List;

public class MLogAdapter extends RecyclerView.Adapter<MLogAdapter.MLogViewHolder> {

    Context context;
    List<Mlog> mlogList;
    protected boolean isScrolling = false;

    public MLogAdapter(Context context, List<Mlog> mlogList) {
        this.context = context;
        this.mlogList = mlogList;
    }

    @NonNull
    @Override
    public MLogViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MLogViewHolder(LayoutInflater.from(context).inflate(R.layout.item_mlog,null));
    }

    @Override
    public void onBindViewHolder(@NonNull MLogViewHolder holder, int position) {
        if("ImageWordMlog".equals(mlogList.get(position).getType())){
            ImageWordMlog imageWordMlog = (ImageWordMlog) mlogList.get(position);
            MergeImage.showGlideImgDb(context,R.drawable.youth,holder.diagonal,10);
            holder.title.setText(imageWordMlog.getTitle());
            holder.likeNumber.setText(imageWordMlog.getLikeNumber()+"赞");
        } else if("VideoMlog".equals(mlogList.get(position).getType())) {
            VideoMlog videoMlog = (VideoMlog) mlogList.get(position);
            MergeImage.showGlideImgDb(context,R.drawable.youth,holder.diagonal,10);
            holder.title.setText(videoMlog.getTitle());
            holder.likeNumber.setText(videoMlog.getLikeNumber()+"赞");
        }
        holder.forwardLin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, ITTalkActivity.class);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mlogList.size();
    }

    class MLogViewHolder extends RecyclerView.ViewHolder{

        ImageView diagonal;
        TextView title,likeNumber;
        LinearLayout forwardLin;

        public MLogViewHolder(@NonNull View itemView) {
            super(itemView);
            diagonal = itemView.findViewById(R.id.diagonal);
            title = itemView.findViewById(R.id.title);
            likeNumber = itemView.findViewById(R.id.like_number);
            forwardLin = itemView.findViewById(R.id.forward_lin);
        }
    }

    public boolean isScrolling() {
        return isScrolling;
    }

    public void setScrolling(boolean scrolling) {
        isScrolling = scrolling;
    }
}
