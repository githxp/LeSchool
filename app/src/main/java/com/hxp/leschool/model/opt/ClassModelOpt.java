package com.hxp.leschool.model.opt;

import android.util.Log;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVObject;
import com.avos.avoscloud.AVQuery;
import com.avos.avoscloud.AVUser;
import com.avos.avoscloud.FindCallback;
import com.hxp.leschool.R;
import com.hxp.leschool.model.server.object.MyClassObject;
import com.hxp.leschool.model.bean.ClassModel;
import com.hxp.leschool.utils.event.UploadEvent;
import com.hxp.leschool.viewmodel.ClassViewModel;

import org.greenrobot.eventbus.EventBus;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
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
    private ClassCallback mClassCallback;
    private int mFirstProcess = 0;
    private AVQuery<AVObject> avQuery;
    private int mCount = 0;
    private BmobQuery<MyClassObject> bmobQuery;

    public ClassModelOpt(ClassViewModel classViewModel) {
        mClassCallback = classViewModel;
    }

    //获取数据
    public void get() {
        bmobQuery = new BmobQuery<>();
        bmobQuery.order("-createdAt");
        bmobQuery.findObjects(new FindListener<MyClassObject>() {
            @Override
            public void done(List<MyClassObject> list, BmobException e) {
                if (e == null) {
                    Log.d("fragment", "查询成功" + list.size());
                    mData.clear();
                    for (int i = 0; i < list.size(); i++) {
                        mClassModel = new ClassModel();
                        mClassModel.setUserName(list.get(i).getUserName());
                        mClassModel.setTitle(list.get(i).getTitle());
                        mClassModel.setUrl(list.get(i).getUrl());
                        mData.add(mClassModel);
                        Log.d("fragment", "查询成功" + list.get(i).getTitle());
                    }
                } else {
                    Log.d("fragment", "查询失败");
                }
                Log.d("fragment", "开始查询头像");
                getAvatar();
            }
        });
    }

    //获取头像
    private void getAvatar() {
        avQuery = new AVQuery<>("UserInfo");
        avQuery.selectKeys(Arrays.asList("userName", "avatar"));
        for (int i = 0; i < mData.size(); i++) {
            Log.d("fragment", "开始查询" + mData.get(i).getUserName() + "的头像");
            avQuery.whereEqualTo("userName", mData.get(i).getUserName());
            final int j = i;
            avQuery.findInBackground(new FindCallback<AVObject>() {
                @Override
                public void done(List<AVObject> list, AVException e) {
                    if (e == null) {
                        if (list.size() == 1) {
                            mData.get(j).setAvatar(list.get(0).getString("avatar"));
                            Log.d("fragment", j + mData.get(j).getUserName() + "的头像获取成功" + mData.get(j).getAvatar());
                            mCount++;
                            Log.d("fragment", "测试发行");
                            if (mCount == mData.size()) {
                                mCount = 0;
                                Log.d("fragment", "ClassModelOpt数据获取成功回调发送方");
                                mClassCallback.get();
                            }
                        } else {
                            Log.d("fragment", "头像获取数量异常");
                        }
                    } else {
                        Log.d("fragment", "ClassModelOpt数据获取失败回调发送方" + e.getMessage());
                        mClassCallback.getErr();
                    }
                }
            });
        }
    }

    //刷新数据
    public void refresh() {
        BmobQuery<MyClassObject> bmobQuery = new BmobQuery<>();
        bmobQuery.order("-createdAt");
        bmobQuery.findObjects(new FindListener<MyClassObject>() {
            @Override
            public void done(List<MyClassObject> list, BmobException e) {
                if (e == null) {
                    Log.d("fragment", "查询成功" + list.size());
                    mData.clear();
                    for (int i = 0; i < list.size(); i++) {
                        mClassModel = new ClassModel();
                        mClassModel.setUserName(list.get(i).getUserName());
                        mClassModel.setTitle(list.get(i).getTitle());
                        mClassModel.setUrl(list.get(i).getUrl());
                        mData.add(mClassModel);
                        Log.d("fragment", "查询成功" + list.get(i).getTitle());
                    }
                } else {
                    Log.d("fragment", "查询失败");
                }
                Log.d("fragment", "开始查询头像");
                refreshAvatar();
            }
        });
    }


    //刷新头像
    private void refreshAvatar() {
        avQuery = new AVQuery<>("UserInfo");
        avQuery.selectKeys(Arrays.asList("userName", "avatar"));
        for (int i = 0; i < mData.size(); i++) {
            Log.d("fragment", "开始查询" + mData.get(i).getUserName() + "的头像");
            avQuery.whereEqualTo("userName", mData.get(i).getUserName());
            final int j = i;
            avQuery.findInBackground(new FindCallback<AVObject>() {
                @Override
                public void done(List<AVObject> list, AVException e) {
                    if (e == null) {
                        if (list.size() == 1) {
                            mData.get(j).setAvatar(list.get(0).getString("avatar"));
                            Log.d("fragment", j + mData.get(j).getUserName() + "的头像获取成功" + mData.get(j).getAvatar());
                            mCount++;
                            Log.d("fragment", "测试发行");
                            if (mCount == mData.size()) {
                                mCount = 0;
                                Log.d("fragment", "ClassModelOpt数据刷新成功回调发送方");
                                mClassCallback.refresh();
                            }
                        } else {
                            Log.d("fragment", "头像获取数量异常");
                        }
                    } else {
                        Log.d("fragment", "ClassModelOpt数据刷新失败回调发送方" + e.getMessage());
                        mClassCallback.refreshErr();
                    }
                }
            });
        }
    }

    //获取数据数量
    public int getCount() {
        return mData.size();
    }

    //上传数据到服务器
    public void uploadData(String filePath) {
        final String uploadTitle = filePath.substring(filePath.lastIndexOf("/") + 1);
        final BmobFile bmobFile = new BmobFile(new File(filePath));
        bmobFile.uploadblock(new UploadFileListener() {
            @Override
            public void done(BmobException e) {
                Log.d("fragment", "上传文件成功:" + bmobFile.getFileUrl());
                MyClassObject myClassObject = new MyClassObject();
                myClassObject.setUserName(AVUser.getCurrentUser().getUsername());
                myClassObject.setTitle(bmobFile.getFilename());
                myClassObject.setUrl(bmobFile.getFileUrl());
                myClassObject.save(new SaveListener<String>() {
                    @Override
                    public void done(String s, BmobException e) {
                        if (e == null) {
                            Log.d("fragment", "上传保存对象成功");
                        } else {
                            Log.d("fragment", "上传保存对象异常" + e.getMessage());
                        }
                    }
                });

            }

            @Override
            public void onProgress(Integer value) {
                if (value - mFirstProcess >= 1) {
                    mFirstProcess = value;
                    Log.d("fragment", "触发上传进度:" + value + uploadTitle);
                    EventBus.getDefault().post(new UploadEvent(uploadTitle, R.mipmap.ic_launcher, value));
                }
            }
        });
    }

    //刷新数据成功回调
    //刷新数据失败回调
    public interface ClassCallback {
        void get();

        void getErr();

        void refresh();

        void refreshErr();
    }
}
