package com.gxuwz.beethoven.model.entity;

import java.io.Serializable;

/**
 * 文件下载信息
 */
public class FileInfo implements Serializable {
    private Integer id;//文件id
    private String url;//文件的下载url
    private String fileName;//文件名
    private int length;//文件长度
    private int finished;//文件下载完成度

    public FileInfo() {
    }

    public FileInfo(Integer id, String url, String fileName, int length, int finished) {
        this.id = id;
        this.url = url;
        this.fileName = fileName;
        this.length = length;
        this.finished = finished;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public int getFinished() {
        return finished;
    }

    public void setFinished(int finished) {
        this.finished = finished;
    }
}
