package com.gxuwz.beethoven.adapter.find;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.gxuwz.beethoven.R;
import com.gxuwz.beethoven.model.entity.current.Songlist;
import com.gxuwz.beethoven.page.index.findview.songlist.SongListFActivity;
import com.gxuwz.beethoven.util.MergeImage;
import com.gxuwz.beethoven.util.staticdata.StaticHttp;

import java.util.List;

public class SongListAdapter extends RecyclerView.Adapter<SongListAdapter.SongListViewHolder> {

    Context context;
    List<Songlist> songlists;
    protected boolean isScrolling = false;

    public SongListAdapter(Context context, List<Songlist> songlists) {
        this.context = context;
        this.songlists = songlists;
    }

    @NonNull
    @Override
    public SongListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new SongListViewHolder(LayoutInflater.from(context).inflate(R.layout.item_recsl,null));
    }

    @Override
    public void onBindViewHolder(@NonNull SongListViewHolder holder, int position) {
        Songlist songlist = songlists.get(position);
        MergeImage.showGlideImgDb(context,
                StaticHttp.STATIC_BASEURL+songlist.getCoverPicture(),holder.slImg,10);
        holder.playNumber.setText(songlist.getPlayNumber()+"");
        holder.slName.setText(songlist.getSlName());
        holder.onclickSongList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context,SongListFActivity.class);
                intent.putExtra("songlist",songlist);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return songlists.size();
    }

    class SongListViewHolder extends RecyclerView.ViewHolder{

        LinearLayout onclickSongList;
        ImageView slImg;
        TextView playNumber,slName;

        public SongListViewHolder(@NonNull View itemView) {
            super(itemView);
            onclickSongList = itemView.findViewById(R.id.onclick_songlist);
            slImg = itemView.findViewById(R.id.sl_img);
            playNumber = itemView.findViewById(R.id.play_number);
            slName = itemView.findViewById(R.id.sl_name);
        }
    }

    public boolean isScrolling() {
        return isScrolling;
    }

    public void setScrolling(boolean scrolling) {
        isScrolling = scrolling;
    }
}
