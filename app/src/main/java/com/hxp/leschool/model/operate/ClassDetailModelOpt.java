package com.hxp.leschool.model.operate;

import android.util.Log;

import com.hxp.leschool.R;
import com.hxp.leschool.model.bean.ClassDetailModel;
import com.hxp.leschool.model.bean.DownloadTaskModel;
import com.hxp.leschool.utils.DownloadingPublish;
import com.hxp.leschool.utils.MyApplication;
import com.hxp.leschool.view.activity.ClassDetailActivity;
import com.hxp.leschool.viewmodel.ClassDetailViewModel;

import java.io.File;

import cn.bmob.v3.datatype.BmobFile;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.DownloadFileListener;

/**
 * Created by hxp on 17-1-25.
 */


public class ClassDetailModelOpt {

    public ClassDetailModel mClassDetailModel;
    private ClassDetailOptCallback mClassDetailOptCallback;
    private ClassDetailActivity mClassDetailActivity;
    private String mClassTitle;
    private String mClassUrl;
    private int mFirstProcess = 0;

    public ClassDetailModelOpt(ClassDetailActivity classDetailActivity, ClassDetailViewModel classDetailViewModel) {
        mClassDetailActivity = classDetailActivity;
        mClassDetailOptCallback = classDetailViewModel;

        mClassDetailModel = new ClassDetailModel();

        mClassTitle = mClassDetailActivity.getIntent().getStringExtra("classTitle");
        mClassUrl = mClassDetailActivity.getIntent().getStringExtra("classUrl");
    }

    public void getData() {
        mClassDetailModel.setTitle(mClassTitle);
        Log.d("fragment", "ClassDetailViewModelOpt数据获取成功回调发送方");
        mClassDetailOptCallback.classDetailGetdataSucceedCompleted();
    }

    //添加下载任务
    public void download() {
        final DownloadTaskModel mDownloadTaskModel = new DownloadTaskModel();
        mDownloadTaskModel.setTitle(mClassTitle);
        mDownloadTaskModel.setPicture(R.mipmap.ic_launcher);
        DownloadingPublish.addDownloadTask(mDownloadTaskModel);
        BmobFile bmobFile = new BmobFile(mClassTitle, "", mClassUrl);
        bmobFile.download(new File(MyApplication.getInstance().getExternalFilesDir("download"), mClassTitle), new DownloadFileListener() {
            @Override
            public void done(String s, BmobException e) {
                Log.d("fragment", "下载路径：" + s);
                mDownloadTaskModel.setDownloadState(DownloadingPublish.getDownloadTask().indexOf(mDownloadTaskModel), true);
            }

            @Override
            public void onProgress(Integer integer, long l) {
                if (integer - mFirstProcess >= 1) {
                    mFirstProcess = integer;
                    Log.d("fragment", "触发更新进度:" + integer + mClassTitle);
                    mDownloadTaskModel.setDownloadProcess(DownloadingPublish.getDownloadTask().indexOf(mDownloadTaskModel), integer);
                }
            }
        });
        Log.d("fragment", "下载的文件名：" + mClassTitle);
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