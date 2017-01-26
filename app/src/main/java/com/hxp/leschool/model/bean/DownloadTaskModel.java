package com.hxp.leschool.model.bean;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import com.hxp.leschool.BR;

/**
 * Created by hxp on 17-1-24.
 */

public class DownloadTaskModel {

    private String title;
    private int picture;
    private int downloadProcess = 0;
    private boolean isDownloadCompleted = false;

    public DownloadTaskModel() {
    }

    public DownloadTaskModel(String title, int picture, int downloadProcess, boolean isDownloadCompleted) {
        this.title = title;
        this.picture = picture;
        this.downloadProcess = downloadProcess;
        this.isDownloadCompleted = isDownloadCompleted;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getPicture() {
        return picture;
    }

    public void setPicture(int picture) {
        this.picture = picture;
    }

    public int getDownloadProcess() {
        return downloadProcess;
    }

    public void setDownloadProcess(int downloadProcess) {
        this.downloadProcess = downloadProcess;
    }

    public boolean getIsDownloadCompleted() {
        return isDownloadCompleted;
    }

    public void setIsDownloadCompleted(boolean isDownloadCompleted) {
        this.isDownloadCompleted = isDownloadCompleted;
    }
}
