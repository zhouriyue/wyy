package com.gxuwz.beethoven.hanlder;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import com.gxuwz.beethoven.model.entity.PlayList;
import com.gxuwz.beethoven.model.entity.SongListsMusic;
import com.gxuwz.beethoven.receiver.IndexBottomBarReceiver;
import com.gxuwz.beethoven.util.Player;

import org.json.JSONException;
import org.json.JSONObject;

public class MusicHandler extends Handler {

    int back;
    Player mPlayer;
    Context context;
    PlayList playList;
    int position;
    static boolean isPlay = false;

    SongListsMusic songListsMusic;
    SharedPreferences sharedPreferences;
    String songListUrl;

    public MusicHandler(SongListsMusic songListsMusic, SharedPreferences sharedPreferences,String songListUrl) {
        this.songListsMusic = songListsMusic;
        this.sharedPreferences = sharedPreferences;
        this.songListUrl = songListUrl;
    }

    public void handleMessage(Message msg) {
        super.handleMessage(msg);
        //接收到message之后的处理过程
        if(msg.what ==1 ){
            //获取子线程回传的数据
            Bundle bundle = msg.getData();
            String result = bundle.getString("result");
            //解析json
            try {
                JSONObject all_json = new JSONObject(result);
                String songUrl = all_json.optString("songUrl");
                addPlayList(songUrl);
                addSongLMusic();
                saveSharedPreferences(playList);
                IndexBottomBarReceiver.sendBroadcast(IndexBottomBarReceiver.FLAT_PLAY,context);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    public void addSongLMusic(){
        //System.out.println(songListsMusic.toString());
    }

    private void addPlayList(String songUrl){
        /*PlayListDao playListDao = new PlayListDao(context);
        playList = new PlayList();
        playList.setSongName(songListsMusic.getMusicName());
        playList.setSingerName(songListsMusic.getSingerName());
        playList.setNetworkUri(songUrl);
        playList.setSongTime(songListsMusic.getSongTime());
        String localUrl = HttpUtils.downFile(context,HttpUtils.BASEURL+songUrl,songUrl.substring(songUrl.lastIndexOf("/")));
        playList.setLocalUri(localUrl);
        int size = playListDao.find(playList).size();
        if(size==0) {
            playListDao.insert(playList);
        }
        playList = playListDao.find(playList).get(0);
        playList.setSongListUri(songListUrl);
        if(size==0) {
            freshPlayList(playList);
        }*/
    }

    private void saveSharedPreferences(PlayList playList){
        /**
         * 设置播放歌曲的歌名、歌手、歌曲
         */
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("id",playList.getId());
        editor.putString("songName",playList.getSongName());
        editor.putString("singerName",playList.getSingerName());
        editor.putInt("songTime",playList.getSongTime());
        editor.putString("localUri",playList.getLocalUri());
        editor.putString("networkUri",playList.getNetworkUri());
        editor.putString("songListUri",playList.getSongListUri());
        /**
         * playStatus==1表示播放
         * playStatus==0表示停止
         */
        editor.putString("playStatus","1");
        editor.commit();
    }

    /*private void freshPlayList(PlayList playList) {
        Intent intent = new Intent(CurrentPlayView.FreshPlayListReceiver.ACTION);
        intent.putExtra("playList",playList);
        context.sendBroadcast(intent);
    }*/

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public static boolean isIsPlay() {
        return isPlay;
    }

    public static void setIsPlay(boolean isPlay) {
        MusicHandler.isPlay = isPlay;
    }

    public int getBack() {
        return back;
    }

    public void setBack(int back) {
        this.back = back;
    }
}
