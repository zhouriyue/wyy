package com.gxuwz.beethoven.page.fragment;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.ColorDrawable;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.gxuwz.beethoven.LoginActivity;
import com.gxuwz.beethoven.R;
import com.gxuwz.beethoven.util.staticdata.StaticHttp;

public class MoreFunView {

    Activity activity;
    PopupWindow morePw;
    View moreFun;
    RecyclerMoreFun recyclerMoreFun;

    public MoreFunView(Activity activity) {
        this.activity = activity;
        moreFun = activity.getLayoutInflater().inflate(R.layout.activity_more_fun,null,false);
        TextView switchingAccount = moreFun.findViewById(R.id.switching_account);
        switchingAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sharedPreferences = activity.getSharedPreferences(StaticHttp.DATA, Context.MODE_PRIVATE);
                morePw.dismiss();
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putBoolean(StaticHttp.LONGIN_STATUS,false);
                editor.commit();
                Intent intent = new Intent(activity, LoginActivity.class);
                activity.startActivity(intent);
                activity.finish();
            }
        });
        initView();
    }

    public void initView(){
        recyclerMoreFun = new RecyclerMoreFun(activity,moreFun);
    }

    public void showAtLocation(View view){
        getPopupWindow();
        morePw.showAtLocation(view, Gravity.LEFT,0,0);
    }

    protected  void  initPopupWindow(){
        int width = (int) (activity.getWindowManager().getDefaultDisplay().getWidth()*0.9);
        morePw = new PopupWindow(moreFun,width, ViewGroup.LayoutParams.MATCH_PARENT,true);
        morePw.setBackgroundDrawable(new ColorDrawable(0));
    }

    private  void  getPopupWindow() {
        if (null != morePw) {
            morePw.dismiss();
            return;
        } else {
            initPopupWindow();
        }
    }
}
