package com.gxuwz.beethoven.adapter.search;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.gxuwz.beethoven.R;
import com.gxuwz.beethoven.model.entity.search.Search;
import com.gxuwz.beethoven.util.HttpUtil;
import com.gxuwz.beethoven.util.MergeImage;
import com.gxuwz.beethoven.viewholder.search.SearchViewHolder;

import java.util.List;

public class SearchAdapter extends RecyclerView.Adapter<SearchViewHolder> {

    Context context;
    List<Search> searchList;

    @NonNull
    @Override
    public SearchViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new SearchViewHolder(LayoutInflater.from(context).inflate(R.layout.item_search,null));
    }

    @Override
    public void onBindViewHolder(@NonNull SearchViewHolder holder, int position) {
        Search search = searchList.get(position);
        holder.title.setText(search.getTitle());
        holder.tagIcon.setImageBitmap(MergeImage.roundedCustomDB(HttpUtil.getRes(search.getTagIcon(),context),10,10));
        holder.tag.setText(search.getTag());
        holder.searchItemRv.setLayoutManager(new StaggeredGridLayoutManager(1,StaggeredGridLayoutManager.VERTICAL));
        holder.searchItemRv.setAdapter(new SearchCommAdapter(context,search));
    }

    @Override
    public int getItemCount() {
        return searchList.size();
    }
}
