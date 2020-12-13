package com.gxuwz.beethoven.page.index.cloudview;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.View;
import android.view.WindowManager;
import android.widget.Toast;

import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.gxuwz.beethoven.R;
import com.gxuwz.beethoven.adapter.cloud.PlazaAdapter;
import com.gxuwz.beethoven.adapter.my.RecentBroadcastsAdapter;
import com.gxuwz.beethoven.model.entity.SysUser;
import com.gxuwz.beethoven.model.entity.mlog.ImageWordMlog;
import com.gxuwz.beethoven.model.entity.mlog.Mlog;

import java.util.ArrayList;
import java.util.List;

public class PlazaViewInit {
    /**
     * 广场
     */
    RecyclerView plazaView;

    Context context;
    WindowManager windowManager;
    View plaza;
    List<Mlog> mlogList;

    public PlazaViewInit(Context context, View plaza,WindowManager windowManager) {
        this.context = context;
        this.plaza = plaza;
        this.windowManager = windowManager;
    }

    public void init() {
        findByIdAndNew();
        setData();
        recyclerInit();
    }

    public void setData(){
        ImageWordMlog imageWordMlog = new ImageWordMlog();
        imageWordMlog.setMusicDiagonal("zhoushen1");
        imageWordMlog.setContent("张一山演技炸裂表演 终于找到出处了");
        SysUser sysUser = new SysUser();
        sysUser.setPerPic("zhoushen");
        sysUser.setUserName("zhouriyue");
        imageWordMlog.setSysUser(sysUser);
        imageWordMlog.setLikeNumber(230);
        mlogList.add(imageWordMlog);

        imageWordMlog = new ImageWordMlog();
        imageWordMlog.setMusicDiagonal("zhoushen");
        imageWordMlog.setContent("张一山演技炸裂表演 终于找到出处了");
        sysUser = new SysUser();
        sysUser.setPerPic("zhoushen");
        sysUser.setUserName("zhouriyue");
        imageWordMlog.setSysUser(sysUser);
        imageWordMlog.setLikeNumber(230);
        mlogList.add(imageWordMlog);

        imageWordMlog = new ImageWordMlog();
        imageWordMlog.setMusicDiagonal("mlog");
        imageWordMlog.setContent("张一山演技炸裂表演 终于找到出处了");
        sysUser = new SysUser();
        sysUser.setPerPic("zhoushen");
        sysUser.setUserName("zhouriyue");
        imageWordMlog.setSysUser(sysUser);
        imageWordMlog.setLikeNumber(230);
        mlogList.add(imageWordMlog);

        imageWordMlog = new ImageWordMlog();
        imageWordMlog.setMusicDiagonal("mlog");
        imageWordMlog.setContent("张一山演技炸裂表演 终于找到出处了");
        sysUser = new SysUser();
        sysUser.setPerPic("zhoushen");
        sysUser.setUserName("zhouriyue");
        imageWordMlog.setSysUser(sysUser);
        imageWordMlog.setLikeNumber(230);
        mlogList.add(imageWordMlog);

        imageWordMlog = new ImageWordMlog();
        imageWordMlog.setMusicDiagonal("mlog");
        imageWordMlog.setContent("张一山演技炸裂表演 终于找到出处了");
        sysUser = new SysUser();
        sysUser.setPerPic("zhoushen");
        sysUser.setUserName("zhouriyue");
        imageWordMlog.setSysUser(sysUser);
        imageWordMlog.setLikeNumber(230);
        mlogList.add(imageWordMlog);

        imageWordMlog = new ImageWordMlog();
        imageWordMlog.setMusicDiagonal("mlog");
        imageWordMlog.setContent("张一山演技炸裂表演 终于找到出处了");
        sysUser = new SysUser();
        sysUser.setPerPic("zhoushen");
        sysUser.setUserName("zhouriyue");
        imageWordMlog.setSysUser(sysUser);
        imageWordMlog.setLikeNumber(230);
        mlogList.add(imageWordMlog);

        imageWordMlog = new ImageWordMlog();
        imageWordMlog.setMusicDiagonal("mlog");
        imageWordMlog.setContent("张一山演技炸裂表演 终于找到出处了");
        sysUser = new SysUser();
        sysUser.setPerPic("zhoushen");
        sysUser.setUserName("zhouriyue");
        imageWordMlog.setSysUser(sysUser);
        imageWordMlog.setLikeNumber(230);
        mlogList.add(imageWordMlog);

        imageWordMlog = new ImageWordMlog();
        imageWordMlog.setMusicDiagonal("mlog");
        imageWordMlog.setContent("张一山演技炸裂表演 终于找到出处了");
        sysUser = new SysUser();
        sysUser.setPerPic("zhoushen");
        sysUser.setUserName("zhouriyue");
        imageWordMlog.setSysUser(sysUser);
        imageWordMlog.setLikeNumber(230);
        mlogList.add(imageWordMlog);
    }

    public void findByIdAndNew(){
        plazaView = plaza.findViewById(R.id.plaza_recycler_view);
        mlogList = new ArrayList<Mlog>();
    }

    public void recyclerInit(){
        plazaView.setHasFixedSize(true);
        plazaView.setNestedScrollingEnabled(false);
        plazaView.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
        PlazaAdapter plazaAdapter = new PlazaAdapter(context,mlogList,windowManager);
        plazaView.setOnScrollListener(new ThisScrollListener());
        plazaView.setAdapter(plazaAdapter);
    }

    class ThisScrollListener extends RecyclerView.OnScrollListener {
        //用来标记是否正在向最后一个滑动，既是否向下滑动
        boolean isSlidingToLast = false;

        @SuppressLint("WrongConstant")
        @Override
        public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
            StaggeredGridLayoutManager manager = (StaggeredGridLayoutManager) recyclerView.getLayoutManager();
            // 当不滚动时
            if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                //获取最后一个完全显示的ItemPosition
                int[] lastVisiblePositions = manager.findLastVisibleItemPositions(new int[manager.getSpanCount()]);
                int lastVisiblePos = getMaxElem(lastVisiblePositions);
                int totalItemCount = manager.getItemCount();
                // 判断是否滚动到底部
                if (lastVisiblePos == (totalItemCount -1) && isSlidingToLast) {
                    //加载更多功能的代码
                    Toast.makeText(context,"加载更多",1).show();
                }
            }
        }

        @Override
        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
            super.onScrolled(recyclerView, dx, dy);
            //dx用来判断横向滑动方向，dy用来判断纵向滑动方向
            if(dy > 0){
                //大于0表示，正在向下滚动
                isSlidingToLast = true;
            }else{
                //小于等于0 表示停止或向上滚动
                isSlidingToLast = false;
            }

        }
    }

    private int getMaxElem(int[] arr) {
        int size = arr.length;
        int maxVal = Integer.MIN_VALUE;
        for (int i = 0; i < size; i++) {
            if (arr[i]>maxVal)
                maxVal = arr[i];
        }
        return maxVal;
    }
}
