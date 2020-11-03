package com.gxuwz.beethoven.service;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;

import com.gxuwz.beethoven.dao.ThreadDAO;
import com.gxuwz.beethoven.dao.ThreadDAOImpl;
import com.gxuwz.beethoven.model.entity.FileInfo;
import com.gxuwz.beethoven.model.entity.ThreadInfo;
import com.gxuwz.beethoven.util.MergeImage;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 下载任务类
 */

public class DownloadTask {

    private Context mContext;
    private FileInfo mFileInfo;
    private ThreadDAO mDao;
    private int mFinished;
    public boolean isPause = false;
    private int mThreadCount = 1;//单个任务下载线程数
    private List<DownloadThread> mThreadList;
    public static ExecutorService sExecutorService = Executors.newCachedThreadPool();
    private Messenger mMessenger = null;

    private Timer mTimer = new Timer();//定时器
    private Handler handler;

    public DownloadTask(Context mContext, FileInfo mFileInfo, Messenger mMessenger) {
        this.mContext = mContext;
        this.mFileInfo = mFileInfo;
        this.mMessenger = mMessenger;
        mFinished = 0;
        mDao = new ThreadDAOImpl(mContext);
    }

    public DownloadTask(Context mContext, FileInfo mFileInfo, Handler handler) {
        this.mContext = mContext;
        this.mFileInfo = mFileInfo;
        this.handler = handler;
        mFinished = 0;
        mDao = new ThreadDAOImpl(mContext);
    }

    public DownloadTask(Context mContext, FileInfo mFileInfo, Messenger mMessenger, int mThreadCount) {
        this.mContext = mContext;
        this.mFileInfo = mFileInfo;
        this.mMessenger = mMessenger;
        this.mThreadCount = mThreadCount;
        mFinished = 0;
        mDao = new ThreadDAOImpl(mContext);
    }

    public void download() {
        //读取数据库中的线程信息
        List<ThreadInfo> threads = mDao.getThreads(mFileInfo.getUrl());
        ThreadInfo threadInfo = null;
        if (threads.size() == 0) {
            //初始化线程信息对象
            int len = mFileInfo.getLength() / mThreadCount;
            for (int i = 0; i < mThreadCount; i++) {
                threadInfo = new ThreadInfo(i,mFileInfo.getFileName(), mFileInfo.getUrl(), len * i, len * (i + 1) - 1, 0);
                if (i == mThreadCount - 1) {
                    threadInfo.setEnds(mFileInfo.getLength());
                }
                threads.add(threadInfo);
                mDao.insertThread(threadInfo);
            }
        } else {
            for (ThreadInfo thread : threads) {
                mFinished += thread.getFinished();
            }
        }

        //启动定时任务
        mTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                Message msg = new Message();
                msg.what = DownloadService.MSG_UPDATE;
                msg.arg1 = mFinished * 100 / mFileInfo.getLength();
                msg.arg2 = mFileInfo.getId()!=null?mFileInfo.getId():1;
                try {
                    if(mMessenger!=null) mMessenger.send(msg);
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
        }, 0, 300);

        //创建多个子线程进行下载
        mThreadList = new ArrayList<>();
        for (ThreadInfo info : threads) {
            DownloadThread thread = new DownloadThread(info);
            DownloadTask.sExecutorService.execute(thread);
            //添加线程到集合中
            mThreadList.add(thread);
        }
    }

    /**
     * 判断是否所有线程都执行完毕
     */
    private synchronized void checkAllThreadsFinished() {
        boolean allFinished = true;
        for (DownloadThread thread : mThreadList) {
            if (!thread.isFinished) {
                allFinished = false;
                break;
            }
        }
        if (allFinished) {
            mTimer.cancel();
            //删除线程信息
            mDao.deleteThread(mFileInfo.getUrl());
            //通知UI下载结束
            Message msg = new Message();
            msg.what = DownloadService.MSG_FINISH;
            msg.obj = mFileInfo;
            if(handler!=null) {
                Message lyrMess = new Message();
                lyrMess.what = 1;
                handler.sendMessage(lyrMess);
            }
            try {
                if(mMessenger!=null)
                mMessenger.send(msg);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 下载线程
     */
    class DownloadThread extends Thread {
        private ThreadInfo mThreadInfo = null;
        private boolean isFinished = false;

        public DownloadThread(ThreadInfo mThreadInfo) {
            this.mThreadInfo = mThreadInfo;
        }

        @Override
        public void run() {
            //设置下载位置
            HttpURLConnection conn = null;
            RandomAccessFile raf = null;
            InputStream input = null;
            try {
                URL url = new URL(mThreadInfo.getUrl());
                conn = (HttpURLConnection) url.openConnection();
                conn.setConnectTimeout(3000);
                conn.setRequestMethod("GET");
                //设置下载位置
                int start = mThreadInfo.getStart() + mThreadInfo.getFinished();
                conn.setRequestProperty("Range", "bytes=" + start + "-" + mThreadInfo.getEnds());
                //设置文件写入位置
                String path = mFileInfo.getUrl();
                path = path.split("/static/")[1];
                String[] packages = path.split("/");
                String pathName = DownloadService.DOWNLOAD_PATH;
                if(packages!=null&&packages.length!=0) {
                    File file = new File(pathName += packages[0]);
                    if(!file.exists()) {
                        file.mkdir();
                    }
                }
                for(int i = 1;i < packages.length-1;i++) {
                    File file = new File(pathName+="/"+packages[i]);
                    if(!file.exists()) {
                        file.mkdir();
                    }
                }
                File file = null;
                if(mThreadInfo.getDownType()!=0) {
                    String fileType = path.substring(path.lastIndexOf("."));
                    file = new File(pathName+"/"+mThreadInfo.getFileName()+fileType);
                } else {
                    file = new File(DownloadService.DOWNLOAD_PATH+path);
                }
                raf = new RandomAccessFile(file, "rwd");
                raf.seek(start);
                int code = conn.getResponseCode();
                //开始下载
                if (code == HttpURLConnection.HTTP_PARTIAL) {
                    //读取数据
                    input = conn.getInputStream();
                    byte[] buffer = new byte[1024 * 4];
                    int len = -1;
                    while ((len = input.read(buffer)) != -1) {
                        //写入文件
                        raf.write(buffer, 0, len);
                        //把下载进度发送广播给Activity
                        //整个文件的下载进度
                        mFinished += len;
                        //每个线程的下载进度
                        mThreadInfo.setFinished(mThreadInfo.getFinished() + len);
                        //在暂停时保存下载进度
                        if (isPause) {
                            mTimer.cancel();
                            mDao.updateThread(mFileInfo.getUrl(), mThreadInfo.getId(), mThreadInfo.getFinished());
                            return;
                        }
                    }
                    isFinished = true;
                    checkAllThreadsFinished();
                }
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if(conn!=null) conn.disconnect();
                try {
                    if (raf != null)
                        raf.close();
                    if (input != null)
                        input.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
