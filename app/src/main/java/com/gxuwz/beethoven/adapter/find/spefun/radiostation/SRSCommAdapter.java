package com.gxuwz.beethoven.adapter.find.spefun.radiostation;

import android.content.Context;
import android.graphics.Typeface;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.gxuwz.beethoven.R;
import com.gxuwz.beethoven.model.entity.SysUser;
import com.gxuwz.beethoven.model.entity.find.Banners;
import com.gxuwz.beethoven.model.entity.find.spefun.radiostation.RadioStation;
import com.gxuwz.beethoven.model.entity.find.spefun.radiostation.RadioStationSF;
import com.gxuwz.beethoven.model.entity.find.spefun.radiostation.RadioStationView;
import com.gxuwz.beethoven.model.entity.find.spefun.radiostation.StationsClass;
import com.gxuwz.beethoven.util.HttpUtil;
import com.gxuwz.beethoven.util.MergeImage;
import com.gxuwz.beethoven.util.WindowPixels;
import com.gxuwz.beethoven.viewholder.find.spefun.radiostation.BannerViewHolder;
import com.gxuwz.beethoven.viewholder.find.spefun.radiostation.CreateCoverViewHolder;
import com.gxuwz.beethoven.viewholder.find.spefun.radiostation.ListenerViewHolder;
import com.gxuwz.beethoven.viewholder.find.spefun.radiostation.RSSFViewHolder;
import com.gxuwz.beethoven.viewholder.find.spefun.radiostation.SRecommendViewHolder;
import com.gxuwz.beethoven.viewholder.find.spefun.radiostation.StationClassViewHolder;

public class SRSCommAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    Context context;
    RadioStationView radioStationView;
    int itemWidth = (int) ((WindowPixels.WIDTH-60)/3);

    public SRSCommAdapter(Context context, RadioStationView radioStationView) {
        this.context = context;
        this.radioStationView = radioStationView;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder viewHolder = null;
        switch (viewType){
            case 1:{
                viewHolder = new BannerViewHolder(LayoutInflater.from(context).inflate(R.layout.item_banner,null));
            };break;
            case 2:{
                viewHolder = new RSSFViewHolder(LayoutInflater.from(context).inflate(R.layout.item_srs_sf,null));
            };break;
            case 3:{
                viewHolder = new ListenerViewHolder(LayoutInflater.from(context).inflate(R.layout.item_srs_listener,null));
            };break;
            case 4:{
                viewHolder = new SRecommendViewHolder(LayoutInflater.from(context).inflate(R.layout.item_srs_recommend,null));
            };break;
            case 5:{
                viewHolder = new CreateCoverViewHolder(LayoutInflater.from(context).inflate(R.layout.item_srs_createcover,null));
            };break;
            case 6:{
                viewHolder = new StationClassViewHolder(LayoutInflater.from(context).inflate(R.layout.item_srs_stationclass,null));
            };break;
        }
        return viewHolder;
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        switch (radioStationView.getShowType()){
            case 1:{
                BannerViewHolder  bannerViewHolder = (BannerViewHolder) holder;
                Banners.initBanner((com.youth.banner.Banner) bannerViewHolder.banner, radioStationView.getBanners().getImages());
            };break;
            case 2:{
                RSSFViewHolder rssfViewHolder = (RSSFViewHolder) holder;
                RadioStationSF stationSF = radioStationView.getRadioStationSFList().get(position);
                rssfViewHolder.icon.setImageBitmap(MergeImage.circleShow(HttpUtil.getRes(stationSF.getIcon(),context)));
                rssfViewHolder.sfName.setText(stationSF.getSfName());
            };break;
            case 3:{
                ListenerViewHolder listenerViewHolder = (ListenerViewHolder) holder;
                RadioStation radioStation = radioStationView.getRadioStationList().get(position);
                listenerViewHolder.img.setImageBitmap(MergeImage.roundedCustomDB(HttpUtil.getRes(radioStation.getImg(),context),itemWidth,itemWidth,5));
                listenerViewHolder.name.setText(radioStation.getName());
                listenerViewHolder.type.setText(radioStation.getType());
            };break;
            case 4:{
                SRecommendViewHolder sRecommendViewHolder = (SRecommendViewHolder) holder;
                RadioStation radioStation = radioStationView.getRadioStationList().get(position);
                sRecommendViewHolder.img.setImageBitmap(MergeImage.roundedCustomDB(HttpUtil.getRes(radioStation.getImg(),context),itemWidth,itemWidth,5));
                sRecommendViewHolder.name.setText(radioStation.getName());
                if(radioStation.getIsPay()==1) {
                    sRecommendViewHolder.rsIsPay.setText("精品付费");
                }
                if(radioStationView.getIsShowPrice()==1) {
                    sRecommendViewHolder.costLin.setVisibility(View.VISIBLE);
                    sRecommendViewHolder.cost.setText(radioStation.getCost()+"");
                } else {
                    sRecommendViewHolder.costLin.setVisibility(View.GONE);
                }
            };break;
            case 5:{
                CreateCoverViewHolder createCoverViewHolder = (CreateCoverViewHolder) holder;
                RadioStation radioStation = radioStationView.getRadioStationList().get(position);
                createCoverViewHolder.img.setImageBitmap(MergeImage.roundedCustomDB(HttpUtil.getRes(radioStation.getImg(),context),60,60,5));
                createCoverViewHolder.hotNumber.setText(radioStation.getHotNumber()+"");
                SysUser sysUser = radioStation.getSysUser();
                createCoverViewHolder.perPic.setImageBitmap(MergeImage.circleShow(HttpUtil.getRes(sysUser.getPerPic(),context)));
                createCoverViewHolder.title.setText(radioStation.getTitle());
                createCoverViewHolder.username.setText(sysUser.getUserName());
            };break;
            case 6:{
                StationClassViewHolder stationClassViewHolder = (StationClassViewHolder) holder;
                StationsClass stationsClass = radioStationView.getStationsClassList().get(position);
                stationClassViewHolder.icon.setImageBitmap(MergeImage.roundedCustomDB(HttpUtil.getRes(stationsClass.getIcon(),context),30,30,1));
                stationClassViewHolder.name.setText(stationsClass.getClassName());
            };break;
        }
    }

    @Override
    public int getItemCount() {
        int count = 0;
        switch (radioStationView.getShowType()){
            case 1:{
                count = 1;
            };break;
            case 2:{
                count = radioStationView.getRadioStationSFList().size();
            };break;
            case 3:{
                count = radioStationView.getRadioStationList().size();
            };break;
            case 4:{
                count = radioStationView.getRadioStationList().size();
            };break;
            case 5:{
                count = radioStationView.getRadioStationList().size();
            };break;
            case 6:{
                count = radioStationView.getStationsClassList().size();
            };break;
        }
        return count;
    }

    @Override
    public int getItemViewType(int position) {
        return radioStationView.getShowType();
    }

}
