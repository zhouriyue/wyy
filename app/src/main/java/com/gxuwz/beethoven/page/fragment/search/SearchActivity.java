package com.gxuwz.beethoven.page.fragment.search;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListPopupWindow;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.reflect.TypeToken;
import com.gxuwz.beethoven.R;
import com.gxuwz.beethoven.adapter.search.tip.TipAdapter;
import com.gxuwz.beethoven.model.entity.current.Song;
import com.gxuwz.beethoven.util.HttpUtils;
import com.gxuwz.beethoven.util.staticdata.StaticHttp;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Type;
import java.net.URLEncoder;
import java.util.Date;
import java.util.List;

public class SearchActivity extends AppCompatActivity {

    LinearLayout toBackLin;

    SearchView searchView;
    ListPopupWindow searchTipPw;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(getResources().getColor(R.color.black));
        }
        setContentView(R.layout.activity_search);
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
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @SuppressLint("NewApi")
            @Override
            public boolean onQueryTextSubmit(String query) {
                Intent intent = new Intent(SearchActivity.this,SeaSongActivity.class);
                intent.putExtra("keyword",query);
                startActivity(intent);
                return true;
            }

            @SuppressLint("NewApi")
            @Override
            public boolean onQueryTextChange(String newText) {
                try {
                    if(!"".equals(newText)){
                        setSearchSongList(newText);
                    }
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                return false;
            }
        });
    }

    public void setSearchSongList(String newText) throws UnsupportedEncodingException {
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
                    searchTipPw.setAdapter((ListAdapter) new TipAdapter(SearchActivity.this,songList));
                }
                if(!searchTipPw.isShowing()){
                    searchTipPw.show();
                }
            }
        };
        String url = StaticHttp.BASEURL+StaticHttp.SELECT_SONG;
        url += "?wordKey="+URLEncoder.encode(newText);
        HttpUtils.get(url,searchHanlder);
    }


    @SuppressLint("NewApi")
    public void initOnClick(){
        toBackLin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        searchTipPw.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @SuppressLint("NewApi")
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                searchTipPw.dismiss();
            }
        });
    }

    @SuppressLint("NewApi")
    public void findByIdAll(){
        toBackLin = findViewById(R.id.to_back_lin);
        searchView = findViewById(R.id.search_view);
        //搜索框不自动缩小为一个搜索图标，而是match_parent
        searchView.setIconifiedByDefault(false);
        //默认提示文本
        searchView.setQueryHint("搜索");
        searchTipPw = new ListPopupWindow(this);
        searchTipPw.setAnchorView(searchView);
        searchTipPw.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
    }

}
