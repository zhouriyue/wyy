package com.gxuwz.beethoven.model.entity.current;

public class Operate {

    //从加入歌单
    public final static String SONG_ADD_SL = "song:add_sl";
    //从加入歌单
    public final static String SONG_COL = "song:col";
    //从歌单中移除歌曲歌曲
    public final static String SONG_DELETE = "song:del";
    //下载歌曲
    public final static String SONG_DOWNLOAD = "song:download";
    //下载歌曲
    public final static String SONG_SET_RING = "song:set_ring";

    private Long id;
    private String oId;
    private String oName;
    private String icon;
    private Integer type;
    private String path;

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
}
