package com.gxuwz.beethoven.page.fragment.my.collect.video;

import android.content.Context;
import android.view.View;

import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.gxuwz.beethoven.R;
import com.gxuwz.beethoven.adapter.my.collect.video.VideoAdapter;
import com.gxuwz.beethoven.model.entity.my.collect.Video;

import java.util.ArrayList;
import java.util.List;

/**
 * 收藏中的视频页
 */
public class VideoViewInit {

    View videoView;
    RecyclerView videoRv;
    List<Video> videoList;

    public VideoViewInit(View videoView) {
        this.videoView = videoView;
    }

    public void init(Context context){
        findByIdAndNew(context);
    }

    public void findByIdAndNew(Context context){
        videoRv = videoView.findViewById(R.id.video_rv);
        videoList = new ArrayList<Video>();
        setData();
        videoRv.setLayoutManager(new StaggeredGridLayoutManager(1,StaggeredGridLayoutManager.VERTICAL));
        videoRv.setAdapter(new VideoAdapter(context,videoList));
    }

    public void setData(){
        Video video = new Video();
        video.setDiagonal("youth");
        video.setPlayNumber(43);
        video.setUploadMan("周深");
        video.setUploadTime("8:53");
        video.setVideoName("对话陈奕迅：《L.O.V.E》 就是老友记");
        videoList.add(video);

        video = new Video();
        video.setDiagonal("youth");
        video.setPlayNumber(43);
        video.setUploadMan("周深");
        video.setUploadTime("8:53");
        video.setVideoName("对话陈奕迅：《L.O.V.E》 就是老友记");
        videoList.add(video);

        video = new Video();
        video.setDiagonal("youth");
        video.setPlayNumber(43);
        video.setUploadMan("周深");
        video.setUploadTime("8:53");
        video.setVideoName("对话陈奕迅：《L.O.V.E》 就是老友记");
        videoList.add(video);

        video = new Video();
        video.setDiagonal("youth");
        video.setPlayNumber(43);
        video.setUploadMan("周深");
        video.setUploadTime("8:53");
        video.setVideoName("对话陈奕迅：《L.O.V.E》 就是老友记");
        videoList.add(video);
    }

}
