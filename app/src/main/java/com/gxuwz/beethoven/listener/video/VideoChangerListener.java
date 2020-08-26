package com.gxuwz.beethoven.listener.video;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Build;
import android.view.View;
import android.view.WindowManager;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.gxuwz.beethoven.R;
import com.gxuwz.beethoven.model.entity.video.VideoBox;
import com.gxuwz.beethoven.page.index.cloudview.PlazaViewInit;
import com.gxuwz.beethoven.page.index.videoview.VideoBoxItem;
import com.gxuwz.beethoven.page.index.videoview.mv.MvVideoInit;
import com.gxuwz.beethoven.page.index.videoview.official.OfficialViewInit;
import com.gxuwz.beethoven.page.index.videoview.plazaadvert.PlazaAdvertInit;
import com.gxuwz.beethoven.page.index.videoview.telecast.TelecastViewInit;

import java.util.ArrayList;
import java.util.List;

public class VideoChangerListener implements ViewPager.OnPageChangeListener {

    RecyclerView viewNameRv;
    ArrayList<View> subPageList;
    Context context;
    WindowManager windowManager;
    List<VideoBox> videoBoxList;
    int next = 0;
    int current = 0;
    int max = 0;

    public VideoChangerListener(RecyclerView viewNameRv,ArrayList<View> subPageList,Context context,WindowManager windowManager) {
        this.viewNameRv = viewNameRv;
        this.max = viewNameRv.getAdapter().getItemCount();
        this.subPageList = subPageList;
        this.context = context;
        this.windowManager = windowManager;
        this.videoBoxList = new ArrayList<VideoBox>();
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        View nextView = viewNameRv.getLayoutManager().findViewByPosition(next);
        TextView nextViewName = nextView.findViewById(R.id.view_name);
        nextViewName.setTextColor(Color.BLACK);
        if (position - next > 0) {
            if(position + 2 > max-1) {
                current = max-1;
            } else {
                current = position + 2;
            }
        } else if (position - next < 0) {
            if (next - 3 >= 0) {
                current = next - 3;
            } else {
                current = 0;
            }
        } else {
            next = position;
        }
        next = position;
        viewNameRv.smoothScrollToPosition(current);
        View view = viewNameRv.getLayoutManager().findViewByPosition(position);
        TextView viewName = view.findViewById(R.id.view_name);
        viewName.setTextColor(Color.RED);

        switch (position) {
            case 1:{
                View telecastView = subPageList.get(position);
                TelecastViewInit telecastViewInit = new TelecastViewInit(telecastView);
                telecastViewInit.init(context,windowManager);
            };break;
            case 6:{
                View officialView = subPageList.get(position);
                OfficialViewInit officialViewInit = new OfficialViewInit(officialView);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    officialViewInit.init(context);
                }
            };break;
            case 7:{
                View plazaadvertView = subPageList.get(position);
                PlazaAdvertInit plazaViewInit = new PlazaAdvertInit(plazaadvertView);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    plazaViewInit.init(context);
                }
            };break;
            case 8:{
                View mvVideoView = subPageList.get(position);
                MvVideoInit mvVideoInit = new MvVideoInit(mvVideoView);
                mvVideoInit.init(context);
            };break;
            default:{
                View mvVideoView = subPageList.get(position);
                setData();
                RecyclerView recyclerView = mvVideoView.findViewById(R.id.dyn_video);
                VideoBoxItem videoBoxItem = new VideoBoxItem(recyclerView,videoBoxList);
                videoBoxItem.init(context);
            };break;
        }
    }

    public void setData(){
        VideoBox videoBox = new VideoBox();
        videoBox.setDiagonal("youth");
        videoBox.setGetLike(23);
        videoBox.setPlayNumber(21345);
        videoBox.setPlayTime("12:00");
        videoBox.setMessNumber(25);
        videoBox.setTag("#人气榜飙升榜#");
        videoBox.setTitle("周深《爱若琉璃》混剪 电视剧 《琉璃》 主题曲");
        videoBoxList.add(videoBox);

        videoBox = new VideoBox();
        videoBox.setDiagonal("youth");
        videoBox.setGetLike(23);
        videoBox.setPlayNumber(21345);
        videoBox.setPlayTime("12:00");
        videoBox.setMessNumber(25);
        videoBox.setTag("#人气榜飙升榜#");
        videoBox.setTitle("周深《爱若琉璃》混剪 电视剧 《琉璃》 主题曲");
        videoBoxList.add(videoBox);
    }

    @Override
    public void onPageSelected(int position) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
