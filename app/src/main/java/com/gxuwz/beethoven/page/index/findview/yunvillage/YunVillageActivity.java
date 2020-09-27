package com.gxuwz.beethoven.page.index.findview.yunvillage;

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

import com.gxuwz.beethoven.R;
import com.gxuwz.beethoven.adapter.PagerCustomAdapter;
import com.gxuwz.beethoven.adapter.video.NameViewAdapter;
import com.gxuwz.beethoven.listener.find.slplaza.SLPlazaListener;

import java.util.ArrayList;
import java.util.List;

public class YunVillageActivity extends AppCompatActivity {

    Context context;
    WindowManager windowManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(getResources().getColor(R.color.black));
        }
        setContentView(R.layout.activity_yun_village);
        /**
         * 隐藏标题栏
         */
        getSupportActionBar().hide();
        findByIdAndNew();
        initPager();
    }

    ViewPager viewPager;
    PagerCustomAdapter pagerCustomAdapter;
    RecyclerView menuNameRv;
    List<String> nameList;
    ArrayList<View> viewList;
    public void findByIdAndNew(){
        context = YunVillageActivity.this;
        windowManager = getWindowManager();
        viewPager = findViewById(R.id.view_pager);
        menuNameRv = findViewById(R.id.menu_name_rv);
        viewList = new ArrayList<View>();
        viewList.add(LayoutInflater.from(context).inflate(R.layout.item_yunv,null));
        viewList.add(LayoutInflater.from(context).inflate(R.layout.item_yunv,null));
        viewList.add(LayoutInflater.from(context).inflate(R.layout.item_yunv,null));
        pagerCustomAdapter = new PagerCustomAdapter(viewList);
        nameList = new ArrayList<String>();
        nameList.add("讨论");
        nameList.add("云圈精华");
        nameList.add("玩音乐");
        LinearLayoutManager lm = new LinearLayoutManager(context);
        lm.setOrientation(RecyclerView.HORIZONTAL);
        menuNameRv.setLayoutManager(lm);
        menuNameRv.setAdapter(new NameViewAdapter(nameList,context));

    }

    public void initPager(){
        viewPager.setAdapter(pagerCustomAdapter);
        viewPager.setCurrentItem(0);
        viewPager.addOnPageChangeListener(new SLPlazaListener());
        DisplayMetrics displayMetrics = new DisplayMetrics();
        windowManager.getDefaultDisplay().getMetrics(displayMetrics);
    }
}
