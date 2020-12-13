package com.gxuwz.beethoven.adapter.popwind;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.gxuwz.beethoven.R;
import com.gxuwz.beethoven.http.CollectionesHttp;
import com.gxuwz.beethoven.model.entity.current.Operate;
import com.gxuwz.beethoven.model.entity.current.Songlist;
import com.gxuwz.beethoven.page.fragment.my.AddSongListPW;
import com.gxuwz.beethoven.page.fragment.my.FragmentMy;
import com.gxuwz.beethoven.util.HttpUtils;
import com.gxuwz.beethoven.util.MergeImage;
import com.gxuwz.beethoven.util.staticdata.StaticHttp;

import java.util.List;

public class SonglistOperAdapter extends RecyclerView.Adapter<SonglistOperAdapter.SonglistOperViewHolder> {

    Context context;
    SharedPreferences sharedPreferences;
    List<Operate> operates;
    Songlist songlist;

    public SonglistOperAdapter(Context context, List<Operate> operates,Songlist songlist) {
        this.context = context;
        this.sharedPreferences = context.getSharedPreferences("data",Context.MODE_PRIVATE);
        this.operates = operates;
        this.songlist = songlist;
    }

    @NonNull
    @Override
    public SonglistOperViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new SonglistOperViewHolder(LayoutInflater.from(context).inflate(R.layout.item_sl_more,null));
    }

    @Override
    public void onBindViewHolder(@NonNull SonglistOperViewHolder holder, int position) {
        Operate operate = operates.get(position);
        MergeImage.showGlideImgDb(context, StaticHttp.LOCAL_HOST+operate.getIcon(),holder.icon,1);
        holder.name.setText(operate.getoName());
        songOperate(holder.pId,operate);
    }

    /**
     * 处理操作
     * @param pid
     * @param operate
     */
    private void songOperate(LinearLayout pid,Operate operate){
        pid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //创建到新歌单
                switch (operate.getoId()) {
                    //删除
                    case Operate.SONG_DELETE:{
                        Handler handler = new Handler(){
                            @SuppressLint("WrongConstant")
                            @Override
                            public void handleMessage(@NonNull Message msg) {
                                if(msg.what==1) {
                                    Toast.makeText(context,"删除成功！",Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(FragmentMy.UpdateSonglistReceiver.ACTION);
                                    intent.putExtra(FragmentMy.UpdateSonglistReceiver.CONTROLLER,"songlist");
                                    context.sendBroadcast(intent);
                                }
                            }
                        };
                        String url = StaticHttp.BASEURL+StaticHttp.DELECT_SONGLIST;
                        url += "?slId=" + songlist.getSlId();
                        HttpUtils.get(url,handler);
                    };break;
                    //下载
                    case Operate.SONG_DOWNLOAD:{};break;
                    //取消收藏
                    case Operate.COELLECTIONES_CANCEL:{
                        Long userId = sharedPreferences.getLong(StaticHttp.USER_ID,-1);
                        CollectionesHttp.cancel(context,userId,songlist.getSlId());
                    };break;
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return operates.size();
    }

    class SonglistOperViewHolder extends RecyclerView.ViewHolder {
        LinearLayout pId;
        ImageView icon,tag;
        TextView name;
        public SonglistOperViewHolder(@NonNull View itemView) {
            super(itemView);
            pId = itemView.findViewById(R.id.p_id);
            icon = itemView.findViewById(R.id.icon);
            name = itemView.findViewById(R.id.name);
            tag = itemView.findViewById(R.id.tag);
        }
    }
}
