package com.gxuwz.beethoven.page.fragment.my.localmusic.album;

import android.content.Context;
import android.view.View;

import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.gxuwz.beethoven.R;
import com.gxuwz.beethoven.adapter.my.localmusic.album.AlbumItemAdapter;
import com.gxuwz.beethoven.model.entity.my.local.album.Album;

import java.util.ArrayList;
import java.util.List;

public class AlbumViewInit {
    View albumView;
    RecyclerView albumRv;
    List<Album> albumList;

    public AlbumViewInit(View albumView) {
        this.albumView = albumView;
    }

    public void init(Context context){
        findByIdAndNew(context);
    }

    public void findByIdAndNew(Context context){
        albumRv = albumView.findViewById(R.id.album_rv);
        albumList = new ArrayList<Album>();
        setData();
        albumRv.setLayoutManager(new StaggeredGridLayoutManager(1,StaggeredGridLayoutManager.VERTICAL));
        albumRv.setAdapter(new AlbumItemAdapter(context,albumList));
    }

    public void setData(){
        Album album = new Album();
        album.setAlbumDiagonal("zhoushen");
        album.setAlbumTitle("2011 Hit Fm 年度百首单曲");
        album.setCount(10);
        album.setSingerName("林俊杰");
        albumList.add(album);

        album = new Album();
        album.setAlbumDiagonal("zhoushen");
        album.setAlbumTitle("2011 Hit Fm 年度百首单曲");
        album.setCount(10);
        album.setSingerName("林俊杰");
        albumList.add(album);

        album = new Album();
        album.setAlbumDiagonal("zhoushen");
        album.setAlbumTitle("2011 Hit Fm 年度百首单曲");
        album.setCount(10);
        album.setSingerName("林俊杰");
        albumList.add(album);

        album = new Album();
        album.setAlbumDiagonal("zhoushen");
        album.setAlbumTitle("2011 Hit Fm 年度百首单曲");
        album.setCount(10);
        album.setSingerName("林俊杰");
        albumList.add(album);
    }
}
