package com.gxuwz.beethoven.page.fragment.my.collect.specialcolumn;

import android.content.Context;
import android.view.View;

import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.gxuwz.beethoven.R;
import com.gxuwz.beethoven.adapter.my.collect.specialcolumn.RecommendSCAdapter;
import com.gxuwz.beethoven.model.entity.my.collect.SpecialColumn;

import java.util.ArrayList;
import java.util.List;

public class SpecialColumnInit {

    View specialColumnView;
    RecyclerView recommendSpecialColumnRv;
    List<SpecialColumn> specialColumnList;

    public SpecialColumnInit(View specialColumnView) {
        this.specialColumnView = specialColumnView;
    }

    public void init(Context context){
        findByIdAndNew(context);
    }

    public void findByIdAndNew(Context context){
        recommendSpecialColumnRv = specialColumnView.findViewById(R.id.recommend_special_column_rv);
        specialColumnList = new ArrayList<SpecialColumn>();
        setData();
        recommendSpecialColumnRv.setLayoutManager(new StaggeredGridLayoutManager(1,StaggeredGridLayoutManager.VERTICAL));
        recommendSpecialColumnRv.setAdapter(new RecommendSCAdapter(context,specialColumnList));
    }

    public void setData(){
        SpecialColumn specialColumn = new SpecialColumn();
        specialColumn.setDiagonal("youth");
        specialColumn.setReadNumber(43);
        specialColumn.setContent("智能播放全新升级：每一刻的你，心动模式都懂");
        specialColumnList.add(specialColumn);

        specialColumn = new SpecialColumn();
        specialColumn.setDiagonal("youth");
        specialColumn.setReadNumber(43);
        specialColumn.setContent("智能播放全新升级：每一刻的你，心动模式都懂");
        specialColumnList.add(specialColumn);

        specialColumn = new SpecialColumn();
        specialColumn.setDiagonal("youth");
        specialColumn.setReadNumber(43);
        specialColumn.setContent("智能播放全新升级：每一刻的你，心动模式都懂");
        specialColumnList.add(specialColumn);

        specialColumn = new SpecialColumn();
        specialColumn.setDiagonal("youth");
        specialColumn.setReadNumber(43);
        specialColumn.setContent("智能播放全新升级：每一刻的你，心动模式都懂");
        specialColumnList.add(specialColumn);
    }
}
