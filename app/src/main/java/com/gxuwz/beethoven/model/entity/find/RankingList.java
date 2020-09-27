package com.gxuwz.beethoven.model.entity.find;


import com.gxuwz.beethoven.model.entity.Song;

import java.util.List;

/**
 * 排行榜
 */
public class RankingList {
    
    private String name;
    private String img;
    private String updateType;
    private List<Song> songList;

    public RankingList() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Song> getSongList() {
        return songList;
    }

    public void setSongList(List<Song> songList) {
        this.songList = songList;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getUpdateType() {
        return updateType;
    }

    public void setUpdateType(String updateType) {
        this.updateType = updateType;
    }
}
