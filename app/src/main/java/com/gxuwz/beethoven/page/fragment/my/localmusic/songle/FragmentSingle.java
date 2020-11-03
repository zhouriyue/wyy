package com.gxuwz.beethoven.page.fragment.my.localmusic.songle;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;
import com.gxuwz.beethoven.R;
import com.gxuwz.beethoven.adapter.my.LocalMusicAdapter;
import com.gxuwz.beethoven.dao.LocalSongDao;
import com.gxuwz.beethoven.model.entity.Music;
import com.gxuwz.beethoven.model.entity.current.LocalSong;
import com.gxuwz.beethoven.page.fragment.my.songlist.LoadDownPopuWindow;
import com.gxuwz.beethoven.util.MusicUtils;

import java.util.ArrayList;
import java.util.List;

public class FragmentSingle extends Fragment {
    LoadDownPopuWindow loadDownPopuWindow;
    SharedPreferences sharedPreferences;
    RecyclerView songListMusic;
    LocalSongDao localSongDao;
    List<Music> list;
    List<LocalSong> localSongs;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.local_music_single,null);
        init(view);
        return view;
    }

    public void init(View view){
        findViewByIdAll(view);
        initData(view);
        initLocalSongList(view);
    }

    public void initLocalSongList(View view){
        View moreView = getLayoutInflater().inflate(R.layout.sl_loaddown_popupwindow,null);
        loadDownPopuWindow = new LoadDownPopuWindow(getContext(),moreView);
        songListMusic.setLayoutManager(new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL));
        songListMusic.setAdapter(new LocalMusicAdapter(getContext(),localSongs,sharedPreferences,loadDownPopuWindow));
    }

    public void initData(View view){
        list = MusicUtils.getMusicData(getContext());
        sharedPreferences = getContext().getSharedPreferences("data", Context.MODE_PRIVATE);
        localSongDao = new LocalSongDao(getContext());
        for(int i = 0;i < list.size();i++) {
            if(!localSongDao.isLocalUrl(list.get(i).getSongUrl())) {
                localSongDao.insert(list.get(i));
            }
        }
        localSongs = localSongDao.findAll();
    }

    public void findViewByIdAll(View view){
        songListMusic = view.findViewById(R.id.single_rv);
    }
}
