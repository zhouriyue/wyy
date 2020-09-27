package com.gxuwz.beethoven.page.index.findview.spefun.rankinglist;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;

import com.gxuwz.beethoven.R;
import com.gxuwz.beethoven.adapter.find.spefun.rankinglist.RLItemAdapter;
import com.gxuwz.beethoven.model.entity.Song;
import com.gxuwz.beethoven.model.entity.find.RankingList;
import com.gxuwz.beethoven.model.entity.find.spefun.rankinglist.RankingListShow;

import java.util.ArrayList;
import java.util.List;

public class RankingListActivity extends AppCompatActivity {


    LinearLayout toBackLin;
    RecyclerView rankingListRv;
    List<RankingListShow> rklShowList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(getResources().getColor(R.color.black));
        }
        setContentView(R.layout.rankinglist);
        /**
         * 隐藏标题栏
         */
        getSupportActionBar().hide();
        findByIdAndNew();
    }

    public void findByIdAndNew(){
        rankingListRv = findViewById(R.id.rankinglist_rv);
        rklShowList = new ArrayList<RankingListShow>();
        setData();
        toBackLin = findViewById(R.id.to_back_lin);
        toBackLin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        rankingListRv.setLayoutManager(new StaggeredGridLayoutManager(1,StaggeredGridLayoutManager.VERTICAL));
        rankingListRv.setAdapter(new RLItemAdapter(RankingListActivity.this,rklShowList));
    }

    public void setData(){
        RankingListShow rankingListShow = new RankingListShow();
        rankingListShow.setShowType(1);
        rankingListShow.setTitle("榜单推荐");
        List<RankingList> rankingListList = new ArrayList<RankingList>();
        RankingList rankingList = new RankingList();
        rankingList.setName("硬地原创歌曲");
        rankingList.setImg("youth");
        rankingList.setUpdateType("每天更新");
        rankingListList.add(rankingList);

        rankingList = new RankingList();
        rankingList.setName("硬地原创歌曲");
        rankingList.setImg("youth");
        rankingList.setUpdateType("每天更新");
        rankingListList.add(rankingList);

        rankingList = new RankingList();
        rankingList.setName("硬地原创歌曲");
        rankingList.setImg("youth");
        rankingList.setUpdateType("每天更新");
        rankingListList.add(rankingList);
        rankingListShow.setRankingListList(rankingListList);
        rklShowList.add(rankingListShow);

        /**
         * 竖着显示
         */
        rankingListShow = new RankingListShow();
        rankingListShow.setShowType(2);
        rankingListShow.setTitle("官方榜");
        rankingListList = new ArrayList<RankingList>();
        rankingList = new RankingList();
        rankingList.setName("硬地原创歌曲");
        rankingList.setImg("youth");
        rankingList.setUpdateType("每天更新");
        List<Song> songList = new ArrayList<Song>();
        Song song = new Song();
        song.setSongName("他只是经过");
        song.setSinger("h3R3/Felix Bennett");
        songList.add(song);
        song = new Song();
        song.setSongName("他只是经过");
        song.setSinger("h3R3/Felix Bennett");
        songList.add(song);
        song = new Song();
        song.setSongName("他只是经过");
        song.setSinger("h3R3/Felix Bennett");
        songList.add(song);
        rankingList.setSongList(songList);
        rankingListList.add(rankingList);

        rankingList = new RankingList();
        rankingList.setName("硬地原创歌曲");
        rankingList.setImg("youth");
        rankingList.setUpdateType("每天更新");
        songList = new ArrayList<Song>();
        song = new Song();
        song.setSongName("他只是经过");
        song.setSinger("h3R3/Felix Bennett");
        songList.add(song);
        song = new Song();
        song.setSongName("他只是经过");
        song.setSinger("h3R3/Felix Bennett");
        songList.add(song);
        song = new Song();
        song.setSongName("他只是经过");
        song.setSinger("h3R3/Felix Bennett");
        songList.add(song);
        rankingList.setSongList(songList);
        rankingListList.add(rankingList);

        rankingList = new RankingList();
        rankingList.setName("硬地原创歌曲");
        rankingList.setImg("youth");
        rankingList.setUpdateType("每天更新");
        songList = new ArrayList<Song>();
        song = new Song();
        song.setSongName("他只是经过");
        song.setSinger("h3R3/Felix Bennett");
        songList.add(song);
        song = new Song();
        song.setSongName("他只是经过");
        song.setSinger("h3R3/Felix Bennett");
        songList.add(song);
        song = new Song();
        song.setSongName("他只是经过");
        song.setSinger("h3R3/Felix Bennett");
        songList.add(song);
        rankingList.setSongList(songList);
        rankingListList.add(rankingList);
        rankingListShow.setRankingListList(rankingListList);
        rklShowList.add(rankingListShow);

        //第三个数据
        rankingListShow = new RankingListShow();
        rankingListShow.setShowType(1);
        rankingListShow.setTitle("榜单推荐");
        rankingListList = new ArrayList<RankingList>();
        rankingList = new RankingList();
        rankingList.setName("硬地原创歌曲");
        rankingList.setImg("youth");
        rankingList.setUpdateType("每天更新");
        rankingListList.add(rankingList);

        rankingList = new RankingList();
        rankingList.setName("硬地原创歌曲");
        rankingList.setImg("youth");
        rankingList.setUpdateType("每天更新");
        rankingListList.add(rankingList);

        rankingList = new RankingList();
        rankingList.setName("硬地原创歌曲");
        rankingList.setImg("youth");
        rankingList.setUpdateType("每天更新");
        rankingListList.add(rankingList);
        rankingListShow.setRankingListList(rankingListList);
        rklShowList.add(rankingListShow);

        //第三个数据
        rankingListShow = new RankingListShow();
        rankingListShow.setShowType(1);
        rankingListShow.setTitle("榜单推荐");
        rankingListList = new ArrayList<RankingList>();
        rankingList = new RankingList();
        rankingList.setName("硬地原创歌曲");
        rankingList.setImg("youth");
        rankingList.setUpdateType("每天更新");
        rankingListList.add(rankingList);

        rankingList = new RankingList();
        rankingList.setName("硬地原创歌曲");
        rankingList.setImg("youth");
        rankingList.setUpdateType("每天更新");
        rankingListList.add(rankingList);

        rankingList = new RankingList();
        rankingList.setName("硬地原创歌曲");
        rankingList.setImg("youth");
        rankingList.setUpdateType("每天更新");
        rankingListList.add(rankingList);
        rankingListShow.setRankingListList(rankingListList);
        rklShowList.add(rankingListShow);

        //第三个数据
        rankingListShow = new RankingListShow();
        rankingListShow.setShowType(1);
        rankingListShow.setTitle("榜单推荐");
        rankingListList = new ArrayList<RankingList>();
        rankingList = new RankingList();
        rankingList.setName("硬地原创歌曲");
        rankingList.setImg("youth");
        rankingList.setUpdateType("每天更新");
        rankingListList.add(rankingList);

        rankingList = new RankingList();
        rankingList.setName("硬地原创歌曲");
        rankingList.setImg("youth");
        rankingList.setUpdateType("每天更新");
        rankingListList.add(rankingList);

        rankingList = new RankingList();
        rankingList.setName("硬地原创歌曲");
        rankingList.setImg("youth");
        rankingList.setUpdateType("每天更新");
        rankingListList.add(rankingList);
        rankingListShow.setRankingListList(rankingListList);
        rklShowList.add(rankingListShow);

        //第三个数据
        rankingListShow = new RankingListShow();
        rankingListShow.setShowType(1);
        rankingListShow.setTitle("榜单推荐");
        rankingListList = new ArrayList<RankingList>();
        rankingList = new RankingList();
        rankingList.setName("硬地原创歌曲");
        rankingList.setImg("youth");
        rankingList.setUpdateType("每天更新");
        rankingListList.add(rankingList);

        rankingList = new RankingList();
        rankingList.setName("硬地原创歌曲");
        rankingList.setImg("youth");
        rankingList.setUpdateType("每天更新");
        rankingListList.add(rankingList);

        rankingList = new RankingList();
        rankingList.setName("硬地原创歌曲");
        rankingList.setImg("youth");
        rankingList.setUpdateType("每天更新");
        rankingListList.add(rankingList);
        rankingListShow.setRankingListList(rankingListList);
        rklShowList.add(rankingListShow);

        //第三个数据
        rankingListShow = new RankingListShow();
        rankingListShow.setShowType(1);
        rankingListShow.setTitle("榜单推荐");
        rankingListList = new ArrayList<RankingList>();
        rankingList = new RankingList();
        rankingList.setName("硬地原创歌曲");
        rankingList.setImg("youth");
        rankingList.setUpdateType("每天更新");
        rankingListList.add(rankingList);

        rankingList = new RankingList();
        rankingList.setName("硬地原创歌曲");
        rankingList.setImg("youth");
        rankingList.setUpdateType("每天更新");
        rankingListList.add(rankingList);

        rankingList = new RankingList();
        rankingList.setName("硬地原创歌曲");
        rankingList.setImg("youth");
        rankingList.setUpdateType("每天更新");
        rankingListList.add(rankingList);
        rankingListShow.setRankingListList(rankingListList);
        rklShowList.add(rankingListShow);

        //第三个数据
        rankingListShow = new RankingListShow();
        rankingListShow.setShowType(1);
        rankingListShow.setTitle("榜单推荐");
        rankingListList = new ArrayList<RankingList>();
        rankingList = new RankingList();
        rankingList.setName("硬地原创歌曲");
        rankingList.setImg("youth");
        rankingList.setUpdateType("每天更新");
        rankingListList.add(rankingList);

        rankingList = new RankingList();
        rankingList.setName("硬地原创歌曲");
        rankingList.setImg("youth");
        rankingList.setUpdateType("每天更新");
        rankingListList.add(rankingList);

        rankingList = new RankingList();
        rankingList.setName("硬地原创歌曲");
        rankingList.setImg("youth");
        rankingList.setUpdateType("每天更新");
        rankingListList.add(rankingList);
        rankingListShow.setRankingListList(rankingListList);
        rklShowList.add(rankingListShow);
    }
}
