package com.gxuwz.beethoven.adapter.find.telecastroom;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.gxuwz.beethoven.R;
import com.gxuwz.beethoven.model.entity.SysUser;
import com.gxuwz.beethoven.model.entity.find.spefun.telecast.TrMessage;
import com.gxuwz.beethoven.util.HttpUtils;
import com.gxuwz.beethoven.util.MergeImage;

import java.util.List;

public class MessageAdapter extends RecyclerView.Adapter<MessageAdapter.MessageViewHolder> {

    Context context;
    List<TrMessage> trMessageList;

    public MessageAdapter(Context context, List<TrMessage> trMessageList) {
        this.context = context;
        this.trMessageList = trMessageList;
    }

    @NonNull
    @Override
    public MessageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MessageViewHolder(LayoutInflater.from(context).inflate(R.layout.tr_mess_item,null));
    }

    @Override
    public void onBindViewHolder(@NonNull MessageViewHolder holder, int position) {
        TrMessage trMessage = trMessageList.get(position);
        SysUser sysUser = trMessage.getSysUser();
        holder.perPic.setImageBitmap(MergeImage.circleShow(HttpUtils.getRes(sysUser.getPerPic(),context)));
        holder.content.setText(trMessage.getContent());
        holder.position.setText(position+"");
        holder.username.setText(sysUser.getUserName());
    }

    @Override
    public int getItemCount() {
        return trMessageList.size();
    }

    class MessageViewHolder extends RecyclerView.ViewHolder{

        ImageView perPic;
        TextView username,position,content;

        public MessageViewHolder(@NonNull View itemView) {
            super(itemView);
            perPic = itemView.findViewById(R.id.per_pic);
            username = itemView.findViewById(R.id.username);
            position = itemView.findViewById(R.id.position);
            content = itemView.findViewById(R.id.content);
        }
    }
}
