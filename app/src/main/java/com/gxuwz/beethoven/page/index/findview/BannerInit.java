package com.gxuwz.beethoven.page.index.findview;

import android.graphics.Outline;
import android.os.Build;
import android.view.View;
import android.view.ViewOutlineProvider;

import androidx.annotation.RequiresApi;

import com.gxuwz.beethoven.R;
import com.youth.banner.Banner;

import java.util.ArrayList;
import java.util.List;

public class BannerInit {
    View view;
    Banner banner;
    List images;

    public BannerInit(View view) {
        this.view = view;
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public void init(){
        findByIdAndNew();

        banner.setImageLoader(new GlideImageLoader());
        //设置图片集合
        banner.setImages(images);
        //banner设置方法全部调用完毕时最后调用
        banner.setOutlineProvider(new ViewOutlineProvider() {
            @Override
            public void getOutline(View view, Outline outline) {
                outline.setRoundRect(0, 0, view.getWidth(), view.getHeight(), 30);
            }
        });
        banner.setClipToOutline(true);
        banner.start();
    }

    public void findByIdAndNew(){
        banner = view.findViewById(R.id.banner);
        images = new ArrayList();
        images.add("http://kwimg2.kuwo.cn/star/upload/66/85/1575256374021_.jpg");
        images.add("http://kwimg2.kuwo.cn/star/upload/71/68/1575818166158_.jpg");
        images.add("http://kwimg1.kuwo.cn/star/upload/68/54/1575429173078_.jpg");
    }
}
