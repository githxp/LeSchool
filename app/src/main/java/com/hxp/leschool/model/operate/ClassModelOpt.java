package com.hxp.leschool.model.operate;

import android.util.Log;
import android.widget.Toast;

import com.hxp.leschool.R;
import com.hxp.leschool.model.bean.BmobClassModel;
import com.hxp.leschool.model.bean.ClassModel;
import com.hxp.leschool.viewmodel.ClassViewModel;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.datatype.BmobFile;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.SaveListener;
import cn.bmob.v3.listener.UploadFileListener;

/**
 * Created by hxp on 17-1-15.
 */

public class ClassModelOpt {

    public ClassModel mClassModel;
    public ArrayList<ClassModel> mData = new ArrayList<>();
    private ClassOptCallback mClassOptCallback;

    public ClassModelOpt(ClassViewModel classViewModel) {
        mClassOptCallback = classViewModel;
    }

    //获取数据
    public void getData() {
        BmobQuery<BmobClassModel> bmobQuery = new BmobQuery<>();
        bmobQuery.order("-createdAt");
        bmobQuery.findObjects(new FindListener<BmobClassModel>() {
            @Override
            public void done(List<BmobClassModel> list, BmobException e) {
                if (e == null) {
                    Log.d("fragment", "查询成功" + list.size());
                    mData.clear();
                    for (int i = 0; i < list.size(); i++) {
                        mClassModel = new ClassModel();
                        mClassModel.setPicture(R.mipmap.ic_launcher);
                        mClassModel.setTitle(list.get(i).getTitle());
                        mClassModel.setUrl(list.get(i).getUrl());
                        mData.add(mClassModel);
                        Log.d("fragment", "查询成功" + list.get(i).getTitle());
                    }
                    Log.d("fragment", "ClassModelOpt数据获取成功回调发送方");
                    mClassOptCallback.classGetdataSucceedCompleted();
                } else {
                    Log.d("fragment", "查询失败");
                }
            }
        });
    }

    //刷新数据
    public void refreshData() {
        BmobQuery<BmobClassModel> bmobQuery = new BmobQuery<>();
        bmobQuery.order("-createdAt");
        bmobQuery.findObjects(new FindListener<BmobClassModel>() {
            @Override
            public void done(List<BmobClassModel> list, BmobException e) {
                if (e == null) {
                    Log.d("fragment", "查询成功" + list.size());
                    mData.clear();
                    for (int i = 0; i < list.size(); i++) {
                        mClassModel = new ClassModel();
                        mClassModel.setPicture(R.mipmap.ic_launcher);
                        mClassModel.setTitle(list.get(i).getTitle());
                        mClassModel.setUrl(list.get(i).getUrl());
                        mData.add(mClassModel);
                        Log.d("fragment", "查询成功" + list.get(i).getTitle());
                    }
                    Log.d("fragment", "ClassModelOpt数据刷新成功回调发送方");
                    mClassOptCallback.classRefreshdataSucceedCompleted();
                } else {
                    Log.d("fragment", "查询失败");
                }
            }
        });
    }

    //获取数据数量
    public int getCount() {
        return mData.size();
    }

    //上传数据到服务器
    public void uploadData(String filePath) {
        final BmobFile bmobFile = new BmobFile(new File(filePath));
        bmobFile.uploadblock(new UploadFileListener() {
            @Override
            public void done(BmobException e) {
                if (e == null) {
                    Log.d("fragment", "上传文件成功:" + bmobFile.getFileUrl());
                    BmobClassModel bmobClassModel = new BmobClassModel();
                    bmobClassModel.setTitle(bmobFile.getFilename());
                    bmobClassModel.setUrl(bmobFile.getFileUrl());
                    bmobClassModel.save(new SaveListener<String>() {
                        @Override
                        public void done(String s, BmobException e) {
                            if (e == null) {
                                Log.d("fragment", "上传保存对象成功");
                            } else {
                                Log.d("fragment", "上传保存对象异常" + e.getMessage());
                            }
                        }
                    });
                } else {
                    Log.d("fragment", "上传文件失败：" + e.getMessage());
                }
            }
            @Override
            public void onProgress(Integer value) {
                Log.d("fragment", "上传进度：" + value);
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
