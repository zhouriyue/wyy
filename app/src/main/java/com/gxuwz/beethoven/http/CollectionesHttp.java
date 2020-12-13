package com.gxuwz.beethoven.http;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.gxuwz.beethoven.adapter.find.collectiones.CollectionesAdapter;
import com.gxuwz.beethoven.model.entity.current.Collectiones;
import com.gxuwz.beethoven.page.fragment.my.FragmentMy;
import com.gxuwz.beethoven.util.HttpUtils;
import com.gxuwz.beethoven.util.ToastUtil;
import com.gxuwz.beethoven.util.staticdata.StaticHttp;

import java.lang.reflect.Type;
import java.util.List;

public class CollectionesHttp {

    /** 歌单列表 **/
    public static void list(Context context,RecyclerView recyclerView,Long userId){
        Handler handler = new Handler(){
            @Override
            public void handleMessage(@NonNull Message msg) {
                super.handleMessage(msg);
                if(msg.what==1) {
                    Bundle bundle = msg.getData();
                    String result = bundle.getString("result");
                    GsonBuilder gsonBuilder = new GsonBuilder();
                    gsonBuilder.setDateFormat("yyyy-MM-dd");
                    Gson gson = gsonBuilder.create();
                    Type listtype = new TypeToken<List<Collectiones>>(){}.getType();
                    List<Collectiones> collectionesList = gson.fromJson(result,listtype);
                    recyclerView.setAdapter(new CollectionesAdapter(context,collectionesList));
                } else {
                    ToastUtil.showToast(context,"网络错误！");
                }
            }
        };
        String url = StaticHttp.BASEURL+StaticHttp.COLLECTIONES_ALL;
        url += "?collectBy="+userId;
        HttpUtils.get(url,handler);
    }

    /** 保存歌单 **/
    public static void save(Context context,Long userId, Long slId){
        Handler handler = new Handler(){
            @Override
            public void handleMessage(@NonNull Message msg) {
                super.handleMessage(msg);
                if(msg.what==1) {
                    ToastUtil.showToast(context,"收藏成功！");
                    Intent intent = new Intent(FragmentMy.UpdateSonglistReceiver.ACTION);
                    intent.putExtra(FragmentMy.UpdateSonglistReceiver.CONTROLLER,"collectiones");
                    context.sendBroadcast(intent);
                } else {
                    ToastUtil.showToast(context,"网络错误！");
                }
            }
        };
        String url = StaticHttp.BASEURL+StaticHttp.COLLECTIONES_ADDAND;
        String data = "collectBy="+userId+"&slId="+slId;
        HttpUtils.post(url,data,handler);
    }

    /** 取消收藏 **/
    public static void cancel(Context context, Long userId, Long slId){
        Handler handler = new Handler(){
            @Override
            public void handleMessage(@NonNull Message msg) {
                super.handleMessage(msg);
                if(msg.what==1){
                    ToastUtil.showToast(context,"取消成功！");
                    Intent intent = new Intent(FragmentMy.UpdateSonglistReceiver.ACTION);
                    intent.putExtra(FragmentMy.UpdateSonglistReceiver.CONTROLLER,"collectiones");
                    context.sendBroadcast(intent);
                } else {
                    ToastUtil.showToast(context,"取消失败！");
                }
            }
        };
        String url = StaticHttp.BASEURL+StaticHttp.COLLECTIONES_CANCEL;
        String data = "userId="+userId+"&slId="+slId;
        HttpUtils.post(url,data,handler);
    }

}
