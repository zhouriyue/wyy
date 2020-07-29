package com.gxuwz.beethoven.adapter.my;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.gxuwz.beethoven.R;
import com.gxuwz.beethoven.model.entity.Music;
import com.gxuwz.beethoven.receiver.IndexBottomBarReceiver;
import com.gxuwz.beethoven.service.MusicService;
import com.gxuwz.beethoven.util.HttpUtil;

import java.util.List;

public class LocalMusicAdapter extends RecyclerView.Adapter<LocalMusicAdapter.LocalMusicViewHolder> {
    private Context context;
    List<Music> musicList;
    private int backItem = -1;
    ImageView ptTagBack;

    public LocalMusicAdapter(Context context, List<Music> musicList) {
        this.context = context;
        this.musicList = musicList;
    }

    @NonNull
    @Override
    public LocalMusicAdapter.LocalMusicViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new LocalMusicAdapter.LocalMusicViewHolder(LayoutInflater.from(context).inflate(R.layout.activity_local_music_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull LocalMusicAdapter.LocalMusicViewHolder holder, int position) {
        Music music = musicList.get(position);
        holder.position.setText((position+1)+"");
        holder.musicName.setText(music.getMusicName());
        holder.nameAndSinger.setText(music.getSinger()+"-"+music.getMusicName());
        if(MusicService.position==position&&MusicService.isRun) {
            if(MusicService.ptTagBack!=null) {
                System.out.println(MusicService.position+"="+position+","+MusicService.isRun+","+MusicService.ptTagBack);
                MusicService.ptTagBack.setBackgroundResource(0);
                MusicService.ptTagBack.setImageBitmap(HttpUtil.getRes("stop1",context));
            }
        }
        holder.relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(MusicService.ptTagBack!=null) {
                    MusicService.ptTagBack.setImageBitmap(HttpUtil.getRes("play3",context));
                }
                holder.ptTag.setBackgroundResource(0);
                holder.ptTag.setImageBitmap(HttpUtil.getRes("stop1",context));
                Intent intent = new Intent("CTL_ACTION");
                intent.putExtra("controller",2);
                intent.putExtra("songUrl",music.getSongUrl());
                intent.putExtra("position",position);
                MusicService.ptTagBack = holder.ptTag;
                MusicService.position = position;
                MusicService.isRun = true;
                context.sendBroadcast(intent);

                IndexBottomBarReceiver.sendBroadcast(IndexBottomBarReceiver.FLAT_PLAY,context);
            }
        });
    }

    @Override
    public int getItemCount() {
        return musicList.size();
    }

    class LocalMusicViewHolder extends RecyclerView.ViewHolder {

        private TextView position;
        private TextView musicName;
        private TextView nameAndSinger;
        private RelativeLayout relativeLayout;
        private ImageView ptTag;

        public LocalMusicViewHolder(@NonNull View itemView) {
            super(itemView);
            position = itemView.findViewById(R.id.position);
            musicName = itemView.findViewById(R.id.music_name);
            nameAndSinger = itemView.findViewById(R.id.music_singer_name);
            relativeLayout = itemView.findViewById(R.id.id);
            ptTag = itemView.findViewById(R.id.pt_tag);
        }
    }

    public int getBackItem() {
        return backItem;
    }

    public void setBackItem(int backItem) {
        this.backItem = backItem;
    }
}
