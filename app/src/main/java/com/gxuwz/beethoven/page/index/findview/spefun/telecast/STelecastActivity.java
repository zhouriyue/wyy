package com.gxuwz.beethoven.page.index.findview.spefun.telecast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.menu.MenuAdapter;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;
import androidx.viewpager.widget.ViewPager;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.gxuwz.beethoven.R;
import com.gxuwz.beethoven.adapter.PagerCustomAdapter;
import com.gxuwz.beethoven.adapter.find.spefun.telecast.StcFollowAdapter;
import com.gxuwz.beethoven.adapter.find.spefun.telecast.TelecastAdapter;
import com.gxuwz.beethoven.adapter.find.spefun.telecast.TelecastClassAdapter;
import com.gxuwz.beethoven.adapter.video.NameViewAdapter;
import com.gxuwz.beethoven.listener.find.slplaza.SLPlazaListener;
import com.gxuwz.beethoven.model.entity.SysUser;
import com.gxuwz.beethoven.model.entity.find.Telecast;
import com.gxuwz.beethoven.model.entity.find.spefun.telecast.TelecastClass;
import com.gxuwz.beethoven.model.entity.find.spefun.telecast.TelecastFollowView;
import com.gxuwz.beethoven.util.WindowPixels;

import java.util.ArrayList;
import java.util.List;

/**
 * 个性化功能
 * 直播
 */
public class STelecastActivity extends AppCompatActivity {

    int itemWidth = (int) ((WindowPixels.WIDTH-100)/5);

    Context context;
    RecyclerView telecastMenunameRv;
    PagerCustomAdapter pagerCustomAdapter;
    WindowManager windowManager;
    ViewPager viewPager;
    LinearLayout toBackLin;
    ArrayList<View> viewList;
    List<String> nameList;

    List<TelecastClass> telecastClassList;
    List<TelecastFollowView> telecastFollowViews;
    List<Telecast> telecastList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(getResources().getColor(R.color.black));
        }
        setContentView(R.layout.stelecast);
        /**
         * 隐藏标题栏
         */
        getSupportActionBar().hide();
        findByIdAndNew();
        initPager();
    }

    public void findByIdAndNew(){
        context = STelecastActivity.this;
        windowManager = getWindowManager();
        viewPager = findViewById(R.id.view_pager);
        telecastMenunameRv = findViewById(R.id.telecast_menuname_rv);
        toBackLin = findViewById(R.id.to_back_lin);
        toBackLin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        nameList = new ArrayList<String>();
        nameList.add("听听");
        nameList.add("看看");
        nameList.add("派对");
        nameList.add("关注");
        LinearLayoutManager lm = new LinearLayoutManager(context);
        lm.setOrientation(RecyclerView.HORIZONTAL);
        telecastMenunameRv.setLayoutManager(lm);
        telecastMenunameRv.setAdapter(new NameViewAdapter(nameList,context));
        viewList = new ArrayList<View>();
        viewList.add(LayoutInflater.from(context).inflate(R.layout.pager_comm,null));
        indexInit();
        viewList.add(LayoutInflater.from(context).inflate(R.layout.pager_comm,null));
        viewList.add(LayoutInflater.from(context).inflate(R.layout.pager_comm,null));
        viewList.add(LayoutInflater.from(context).inflate(R.layout.item_follow_rv,null));
        telecastFollowViews = new ArrayList<TelecastFollowView>();
        setData();
        initView();
    }

    public void initView(){
        RecyclerView telecastTopRv,telecastAll,telecastRv;
        View view;
        int i = 0;
        while (i<viewList.size()) {
            view = viewList.get(i);
            switch (i) {
                case 0:{
                    telecastTopRv = view.findViewById(R.id.telecast_top_rv);
                    initRecyclerViewClass(telecastTopRv,telecastClassList.subList(0,4),5);
                    telecastAll = view.findViewById(R.id.telecast_all);
                    initRecyclerViewClass(telecastAll,telecastClassList.subList(4,telecastClassList.size()),5);
                    telecastRv = view.findViewById(R.id.telecast_rv);
                    telecastRv.setLayoutManager(new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL));

                    telecastRv.setAdapter(new TelecastAdapter(context,telecastList));
                };break;
                case 1:{

                };break;
                case 2:{

                };break;
                case 3:{
                    RecyclerView followItemRv = view.findViewById(R.id.follow_item_rv);
                    followItemRv.setLayoutManager(new StaggeredGridLayoutManager(1,StaggeredGridLayoutManager.VERTICAL));
                    followItemRv.setAdapter(new StcFollowAdapter(context,telecastFollowViews));
                };break;
            }
            i++;
        }
    }

    public void setData(){
        telecastList = new ArrayList<Telecast>();
        Telecast telecast = new Telecast();
        telecast.setImg("youth");
        telecast.setTitle("期待你的到来想要成为男团");
        telecast.setType("处女星主");
        telecast.setOnlineNumber(1230);
        telecastList.add(telecast);
        telecast = new Telecast();
        telecast.setImg("youth");
        telecast.setTitle("期待你的到来想要成为男团");
        telecast.setType("处女星主");
        telecast.setOnlineNumber(1230);
        telecastList.add(telecast);
        telecast = new Telecast();
        telecast.setImg("youth");
        telecast.setTitle("期待你的到来想要成为男团");
        telecast.setType("处女星主");
        telecast.setOnlineNumber(1230);
        telecastList.add(telecast);
        telecast = new Telecast();
        telecast.setImg("youth");
        telecast.setTitle("期待你的到来想要成为男团");
        telecast.setType("处女星主");
        telecast.setOnlineNumber(1230);
        telecastList.add(telecast);
        telecast = new Telecast();
        telecast.setImg("youth");
        telecast.setTitle("期待你的到来想要成为男团");
        telecast.setType("处女星主");
        telecast.setOnlineNumber(1230);
        telecastList.add(telecast);

        telecastClassList = new ArrayList<TelecastClass>();
        TelecastClass telecastClass = new TelecastClass();
        telecastClass.setIcon("youth");
        telecastClass.setName("唱见");
        telecastClassList.add(telecastClass);
        telecastClass = new TelecastClass();
        telecastClass.setIcon("youth");
        telecastClass.setName("唱见");
        telecastClassList.add(telecastClass);
        telecastClass = new TelecastClass();
        telecastClass.setIcon("youth");
        telecastClass.setName("唱见");
        telecastClassList.add(telecastClass);
        telecastClass = new TelecastClass();
        telecastClass.setIcon("youth");
        telecastClass.setName("唱见");
        telecastClassList.add(telecastClass);
        telecastClass = new TelecastClass();
        telecastClass.setIcon("youth");
        telecastClass.setName("唱见");
        telecastClassList.add(telecastClass);
        telecastClass = new TelecastClass();
        telecastClass.setIcon("youth");
        telecastClass.setName("唱见");
        telecastClassList.add(telecastClass);
        telecastClass = new TelecastClass();
        telecastClass.setIcon("youth");
        telecastClass.setName("唱见");
        telecastClassList.add(telecastClass);

        TelecastFollowView telecastFollowView = new TelecastFollowView();
        telecastFollowView.setShowType(1);
        telecastFollowView.setTitle("暂未开播");
        List<Telecast> telecastList = new ArrayList<Telecast>();
        telecast = new Telecast();
        telecast.setTitle("温婷_Amy");
        telecast.setDetail("每晚10:00-5:20治愈系温助眠电台");
        telecast.setImg("youth");
        telecastList.add(telecast);
        telecast = new Telecast();
        telecast.setTitle("温婷_Amy");
        telecast.setDetail("每晚10:00-5:20治愈系温助眠电台");
        telecast.setImg("youth");
        telecastList.add(telecast);
        telecastFollowView.setTelecastList(telecastList);
        telecastFollowViews.add(telecastFollowView);

        telecastFollowView = new TelecastFollowView();
        telecastFollowView.setShowType(2);
        telecastFollowView.setTitle("更多推荐");
        telecastList = new ArrayList<Telecast>();
        telecast = new Telecast();
        telecast.setTitle("寻常三国直播");
        telecast.setType("视频");
        telecast.setImg("youth");
        telecast.setHotNumber(2000);
        SysUser sysUser = new SysUser();
        sysUser.setUserName("谁给我一只猫");
        sysUser.setPerPic("zhoushen");
        telecast.setSysUser(sysUser);
        telecastList.add(telecast);
        telecast = new Telecast();
        telecast.setTitle("寻常三国直播");
        telecast.setType("视频");
        telecast.setImg("youth");
        telecast.setHotNumber(2000);
        sysUser = new SysUser();
        sysUser.setUserName("谁给我一只猫");
        sysUser.setPerPic("zhoushen");
        telecast.setSysUser(sysUser);
        telecastList.add(telecast);
        telecast = new Telecast();
        telecast.setTitle("寻常三国直播");
        telecast.setType("视频");
        telecast.setImg("youth");
        telecast.setHotNumber(2000);
        sysUser = new SysUser();
        sysUser.setUserName("谁给我一只猫");
        sysUser.setPerPic("zhoushen");
        telecast.setSysUser(sysUser);
        telecastList.add(telecast);
        telecast = new Telecast();
        telecast.setTitle("寻常三国直播");
        telecast.setType("视频");
        telecast.setImg("youth");
        telecast.setHotNumber(2000);
        sysUser = new SysUser();
        sysUser.setUserName("谁给我一只猫");
        sysUser.setPerPic("zhoushen");
        telecast.setSysUser(sysUser);
        telecastList.add(telecast);
        telecastFollowView.setTelecastList(telecastList);
        telecastFollowViews.add(telecastFollowView);
    }

    public void initRecyclerViewClass(RecyclerView recyclerView, List<TelecastClass> telecastClassList,int spanCount){
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(spanCount,StaggeredGridLayoutManager.VERTICAL));
        recyclerView.setAdapter(new TelecastClassAdapter(context,telecastClassList));
    }

    LinearLayout mHiddenLayout;
    float mDensity;
    int mHiddenViewMeasuredHeight;
    ImageView pullDownIcon;

    public void indexInit(){
        View listenerView = viewList.get(0);
        mHiddenLayout = (LinearLayout) listenerView.findViewById(R.id.linear_hidden);
        pullDownIcon = (ImageView) listenerView.findViewById(R.id.pull_down_icon);
        mDensity = getResources().getDisplayMetrics().density;
        mHiddenViewMeasuredHeight = (int) (mDensity * 120 + 0.5);
    }

    public void initPager(){
        pagerCustomAdapter = new PagerCustomAdapter(viewList);
        //绑定适配器
        viewPager.setAdapter(pagerCustomAdapter);
        //设置viewPager的初始界面为第一个界面
        viewPager.setCurrentItem(0);
        //添加切换界面的监听器
        viewPager.addOnPageChangeListener(new SLPlazaListener());
        //为了获取屏幕宽度，新建一个DisplayMetrics对象
        DisplayMetrics displayMetrics = new DisplayMetrics();
        //将当前窗口的一些信息放在DisplayMetrics类中
        windowManager.getDefaultDisplay().getMetrics(displayMetrics);
    }

    public void onClick(View v) {
        if (mHiddenLayout.getVisibility() == View.GONE) {
            animateOpen(mHiddenLayout);
            animationIvOpen();
        } else {
            animateClose(mHiddenLayout);
            animationIvClose();
        }
    }

    private void animateOpen(View v) {
        v.setVisibility(View.VISIBLE);
        ValueAnimator animator = createDropAnimator(v, 0,
                mHiddenViewMeasuredHeight);
        animator.start();

    }

    private void animationIvOpen() {
        RotateAnimation animation = new RotateAnimation(0, 180,
                Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF,
                0.5f);
        animation.setFillAfter(true);
        animation.setDuration(100);
        pullDownIcon.startAnimation(animation);
    }

    private void animationIvClose() {
        RotateAnimation animation = new RotateAnimation(180, 0,
                Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF,
                0.5f);
        animation.setFillAfter(true);
        animation.setDuration(100);
        pullDownIcon.startAnimation(animation);
    }

    private void animateClose(final View view) {
        int origHeight = view.getHeight();
        ValueAnimator animator = createDropAnimator(view, origHeight, 0);
        animator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                view.setVisibility(View.GONE);
            }

        });
        animator.start();
    }

    private ValueAnimator createDropAnimator(final View v, int start, int end) {
        ValueAnimator animator = ValueAnimator.ofInt(start, end);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {

            @Override
            public void onAnimationUpdate(ValueAnimator arg0) {
                int value = (int) arg0.getAnimatedValue();
                ViewGroup.LayoutParams layoutParams = v.getLayoutParams();
                layoutParams.height = value;
                v.setLayoutParams(layoutParams);

            }
        });
        return animator;
    }

}
