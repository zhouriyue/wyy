package com.gxuwz.beethoven.adapter.my;

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
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.gxuwz.beethoven.R;
import com.gxuwz.beethoven.dao.SongDao;
import com.gxuwz.beethoven.model.entity.current.Song;
import com.gxuwz.beethoven.model.entity.current.Songlist;
import com.gxuwz.beethoven.page.fragment.my.songlist.SongListActivity;
import com.gxuwz.beethoven.util.HttpUtil;
import com.gxuwz.beethoven.util.MergeImage;
import com.gxuwz.beethoven.util.staticdata.StaticHttp;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

public class SongListAdapter extends RecyclerView.Adapter<SongListAdapter.SongListViewHolder> {

    Context context;
    List<Songlist> songlists;
    SharedPreferences sharedPreferences;
    SongDao songDao;

    public SongListAdapter(Context context, List<Songlist> songlists) {
        this.context = context;
        this.songlists = songlists;
        songDao = new SongDao(context);
        sharedPreferences = context.getSharedPreferences("data",Context.MODE_PRIVATE);
    }

    @NonNull
    @Override
    public SongListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new SongListViewHolder(LayoutInflater.from(context).inflate(R.layout.item_my_songlist,null));
    }

    @Override
    public void onBindViewHolder(@NonNull SongListViewHolder holder, int position) {
        Songlist songlist = songlists.get(position);
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
                                        MergeImage.showGlideImgDb(context,StaticHttp.STATIC_BASEURL+coverPicture,holder.songListUrl,10);
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
                    HttpUtil.get(url,handler);
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
    }

    public void updateData(List<Songlist> songlists){
        this.songlists = songlists;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return songlists.size();
    }

    class SongListViewHolder extends RecyclerView.ViewHolder{

        LinearLayout pId;
        ImageView songListUrl;
        TextView songListName;
        TextView songListNumber;

        public SongListViewHolder(@NonNull View itemView) {
            super(itemView);
            pId = itemView.findViewById(R.id.p_id);
            songListUrl = itemView.findViewById(R.id.song_list_url);
            songListName = itemView.findViewById(R.id.song_list_name);
            songListNumber = itemView.findViewById(R.id.song_list_number);
        }
    }
}
