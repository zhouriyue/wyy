package com.gxuwz.beethoven.adapter.my;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.gxuwz.beethoven.R;
import com.gxuwz.beethoven.dao.LocalSongDao;
import com.gxuwz.beethoven.dao.SingerDao;
import com.gxuwz.beethoven.dao.SongDao;
import com.gxuwz.beethoven.model.entity.LatePlay;
import com.gxuwz.beethoven.model.entity.current.LocalSong;
import com.gxuwz.beethoven.model.entity.current.Singer;
import com.gxuwz.beethoven.model.entity.current.Song;
import com.gxuwz.beethoven.util.DateUtil;

import java.util.Date;
import java.util.List;

public class LatePlayAdapter extends RecyclerView.Adapter<LatePlayAdapter.LatePlayViewHolder> {

    Context context;
    List<LatePlay> latePlayList;
    SongDao songDao;
    LocalSongDao localSongDao;
    SingerDao singerDao;

    public LatePlayAdapter(Context context, List<LatePlay> latePlayList) {
        this.context = context;
        this.latePlayList = latePlayList;
        songDao = new SongDao(context);
        localSongDao = new LocalSongDao(context);
        singerDao = new SingerDao(context);
    }

    @NonNull
    @Override
    public LatePlayViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new LatePlayViewHolder(LayoutInflater.from(context).inflate(R.layout.item_late,null));
    }

    @Override
    public void onBindViewHolder(@NonNull LatePlayViewHolder holder, int position) {
        LatePlay latePlay = latePlayList.get(position);
        String singerName = "";
        Song song;
        if(latePlay.getSlId()!=-1){
            song = songDao.findById(latePlay.getSongId());
            List<Singer> singerList = singerDao.findSongSiner(song.getSongId());
            if(singerList.size()>1) singerName += singerList.get(0).getSinName();
            for(int i = 1;i < singerList.size();i++) {
                singerName += "/" + singerList.get(i).getSinName();
            }
            switch (song.getTimbreType()){
                case 1:{
                    holder.timbreType.setText("HQ");
                };break;
                case 2:{
                    holder.timbreType.setText("HQ");
                };break;
            }
            holder.timTLin.setVisibility(View.VISIBLE);
            long count = DateUtil.computeDay(latePlay.getPlayDate(),new Date());
            if(count/60==0){
                holder.lateDate.setText(count%60+"分钟前");
            } else {
                holder.lateDate.setText(count/60+"小时前");
            }
            holder.songName.setText(song.getSongName());
            holder.singerName.setText(singerName);
        } else {
            LocalSong localSong = localSongDao.findBySongId(latePlay.getSongId());
            if(localSong!=null) {
                long count = DateUtil.computeMin(latePlay.getPlayDate(),new Date());
                if(count/60==0){
                    if(count==0){
                        holder.lateDate.setText("刚刚");
                    } else {
                        holder.lateDate.setText(count+"分钟前");
                    }
                } else {
                    holder.lateDate.setText(count/60+"小时前");
                }
                holder.timTLin.setVisibility(View.GONE);
                holder.songName.setText(localSong.getSongName()); //歌曲名
                holder.singerName.setText(localSong.getSingerName()); //歌手
            }
        }
    }

    @Override
    public int getItemCount() {
        return latePlayList.size();
    }

    public void updateView(List<LatePlay> latePlays) {
        notifyDataSetChanged();
        this.latePlayList = latePlays;
    }

    class LatePlayViewHolder extends RecyclerView.ViewHolder{

        LinearLayout timTLin;
        TextView songName,timbreType,singerName,lateDate;

        public LatePlayViewHolder(@NonNull View itemView) {
            super(itemView);
            timTLin = itemView.findViewById(R.id.timt_lin);
            songName = itemView.findViewById(R.id.song_name);
            timbreType = itemView.findViewById(R.id.timbre_type);
            singerName = itemView.findViewById(R.id.singer_name);
            lateDate = itemView.findViewById(R.id.late_date);
        }
    }
}
