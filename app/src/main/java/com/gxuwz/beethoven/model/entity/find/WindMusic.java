package com.gxuwz.beethoven.model.entity.find;

import com.gxuwz.beethoven.model.entity.Music;

public class WindMusic {
    private Music music;
    private String status;

    public WindMusic() {
    }

    public Music getMusic() {
        return music;
    }

    public void setMusic(Music music) {
        this.music = music;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
