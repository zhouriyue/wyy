package com.gxuwz.beethoven.adapter.find;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;
import com.gxuwz.beethoven.R;
import com.gxuwz.beethoven.model.entity.current.Banners;
import com.gxuwz.beethoven.model.entity.current.Find;
import com.gxuwz.beethoven.model.entity.current.Song;
import com.gxuwz.beethoven.model.entity.current.Songlist;
import com.gxuwz.beethoven.model.entity.find.SpecialFun;

import java.util.List;

public class FindAdapter extends RecyclerView.Adapter<FindAdapter.FindViewHolder> {

    Context context;
    List<Find> findList;
    protected boolean isScrolling = false;

    public FindAdapter(Context context, List<Find> findList) {
        this.context = context;
        this.findList = findList;
    }

    @NonNull
    @Override
    public FindViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new FindViewHolder(LayoutInflater.from(context).inflate(R.layout.item,null));
    }

    @Override
    public void onBindViewHolder(@NonNull FindViewHolder holder, int position) {
        initData(holder,position);
    }

    public void initData(FindViewHolder holder,int position){
        Find find = findList.get(position);
        holder.findItemOl.setVisibility(View.GONE);
        holder.titleBoxOnclick.setVisibility(View.GONE);
        switch (find.getType()) {
            //每日推荐
            case 1:{
                holder.titleBox.setVisibility(View.GONE);
                List<Banners> bannersList = find.getBannersList();
                /**
                 * recycler 设置流畅度
                 */
                holder.findItemRv.setHasFixedSize(true);
                holder.findItemRv.setNestedScrollingEnabled(false);
                holder.findItemRv.setItemViewCacheSize(20);
                RecyclerView.RecycledViewPool recycledViewPool = new RecyclerView.RecycledViewPool();
                holder.findItemRv.setRecycledViewPool(recycledViewPool);
                holder.findItemRv.setLayoutManager(new StaggeredGridLayoutManager(1,StaggeredGridLayoutManager.VERTICAL));
                BannerAdapter bannerAdapter = new BannerAdapter(context, bannersList);
                holder.findItemRv.setAdapter(bannerAdapter);
            };break;
            //个性功能
            case 2:{
                holder.titleBox.setVisibility(View.GONE);
                List<SpecialFun> specialFunList = find.getSpecialFunList();
                /**
                 * recycler 设置流畅度
                 */
                holder.findItemRv.setHasFixedSize(true);
                holder.findItemRv.setNestedScrollingEnabled(false);
                holder.findItemRv.setItemViewCacheSize(20);
                RecyclerView.RecycledViewPool recycledViewPool = new RecyclerView.RecycledViewPool();
                holder.findItemRv.setRecycledViewPool(recycledViewPool);
                LinearLayoutManager ms= new LinearLayoutManager(context);
                ms.setOrientation(LinearLayoutManager.HORIZONTAL);
                holder.findItemRv.setLayoutManager(ms);
                holder.findItemRv.setNestedScrollingEnabled(false);
                SpeFunAdapter speFunAdapter = new SpeFunAdapter(context,specialFunList);
                holder.findItemRv.setAdapter(speFunAdapter);
            };break;
            //歌单
            case 3:{
                holder.titleBoxOnclick.setVisibility(View.GONE);
                holder.title.setText(find.getTitle());
                holder.toMany.setText(find.getToMangy());
                List<Songlist> songlists = find.getSonglists();
                /**
                 * recycler 设置流畅度
                 */
                holder.findItemRv.setHasFixedSize(true);
                holder.findItemRv.setNestedScrollingEnabled(false);
                holder.findItemRv.setItemViewCacheSize(5);
                RecyclerView.RecycledViewPool recycledViewPool = new RecyclerView.RecycledViewPool();
                holder.findItemRv.setRecycledViewPool(recycledViewPool);
                LinearLayoutManager ms= new LinearLayoutManager(context);
                //禁用滑动
                ms.setSmoothScrollbarEnabled(false);
                ms.setOrientation(LinearLayoutManager.HORIZONTAL);
                holder.findItemRv.setLayoutManager(ms);
                holder.findItemRv.setNestedScrollingEnabled(false);
                SongListAdapter songListAdapter = new SongListAdapter(context,songlists);
                holder.findItemRv.setAdapter(songListAdapter);
            };break;
            //推荐歌曲
            case 4:{
                holder.titleBox.setVisibility(View.VISIBLE);
                holder.title.setText(find.getTitle());
                holder.toMany.setText(find.getToMangy());
                List<Song> songList = find.getSongList();
                /**
                 * recycler 设置流畅度
                 */
                holder.findItemRv.setHasFixedSize(true);
                holder.findItemRv.setNestedScrollingEnabled(false);
                holder.findItemRv.setItemViewCacheSize(5);
                RecyclerView.RecycledViewPool recycledViewPool = new RecyclerView.RecycledViewPool();
                holder.findItemRv.setRecycledViewPool(recycledViewPool);
                LinearLayoutManager ms= new LinearLayoutManager(context);
                //禁用滑动
                ms.setSmoothScrollbarEnabled(false);
                ms.setOrientation(LinearLayoutManager.HORIZONTAL);
                holder.findItemRv.setLayoutManager(ms);
                holder.findItemRv.setNestedScrollingEnabled(false);
                SongAdapter songAdapter = new SongAdapter(context,songList);
                holder.findItemRv.setAdapter(songAdapter);
            };break;

        }
    }

    @Override
    public int getItemCount() {
        return findList.size();
    }

    class FindViewHolder extends RecyclerView.ViewHolder{

        RelativeLayout titleBox,titleBoxOnclick;
        RecyclerView findItemRv,findItemOl;
        TextView title,toMany,titleOnclick1,titleOnclick2;
        ImageView icon;

        public FindViewHolder(@NonNull View itemView) {
            super(itemView);
            findItemRv = itemView.findViewById(R.id.find_item_rv);
            findItemOl = itemView.findViewById(R.id.find_item_ol);
            titleBox = itemView.findViewById(R.id.title_box);
            titleBoxOnclick = itemView.findViewById(R.id.title_box_onclick);
            title = itemView.findViewById(R.id.title);
            toMany = itemView.findViewById(R.id.to_many);
            icon = itemView.findViewById(R.id.icon);
            titleOnclick1 = itemView.findViewById(R.id.title_onclick1);
            titleOnclick2 = itemView.findViewById(R.id.title_onclick2);
        }
    }

    private int getMaxElem(int[] arr) {
        int size = arr.length;
        int maxVal = Integer.MIN_VALUE;
        for (int i = 0; i < size; i++) {
            if (arr[i]>maxVal)
                maxVal = arr[i];
        }
        return maxVal;
    }

    public boolean isScrolling() {
        return isScrolling;
    }

    public void setScrolling(boolean scrolling) {
        isScrolling = scrolling;
    }

    /*@Override
    public void onBindViewHolder(@NonNull FindViewHolder holder, int position) {
        Find find = findList.get(position);
        holder.findItemOl.setVisibility(View.GONE);
        holder.titleBoxOnclick.setVisibility(View.GONE);
        switch (find.getType()) {
            case 1:{
                holder.titleBox.setVisibility(View.GONE);
                List<Banners> bannersList = find.getBannersList();
                *//**
                 * recycler 设置流畅度
                 *//*
                holder.findItemRv.setHasFixedSize(true);
                holder.findItemRv.setNestedScrollingEnabled(false);
                holder.findItemRv.setItemViewCacheSize(20);
                RecyclerView.RecycledViewPool recycledViewPool = new RecyclerView.RecycledViewPool();
                holder.findItemRv.setRecycledViewPool(recycledViewPool);
                holder.findItemRv.setLayoutManager(new StaggeredGridLayoutManager(1,StaggeredGridLayoutManager.VERTICAL));
                BannerAdapter bannerAdapter = new BannerAdapter(context, bannersList);
                holder.findItemRv.setAdapter(bannerAdapter);
            };break;
            case 2:{
                holder.titleBox.setVisibility(View.GONE);
                List<SpecialFun> specialFunList = find.getSpecialFunList();
                *//**
                 * recycler 设置流畅度
                 *//*
                holder.findItemRv.setHasFixedSize(true);
                holder.findItemRv.setNestedScrollingEnabled(false);
                holder.findItemRv.setItemViewCacheSize(20);
                RecyclerView.RecycledViewPool recycledViewPool = new RecyclerView.RecycledViewPool();
                holder.findItemRv.setRecycledViewPool(recycledViewPool);
                LinearLayoutManager ms= new LinearLayoutManager(context);
                ms.setOrientation(LinearLayoutManager.HORIZONTAL);
                holder.findItemRv.setLayoutManager(ms);
                holder.findItemRv.setNestedScrollingEnabled(false);
                SpeFunAdapter speFunAdapter = new SpeFunAdapter(context,specialFunList);
                holder.findItemRv.setAdapter(speFunAdapter);
            };break;
            case 3:{
                holder.titleBoxOnclick.setVisibility(View.GONE);
                holder.title.setText(find.getTitle());
                holder.toMany.setText(find.getToMangy());
                List<SongList> songLists = find.getSongLists();
                *//**
                 * recycler 设置流畅度
                 *//*
                holder.findItemRv.setHasFixedSize(true);
                holder.findItemRv.setNestedScrollingEnabled(false);
                holder.findItemRv.setItemViewCacheSize(5);
                RecyclerView.RecycledViewPool recycledViewPool = new RecyclerView.RecycledViewPool();
                holder.findItemRv.setRecycledViewPool(recycledViewPool);
                LinearLayoutManager ms= new LinearLayoutManager(context);
                //禁用滑动
                ms.setSmoothScrollbarEnabled(false);
                ms.setOrientation(LinearLayoutManager.HORIZONTAL);
                holder.findItemRv.setLayoutManager(ms);
                holder.findItemRv.setNestedScrollingEnabled(false);
                SongListAdapter songListAdapter = new SongListAdapter(context,songLists);
                holder.findItemRv.setAdapter(songListAdapter);
            };break;
            case 4:{
                holder.title.setText(find.getTitle());
                holder.toMany.setText(find.getToMangy());
                List<Telecast> telecastList = find.getTelecastList();
                *//**
                 * recycler 设置流畅度
                 *//*
                holder.findItemRv.setHasFixedSize(true);
                holder.findItemRv.setNestedScrollingEnabled(false);
                holder.findItemRv.setItemViewCacheSize(20);
                RecyclerView.RecycledViewPool recycledViewPool = new RecyclerView.RecycledViewPool();
                holder.findItemRv.setRecycledViewPool(recycledViewPool);
                LinearLayoutManager ms= new LinearLayoutManager(context);
                ms.setOrientation(LinearLayoutManager.HORIZONTAL);
                //禁用滑动
                ms.setSmoothScrollbarEnabled(false);
                holder.findItemRv.setLayoutManager(ms);
                holder.findItemRv.setNestedScrollingEnabled(false);
                TelecastAdapter telecastAdapter = new TelecastAdapter(context,telecastList);
                holder.findItemRv.setAdapter(telecastAdapter);
            };break;
            case 5:{
                holder.titleBox.setVisibility(View.VISIBLE);
                holder.title.setText(find.getTitle());
                holder.toMany.setText(find.getToMangy());
                List<Song> songList = find.getSongList();
                *//**
                 * recycler 设置流畅度
                 *//*
                holder.findItemRv.setHasFixedSize(true);
                holder.findItemRv.setNestedScrollingEnabled(false);
                holder.findItemRv.setItemViewCacheSize(5);
                RecyclerView.RecycledViewPool recycledViewPool = new RecyclerView.RecycledViewPool();
                holder.findItemRv.setRecycledViewPool(recycledViewPool);
                LinearLayoutManager ms= new LinearLayoutManager(context);
                //禁用滑动
                ms.setSmoothScrollbarEnabled(false);
                ms.setOrientation(LinearLayoutManager.HORIZONTAL);
                holder.findItemRv.setLayoutManager(ms);
                holder.findItemRv.setNestedScrollingEnabled(false);
                SongAdapter songAdapter = new SongAdapter(context,songList);
                holder.findItemRv.setAdapter(songAdapter);
            };break;
            case 6:{
                holder.titleBox.setVisibility(View.GONE);
                holder.title.setText(find.getTitle());
                holder.toMany.setText(find.getToMangy());
                List<MusicCal> musicCalList = find.getMusicCals();
                *//**
                 * recycler 设置流畅度
                 *//*
                holder.findItemRv.setHasFixedSize(true);
                holder.findItemRv.setNestedScrollingEnabled(false);
                holder.findItemRv.setItemViewCacheSize(5);
                RecyclerView.RecycledViewPool recycledViewPool = new RecyclerView.RecycledViewPool();
                holder.findItemRv.setRecycledViewPool(recycledViewPool);
                holder.findItemRv.setLayoutManager(new StaggeredGridLayoutManager(1,StaggeredGridLayoutManager.VERTICAL));
                MusicCalAdapter musicCalAdapter = new MusicCalAdapter(context,musicCalList);
                holder.findItemRv.setAdapter(musicCalAdapter);
            };break;
            case 7:{
                holder.title.setText(find.getTitle());
                holder.toMany.setText(find.getToMangy());
                List<Mlog> mlogList = find.getMlogList();
                *//**
                 * recycler 设置流畅度
                 *//*
                holder.findItemRv.setHasFixedSize(true);
                holder.findItemRv.setNestedScrollingEnabled(false);
                holder.findItemRv.setItemViewCacheSize(5);
                RecyclerView.RecycledViewPool recycledViewPool = new RecyclerView.RecycledViewPool();
                holder.findItemRv.setRecycledViewPool(recycledViewPool);
                LinearLayoutManager ms= new LinearLayoutManager(context);
                ms.setOrientation(LinearLayoutManager.HORIZONTAL);
                holder.findItemRv.setLayoutManager(ms);
                MLogAdapter mLogAdapter = new MLogAdapter(context,mlogList);
                holder.findItemRv.setAdapter(mLogAdapter);
            };break;
            case 8:{
                holder.titleBoxOnclick.setVisibility(View.VISIBLE);
                holder.titleBox.setVisibility(View.GONE);
                holder.title.setText(find.getTitle());
                holder.toMany.setText(find.getToMangy());
                LinearLayoutManager lm = new LinearLayoutManager(context);
                //禁用滑动
                lm.setSmoothScrollbarEnabled(false);
                lm.setOrientation(RecyclerView.HORIZONTAL);
                LinearLayoutManager lmOl = new LinearLayoutManager(context);
                //禁用滑动
                lmOl.setSmoothScrollbarEnabled(false);
                lmOl.setOrientation(RecyclerView.HORIZONTAL);
                holder.findItemRv.setNestedScrollingEnabled(false);
                holder.findItemRv.setLayoutManager(lm);
                holder.findItemOl.setNestedScrollingEnabled(false);
                holder.findItemOl.setLayoutManager(lmOl);
                SongAdapter songAdapter = new SongAdapter(context,find.getSongList());
                DicAdapter dicAdapter = new DicAdapter(context,find.getDicList());
                holder.findItemRv.setAdapter(songAdapter);
                holder.findItemOl.setAdapter(dicAdapter);
                holder.titleOnclick1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        holder.findItemRv.setVisibility(View.VISIBLE);
                        holder.findItemOl.setVisibility(View.GONE);
                        holder.titleOnclick1.setTextColor(context.getResources().getColor(R.color.black_2c));
                        holder.titleOnclick2.setTextColor(context.getResources().getColor(R.color.black_A2));
                    }
                });
                holder.titleOnclick2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        holder.findItemRv.setVisibility(View.GONE);
                        holder.findItemOl.setVisibility(View.VISIBLE);
                        holder.titleOnclick1.setTextColor(context.getResources().getColor(R.color.black_A2));
                        holder.titleOnclick2.setTextColor(context.getResources().getColor(R.color.black_2c));
                    }
                });

            };break;
            case 9:{
                holder.titleBox.setVisibility(View.VISIBLE);
                holder.title.setText(find.getTitle());
                holder.toMany.setText(find.getToMangy());
                List<Room> roomList = find.getRoomList();
                *//**
                 * recycler 设置流畅度
                 *//*
                holder.findItemRv.setHasFixedSize(true);
                holder.findItemRv.setNestedScrollingEnabled(false);
                holder.findItemRv.setItemViewCacheSize(5);
                RecyclerView.RecycledViewPool recycledViewPool = new RecyclerView.RecycledViewPool();
                holder.findItemRv.setRecycledViewPool(recycledViewPool);
                LinearLayoutManager ms= new LinearLayoutManager(context);
                //禁用滑动
                ms.setSmoothScrollbarEnabled(false);
                ms.setOrientation(LinearLayoutManager.HORIZONTAL);
                holder.findItemRv.setLayoutManager(ms);
                RoomAdapter roomAdapter = new RoomAdapter(context,roomList);
                holder.findItemRv.setAdapter(roomAdapter);
            };break;
            case 10:{
                holder.titleBox.setVisibility(View.VISIBLE);
                holder.title.setText(find.getTitle());
                holder.toMany.setText(find.getToMangy());
                List<YunVillage> yunVillageList = find.getYunVillageList();
                *//**
                 * recycler 设置流畅度
                 *//*
                holder.findItemRv.setHasFixedSize(true);
                holder.findItemRv.setNestedScrollingEnabled(false);
                holder.findItemRv.setItemViewCacheSize(5);
                RecyclerView.RecycledViewPool recycledViewPool = new RecyclerView.RecycledViewPool();
                holder.findItemRv.setRecycledViewPool(recycledViewPool);
                LinearLayoutManager ms= new LinearLayoutManager(context);
                ms.setOrientation(LinearLayoutManager.HORIZONTAL);
                holder.findItemRv.setLayoutManager(ms);
                YunVillageAdapter yunVillageAdapter = new YunVillageAdapter(context,yunVillageList);
                holder.findItemRv.setAdapter(yunVillageAdapter);
            };break;
            case 11:{
                holder.titleBox.setVisibility(View.VISIBLE);
                holder.title.setText(find.getTitle());
                holder.toMany.setText(find.getToMangy());
                List<RankingList> rankingListList = find.getRankingListList();
                *//**
                 * recycler 设置流畅度
                 *//*
                holder.findItemRv.setHasFixedSize(true);
                holder.findItemRv.setNestedScrollingEnabled(false);
                holder.findItemRv.setItemViewCacheSize(5);
                RecyclerView.RecycledViewPool recycledViewPool = new RecyclerView.RecycledViewPool();
                holder.findItemRv.setRecycledViewPool(recycledViewPool);
                LinearLayoutManager ms= new LinearLayoutManager(context);
                ms.setOrientation(LinearLayoutManager.HORIZONTAL);
                holder.findItemRv.setLayoutManager(ms);
                RankingListAdapter rankingListAdapter = new RankingListAdapter(context,rankingListList);
                holder.findItemRv.setAdapter(rankingListAdapter);
            };break;
            case 12:{
                holder.titleBox.setVisibility(View.VISIBLE);
                holder.title.setText(find.getTitle());
                holder.toMany.setText(find.getToMangy());
                List<SpeSong> speSongList = find.getSpeSongList();
                *//**
                 * recycler 设置流畅度
                 *//*
                holder.findItemRv.setHasFixedSize(true);
                holder.findItemRv.setNestedScrollingEnabled(false);
                holder.findItemRv.setItemViewCacheSize(5);
                RecyclerView.RecycledViewPool recycledViewPool = new RecyclerView.RecycledViewPool();
                holder.findItemRv.setRecycledViewPool(recycledViewPool);
                LinearLayoutManager ms= new LinearLayoutManager(context);
                ms.setOrientation(LinearLayoutManager.HORIZONTAL);
                holder.findItemRv.setLayoutManager(ms);
                holder.findItemRv.setNestedScrollingEnabled(false);
                SpeSongAdapter speSongAdapter = new SpeSongAdapter(context,speSongList);
                holder.findItemRv.setAdapter(speSongAdapter);
            };break;
        }
    }*/
}
