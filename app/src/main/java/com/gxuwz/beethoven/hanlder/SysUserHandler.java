package com.gxuwz.beethoven.hanlder;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.gxuwz.beethoven.model.entity.SongList;
import com.gxuwz.beethoven.model.entity.SysUser;
import com.gxuwz.beethoven.util.HttpUtil;
import com.gxuwz.beethoven.util.MergeImage;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.List;

public class SysUserHandler extends Handler {

    TextView userNameView = null;
    ImageView perPicView = null;
    RecyclerView songList = null;
    Context context = null;
    Bitmap usernameUrilB = null;
    SysUser sysUser = null;
    String songLists = null;

    public void handleMessage(Message msg) {
        super.handleMessage(msg);
        //接收到message之后的处理过程
        if(msg.what ==1 ){
            //获取子线程回传的数据
            Bundle bundle = msg.getData();
            String result = bundle.getString("result");
            //解析json
            try {
                JSONObject all_json = new JSONObject(result);
                String userName = all_json.optString("userName");
                String perPic = all_json.optString("perPic");
                songLists = all_json.getJSONObject("_links").getJSONObject("songLists").optString("href");
                sysUser = new SysUser();
                sysUser.setUserName(userName);
                sysUser.setPerPic(perPic);
                /**
                 * 视图
                 */
                System.out.println(userNameView);
                userNameView.setText(userName);
                final Handler perPicViewHandle = new Handler() {
                    public void handleMessage(android.os.Message msg) {
                        usernameUrilB = (Bitmap) msg.obj;
                        usernameUrilB = MergeImage.circleShow(usernameUrilB);
                        perPicView.setImageBitmap(usernameUrilB);
                        SongListHandler songListHandler = new SongListHandler();
                        songListHandler.setSongList(songList);
                        songListHandler.setContext(context);
                        songListHandler.usernameUrilB = usernameUrilB;
                        songListHandler.setSysUser(sysUser);
                        HttpUtil.get(songLists,songListHandler);

                    };
                };
                new Thread(){
                    @Override
                    public void run() {
                        Bitmap imageDate = HttpUtil.getImage(perPic);
                        Message msg = new Message();
                        msg.obj = imageDate;
                        perPicViewHandle.sendMessage(msg);
                    }
                }.start();
            } catch (JSONException e) {
                e.printStackTrace();
            }


            /**
             * 获取歌单
             */

        }
    }

    public TextView getUserNameView() {
        return userNameView;
    }

    public void setUserNameView(TextView userNameView) {
        this.userNameView = userNameView;
    }

    public ImageView getPerPicView() {
        return perPicView;
    }

    public void setPerPicView(ImageView perPicView) {
        this.perPicView = perPicView;
    }

    public RecyclerView getSongList() {
        return songList;
    }

    public void setSongList(RecyclerView songList) {
        this.songList = songList;
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }
}
