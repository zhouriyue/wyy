package com.gxuwz.beethoven.page.fragment.playlistview;

import android.content.Context;
import android.view.View;

import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.gxuwz.beethoven.R;
import com.gxuwz.beethoven.adapter.my.LatePlayAdapter;
import com.gxuwz.beethoven.adapter.playlist.currentplaylist.CPLAdapter;
import com.gxuwz.beethoven.dao.LatePlayDao;
import com.gxuwz.beethoven.dao.PlayListDao;
import com.gxuwz.beethoven.model.entity.LatePlay;
import com.gxuwz.beethoven.model.entity.current.PlayList;

import java.util.ArrayList;
import java.util.List;

public class LatePlayView {

    Context context;

    View latePlayView;

    RecyclerView latePlayRV;
    LatePlayAdapter latePlayAdapter;

    List<LatePlay> latePlays;
    LatePlayDao latePlayDao;

    public LatePlayView(Context context, View latePlayView) {
        this.context = context;
        this.latePlayView = latePlayView;
        this.latePlayDao = new LatePlayDao(context);
        this.latePlays = new ArrayList<LatePlay>();
    }

    public void init(){
        latePlayRV = latePlayView.findViewById(R.id.last_pl_rv);
        latePlayRV.setLayoutManager(new StaggeredGridLayoutManager(1,StaggeredGridLayoutManager.VERTICAL));
        List<LatePlay> latePlays = latePlayDao.findAll();
        if(latePlayAdapter==null) {
            latePlayAdapter = new LatePlayAdapter(context,latePlays);
            latePlayRV.setAdapter(latePlayAdapter);
        } else {
            latePlayRV.removeAllViews();
            latePlayAdapter.updateView(latePlays);
        }
    }
}
