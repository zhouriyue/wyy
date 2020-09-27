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
import com.gxuwz.beethoven.model.entity.find.Telecast;
import com.gxuwz.beethoven.page.index.findview.telecastroom.TelecastRoomActivity;
import com.gxuwz.beethoven.util.HttpUtil;
import com.gxuwz.beethoven.util.MergeImage;

import java.util.List;

public class TelecastAdapter extends RecyclerView.Adapter<TelecastAdapter.TelecastViewHolder> {

    Context context;
    List<Telecast> telecastList;
    protected boolean isScrolling = false;

    public TelecastAdapter(Context context, List<Telecast> telecastList) {
        this.context = context;
        this.telecastList = telecastList;
    }

    @NonNull
    @Override
    public TelecastViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new TelecastViewHolder(LayoutInflater.from(context).inflate(R.layout.item_rectelecast,null));
    }

    @Override
    public void onBindViewHolder(@NonNull TelecastViewHolder holder, int position) {
        Telecast telecast = telecastList.get(position);
        holder.image.setImageBitmap(MergeImage.roundedCustomDB(HttpUtil.getRes(telecast.getImg(),context),100,100));
        holder.title.setText(telecast.getTitle());
        holder.type.setText(telecast.getType());
        holder.onlineNumber.setText(telecast.getOnlineNumber()+"");
        holder.telecastLin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context,TelecastRoomActivity.class);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return telecastList.size();
    }

    class TelecastViewHolder extends RecyclerView.ViewHolder{

        LinearLayout telecastLin;
        ImageView image;
        TextView onlineNumber,type,title;

        public TelecastViewHolder(@NonNull View itemView) {
            super(itemView);
            telecastLin = itemView.findViewById(R.id.telecast_lin);
            image = itemView.findViewById(R.id.image);
            onlineNumber = itemView.findViewById(R.id.online_number);
            type = itemView.findViewById(R.id.type);
            title = itemView.findViewById(R.id.title);
        }
    }

    public boolean isScrolling() {
        return isScrolling;
    }

    public void setScrolling(boolean scrolling) {
        isScrolling = scrolling;
    }
}
