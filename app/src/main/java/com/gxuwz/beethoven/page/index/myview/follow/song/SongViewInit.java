package com.gxuwz.beethoven.page.index.myview.follow.song;

import android.content.Context;
import android.view.View;

import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.gxuwz.beethoven.R;
import com.gxuwz.beethoven.adapter.my.follow.SongAdapter;
import com.gxuwz.beethoven.model.entity.my.follow.Follow;
import com.gxuwz.beethoven.model.entity.my.follow.Singer;
import com.gxuwz.beethoven.model.entity.my.follow.Song;

import java.util.ArrayList;
import java.util.List;

public class SongViewInit {

    View songView;
    RecyclerView songRv;
    List<Follow> followList;

    public SongViewInit(View songView) {
        this.songView = songView;
    }

    public void init(Context context){
        findByIdAndNew(context);
    }

    public void findByIdAndNew(Context context){
        songRv = songView.findViewById(R.id.song_rv);
        followList = new ArrayList<Follow>();
        setData();
        songRv.setLayoutManager(new StaggeredGridLayoutManager(1,StaggeredGridLayoutManager.VERTICAL));
        songRv.setAdapter(new SongAdapter(context,followList));
    }

    private void setData(){
        //1
        Follow follow = new Follow();
        follow.setDetail("8月4日上线了新专辑《新手驾到》");
        Singer singer = new Singer();
        singer.setPerPic("youth");
        singer.setSingerName("周深");
        follow.setSinger(singer);
        List<Song> songList = new ArrayList<Song>();
        Song song = new Song();
        song.setImg("youth");
        song.setName("新手驾到");
        song.setDetail("（综艺 《新手驾到》 同名的综艺）2.新手驾到（综艺 《新手驾到》 同名的综艺）");
        song.setSingles("戚薇/吴宣仪/周深/Lil Ghost小鬼/霍尊/戚薇/吴宣仪/周深/Lil Ghost小鬼/霍尊");
        songList.add(song);
        song = new Song();
        song.setImg("youth");
        song.setName("新手驾到（伴奏）");
        song.setDetail("");
        song.setSingles("戚薇/吴宣仪/周深/Lil Ghost小鬼/霍尊/戚薇/吴宣仪/周深/Lil Ghost小鬼/霍尊");
        songList.add(song);
        follow.setSong(songList);
        followList.add(follow);
        //2
        follow = new Follow();
        follow.setDetail("8月4日上线了新专辑《新手驾到》");
        singer = new Singer();
        singer.setPerPic("youth");
        singer.setSingerName("周深");
        follow.setSinger(singer);
        songList = new ArrayList<Song>();
        song = new Song();
        song.setImg("youth");
        song.setName("新手驾到");
        song.setDetail("（综艺 《新手驾到》 同名的综艺）2.新手驾到（综艺 《新手驾到》 同名的综艺）");
        song.setSingles("戚薇/吴宣仪/周深/Lil Ghost小鬼/霍尊/戚薇/吴宣仪/周深/Lil Ghost小鬼/霍尊");
        songList.add(song);
        song = new Song();
        song.setImg("youth");
        song.setName("新手驾到（伴奏）");
        song.setDetail("");
        song.setSingles("戚薇/吴宣仪/周深/Lil Ghost小鬼/霍尊/戚薇/吴宣仪/周深/Lil Ghost小鬼/霍尊");
        songList.add(song);
        follow.setSong(songList);
        followList.add(follow);
        //3
        follow = new Follow();
        follow.setDetail("8月4日上线了新专辑《新手驾到》");
        singer = new Singer();
        singer.setPerPic("youth");
        singer.setSingerName("周深");
        follow.setSinger(singer);
        songList = new ArrayList<Song>();
        song = new Song();
        song.setImg("youth");
        song.setName("新手驾到");
        song.setDetail("（综艺 《新手驾到》 同名的综艺）2.新手驾到（综艺 《新手驾到》 同名的综艺）");
        song.setSingles("戚薇/吴宣仪/周深/Lil Ghost小鬼/霍尊/戚薇/吴宣仪/周深/Lil Ghost小鬼/霍尊");
        songList.add(song);
        song = new Song();
        song.setImg("youth");
        song.setName("新手驾到（伴奏）");
        song.setDetail("");
        song.setSingles("戚薇/吴宣仪/周深/Lil Ghost小鬼/霍尊/戚薇/吴宣仪/周深/Lil Ghost小鬼/霍尊");
        songList.add(song);
        follow.setSong(songList);
        followList.add(follow);
    }

}
