package com.gxuwz.beethoven.page.index.myview.collect.yunvillage;

import android.content.Context;
import android.view.View;

import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.gxuwz.beethoven.R;
import com.gxuwz.beethoven.adapter.my.collect.likeyunvillage.LikeYunVillageAdapter;
import com.gxuwz.beethoven.model.entity.my.collect.YunVillage;

import java.util.ArrayList;
import java.util.List;


public class YunVillageViewInit {

    View yunVillageView;
    RecyclerView likeYunvillageRv;
    List<YunVillage> yunVillageList;

    public YunVillageViewInit(View yunVillageView) {
        this.yunVillageView = yunVillageView;
    }

    public void init(Context context) {
        findByIdAndNew(context);
    }

    public void setData(){
        YunVillage yunVillage = new YunVillage();
        yunVillage.setDiagonal("youth");
        yunVillage.setTitle("周深的云圈");
        yunVillage.setContent("66万生米已经加入 | 1239万阅读");
        yunVillageList.add(yunVillage);

        yunVillage = new YunVillage();
        yunVillage.setDiagonal("youth");
        yunVillage.setTitle("周深的云圈");
        yunVillage.setContent("66万生米已经加入 | 1239万阅读");
        yunVillageList.add(yunVillage);

        yunVillage = new YunVillage();
        yunVillage.setDiagonal("youth");
        yunVillage.setTitle("周深的云圈");
        yunVillage.setContent("66万生米已经加入 | 1239万阅读");
        yunVillageList.add(yunVillage);

        yunVillage = new YunVillage();
        yunVillage.setDiagonal("youth");
        yunVillage.setTitle("周深的云圈");
        yunVillage.setContent("66万生米已经加入 | 1239万阅读");
        yunVillageList.add(yunVillage);
    }

    public void findByIdAndNew(Context context) {
        likeYunvillageRv = yunVillageView.findViewById(R.id.like_yunvillage_rv);
        yunVillageList = new ArrayList<YunVillage>();
        setData();
        likeYunvillageRv.setLayoutManager(new StaggeredGridLayoutManager(1,StaggeredGridLayoutManager.VERTICAL));
        likeYunvillageRv.setAdapter(new LikeYunVillageAdapter(context,yunVillageList));
    }
}
