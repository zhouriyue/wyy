package com.gxuwz.beethoven.page.fragment.my.downmanage.download;

import android.Manifest;
import android.app.Service;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.gxuwz.beethoven.R;
import com.gxuwz.beethoven.adapter.my.FileListAdapter;
import com.gxuwz.beethoven.dao.LocalSongDao;
import com.gxuwz.beethoven.dao.ThreadDAO;
import com.gxuwz.beethoven.dao.ThreadDAOImpl;
import com.gxuwz.beethoven.model.entity.FileInfo;
import com.gxuwz.beethoven.model.entity.current.LocalSong;
import com.gxuwz.beethoven.service.DownloadService;

import java.util.ArrayList;
import java.util.List;

public class FragmentDownLoad extends Fragment {

    private RecyclerView mLvFile;
    private List<FileInfo> mFileList;
    private FileListAdapter mAdapter;
    private Messenger mServiceMessenger = null;
    ThreadDAO threadDAO;
    LocalSongDao localSongDao;

    public FragmentDownLoad() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.download_downloading,null);
        init(view);
        return view;
    }

    public void init(View view){
        initView(view);
        initEvent();
    }

    private void initView(View view) {
        mLvFile = view.findViewById(R.id.lvFile);
    }


    private void initEvent() {
        if(threadDAO==null) {
            threadDAO = new ThreadDAOImpl(getContext());
        }
        mFileList = threadDAO.findAllFileInfo();
        /*mFileList = new ArrayList<>();
        FileInfo fileInfo0 = new FileInfo(0, "https://www.zhou.website/wyy-0.0.1-SNAPSHOT/static/music/shuohaobugu-zhoujielun.mp3", "一眼一生", 12913518, 0);//l
        FileInfo fileInfo1 = new FileInfo(1, "https://www.zhou.website/wyy-0.0.1-SNAPSHOT/static/music/linjunjie-tashuo.mp3", "不负时代", 12913518, 0);
        FileInfo fileInfo2 = new FileInfo(2, "https://www.zhou.website/wyy-0.0.1-SNAPSHOT/static/music/linjunjie-tashuo.mp3", "万王归来", 12913518, 0);
        mFileList.add(fileInfo0);
        mFileList.add(fileInfo1);
        mFileList.add(fileInfo2);*/

        mLvFile.setLayoutManager(new StaggeredGridLayoutManager(1,StaggeredGridLayoutManager.VERTICAL));
        mAdapter = new FileListAdapter(getActivity(), mFileList);
        mLvFile.setAdapter(mAdapter);

        //绑定Service
        Intent intent = new Intent(getActivity(), DownloadService.class);
        getActivity().bindService(intent, mConnection, getActivity().BIND_AUTO_CREATE);
    }

    ServiceConnection mConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            //获得Service中的Messenger
            mServiceMessenger = new Messenger(iBinder);
            //设置适配器中的Messenger
            mAdapter.setmMessenger(mServiceMessenger);
            //创建Activity中的Messenger
            Messenger messenger = new Messenger(mHandler);
            //创建消息
            Message msg = new Message();
            msg.what = DownloadService.MSG_BIND;
            msg.replyTo = messenger;
            //使用Service的Messenger发送Activity中的Messenger
            try {
                mServiceMessenger.send(msg);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {

        }
    };

    Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case DownloadService.MSG_UPDATE:
                    int finished = msg.arg1;
                    int id = msg.arg2;
                    mAdapter.updateProgress(id, finished);
                    break;
                case DownloadService.MSG_FINISH:
                    if(localSongDao==null) {
                        localSongDao = new LocalSongDao(getContext());
                    }
                    FileInfo fileInfo = (FileInfo) msg.obj;
                    String fileUrl[] = fileInfo.getUrl().split("/static");
                    String fileType = fileUrl[1].substring(fileUrl[1].lastIndexOf("."));
                    String fileName = fileInfo.getFileName()+fileType;
                    LocalSong localSong = new LocalSong();
                    localSong.setSongName(fileInfo.getFileName());
                    localSong.setSongUrl(fileInfo.getUrl());
                    localSongDao.insert(localSong);
                    //更新进度为0
                    mAdapter.updateProgress(fileInfo.getId(), fileInfo.getLength());
                    Toast.makeText(getActivity(), mFileList.get(fileInfo.getId()).getFileName() + "下载完毕", Toast.LENGTH_SHORT).show();
                    break;
            }
        }
    };

    @Override
    public void onDestroy() {
        super.onDestroy();
        Message msg = new Message();
        msg.what = DownloadService.ALL_STOP;
        try {
            mServiceMessenger.send(msg);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        Intent intent = new Intent(getActivity(), DownloadService.class);
        getActivity().unbindService(mConnection);
    }
}
