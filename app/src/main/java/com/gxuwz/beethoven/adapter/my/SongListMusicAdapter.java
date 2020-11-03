package com.gxuwz.beethoven.adapter.my;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.gxuwz.beethoven.R;
import com.gxuwz.beethoven.dao.SLMusicDao;
import com.gxuwz.beethoven.hanlder.MusicHandler;
import com.gxuwz.beethoven.model.entity.SongListsMusic;
import com.gxuwz.beethoven.model.entity.my.songlist.SLMore;
import com.gxuwz.beethoven.page.fragment.my.songlist.LoadDownView;
import com.gxuwz.beethoven.service.MusicService;
import com.gxuwz.beethoven.util.HttpUtil;

import java.util.List;

public class SongListMusicAdapter extends RecyclerView.Adapter<SongListMusicAdapter.SongListViewHolder> {
    private Context context;
    List<SongListsMusic> songListsMusics;
    private int backItem = -1;
    ImageView ptTagBack;
    /**
     * 获取SharedPreferences对象
     */
    SharedPreferences sharedPreferences;
    String songListUrl;

    LoadDownView loadDownView;
    SLMusicDao slMusicDao;

    public SongListMusicAdapter(Context context, List<SongListsMusic> songListsMusics,
                                SharedPreferences sharedPreferences,String songListUrl) {
        this.context = context;
        this.songListsMusics = songListsMusics;
        this.sharedPreferences = sharedPreferences;
        this.songListUrl = songListUrl;
        this.slMusicDao = new SLMusicDao(context);
    }

    @NonNull
    @Override
    public SongListMusicAdapter.SongListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new SongListMusicAdapter.SongListViewHolder(LayoutInflater.from(context).inflate(R.layout.song_list_music_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull SongListMusicAdapter.SongListViewHolder holder, int position) {
        SongListsMusic songListsMusic = songListsMusics.get(position);

        holder.position.setText((position+1)+"");
        holder.musicName.setText(songListsMusic.getMusicName());
        holder.nameAndSinger.setText(songListsMusic.getSingerName()+"-"+songListsMusic.getMusicName());
        if(MusicService.position==position&&MusicService.isRun) {
            if(MusicService.ptTagBack!=null) {
                MusicService.ptTagBack.setBackgroundResource(0);
                MusicService.ptTagBack.setImageBitmap(HttpUtil.getRes("stop1",context));
            }
        }
        holder.relativeLayout.setOnClickListener(new ItemOnClickListener(holder,songListsMusic,position));
        holder.more.setOnClickListener(new ItemMoreListener(songListsMusic.getSlMoreList()));
    }

    @Override
    public int getItemCount() {
        return songListsMusics.size();
    }

    class SongListViewHolder extends RecyclerView.ViewHolder {

        private TextView position;
        private TextView musicName;
        private TextView nameAndSinger;
        private RelativeLayout relativeLayout;
        private ImageView ptTag,more;

        public SongListViewHolder(@NonNull View itemView) {
            super(itemView);
            position = itemView.findViewById(R.id.position);
            musicName = itemView.findViewById(R.id.music_name);
            nameAndSinger = itemView.findViewById(R.id.music_singer_name);
            ptTag = itemView.findViewById(R.id.pt_tag);
            more = itemView.findViewById(R.id.more);
        }
    }

    class ItemMoreListener implements View.OnClickListener{

        List<SLMore> slMores;

        public ItemMoreListener(List<SLMore> slMores) {
            this.slMores = slMores;
        }

        @Override
        public void onClick(View v) {
            //loadDownView.initView(slMores,v,null);
        }
    }

    class ItemOnClickListener implements View.OnClickListener {

        SongListViewHolder holder;
        SongListsMusic songListsMusic;
        int position;

        public ItemOnClickListener(SongListViewHolder holder, SongListsMusic songListsMusic, int position) {
            this.holder = holder;
            this.songListsMusic = songListsMusic;
            this.position = position;
        }

        @Override
        public void onClick(View view) {
            MusicHandler musicHandler = new MusicHandler(songListsMusic,sharedPreferences,songListUrl);
            musicHandler.setContext(context);
            if(MusicService.ptTagBack!=null) {
                MusicService.ptTagBack.setImageBitmap(HttpUtil.getRes("play3",context));
            }
            holder.ptTag.setBackgroundResource(0);
            holder.ptTag.setImageBitmap(HttpUtil.getRes("stop1",context));
            MusicService.ptTagBack = holder.ptTag;
            MusicService.position = position;
            MusicService.isRun = true;
            HttpUtil.get(songListsMusics.get(position).get_links().getMusic().getHref(),musicHandler);

        }
    }

    public int getBackItem() {
        return backItem;
    }

    public void setBackItem(int backItem) {
        this.backItem = backItem;
    }

    public SharedPreferences getSharedPreferences() {
        return sharedPreferences;
    }

    public void setSharedPreferences(SharedPreferences sharedPreferences) {
        this.sharedPreferences = sharedPreferences;
    }

    public SongListMusicAdapter(String songListUrl) {
        this.songListUrl = songListUrl;
    }

    public LoadDownView getLoadDownView() {
        return loadDownView;
    }

    public void setLoadDownView(LoadDownView loadDownView) {
        this.loadDownView = loadDownView;
    }
}
