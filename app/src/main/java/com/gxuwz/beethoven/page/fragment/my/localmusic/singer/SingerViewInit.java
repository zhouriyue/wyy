package com.gxuwz.beethoven.page.fragment.my.localmusic.singer;

import android.content.Context;
import android.view.View;

import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.gxuwz.beethoven.R;
import com.gxuwz.beethoven.adapter.my.localmusic.singer.SingerItemAdapter;
import com.gxuwz.beethoven.model.entity.my.local.singer.SingerItem;

import java.util.ArrayList;
import java.util.List;

public class SingerViewInit {

    View singerView;
    RecyclerView singerRv;
    List<SingerItem> singerItemList;

    public SingerViewInit(View singerView) {
        this.singerView = singerView;
    }

    public void init(Context context){
        findByIdAndNew(context);
    }

    public void findByIdAndNew(Context context){
        singerRv = singerView.findViewById(R.id.singer_rv);
        singerItemList = new ArrayList<SingerItem>();
        setData();
        singerRv.setLayoutManager(new StaggeredGridLayoutManager(1,StaggeredGridLayoutManager.VERTICAL));
        singerRv.setAdapter(new SingerItemAdapter(context,singerItemList));
    }

    public void setData(){
        SingerItem singerItem = new SingerItem();
        singerItem.setPerPic("zhoushen1");
        singerItem.setSingerName("周深");
        singerItem.setCount(12);
        singerItemList.add(singerItem);

        singerItem = new SingerItem();
        singerItem.setPerPic("zhoushen1");
        singerItem.setSingerName("周深");
        singerItem.setCount(12);
        singerItemList.add(singerItem);

        singerItem = new SingerItem();
        singerItem.setPerPic("zhoushen1");
        singerItem.setSingerName("周深");
        singerItem.setCount(12);
        singerItemList.add(singerItem);
    }
}
