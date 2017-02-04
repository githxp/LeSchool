package com.hxp.leschool.model.operate;

import android.util.Log;

import com.hxp.leschool.model.bean.UploadingModel;
import com.hxp.leschool.utils.event.UploadEvent;
import com.hxp.leschool.viewmodel.UploadingViewModel;

import java.util.ArrayList;


/**
 * Created by hxp on 17-1-15.
 */


public class UploadingModelOpt {

    public UploadingModel mUploadingModel;
    public ArrayList<UploadingModel> mData = new ArrayList<>();
    private UploadCallback mUploadCallback;

    public UploadingModelOpt(UploadingViewModel uploadingViewModel) {
        mUploadCallback = uploadingViewModel;
    }

    //处理上传事件
    public void handleUploadEvent(UploadEvent uploadEvent) {
        boolean isNewDownload = true;
        Log.d("fragment", "UploadingModelOpt正在处理" + uploadEvent.getTitle() + uploadEvent.getProcess());
        if (mData.size() != 0) {
            for (int i = 0; i < mData.size(); i++) {//轮询消息
                if (uploadEvent.getTitle().equals(mData.get(i).getTitle())) {//已有下载
                    Log.d("fragment", "更新下载进度");
                    mData.get(i).setProcess(uploadEvent.getProcess());
                    isNewDownload = false;
                    break;
                }
            }
            if (isNewDownload) {
                Log.d("fragment", "新的下载任务" + uploadEvent.getTitle());
                mUploadingModel = new UploadingModel();
                mUploadingModel.setTitle(uploadEvent.getTitle());
                mUploadingModel.setAvatar(uploadEvent.getAvatar());
                mUploadingModel.setProcess(uploadEvent.getProcess());
                mData.add(mUploadingModel);
                mUploadCallback.refresh();//刷新进度(需要event替换)
            }
        } else {//初始下载任务
            Log.d("fragment", "新的下载任务0" + uploadEvent.getTitle());
            mUploadingModel = new UploadingModel();
            mUploadingModel.setTitle(uploadEvent.getTitle());
            mUploadingModel.setAvatar(uploadEvent.getAvatar());
            mUploadingModel.setProcess(uploadEvent.getProcess());
            mData.add(mUploadingModel);
            mUploadCallback.refresh();//刷新进度(需要event替换)
        }
    }

    //获取数据数量
    public int getCount() {
        return mData.size();
    }

    //刷新进度回调
    public interface UploadCallback {
        void refresh();
    }
}
