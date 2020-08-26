package com.gxuwz.beethoven.page.index.myview.collect.anchor;

import android.content.Context;
import android.view.View;

import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.gxuwz.beethoven.R;
import com.gxuwz.beethoven.adapter.my.collect.anchor.AnchorAdapter;
import com.gxuwz.beethoven.adapter.my.collect.anchor.AnchorRecommendAdapter;
import com.gxuwz.beethoven.model.entity.my.local.anchor.Anchor;

import java.util.ArrayList;
import java.util.List;

public class AnchorViewInit {

    View anchorView;
    RecyclerView anchorRv,anchorRecommendRv;
    List<Anchor> anchorList,anchorRecommendList;

    public AnchorViewInit(View anchorView) {
        this.anchorView = anchorView;
    }

    public void init(Context context){
        findByIdAndNew(context);
    }

    public void setData(){
        Anchor anchor = new Anchor();
        anchor.setPicPer("youth");
        anchor.setTitle("文婷_Amy");
        anchor.setContent("每晚10:00-5:20治愈系助眠电台");
        anchorList.add(anchor);

        anchor = new Anchor();
        anchor.setPicPer("youth");
        anchor.setTitle("文婷_Amy");
        anchor.setContent("每晚10:00-5:20治愈系助眠电台");
        anchorList.add(anchor);

        anchor = new Anchor();
        anchor.setPicPer("youth");
        anchor.setTitle("文婷_Amy");
        anchor.setContent("每晚10:00-5:20治愈系助眠电台");
        anchorList.add(anchor);

        /**
         * 更多推荐
         */
        anchor = new Anchor();
        anchor.setDiagonal("youth");
        anchor.setPicPer("zhoushen");
        anchor.setAnchorType("视频");
        anchor.setHeat(14);
        anchor.setTitle("铁齿牙纪大烟袋");
        anchor.setUsername("周日月");
        anchorRecommendList.add(anchor);

        anchor = new Anchor();
        anchor.setDiagonal("youth");
        anchor.setPicPer("zhoushen");
        anchor.setAnchorType("视频");
        anchor.setHeat(14);
        anchor.setTitle("铁齿牙纪大烟袋");
        anchor.setUsername("周日月");
        anchorRecommendList.add(anchor);

        anchor = new Anchor();
        anchor.setDiagonal("youth");
        anchor.setPicPer("zhoushen");
        anchor.setAnchorType("视频");
        anchor.setHeat(14);
        anchor.setTitle("铁齿牙纪大烟袋");
        anchor.setUsername("周日月");
        anchorRecommendList.add(anchor);

        anchor = new Anchor();
        anchor.setDiagonal("youth");
        anchor.setPicPer("zhoushen");
        anchor.setAnchorType("视频");
        anchor.setHeat(14);
        anchor.setTitle("铁齿牙纪大烟袋");
        anchor.setUsername("周日月");
        anchorRecommendList.add(anchor);
    }

    public void findByIdAndNew(Context context){
        anchorRv = anchorView.findViewById(R.id.anchor_rv);
        anchorRecommendRv = anchorView.findViewById(R.id.anchor_recommend_rv);
        anchorList = new ArrayList<Anchor>();
        anchorRecommendList = new ArrayList<Anchor>();

        setData();
        anchorRv.setLayoutManager(new StaggeredGridLayoutManager(1,StaggeredGridLayoutManager.VERTICAL));
        anchorRv.setAdapter(new AnchorAdapter(context,anchorList));

        anchorRecommendRv.setLayoutManager(new StaggeredGridLayoutManager(1,StaggeredGridLayoutManager.VERTICAL));
        anchorRecommendRv.setAdapter(new AnchorRecommendAdapter(context,anchorRecommendList));
    }

}
