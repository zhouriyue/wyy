package com.gxuwz.beethoven.page.index.findview.spefun.slplaza.recommendview;

import android.content.Context;
import android.view.View;

import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.gxuwz.beethoven.R;
import com.gxuwz.beethoven.adapter.find.spefun.slplaza.RecommendAdapter;
import com.gxuwz.beethoven.model.entity.SongList;

import java.util.ArrayList;
import java.util.List;

public class RecommendView {

    View recommendView;
    Context context;
    RecyclerView recommendRv,bannerMessRv;
    List<SongList> songLists;

    public RecommendView(View recommendView,Context context) {
        this.recommendView = recommendView;
        this.context = context;
        init();
    }

    public void init(){
        findByIdAndNew();
        setData();
        bannerMessRv.setLayoutManager(new StaggeredGridLayoutManager(3,StaggeredGridLayoutManager.VERTICAL));

        recommendRv.setLayoutManager(new StaggeredGridLayoutManager(3,StaggeredGridLayoutManager.VERTICAL));
        recommendRv.setAdapter(new RecommendAdapter(context,songLists));
    }

    public void findByIdAndNew(){
        bannerMessRv = recommendView.findViewById(R.id.banner_mess_rv);
        recommendRv = recommendView.findViewById(R.id.recommend_rv);
    }

    public void setData(){
        songLists = new ArrayList<SongList>();
        SongList songList = new SongList();
        songList.setSongListUrl("youth");
        songList.setPlayNumber(2000);
        songList.setSongListName("今天从《骄傲的少年》听起私人雷达，今天从《骄傲的少年》听起私人雷达");
        songLists.add(songList);

        songList = new SongList();
        songList.setSongListUrl("youth");
        songList.setPlayNumber(2000);
        songList.setSongListName("今天从《骄傲的少年》听起私人雷达，今天从《骄傲的少年》听起私人雷达");
        songLists.add(songList);

        songList = new SongList();
        songList.setSongListUrl("youth");
        songList.setPlayNumber(2000);
        songList.setSongListName("今天从《骄傲的少年》听起私人雷达，今天从《骄傲的少年》听起私人雷达");
        songLists.add(songList);

        songList = new SongList();
        songList.setSongListUrl("youth");
        songList.setPlayNumber(2000);
        songList.setSongListName("今天从《骄傲的少年》听起私人雷达，今天从《骄傲的少年》听起私人雷达");
        songLists.add(songList);

        songList = new SongList();
        songList.setSongListUrl("youth");
        songList.setPlayNumber(2000);
        songList.setSongListName("今天从《骄傲的少年》听起私人雷达，今天从《骄傲的少年》听起私人雷达");
        songLists.add(songList);

        songList = new SongList();
        songList.setSongListUrl("youth");
        songList.setPlayNumber(2000);
        songList.setSongListName("今天从《骄傲的少年》听起私人雷达，今天从《骄傲的少年》听起私人雷达");
        songLists.add(songList);

        songList = new SongList();
        songList.setSongListUrl("youth");
        songList.setPlayNumber(2000);
        songList.setSongListName("今天从《骄傲的少年》听起私人雷达，今天从《骄傲的少年》听起私人雷达");
        songLists.add(songList);
    }
}
