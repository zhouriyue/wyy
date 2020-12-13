package com.gxuwz.beethoven.adapter.playlist.currentplaylist;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.gxuwz.beethoven.R;
import com.gxuwz.beethoven.dao.LocalSongDao;
import com.gxuwz.beethoven.dao.PlayListDao;
import com.gxuwz.beethoven.dao.SingerDao;
import com.gxuwz.beethoven.dao.SongDao;
import com.gxuwz.beethoven.model.entity.current.LocalSong;
import com.gxuwz.beethoven.model.entity.current.PlayList;
import com.gxuwz.beethoven.model.entity.current.Singer;
import com.gxuwz.beethoven.model.entity.current.Song;
import com.gxuwz.beethoven.receiver.IndexBottomBarReceiver;
import com.gxuwz.beethoven.service.MusicService;
import com.gxuwz.beethoven.util.Player;

import java.util.List;

public class CPLAdapter extends RecyclerView.Adapter<CPLAdapter.CPLViewHolder> {

    Context context;
    List<PlayList> playLists;
    SharedPreferences sharedPreferences;
    PlayListDao playListDao;
    SingerDao singerDao;
    SongDao songDao;
    LocalSongDao localSongDao;

    public CPLAdapter(Context context, List<PlayList> playLists) {
        this.context = context;
        this.playLists = playLists;
        this.playListDao = new PlayListDao(context);
        this.singerDao = new SingerDao(context);
        this.songDao = new SongDao(context);
        this.localSongDao = new LocalSongDao(context);
        sharedPreferences = context.getSharedPreferences("data",Context.MODE_PRIVATE);
    }

    @NonNull
    @Override
    public CPLViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new CPLViewHolder(LayoutInflater.from(context).inflate(R.layout.current_pl_item,null));
    }

    @Override
    public void onBindViewHolder(@NonNull CPLViewHolder holder, int position) {
        PlayList playList = playLists.get(position);
        if(playList.getSlId()!=-1) {
            Song song = songDao.findById(playList.getSongId());
            holder.songName.setText(song.getSongName());
            List<Singer> singerList = singerDao.findSongSiner(song.getSongId());
            String singerName = "";
            if(singerList!=null&&singerList.size()!=0) {
                singerName += singerList.get(0).getSinName();
                for(int i = 1;i < singerList.size();i++) {
                    singerName += "/"+singerList.get(i).getSinName();
                }
            }
            holder.singerName.setText(singerName);
        } else {
            LocalSong localSong = localSongDao.findBySongId(playList.getSongId());
            holder.songName.setText(localSong.getSongName());
            holder.singerName.setText(localSong.getSingerName());
        }
        holder.fork.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playListDao.delete(playList.getId());
                notifyItemRemoved(position);
                playLists.remove(position);
                notifyDataSetChanged();
            }
        });
        holder.positionItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Song song = new Song();
                song.setSlId(playList.getSlId());
                song.setSongId(playList.getSongId());
                Player.play(context,song);
            }
        });
    }

    public void updateData(List<PlayList> playLists) {
        this.playLists = playLists;
        notifyDataSetChanged();
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
