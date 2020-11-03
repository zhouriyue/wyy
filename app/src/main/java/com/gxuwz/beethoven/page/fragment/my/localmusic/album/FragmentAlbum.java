package com.gxuwz.beethoven.page.fragment.my.localmusic.album;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.gxuwz.beethoven.R;
import com.gxuwz.beethoven.page.fragment.my.localmusic.LocalMusicActivity;

public class FragmentAlbum extends Fragment {

    AlbumViewInit albumViewInit;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.local_music_album,null);
        init(view);
        return view;
    }

    public void init(View view) {
        albumViewInit = new AlbumViewInit(view);
        albumViewInit.init(getContext());
    }
}
