package com.gxuwz.beethoven.listener.localmusic;

import android.view.View;

import androidx.viewpager.widget.ViewPager;

import com.gxuwz.beethoven.R;

public class TopMenuOnClickListener implements View.OnClickListener{
    ViewPager localMenu;

    public TopMenuOnClickListener(ViewPager localMenu) {
        this.localMenu = localMenu;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.local_music_single:{
                /**
                 * 当点击“单曲”按钮切换到单曲页
                 */
                localMenu.setCurrentItem(0);
            };break;
            case R.id.local_music_singer:{
                /**
                 * 当点击“单曲”按钮切换到单曲页
                 */
                localMenu.setCurrentItem(1);
            };break;
            case R.id.local_music_album:{
                /**
                 * 当点击“单曲”按钮切换到单曲页
                 */
                localMenu.setCurrentItem(2);
            };break;
            case R.id.local_music_file:{
                /**
                 * 当点击“单曲”按钮切换到单曲页
                 */
                localMenu.setCurrentItem(3);
            };break;
        }
    }
}
