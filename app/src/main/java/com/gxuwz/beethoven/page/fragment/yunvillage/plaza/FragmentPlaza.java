package com.gxuwz.beethoven.page.fragment.yunvillage.plaza;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.gxuwz.beethoven.R;
import com.gxuwz.beethoven.page.index.cloudview.PlazaViewInit;

public class FragmentPlaza extends Fragment {

    /**
     * "广场"菜单
     */
    PlazaViewInit plazaViewInit;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.plaza,null);
        initView(view);
        return view;
    }

    public void initView(View view){
        plazaViewInit = new PlazaViewInit(getActivity(),view,getActivity().getWindowManager());
        plazaViewInit.init();
    }
}
