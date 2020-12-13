package com.gxuwz.beethoven;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.gxuwz.beethoven.model.entity.current.SysUser;
import com.gxuwz.beethoven.page.fragment.PrincipalSheetActivity;
import com.gxuwz.beethoven.util.HttpUtils;
import com.gxuwz.beethoven.util.MergeImage;
import com.gxuwz.beethoven.util.staticdata.StaticHttp;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.URLEncoder;

public class OnKAuthActivity extends AppCompatActivity {

    boolean isUsername = false;
    boolean isPwd = false;
    boolean isPassPwd = false;
    int pwdStatus = 0;
    SysUser sysUser = null;
    String username;
    String pwd;
    String passPwd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(getResources().getColor(R.color.black));
        }
        setContentView(R.layout.activity_on_kauth);
        getSupportActionBar().hide();
        init();
    }

    public void init(){
        checkUserName();
        checkPwd();
        checkPassPwd();
        checkRegister();
    }

    //验证用户名
    public void checkUserName(){
        EditText usernameTv = findViewById(R.id.username);
        ImageView usernameIv = findViewById(R.id.username_iv);
        TextView checkUsername = findViewById(R.id.check_username);
        usernameTv.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                Handler handler = new Handler(){
                    @Override
                    public void handleMessage(@NonNull Message msg) {
                        if(msg.what==1) {
                            Bundle bundle = msg.getData();
                            String result  = bundle.getString("result");
                            if("1".equals(result)) {
                                MergeImage.showGlideImg(OnKAuthActivity.this,R.drawable.icon_error_red,usernameIv);
                                checkUsername.setVisibility(View.VISIBLE);
                                checkUsername.setText("用户名已存在");
                                isUsername = false;
                            } else {
                                MergeImage.showGlideImg(OnKAuthActivity.this,R.drawable.icon_right,usernameIv);
                                checkUsername.setVisibility(View.GONE);
                                isUsername = true;
                            }
                        }
                    }
                };
                String url = StaticHttp.SYSTEM_BASEURL + StaticHttp.CHECK_USERNAME;
                username = usernameTv.getText().toString();
                url += "?username="+ URLEncoder.encode(username);
                HttpUtils.get(url,handler);
            }
        });
    }

    //校验密码格式是否正确
    public void checkPwd(){
        EditText pwdTv = findViewById(R.id.pwd);
        TextView checkMess = findViewById(R.id.check_mess);
        ImageView pwdIv = findViewById(R.id.pwd_iv);
        pwdTv.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                pwd = pwdTv.getText().toString();
                if(pwd.matches("[\\da-zA-z]+")){
                    if(pwd.matches("[a-zA-z]]+")) {
                        //全为字母
                        pwdStatus = 2;
                    } else {
                        if(pwd.matches("[0-9]+")){
                            //全为数字
                            pwdStatus = 3;
                        } else {
                            pwdStatus = 0;
                        }
                    }
                } else {
                    //包含特殊字符
                    pwdStatus = 1;
                }
                System.out.println(pwdStatus);
                switch (pwdStatus) {
                    case 0:{
                        checkMess.setVisibility(View.GONE);
                        MergeImage.showGlideImg(OnKAuthActivity.this,R.drawable.icon_right,pwdIv);
                        isPwd = true;
                    };break;
                    case 1:{
                        checkMess.setVisibility(View.VISIBLE);
                        checkMess.setText("包含特殊字符");
                        MergeImage.showGlideImg(OnKAuthActivity.this,R.drawable.icon_error_red,pwdIv);
                        isPwd = false;
                    };break;
                    case 2:{
                        checkMess.setVisibility(View.VISIBLE);
                        checkMess.setText("密码不能全为字母");
                        MergeImage.showGlideImg(OnKAuthActivity.this,R.drawable.icon_error_red,pwdIv);
                        isPwd = false;
                    };break;
                    case 3:{
                        checkMess.setVisibility(View.VISIBLE);
                        checkMess.setText("密码不能全为数字");
                        MergeImage.showGlideImg(OnKAuthActivity.this,R.drawable.icon_error_red,pwdIv);
                        isPwd = false;
                    };break;
                }
            }
        });
    }

    //校验确认密码是否通过
    public void checkPassPwd(){
        EditText passPwdEt = findViewById(R.id.pass_pwd);
        ImageView passPwdIv = findViewById(R.id.pass_pwd_iv);

        passPwdEt.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                passPwd = passPwdEt.getText().toString();
                if(passPwd!=null&&passPwd.equals(pwd)) {
                    MergeImage.showGlideImg(OnKAuthActivity.this,R.drawable.icon_right,passPwdIv);
                    isPassPwd = true;
                } else {
                    MergeImage.showGlideImg(OnKAuthActivity.this,R.drawable.icon_error_red,passPwdIv);
                    isPassPwd = false;
                }
            }
        });
    }

    //效验注册
    public void checkRegister(){
        LinearLayout loginBox = findViewById(R.id.login_box);
        loginBox.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("WrongConstant")
            @Override
            public void onClick(View v) {
                if(isUsername&&isPwd&&isPassPwd) {
                    register();
                    Handler handler = new Handler(){
                        @Override
                        public void handleMessage(@NonNull Message msg) {
                            super.handleMessage(msg);
                            if(msg.what==1) {
                                Bundle bundle = msg.getData();
                                String result = bundle.getString("result");
                                try {
                                    JSONObject jsonObject = new JSONObject(result);
                                    String userId = jsonObject.getString("userId");
                                    if("null".equals(userId)) {
                                        Toast.makeText(OnKAuthActivity.this,"账号已存在请登录！",Toast.LENGTH_SHORT).show();
                                    } else {
                                        register();
                                    }
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                    };
                    String url = StaticHttp.BASEURL+StaticHttp.SELECT_OPENID;
                    url += "?openid="+ URLEncoder.encode(getIntent().getStringExtra("openid"));
                    HttpUtils.get(url,handler);
                } else {
                    Toast.makeText(OnKAuthActivity.this,"请修改好信息再提交!",Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    //注册操作
    private void register() {
        Handler handler = new Handler() {
            @Override
            public void handleMessage(@NonNull Message msg) {
                if (msg.what == 1) {
                    Bundle bundle = msg.getData();
                    String result = bundle.getString("result");
                    Log.i("tag",result);
                    try {
                        JSONObject all_json = new JSONObject(result);
                        initUserData(all_json);
                        SharedPreferences.Editor editor = getSharedPreferences(StaticHttp.DATA,MODE_PRIVATE).edit();
                        editor.putLong("userId",sysUser.getUserId());
                        editor.putBoolean(StaticHttp.LONGIN_STATUS,true);
                        editor.commit();
                        Intent intent = new Intent(OnKAuthActivity.this, PrincipalSheetActivity.class);
                        startActivity(intent);
                        finish();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        String url = StaticHttp.SYSTEM_BASEURL + StaticHttp.REGISTER_USER;
        Intent intent = getIntent();
        String data = "";
        data+="userName="+URLEncoder.encode(username);
        data+="&password="+URLEncoder.encode(passPwd);
        data+="&openid="+URLEncoder.encode(intent.getStringExtra("openid"));
        data+="&avatorUrl="+URLEncoder.encode(intent.getStringExtra("avaterUrl"));
        data+="&displayName="+URLEncoder.encode(intent.getStringExtra("displayName"));
        HttpUtils.post(url,data, handler);
    }

    /** 初始化user、userDetail数据*/
    public void initUserData(JSONObject all_json) throws JSONException {
        if(sysUser == null) {
            sysUser = new SysUser();
        }
        sysUser.setUserId(all_json.getLong("userId"));
    }
}
