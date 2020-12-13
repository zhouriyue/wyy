package com.gxuwz.beethoven.page.fragment.my;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.ColorDrawable;
import android.os.Handler;
import android.os.Message;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.gxuwz.beethoven.R;
import com.gxuwz.beethoven.util.HttpUtils;
import com.gxuwz.beethoven.util.WindowPixels;
import com.gxuwz.beethoven.util.staticdata.StaticHttp;

import java.net.URLEncoder;

/**
 * 添加歌单弹框
 */
public class AddSongListPW {

    Context context;
    SharedPreferences sharedPreferences;
    PopupWindow addSongList;
    View addSLView;
    LinearLayout addBox;
    TextView submit,cancel;

    EditText songlistName;
    CheckBox isPublicC;

    public AddSongListPW(Context context) {
        this.context = context;
        this.addSLView = LayoutInflater.from(context).inflate(R.layout.songlist_add,null);
        this.sharedPreferences = context.getSharedPreferences("data", Context.MODE_PRIVATE);
        this.addBox = addSLView.findViewById(R.id.add_box);
        int scopeX1 = (int) (20*WindowPixels.DENSITY);
        int scopeY1 = (int) (WindowPixels.HEIGHT/2-120*WindowPixels.DENSITY);
        int scopeX2 = (int) ((WindowPixels.WIDTH-20)*WindowPixels.DENSITY);
        int scopeY2 = scopeY1+(int)(240*WindowPixels.DENSITY);
        this.addSLView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(event.getX()>scopeX2||event.getX()<scopeX1||event.getY()>scopeY2||event.getY()<scopeY1){
                    addSongList.dismiss();
                }
                return false;
            }
        });
        this.submit = addSLView.findViewById(R.id.submit);
        this.songlistName = addSLView.findViewById(R.id.songlist_name);
        this.isPublicC = addSLView.findViewById(R.id.is_public);
        this.submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Handler handler = new Handler(){
                    @Override
                    public void handleMessage(@NonNull Message msg) {
                        if(msg.what==1) {
                            Intent intent = new Intent(FragmentMy.UpdateSonglistReceiver.ACTION);
                            intent.putExtra(FragmentMy.UpdateSonglistReceiver.CONTROLLER,"songlist");
                            context.sendBroadcast(intent);
                        }
                    }
                };
                String url = StaticHttp.BASEURL+StaticHttp.ADD_SONGLIST;
                String data = "";
                String slName = songlistName.getText().toString();
                Integer isPublic = 1;
                if(isPublicC.isChecked()){
                    isPublic = 0;
                }
                data+="createById="+ URLEncoder.encode(String.valueOf(sharedPreferences.getLong("userId",-1)));
                data+="&slName=" + URLEncoder.encode(slName);
                data+="&isPublic=" + URLEncoder.encode(String.valueOf(isPublic));
                HttpUtils.post(url,data,handler);
                addSongList.dismiss();
            }
        });
        this.cancel = addSLView.findViewById(R.id.cancel);
        this.cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addSongList.dismiss();
            }
        });
    }

    public void showAtLocation(View view){
        getPopupWindow();
        addSongList.showAtLocation(view, Gravity.LEFT,0,0);
    }

    protected  void  initPopupWindow(){
        int width = (int) (WindowPixels.WIDTH*WindowPixels.DENSITY)+3;
        addSongList = new PopupWindow(addSLView,width, ViewGroup.LayoutParams.MATCH_PARENT,true);
        addSongList.setBackgroundDrawable(new ColorDrawable(0));
    }

    public void  getPopupWindow() {
        if (null != addSongList) {
            addSongList.dismiss();
            return;
        } else {
            initPopupWindow();
        }
    }
}
