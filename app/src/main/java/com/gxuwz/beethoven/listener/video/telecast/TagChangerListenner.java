package com.gxuwz.beethoven.listener.video.telecast;

import android.content.Context;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;
import androidx.viewpager.widget.ViewPager;

import com.gxuwz.beethoven.R;
import com.gxuwz.beethoven.adapter.video.telecast.TagViewAdapter;
import com.gxuwz.beethoven.model.entity.video.TagVideo;

import java.util.ArrayList;
import java.util.List;

public class TagChangerListenner implements ViewPager.OnPageChangeListener{

    Context context;
    RecyclerView tagRecyclerView;
    ArrayList<View> tagViewList;

    WindowManager windowManager;

    int current = 0;
    int last = 0;
    int size = 0;

    List<TagVideo> tagVideoList;

    public TagChangerListenner(RecyclerView tagRecyclerView,Context context,ArrayList<View> tagViewList,ArrayList<TagVideo> tagVideoList,WindowManager windowManager) {
        this.tagRecyclerView = tagRecyclerView;
        this.context = context;
        this.size = tagRecyclerView.getAdapter().getItemCount();
        this.tagViewList = tagViewList;
        this.tagVideoList = tagVideoList;
        this.windowManager = windowManager;
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        View view1 = tagRecyclerView.getLayoutManager().findViewByPosition(last);
        TextView textView1 = view1.findViewById(R.id.tag_str);
        textView1.setTextColor(context.getResources().getColor(R.color.black_2c));
        LinearLayout linearLayout1 = tagRecyclerView.findViewById(R.id.telecast_sp_box);
        linearLayout1.setBackground(context.getResources().getDrawable(R.drawable.shape_15dp_black_f0));
        if(position - last > 0) {
            if(position + 2 >= size) {
                tagRecyclerView.smoothScrollToPosition(size);
            } else {
                tagRecyclerView.smoothScrollToPosition(position+2);
            }
        } else if(position - last < 0) {
            if(position-2 < 0) {
                tagRecyclerView.smoothScrollToPosition(0);
            } else {
                tagRecyclerView.smoothScrollToPosition(position - 2);
            }
        } else {

        }
        last = position;
        View view = tagRecyclerView.getLayoutManager().findViewByPosition(position);
        TextView textView = view.findViewById(R.id.tag_str);
        textView.setTextColor(context.getResources().getColor(R.color.wyy_red));
        LinearLayout linearLayout = tagRecyclerView.findViewById(R.id.telecast_sp_box);
        linearLayout.setBackground(context.getResources().getDrawable(R.drawable.shape_15dp_33a9));

        View view2 = tagViewList.get(position);
        RecyclerView recyclerView = view2.findViewById(R.id.telecast_video_box_rv);
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL));
        recyclerView.setAdapter(new TagViewAdapter(context,tagVideoList,windowManager));
    }

    @Override
    public void onPageSelected(int position) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
