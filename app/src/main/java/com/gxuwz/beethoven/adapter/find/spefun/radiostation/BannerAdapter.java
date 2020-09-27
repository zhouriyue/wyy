package com.gxuwz.beethoven.adapter.find.spefun.radiostation;

import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.gxuwz.beethoven.R;
import com.gxuwz.beethoven.model.entity.find.Banners;
import com.gxuwz.beethoven.util.WindowPixels;

public class BannerAdapter extends RecyclerView.Adapter<BannerAdapter.BannerViewHolder> {

    Context context;
    Banners banners;

    public BannerAdapter(Context context, Banners banners) {
        this.context = context;
        this.banners = banners;
    }

    @NonNull
    @Override
    public BannerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new BannerViewHolder(LayoutInflater.from(context).inflate(R.layout.item_banner,null));
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onBindViewHolder(@NonNull BannerAdapter.BannerViewHolder holder, int position) {
        Banners.initBanner((com.youth.banner.Banner) holder.banner, banners.getImages());
    }

    @Override
    public int getItemCount() {
        return 1;
    }

    class BannerViewHolder extends RecyclerView.ViewHolder{

        View banner;

        public BannerViewHolder(@NonNull View itemView) {
            super(itemView);
            banner = itemView.findViewById(R.id.banner);
            banner.setMinimumWidth((int) ((int) (WindowPixels.WIDTH-20)*WindowPixels.DENSITY));
            banner.setMinimumHeight((int) (125*WindowPixels.DENSITY));
        }
    }
}
