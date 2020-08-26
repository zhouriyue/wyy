package com.gxuwz.beethoven.page.index.myview.radiostation.listening;

import android.content.Context;

import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.gxuwz.beethoven.adapter.my.radiostation.ListeningAdapter;
import com.gxuwz.beethoven.model.entity.my.radiostation.ListeningItem;

import java.util.ArrayList;
import java.util.List;

public class ListeningItemInit {

    RecyclerView listeningItemRv;
    List<ListeningItem> listeningItemList;

    public ListeningItemInit(RecyclerView listeningItemRv){
        this.listeningItemRv = listeningItemRv;
    }

    public void init(Context context){
        findByIdAndNew(context);
    }

    public void findByIdAndNew(Context context){
        listeningItemList = new ArrayList<ListeningItem>();
        setData();
        listeningItemRv.setLayoutManager(new StaggeredGridLayoutManager(3,StaggeredGridLayoutManager.VERTICAL));
        listeningItemRv.setAdapter(new ListeningAdapter(context,listeningItemList));
    }

    private void setData(){
        ListeningItem listeningItem = new ListeningItem();
        listeningItem.setType("直播中");
        listeningItem.setContent("安静 治愈 助眠 电台");
        listeningItem.setDiagonal("youth");
        listeningItemList.add(listeningItem);

        listeningItem = new ListeningItem();
        listeningItem.setType("直播中");
        listeningItem.setContent("安静 治愈 助眠 电台");
        listeningItem.setDiagonal("youth");
        listeningItemList.add(listeningItem);

        listeningItem = new ListeningItem();
        listeningItem.setType("直播中");
        listeningItem.setContent("安静 治愈 助眠 电台");
        listeningItem.setDiagonal("youth");
        listeningItemList.add(listeningItem);

        listeningItem = new ListeningItem();
        listeningItem.setType("直播中");
        listeningItem.setContent("安静 治愈 助眠 电台");
        listeningItem.setDiagonal("youth");
        listeningItemList.add(listeningItem);

        listeningItem = new ListeningItem();
        listeningItem.setType("直播中");
        listeningItem.setContent("安静 治愈 助眠 电台");
        listeningItem.setDiagonal("youth");
        listeningItemList.add(listeningItem);

        listeningItem = new ListeningItem();
        listeningItem.setType("直播中");
        listeningItem.setContent("安静 治愈 助眠 电台");
        listeningItem.setDiagonal("youth");
        listeningItemList.add(listeningItem);
    }
}
