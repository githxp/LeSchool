package com.hxp.leschool.model.bean;

import android.util.Log;
/**
 * Created by hxp on 17-1-24.
 */

public class DownloadTaskModel {

    private String mTitle;
    private int mPicture;
    private int mDownloadProcess;
    private boolean mDownloadState;
    private static DownloadUpdateCallback mDownloadUpdateCallback;

    public DownloadTaskModel() {
    }

    public DownloadTaskModel(String title, int picture, int downloadProcess, boolean downloadState) {
        mTitle = title;
        mPicture = picture;
        mDownloadProcess = downloadProcess;
        mDownloadState = downloadState;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public int getPicture() {
        return mPicture;
    }

    public void setPicture(int picture) {
        mPicture = picture;
    }

    public int getDownloadProcess() {
        return mDownloadProcess;
    }

    public void setDownloadProcess(int position,int downloadProcess) {
        mDownloadProcess = downloadProcess;
        //更新订阅者状态
        if (mDownloadUpdateCallback != null) {
            Log.d("fragment", "DownloadTaskModel发送下载进度回调");
            mDownloadUpdateCallback.updateDownloadProcess(position,downloadProcess);
        }
    }

    public boolean getDownloadState() {
        return mDownloadState;
    }

    public void setDownloadState(int position,boolean downloadState) {
        mDownloadState = downloadState;
        //更新订阅者状态
        if (mDownloadUpdateCallback != null) {
            Log.d("fragment", "DownloadTaskModel发送下载已完成回调");
            mDownloadUpdateCallback.updateDownloadState(position,downloadState);
        }
    }

    public interface DownloadUpdateCallback {
        void updateDownloadProcess(int position,int downloadProcess);

        void updateDownloadState(int position,boolean downloadState);
    }

    public static void setDownloadUpdateCallback(DownloadUpdateCallback downloadUpdateCallback) {
        mDownloadUpdateCallback = downloadUpdateCallback;
    }
}
