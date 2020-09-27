package com.gxuwz.beethoven.page.index.playlistview;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.util.Log;
import android.view.View;

import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.gxuwz.beethoven.R;
import com.gxuwz.beethoven.adapter.playlist.currentplaylist.CPLAdapter;
import com.gxuwz.beethoven.dao.PlayListDao;
import com.gxuwz.beethoven.model.entity.PlayList;
import com.gxuwz.beethoven.model.entity.Song;

import java.util.ArrayList;
import java.util.List;

public class CurrentPlayView {

    Context context;

    View currentView;

    RecyclerView currentPlayRV;
    CPLAdapter cplAdapter;

    List<PlayList> playLists;
    PlayListDao playListDao;

    public CurrentPlayView(Context context, View currentView) {
        this.context = context;
        this.currentView = currentView;
        this.playListDao = new PlayListDao(context);
        this.playLists = new ArrayList<PlayList>();
        setData();
        this.cplAdapter = new CPLAdapter(context,playLists);
        init();
    }

    public void init(){
        currentPlayRV = currentView.findViewById(R.id.current_pl_rv);
        currentPlayRV.setLayoutManager(new StaggeredGridLayoutManager(1,StaggeredGridLayoutManager.VERTICAL));
        currentPlayRV.setAdapter(cplAdapter);
        FreshPlayListReceiver freshPlayListReceiver = new FreshPlayListReceiver();
        IntentFilter intentFilter = new IntentFilter(FreshPlayListReceiver.ACTION);
        context.registerReceiver(freshPlayListReceiver,intentFilter);
    }

    public void setData(){
        playLists = playListDao.findAll();
    }

    public class FreshPlayListReceiver extends BroadcastReceiver{

        public final static String ACTION = "fresh_play_list";

        @Override
        public void onReceive(Context context, Intent intent) {
            PlayList playList = (PlayList) intent.getSerializableExtra("playList");
            playLists.add(playList);
            cplAdapter.notifyItemChanged(playLists.size()-1,playList);
        }
    }
}
