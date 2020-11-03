package com.gxuwz.beethoven.hanlder;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.gxuwz.beethoven.model.entity.mlog.SysUserDao;
import com.gxuwz.beethoven.model.entity.SysUser;
import com.gxuwz.beethoven.util.HttpUtil;
import com.gxuwz.beethoven.util.MergeImage;

import org.json.JSONException;
import org.json.JSONObject;

public class SysUserHandler extends Handler {

    TextView userNameView = null;
    ImageView perPicView = null;
    RecyclerView songList = null;
    Context context = null;
    SysUser sysUser = null;
    String songLists = null;
    SysUserDao sysUserDao = null;
    SysUser localSysUser = null;
    Bitmap bitmap = null;

    public void handleMessage(Message msg) {
        super.handleMessage(msg);
        //接收到message之后的处理过程
        if(msg.what ==1 ){
            //获取子线程回传的数据
            Bundle bundle = msg.getData();
            String result = bundle.getString("result");
            if(result==null) return;
            //解析json
            try {
                JSONObject all_json = new JSONObject(result);
                Log.i("sysUser:",all_json.toString());
                String userName = all_json.optString("userName");
                String perPic = all_json.optString("perPic");
                songLists = all_json.getJSONObject("_links").getJSONObject("songLists").optString("href");
                sysUser = new SysUser();
                sysUser.setUserId(userName);
                sysUser.setUserName(userName);
                sysUser.setPerPic(perPic);
                sysUserDao = new SysUserDao(context);
                localSysUser = sysUserDao.findByUserId(userName);
                final Handler perPicViewHandle = new PerPicViewHandle();
                startThreadPic(perPicViewHandle);
                /**
                 * 视图
                 */
                userNameView.setText(userName);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    public void startThreadPic(Handler perPicViewHandle){
        new Thread(new Runnable() {
            @Override
            public void run() {
                String fileName = sysUser.getPerPic().substring(sysUser.getPerPic().lastIndexOf("/")+1);
                bitmap = HttpUtil.getImage(sysUser.getPerPic());
                String url = HttpUtil.saveBitmap(context,bitmap,HttpUtil.USER,fileName);
                Message msg = new Message();
                msg.obj = url;
                perPicViewHandle.sendMessage(msg);
            }
        }).start();
    }

    class PerPicViewHandle extends Handler{

        public void handleMessage(android.os.Message msg) {
            sysUser.setPerPic((String) msg.obj);
            if(localSysUser==null) {
                sysUserDao.insert(sysUser);
            } else {
                sysUserDao.update(sysUser);
            }
            Glide.with(context).load(MergeImage.circleShow(HttpUtil.getLocalImage(sysUser.getPerPic()))).into(perPicView);
            SongListHandler songListHandler = new SongListHandler();
            songListHandler.setSongList(songList);
            songListHandler.setContext(context);
            songListHandler.setSysUser(sysUser);
            HttpUtil.get(songLists,songListHandler);
        };
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
