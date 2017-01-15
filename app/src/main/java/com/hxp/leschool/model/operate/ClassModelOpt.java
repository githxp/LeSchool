package com.hxp.leschool.model.operate;

import android.util.Log;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVObject;
import com.avos.avoscloud.AVQuery;
import com.avos.avoscloud.CountCallback;
import com.avos.avoscloud.FindCallback;
import com.hxp.leschool.adapter.ClassAdapter;
import com.hxp.leschool.databinding.ClassFmBinding;
import com.hxp.leschool.model.bean.ClassModel;
import com.hxp.leschool.viewmodel.ClassViewModel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by hxp on 17-1-15.
 */

public class ClassModelOpt {

    public ClassModel mClassModel;
    public ArrayList<ClassModel> mData;
    private AVQuery<AVObject> avQuery = new AVQuery<>("TodoFolder");
    private GetdataCallback mGetdataCallback;
    private RefreshdataCallback mRefreshdataCallback;

    public ClassModelOpt(ClassViewModel classViewModel) {
        mGetdataCallback = classViewModel;
        mRefreshdataCallback = classViewModel;
    }

    //获取数据
    public void getData() {
        avQuery.selectKeys(Arrays.asList("name"));
        avQuery.orderByDescending("createdAt");
        avQuery.findInBackground(new FindCallback<AVObject>() {
            @Override
            public void done(List<AVObject> list, AVException e) {
                mData = new ArrayList<>();
                for (int i = 0; i < list.size(); i++) {
                    mClassModel = new ClassModel();
                    mClassModel.setTitle(list.get(i).getString("name"));
                    mData.add(mClassModel);
                }
                for (int i = 0; i < mData.size(); i++) {
                    Log.d("fragment", "获得数据1：" + mData.get(i).getTitle() + "  ");
                }
                Log.d("fragment", "数据获取回调发送方");
                mGetdataCallback.getdataCompleted();
            }
        });
    }

    //获取数据数量
    public int getCount() {
        return mData.size();
    }

    //上传数据到服务器
    public void uploadData() {

    }

    //下载数据到本地
    public void downloadData() {

    }

    //刷新数据
    public void refreshData() {
        avQuery.selectKeys(Arrays.asList("name"));
        avQuery.orderByDescending("createdAt");
        avQuery.findInBackground(new FindCallback<AVObject>() {
            @Override
            public void done(List<AVObject> list, AVException e) {
                mData = new ArrayList<>();
                for (int i = 0; i < list.size(); i++) {
                    mClassModel = new ClassModel();
                    mClassModel.setTitle(list.get(i).getString("name"));
                    mData.add(mClassModel);
                }
                for (int i = 0; i < mData.size(); i++) {
                    Log.d("fragment", "获得数据2：" + mData.get(i).getTitle() + "  ");
                }
                Log.d("fragment", "数据刷新回调发送方");
                mRefreshdataCallback.refreshdataCompleted();
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
