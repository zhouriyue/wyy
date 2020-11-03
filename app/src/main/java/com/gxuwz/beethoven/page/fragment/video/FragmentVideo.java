package com.gxuwz.beethoven.page.fragment.video;

import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import com.gxuwz.beethoven.R;
import com.gxuwz.beethoven.page.index.videoview.VideoViewInit;

public class FragmentVideo extends Fragment {

    VideoViewInit videoViewInit;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.video, container, false);
        videoViewInit = new VideoViewInit(view,inflater,getActivity().getWindowManager());
        videoViewInit.init(getActivity());
        return view;
    }
}
