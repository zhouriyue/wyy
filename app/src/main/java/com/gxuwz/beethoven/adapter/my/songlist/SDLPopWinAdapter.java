package com.gxuwz.beethoven.adapter.my.songlist;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Parcelable;
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
import com.gxuwz.beethoven.dao.MemberDao;
import com.gxuwz.beethoven.dao.SongListDao;
import com.gxuwz.beethoven.dao.ThreadDAO;
import com.gxuwz.beethoven.dao.ThreadDAOImpl;
import com.gxuwz.beethoven.dao.UserDetailDao;
import com.gxuwz.beethoven.model.entity.ThreadInfo;
import com.gxuwz.beethoven.model.entity.current.LocalSong;
import com.gxuwz.beethoven.model.entity.current.Member;
import com.gxuwz.beethoven.model.entity.current.Operate;
import com.gxuwz.beethoven.model.entity.current.Song;
import com.gxuwz.beethoven.model.vo.UserDetailVo;
import com.gxuwz.beethoven.page.fragment.member.RechargeActivity;
import com.gxuwz.beethoven.page.fragment.my.AddSongListPW;
import com.gxuwz.beethoven.page.fragment.my.downmanage.DownLoadActivity;
import com.gxuwz.beethoven.page.fragment.my.ring.RingTestPW;
import com.gxuwz.beethoven.page.fragment.search.SaveToSonglistPw;
import com.gxuwz.beethoven.util.DateUtil;
import com.gxuwz.beethoven.util.MergeImage;
import com.gxuwz.beethoven.util.staticdata.StaticHttp;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class SDLPopWinAdapter extends RecyclerView.Adapter<SDLPopWinAdapter.SdLPopWinViewHolder> {

    Context context;
    SharedPreferences sharedPreferences;
    SongListDao songListDao;
    UserDetailDao userDetailDao;
    MemberDao memberDao;
    ThreadDAO threadDAO;
    List<Operate> operates;
    //添加歌单
    AddSongListPW addSongListPW;
    SaveToSonglistPw saveToSonglistPw;
    RingTestPW ringTestPW;
    PopupWindow ldPopupWindow;
    View view;
    Song song;
    Long slId;
    Long userId;
    LocalSong localSong;

    public SDLPopWinAdapter(Context context, List<Operate> operates,SaveToSonglistPw saveToSonglistPw,View view) {
        this.context = context;
        this.operates = operates;
        this.saveToSonglistPw = saveToSonglistPw;
        this.view = view;
    }

    public SDLPopWinAdapter(Context context, List<Operate> operates, SaveToSonglistPw saveToSonglistPw, View view, long slId, Song song) {
        this.context = context;
        this.operates = operates;
        this.saveToSonglistPw = saveToSonglistPw;
        this.view = view;
        this.song = song;
        this.slId = slId;
        this.songListDao = new SongListDao(context);
        this.threadDAO = new ThreadDAOImpl(context);
        this.userDetailDao = new UserDetailDao(context);
        this.memberDao = new MemberDao(context);
        this.sharedPreferences = context.getSharedPreferences("data",Context.MODE_PRIVATE);
        this.userId = this.sharedPreferences.getLong("userId",-1);
        saveToSonglistPw.setLdPopupWindow(ldPopupWindow);
    }

    public SDLPopWinAdapter(Context context, List<Operate> operates,View view, Long slId) {
        this.context = context;
        this.operates = operates;
        this.view = view;
        this.slId = slId;
        this.songListDao = new SongListDao(context);
        this.sharedPreferences = context.getSharedPreferences("data",Context.MODE_PRIVATE);
        this.userId = this.sharedPreferences.getLong("userId",-1);
    }

    public SDLPopWinAdapter(Context context, List<Operate> operates, SaveToSonglistPw saveToSonglistPw, View view, Song song) {
        this.context = context;
        this.operates = operates;
        this.saveToSonglistPw = saveToSonglistPw;
        this.view = view;
        this.song = song;
        saveToSonglistPw.setLdPopupWindow(ldPopupWindow);
    }

    public SDLPopWinAdapter(Context context, List<Operate> operates, SaveToSonglistPw saveToSonglistPw, View view, LocalSong localSong) {
        this.context = context;
        this.operates = operates;
        this.saveToSonglistPw = saveToSonglistPw;
        this.view = view;
        this.localSong = localSong;
        saveToSonglistPw.setLdPopupWindow(ldPopupWindow);
    }

    @NonNull
    @Override
    public SdLPopWinViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new SdLPopWinViewHolder(LayoutInflater.from(context).inflate(R.layout.item_sl_more,null));
    }

    @Override
    public void onBindViewHolder(@NonNull SdLPopWinViewHolder holder, int position) {
        if(localSong==null) {
            Operate operate = operates.get(position);
            MergeImage.showGlideImgDb(context, StaticHttp.LOCAL_HOST+operate.getIcon(),holder.icon,1);
            holder.name.setText(operate.getoName());
            if("song:download".equals(operate.getoId())&&song.getIsCharge()!=0) {
                MergeImage.showGlideImgDb(context,StaticHttp.ICON_VIP,holder.tag,1);
            }
            songOperate(holder.pId,operate);
        } else {
            Operate operate = operates.get(position);
            MergeImage.showGlideImgDb(context, StaticHttp.LOCAL_HOST+operate.getIcon(),holder.icon,1);
            holder.name.setText(operate.getoName());
            localSong(holder.pId,operate);
        }
    }

    private void localSong(LinearLayout pid,Operate operate){
        pid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //创建到新歌单
                switch (operate.getoId()) {
                    case Operate.SONG_ADD_SL:{
                        if(addSongListPW==null) {
                            addSongListPW = new AddSongListPW(context);
                        }
                        addSongListPW.showAtLocation(view);
                    };break;
                    //收藏到歌单
                    case Operate.SONG_COL:{
                        saveToSonglistPw.showAtLocation(view,song.getSongId());
                    };break;
                    //删除
                    case Operate.SONG_DELETE:{
                        deleteSong();
                    };break;
                    //下载
                    case Operate.SONG_DOWNLOAD:{
                        downloadSong();
                    };break;
                    //设置铃声
                    case Operate.SONG_SET_RING:{
                        //MusicUtils.setRingtoneImpl(context,localSong);
                        if(ringTestPW==null) {
                            ringTestPW = new RingTestPW(context);
                        }
                        ringTestPW.showAtLocation(view,localSong);
                    };break;
                }
            }
        });
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
                    case Operate.SONG_ADD_SL:{
                        if(addSongListPW==null) {
                            addSongListPW = new AddSongListPW(context);
                        }
                        addSongListPW.showAtLocation(view);
                    };break;
                    //收藏到歌单
                    case Operate.SONG_COL:{
                        saveToSonglistPw.showAtLocation(view,song.getSongId());
                    };break;
                    //删除
                    case Operate.SONG_DELETE:{
                        deleteSong();
                    };break;
                    //下载
                    case Operate.SONG_DOWNLOAD:{
                        downloadSong();
                    };break;
                    //设置铃声
                    case Operate.SONG_SET_RING:{

                    };break;
                }
            }
        });
    }


    public void downloadSong(){
        //歌曲权限，是否免费
        if(userDetailDao==null) {
            userDetailDao = new UserDetailDao(context);
        }
        UserDetailVo userDetailVo  = userDetailDao.findByUserId(userId);
        switch (song.getIsCharge()) {
            case 0:{
                download();
            };break;
            case 1:{
                System.out.println(userDetailVo.toString());
                if(userDetailVo!=null&&userDetailVo.getIsMember()==1) {
                    Member member = memberDao.findById(userId);
                    System.out.println(member.toString());
                    long count = DateUtil.computeDay(member.getEnrollDate(),new Date());
                    if(count<=member.getValidDay()){
                        download();
                    } else {
                        Intent intent = new Intent(context, RechargeActivity.class);
                        intent.putExtra("member", (Serializable) member);
                        context.startActivity(intent);
                    }
                } else {
                    Intent intent = new Intent(context, RechargeActivity.class);
                    context.startActivity(intent);
                }
            };break;
            case 2:{

            };break;
            case 3:{

            };break;
        }
    }

    private void deleteSong(){
        SongListDao.deleteRequest(slId,song.getSongId());
        songListDao.deleteSonglistSong(slId,song.getSongId());
        ldPopupWindow.dismiss();
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean("isRefreSonglist",true);
        editor.commit();
        Toast.makeText(context, "删除成功！", Toast.LENGTH_SHORT).show();
    }

    private void download(){
        ThreadInfo threadInfo = new ThreadInfo(0,song.getSongName(),StaticHttp.STATIC_BASEURL+song.getStandardUrl(),0,12913518,0);
        threadInfo.setDownType(1);
        threadDAO.insertThread(threadInfo);
        Intent intent = new Intent(context, DownLoadActivity.class);
        intent.putExtra("fragment_flag",3);
        context.startActivity(intent);
    }

    @Override
    public int getItemCount() {
        return operates.size();
    }

    class SdLPopWinViewHolder extends RecyclerView.ViewHolder{

        LinearLayout pId;
        ImageView icon,tag;
        TextView name;

        public SdLPopWinViewHolder(@NonNull View itemView) {
            super(itemView);
            pId = itemView.findViewById(R.id.p_id);
            icon = itemView.findViewById(R.id.icon);
            name = itemView.findViewById(R.id.name);
            tag = itemView.findViewById(R.id.tag);
        }
    }

    public PopupWindow getLdPopupWindow() {
        return ldPopupWindow;
    }

    public void setLdPopupWindow(PopupWindow ldPopupWindow) {
        this.ldPopupWindow = ldPopupWindow;
    }
}
