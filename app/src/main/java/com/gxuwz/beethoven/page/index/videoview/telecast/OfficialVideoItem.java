package com.gxuwz.beethoven.page.index.videoview.telecast;

import android.content.Context;

import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.gxuwz.beethoven.adapter.video.official.OfficialVideoAdapter;
import com.gxuwz.beethoven.model.entity.video.official.OfficialVideo;

import java.util.ArrayList;
import java.util.List;

public class OfficialVideoItem {

    RecyclerView officialVideoRv;
    List<OfficialVideo> officialVideoList;

    public OfficialVideoItem(RecyclerView officialVideoRv) {
        this.officialVideoRv = officialVideoRv;
    }

    public void init(Context context){
        findByIdAndNew(context);
        setData();
    }

    public void setData(){
        OfficialVideo officialVideo = new OfficialVideo();
        officialVideo.setDiagonal("youth");
        officialVideo.setTime("05:20");
        officialVideo.setTitle("一周热门速报EP20 快来体验“异地恋神器”");
        officialVideoList.add(officialVideo);

        officialVideo = new OfficialVideo();
        officialVideo.setDiagonal("youth");
        officialVideo.setTime("05:20");
        officialVideo.setTitle("一周热门速报EP20 快来体验“异地恋神器”");
        officialVideoList.add(officialVideo);

        officialVideo = new OfficialVideo();
        officialVideo.setDiagonal("youth");
        officialVideo.setTime("05:20");
        officialVideo.setTitle("一周热门速报EP20 快来体验“异地恋神器”");
        officialVideoList.add(officialVideo);
    }

    public void findByIdAndNew(Context context){
        officialVideoList = new ArrayList<OfficialVideo>();
        officialVideoRv.setLayoutManager(new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL));
        officialVideoRv.setAdapter(new OfficialVideoAdapter(context,officialVideoList));
    }
}
