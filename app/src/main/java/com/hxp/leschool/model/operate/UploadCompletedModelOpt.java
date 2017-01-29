/*
package com.hxp.leschool.model.operate;

import android.util.Log;
import android.widget.Toast;

import com.hxp.leschool.model.bean.UploadCompletedModel;
import com.hxp.leschool.view.fragment.UploadCompletedFragment;
import com.hxp.leschool.viewmodel.UploadCompletedViewModel;

import java.util.ArrayList;

*/
/**
 * Created by hxp on 17-1-15.
 *//*


public class UploadCompletedModelOpt {

    public UploadCompletedModel mUploadCompletedModel;
    public ArrayList<UploadCompletedModel> mData = new ArrayList<>();
    private UploadCompletedOptCallback mUploadCompletedOptCallback;
    private UploadCompletedFragment mUploadCompletedFragment;

    public UploadCompletedModelOpt(UploadCompletedViewModel uploadCompletedViewModel, UploadCompletedFragment uploadCompletedFragment) {
        mUploadCompletedOptCallback = uploadCompletedViewModel;
        mUploadCompletedFragment = uploadCompletedFragment;
    }

    //获取数据
    public void getData() {

        Log.d("fragment", "DownloadModelOpt数据获取成功回调发送方");
        mUploadCompletedOptCallback.uploadCompletedGetdataSucceedCompleted();
    }

    //刷新数据
    public void refreshData() {

        Log.d("fragment", "DownloadModelOpt数据刷新成功回调发送方");
        mUploadCompletedOptCallback.uploadCompletedRefreshdataSucceedCompleted();
    }

    //获取数据数量
    public int getCount() {
        return mData.size();
    }

    //删除上传记录
    public void delRecord(int position) {

        Toast.makeText(mUploadCompletedFragment.getActivity(), "删除记录", Toast.LENGTH_SHORT).show();
    }

    //获取数据成功回调
    //获取数据失败回调
    public interface UploadCompletedOptCallback {
        void uploadCompletedGetdataSucceedCompleted();

        void uploadCompletedGetdataFailedCompleted();

        void uploadCompletedRefreshdataSucceedCompleted();

        void uploadCompletedRefreshdataFailedCompleted();
    }
}
*/
