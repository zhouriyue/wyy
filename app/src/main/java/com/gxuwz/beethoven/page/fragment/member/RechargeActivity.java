package com.gxuwz.beethoven.page.fragment.member;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.gxuwz.beethoven.R;
import com.gxuwz.beethoven.adapter.my.member.SetMealAdapter;
import com.gxuwz.beethoven.dao.SetMealDao;
import com.gxuwz.beethoven.dao.SysUserDao;
import com.gxuwz.beethoven.model.entity.current.SetMeal;
import com.gxuwz.beethoven.model.entity.current.SysUser;
import com.gxuwz.beethoven.util.MergeImage;
import com.gxuwz.beethoven.util.staticdata.StaticHttp;

import java.util.List;

public class RechargeActivity extends AppCompatActivity {

    SharedPreferences sharedPreferences;

    ImageView perPic;
    TextView usernameTv,phoneTv,payPriceTv;
    SetMealDao setMealDao;
    SysUserDao sysUserDao;
    SysUser sysUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(getResources().getColor(R.color.black));
        }
        setContentView(R.layout.activity_recharge);
        /**
         * 隐藏标题栏
         * hide title box
         */
        getSupportActionBar().hide();
        sharedPreferences = getSharedPreferences("data",MODE_PRIVATE);
        init();
    }

    public void init(){
        initIcon();
        initPerMess();
        initSetMealList();
    }

    public void initSetMealList(){
        if(setMealDao==null) {
            setMealDao = new SetMealDao(this);
        }
        payPriceTv = findViewById(R.id.pay_price_tv);
        List<SetMeal> setMealList = setMealDao.findAll();
        RecyclerView setMealRv = findViewById(R.id.set_meal_rv);
        LinearLayoutManager lm = new LinearLayoutManager(this);
        lm.setOrientation(RecyclerView.HORIZONTAL);
        setMealRv.setItemViewCacheSize(20);
        setMealRv.setLayoutManager(lm);
        setMealRv.setAdapter(new SetMealAdapter(this,setMealList,payPriceTv));
    }

    public void initPerMess(){
        if(sysUserDao==null) {
            sysUserDao = new SysUserDao(this);
        }
        perPic = findViewById(R.id.per_pic);
        usernameTv = findViewById(R.id.username);
        phoneTv = findViewById(R.id.phone);
        long userId = sharedPreferences.getLong("userId",-1);
        sysUser = sysUserDao.findById(userId);
        MergeImage.showGlideImgDb(this,StaticHttp.STATIC_BASEURL+sysUser.getAvatar(),perPic,24);
        usernameTv.setText(sysUser.getNickName());
        phoneTv.setText(sysUser.getPhonenumber());
    }

    public void initIcon(){
        //返回按钮
        ImageView toBack = findViewById(R.id.to_back);
        toBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        //显示阿里图标
        ImageView alipayIv = findViewById(R.id.alipay_iv);
        MergeImage.showGlideImgDb(this,StaticHttp.ICON_ALIPAY,alipayIv,1);

        //微信图标
        ImageView wxIv = findViewById(R.id.wx_iv);
        MergeImage.showGlideImgDb(this,StaticHttp.ICON_WXPAY,wxIv,1);
    }

}
