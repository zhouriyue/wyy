package com.gxuwz.beethoven.adapter.find;

import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;
import com.gxuwz.beethoven.R;
import com.gxuwz.beethoven.model.entity.current.Banners;
import com.gxuwz.beethoven.util.WindowPixels;

import java.util.List;

public class BannerAdapter extends RecyclerView.Adapter<BannerAdapter.BannerViewHolder> {

    Context context;
    List<Banners> bannersList;
    protected boolean isScrolling = false;

    public BannerAdapter(Context context, List<Banners> bannersList) {
        this.context = context;
        this.bannersList = bannersList;
    }

    @NonNull
    @Override
    public BannerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new BannerViewHolder(LayoutInflater.from(context).inflate(R.layout.item_banner,null));
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onBindViewHolder(@NonNull BannerViewHolder holder, int position) {
        Banners.initBanner((com.youth.banner.Banner) holder.banner, bannersList);
    }

    @Override
    public int getItemCount() {
        return 1;
    }

    class BannerViewHolder extends RecyclerView.ViewHolder{

        View banner;
        TextView type;

        public BannerViewHolder(@NonNull View itemView) {
            super(itemView);
            banner = itemView.findViewById(R.id.banner);
            banner.setMinimumWidth((int) ((int) (WindowPixels.WIDTH-20)*WindowPixels.DENSITY));
            banner.setMinimumHeight((int) (125*WindowPixels.DENSITY));
            type = itemView.findViewById(R.id.type);
        }
    }

    public boolean isScrolling() {
        return isScrolling;
    }

    public void setScrolling(boolean scrolling) {
        isScrolling = scrolling;
    }
}
