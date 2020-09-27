package com.gxuwz.beethoven.page.index.findview.spefun.slplaza;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;

import com.gxuwz.beethoven.R;
import com.gxuwz.beethoven.adapter.PagerCustomAdapter;
import com.gxuwz.beethoven.adapter.find.SLPNameAdapter;
import com.gxuwz.beethoven.listener.find.slplaza.SLPlazaListener;
import com.gxuwz.beethoven.page.index.findview.spefun.slplaza.recommendview.RecommendView;

import java.util.ArrayList;

/**
 * 歌单广场
 */
public class SLPlazaActivity extends AppCompatActivity {

    Context context;
    LinearLayout toBackLin;
    RecyclerView menuName;
    ViewPager viewPager;
    WindowManager windowManager;
    LayoutInflater layoutInflater;
    PagerCustomAdapter pagerCustomAdapter;
    ArrayList<View> viewList;
    ArrayList<String> nameList;

    /**
     * 子页面组件
     */
    View slpRecommend;
    RecommendView recommendView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(getResources().getColor(R.color.black));
        }
        setContentView(R.layout.slplaza);
        /**
         * 隐藏标题栏
         */
        getSupportActionBar().hide();
        findByIdAndNew();
        initPager();
    }

    public void initPager(){
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

    public void findByIdAndNew(){
        context = SLPlazaActivity.this;
        windowManager = getWindowManager();
        layoutInflater = getLayoutInflater();
        viewPager = findViewById(R.id.menu_vp);
        menuName = findViewById(R.id.menu_name);
        toBackLin = findViewById(R.id.to_back_lin);
        toBackLin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        slpRecommend = layoutInflater.inflate(R.layout.slp_recommend,null);
        viewList = new ArrayList<View>();
        viewList.add(slpRecommend);
        recommendView = new RecommendView(slpRecommend,context);

        nameList = new ArrayList<String>();
        nameList.add("推荐");
        nameList.add("官方");
        nameList.add("精品");
        nameList.add("华语");
        nameList.add("流行");
        nameList.add("民谣");
        LinearLayoutManager lm = new LinearLayoutManager(SLPlazaActivity.this);
        lm.setOrientation(RecyclerView.HORIZONTAL);
        menuName.setLayoutManager(lm);
        menuName.setAdapter(new SLPNameAdapter(context,nameList));
        pagerCustomAdapter = new PagerCustomAdapter(viewList);
    }
}
