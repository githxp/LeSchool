package com.hxp.leschool.model.operate;

import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.widget.Toast;

import com.hxp.leschool.R;
import com.hxp.leschool.model.bean.DownloadCompletedModel;
import com.hxp.leschool.model.bean.DownloadTaskModel;
import com.hxp.leschool.model.bean.DownloadingModel;
import com.hxp.leschool.utils.MIMEHelper;
import com.hxp.leschool.utils.MyApplication;
import com.hxp.leschool.utils.MyFileHelper;
import com.hxp.leschool.view.fragment.DownloadCompletedFragment;
import com.hxp.leschool.view.fragment.DownloadingFragment;
import com.hxp.leschool.viewmodel.DownloadCompletedViewModel;
import com.hxp.leschool.viewmodel.DownloadingViewModel;

import java.io.File;
import java.util.ArrayList;

/**
 * Created by hxp on 17-1-15.
 */

public class DownloadingModelOpt {

    public DownloadingModel mDownloadingModel;
    public ArrayList<DownloadingModel> mData = new ArrayList<>();
    private DownloadingOptCallback mDownloadingOptCallback;
    private ArrayList<File> fileNameList;
    private DownloadingFragment mDownloadingFragment;

    public DownloadingModelOpt(DownloadingViewModel downloadingViewModel, DownloadingFragment downloadingFragment) {
        mDownloadingOptCallback = downloadingViewModel;
        mDownloadingFragment = downloadingFragment;
    }

    //获取数据
    public void getData() {
        mData.clear();
        DownloadTaskModel downloadTaskModel;
        for (int i = 0; i < MyApplication.getInstance().getDownloadTask().size(); i++) {
            downloadTaskModel = MyApplication.getInstance().getDownloadTask().get(i);
            mDownloadingModel = new DownloadingModel();
            mDownloadingModel.setTitle(downloadTaskModel.getTitle());
            mDownloadingModel.setPicture(downloadTaskModel.getPicture());
            mDownloadingModel.setDownloadstate(downloadTaskModel.getIsDownloadCompleted());
            mDownloadingModel.setDownloadProcess(downloadTaskModel.getDownloadProcess());
            mData.add(mDownloadingModel);
        }
        Log.d("fragment", "DownloadModelOpt数据获取成功回调发送方");
        mDownloadingOptCallback.downloadingGetdataSucceedCompleted();
    }

    //刷新数据
    public void refreshData() {
        for (int i = 0; i < MyApplication.getInstance().getDownloadTask().size(); i++) {
            if (!MyApplication.getInstance().getDownloadTask().get(i).getIsDownloadCompleted()) {
                mData.clear();
                DownloadTaskModel downloadTaskModel;
                for (int j = 0; j < MyApplication.getInstance().getDownloadTask().size(); j++) {
                    downloadTaskModel = MyApplication.getInstance().getDownloadTask().get(j);
                    mDownloadingModel = new DownloadingModel();
                    mDownloadingModel.setTitle(downloadTaskModel.getTitle());
                    mDownloadingModel.setPicture(downloadTaskModel.getPicture());
                    mDownloadingModel.setDownloadstate(downloadTaskModel.getIsDownloadCompleted());
                    mDownloadingModel.setDownloadProcess(downloadTaskModel.getDownloadProcess());
                    mData.add(mDownloadingModel);
                }
                Log.d("fragment", "DownloadModelOpt数据刷新成功回调发送方");
                mDownloadingOptCallback.downloadingRefreshdataSucceedCompleted();
            }
        }
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
}
