package com.gxuwz.beethoven.page.fragment.yunvillage.follow;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.gxuwz.beethoven.R;
import com.gxuwz.beethoven.page.index.cloudview.FollowInit;

public class FragmentFollow extends Fragment {

    /**
     * “关注”
     */
    FollowInit followInit;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.follow_cloud,null);
        initView(view);
        return view;
    }

    public void initView(View view){
        followInit = new FollowInit(view,getActivity());
        followInit.init();
    }
}
