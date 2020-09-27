package com.gxuwz.beethoven.adapter.find.spefun.radiostation;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;
import com.gxuwz.beethoven.R;
import com.gxuwz.beethoven.model.entity.find.spefun.radiostation.RadioStation;
import com.gxuwz.beethoven.model.entity.find.spefun.radiostation.RadioStationView;
import com.gxuwz.beethoven.model.entity.find.spefun.radiostation.StationsClass;
import com.gxuwz.beethoven.model.entity.find.spefun.telecast.STelecastShow;
import com.gxuwz.beethoven.util.HttpUtil;
import com.gxuwz.beethoven.util.MergeImage;

import java.util.List;

public class SRSItemAdapter extends RecyclerView.Adapter<SRSItemAdapter.SRSItemViewHolder> {

    Context context;
    List<RadioStationView> sTelecastShowList;

    public SRSItemAdapter(Context context, List<RadioStationView> sTelecastShowList) {
        this.context = context;
        this.sTelecastShowList = sTelecastShowList;
    }

    @NonNull
    @Override
    public SRSItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new SRSItemViewHolder(LayoutInflater.from(context).inflate(R.layout.srs_item,null));
    }

    @Override
    public void onBindViewHolder(@NonNull SRSItemViewHolder holder, int position) {
        RadioStationView radioStationView = sTelecastShowList.get(position);
        switch (radioStationView.getShowType()) {
            case 1:{
                //标题显示
                showTitle(holder,radioStationView,20,20,1);
                //标签显示
                showTag(holder,radioStationView,10,10,1);
                holder.stelecastRv.setLayoutManager(new StaggeredGridLayoutManager(1,StaggeredGridLayoutManager.VERTICAL));
                holder.stelecastRv.setAdapter(new SRSCommAdapter(context,radioStationView));
            };break;
            case 2:{
                //标题显示
                showTitle(holder,radioStationView,20,20,1);
                //标签显示
                showTag(holder,radioStationView,10,10,1);
                LinearLayoutManager lm = new LinearLayoutManager(context);
                holder.tagLin.setVisibility(View.GONE);
                lm.setOrientation(RecyclerView.HORIZONTAL);
                holder.stelecastRv.setLayoutManager(lm);
                holder.stelecastRv.setAdapter(new SRSCommAdapter(context,radioStationView));
            };break;
            case 3:{
                //标题显示
                showTitle(holder,radioStationView,20,20,1);
                //标签显示
                showTag(holder,radioStationView,10,10,1);
                LinearLayoutManager lm = new LinearLayoutManager(context);
                lm.setOrientation(RecyclerView.HORIZONTAL);
                holder.stelecastRv.setLayoutManager(lm);
                holder.stelecastRv.setAdapter(new SRSCommAdapter(context,radioStationView));
            };break;
            case 4:{
                //标题显示
                showTitle(holder,radioStationView,20,20,1);
                //标签显示
                showTag(holder,radioStationView,10,10,1);
                LinearLayoutManager lm = new LinearLayoutManager(context);
                lm.setOrientation(RecyclerView.HORIZONTAL);
                holder.stelecastRv.setLayoutManager(lm);
                holder.stelecastRv.setAdapter(new SRSCommAdapter(context,radioStationView));

            };break;
            case 5:{
                //标题显示
                showTitle(holder,radioStationView,20,20,1);
                //标签显示
                showTag(holder,radioStationView,8,8,1);
                //adapter初始化
                holder.stelecastRv.setLayoutManager(new StaggeredGridLayoutManager(1,StaggeredGridLayoutManager.VERTICAL));
                holder.stelecastRv.setAdapter(new SRSCommAdapter(context,radioStationView));
            };break;
            case 6:{
                //标题显示
                showTitle(holder,radioStationView,20,20,1);
                //标签显示
                showTag(holder,radioStationView,8,8,1);
                //adapter初始化
                holder.stelecastRv.setLayoutManager(new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL));
                holder.stelecastRv.setAdapter(new SRSCommAdapter(context,radioStationView));
            };break;
        }
    }

    /**
     * 显示标题栏
     * show title LinearLayout
     * @param holder
     * @param radioStationView
     */
    public void showTitle(SRSItemViewHolder holder, RadioStationView radioStationView,int titleIconWidth,int titleIconHeight,int radius){
        if(radioStationView.getTitle()!=null) {
            holder.titleLin.setVisibility(View.VISIBLE);
            holder.title.setText(radioStationView.getTitle());
            if(radioStationView.getTitleIcon()!=null) {
                holder.titleIcon.setImageBitmap(MergeImage.roundedCustomDB(HttpUtil.getRes(radioStationView.getTitleIcon(),context),titleIconWidth,titleIconHeight,radius));
            } else {
                holder.titleIcon.setVisibility(View.GONE);
            }
            if(radioStationView.getSubTitle()!=null) {
                holder.subTitle.setVisibility(View.VISIBLE);
                holder.subTitle.setText(radioStationView.getSubTitle());
            } else {
                holder.subTitle.setVisibility(View.GONE);
            }
            holder.title.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
        } else {
            holder.titleLin.setVisibility(View.GONE);
        }
    }

    /**
     * 显示右边标签栏
     * show right tag lineaLayout
     * @param holder
     * @param radioStationView
     */
    public void showTag(SRSItemViewHolder holder, RadioStationView radioStationView,int tagIconWidth,int tagIconHeight,int radius){
        if(radioStationView.getTagTitle()!=null) {
            holder.tagLin.setVisibility(View.VISIBLE);
            holder.tagTitle.setText(radioStationView.getTagTitle());
            if(radioStationView.getTagIcon()!=null) {
                holder.tagImage.setVisibility(View.VISIBLE);
                holder.tagImage.setImageBitmap(MergeImage.roundedCustomDB(HttpUtil.getRes(radioStationView.getTagIcon(),context),tagIconWidth,tagIconHeight,radius));
            } else {
                holder.tagImage.setVisibility(View.GONE);
            }
        } else {
            holder.tagLin.setVisibility(View.GONE);
        }
    }

    @Override
    public int getItemCount() {
        return sTelecastShowList.size();
    }

    class SRSItemViewHolder extends RecyclerView.ViewHolder{

        LinearLayout titleLin,tagLin;
        RecyclerView stelecastRv;
        TextView title,tagTitle,subTitle;
        ImageView tagImage,titleIcon;

        public SRSItemViewHolder(@NonNull View itemView) {
            super(itemView);
            titleLin = itemView.findViewById(R.id.title_lin);
            tagLin = itemView.findViewById(R.id.tag_lin);
            stelecastRv = itemView.findViewById(R.id.srs_item);
            title = itemView.findViewById(R.id.title);
            tagTitle = itemView.findViewById(R.id.tag_title);
            title.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
            tagImage = itemView.findViewById(R.id.tag_img);
            titleIcon = itemView.findViewById(R.id.title_icon);
            subTitle = itemView.findViewById(R.id.subtitle);
        }
    }
}
