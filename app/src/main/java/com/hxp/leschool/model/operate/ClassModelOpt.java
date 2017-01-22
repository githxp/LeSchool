package com.hxp.leschool.model.operate;

import android.util.Log;
import android.widget.Toast;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVFile;
import com.avos.avoscloud.AVObject;
import com.avos.avoscloud.AVQuery;
import com.avos.avoscloud.FindCallback;
import com.avos.avoscloud.GetDataCallback;
import com.avos.avoscloud.SaveCallback;
import com.hxp.leschool.R;
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
    private ClassOptCallback mClassOptCallback;

    public ClassModelOpt(ClassViewModel classViewModel) {
        mClassOptCallback = classViewModel;
    }

    //获取数据
    public void getData() {
        avQuery.selectKeys(Arrays.asList("name", "url"));
        avQuery.orderByDescending("createdAt");
        avQuery.findInBackground(new FindCallback<AVObject>() {
            @Override
            public void done(List<AVObject> list, AVException e) {
                if (e == null) {
                    mData.clear();
                    Log.d("fragment", "mdata已清除-ClassModelOpt");
                    for (int i = 0; i < list.size(); i++) {
                        mClassModel = new ClassModel();
                        mClassModel.setTitle(list.get(i).getString("name"));
                        mClassModel.setUrl(list.get(i).getString("url"));
                        mClassModel.setPicture(R.mipmap.ic_launcher);
                        mData.add(mClassModel);
                    }
                    for (int i = 0; i < mData.size(); i++) {
                        Log.d("fragment", "ClassModelOpt获得数据：" + mData.get(i).getTitle() + "url:" + mData.get(i).getUrl());
                    }
                    Log.d("fragment", "mdata.size:" + mData.size() + "ClassModelOpt");
                    Log.d("fragment", "数据获取成功回调发送方-ClassModelOpt");
                    mClassOptCallback.classGetdataSucceedCompleted();
                } else {
                    Log.d("fragment", "数据获取失败回调发送方-ClassModelOpt");
                    mClassOptCallback.classGetdataFailedCompleted();
                }
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
                if (e == null) {
                    mData.clear();
                    Log.d("fragment", "mdata已清除-ClassModelOpt");
                    for (int i = 0; i < list.size(); i++) {
                        mClassModel = new ClassModel();
                        mClassModel.setTitle(list.get(i).getString("name"));
                        mClassModel.setUrl(list.get(i).getString("url"));
                        mClassModel.setPicture(R.mipmap.ic_launcher);
                        mData.add(mClassModel);
                    }
                    for (int i = 0; i < mData.size(); i++) {
                        Log.d("fragment", "ClassModelOpt刷新数据：" + mData.get(i).getTitle() + "url:" + mData.get(i).getUrl());
                    }
                    Log.d("fragment", "mdata.size:" + mData.size() + "ClassModelOpt");
                    Log.d("fragment", "数据刷新成功回调发送方-ClassModelOpt");
                    mClassOptCallback.classRefreshdataSucceedCompleted();
                } else {
                    Log.d("fragment", "数据刷新失败回调发送方-ClassModelOpt");
                    mClassOptCallback.classRefreshdataFailedCompleted();
                }
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
                if (e == null) {
                    AVObject avObject = new AVObject("TodoFolder");
                    Log.d("fragment", "ClassModelOpt上传完成：" + file.getUrl());
                    avObject.put("name", file.getOriginalName());
                    avObject.put("url", file.getUrl());
                    avObject.saveInBackground(new SaveCallback() {
                        @Override
                        public void done(AVException e) {
                            if (e == null) {
                                Log.d("fragment", "ClassModelOpt创建对象完成" + file.getOriginalName());
                            } else {
                                Log.d("fragment", "ClassModelOpt创建对象失败" + e.getMessage());
                            }
                        }
                    });
                } else {
                    Log.d("fragment", "上传失败" + e.getMessage());
                }

            }
        });

    }

    //下载数据到本地
    public void downloadData(int position) {
        final AVFile file = new AVFile(mData.get(position).getTitle(), mData.get(position).getUrl(), new HashMap<String, Object>());
        file.getDataInBackground(new GetDataCallback() {
            @Override
            public void done(byte[] bytes, AVException e) {
                Log.d("fragment", "ClassModelOpt下载完成");
                MyFileHelper.saveFileToExternalStoragePrivateFileDir(bytes, "download", file.getName(), MyApplication.getInstance());
            }
        });
    }

    //获取数据成功回调
    //获取数据失败回调
    //刷新数据成功回调
    //刷新数据失败回调
    public interface ClassOptCallback {
        void classGetdataSucceedCompleted();

        void classGetdataFailedCompleted();

        void classRefreshdataSucceedCompleted();

        void classRefreshdataFailedCompleted();
    }
}
