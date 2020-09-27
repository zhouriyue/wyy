package com.gxuwz.beethoven.page.index.playview;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.view.MotionEvent;
import android.view.View;
import com.gxuwz.beethoven.R;
import com.gxuwz.beethoven.component.lyc.LycicView;
import com.gxuwz.beethoven.util.Player;
import com.gxuwz.beethoven.util.lyc.LrcMusic;
import com.gxuwz.beethoven.util.lyc.Utils;
import java.io.InputStream;
import java.util.ArrayList;

public class LyricShowView {

    Context context;
    View lycShowView;
    LycicView view;
    private ArrayList<LrcMusic> lrcs;
    private ArrayList<String> list;
    private ArrayList<Long> list1;
    private int lrc_index;

    public LyricShowView(Context context,View lycShowView) {
        this.context = context;
        this.lycShowView = lycShowView;
        this.view = lycShowView.findViewById(R.id.view);
        initEvents();
        Player.lyriscView = view;
        Player.lyriscTimes = list1;
    }

    Handler handler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            return false;
        }
    });

    private void initEvents() {
        InputStream is = context.getResources().openRawResource(R.raw.zhj);

        // BufferedReader br = new BufferedReader(new InputStreamReader(is));
        list = new ArrayList<String>();
        list1 = new ArrayList<>();
        lrcs = Utils.redLrc(is);
        for (int i = 0; i < lrcs.size(); i++) {
            list.add(lrcs.get(i).getLrc());
            list1.add((long) lrcs.get(i).getTime());
        }
        view.setLyricText(list, list1);
    }
}
