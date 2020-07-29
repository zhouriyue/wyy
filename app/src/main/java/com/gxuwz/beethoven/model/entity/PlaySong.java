package com.gxuwz.beethoven.model.entity;

import java.util.Date;

public class PlaySong {
    private SongListsMusic songListsMusic;
    private Date playDate;

    public PlaySong() {
    }

    public SongListsMusic getSongListsMusic() {
        return songListsMusic;
    }

    public void setSongListsMusic(SongListsMusic songListsMusic) {
        this.songListsMusic = songListsMusic;
    }

    public Date getPlayDate() {
        return playDate;
    }

    public void setPlayDate(Date playDate) {
        this.playDate = playDate;
    }
}
