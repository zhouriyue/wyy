package com.gxuwz.beethoven.page.fragment.find;

import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import com.gxuwz.beethoven.R;
import com.gxuwz.beethoven.page.index.findview.FindViewInit;

public class FragmentFind extends Fragment {

    FindViewInit findViewInit;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.find, container, false);
        init(view);
        return view;
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public void init(View view){
        findViewInit = new FindViewInit(view,getActivity().getWindowManager(),getActivity());
        findViewInit.init(getLayoutInflater());
    }
}
