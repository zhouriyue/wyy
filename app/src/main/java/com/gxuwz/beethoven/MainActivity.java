package com.gxuwz.beethoven;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Message;
import android.view.Window;
import android.view.WindowManager;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.gxuwz.beethoven.dao.OperateDao;
import com.gxuwz.beethoven.dao.SetMealDao;
import com.gxuwz.beethoven.model.entity.current.Operate;
import com.gxuwz.beethoven.model.entity.current.SetMeal;
import com.gxuwz.beethoven.model.entity.current.TypeOperate;
import com.gxuwz.beethoven.page.fragment.PrincipalSheetActivity;
import com.gxuwz.beethoven.util.HttpUtils;
import com.gxuwz.beethoven.util.MergeImage;
import com.gxuwz.beethoven.util.WindowPixels;
import com.gxuwz.beethoven.util.staticdata.StaticHttp;

import java.lang.reflect.Type;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    SharedPreferences sharedPreferences;
    OperateDao operateDao;
    SetMealDao setMealDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(getResources().getColor(R.color.black));
        }
        setContentView(R.layout.activity_main);
        /**
         * 隐藏标题栏
         * hide title box
         */
        getSupportActionBar().hide();
        sharedPreferences = getSharedPreferences("data",MODE_PRIVATE);
        MergeImage.showGlideImg(this,R.drawable.main,findViewById(R.id.main));
        operateDao = new OperateDao(MainActivity.this);
        Handler handler = new Handler(){
            @Override
            public void handleMessage(@NonNull Message msg) {
                if(msg.what==1) {
                    Bundle bundle = msg.getData();
                    String result = bundle.getString("result");
                    Gson gson = new Gson();
                    Type listtype = new TypeToken<List<Operate>>(){}.getType();
                    List<Operate> operateList = gson.fromJson(result,listtype);
                    System.out.println(operateList.size());
                    if(operateList!=null) {
                        for(int i = 0;i < operateList.size();i++) {
                            if(!operateDao.isExist(operateList.get(i).getId())){
                                operateDao.insert(operateList.get(i));
                            }
                        }
                    }
                }
            }
        };
        String url = StaticHttp.BASEURL+StaticHttp.SELECT_OPERATE_ALL;
        HttpUtils.get(url,handler);

        Handler handlerType = new Handler(){
            @Override
            public void handleMessage(@NonNull Message msg) {
                if(msg.what==1) {
                    Bundle bundle = msg.getData();
                    String result = bundle.getString("result");
                    Gson gson = new Gson();
                    Type listtype = new TypeToken<List<TypeOperate>>(){}.getType();
                    List<TypeOperate> typeOperates = gson.fromJson(result,listtype);
                    for(int i = 0;i < typeOperates.size();i++) {
                        if(!operateDao.isExistT(typeOperates.get(i).gettId())) {
                            operateDao.insert(typeOperates.get(i));
                        }
                    }
                }
            }
        };
        url = StaticHttp.BASEURL+StaticHttp.SELECT_OPERATE_AllTYPE;
        HttpUtils.get(url,handlerType);

        setMealDao = new SetMealDao(this);
        Handler setMealHanlder = new Handler(){
            @Override
            public void handleMessage(@NonNull Message msg) {
                if(msg.what==1) {
                    Bundle bundle = msg.getData();
                    String result = bundle.getString("result");
                    Gson gson = new Gson();
                    Type listtype = new TypeToken<List<SetMeal>>(){}.getType();
                    List<SetMeal> setMeals = gson.fromJson(result,listtype);
                    for(int i = 0;i < setMeals.size();i++) {
                        if(!setMealDao.isExist(setMeals.get(i).getSmId())) {
                            setMealDao.insert(setMeals.get(i));
                        }
                    }
                }
            }
        };
        url = StaticHttp.BASEURL+StaticHttp.SELECT_SETMEAL;
        HttpUtils.get(url,setMealHanlder);
        WindowPixels windowPixels = new WindowPixels(getWindowManager());
        WindowPixels.DENSITY = windowPixels.getDensity();
        WindowPixels.WIDTH = windowPixels.getScreenWidth();
        WindowPixels.HEIGHT = windowPixels.getHeight();
        new CountDownTimer(2000,100) {
            @Override
            public void onTick(long millisUntilFinished) {
            }
            @Override
            public void onFinish() {
                //倒计bai时结束后在这里实现activity跳转du
                Intent intent = new Intent();
                boolean loginStatus = sharedPreferences.getBoolean("loginStatus",false);
                loginStatus = true;
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putLong(StaticHttp.USER_ID,41);
                editor.commit();
                if(loginStatus) {
                    intent.setClass(MainActivity.this, PrincipalSheetActivity.class);
                } else {
                    intent.setClass(MainActivity.this, LoginActivity.class);
                }
                startActivity(intent);
                //跳转后zhi销毁自身的activity 否则按返回 还会跳回到图片dao
                finish();
            }
        }.start();
    }
}
