package com.gxuwz.beethoven.page.fragment.my.radiostation;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;

import com.gxuwz.beethoven.R;
import com.gxuwz.beethoven.page.fragment.my.radiostation.listening.ListeningItemInit;
import com.gxuwz.beethoven.page.fragment.my.radiostation.recommend.RecommendItemInit;

public class RadioStationActivity extends AppCompatActivity {

    LinearLayout toBackLin;
    RecommendItemInit recommendItemInit;
    ListeningItemInit listeningItemInit;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(getResources().getColor(R.color.black));
        }
        setContentView(R.layout.radiostation);
        /**
         * 隐藏标题栏
         */
        getSupportActionBar().hide();
        findByIdAndNew();
        /**
         * 为你推荐模块
         */
        recommendItemInit.init(context);
        /**
         * 听听模块
         */
        listeningItemInit.init(context);
    }

    public void findByIdAndNew(){
        context = RadioStationActivity.this;
        recommendItemInit = new RecommendItemInit(findViewById(R.id.radio_station_rv));
        listeningItemInit = new ListeningItemInit(findViewById(R.id.listenner_rv));
        toBackLin = findViewById(R.id.to_back_lin);
        toBackLin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}
