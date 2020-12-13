package com.gxuwz.beethoven.adapter.find.ittalk;

import android.content.Context;
import android.graphics.Color;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.gxuwz.beethoven.R;
import com.gxuwz.beethoven.model.entity.find.talk.Comment;
import com.gxuwz.beethoven.util.HttpUtils;
import com.gxuwz.beethoven.util.MergeImage;

import java.util.List;


public class CommentAdapter extends RecyclerView.Adapter<CommentAdapter.CommentViewHolder> {

    Context context;
    List<Comment> commentList;

    public CommentAdapter(Context context, List<Comment> commentList) {
        this.context = context;
        this.commentList = commentList;
    }

    @NonNull
    @Override
    public CommentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new CommentViewHolder(LayoutInflater.from(context).inflate(R.layout.ittalk_comment,null));
    }

    @Override
    public void onBindViewHolder(@NonNull CommentViewHolder holder, int position) {
        Comment comment = commentList.get(position);
        holder.perPic.setImageBitmap(MergeImage.circleShow(HttpUtils.getRes(comment.getSysUser().getPerPic(),context)));
        String content = comment.getSysUser().getUserName()+":"+comment.getContent();
        SpannableString spannableString = new SpannableString(content);
        //设置文字颜色
        ForegroundColorSpan foregroundColorSpan = new ForegroundColorSpan(Color.parseColor("#2c2c2c"));
        spannableString.setSpan(foregroundColorSpan, 0, comment.getSysUser().getUserName().length()+1, Spannable.SPAN_INCLUSIVE_INCLUSIVE);
        holder.usernameContent.setText(spannableString);
    }

    @Override
    public int getItemCount() {
        return commentList.size();
    }

    class CommentViewHolder extends RecyclerView.ViewHolder{

        ImageView perPic;
        TextView usernameContent;

        public CommentViewHolder(@NonNull View itemView) {
            super(itemView);
            perPic = itemView.findViewById(R.id.per_pic);
            usernameContent = itemView.findViewById(R.id.username_content);
        }
    }
}
