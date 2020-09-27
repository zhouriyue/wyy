package com.gxuwz.beethoven.page.index.findview.spefun.radiostation;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.annotation.SuppressLint;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.gxuwz.beethoven.R;
import com.gxuwz.beethoven.adapter.find.spefun.radiostation.SRSItemAdapter;
import com.gxuwz.beethoven.model.entity.SysUser;
import com.gxuwz.beethoven.model.entity.find.Banners;
import com.gxuwz.beethoven.model.entity.find.spefun.radiostation.RadioStation;
import com.gxuwz.beethoven.model.entity.find.spefun.radiostation.RadioStationSF;
import com.gxuwz.beethoven.model.entity.find.spefun.radiostation.RadioStationView;
import com.gxuwz.beethoven.model.entity.find.spefun.radiostation.StationsClass;
import com.gxuwz.beethoven.model.entity.find.spefun.telecast.STelecastShow;

import java.util.ArrayList;
import java.util.List;

/**
 * 个性功能  special funation
 * 电台  radio station
 */
public class SRadioStationActivity extends AppCompatActivity {

    RecyclerView radioStationRv;
    List<RadioStationView> sRSShowList;
    LinearLayout toBackLin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(getResources().getColor(R.color.black));
        }
        setContentView(R.layout.sradiostation);
        /**
         * 隐藏标题栏
         * hide title box
         */
        getSupportActionBar().hide();
        findByIdAndNew();
    }

    public void findByIdAndNew(){
        radioStationRv = findViewById(R.id.radio_station_rv);
        toBackLin = findViewById(R.id.to_back_lin);
        toBackLin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        sRSShowList = new ArrayList<RadioStationView>();
        setData();
        radioStationRv.setLayoutManager(new StaggeredGridLayoutManager(1,StaggeredGridLayoutManager.VERTICAL));
        /**
         * recycler 设置流畅度
         */
        radioStationRv.setHasFixedSize(true);
        radioStationRv.setNestedScrollingEnabled(false);
        radioStationRv.setItemViewCacheSize(15);
        RecyclerView.RecycledViewPool recycledViewPool = new RecyclerView.RecycledViewPool();
        radioStationRv.setRecycledViewPool(recycledViewPool);
        radioStationRv.setOnScrollListener(new RecyclerView.OnScrollListener(){
            //用来标记是否正在向最后一个滑动，既是否向下滑动
            boolean isSlidingToLast = false;

            @SuppressLint("WrongConstant")
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                StaggeredGridLayoutManager manager = (StaggeredGridLayoutManager) recyclerView.getLayoutManager();
                // 当不滚动时
                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                    //获取最后一个完全显示的ItemPosition
                    int[] lastVisiblePositions = manager.findLastVisibleItemPositions(new int[manager.getSpanCount()]);
                    int lastVisiblePos = getMaxElem(lastVisiblePositions);
                    int totalItemCount = manager.getItemCount();

                    // 判断是否滚动到底部
                    if (lastVisiblePos == (totalItemCount -1) && isSlidingToLast) {
                        //加载更多功能的代码
                        Toast.makeText(SRadioStationActivity.this,"加载更多",1).show();
                    }
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                //dx用来判断横向滑动方向，dy用来判断纵向滑动方向
                if(dy > 0){
                    //大于0表示，正在向下滚动
                    isSlidingToLast = true;
                }else{
                    //小于等于0 表示停止或向上滚动
                    isSlidingToLast = false;
                }

            }
        });
        radioStationRv.setAdapter(new SRSItemAdapter(SRadioStationActivity.this,sRSShowList));
    }

    public void setData(){
        RadioStationView radioStationView = new RadioStationView();
        radioStationView.setShowType(1);
        Banners banners = new Banners();
        List<String> images = new ArrayList<>();
        images.add("http://kwimg2.kuwo.cn/star/upload/66/85/1575256374021_.jpg");
        images.add("http://kwimg2.kuwo.cn/star/upload/71/68/1575818166158_.jpg");
        images.add("http://kwimg1.kuwo.cn/star/upload/68/54/1575429173078_.jpg");
        banners.setImages(images);
        radioStationView.setBanners(banners);
        sRSShowList.add(radioStationView);

        radioStationView = new RadioStationView();
        radioStationView.setShowType(2);
        List<RadioStationSF> radioStationSFList = new ArrayList<RadioStationSF>();
        RadioStationSF radioStationSF = new RadioStationSF();
        radioStationSF.setIcon("youth");
        radioStationSF.setSfName("电台分类");
        radioStationSFList.add(radioStationSF);

        radioStationSF = new RadioStationSF();
        radioStationSF.setIcon("youth");
        radioStationSF.setSfName("电台分类");
        radioStationSFList.add(radioStationSF);

        radioStationSF = new RadioStationSF();
        radioStationSF.setIcon("youth");
        radioStationSF.setSfName("电台分类");
        radioStationSFList.add(radioStationSF);

        radioStationSF = new RadioStationSF();
        radioStationSF.setIcon("youth");
        radioStationSF.setSfName("电台分类");
        radioStationSFList.add(radioStationSF);
        radioStationView.setRadioStationSFList(radioStationSFList);
        sRSShowList.add(radioStationView);

        radioStationView = new RadioStationView();
        radioStationView.setShowType(3);
        radioStationView.setTitle("听听");
        radioStationView.setTitleIcon("arrow_right_707070");
        List<RadioStation> radioStationList = new ArrayList<RadioStation>();

        RadioStation radioStation = new RadioStation();
        radioStation.setImg("youth");
        radioStation.setName("月亮过了度");
        radioStation.setType("段子手");
        radioStationList.add(radioStation);
        radioStation = new RadioStation();
        radioStation.setImg("youth");
        radioStation.setName("月亮过了度");
        radioStation.setType("段子手");
        radioStationList.add(radioStation);
        radioStation = new RadioStation();
        radioStation.setImg("youth");
        radioStation.setName("月亮过了度");
        radioStation.setType("段子手");
        radioStationList.add(radioStation);
        radioStationView.setRadioStationList(radioStationList);
        sRSShowList.add(radioStationView);

        //电台推荐
        radioStationView = new RadioStationView();
        radioStationView.setShowType(4);
        radioStationView.setIsShowPrice(0);
        radioStationView.setTitle("电台推荐");
        radioStationView.setTagIcon("change");
        radioStationView.setTagTitle("换一换");
        radioStationList = new ArrayList<RadioStation>();

        radioStation = new RadioStation();
        radioStation.setIsPay(1);
        radioStation.setName("阴间打工，阴间走红");
        radioStation.setImg("youth");
        radioStation.setRemark("民当瑶");
        radioStationList.add(radioStation);
        radioStation = new RadioStation();
        radioStation.setIsPay(0);
        radioStation.setName("阴间打工，阴间走红");
        radioStation.setImg("youth");
        radioStation.setRemark("民当瑶");
        radioStationList.add(radioStation);
        radioStation = new RadioStation();
        radioStation.setIsPay(0);
        radioStation.setName("阴间打工，阴间走红");
        radioStation.setImg("youth");
        radioStation.setRemark("民当瑶");
        radioStationList.add(radioStation);
        radioStationView.setRadioStationList(radioStationList);
        sRSShowList.add(radioStationView);

        //精品推荐
        radioStationView = new RadioStationView();
        radioStationView.setShowType(4);
        radioStationView.setIsShowPrice(1);
        radioStationView.setTitle("精品推荐");
        radioStationView.setSubTitle("你值得拥有的优质内容");
        radioStationView.setTagTitle("全部精品");
        radioStationView.setIsShowPrice(1);
        radioStationList = new ArrayList<RadioStation>();

        radioStation = new RadioStation();
        radioStation.setIsPay(1);
        radioStation.setName("阴间打工，阴间走红");
        radioStation.setImg("youth");
        radioStation.setRemark("民当瑶");
        radioStation.setCost(39.9);
        radioStationList.add(radioStation);
        radioStation = new RadioStation();
        radioStation.setIsPay(0);
        radioStation.setName("阴间打工，阴间走红");
        radioStation.setImg("youth");
        radioStation.setRemark("民当瑶");
        radioStation.setCost(39.9);
        radioStationList.add(radioStation);
        radioStation = new RadioStation();
        radioStation.setIsPay(0);
        radioStation.setName("阴间打工，阴间走红");
        radioStation.setImg("youth");
        radioStation.setRemark("民当瑶");
        radioStation.setCost(39.9);
        radioStationList.add(radioStation);
        radioStationView.setRadioStationList(radioStationList);
        sRSShowList.add(radioStationView);

        //创作|翻唱
        radioStationView = new RadioStationView();
        radioStationView.setShowType(5);
        radioStationView.setTitle("创作|翻唱");
        radioStationView.setTitleIcon("arrow_right_707070");
        radioStationView.setTagIcon("play_000");
        radioStationView.setTagTitle("播放全部");
        radioStationList = new ArrayList<RadioStation>();

        radioStation = new RadioStation();
        radioStation.setTitle("我希望你相信，这个世界上总有你想要的音乐，总会有的");
        radioStation.setImg("youth");
        radioStation.setHotNumber(20333);
        SysUser sysUser = new SysUser();
        sysUser.setPerPic("zhoushen1");
        sysUser.setUserName("关于你Mlog-");
        radioStation.setSysUser(sysUser);
        radioStationList.add(radioStation);

        radioStation = new RadioStation();
        radioStation.setTitle("我希望你相信，这个世界上总有你想要的音乐，总会有的");
        radioStation.setImg("youth");
        radioStation.setHotNumber(20333);
        sysUser = new SysUser();
        sysUser.setPerPic("zhoushen1");
        sysUser.setUserName("关于你Mlog-");
        radioStation.setSysUser(sysUser);
        radioStationList.add(radioStation);

        radioStation = new RadioStation();
        radioStation.setTitle("我希望你相信，这个世界上总有你想要的音乐，总会有的");
        radioStation.setImg("youth");
        radioStation.setHotNumber(20333);
        sysUser = new SysUser();
        sysUser.setPerPic("zhoushen1");
        sysUser.setUserName("关于你Mlog-");
        radioStation.setSysUser(sysUser);
        radioStationList.add(radioStation);

        radioStation = new RadioStation();
        radioStation.setTitle("我希望你相信，这个世界上总有你想要的音乐，总会有的");
        radioStation.setImg("youth");
        radioStation.setHotNumber(20333);
        sysUser = new SysUser();
        sysUser.setPerPic("zhoushen1");
        sysUser.setUserName("关于你Mlog-");
        radioStation.setSysUser(sysUser);
        radioStationList.add(radioStation);

        radioStationView.setRadioStationList(radioStationList);
        sRSShowList.add(radioStationView);

        //有书声
        radioStationView = new RadioStationView();
        radioStationView.setShowType(4);
        radioStationView.setTitleIcon("arrow_right_707070");
        radioStationView.setTitle("有书声");
        radioStationList = new ArrayList<RadioStation>();

        radioStation = new RadioStation();
        radioStation.setIsPay(1);
        radioStation.setName("阴间打工，阴间走红");
        radioStation.setImg("youth");
        radioStation.setRemark("民当瑶");
        radioStation.setCost(39.9);
        radioStationList.add(radioStation);
        radioStation = new RadioStation();
        radioStation.setIsPay(0);
        radioStation.setName("阴间打工，阴间走红");
        radioStation.setImg("youth");
        radioStation.setRemark("民当瑶");
        radioStation.setCost(39.9);
        radioStationList.add(radioStation);
        radioStation = new RadioStation();
        radioStation.setIsPay(0);
        radioStation.setName("阴间打工，阴间走红");
        radioStation.setImg("youth");
        radioStation.setRemark("民当瑶");
        radioStation.setCost(39.9);
        radioStationList.add(radioStation);
        radioStationView.setRadioStationList(radioStationList);
        sRSShowList.add(radioStationView);

        //感情基调
        radioStationView = new RadioStationView();
        radioStationView.setShowType(5);
        radioStationView.setTitle("感情基调");
        radioStationView.setTitleIcon("arrow_right_707070");
        radioStationView.setTagIcon("play_000");
        radioStationView.setTagTitle("播放全部");
        radioStationList = new ArrayList<RadioStation>();

        radioStation = new RadioStation();
        radioStation.setTitle("我希望你相信，这个世界上总有你想要的音乐，总会有的");
        radioStation.setImg("youth");
        radioStation.setHotNumber(20333);
        sysUser = new SysUser();
        sysUser.setPerPic("zhoushen1");
        sysUser.setUserName("关于你Mlog-");
        radioStation.setSysUser(sysUser);
        radioStationList.add(radioStation);

        radioStation = new RadioStation();
        radioStation.setTitle("我希望你相信，这个世界上总有你想要的音乐，总会有的");
        radioStation.setImg("youth");
        radioStation.setHotNumber(20333);
        sysUser = new SysUser();
        sysUser.setPerPic("zhoushen1");
        sysUser.setUserName("关于你Mlog-");
        radioStation.setSysUser(sysUser);
        radioStationList.add(radioStation);

        radioStation = new RadioStation();
        radioStation.setTitle("我希望你相信，这个世界上总有你想要的音乐，总会有的");
        radioStation.setImg("youth");
        radioStation.setHotNumber(20333);
        sysUser = new SysUser();
        sysUser.setPerPic("zhoushen1");
        sysUser.setUserName("关于你Mlog-");
        radioStation.setSysUser(sysUser);
        radioStationList.add(radioStation);

        radioStation = new RadioStation();
        radioStation.setTitle("我希望你相信，这个世界上总有你想要的音乐，总会有的");
        radioStation.setImg("youth");
        radioStation.setHotNumber(20333);
        sysUser = new SysUser();
        sysUser.setPerPic("zhoushen1");
        sysUser.setUserName("关于你Mlog-");
        radioStation.setSysUser(sysUser);
        radioStationList.add(radioStation);

        radioStationView.setRadioStationList(radioStationList);
        sRSShowList.add(radioStationView);

        //分类
        radioStationView = new RadioStationView();
        radioStationView.setShowType(6);
        radioStationView.setTitle("全部分类");
        List<StationsClass> stationsClassList = new ArrayList<StationsClass>();
        StationsClass stationsClass = new StationsClass();
        stationsClass.setClassName("创作|翻唱");
        stationsClass.setIcon("icon_microphone_class");
        stationsClassList.add(stationsClass);
        stationsClass = new StationsClass();
        stationsClass.setClassName("创作|翻唱");
        stationsClass.setIcon("icon_microphone_class");
        stationsClassList.add(stationsClass);
        stationsClass = new StationsClass();
        stationsClass.setClassName("创作|翻唱");
        stationsClass.setIcon("icon_microphone_class");
        stationsClassList.add(stationsClass);
        stationsClass = new StationsClass();
        stationsClass.setClassName("创作|翻唱");
        stationsClass.setIcon("icon_microphone_class");
        stationsClassList.add(stationsClass);
        stationsClass = new StationsClass();
        stationsClass.setClassName("创作|翻唱");
        stationsClass.setIcon("icon_microphone_class");
        stationsClassList.add(stationsClass);
        stationsClass = new StationsClass();
        stationsClass.setClassName("创作|翻唱");
        stationsClass.setIcon("icon_microphone_class");
        stationsClassList.add(stationsClass);
        stationsClass = new StationsClass();
        stationsClass.setClassName("创作|翻唱");
        stationsClass.setIcon("icon_microphone_class");
        stationsClassList.add(stationsClass);
        stationsClass = new StationsClass();
        stationsClass.setClassName("创作|翻唱");
        stationsClass.setIcon("icon_microphone_class");
        stationsClassList.add(stationsClass);
        radioStationView.setStationsClassList(stationsClassList);
        sRSShowList.add(radioStationView);
    }

    private int getMaxElem(int[] arr) {
        int size = arr.length;
        int maxVal = Integer.MIN_VALUE;
        for (int i = 0; i < size; i++) {
            if (arr[i]>maxVal)
                maxVal = arr[i];
        }
        return maxVal;
    }
}
