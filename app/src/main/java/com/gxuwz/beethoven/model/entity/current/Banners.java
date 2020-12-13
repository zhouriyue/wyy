package com.gxuwz.beethoven.model.entity.current;

import android.graphics.Outline;
import android.os.Build;
import android.view.View;
import android.view.ViewOutlineProvider;

import androidx.annotation.RequiresApi;

import com.gxuwz.beethoven.page.index.findview.GlideImageLoader;
import com.gxuwz.beethoven.util.staticdata.StaticHttp;

import java.util.ArrayList;
import java.util.List;

/**
 * 轮播图管理对象 banners
 * 
 * @author zhouriyue
 * @date 2020-12-10
 */
public class Banners
{
    private static final long serialVersionUID = 1L;

    /** 编号 */
    private Long id;

    /** 图片 */
    private String image;

    /** 路径 */
    private String path;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setImage(String image) 
    {
        this.image = image;
    }

    public String getImage() 
    {
        return image;
    }
    public void setPath(String path) 
    {
        this.path = path;
    }

    public String getPath() 
    {
        return path;
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public static void initBanner(com.youth.banner.Banner banner, List<Banners> bannersList) {
        banner.setImageLoader(new GlideImageLoader());
        //设置图片集合
        List<String> images = new ArrayList<>();
        for(int i = 0;i < bannersList.size();i++) {
            images.add(StaticHttp.STATIC_BASEURL+bannersList.get(i).getImage());
        }
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

}
