package com.gxuwz.beethoven.model.entity.find;

import com.gxuwz.beethoven.model.entity.Song;

public class SpeSong {
    private String image;
    private String name;
    private String title;
    private Song song;

    public SpeSong() {
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Song getSong() {
        return song;
    }

    public void setSong(Song song) {
        this.song = song;
    }
}
