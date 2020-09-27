package com.gxuwz.beethoven.page.index.findview.spefun.telecast;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.gxuwz.beethoven.R;
import com.gxuwz.beethoven.adapter.find.spefun.telecast.FollowAdapter;
import com.gxuwz.beethoven.adapter.find.spefun.telecast.MoreRecommendAdapter;
import com.gxuwz.beethoven.adapter.my.collect.anchor.AnchorAdapter;
import com.gxuwz.beethoven.model.entity.find.Telecast;
import com.gxuwz.beethoven.model.entity.find.spefun.telecast.TelecastFollowView;

import java.util.List;

public class TelecastFollowPager {

    View telecastFollowPager;
    Context context;
    TelecastFollowView telecastFollowView;

    TextView title;
    RecyclerView itemStcRv;

    public TelecastFollowPager(Context context,View telecastFollowPager,TelecastFollowView telecastFollowView) {
        this.context = context;
        this.telecastFollowPager = telecastFollowPager;
        this.telecastFollowView = telecastFollowView;
        title = telecastFollowPager.findViewById(R.id.title);
        itemStcRv = telecastFollowPager.findViewById(R.id.item_stc_rv);
    }

    public void initPager(){
        List<Telecast> telecastList = telecastFollowView.getTelecastList();
        title.setText(telecastFollowView.getTitle());
       switch (telecastFollowView.getShowType()) {
           case 1:{
               itemStcRv.setLayoutManager(new StaggeredGridLayoutManager(1,StaggeredGridLayoutManager.VERTICAL));
               itemStcRv.setAdapter(new FollowAdapter(context,telecastList));
           };break;
           case 2:{
               itemStcRv.setLayoutManager(new StaggeredGridLayoutManager(1,StaggeredGridLayoutManager.VERTICAL));
               itemStcRv.setAdapter(new MoreRecommendAdapter(context,telecastList));
           };break;
       }
    }
}
