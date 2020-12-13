package com.gxuwz.beethoven.model.entity.current;

import java.sql.Timestamp;

public class Operate {

    //从加入歌单
    public final static String SONG_ADD_SL = "song:add_sl";
    //从加入歌单
    public final static String SONG_COL = "song:col";
    //从歌单中移除歌曲歌曲
    public final static String SONG_DELETE = "song:del";
    //下载歌曲
    public final static String SONG_DOWNLOAD = "song:download";
    //设置铃声
    public final static String SONG_SET_RING = "song:set_ring";
    //取消收藏
    public final static String COELLECTIONES_CANCEL = "collectiones:cancel";

    private Long id;
    private String oId;
    private String oName;
    private String icon;
    private Integer type;
    private String path;
    private Timestamp updateDate;

    public Operate() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getoId() {
        return oId;
    }

    public void setoId(String oId) {
        this.oId = oId;
    }

    public String getoName() {
        return oName;
    }

    public void setoName(String oName) {
        this.oName = oName;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Timestamp getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Timestamp updateDate) {
        this.updateDate = updateDate;
    }
}
