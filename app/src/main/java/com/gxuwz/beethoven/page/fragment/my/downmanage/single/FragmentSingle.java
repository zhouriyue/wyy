package com.gxuwz.beethoven.page.fragment.my.downmanage.single;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import com.gxuwz.beethoven.R;
import com.gxuwz.beethoven.page.fragment.my.localmusic.singer.SingerViewInit;
import com.gxuwz.beethoven.page.fragment.my.localmusic.songle.SingleViewInit;

public class FragmentSingle extends Fragment {

    SingerViewInit singerViewInit;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.local_music_singer,null);
        init(view);
        return view;
    }

    public void init(View view) {
        initData(view);
    }

    public void initData(View view) {
        singerViewInit = new SingerViewInit(view);
        singerViewInit.init(getContext());
    }
}
