package com.gxuwz.beethoven.page.index.videoview.plazaadvert;

import android.content.Context;
import android.view.View;

import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.gxuwz.beethoven.R;
import com.gxuwz.beethoven.adapter.video.plazaadvert.PlazaAdvertAdapter;
import com.gxuwz.beethoven.model.entity.video.plazaadvert.PlazaAdvert;

import java.util.ArrayList;
import java.util.List;

/**
 * 广场子页
 */
public class PlazaAdvertInit {
    View plazaAdvertView;
    RecyclerView plazaAdvertRv;
    List<PlazaAdvert> plazaAdvertList;

    public PlazaAdvertInit(View plazaAdvertView) {
        this.plazaAdvertView = plazaAdvertView;
    }

    public void init(Context context){
        findByIdAndNew(context);
        setData();
    }

    public void setData(){
        PlazaAdvert plazaAdvert = new PlazaAdvert();
        plazaAdvert.setTitle("让音乐看得见");
        plazaAdvert.setDiagonal("youth");
        plazaAdvert.setContent("投稿百万视频，赢取百万奖金！");
        plazaAdvertList.add(plazaAdvert);

        plazaAdvert = new PlazaAdvert();
        plazaAdvert.setTitle("让音乐看得见");
        plazaAdvert.setDiagonal("youth");
        plazaAdvert.setContent("投稿百万视频，赢取百万奖金！");
        plazaAdvertList.add(plazaAdvert);

        plazaAdvert = new PlazaAdvert();
        plazaAdvert.setTitle("让音乐看得见");
        plazaAdvert.setDiagonal("youth");
        plazaAdvert.setContent("投稿百万视频，赢取百万奖金！");
        plazaAdvertList.add(plazaAdvert);
    }

    public void findByIdAndNew(Context context){
        plazaAdvertRv = plazaAdvertView.findViewById(R.id.plaza_advert_rv);
        plazaAdvertList = new ArrayList<PlazaAdvert>();
        plazaAdvertRv.setLayoutManager(new StaggeredGridLayoutManager(1,StaggeredGridLayoutManager.VERTICAL));
        plazaAdvertRv.setAdapter(new PlazaAdvertAdapter(context,plazaAdvertList));
    }
}
