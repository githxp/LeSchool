package com.hxp.leschool.model.operate;

import android.util.Log;
import android.widget.Toast;

import com.hxp.leschool.databinding.UploadingFmBinding;
import com.hxp.leschool.model.bean.UploadCompletedModel;
import com.hxp.leschool.model.bean.UploadingModel;
import com.hxp.leschool.view.fragment.UploadCompletedFragment;
import com.hxp.leschool.view.fragment.UploadingFragment;
import com.hxp.leschool.viewmodel.UploadCompletedViewModel;
import com.hxp.leschool.viewmodel.UploadingViewModel;

import java.util.ArrayList;

/**
 * Created by hxp on 17-1-15.
 */

public class UploadingModelOpt {

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

        Log.d("fragment", "DownloadModelOpt数据获取成功回调发送方");
        mUploadingOptCallback.uploadingGetdataSucceedCompleted();
    }

    //刷新数据
    public void refreshData() {

        Log.d("fragment", "DownloadModelOpt数据刷新成功回调发送方");
        mUploadingOptCallback.uploadingRefreshdataSucceedCompleted();
    }

    //获取数据数量
    public int getCount() {
        return mData.size();
    }

    //暂停上传
    public void pauseUpload(int position) {

        Toast.makeText(mUploadingFragment.getActivity(), "删除记录", Toast.LENGTH_SHORT).show();
    }

    //删除上传
    public void delUpload(int position) {

        Toast.makeText(mUploadingFragment.getActivity(), "删除记录", Toast.LENGTH_SHORT).show();
    }

    //获取数据成功回调
    //获取数据失败回调
    public interface UploadingOptCallback {
        void uploadingGetdataSucceedCompleted();

        void uploadingGetdataFailedCompleted();

        void uploadingRefreshdataSucceedCompleted();

        void uploadingRefreshdataFailedCompleted();
    }
}
