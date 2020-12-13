package com.gxuwz.beethoven.adapter.find;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.gxuwz.beethoven.R;
import com.gxuwz.beethoven.model.entity.find.MusicCal;
import com.gxuwz.beethoven.page.index.findview.musiccal.MusicCalActivity;
import com.gxuwz.beethoven.util.HttpUtils;
import com.gxuwz.beethoven.util.MergeImage;
import com.gxuwz.beethoven.util.WindowPixels;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class MusicCalAdapter extends RecyclerView.Adapter<MusicCalAdapter.MusicCalViewHolder> {

    Context context;
    List<MusicCal> musicCalList;
    Timer timer;
    TimerTask timerTask;
    public int size,current;
    public MusicCal musicCal,musicCalNext;
    public ImageView image,nextImage;
    public TextView title,time;
    protected boolean isScrolling = false;


    public MusicCalAdapter(Context context, List<MusicCal> musicCalList) {
        this.context = context;
        this.musicCalList = musicCalList;
        this.size = musicCalList.size();
    }

    @NonNull
    @Override
    public MusicCalViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MusicCalViewHolder(LayoutInflater.from(context).inflate(R.layout.item_musiccal,null));
    }

    @Override
    public void onBindViewHolder(@NonNull MusicCalViewHolder holder, int position) {
        current = position;
        musicCal = musicCalList.get(current);
        musicCalNext = musicCalList.get((current+1)%size);
        this.image = holder.image;
        this.nextImage = holder.nextImage;
        this.time = holder.time;
        this.title = holder.title;
        MergeImage.showGlideImgDb(context,R.drawable.zhoushen,holder.image,10);
        MergeImage.showGlideImgDb(context,R.drawable.zhoushen1,holder.nextImage,10);
        holder.title.setText(musicCal.getTitle());
        holder.time.setText(musicCal.getTime());
        holder.musicCalBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, MusicCalActivity.class);
                context.startActivity(intent);
            }
        });
        /*timer = new Timer();
        timerTask = new TimerTask() {
            @Override
            public void run() {
                handler.sendEmptyMessage(1);
            }
        };
        timer.schedule(timerTask,3,10000);*/

    }

    Handler handler = new Handler() {
        @Override
        public void handleMessage(@NonNull Message msg) {
            if(msg.what==1) {
                current = (current+1)%size;
                musicCal = musicCalNext;
                musicCalNext = musicCalList.get(current);
                image.setImageBitmap(MergeImage.roundedCustomDB(HttpUtils.getRes(musicCal.getImage(),context),60,60,10));
                title.setText(musicCal.getTitle());
                time.setText(musicCal.getTime());
                Log.i("title",musicCal.getTitle());
                Log.i("current",current+"="+size);
                nextImage.setImageBitmap(MergeImage.roundedCustomDB(HttpUtils.getRes(musicCalNext.getImage(),context),50,50,10));
            }
        }
    };

    @Override
    public int getItemCount() {
        return 1;
    }

    class MusicCalViewHolder extends RecyclerView.ViewHolder{

        LinearLayout musicCalBox;
        ImageView nextImage,image;
        TextView time,title;
        int itemWidth = (int) ((WindowPixels.WIDTH-30)*WindowPixels.DENSITY);

        public MusicCalViewHolder(@NonNull View itemView) {
            super(itemView);
            nextImage = itemView.findViewById(R.id.next_image);
            image = itemView.findViewById(R.id.image);
            time = itemView.findViewById(R.id.time);
            title = itemView.findViewById(R.id.title);
            musicCalBox = itemView.findViewById(R.id.music_cal_box);
            musicCalBox.setMinimumWidth(itemWidth);
        }
    }

    public boolean isScrolling() {
        return isScrolling;
    }

    public void setScrolling(boolean scrolling) {
        isScrolling = scrolling;
    }
}
