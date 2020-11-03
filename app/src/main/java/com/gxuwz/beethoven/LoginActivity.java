package com.gxuwz.beethoven;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.content.Intent;
import android.os.Build;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.RequiresApi;

import com.gxuwz.beethoven.page.fragment.PrincipalSheetActivity;

public class LoginActivity extends AppCompatActivity {

    SharedPreferences sharedPreferences;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(getResources().getColor(R.color.black));
        }
        setContentView(R.layout.activity_login);
        getSupportActionBar().hide();
        init();
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    public void init(){
        initRegisterTv();
    }

    public void initRegisterTv(){
        sharedPreferences = getSharedPreferences("data",MODE_PRIVATE);
        TextView loginTv = findViewById(R.id.login_tv);
        loginTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putBoolean("loginStatus",true);
                editor.commit();
                Intent intent = new Intent(LoginActivity.this, PrincipalSheetActivity.class);
                startActivity(intent);
                finish();
            }
        });
        TextView registerTv = findViewById(R.id.register_tv);
        registerTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this,RegisterActivity.class);
                startActivity(intent);
            }
        });
    }
}
