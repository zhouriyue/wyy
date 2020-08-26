package com.gxuwz.beethoven.page.index.myview.download.single;

import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

import com.gxuwz.beethoven.R;

public class SingleViewInit {

    View singleView;
    RecyclerView singleRv;

    public SingleViewInit(View singleView) {
        this.singleView = singleView;
    }

    public void init(){

    }

    public void findByIdAndNew(){
        singleRv = singleView.findViewById(R.id.singer_rv);
    }
}
