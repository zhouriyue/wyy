package com.gxuwz.beethoven.adapter.my;

import android.content.Context;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.gxuwz.beethoven.R;
import com.gxuwz.beethoven.model.entity.FileInfo;
import com.gxuwz.beethoven.service.DownloadService;

import java.util.List;

public class FileListAdapter extends RecyclerView.Adapter<FileListAdapter.FLViewHolder> {

    private Context mContext;
    private List<FileInfo> mFileList;
    private Messenger mMessenger = null;

    public FileListAdapter(Context mContext, List<FileInfo> mFileList) {
        this.mContext = mContext;
        this.mFileList = mFileList;
    }

    public void setmMessenger(Messenger mMessenger) {
        this.mMessenger = mMessenger;
        for(int i = 0;i < mFileList.size();i++) {
            Message msg = new Message();
            msg.what = DownloadService.MSG_START;
            msg.obj = mFileList.get(i);
            try {
                mMessenger.send(msg);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

    @NonNull
    @Override
    public FLViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new FLViewHolder(LayoutInflater.from(mContext).inflate(R.layout.listitem, null));
    }

    @Override
    public void onBindViewHolder(@NonNull FLViewHolder holder, int position) {
        FileInfo fileInfo = mFileList.get(position);
        //设置控件
        holder.tvFile.setText(fileInfo.getFileName());
        holder.pbFile.setMax(100);
        Message msg = new Message();
        msg.what = DownloadService.MSG_START;
        msg.obj = fileInfo;
        holder.btStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Message msg = new Message();
                msg.what = DownloadService.MSG_START;
                msg.obj = fileInfo;
                try {
                    mMessenger.send(msg);
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
        });
        holder.btStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Message msg = new Message();
                msg.what = DownloadService.MSG_STOP;
                msg.obj = fileInfo;
                try {
                    mMessenger.send(msg);
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
        });
        holder.pbFile.setProgress(fileInfo.getFinished());
    }


    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public int getItemCount() {
        return mFileList.size();
    }

    public void updateProgress(int id, int progress) {
        FileInfo fileInfo = mFileList.get(id);
        fileInfo.setFinished(progress);
        notifyDataSetChanged();
    }

    class FLViewHolder extends RecyclerView.ViewHolder {
        TextView tvFile;
        Button btStart, btStop;
        ProgressBar pbFile;

        public FLViewHolder(@NonNull View itemView) {
            super(itemView);
            tvFile = itemView.findViewById(R.id.tvFileName);
            btStart = itemView.findViewById(R.id.btStart);
            btStop = itemView.findViewById(R.id.btStop);
            pbFile = itemView.findViewById(R.id.pbProgress);
        }
    }
}
