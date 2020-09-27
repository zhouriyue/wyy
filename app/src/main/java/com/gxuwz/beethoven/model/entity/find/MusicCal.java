package com.gxuwz.beethoven.model.entity.find;

/**
 * 音乐日历
 * 实体
 */
public class MusicCal {
    private String time;
    private String image;
    private String title;

    public MusicCal() {
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
