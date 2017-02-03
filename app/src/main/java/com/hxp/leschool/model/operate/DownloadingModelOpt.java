package com.hxp.leschool.model.operate;

import android.util.Log;

import com.hxp.leschool.model.bean.DownloadTaskModel;
import com.hxp.leschool.model.bean.DownloadTaskModel.DownloadUpdateCallback;
import com.hxp.leschool.model.bean.DownloadingModel;
import com.hxp.leschool.utils.event.DownloadEvent;
import com.hxp.leschool.utils.publish.DownloadingPublish;
import com.hxp.leschool.view.fragment.DownloadingFragment;
import com.hxp.leschool.viewmodel.DownloadingViewModel;

import java.util.ArrayList;


/**
 * Created by hxp on 17-1-15.
 */


public class DownloadingModelOpt implements DownloadUpdateCallback {

    public DownloadingModel mDownloadingModel;
    public ArrayList<DownloadingModel> mData = new ArrayList<>();
    private DownloadingOptCallback mDownloadingOptCallback;
    private DownloadingFragment mDownloadingFragment;

    public DownloadingModelOpt(DownloadingViewModel downloadingViewModel, DownloadingFragment downloadingFragment) {
        mDownloadingOptCallback = downloadingViewModel;
        mDownloadingFragment = downloadingFragment;
    }

    //获取数据
    public void getData() {
        mData.clear();
        DownloadTaskModel downloadTaskModel;
        for (int i = 0; i < DownloadingPublish.getDownloadTaskCount(); i++) {
            downloadTaskModel = DownloadingPublish.getDownloadTask().get(i);
            mDownloadingModel = new DownloadingModel();
            mDownloadingModel.setTitle(downloadTaskModel.getTitle());
            mDownloadingModel.setPicture(downloadTaskModel.getPicture());
            mDownloadingModel.setDownloadstate(downloadTaskModel.getDownloadState());
            mDownloadingModel.setDownloadProcess(downloadTaskModel.getDownloadProcess());
            mData.add(mDownloadingModel);
        }
        Log.d("fragment", "DownloadModelOpt数据获取成功回调发送方");
        DownloadTaskModel.setDownloadUpdateCallback(this);
        mDownloadingOptCallback.downloadingGetdataSucceedCompleted();
    }

    //刷新数据-进度
    public void refreshData(int position, int downloadProcess) {
        if (position >= mData.size()) {
            mData.clear();
            DownloadTaskModel downloadTaskModel;
            for (int i = 0; i < DownloadingPublish.getDownloadTaskCount(); i++) {
                downloadTaskModel = DownloadingPublish.getDownloadTask().get(i);
                mDownloadingModel = new DownloadingModel();
                mDownloadingModel.setTitle(downloadTaskModel.getTitle());
                mDownloadingModel.setPicture(downloadTaskModel.getPicture());
                mDownloadingModel.setDownloadstate(downloadTaskModel.getDownloadState());
                mDownloadingModel.setDownloadProcess(downloadTaskModel.getDownloadProcess());
                mData.add(mDownloadingModel);
            }
            Log.d("fragment", "DownloadModelOpt进度新增");
            mDownloadingOptCallback.downloadingRefreshdataSucceedCompleted();
        } else {
            mData.get(position).setDownloadProcess(downloadProcess);
            Log.d("fragment", "DownloadModelOpt下载进度刷新成功回调发送方" + position);
            mDownloadingOptCallback.downloadingRefreshdataSucceedCompleted();
        }
    }

    //刷新数据-状态
    public void refreshData(int position, boolean downloadState) {
        mData.get(position).setDownloadstate(downloadState);
        Log.d("fragment", "DownloadModelOpt下载状态刷新成功回调发送方" + position);
        mDownloadingOptCallback.downloadingRefreshStateSucceedCompleted();
    }


    //获取数据数量
    public int getCount() {
        return mData.size();
    }

    //获取数据成功回调
    //获取数据失败回调
    public interface DownloadingOptCallback {
        void downloadingGetdataSucceedCompleted();

        void downloadingGetdataFailedCompleted();

        void downloadingRefreshdataSucceedCompleted();

        void downloadingRefreshdataFailedCompleted();

        void downloadingRefreshStateSucceedCompleted();

        void downloadingRefreshStateFailedCompleted();
    }

    @Override
    public void updateDownloadProcess(int position, int downloadProcess) {
        Log.d("fragment", "DownloadingModelOpt执行下载进度回调");
        refreshData(position, downloadProcess);
    }

    @Override
    public void updateDownloadState(int position, boolean downloadState) {
        Log.d("fragment", "DownloadingModelOpt执行下载已完成回调");
        refreshData(position, downloadState);
    }

    //处理下载事件
    public void handleDownloadEvent(DownloadEvent downloadEvent) {
        if (downloadEvent.getNum() > mData.size()) {//属于新增下载任务
            mDownloadingModel = new DownloadingModel();
            mDownloadingModel.setTitle(downloadEvent.getTitle());
            mDownloadingModel.setPicture(downloadEvent.getAvatar());
            mDownloadingModel.setDownloadProcess(downloadEvent.getProcess());
            mDownloadingModel.setDownloadstate(downloadEvent.getStatus());
            mData.add(mDownloadingModel);
        }
    }
}
