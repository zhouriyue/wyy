package com.gxuwz.beethoven.adapter.find;

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
import com.gxuwz.beethoven.dao.SingerDao;
import com.gxuwz.beethoven.dao.SongDao;
import com.gxuwz.beethoven.model.entity.current.Singer;
import com.gxuwz.beethoven.model.entity.current.Song;
import com.gxuwz.beethoven.receiver.IndexBottomBarReceiver;
import com.gxuwz.beethoven.service.MusicService;
import com.gxuwz.beethoven.util.MergeImage;
import com.gxuwz.beethoven.util.Player;
import com.gxuwz.beethoven.util.staticdata.StaticHttp;

import java.util.List;

public class SongItemAdapter extends RecyclerView.Adapter<SongItemAdapter.SongItemViewHolder> {

    Context context;
    SharedPreferences sharedPreferences;
    List<Song> songList;
    SongDao songDao;
    SingerDao singerDao;

    public SongItemAdapter(Context context, List<Song> songList) {
        this.context = context;
        this.songList = songList;
        this.sharedPreferences = context.getSharedPreferences("data",Context.MODE_PRIVATE);
        this.songDao = new SongDao(context);
        this.singerDao = new SingerDao(context);
    }

    @NonNull
    @Override
    public SongItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new SongItemViewHolder(LayoutInflater.from(context).inflate(R.layout.item_recsong_item,null));
    }

    @Override
    public void onBindViewHolder(@NonNull SongItemViewHolder holder, int position) {
        Song song = songList.get(position);
        MergeImage.showGlideImgDb(context, StaticHttp.STATIC_BASEURL+song.getCoverPicture(),holder.image,10);
        holder.detail.setText(song.getDetail());
        holder.songName.setText(song.getSongName());
        String singerName = "";
        List<Singer> singerList = song.getSingerList();
        if(singerList.size()>0) {
            singerName += singerList.get(0).getSinName();
            for(int i = 1;i < singerList.size();i++) {
                singerName += "/" + singerList.get(i).getSinName();
            }
        }
        holder.singerName.setText(singerName);
        holder.pId.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                song.setSlId(song.getSonglistes().getSlId());
                if(songDao.findById(song.getSongId())==null) {
                    songDao.insert(song);
                } else {
                    songDao.update(song);
                }
                if(!songDao.isSlToSong(song.getSlId(),song.getSongId())){
                    songDao.insertSlToSong(song.getSlId(),song.getSongId());
                }
                if(singerList!=null){
                    for(int i = 0;i < singerList.size();i++) {
                        if(singerDao.findById(singerList.get(i).getSinId())==null){
                            singerDao.insert(singerList.get(i));
                        }
                        if(!singerDao.isSongToSinger(song.getSongId(),singerList.get(i).getSinId())){
                            singerDao.insertSongToSinger(song.getSongId(),singerList.get(i).getSinId());
                        }
                    }
                }
                Player.play(context,song);
            }
        });
    }

    @Override
    public int getItemCount() {
        return songList.size();
    }

    class SongItemViewHolder extends RecyclerView.ViewHolder{

        LinearLayout pId;
        ImageView image;
        TextView songName,singerName,detail;

        public SongItemViewHolder(@NonNull View itemView) {
            super(itemView);
            pId = itemView.findViewById(R.id.p_id);
            image = itemView.findViewById(R.id.image);
            songName = itemView.findViewById(R.id.song_name);
            singerName = itemView.findViewById(R.id.singer_name);
            detail = itemView.findViewById(R.id.detail);
        }
    }
}
