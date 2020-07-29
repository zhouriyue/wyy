package com.gxuwz.beethoven.page.index.findview;

import android.app.Application;
import android.content.Context;
import android.graphics.Outline;
import android.os.Build;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewOutlineProvider;
import android.view.WindowManager;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import androidx.annotation.RequiresApi;

import com.gxuwz.beethoven.R;
import com.gxuwz.beethoven.adapter.find.CityItem;
import com.gxuwz.beethoven.adapter.find.FunListAdapter;
import com.youth.banner.Banner;

import java.util.ArrayList;
import java.util.List;

public class FindViewInit {
    View FindView;
    Banner banner;
    List images;

    List<CityItem> cityList;
    RelativeLayout itmel;
    GridView gridView;
    WindowManager windowManager;
    Context context;

    public FindViewInit(View findView, WindowManager windowManager, Context context) {
        this.FindView = findView;
        this.windowManager = windowManager;
        this.context = context;
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public void init(LayoutInflater layoutInflater){
        findByIdAndNew();
        //设置图片加载器
        banner.setOutlineProvider(new ViewOutlineProvider() {
            @Override
            public void getOutline(View view, Outline outline) {
                outline.setRoundRect(0, 0, view.getWidth(), view.getHeight(), 30);
            }
        });
        banner.setClipToOutline(true);
        banner.setImageLoader(new GlideImageLoader());
        //设置图片集合
        banner.setImages(images);
        //banner设置方法全部调用完毕时最后调用
        banner.start();

        gridView = (GridView) FindView.findViewById(R.id.grid);
        setData();
        setGridView(layoutInflater);
    }

    /**设置GirdView参数，绑定数据*/
    private void setGridView(LayoutInflater layoutInflater) {
        int size = cityList.size();
        int length = 100;
        DisplayMetrics dm = new DisplayMetrics();
        windowManager.getDefaultDisplay().getMetrics(dm);
        float density = dm.density;
        int gridviewWidth = (int) (size * length * density);
        int itemWidth = (int) (length * density);

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                (size+1)*190, LinearLayout.LayoutParams.FILL_PARENT);
        System.out.println("params="+params);
        System.out.println("gridviewWidth="+gridviewWidth);
        System.out.println("itemWidth="+itemWidth);

        gridView.setLayoutParams(params); // 设置GirdView布局参数,横向布局的关键
        gridView.setColumnWidth(190); // 设置列表项宽
        gridView.setHorizontalSpacing(5); // 设置列表项水平间距
        gridView.setStretchMode(GridView.NO_STRETCH);
        gridView.setNumColumns(size); // 设置列数量=列表集合数

        FunListAdapter adapter = new FunListAdapter(context,
                cityList,layoutInflater);
        gridView.setAdapter(adapter);
    }

    /**设置数据*/
    private void setData() {
        cityList = new ArrayList<CityItem>();
        CityItem item = new CityItem();
        item.setCityName("深圳");
        cityList.add(item);
        item = new CityItem();
        item.setCityName("上海");
        cityList.add(item);
        item = new CityItem();
        item.setCityName("广州");
        cityList.add(item);
        item = new CityItem();
        item.setCityName("北京");
        cityList.add(item);
        item = new CityItem();
        item.setCityName("武汉");
        cityList.add(item);

        item = new CityItem();
        item.setCityName("孝感");
        cityList.add(item);

        cityList.addAll(cityList);
    }

    public void findByIdAndNew(){
        banner = FindView.findViewById(R.id.banner);
        images = new ArrayList();
        images.add("http://kwimg2.kuwo.cn/star/upload/66/85/1575256374021_.jpg");
        images.add("http://kwimg2.kuwo.cn/star/upload/71/68/1575818166158_.jpg");
        images.add("http://kwimg1.kuwo.cn/star/upload/68/54/1575429173078_.jpg");
    }
}
