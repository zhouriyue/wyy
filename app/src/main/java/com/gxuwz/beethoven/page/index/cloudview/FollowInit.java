package com.gxuwz.beethoven.page.index.cloudview;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;

import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.gxuwz.beethoven.R;
import com.gxuwz.beethoven.adapter.cloud.DynamicsAdapter;
import com.gxuwz.beethoven.model.entity.dynamics.Dynamics;
import com.gxuwz.beethoven.model.entity.dynamics.ImageWordDynamics;
import com.gxuwz.beethoven.util.HttpUtil;
import com.gxuwz.beethoven.util.MergeImage;

import java.util.ArrayList;
import java.util.List;

public class FollowInit {
    View FollowView;
    Context context;
    ImageView cloudCircle;
    RecyclerView dynamicsRv;
    List<Dynamics> dynamicsList;

    public FollowInit(View followView, Context context) {
        FollowView = followView;
        this.context = context;
    }

    public void init(){
        findByIdAndNew();
    }

    public void setData(){
        ImageWordDynamics imageWordDynamics = new ImageWordDynamics();
        imageWordDynamics.setContent("一点小小的梦想也能长成参天大树,一点小小的梦想也能长成参天大树");
        imageWordDynamics.setFriend("#周日月# #码云# #张飞#");
        imageWordDynamics.setDiagonal("zhoushen");
        dynamicsList.add(imageWordDynamics);

        imageWordDynamics = new ImageWordDynamics();
        imageWordDynamics.setContent("一点小小的梦想也能长成参天大树,一点小小的梦想也能长成参天大树");
        imageWordDynamics.setFriend("#周日月# #码云# #张飞#");
        imageWordDynamics.setDiagonal("youth");
        dynamicsList.add(imageWordDynamics);
    }

    public void findByIdAndNew(){
        cloudCircle = FollowView.findViewById(R.id.cloud_circle_img);
        dynamicsRv = FollowView.findViewById(R.id.dynamics_rv);
        dynamicsList = new ArrayList<Dynamics>();
        dynamicsRv.setLayoutManager(new StaggeredGridLayoutManager(1,StaggeredGridLayoutManager.VERTICAL));
        setData();
        dynamicsRv.setNestedScrollingEnabled(false);
        dynamicsRv.setAdapter(new DynamicsAdapter(context,dynamicsList));
        cloudCircle.setImageBitmap(MergeImage.circleShow(HttpUtil.getRes("zhoushen",context)));
    }
}
