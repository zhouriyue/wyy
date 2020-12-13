package com.gxuwz.beethoven.page.fragment.my;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
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
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.gxuwz.beethoven.R;
import com.gxuwz.beethoven.adapter.my.PlayAppAdapter;
import com.gxuwz.beethoven.adapter.my.SongListAdapter;
import com.gxuwz.beethoven.dao.LatePlayDao;
import com.gxuwz.beethoven.dao.LocalSongDao;
import com.gxuwz.beethoven.dao.MemberDao;
import com.gxuwz.beethoven.dao.PlayListDao;
import com.gxuwz.beethoven.dao.SongDao;
import com.gxuwz.beethoven.dao.SongListDao;
import com.gxuwz.beethoven.dao.SysUserDao;
import com.gxuwz.beethoven.dao.UserDetailDao;
import com.gxuwz.beethoven.http.CollectionesHttp;
import com.gxuwz.beethoven.model.entity.LatePlay;
import com.gxuwz.beethoven.model.entity.current.LocalSong;
import com.gxuwz.beethoven.model.entity.current.Member;
import com.gxuwz.beethoven.model.entity.current.PlayApp;
import com.gxuwz.beethoven.model.entity.current.PlayList;
import com.gxuwz.beethoven.model.entity.current.Song;
import com.gxuwz.beethoven.model.entity.current.Songlist;
import com.gxuwz.beethoven.model.entity.current.SysUser;
import com.gxuwz.beethoven.model.vo.UserDetailVo;
import com.gxuwz.beethoven.page.fragment.my.collect.CollectActivity;
import com.gxuwz.beethoven.page.fragment.my.downmanage.DownLoadActivity;
import com.gxuwz.beethoven.page.fragment.my.follow.FollowActivity;
import com.gxuwz.beethoven.page.fragment.my.localmusic.LocalMusicActivity;
import com.gxuwz.beethoven.page.fragment.my.radiostation.RadioStationActivity;
import com.gxuwz.beethoven.page.fragment.my.songlist.LoadDownView;
import com.gxuwz.beethoven.page.fragment.my.usermain.UserMainActivity;
import com.gxuwz.beethoven.util.DateUtil;
import com.gxuwz.beethoven.util.HttpUtils;
import com.gxuwz.beethoven.util.MergeImage;
import com.gxuwz.beethoven.util.WindowPixels;
import com.gxuwz.beethoven.util.staticdata.StaticHttp;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

public class FragmentMy extends Fragment {

    Context context;
    /**
     * 下载、电台、我的收藏、关注模块
     */
    LinearLayout localMusicLin,downloadLin,radioStationLin,collectLin,followLin;
    protected RecyclerView songListRv;

    protected SharedPreferences sharedPreferences;

    protected ImageView perPicView, slAddBt;
    protected TextView userNameView;
    RecyclerView collectRv;
    LinearLayout userMain;
    List<Songlist> songlists;
    SongListAdapter songListAdapter;
    SysUserDao sysUserDao;
    SongListDao songListDao;
    SongDao songDao;
    MemberDao memberDao;
    LocalSongDao localSongDao;
    LatePlayDao latePlayDao;
    PlayListDao playListDao;
    SysUser sysUser;
    Member member;

    UserDetailVo userDetailVo;
    UserDetailDao userDetailDao;

    //添加歌单
    AddSongListPW addSongListPW;

    //操作弹框
    LoadDownView loadDownView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.view_my, container, false);
        sharedPreferences = getActivity().getSharedPreferences("data", Context.MODE_PRIVATE);
        songListDao = new SongListDao(getContext());
        sysUserDao = new SysUserDao(getContext());
        songDao = new SongDao(getContext());
        localSongDao = new LocalSongDao(getContext());
        latePlayDao = new LatePlayDao(getContext());
        playListDao = new PlayListDao(getContext());
        userDetailDao = new UserDetailDao(getContext());
        memberDao = new MemberDao(getContext());
        init(view);
        return view;
    }

    public void init(View view) {
        findByIdAll(view);
        initData();
        initOnClick();
        initPerMess();
        initSonglist();
        initLatePlay(view);
        initForUserMain(view);
        initCollecSonglist(view);
    }

    //显示收藏歌单
    public void initCollecSonglist(View view){
        collectRv = view.findViewById(R.id.collect_rv);
        collectRv.setLayoutManager(new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL));
        CollectionesHttp.list(context,collectRv,sysUser.getUserId());
    }

    public void initForUserMain(View view){
        userMain = view.findViewById(R.id.user_main);
        userMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), UserMainActivity.class);
                getActivity().startActivity(intent);
            }
        });
    }

    public void initLatePlay(View view){
        RecyclerView latePlayRv = view.findViewById(R.id.late_play_rv);
        Long slId = sharedPreferences.getLong("slId",-1);
        Long songId = sharedPreferences.getLong("songId",-1);
        latePlayRv.setLayoutManager(new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL));
        List<PlayApp> playApps = new ArrayList<PlayApp>();
        PlayApp playApp = new PlayApp();
        if(slId!=-1) {
            Song song = songDao.findById(songId);
            if(song!=null)
            playApp.setName("最近播放："+song.getSongName());
        } else {
            LocalSong localSong = localSongDao.findBySongId(songId);
            if(localSong==null) {
                playApp.setName("");
            } else {
                playApp.setName("最近播放："+localSong.getSongName());
            }
        }
        List<LatePlay> latePlays = latePlayDao.findAll();
        playApp.setNumber((long) latePlays.size());
        Song song = null;
        if(latePlays.size()!=0) {
            song = songDao.findById(latePlays.get(0).getSongId());
        }
        if(song!=null) {
            playApp.setName(song!=null?song.getSongName():"");
            playApp.setCoverPicture(song.getCoverPicture());
        }
        playApps.add(playApp);

        playApp = new PlayApp();
        List<PlayList> playLists = playListDao.findAll();
        if(playLists.size()!=0) {
            song = songDao.findById(playLists.get(0).getSongId());
            if(song!=null) {
                playApp.setCoverPicture(song.getCoverPicture());
            }
        }
        if(song!=null) {
            playApp.setName(song.getSongName());
            playApp.setNumber((long) playLists.size());

        } else {
            playApp.setName("");
            playApp.setNumber((long) 0);
        }
        playApp.setType(2);
        playApps.add(playApp);
        latePlayRv.setAdapter(new PlayAppAdapter(context,playApps));
    }

    public void initSonglist() {
        songListRv.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
        songlists = songListDao.findUserSL(sharedPreferences.getLong("userId",-1));
        if(songListAdapter==null){
            songListAdapter = new SongListAdapter(getContext(), songlists,loadDownView);
            songListRv.setAdapter(songListAdapter);
        }
        Handler handler = new Handler() {
            @Override
            public void handleMessage(@NonNull Message msg) {
                if (msg.what == 1) {
                    Bundle bundle = msg.getData();
                    String result = bundle.getString("result");
                    GsonBuilder builder = new GsonBuilder();
                    builder.setDateFormat("yyyy-MM-DD");
                    Gson gson = builder.create();
                    Type listtype = new TypeToken<List<Songlist>>() {}.getType();
                    songlists = gson.fromJson(result, listtype);
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            for(int i = 0;i < songlists.size();i++) {
                                if(!songListDao.isExsit(songlists.get(i).getSlId())){
                                    songListDao.insert(songlists.get(i));
                                } else {
                                    songListDao.update(songlists.get(i));
                                }
                            }
                        }
                    }).start();
                    if(songListAdapter!=null) {
                        songListAdapter.updateData(songlists);
                    }
                }
            }
        };
        String url = StaticHttp.BASEURL + StaticHttp.GET_SONGLIST;
        url += "?createById=" + URLEncoder.encode(String.valueOf(sharedPreferences.getLong("userId",-1)));
        HttpUtils.get(url, handler);
        //弹框
        addSongListPW = new AddSongListPW(getActivity());
    }

    public void initPerMess() {
        sysUser = sysUserDao.findById(sharedPreferences.getLong("userId",-1));
        if(sysUser!=null) {
            MergeImage.showGlideImgDb(getContext(), StaticHttp.STATIC_BASEURL + sysUser.getAvatar(), perPicView, (int) (30 * WindowPixels.DENSITY));
            userNameView.setText(sysUser.getUserName());
        }
        Handler handler = new Handler() {
            @Override
            public void handleMessage(@NonNull Message msg) {
                if (msg.what == 1) {
                    Bundle bundle = msg.getData();
                    String result = bundle.getString("result");
                    try {
                        JSONObject all_json = new JSONObject(result);
                        initUserData(all_json);
                        if(sysUserDao.findById(sysUser.getUserId())==null) {
                            sysUserDao.insert(sysUser);
                            if(!userDetailDao.isExist(userDetailVo.getUserId())) {
                                userDetailDao.insert(userDetailVo);
                            } else {
                                userDetailDao.update(userDetailVo);
                            }
                            if(userDetailVo.getIsMember()==1) {
                                if(memberDao.findById(sysUser.getUserId())==null) {
                                    memberDao.insert(member);
                                } else {
                                    memberDao.update(member);
                                }
                            }
                        } else {
                            sysUserDao.update(sysUser);
                            if(!userDetailDao.isExist(userDetailVo.getUserId())) {
                                userDetailDao.insert(userDetailVo);
                            } else {
                                userDetailDao.update(userDetailVo);
                            }
                            if(userDetailVo.getIsMember()==1) {
                                if(memberDao.findById(sysUser.getUserId())==null) {
                                    memberDao.insert(member);
                                } else {
                                    memberDao.update(member);
                                }
                            }
                        }
                        MergeImage.showGlideImgDb(getContext(), StaticHttp.STATIC_BASEURL + all_json.getString("avatar"), perPicView, (int) (30 * WindowPixels.DENSITY));
                        userNameView.setText(sysUser.getNickName());
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        String url = StaticHttp.SYSTEM_BASEURL + StaticHttp.GET_USER_DETAIL;
        url += "?userId=" + sharedPreferences.getLong("userId",-1);
        HttpUtils.get(url, handler);
    }

    /** 初始化user、userDetail数据*/
    public void initUserData(JSONObject all_json) throws JSONException {
        if(sysUser == null) {
            sysUser = new SysUser();
        }
        sysUser.setUserId(all_json.getLong("userId"));
        sysUser.setUserName(all_json.getString("userName"));
        sysUser.setNickName(all_json.getString("nickName"));
        sysUser.setEmail(all_json.getString("email"));
        sysUser.setPhonenumber(all_json.getString("phonenumber"));
        sysUser.setSex(all_json.getString("sex"));
        sysUser.setAvatar(all_json.getString("avatar"));
        sysUser.setCreateTime(DateUtil.parseString(all_json.getString("createTime")));
        if(userDetailVo==null) {
            userDetailVo = new UserDetailVo();
        }
        userDetailVo.setUserId(all_json.getLong("userId"));
        userDetailVo.setDetail(all_json.getString("detail"));
        userDetailVo.setBirthday(DateUtil.parseString(all_json.getString("birthday")));
        userDetailVo.setAddress(all_json.getString("address"));
        userDetailVo.setPerBg(all_json.getString("perBg"));
        userDetailVo.setIsMember(all_json.getInt("isMember"));
        if(userDetailVo.getIsMember()==1) {
            if(member==null) {
                member = new Member();
            }
            member.setUserId(all_json.getLong("mId"));
            member.setValidDay(all_json.getLong("validDay"));
            //member.setmGrade(all_json.getInt("mGrade"));
            member.setUserId(all_json.getLong("userId"));
            member.setEnrollDate(DateUtil.parseString(all_json.getString("enrollDate")));
        }
    }

    public void initOnClick() {
        slAddBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addSongListPW.showAtLocation(v);
            }
        });
        /**
         * 跳转到本地音乐页面
         */
        localMusicLin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, LocalMusicActivity.class);
                context.startActivity(intent);
            }
        });
        /**
         * 跳转到下载页
         */
        downloadLin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, DownLoadActivity.class);
                context.startActivity(intent);
            }
        });
        /**
         * 跳转到电台页
         */
        radioStationLin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, RadioStationActivity.class);
                context.startActivity(intent);
            }
        });
        /**
         * 跳转到收藏页
         */
        collectLin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, CollectActivity.class);
                context.startActivity(intent);
            }
        });
        /**
         * 跳转到关注页
         */
        followLin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, FollowActivity.class);
                context.startActivity(intent);
            }
        });
    }
    UpdateSonglistReceiver updateDataReceiver;
    public void initData(){
        loadDownView = new LoadDownView(getContext());
        updateDataReceiver = new UpdateSonglistReceiver();
        IntentFilter intentFilter = new IntentFilter(UpdateSonglistReceiver.ACTION);
        getContext().registerReceiver(updateDataReceiver,intentFilter);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        getContext().unregisterReceiver(updateDataReceiver);
    }

    public void findByIdAll(View view) {
        context = getContext();
        userNameView = view.findViewById(R.id.username);
        perPicView = view.findViewById(R.id.per_pic);
        songListRv = view.findViewById(R.id.rv3);
        slAddBt = view.findViewById(R.id.sl_add_bt);
        localMusicLin = view.findViewById(R.id.local_music_lin);
        downloadLin = view.findViewById(R.id.download_lin);
        radioStationLin = view.findViewById(R.id.radio_station_lin);
        collectLin = view.findViewById(R.id.collect_lin);
        followLin = view.findViewById(R.id.follow_lin);
    }

    public class UpdateSonglistReceiver extends BroadcastReceiver{

        public static final String ACTION = "my_data_update";
        public static final String CONTROLLER = "songlist";

        @Override
        public void onReceive(Context context, Intent intent) {
            String controller = intent.getStringExtra(CONTROLLER);
            switch (controller){
                case "songlist":{receiverSonglist();}break;
                case "collectiones":{receiverCollectiones();}break;
            }
            loadDownView.getLoadDownPopuWindow().showPopupWindow();
        }
    }

    private void receiverSonglist(){
        Handler handler = new Handler() {
            @Override
            public void handleMessage(@NonNull Message msg) {
                if (msg.what == 1) {
                    Bundle bundle = msg.getData();
                    String result = bundle.getString("result");
                    GsonBuilder builder = new GsonBuilder();
                    builder.setDateFormat("yyyy-MM-DD");
                    Gson gson = builder.create();
                    Type listtype = new TypeToken<List<Songlist>>() {}.getType();
                    songlists = gson.fromJson(result, listtype);
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            for(int i = 0;i < songlists.size();i++) {
                                if(!songListDao.isExsit(songlists.get(i).getSlId())) {
                                    songListDao.insert(songlists.get(i));
                                } else {
                                    songListDao.update(songlists.get(i));
                                }
                            }
                        }
                    }).start();
                    if(songListAdapter!=null) {
                        songListAdapter.updateData(songlists);
                    }
                }
            }
        };
        String url = StaticHttp.BASEURL + StaticHttp.GET_SONGLIST;
        url += "?createById=" + URLEncoder.encode(String.valueOf(sharedPreferences.getLong("userId",-1)));
        HttpUtils.get(url, handler);
        addSongListPW.getPopupWindow();
    }
    private void receiverCollectiones(){
        CollectionesHttp.list(context,collectRv,sysUser.getUserId());
    }
}
