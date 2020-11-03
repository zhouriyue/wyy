package com.gxuwz.beethoven.adapter.search.tip;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.gxuwz.beethoven.R;
import com.gxuwz.beethoven.model.entity.current.Song;
import com.gxuwz.beethoven.page.fragment.search.SeaSongActivity;

import java.util.List;

public class TipAdapter extends BaseAdapter {

    Context context;
    List<Song> songList;

    public TipAdapter(Context context, List<Song> songList) {
        this.context = context;
        this.songList = songList;
    }

    @Override
    public int getCount() {
        return songList.size();
    }

    @Override
    public Object getItem(int position) {
        return songList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = LayoutInflater.from(context).inflate(R.layout.search_tip,null);
        TextView textView = convertView.findViewById(R.id.song_name);
        textView.setText(songList.get(position).getSongName());
        LinearLayout pid = convertView.findViewById(R.id.position);
        pid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent  intent = new Intent(context, SeaSongActivity.class);
                intent.putExtra("keyword",songList.get(position).getSongName());
                context.startActivity(intent);
            }
        });
        return convertView;
    }
}
