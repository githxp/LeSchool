package com.hxp.leschool.utils.event;

/**
 * 开始下载事件
 * Created by hxp on 17-2-3.
 */

public class DownloadEvent {

    private String title;//下载文件的标题
    private int avatar;//下载文件的图标
    private int process;//下载进度
    private int num;//下载序号
    private boolean status;//是否下载完成

    public DownloadEvent() {
    }

    public DownloadEvent(String title, int avatar, int process, int num, boolean status) {
        this.title = title;
        this.avatar = avatar;
        this.process = process;
        this.num = num;
        this.status = status;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getAvatar() {
        return avatar;
    }

    public void setAvatar(int avatar) {
        this.avatar = avatar;
    }

    public int getProcess() {
        return process;
    }

    public void setProcess(int process) {
        this.process = process;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
