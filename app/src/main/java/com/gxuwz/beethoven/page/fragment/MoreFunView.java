package com.gxuwz.beethoven.page.fragment;

import android.app.Activity;
import android.graphics.drawable.ColorDrawable;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;

import androidx.recyclerview.widget.RecyclerView;

import com.gxuwz.beethoven.R;

public class MoreFunView {

    Activity activity;
    PopupWindow morePw;
    View moreFun;
    RecyclerMoreFun recyclerMoreFun;

    public MoreFunView(Activity activity) {
        this.activity = activity;
        moreFun = activity.getLayoutInflater().inflate(R.layout.activity_more_fun,null,false);
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
