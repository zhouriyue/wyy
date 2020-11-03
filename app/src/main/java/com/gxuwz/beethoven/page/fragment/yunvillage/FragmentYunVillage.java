package com.gxuwz.beethoven.page.fragment.yunvillage;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.gxuwz.beethoven.R;
import com.gxuwz.beethoven.adapter.FragmentCustomAdapter;
import com.gxuwz.beethoven.page.fragment.yunvillage.follow.FragmentFollow;
import com.gxuwz.beethoven.page.fragment.yunvillage.plaza.FragmentPlaza;
import com.gxuwz.beethoven.page.index.cloudview.CloudViewInit;

import java.util.ArrayList;
import java.util.List;

public class FragmentYunVillage extends Fragment {

    ViewPager cloudPager;

    CloudViewInit cloudViewInit;

    List<Fragment> fragmentList;

    TextView plazaTitle,followTitle;
    TextView wordsTitle[];

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.cloud, container, false);
        //cloudViewInit = new CloudViewInit(view,getActivity(),getActivity().getWindowManager(),getLayoutInflater());
        //cloudViewInit.init();
        init(view);
        return view;
    }

    public void init(View view){
        findByIdAll(view);
        initPager();
        intText();
    }

    public void intText(){
        wordsTitle = new TextView[2];
        wordsTitle[0]=plazaTitle;
        wordsTitle[1]=followTitle;
    }

    public void initPager(){
        fragmentList = new ArrayList<Fragment>();
        Fragment fragmentPlaza  = new FragmentPlaza();
        Fragment fragmentFollow  = new FragmentFollow();
        fragmentList.add(fragmentPlaza);
        fragmentList.add(fragmentFollow);
        cloudPager.setAdapter(new FragmentCustomAdapter(getChildFragmentManager(),fragmentList));
        cloudPager.setCurrentItem(0);
    }

    public void findByIdAll(View view){
        cloudPager = view.findViewById(R.id.cloud_viewpager);
        plazaTitle = view.findViewById(R.id.plaza_title);
        followTitle = view.findViewById(R.id.follow_title);
    }

}
