package com.gxuwz.beethoven.page.fragment.search;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.provider.Settings;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.ListPopupWindow;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.gxuwz.beethoven.R;
import com.gxuwz.beethoven.adapter.search.SeaSongAdapter;
import com.gxuwz.beethoven.model.entity.current.Song;
import com.gxuwz.beethoven.page.fragment.my.songlist.LoadDownView;
import com.gxuwz.beethoven.util.HttpUtils;
import com.gxuwz.beethoven.util.staticdata.StaticHttp;

import java.lang.reflect.Type;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

public class SeaSongActivity extends AppCompatActivity {

    LinearLayout toBack;
    RecyclerView songListRv;
    List<Song> songList;
    LoadDownView loadDownView;
    SearchView searchView;
    ListPopupWindow searchTipPw;
    SeaSongAdapter seaSongAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(getResources().getColor(R.color.black));
        }
        setContentView(R.layout.activity_sea_song);
        /**
         * 隐藏标题栏
         * hide title box
         */
        getSupportActionBar().hide();
        init();
    }

    public void init(){
        findByIdAll();
        initOnClick();
        initRecyclerView();
    }

    public void initRecyclerView(){
        loadDownView = new LoadDownView(SeaSongActivity.this);
        //搜索框不自动缩小为一个搜索图标，而是match_parent
        searchView.setIconifiedByDefault(false);
        //默认提示文本
        searchView.setQueryHint("搜索");
        songListRv.setLayoutManager(new StaggeredGridLayoutManager(1,StaggeredGridLayoutManager.VERTICAL));
        songList = new ArrayList<>();
        Intent intent = getIntent();
        String keyword = intent.getStringExtra("keyword");
        setSearchSongList(keyword);
    }

    public void initOnClick(){
        toBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                setSearchSongList(query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return true;
            }
        });
    }

    public void findByIdAll(){
        songListRv = findViewById(R.id.song_list_rv);
        toBack = findViewById(R.id.to_back_lin);
        searchView = findViewById(R.id.search_view);
        searchTipPw = new ListPopupWindow(SeaSongActivity.this);
        searchTipPw.setAnchorView(searchView);
        searchTipPw.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
    }

    public void setSearchSongList(String keyword){
        Handler searchHanlder = new Handler(){
            @Override
            public void handleMessage(@NonNull Message msg) {
                if(msg.what==1) {
                    Bundle bundle = msg.getData();
                    String result = bundle.getString("result");
                    GsonBuilder builder = new GsonBuilder();
                    builder.setDateFormat("yyyy-MM-DD");
                    Gson gson = builder.create();
                    Type listtype = new TypeToken<List<Song>>(){}.getType();
                    List<Song> songList = gson.fromJson(result,listtype);
                    if(seaSongAdapter==null) {
                        seaSongAdapter = new SeaSongAdapter(SeaSongActivity.this,songList,loadDownView);
                        songListRv.setAdapter(seaSongAdapter);
                    } else {
                        songListRv.removeAllViews();
                        seaSongAdapter.updateData(songList);
                    }
                }
                if(searchTipPw!=null&&!searchTipPw.isShowing()){
                    searchTipPw.show();
                }
            }
        };
        String url = StaticHttp.BASEURL+StaticHttp.SELECT_SONG;
        url += "?wordKey="+ URLEncoder.encode(keyword);
        HttpUtils.get(url,searchHanlder);
    }

    @Override
    protected  void  onActivityResult( int  requestCode,  int  resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 10) {
            if (Build.VERSION.SDK_INT >= 23) {
                if (!Settings.canDrawOverlays(this)) {
                    // SYSTEM_ALERT_WINDOW permission not granted...
                    Toast.makeText(SeaSongActivity.this, "not granted", Toast.LENGTH_SHORT);
                }
            }
        }
    }
}
