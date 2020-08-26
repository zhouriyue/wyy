package com.gxuwz.beethoven.page.index.videoview.official;

import android.content.Context;
import android.os.Build;
import android.view.View;

import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.gxuwz.beethoven.R;
import com.gxuwz.beethoven.page.index.findview.BannerInit;
import com.gxuwz.beethoven.page.index.videoview.telecast.OfficialVideoItem;

public class OfficialViewInit {
    View officialView;
    RecyclerView officialRv;
    BannerInit ofBannerInit;
    OfficialVideoItem officialVideoItem;

    public OfficialViewInit(View officialView) {
        this.officialView = officialView;
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public void init(Context context){
        findByIdAndNew();
        ofBannerInit.init();
        officialVideoItem.init(context);
    }

    public void findByIdAndNew(){
        officialRv = officialView.findViewById(R.id.official_video_rv);
        ofBannerInit = new BannerInit(officialView);
        officialVideoItem = new OfficialVideoItem(officialRv);
    }
}
