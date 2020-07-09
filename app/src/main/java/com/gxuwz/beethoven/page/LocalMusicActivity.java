package com.gxuwz.beethoven.page;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.widget.ListView;

import com.gxuwz.beethoven.R;
import com.gxuwz.beethoven.adapter.LocalMusicAdapter;
import com.gxuwz.beethoven.model.entity.MusicUtils;
import com.gxuwz.beethoven.model.entity.Song;

import java.util.ArrayList;
import java.util.List;

public class LocalMusicActivity extends Activity {
    private ListView mListView;
    private List<Song> list;
    private LocalMusicAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_local_music);
        initView();
    }

    /**
     * 初始化view
     */
    private void initView() {
        getSDCardRWPermission(this);
        mListView = (ListView) findViewById(R.id.main_listview);
        list = new ArrayList<>();
        //把扫描到的音乐赋值给list
        list = MusicUtils.getMusicData(this);
        adapter = new LocalMusicAdapter(this,list);
        mListView.setAdapter(adapter);
    }

    public static void getSDCardRWPermission(Activity activity){
        int permission = ActivityCompat.checkSelfPermission(activity,
                Manifest.permission.WRITE_EXTERNAL_STORAGE);

        if (permission != PackageManager.PERMISSION_GRANTED) {
            // We don't have permission so prompt the user
            ActivityCompat.requestPermissions(activity, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                    1);
        }
    }

}
