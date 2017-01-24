package com.hxp.leschool.model.operate;

import android.util.Log;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVFile;
import com.avos.avoscloud.AVObject;
import com.avos.avoscloud.AVQuery;
import com.avos.avoscloud.FindCallback;
import com.avos.avoscloud.GetDataCallback;
import com.avos.avoscloud.SaveCallback;
import com.hxp.leschool.R;
import com.hxp.leschool.model.bean.ClassModel;
import com.hxp.leschool.model.bean.DownloadModel;
import com.hxp.leschool.utils.MyApplication;
import com.hxp.leschool.utils.MyFileHelper;
import com.hxp.leschool.viewmodel.ClassViewModel;
import com.hxp.leschool.viewmodel.DownloadViewModel;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * Created by hxp on 17-1-15.
 */

public class DownloadModelOpt {

    public DownloadModel mDownloadModel;
    public ArrayList<DownloadModel> mData = new ArrayList<>();
    private DownloadOptCallback mDownloadOptCallback;
    private String fileDir = MyApplication.getInstance().getExternalFilesDir("download").getAbsolutePath();
    private ArrayList<String> fileNameList = MyFileHelper.getFileNameList(fileDir);

    public DownloadModelOpt(DownloadViewModel downloadViewModel) {
        mDownloadOptCallback = downloadViewModel;
    }

    //获取数据
    public void getData() {
        Log.d("fragment", "扫描路径：" + fileDir);
        mData.clear();
        for (int i = 0; i < fileNameList.size(); i++) {
            mDownloadModel = new DownloadModel();
            mDownloadModel.setPicture(R.mipmap.ic_launcher);
            mDownloadModel.setTitle(fileNameList.get(i));
            mData.add(mDownloadModel);
        }
        Log.d("fragment", "DownloadModelOpt数据获取成功回调发送方");
        mDownloadOptCallback.downloadGetdataSucceedCompleted();
    }

    //刷新数据
    public void refreshData() {
        Log.d("fragment", "扫描路径：" + fileDir);
        mData.clear();
        for (int i = 0; i < fileNameList.size(); i++) {
            mDownloadModel = new DownloadModel();
            mDownloadModel.setPicture(R.mipmap.ic_launcher);
            mDownloadModel.setTitle(fileNameList.get(i));
            mData.add(mDownloadModel);
        }
        Log.d("fragment", "DownloadModelOpt数据刷新成功回调发送方");
        mDownloadOptCallback.downloadRefreshdataSucceedCompleted();
    }

    //获取数据数量
    public int getCount() {
        return mData.size();
    }

    //获取数据成功回调
    //获取数据失败回调
    public interface DownloadOptCallback {
        void downloadGetdataSucceedCompleted();

        void downloadGetdataFailedCompleted();

        void downloadRefreshdataSucceedCompleted();

        void downloadRefreshdataFailedCompleted();
    }
}
