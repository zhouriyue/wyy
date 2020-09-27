package com.gxuwz.beethoven.model.entity.find.musiccal;

import com.gxuwz.beethoven.model.entity.Song;
import com.gxuwz.beethoven.model.entity.SysUser;

import java.util.List;

public class MusicCal {

    private List<MusicCalSong> musicCalSongList;
    private String time;
    private String title;

    public MusicCal() {
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<MusicCalSong> getMusicCalSongList() {
        return musicCalSongList;
    }

    public void setMusicCalSongList(List<MusicCalSong> musicCalSongList) {
        this.musicCalSongList = musicCalSongList;
    }
}
