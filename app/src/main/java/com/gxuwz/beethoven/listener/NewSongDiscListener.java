package com.gxuwz.beethoven.listener;

import android.graphics.Color;
import android.view.View;
import android.widget.GridView;
import android.widget.TextView;

import com.gxuwz.beethoven.R;

public class NewSongDiscListener implements View.OnClickListener {
    TextView newSongTV,newDiscTV;
    GridView newSongGridView;
    GridView newDiscGridView;

    public NewSongDiscListener(TextView newSongTV, TextView newDiscTV, GridView newSongGridView, GridView newDiscGridView) {
        this.newSongTV = newSongTV;
        this.newDiscTV = newDiscTV;
        this.newSongGridView = newSongGridView;
        this.newDiscGridView = newDiscGridView;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            /*case R.id.new_song_tv:{
                System.out.println("new song");
                newSongTV.setTextColor(Color.parseColor("#000000"));
                newDiscTV.setTextColor(Color.parseColor("#707070"));

                newSongGridView.setVisibility(View.VISIBLE);
                newDiscGridView.setVisibility(View.GONE);
            };break;
            case R.id.new_disc_tv:{
                System.out.println("new disc");
                newSongTV.setTextColor(Color.parseColor("#707070"));
                newDiscTV.setTextColor(Color.parseColor("#000000"));

                newSongGridView.setVisibility(View.GONE);
                newDiscGridView.setVisibility(View.VISIBLE);
            };break;*/
        }
    }
}
