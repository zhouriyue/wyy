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
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.gxuwz.beethoven.R;
import com.gxuwz.beethoven.model.entity.find.Room;
import com.gxuwz.beethoven.page.index.findview.chatroom.ChatRoomActivity;
import com.gxuwz.beethoven.util.MergeImage;

import java.util.List;

public class RoomAdapter extends RecyclerView.Adapter<RoomAdapter.RoomViewHolder> {

    Context context;
    List<Room> roomList;
    protected boolean isScrolling = false;

    public RoomAdapter(Context context, List<Room> roomList) {
        this.context = context;
        this.roomList = roomList;
    }

    @NonNull
    @Override
    public RoomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new RoomViewHolder(LayoutInflater.from(context).inflate(R.layout.item_room,null));
    }

    @Override
    public void onBindViewHolder(@NonNull RoomViewHolder holder, int position) {
        Room room = roomList.get(position);
        MergeImage.showGlideImgDb(context,R.drawable.youth,holder.roomImg,10);
        holder.type.setText(room.getType());
        holder.title.setText(room.getTitle());
        LinearLayoutManager lm = new LinearLayoutManager(context);
        lm.setSmoothScrollbarEnabled(false);
        lm.setOrientation(RecyclerView.HORIZONTAL);
        holder.roomMan.setLayoutManager(lm);
        holder.roomMan.setNestedScrollingEnabled(false);
        holder.roomMan.setAdapter(new RoomManAdapter(context,room.getOnlineSysUser()));
        holder.roomLin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, ChatRoomActivity.class);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return roomList.size();
    }

    class  RoomViewHolder extends RecyclerView.ViewHolder{

        LinearLayout roomLin;
        ImageView roomImg;
        TextView type,title;
        RecyclerView roomMan;

        public RoomViewHolder(@NonNull View itemView) {
            super(itemView);
            roomLin = itemView.findViewById(R.id.room_lin);
            roomImg = itemView.findViewById(R.id.room_img);
            type = itemView.findViewById(R.id.type);
            roomMan = itemView.findViewById(R.id.room_man);
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
