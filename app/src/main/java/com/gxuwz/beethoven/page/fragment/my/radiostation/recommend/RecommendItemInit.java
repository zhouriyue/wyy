package com.gxuwz.beethoven.page.fragment.my.radiostation.recommend;

import android.content.Context;

import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.gxuwz.beethoven.adapter.my.radiostation.RecommendAdapter;
import com.gxuwz.beethoven.model.entity.my.radiostation.RecommendItem;

import java.util.ArrayList;
import java.util.List;

public class RecommendItemInit {

    RecyclerView radioStationRv;
    List<RecommendItem> recommendItemList;

    public RecommendItemInit(RecyclerView radioStationRv){
        this.radioStationRv = radioStationRv;
    }

    public void init(Context context){
        findByIdAndNew(context);
    }

    public void findByIdAndNew(Context context){
        recommendItemList = new ArrayList<RecommendItem>();
        setData();
        radioStationRv.setLayoutManager(new StaggeredGridLayoutManager(3,StaggeredGridLayoutManager.VERTICAL));
        radioStationRv.setAdapter(new RecommendAdapter(context,recommendItemList));
    }

    private void setData(){
        RecommendItem recommendItem = new RecommendItem();
        recommendItem.setDiagonal("youth");
        recommendItem.setContent("每晚睡前，原谅所有的人和事。");
        recommendItemList.add(recommendItem);

        recommendItem = new RecommendItem();
        recommendItem.setDiagonal("youth");
        recommendItem.setContent("每晚睡前，原谅所有的人和事。");
        recommendItemList.add(recommendItem);

        recommendItem = new RecommendItem();
        recommendItem.setDiagonal("youth");
        recommendItem.setContent("每晚睡前，原谅所有的人和事。");
        recommendItemList.add(recommendItem);

        recommendItem = new RecommendItem();
        recommendItem.setDiagonal("youth");
        recommendItem.setContent("每晚睡前，原谅所有的人和事。");
        recommendItemList.add(recommendItem);

        recommendItem = new RecommendItem();
        recommendItem.setDiagonal("youth");
        recommendItem.setContent("每晚睡前，原谅所有的人和事。");
        recommendItemList.add(recommendItem);

        recommendItem = new RecommendItem();
        recommendItem.setDiagonal("youth");
        recommendItem.setContent("每晚睡前，原谅所有的人和事。");
        recommendItemList.add(recommendItem);
    }

}
