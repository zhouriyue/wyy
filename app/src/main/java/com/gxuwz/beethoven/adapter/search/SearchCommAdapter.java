package com.gxuwz.beethoven.adapter.search;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.gxuwz.beethoven.R;
import com.gxuwz.beethoven.model.entity.search.Search;
import com.gxuwz.beethoven.viewholder.search.SHistoryViewHolder;

import java.util.List;

public class SearchCommAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    Context context;
    Search search;

    public SearchCommAdapter(Context context, Search search) {
        this.context = context;
        this.search = search;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder viewHolder = null;
        switch (viewType){
            case 2:{
                viewHolder = new SHistoryViewHolder(LayoutInflater.from(context).inflate(R.layout.history,null));
            };break;
        }
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        switch (search.getShowType()){
            case 2:{
                SHistoryViewHolder sHistoryViewHolder = (SHistoryViewHolder) holder;
                sHistoryViewHolder.word.setText(search.getSearchHistoryList().get(position));
            };break;
        }
    }

    @Override
    public int getItemCount() {
        int count = 0;
        switch (search.getShowType()){
            case 2:{
                count = search.getSearchHistoryList().size();
            };break;
        }
        return count;
    }

    @Override
    public int getItemViewType(int position) {
        return search.getShowType();
    }
}
