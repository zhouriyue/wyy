package com.gxuwz.beethoven.adapter.find;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.gxuwz.beethoven.R;
import com.gxuwz.beethoven.model.entity.find.YunVillage;
import com.gxuwz.beethoven.page.index.findview.yunvillage.YunVillageActivity;
import com.gxuwz.beethoven.util.HttpUtils;
import com.gxuwz.beethoven.util.MergeImage;

import java.util.List;

public class YunVillageAdapter extends RecyclerView.Adapter<YunVillageAdapter.YunVillageViewHolder> {

    Context context;
    List<YunVillage> yunVillageList;
    protected boolean isScrolling = false;

    public YunVillageAdapter(Context context, List<YunVillage> yunVillageList) {
        this.context = context;
        this.yunVillageList = yunVillageList;
    }

    @NonNull
    @Override
    public YunVillageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new YunVillageViewHolder(LayoutInflater.from(context).inflate(R.layout.item_yunvillage,null));
    }

    @Override
    public void onBindViewHolder(@NonNull YunVillageViewHolder holder, int position) {
        YunVillage yunVillage = yunVillageList.get(position);
        holder.img.setImageBitmap(MergeImage.roundedCustomDB(HttpUtils.getRes(yunVillage.getImg(),context),150,140,10));
        holder.title.setText(yunVillage.getTitle());
        holder.hotNumber.setText(yunVillage.getHotNumber()+"");
        holder.name.setText(yunVillage.getName());
        holder.yunvLin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, YunVillageActivity.class);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return yunVillageList.size();
    }

    class YunVillageViewHolder extends RecyclerView.ViewHolder{

        LinearLayout yunvLin;
        ImageView img;
        TextView hotNumber,name,title;

        public YunVillageViewHolder(@NonNull View itemView) {
            super(itemView);
            yunvLin = itemView.findViewById(R.id.yunv_lin);
            img = itemView.findViewById(R.id.img);
            hotNumber = itemView.findViewById(R.id.hot_number);
            name = itemView.findViewById(R.id.name);
            title = itemView.findViewById(R.id.title);
        }
    }

    public boolean isScrolling() {
        return isScrolling;
    }

    public void setScrolling(boolean scrolling) {
        isScrolling = scrolling;
    }
}
