package com.gxuwz.beethoven.model.entity.find.musiccal;

import com.gxuwz.beethoven.model.entity.Song;

public class MusicCalSong {

    private Song song;
    private String title;

    public MusicCalSong() {
    }

    public Song getSong() {
        return song;
    }

    public void setSong(Song song) {
        this.song = song;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
