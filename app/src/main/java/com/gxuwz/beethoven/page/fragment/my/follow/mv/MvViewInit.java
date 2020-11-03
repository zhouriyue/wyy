package com.gxuwz.beethoven.page.fragment.my.follow.mv;

import android.content.Context;
import android.view.View;

import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.gxuwz.beethoven.R;
import com.gxuwz.beethoven.adapter.my.follow.MvAdapter;
import com.gxuwz.beethoven.model.entity.my.follow.Mv;
import com.gxuwz.beethoven.model.entity.my.follow.Singer;

import java.util.ArrayList;
import java.util.List;

public class MvViewInit {

    View mvView;
    RecyclerView mvRv;
    List<Mv> mvList;

    public MvViewInit(View mvView) {
        this.mvView = mvView;
    }

    public void init(Context context){
        findByIdAndNew(context);
    }

    public void findByIdAndNew(Context context){
        mvRv = mvView.findViewById(R.id.mv_rv);
        mvList = new ArrayList<Mv>();
        setData();
        mvRv.setLayoutManager(new StaggeredGridLayoutManager(1,StaggeredGridLayoutManager.VERTICAL));
        mvRv.setAdapter(new MvAdapter(context,mvList));
    }

    private void setData(){
        Mv mv = new Mv();
        Singer singer = new Singer();
        singer.setPerPic("zhoushen1");
        singer.setSingerName("周深");
        mv.setSinger(singer);
        mv.setDetail("8月4日上线了新MV");
        mv.setPlayNumber(200);
        mv.setImg("youth");
        mv.setTime("05:20");
        mvList.add(mv);

        mv = new Mv();
        singer = new Singer();
        singer.setPerPic("zhoushen1");
        singer.setSingerName("周深");
        mv.setSinger(singer);
        mv.setDetail("8月4日上线了新MV");
        mv.setPlayNumber(200);
        mv.setImg("youth");
        mv.setTime("05:20");
        mvList.add(mv);

        mv = new Mv();
        singer = new Singer();
        singer.setPerPic("zhoushen1");
        singer.setSingerName("周深");
        mv.setSinger(singer);
        mv.setDetail("8月4日上线了新MV");
        mv.setPlayNumber(200);
        mv.setImg("youth");
        mv.setTime("05:20");
        mvList.add(mv);
    }
}
