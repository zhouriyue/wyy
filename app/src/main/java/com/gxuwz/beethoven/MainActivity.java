package com.gxuwz.beethoven;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;

import com.gxuwz.beethoven.page.index.Index;

public class MainActivity extends AppCompatActivity {

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
        new CountDownTimer(3000,100) {
            @Override
            public void onTick(long millisUntilFinished) {
            }
            @Override
            public void onFinish() {
                //倒计bai时结束后在这里实现activity跳转du
                Intent intent = new Intent();
                intent.setClass(MainActivity.this, Index.class);
                startActivity(intent);
                //跳转后zhi销毁自身的activity 否则按返回 还会跳回到图片dao
                finish();
            }
        }.start();
    }
}
