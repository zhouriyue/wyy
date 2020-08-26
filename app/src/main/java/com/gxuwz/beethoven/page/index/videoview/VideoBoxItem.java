package com.gxuwz.beethoven.page.index.videoview;

import android.content.Context;
import android.view.WindowManager;

import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.gxuwz.beethoven.adapter.video.VideoBoxAdapter;
import com.gxuwz.beethoven.model.entity.video.VideoBox;

import java.util.List;

public class VideoBoxItem {

    RecyclerView videoBoxRv;
    List<VideoBox> videoBoxList;

    public VideoBoxItem(RecyclerView videoBoxRv, List<VideoBox> videoBoxList) {
        this.videoBoxRv = videoBoxRv;
        this.videoBoxList = videoBoxList;
    }

    public void init(Context context){
        findByIdAndNew(context);
    }

    public void findByIdAndNew(Context context){
        videoBoxRv.setLayoutManager(new StaggeredGridLayoutManager(1,StaggeredGridLayoutManager.VERTICAL));
        videoBoxRv.setAdapter(new VideoBoxAdapter(videoBoxList,context));
    }
}
