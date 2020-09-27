package com.gxuwz.beethoven.page.index.findview.songlist;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.gxuwz.beethoven.R;
import com.gxuwz.beethoven.model.entity.SongList;
import com.gxuwz.beethoven.util.BlurUtil;
import com.gxuwz.beethoven.util.HttpUtil;
import com.gxuwz.beethoven.util.MergeImage;

import java.util.List;

/**
 * “发现”页,推荐类歌单
 */
public class SongListFActivity extends AppCompatActivity {

    Context context;
    LinearLayout toBackLin;

    LinearLayout diagonal;
    ImageView songlistImg,perPic;
    TextView songlistName,username,detail,messNumber,shareNumber,playNumber,collectNumber;
    SongList songList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(getResources().getColor(R.color.black));
        }
        setContentView(R.layout.activity_song_list_f);
        /**
         * 隐藏标题栏
         */
        getSupportActionBar().hide();
        findByIdAndNew();
        init();
    }

    public void init(){
        diagonal.setBackground(new BitmapDrawable(BlurUtil.doBlur(HttpUtil.getRes("youth",SongListFActivity.this),1,20)));
        songlistImg.setImageBitmap(MergeImage.roundedCustomDB(HttpUtil.getRes("youth",context),150,150,8));
        perPic.setImageBitmap(MergeImage.circleShow(HttpUtil.getRes("zhoushen1",context)));
        songlistName.setText("700首流行经典老歌|80/90/00后怀旧珍藏");
        username.setText("zhouriyue");
        detail.setText("最近更新：2020.8.27，持续更新中。最近更新：2020.8.27，持续更新中。最近更新：2020.8.27，持续更新中。");
        messNumber.setText("3434");
        shareNumber.setText("10977");
        playNumber.setText("1.2亿");
        collectNumber.setText("200万");
    }

    public void findByIdAndNew(){
        context = SongListFActivity.this;
        toBackLin = findViewById(R.id.to_back_lin);
        toBackLin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        diagonal = findViewById(R.id.diagonal);
        songlistImg = findViewById(R.id.songlist_img);
        perPic = findViewById(R.id.per_pic);
        songlistName = findViewById(R.id.songlist_name);
        username = findViewById(R.id.username);
        detail = findViewById(R.id.detail);
        messNumber = findViewById(R.id.mess_number);
        shareNumber = findViewById(R.id.share_number);
        playNumber = findViewById(R.id.play_number);
        collectNumber = findViewById(R.id.collect_number);
    }
}
