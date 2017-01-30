package com.hxp.leschool.model.operate;

import android.util.Log;

import com.hxp.leschool.model.bean.UploadTaskModel;
import com.hxp.leschool.model.bean.UploadingModel;
import com.hxp.leschool.model.bean.UploadTaskModel.UploadUpdateCallback;
import com.hxp.leschool.utils.UploadingPublish;
import com.hxp.leschool.view.fragment.UploadingFragment;
import com.hxp.leschool.viewmodel.UploadingViewModel;

import java.util.ArrayList;


/**
 * Created by hxp on 17-1-15.
 */


public class UploadingModelOpt implements UploadUpdateCallback {

    public UploadingModel mUploadingModel;
    public ArrayList<UploadingModel> mData = new ArrayList<>();
    private UploadingOptCallback mUploadingOptCallback;
    private UploadingFragment mUploadingFragment;

    public UploadingModelOpt(UploadingViewModel uploadingViewModel, UploadingFragment uploadingFragment) {
        mUploadingOptCallback = uploadingViewModel;
        mUploadingFragment = uploadingFragment;
    }

    //获取数据
    public void getData() {
        mData.clear();
        UploadTaskModel downloadTaskModel;
        for (int i = 0; i < UploadingPublish.getUploadTaskCount(); i++) {
            downloadTaskModel = UploadingPublish.getUploadTask().get(i);
            mUploadingModel = new UploadingModel();
            mUploadingModel.setTitle(downloadTaskModel.getTitle());
            mUploadingModel.setPicture(downloadTaskModel.getPicture());
            mUploadingModel.setUploadstate(downloadTaskModel.getUploadState());
            mUploadingModel.setUploadProcess(downloadTaskModel.getUploadProcess());
            mData.add(mUploadingModel);
        }
        Log.d("fragment", "DownloadModelOpt数据获取成功回调发送方");
        UploadTaskModel.setUploadUpdateCallback(this);
        mUploadingOptCallback.uploadingGetdataSucceedCompleted();
    }

    //刷新数据
    public void refreshData(int position, int downloadProcess) {
        if (position >= mData.size()) {
            mData.clear();
            UploadTaskModel uploadTaskModel;
            for (int i = 0; i < UploadingPublish.getUploadTaskCount(); i++) {
                uploadTaskModel = UploadingPublish.getUploadTask().get(i);
                mUploadingModel = new UploadingModel();
                mUploadingModel.setTitle(uploadTaskModel.getTitle());
                mUploadingModel.setPicture(uploadTaskModel.getPicture());
                mUploadingModel.setUploadstate(uploadTaskModel.getUploadState());
                mUploadingModel.setUploadProcess(uploadTaskModel.getUploadProcess());
                mData.add(mUploadingModel);
            }
            Log.d("fragment", "DownloadModelOpt进度新增");
            mUploadingOptCallback.uploadingRefreshdataSucceedCompleted();
        } else {
            mData.get(position).setUploadProcess(downloadProcess);
            Log.d("fragment", "DownloadModelOpt下载进度刷新成功回调发送方" + position);
            mUploadingOptCallback.uploadingRefreshdataSucceedCompleted();
        }
        Log.d("fragment", "DownloadModelOpt数据刷新成功回调发送方");
        mUploadingOptCallback.uploadingRefreshdataSucceedCompleted();
    }

    //刷新数据-状态
    public void refreshData(int position, boolean downloadState) {
        mData.get(position).setUploadstate(downloadState);
        Log.d("fragment", "DownloadModelOpt下载状态刷新成功回调发送方" + position);
        mUploadingOptCallback.uploadingRefreshStateSucceedCompleted();
    }

    //获取数据数量
    public int getCount() {
        return mData.size();
    }

    //获取数据成功回调
    //获取数据失败回调
    public interface UploadingOptCallback {
        void uploadingGetdataSucceedCompleted();

        void uploadingGetdataFailedCompleted();

        void uploadingRefreshdataSucceedCompleted();

        void uploadingRefreshdataFailedCompleted();

        void uploadingRefreshStateSucceedCompleted();

        void uploadingRefreshStateFailedCompleted();
    }

    @Override
    public void updateUploadProcess(int position, int downloadProcess) {
        Log.d("fragment", "UploadingModelOpt执行上传进度回调");
        refreshData(position, downloadProcess);
    }

    @Override
    public void updateUploadState(int position, boolean downloadState) {
        Log.d("fragment", "UploadingModelOpt执行上传已完成回调");
        refreshData(position, downloadState);
    }
}
