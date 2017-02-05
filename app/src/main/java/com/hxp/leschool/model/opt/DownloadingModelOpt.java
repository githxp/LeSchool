package com.hxp.leschool.model.opt;

import android.util.Log;

import com.hxp.leschool.model.bean.DownloadingModel;
import com.hxp.leschool.utils.event.DownloadEvent;
import com.hxp.leschool.viewmodel.DownloadingViewModel;

import java.util.ArrayList;


/**
 * Created by hxp on 17-1-15.
 */


public class DownloadingModelOpt {

    public DownloadingModel mDownloadingModel;
    public ArrayList<DownloadingModel> mData = new ArrayList<>();
    private DownloadCallback mDownloadCallback;

    public DownloadingModelOpt(DownloadingViewModel downloadingViewModel) {
        mDownloadCallback = downloadingViewModel;
    }

    //处理下载事件
    public void handleDownloadEvent(DownloadEvent downloadEvent) {
        boolean isNewDownload = true;
        Log.d("fragment", "downloadingopt正在处理" + downloadEvent.getTitle() + downloadEvent.getProcess());
        if (mData.size() != 0) {
            for (int i = 0; i < mData.size(); i++) {//轮询消息
                if (downloadEvent.getTitle().equals(mData.get(i).getTitle())) {//已有下载
                    Log.d("fragment", "更新下载进度");
                    mData.get(i).setProcess(downloadEvent.getProcess());
                    isNewDownload = false;
                    break;
                }
            }
            if (isNewDownload) {
                Log.d("fragment", "新的下载任务" + downloadEvent.getTitle());
                mDownloadingModel = new DownloadingModel();
                mDownloadingModel.setTitle(downloadEvent.getTitle());
                mDownloadingModel.setAvatar(downloadEvent.getAvatar());
                mDownloadingModel.setProcess(downloadEvent.getProcess());
                mData.add(mDownloadingModel);
                mDownloadCallback.refresh();//刷新进度(需要event替换)
            }
        } else {//初始下载任务
            Log.d("fragment", "新的下载任务0" + downloadEvent.getTitle());
            mDownloadingModel = new DownloadingModel();
            mDownloadingModel.setTitle(downloadEvent.getTitle());
            mDownloadingModel.setAvatar(downloadEvent.getAvatar());
            mDownloadingModel.setProcess(downloadEvent.getProcess());
            mData.add(mDownloadingModel);
            mDownloadCallback.refresh();//刷新进度(需要event替换)
        }
    }

    //获取数据数量
    public int getCount() {
        return mData.size();
    }

    //刷新进度回调
    public interface DownloadCallback {
        void refresh();
    }
}


