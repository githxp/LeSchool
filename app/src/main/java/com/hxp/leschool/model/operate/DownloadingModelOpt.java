/*
package com.hxp.leschool.model.operate;

import android.util.Log;
import android.widget.Toast;

import com.hxp.leschool.model.bean.DownloadTaskModel;
import com.hxp.leschool.model.bean.DownloadingModel;
import com.hxp.leschool.utils.DownloadingPublish;
import com.hxp.leschool.view.fragment.DownloadingFragment;
import com.hxp.leschool.viewmodel.DownloadingViewModel;

import java.util.ArrayList;

*/
/**
 * Created by hxp on 17-1-15.
 *//*


public class DownloadingModelOpt implements DownloadTaskModel.DownloadUpdateCallback {

    public DownloadingModel mDownloadingModel;
    public ArrayList<DownloadingModel> mData = new ArrayList<>();
    private DownloadingOptCallback mDownloadingOptCallback;
    private DownloadingFragment mDownloadingFragment;

    public DownloadingModelOpt(DownloadingViewModel downloadingViewModel, DownloadingFragment downloadingFragment) {
        mDownloadingOptCallback = downloadingViewModel;
        mDownloadingFragment = downloadingFragment;

        DownloadTaskModel.setDownloadUpdateCallback(this);
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
        mDownloadingOptCallback.downloadingGetdataSucceedCompleted();
    }

    //刷新数据
    public void refreshData() {
        mData.clear();
        DownloadTaskModel downloadTaskModel;
        for (int i = 0; i < DownloadingPublish.getDownloadTaskCount(); i++) {
            downloadTaskModel = DownloadingPublish.getDownloadTask().get(i);
            if (mDownloadingModel == null) {
                Log.d("fragment", "mDownloadingModel刷新时新建");
                mDownloadingModel = new DownloadingModel();
                mDownloadingModel.setTitle(downloadTaskModel.getTitle());
                mDownloadingModel.setPicture(downloadTaskModel.getPicture());
                mDownloadingModel.setDownloadstate(downloadTaskModel.getDownloadState());
                mDownloadingModel.setDownloadProcess(downloadTaskModel.getDownloadProcess());
            }
            Log.d("fragment", "mDownloadingModel刷新时未新建");
            mDownloadingModel.setDownloadProcess(downloadTaskModel.getDownloadProcess());
            mData.add(mDownloadingModel);
        }
        Log.d("fragment", "DownloadModelOpt数据刷新成功回调发送方");
        mDownloadingOptCallback.downloadingRefreshdataSucceedCompleted();
    }

    //获取数据数量
    public int getCount() {
        return mData.size();
    }

    //暂停下载
    public void pauseDownload(int position) {

        Toast.makeText(mDownloadingFragment.getActivity(), "暂停下载", Toast.LENGTH_SHORT).show();

    }

    //删除下载
    public void delDownload(int position) {

        Toast.makeText(mDownloadingFragment.getActivity(), "删除下载", Toast.LENGTH_SHORT).show();
    }

    //获取数据成功回调
    //获取数据失败回调
    public interface DownloadingOptCallback {
        void downloadingGetdataSucceedCompleted();

        void downloadingGetdataFailedCompleted();

        void downloadingRefreshdataSucceedCompleted();

        void downloadingRefreshdataFailedCompleted();
    }

    @Override
    public void updateDownloadProcess() {
        Log.d("fragment", "DownloadingModelOpt执行下载进度回调");
        refreshData();
    }

    @Override
    public void updateDownloadState() {
        Log.d("fragment", "DownloadingModelOpt执行下载已完成回调");
        refreshData();
    }
}
*/
