package com.hxp.leschool.model.operate;

import android.util.Log;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVFile;
import com.avos.avoscloud.GetDataCallback;
import com.avos.avoscloud.ProgressCallback;
import com.hxp.leschool.model.bean.ClassDetailModel;
import com.hxp.leschool.utils.MyApplication;
import com.hxp.leschool.utils.MyFileHelper;
import com.hxp.leschool.view.activity.ClassDetailActivity;
import com.hxp.leschool.viewmodel.ClassDetailViewModel;

import java.util.HashMap;

/**
 * Created by hxp on 17-1-25.
 */

public class ClassDetailModelOpt {

    public ClassDetailModel mClassDetailModel;
    private ClassDetailOptCallback mClassDetailOptCallback;
    private ClassDetailActivity mClassDetailActivity;
    private String mClassTitle;
    private String mClassUrl;

    public ClassDetailModelOpt(ClassDetailActivity classDetailActivity, ClassDetailViewModel classDetailViewModel) {
        mClassDetailActivity = classDetailActivity;
        mClassDetailOptCallback = classDetailViewModel;

        mClassTitle = mClassDetailActivity.getIntent().getStringExtra("classTitle");
        mClassUrl = mClassDetailActivity.getIntent().getStringExtra("classUrl");
    }

    public void getData() {
        mClassDetailModel = new ClassDetailModel();
        mClassDetailModel.setTitle(mClassTitle);
        Log.d("fragment", "ClassDetailViewModelOpt数据获取成功回调发送方");
        mClassDetailOptCallback.classDetailGetdataSucceedCompleted();
    }

    //下载数据到本地
    public void downloadData() {
        final AVFile file = new AVFile(mClassTitle, mClassUrl, new HashMap<String, Object>());
        Log.d("fragment", "下载的文件名：" + mClassTitle);
        file.getDataInBackground(new GetDataCallback() {
            @Override
            public void done(byte[] bytes, AVException e) {
                Log.d("fragment", "ClassModelOpt下载完成");
                MyFileHelper.saveFileToExternalStoragePrivateFileDir(bytes, "download", file.getName(), MyApplication.getInstance());
            }
        }, new ProgressCallback() {
            @Override
            public void done(Integer integer) {
                Log.d("fragment", "下载进度：" + integer);
            }
        });
    }

    //获取数据成功回调
    //获取数据失败回调
    //刷新数据成功回调
    //刷新数据失败回调
    public interface ClassDetailOptCallback {
        void classDetailGetdataSucceedCompleted();

        void classDetailGetdataFailedCompleted();

        void classDetailRefreshdataSucceedCompleted();

        void classDetailRefreshdataFailedCompleted();
    }
}
