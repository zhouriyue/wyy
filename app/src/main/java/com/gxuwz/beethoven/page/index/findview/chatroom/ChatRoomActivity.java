package com.gxuwz.beethoven.page.index.findview.chatroom;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.media.Image;
import android.os.Build;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.gxuwz.beethoven.R;
import com.gxuwz.beethoven.adapter.find.chatroom.ChatRoomerAdapter;
import com.gxuwz.beethoven.model.entity.SysUser;
import com.gxuwz.beethoven.util.HttpUtil;
import com.gxuwz.beethoven.util.MergeImage;
import com.gxuwz.beethoven.util.WindowPixels;

import java.util.ArrayList;
import java.util.List;

public class ChatRoomActivity extends AppCompatActivity {

    Context context;
    LinearLayout chatroomBg;
    List<SysUser> sysUserList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(getResources().getColor(R.color.black));
        }
        setContentView(R.layout.activity_chat_room);
        /**
         * 隐藏标题栏
         */
        getSupportActionBar().hide();
        findByIdAndNew();
    }

    RecyclerView roomerRv;
    public void findByIdAndNew(){
        context = ChatRoomActivity.this;
        chatroomBg = findViewById(R.id.chatroom_bg);
        chatroomBg.setBackground(new BitmapDrawable(HttpUtil.getRes("img_comm",context)));
        roomerRv = findViewById(R.id.roomer_rv);
        sysUserList = new ArrayList<SysUser>();
        setData();
        LinearLayoutManager lm = new LinearLayoutManager(context);
        lm.setOrientation(RecyclerView.HORIZONTAL);
        roomerRv.setLayoutManager(lm);
        roomerRv.setAdapter(new ChatRoomerAdapter(context,sysUserList));
    }

    public void setData(){
        SysUser sysUser = new SysUser();
        sysUser.setPerPic("zhoushen");
        sysUserList.add(sysUser);
        sysUser = new SysUser();
        sysUser.setPerPic("zhoushen1");
        sysUserList.add(sysUser);
        sysUser = new SysUser();
        sysUser.setPerPic("youth");
        sysUserList.add(sysUser);
        sysUser = new SysUser();
        sysUser.setPerPic("zhoushen1");
        sysUserList.add(sysUser);
        sysUser = new SysUser();
        sysUser.setPerPic("youth");
        sysUserList.add(sysUser);
        sysUser = new SysUser();
        sysUser.setPerPic("zhoushen1");
        sysUserList.add(sysUser);
        sysUser = new SysUser();
        sysUser.setPerPic("youth");
        sysUserList.add(sysUser);
    }
}
