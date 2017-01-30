package com.hxp.leschool.model.bean;

import android.util.Log;

/**
 * Created by hxp on 17-1-24.
 */

public class UploadTaskModel {

    private String mTitle;
    private int mPicture;
    private int mUploadProcess;
    private boolean mUploadState;
    private static UploadUpdateCallback mUploadUpdateCallback;

    public UploadTaskModel() {
    }

    public UploadTaskModel(String title, int picture, int uploadProcess, boolean uploadState) {
        mTitle = title;
        mPicture = picture;
        mUploadProcess = uploadProcess;
        mUploadState = uploadState;
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

    public int getUploadProcess() {
        return mUploadProcess;
    }

    public void setUploadProcess(int position, int uploadProcess) {
        mUploadProcess = uploadProcess;
        //更新订阅者状态
        if (mUploadUpdateCallback != null) {
            Log.d("fragment", "DownloadTaskModel发送下载进度回调");
            mUploadUpdateCallback.updateUploadProcess(position, uploadProcess);
        }
    }

    public boolean getUploadState() {
        return mUploadState;
    }

    public void setUploadState(int position, boolean uploadState) {
        mUploadState = uploadState;
        //更新订阅者状态
        if (mUploadUpdateCallback != null) {
            Log.d("fragment", "DownloadTaskModel发送下载已完成回调");
            mUploadUpdateCallback.updateUploadState(position, uploadState);
        }
    }

    public interface UploadUpdateCallback {
        void updateUploadProcess(int position, int downloadProcess);

        void updateUploadState(int position, boolean downloadState);
    }

    public static void setUploadUpdateCallback(UploadUpdateCallback uploadUpdateCallback) {
        mUploadUpdateCallback = uploadUpdateCallback;
    }
}
