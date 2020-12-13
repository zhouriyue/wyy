package com.gxuwz.beethoven.adapter.my.radiostation;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.gxuwz.beethoven.R;
import com.gxuwz.beethoven.model.entity.my.radiostation.ListeningItem;
import com.gxuwz.beethoven.util.HttpUtils;
import com.gxuwz.beethoven.util.MergeImage;
import com.gxuwz.beethoven.util.WindowPixels;

import java.util.List;


public class ListeningAdapter extends RecyclerView.Adapter<ListeningAdapter.ListeningViewHolder> {

    Context context;
    List<ListeningItem> listeningItemList;
    int itemWidth;
    int height;

    public ListeningAdapter(Context context, List<ListeningItem> listeningItemList) {
        this.context = context;
        this.listeningItemList = listeningItemList;
        this.itemWidth = (int) ((WindowPixels.WIDTH-60)/3);
        this.height = itemWidth;
    }

    @NonNull
    @Override
    public ListeningViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ListeningViewHolder(LayoutInflater.from(context).inflate(R.layout.radiostation_listening_item,null));
    }

    @Override
    public void onBindViewHolder(@NonNull ListeningViewHolder holder, int position) {
        ListeningItem listeningItem = listeningItemList.get(position);
        holder.diagonal.setImageBitmap(MergeImage.roundedCustomDB(HttpUtils.getRes(listeningItem.getDiagonal(),context),itemWidth,height,5));
        holder.type.setText(listeningItem.getType());
        holder.content.setText(listeningItem.getContent());
    }

    @Override
    public int getItemCount() {
        return listeningItemList.size();
    }

    class ListeningViewHolder extends RecyclerView.ViewHolder{

        ImageView diagonal;
        TextView type,content;

        public ListeningViewHolder(@NonNull View itemView) {
            super(itemView);
            diagonal = itemView.findViewById(R.id.diagonal);
            type = itemView.findViewById(R.id.type);
            content = itemView.findViewById(R.id.content);
        }
    }
}
