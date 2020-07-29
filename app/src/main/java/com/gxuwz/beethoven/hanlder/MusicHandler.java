package com.gxuwz.beethoven.hanlder;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.gxuwz.beethoven.R;
import com.gxuwz.beethoven.util.HttpUtil;
import com.gxuwz.beethoven.util.Player;

import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.io.IOException;

public class MusicHandler extends Handler {

    int back;
    Player mPlayer;
    Context context;
    int position;
    static boolean isPlay = false;

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
                String songUrl = all_json.optString("songUrl");
                /**
                 * 设置播放广播
                 */
                Intent intent = new Intent("CTL_ACTION");
                intent.putExtra("controller",2);
                intent.putExtra("songUrl", HttpUtil.BASEURL+songUrl);
                intent.putExtra("position",position);
                context.sendBroadcast(intent);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public static boolean isIsPlay() {
        return isPlay;
    }

    public static void setIsPlay(boolean isPlay) {
        MusicHandler.isPlay = isPlay;
    }

    public int getBack() {
        return back;
    }

    public void setBack(int back) {
        this.back = back;
    }
}
