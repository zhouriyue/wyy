package com.gxuwz.beethoven;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.content.Intent;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import com.gxuwz.beethoven.page.fragment.PrincipalSheetActivity;
import com.gxuwz.beethoven.util.HttpUtils;
import com.gxuwz.beethoven.util.staticdata.StaticHttp;
import com.huawei.hmf.tasks.OnCompleteListener;
import com.huawei.hmf.tasks.Task;
import com.huawei.hms.common.ApiException;
import com.huawei.hms.support.hwid.HuaweiIdAuthManager;
import com.huawei.hms.support.hwid.request.HuaweiIdAuthParams;
import com.huawei.hms.support.hwid.request.HuaweiIdAuthParamsHelper;
import com.huawei.hms.support.hwid.result.AuthHuaweiId;
import com.huawei.hms.support.hwid.service.HuaweiIdAuthService;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.URLEncoder;

public class LoginActivity extends AppCompatActivity {

    SharedPreferences sharedPreferences;
    HuaweiIdAuthParams authParams;
    HuaweiIdAuthService service;
    CheckBox checkBox;
    TextView displayNameTv;
    String openid;
    String avaterUrl;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(getResources().getColor(R.color.black));
        }
        setContentView(R.layout.activity_login);
        getSupportActionBar().hide();
        init();
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    public void init(){
        //initRegisterTv();
        initData();
        oneKeyLogin();
        editPhone();
        login();
    }

    /**
     * 登录
     */
    public void login(){
        LinearLayout login = findViewById(R.id.login_box);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(checkBox.isChecked()){
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putBoolean(StaticHttp.LONGIN_STATUS,true);
                    editor.commit();
                    Intent intent = new Intent(LoginActivity.this, PrincipalSheetActivity.class);
                    outAuthHw();
                    startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(LoginActivity.this,"没选择协议",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void editPhone(){
        ImageView editIv = findViewById(R.id.edit);
        editIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this,SMSActivity.class);
                startActivity(intent);
            }
        });
    }

    //一键登录
    public void oneKeyLogin(){
        ImageView hwIv = findViewById(R.id.layout_sign_in_api);
        //调转到主页面
        hwIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //拉起华为帐号登录授权页面
                startActivityForResult(service.getSignInIntent(), 8888);
            }
        });
    }

    //登出华为账号
    public void outAuthHw(){
        //service为登录授权时使用getService方法生成的HuaweiIdAuthService实例
        Task<Void> signOutTask = service.signOut();
        signOutTask.addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(Task<Void> task) {
                //完成登出后的处理
                Log.i("tag", "signOut complete");
            }
        });
        //service为登录授权时使用getService方法生成的HuaweiIdAuthService实例
        service.cancelAuthorization().addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(Task<Void> task) {
                if (task.isSuccessful()) {
                    //取消授权成功后的处理
                    Log.i("tag", "onSuccess: ");
                } else {
                    //异常处理
                    Exception exception = task.getException();
                    if (exception instanceof ApiException){
                        int statusCode = ((ApiException) exception).getStatusCode();
                        Log.i("tag", "onFailure: " + statusCode);
                    }
                }
            }
        });
    }

    //一键登录，授权完成后处理登录结果
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        //授权登录结果处理，从AuthHuaweiId中获取Authorization Code
        super.onActivityResult(requestCode, resultCode, data);
        displayNameTv = findViewById(R.id.display_name);
        displayNameTv.addTextChangedListener(new MyTextWatcher());
        if (requestCode == 8888) {
            Task<AuthHuaweiId> authHuaweiIdTask = HuaweiIdAuthManager.parseAuthResultFromIntent(data);
            if (authHuaweiIdTask.isSuccessful()) {
                //登录成功，获取用户的华为帐号信息和Authorization Code
                AuthHuaweiId huaweiAccount = authHuaweiIdTask.getResult();
                Log.i("OpenId:",huaweiAccount.getOpenId());
                Log.i("huaweiAccount:",huaweiAccount.toString());
                Log.i("TAG", "成功---Authorization code:" + huaweiAccount.getAuthorizationCode());
                openid = huaweiAccount.getOpenId();
                displayNameTv.setText(huaweiAccount.getDisplayName());
                avaterUrl = huaweiAccount.getAvatarUriString();
                Log.i("avaterUrl:",avaterUrl);
            } else {
                //登录失败
                Log.e("TAG", "失败---sign in failed : " + ((ApiException)authHuaweiIdTask.getException()).getStatusCode());
            }
        }
    }

    class MyTextWatcher implements TextWatcher{

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
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
                            if(!"null".equals(userId)) {
                                userLogin(Long.parseLong(userId));
                            } else {
                                Intent intent = new Intent(LoginActivity.this,OnKAuthActivity.class);
                                intent.putExtra("openid",openid);
                                intent.putExtra("avaterUrl",avaterUrl);
                                intent.putExtra("displayName",displayNameTv.getText().toString());
                                startActivity(intent);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }
            };
            String url = StaticHttp.BASEURL+StaticHttp.SELECT_OPENID;
            url += "?openid="+ URLEncoder.encode(openid);
            HttpUtils.get(url,handler);
        }
    }

    private void userLogin(Long userId){
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putLong("userId",userId);
        editor.putBoolean(StaticHttp.LONGIN_STATUS,true);
        editor.commit();
        LoginMain();
    }

    public void initData(){
        sharedPreferences = getSharedPreferences(StaticHttp.DATA,MODE_PRIVATE);
        checkBox = findViewById(R.id.check_box);
        boolean checkBoxStatus = sharedPreferences.getBoolean(StaticHttp.CHECK_BOX,false);
        checkBox.setChecked(checkBoxStatus);
        //请求授权
        authParams = new HuaweiIdAuthParamsHelper(HuaweiIdAuthParams.DEFAULT_AUTH_REQUEST_PARAM).setAuthorizationCode().createParams();
        //初始化HuaweiIdAuthService对象
        service = HuaweiIdAuthManager.getService(LoginActivity.this, authParams);
    }

    /** 进入到住页面**/
    public void LoginMain(){
        if(checkBox.isChecked()){
            Intent intent = new Intent(LoginActivity.this, PrincipalSheetActivity.class);
            outAuthHw();
            startActivity(intent);
            finish();
        } else {
            Toast.makeText(LoginActivity.this,"没选择协议",Toast.LENGTH_SHORT).show();
        }
    }
    /**public void initRegisterTv(){
        sharedPreferences = getSharedPreferences(StaticHttp.DATA,MODE_PRIVATE);
        LinearLayout loginBox = findViewById(R.id.login_box);
        loginBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putBoolean(StaticHttp.LONGIN_STATUS,true);
                editor.commit();
                Intent intent = new Intent(LoginActivity.this, PrincipalSheetActivity.class);
                startActivity(intent);
                finish();
            }
        });
        TextView registerTv = findViewById(R.id.register_tv);
        registerTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this,RegisterActivity.class);
                startActivity(intent);
            }
        });
    }*/
}
