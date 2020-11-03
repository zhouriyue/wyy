package com.gxuwz.beethoven.adapter.search;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.gxuwz.beethoven.R;
import com.gxuwz.beethoven.dao.SongDao;
import com.gxuwz.beethoven.dao.SongListDao;
import com.gxuwz.beethoven.model.entity.current.Song;
import com.gxuwz.beethoven.model.entity.current.Songlist;
import com.gxuwz.beethoven.util.HttpUtil;
import com.gxuwz.beethoven.util.MergeImage;
import com.gxuwz.beethoven.util.staticdata.StaticHttp;

import java.net.URLEncoder;
import java.util.List;

public class SaveSLAdapter extends RecyclerView.Adapter<SaveSLAdapter.SaveSLViewholder> {

    Context context;
    List<Songlist> songlists;
    PopupWindow ldPopupWindow;
    PopupWindow saveSlPopupWindow;
    SongListDao songListDao;
    SongDao songDao;
    long songId;

    public SaveSLAdapter(Context context, List<Songlist> songlists,long songId) {
        this.context = context;
        this.songlists = songlists;
        this.songId = songId;
        this.songListDao = new SongListDao(context);
        this.songDao = new SongDao(context);
    }

    @NonNull
    @Override
    public SaveSLViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new SaveSLViewholder(LayoutInflater.from(context).inflate(R.layout.search_savesl,null));
    }

    @Override
    public void onBindViewHolder(@NonNull SaveSLViewholder holder, int position) {
        Songlist songlist = songlists.get(position);
        String url = StaticHttp.STATIC_BASEURL + songlist.getCoverPicture();
        if(songlist.getCoverPicture()!=null) {
            MergeImage.showGlideImgDb(context,url,holder.coverPicture,10);
        } else {
            Song song = songDao.findIndexSong(songlist.getSlId());
            if(song!=null&&song.getCoverPicture()!=null) {
                url = StaticHttp.STATIC_BASEURL + song.getCoverPicture();
                MergeImage.showGlideImgDb(context,url,holder.coverPicture,10);
            } else {
                MergeImage.showGlideImgDb(context,StaticHttp.DEFALUT_SONGLIST_COVERPICTURE,holder.coverPicture,10);
            }
        }
        holder.songlistName.setText(songlist.getSlName());
        holder.songNumber.setText(songlist.getSongNumber()+"");
        holder.pId.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Handler handler = new Handler(){
                    @Override
                    public void handleMessage(@NonNull Message msg) {
                        if(msg.what==1){
                            Bundle bundle = msg.getData();
                            String result = bundle.getString("result");
                            switch (result) {
                                case "-1":{
                                    Toast.makeText(context, "添加失败！", Toast.LENGTH_SHORT).show();
                                };break;
                                case "0":{
                                    Toast.makeText(context, "已存在！", Toast.LENGTH_SHORT).show();
                                };break;
                                case "1":{
                                    Toast.makeText(context, "添加成功！", Toast.LENGTH_SHORT).show();
                                };break;
                            }
                        }
                        if(ldPopupWindow!=null) {
                            ldPopupWindow.dismiss();
                        }
                        if(saveSlPopupWindow!=null) {
                            saveSlPopupWindow.dismiss();
                        }
                    }
                };
                String url = StaticHttp.BASEURL+StaticHttp.ADD_TO_SONGLIST;
                String data = "slId="+ URLEncoder.encode(String.valueOf(songlist.getSlId()));
                data += "&songId="+URLEncoder.encode(String.valueOf(songId));
                HttpUtil.post(url,data,handler);
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

    class SaveSLViewholder extends RecyclerView.ViewHolder{

        LinearLayout pId;
        ImageView coverPicture;
        TextView songlistName,songNumber;

        public SaveSLViewholder(@NonNull View itemView) {
            super(itemView);
            pId = itemView.findViewById(R.id.p_id);
            coverPicture = itemView.findViewById(R.id.cover_picture);
            songlistName = itemView.findViewById(R.id.songlist_name);
            songNumber = itemView.findViewById(R.id.song_number);
        }
    }

    public PopupWindow getLdPopupWindow() {
        return ldPopupWindow;
    }

    public void setLdPopupWindow(PopupWindow ldPopupWindow) {
        this.ldPopupWindow = ldPopupWindow;
    }

    public PopupWindow getSaveSlPopupWindow() {
        return saveSlPopupWindow;
    }

    public void setSaveSlPopupWindow(PopupWindow saveSlPopupWindow) {
        this.saveSlPopupWindow = saveSlPopupWindow;
    }
}
