package com.hxp.leschool.utils.event;

/**
 * 上传消息
 * Created by hxp on 17-2-3.
 */

public class UploadEvent {

    private String title;//上传文件的标题
    private int avatar;//上传文件的图标
    private int process;//上传进度

    public UploadEvent() {
    }

    public UploadEvent(String title, int avatar, int process) {
        this.title = title;
        this.avatar = avatar;
        this.process = process;
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
}
