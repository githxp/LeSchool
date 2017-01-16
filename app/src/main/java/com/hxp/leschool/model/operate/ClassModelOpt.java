package com.hxp.leschool.model.operate;

import android.util.Log;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVFile;
import com.avos.avoscloud.AVObject;
import com.avos.avoscloud.AVQuery;
import com.avos.avoscloud.FindCallback;
import com.avos.avoscloud.GetDataCallback;
import com.avos.avoscloud.SaveCallback;
import com.hxp.leschool.model.bean.ClassModel;
import com.hxp.leschool.utils.MyApplication;
import com.hxp.leschool.utils.MyFileHelper;
import com.hxp.leschool.viewmodel.ClassViewModel;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * Created by hxp on 17-1-15.
 */

public class ClassModelOpt {

    public ClassModel mClassModel;
    public ArrayList<ClassModel> mData = new ArrayList<>();
    private AVQuery<AVObject> avQuery = new AVQuery<>("TodoFolder");
    private GetdataCallback mGetdataCallback;
    private RefreshdataCallback mRefreshdataCallback;

    public ClassModelOpt(ClassViewModel classViewModel) {
        mGetdataCallback = classViewModel;
        mRefreshdataCallback = classViewModel;
    }

    //获取数据
    public void getData() {
        avQuery.selectKeys(Arrays.asList("name", "url"));
        avQuery.orderByDescending("createdAt");
        avQuery.findInBackground(new FindCallback<AVObject>() {
            @Override
            public void done(List<AVObject> list, AVException e) {
                mData.clear();
                Log.d("fragment", "mdata已清除");
                for (int i = 0; i < list.size(); i++) {
                    mClassModel = new ClassModel();
                    mClassModel.setTitle(list.get(i).getString("name"));
                    mClassModel.setUrl(list.get(i).getString("url"));
                    mData.add(mClassModel);
                }
                for (int i = 0; i < mData.size(); i++) {
                    Log.d("fragment", "获得数据1：" + mData.get(i).getTitle() + "url:" + mData.get(i).getUrl());
                }
                Log.d("fragment", "mdata.size:" + mData.size());
                Log.d("fragment", "数据获取回调发送方");
                mGetdataCallback.getdataCompleted();
            }
        });
    }

    //刷新数据
    public void refreshData() {
        avQuery.selectKeys(Arrays.asList("name", "url"));
        avQuery.orderByDescending("createdAt");
        avQuery.findInBackground(new FindCallback<AVObject>() {
            @Override
            public void done(List<AVObject> list, AVException e) {
                mData.clear();
                Log.d("fragment", "mdata已清除");
                for (int i = 0; i < list.size(); i++) {
                    mClassModel = new ClassModel();
                    mClassModel.setTitle(list.get(i).getString("name"));
                    mClassModel.setUrl(list.get(i).getString("url"));
                    mData.add(mClassModel);
                }
                for (int i = 0; i < mData.size(); i++) {
                    Log.d("fragment", "获得数据2：" + mData.get(i).getTitle() + "url:" + mData.get(i).getUrl());
                }
                Log.d("fragment", "mdata.size:" + mData.size());
                Log.d("fragment", "数据刷新回调发送方");
                mRefreshdataCallback.refreshdataCompleted();
            }
        });
    }

    //获取数据数量
    public int getCount() {
        return mData.size();
    }

    //上传数据到服务器
    public void uploadData(String fileName, String filePath) throws FileNotFoundException {
        final AVFile file = AVFile.withAbsoluteLocalPath(fileName, filePath);
        file.saveInBackground(new SaveCallback() {
            @Override
            public void done(AVException e) {
                AVObject avObject = new AVObject("TodoFolder");
                Log.d("fragment", "上传完成：" + file.getUrl());
                avObject.put("name", file.getOriginalName());
                avObject.put("url", file.getUrl());
                avObject.saveInBackground(new SaveCallback() {
                    @Override
                    public void done(AVException e) {
                        Log.d("fragment", "创建对象完成" + file.getOriginalName());
                    }
                });
            }
        });

    }

    //下载数据到本地
    public void downloadData(int position) {
        final AVFile file = new AVFile(mData.get(position).getTitle(), mData.get(position).getUrl(), new HashMap<String, Object>());
        file.getDataInBackground(new GetDataCallback() {
            @Override
            public void done(byte[] bytes, AVException e) {
                Log.d("fragment", "下载完成");
                MyFileHelper.saveFileToExternalStoragePrivateFileDir(bytes, "download", file.getName(), MyApplication.getInstance());
            }
        });
    }

    //获取数据回调
    public interface GetdataCallback {
        void getdataCompleted();
    }

    //刷新数据回调
    public interface RefreshdataCallback {
        void refreshdataCompleted();
    }
}
