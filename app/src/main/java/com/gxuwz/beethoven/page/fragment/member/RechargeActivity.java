package com.gxuwz.beethoven.page.fragment.member;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.content.IntentSender;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.gxuwz.beethoven.R;
import com.gxuwz.beethoven.adapter.my.member.SetMealAdapter;
import com.gxuwz.beethoven.dao.SetMealDao;
import com.gxuwz.beethoven.dao.SysUserDao;
import com.gxuwz.beethoven.model.entity.current.Member;
import com.gxuwz.beethoven.model.entity.current.SetMeal;
import com.gxuwz.beethoven.model.entity.current.SysUser;
import com.gxuwz.beethoven.util.MergeImage;
import com.gxuwz.beethoven.util.staticdata.StaticHttp;
import com.huawei.hmf.tasks.OnFailureListener;
import com.huawei.hmf.tasks.OnSuccessListener;
import com.huawei.hmf.tasks.Task;
import com.huawei.hms.iap.Iap;
import com.huawei.hms.iap.IapApiException;
import com.huawei.hms.iap.entity.IsEnvReadyResult;
import com.huawei.hms.iap.entity.OrderStatusCode;
import com.huawei.hms.iap.entity.ProductInfo;
import com.huawei.hms.iap.entity.ProductInfoReq;
import com.huawei.hms.iap.entity.ProductInfoResult;
import com.huawei.hms.iap.entity.PurchaseIntentReq;
import com.huawei.hms.iap.entity.PurchaseIntentResult;
import com.huawei.hms.iap.entity.PurchaseResultInfo;
import com.huawei.hms.iap.util.IapClientHelper;
import com.huawei.hms.support.api.client.Status;

import java.util.ArrayList;
import java.util.Date;
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
        initPay();
    }

    public void initPay(){
        TextView payBtn = findViewById(R.id.pay_btn);
        payBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showCommodity();
            }
        });
    }

    /** 验证是否登录**/
    public void checkLongin(){
        // 获取调用接口的Activity对象
        Activity activity = this;
        Task<IsEnvReadyResult> task = Iap.getIapClient(activity).isEnvReady();
        task.addOnSuccessListener(new OnSuccessListener<IsEnvReadyResult>() {
            @Override
            public void onSuccess(IsEnvReadyResult result) {
                // 获取接口请求的结果

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(Exception e) {
                if (e instanceof IapApiException) {
                    IapApiException apiException = (IapApiException) e;
                    Status status = apiException.getStatus();
                    if (status.getStatusCode() == OrderStatusCode.ORDER_HWID_NOT_LOGIN) {
                        // 未登录帐号
                        if (status.hasResolution()) {
                            try {
                                // 6666是开发者自定义的int类型常量
                                // 启动IAP返回的登录页面
                                status.startResolutionForResult(activity, 6666);
                            } catch (IntentSender.SendIntentException exp) {
                            }
                        }
                    } else if (status.getStatusCode() == OrderStatusCode.ORDER_ACCOUNT_AREA_NOT_SUPPORTED) {
                        // 用户当前登录的华为帐号所在的服务地不在华为IAP支持结算的国家或地区中
                        Toast.makeText(RechargeActivity.this,"用户当前登录的华为帐号所在的服务地不在华为IAP支持结算的国家或地区中",Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

    public void showCommodity(){
        List<String> productIdList = new ArrayList<>();
        // 查询的商品必须是开发者在AppGallery Connect网站配置的商品
        productIdList.add("sm_8");
        ProductInfoReq req = new ProductInfoReq();
        // priceType: 0：消耗型商品; 1：非消耗型商品; 2：订阅型商品
        req.setPriceType(0);
        req.setProductIds(productIdList);
        // 获取调用接口的Activity对象
        Activity activity = this;
        // 调用obtainProductInfo接口获取AppGallery Connect网站配置的商品的详情信息
        Task<ProductInfoResult> task = Iap.getIapClient(activity).obtainProductInfo(req);
        task.addOnSuccessListener(new OnSuccessListener<ProductInfoResult>() {
            @Override
            public void onSuccess(ProductInfoResult result) {
                // 获取接口请求成功时返回的商品详情信息
                List<ProductInfo> productList = result.getProductInfoList();
                for(ProductInfo productInfo:productList){
                    System.out.println(productInfo.toString());
                }
                buyRequest();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(Exception e) {
                if (e instanceof IapApiException) {
                    IapApiException apiException = (IapApiException) e;
                    int returnCode = apiException.getStatusCode();
                } else {
                    // 其他外部错误
                }
            }
        });
    }

    public void buyRequest(){
        // 构造一个PurchaseIntentReq对象
        PurchaseIntentReq req = new PurchaseIntentReq();
        // 通过createPurchaseIntent接口购买的商品必须是开发者在AppGallery Connect网站配置的商品。
        req.setProductId("sm_8");
        // priceType: 0：消耗型商品; 1：非消耗型商品; 2：订阅型商品
        req.setPriceType(0);
        req.setDeveloperPayload(new Date().toString());
        // 获取调用接口的Activity对象
        Activity activity = this;
        // 调用createPurchaseIntent接口创建托管商品订单
        Task<PurchaseIntentResult> task = Iap.getIapClient(activity).createPurchaseIntent(req);
        task.addOnSuccessListener(new OnSuccessListener<PurchaseIntentResult>() {
            @Override
            public void onSuccess(PurchaseIntentResult result) {
                // 获取创建订单的结果
                Status status = result.getStatus();
                if (status.hasResolution()) {
                    try {
                        // 6666是开发者自定义的int类型常量
                        // 启动IAP返回的收银台页面
                        status.startResolutionForResult(activity, 6666);
                    } catch (IntentSender.SendIntentException exp) {
                    }
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(Exception e) {
                if (e instanceof IapApiException) {
                    IapApiException apiException = (IapApiException) e;
                    Status status = apiException.getStatus();
                    int returnCode = apiException.getStatusCode();
                } else {
                    // 其他外部错误
                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 6666) {
            if (data == null) {
                Log.e("onActivityResult", "data is null");
                return;
            }
            // 调用parsePurchaseResultInfoFromIntent方法解析支付结果数据
            PurchaseResultInfo purchaseResultInfo = Iap.getIapClient(this).parsePurchaseResultInfoFromIntent(data);
            switch(purchaseResultInfo.getReturnCode()) {
                case OrderStatusCode.ORDER_STATE_CANCEL:
                    // 用户取消
                    break;
                case OrderStatusCode.ORDER_STATE_FAILED:
                case OrderStatusCode.ORDER_PRODUCT_OWNED:
                    // 检查是否存在未发货商品
                    break;
                case OrderStatusCode.ORDER_STATE_SUCCESS:
                    // 支付成功
                    String inAppPurchaseData = purchaseResultInfo.getInAppPurchaseData();
                    String inAppPurchaseDataSignature = purchaseResultInfo.getInAppDataSignature();
                    // 使用开发者应用的IAP公钥验证签名
                    // 若验签成功，则进行发货
                    // 若用户购买商品为消耗型商品，开发者需要在发货成功后调用consumeOwnedPurchase接口进行消耗
                    break;
                default:
                    break;
            }
        }
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
        TextView memberStatus = findViewById(R.id.member_status);
        Intent intent = getIntent();
        Member member = (Member) intent.getSerializableExtra("member");
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
