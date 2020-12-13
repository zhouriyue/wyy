package com.gxuwz.beethoven.page.index.findview.telecastroom;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.gxuwz.beethoven.R;
import com.gxuwz.beethoven.adapter.find.telecastroom.MessageAdapter;
import com.gxuwz.beethoven.adapter.find.telecastroom.UsersAdapter;
import com.gxuwz.beethoven.model.entity.SysUser;
import com.gxuwz.beethoven.model.entity.find.spefun.telecast.TrMessage;
import com.gxuwz.beethoven.util.HttpUtils;
import com.gxuwz.beethoven.util.MergeImage;

import java.util.ArrayList;
import java.util.List;

/**
 * 直播间 telecast room
 */
public class TelecastRoomActivity extends AppCompatActivity {

    Context context;
    List<SysUser> sysUserList;
    List<TrMessage> trMessageList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(getResources().getColor(R.color.black));
        }
        setContentView(R.layout.activity_telecast_room);
        /**
         * 隐藏标题栏
         */
        getSupportActionBar().hide();
        findByIdAndNew();
    }

    LinearLayout bg;
    ImageView perPic,oneUserImg;
    TextView username,hotNumber,followNumber,onlineNumber;
    RecyclerView usersRv,trMessRv;
    public void findByIdAndNew(){
        context = TelecastRoomActivity.this;
        bg = findViewById(R.id.bg);
        perPic = findViewById(R.id.per_pic);
        oneUserImg = findViewById(R.id.one_user_img);
        username = findViewById(R.id.username);
        hotNumber = findViewById(R.id.hot_number);
        followNumber = findViewById(R.id.follow_number);
        onlineNumber = findViewById(R.id.online_number);
        usersRv = findViewById(R.id.users_rv);
        trMessRv = findViewById(R.id.tr_mess_rv);
        trMessageList = new ArrayList<TrMessage>();
        setData();
        LinearLayoutManager lm = new LinearLayoutManager(context);
        lm.setOrientation(RecyclerView.HORIZONTAL);
        usersRv.setLayoutManager(lm);
        usersRv.setAdapter(new UsersAdapter(context,sysUserList));
        trMessRv.setLayoutManager(new StaggeredGridLayoutManager(1,StaggeredGridLayoutManager.VERTICAL));
        trMessRv.setAdapter(new MessageAdapter(context,trMessageList));
    }

    public void setData(){
        bg.setBackground(new BitmapDrawable(HttpUtils.getRes("zhoushen",context)));
        perPic.setImageBitmap(MergeImage.circleShow(HttpUtils.getRes("youth",context)));
        oneUserImg.setImageBitmap(MergeImage.circleShow(HttpUtils.getRes("youth",context)));
        username.setText("墨涵mm-墨涵mm-墨涵mm");
        hotNumber.setText("2000");
        followNumber.setText("1220");
        onlineNumber.setText("112,936");

        sysUserList = new ArrayList<SysUser>();
        SysUser sysUser = new SysUser();
        sysUser.setPerPic("youth");
        sysUserList.add(sysUser);
        sysUser = new SysUser();
        sysUser.setPerPic("zhoushen");
        sysUserList.add(sysUser);
        sysUser = new SysUser();
        sysUser.setPerPic("zhoushen1");
        sysUserList.add(sysUser);
        //聊天记录
        TrMessage trMessage = new TrMessage();
        sysUser = new SysUser();
        sysUser.setPerPic("zhoushen");
        sysUser.setUserName("惊艳lloveyou");
        trMessage.setContent("我在哪，我是谁");
        trMessage.setSysUser(sysUser);
        trMessageList.add(trMessage);
        trMessage = new TrMessage();
        sysUser = new SysUser();
        sysUser.setPerPic("zhoushen");
        sysUser.setUserName("惊艳lloveyou");
        trMessage.setContent("我在哪，我是谁");
        trMessage.setSysUser(sysUser);
        trMessageList.add(trMessage);
        trMessage = new TrMessage();
        sysUser = new SysUser();
        sysUser.setPerPic("zhoushen");
        sysUser.setUserName("惊艳lloveyou");
        trMessage.setContent("我在哪，我是谁");
        trMessage.setSysUser(sysUser);
        trMessageList.add(trMessage);
        trMessage = new TrMessage();
        sysUser = new SysUser();
        sysUser.setPerPic("zhoushen");
        sysUser.setUserName("惊艳lloveyou");
        trMessage.setContent("我在哪，我是谁");
        trMessage.setSysUser(sysUser);
        trMessageList.add(trMessage);
        trMessage = new TrMessage();
        sysUser = new SysUser();
        sysUser.setPerPic("zhoushen");
        sysUser.setUserName("惊艳lloveyou");
        trMessage.setContent("我在哪，我是谁");
        trMessage.setSysUser(sysUser);
        trMessageList.add(trMessage);
        trMessage = new TrMessage();
        sysUser = new SysUser();
        sysUser.setPerPic("zhoushen");
        sysUser.setUserName("惊艳lloveyou");
        trMessage.setContent("我在哪，我是谁");
        trMessage.setSysUser(sysUser);
        trMessageList.add(trMessage);
    }
}
