package com.gxuwz.beethoven.page.fragment.my.downmanage;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.Manifest;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

import com.gxuwz.beethoven.R;

import com.gxuwz.beethoven.adapter.FragmentCustomAdapter;
import com.gxuwz.beethoven.page.fragment.my.downmanage.download.FragmentDownLoad;
import com.gxuwz.beethoven.page.fragment.my.downmanage.radiostation.FragmentRadioStation;
import com.gxuwz.beethoven.page.fragment.my.downmanage.single.FragmentSingle;
import com.gxuwz.beethoven.page.fragment.my.downmanage.video.FragmentVideo;

import java.util.ArrayList;
import java.util.List;

public class DownLoadActivity extends AppCompatActivity implements View.OnClickListener {

    Context context;
    ViewPager videoPager;
    ImageView toBack;
    List<Fragment> fragmentList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(getResources().getColor(R.color.black));
        }
        setContentView(R.layout.download);
        /**
         * 隐藏标题栏
         */
        getSupportActionBar().hide();
        checkNeedPermissions();
        findByIdAndNew();
    }

    public void findByIdAndNew(){
        videoPager = findViewById(R.id.download_menu);
        toBack = findViewById(R.id.to_back);
        context = DownLoadActivity.this;
        toBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        initPager();
    }

    public void initPager(){
        fragmentList = new ArrayList<Fragment>();
        Fragment fragmentSingle = new FragmentSingle();
        Fragment fragmentRadioStation = new FragmentRadioStation();
        Fragment fragmentVideo = new FragmentVideo();
        Fragment fragmentDownLoad = new FragmentDownLoad();
        fragmentList.add(fragmentSingle);
        fragmentList.add(fragmentRadioStation);
        fragmentList.add(fragmentVideo);
        fragmentList.add(fragmentDownLoad);
        videoPager.setAdapter(new FragmentCustomAdapter(getSupportFragmentManager(),fragmentList));
        initFragement();
    }

    public void  initFragement(){
        int fragmentFlag = getIntent().getIntExtra("fragment_flag",0);
        videoPager.setCurrentItem(fragmentFlag);
    }

    private void checkNeedPermissions() {
        //6.0以上需要动态申请权限
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED
                || ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED
                || ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            //多个权限一起申请
            ActivityCompat.requestPermissions(this, new String[]{
                    Manifest.permission.CAMERA,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE,
                    Manifest.permission.READ_EXTERNAL_STORAGE
            }, 1);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.single:{
                videoPager.setCurrentItem(0);
            };break;
            case R.id.radio_station:{
                videoPager.setCurrentItem(1);
            };break;
            case R.id.video:{
                videoPager.setCurrentItem(2);
            };break;
            case R.id.download:{
                videoPager.setCurrentItem(3);
            };break;
        }
    }
}
