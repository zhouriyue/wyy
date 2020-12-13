package com.gxuwz.beethoven.adapter.find.collectiones;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.gxuwz.beethoven.R;
import com.gxuwz.beethoven.dao.OperateDao;
import com.gxuwz.beethoven.dao.SongDao;
import com.gxuwz.beethoven.model.entity.current.Collectiones;
import com.gxuwz.beethoven.model.entity.current.Operate;
import com.gxuwz.beethoven.model.entity.current.Song;
import com.gxuwz.beethoven.model.entity.current.Songlist;
import com.gxuwz.beethoven.page.fragment.my.songlist.LoadDownView;
import com.gxuwz.beethoven.page.fragment.my.songlist.SongListActivity;
import com.gxuwz.beethoven.util.HttpUtils;
import com.gxuwz.beethoven.util.MergeImage;
import com.gxuwz.beethoven.util.staticdata.StaticHttp;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

public class CollectionesAdapter extends RecyclerView.Adapter<CollectionesAdapter.CollectionesViewHolder> {

    Context context;
    List<Collectiones> collectionesList;
    SharedPreferences sharedPreferences;
    SongDao songDao;
    LoadDownView loadDownView;
    OperateDao operateDao;

    public CollectionesAdapter(Context context, List<Collectiones> collectionesList) {
        this.context = context;
        this.collectionesList = collectionesList;
        this.songDao = new SongDao(context);
        this.sharedPreferences = context.getSharedPreferences("data",Context.MODE_PRIVATE);
        this.loadDownView = new LoadDownView(context);
        this.operateDao = new OperateDao(context);
    }

    @NonNull
    @Override
    public CollectionesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new CollectionesViewHolder(LayoutInflater.from(context).inflate(R.layout.item_my_songlist,null));
    }

    @Override
    public void onBindViewHolder(@NonNull CollectionesViewHolder holder, int position) {
        Songlist songlist = collectionesList.get(position).getSonglist();
        holder.songListNumber.setText(songlist.getSongNumber()+"");
        holder.songListName.setText(songlist.getSlName());
        if(songlist.getCoverPicture()==null) {
            if(songlist.getSongNumber()>0) {
                Song song = songDao.findIndexSong(songlist.getSlId());
                if(song==null) {
                    Handler handler = new Handler(){
                        @Override
                        public void handleMessage(@NonNull Message msg) {
                            if(msg.what==1) {
                                Bundle bundle = msg.getData();
                                String result = bundle.getString("result");
                                try {
                                    JSONObject jsonObject = new JSONObject(result);
                                    String coverPicture = jsonObject.getString("coverPicture");
                                    if(coverPicture!=null) {
                                        MergeImage.showGlideImgDb(context, StaticHttp.STATIC_BASEURL+coverPicture,holder.songListUrl,10);
                                    } else {
                                        MergeImage.showGlideImgDb(context,StaticHttp.DEFALUT_SONGLIST_COVERPICTURE,holder.songListUrl,10);
                                    }
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                    };
                    String url = StaticHttp.BASEURL+StaticHttp.SELECT_INDEX_SONG;
                    url += "?slId="+songlist.getSlId();
                    System.out.println(songlist.getSlId());
                    HttpUtils.get(url,handler);
                } else {
                    String coverPicture = StaticHttp.STATIC_BASEURL+song.getCoverPicture();
                    MergeImage.showGlideImgDb(context,coverPicture,holder.songListUrl,10);
                }
            } else {
                MergeImage.showGlideImgDb(context,StaticHttp.DEFALUT_SONGLIST_COVERPICTURE,holder.songListUrl,10);
            }
        } else {
            String coverPicture = StaticHttp.STATIC_BASEURL+songlist.getCoverPicture();
            MergeImage.showGlideImgDb(context,coverPicture,holder.songListUrl,10);
        }
        holder.pId.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(context, SongListActivity.class);
                Bundle b = new Bundle();
                b.putSerializable("songlist", songlist);
                intent.putExtras(b);
                context.startActivity(intent);
            }
        });
        holder.pId.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                List<Operate> operates = operateDao.findByType(4);
                loadDownView.initView(operates,v,songlist);
                return true;
            }
        });
    }

    @Override
    public int getItemCount() {
        return collectionesList.size();
    }

    class CollectionesViewHolder extends RecyclerView.ViewHolder{

        LinearLayout pId;
        ImageView songListUrl;
        TextView songListName;
        TextView songListNumber;

        public CollectionesViewHolder(@NonNull View itemView) {
            super(itemView);
            pId = itemView.findViewById(R.id.p_id);
            songListUrl = itemView.findViewById(R.id.song_list_url);
            songListName = itemView.findViewById(R.id.song_list_name);
            songListNumber = itemView.findViewById(R.id.song_list_number);
        }
    }
}
