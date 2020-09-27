package com.gxuwz.beethoven.adapter.playlist.currentplaylist;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.gxuwz.beethoven.R;
import com.gxuwz.beethoven.dao.PlayListDao;
import com.gxuwz.beethoven.model.entity.PlayList;
import com.gxuwz.beethoven.receiver.IndexBottomBarReceiver;
import com.gxuwz.beethoven.util.Player;

import java.util.List;

public class CPLAdapter extends RecyclerView.Adapter<CPLAdapter.CPLViewHolder> {

    Context context;
    List<PlayList> playLists;
    PlayListDao playListDao;

    public CPLAdapter(Context context, List<PlayList> playLists) {
        this.context = context;
        this.playLists = playLists;
        this.playListDao = new PlayListDao(context);
    }

    @NonNull
    @Override
    public CPLViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new CPLViewHolder(LayoutInflater.from(context).inflate(R.layout.current_pl_item,null));
    }

    @Override
    public void onBindViewHolder(@NonNull CPLViewHolder holder, int position) {
        PlayList playList = playLists.get(position);
        holder.songName.setText(playList.getSongName());
        holder.singerName.setText(playList.getSingerName());
        holder.fork.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playListDao.delete(playList.getId());
                notifyItemRemoved(position);
                playLists.remove(position);
            }
        });
        holder.positionItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Player.saveSharedPreferences(context.getSharedPreferences("data",Context.MODE_PRIVATE),playList);
                Player.playCurrent(context);
                IndexBottomBarReceiver.sendBroadcast(IndexBottomBarReceiver.FLAT_PLAY,context);
            }
        });
    }

    @Override
    public int getItemCount() {
        return playLists.size();
    }

    class CPLViewHolder extends RecyclerView.ViewHolder{

        LinearLayout positionItem;
        TextView songName,singerName;
        ImageView fork;

        public CPLViewHolder(@NonNull View itemView) {
            super(itemView);
            positionItem = itemView.findViewById(R.id.position_item);
            fork = itemView.findViewById(R.id.fork);
            songName = itemView.findViewById(R.id.song_name);
            singerName = itemView.findViewById(R.id.singer_name);
        }
    }
}
