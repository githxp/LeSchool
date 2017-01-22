package com.hxp.leschool.model.operate;

import android.util.Log;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVObject;
import com.avos.avoscloud.AVQuery;
import com.avos.avoscloud.FindCallback;
import com.hxp.leschool.R;
import com.hxp.leschool.model.bean.ClassModel;
import com.hxp.leschool.model.bean.MicroblogContactsModel;
import com.hxp.leschool.utils.MyApplication;
import com.hxp.leschool.viewmodel.MicroblogViewModel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by hxp on 17-1-22.
 */

public class MicroblogContactsModelOpt {

    public MicroblogContactsModel microblogContactsModel;
    public ArrayList<MicroblogContactsModel> mData = new ArrayList<>();
    private AVQuery<AVObject> avQuery = new AVQuery<>("UserInfo");
    private MicroblogContactsOptCallback mMicroblogContactsOptCallback;

    public MicroblogContactsModelOpt(MicroblogViewModel microblogViewModel) {
        mMicroblogContactsOptCallback = microblogViewModel;
    }

    //获取数据
    public void getData() {
        avQuery.selectKeys(Arrays.asList("useNname", "userPassword"));
        avQuery.orderByDescending("createdAt");
        avQuery.findInBackground(new FindCallback<AVObject>() {
            @Override
            public void done(List<AVObject> list, AVException e) {
                if (e == null) {
                    mData.clear();
                    Log.d("fragment", "mdata已清除-ClassModelOpt");
                    for (int i = 0; i < list.size(); i++) {
                        microblogContactsModel = new MicroblogContactsModel();
                        microblogContactsModel.setUserName(list.get(i).getString("useNname"));
                        microblogContactsModel.setUserPicture(R.mipmap.ic_launcher);
                        mData.add(microblogContactsModel);
                    }
                    for (int i = 0; i < mData.size(); i++) {
                        Log.d("fragment", "ClassModelOpt获得数据：" + mData.get(i).getUserName());
                    }
                    Log.d("fragment", "mdata.size:" + mData.size() + "MicroblogContactsModelOpt");
                    Log.d("fragment", "数据获取成功回调发送方-MicroblogContactsModelOpt");
                    mMicroblogContactsOptCallback.microblogGetdataSucceedCompleted();
                } else {
                    Log.d("fragment", "数据获取失败回调发送方-MicroblogContactsModelOpt");
                    mMicroblogContactsOptCallback.microblogGetdataFailedCompleted();
                }
            }
        });
    }

    //刷新数据
    public void refreshData() {
        avQuery.selectKeys(Arrays.asList("useNname"));
        avQuery.orderByDescending("createdAt");
        avQuery.findInBackground(new FindCallback<AVObject>() {
            @Override
            public void done(List<AVObject> list, AVException e) {
                if (e == null) {
                    mData.clear();
                    Log.d("fragment", "mdata已清除-ClassModelOpt");
                    for (int i = 0; i < list.size(); i++) {
                        microblogContactsModel = new MicroblogContactsModel();
                        microblogContactsModel.setUserName(list.get(i).getString("useNname"));
                        microblogContactsModel.setUserPicture(R.mipmap.ic_launcher);
                        mData.add(microblogContactsModel);
                    }
                    for (int i = 0; i < mData.size(); i++) {
                        Log.d("fragment", "ClassModelOpt刷新数据：" + mData.get(i).getUserName());
                    }
                    Log.d("fragment", "mdata.size:" + mData.size() + "MicroblogContactsModelOpt");
                    Log.d("fragment", "数据刷新成功回调发送方-MicroblogContactsModelOpt");
                    mMicroblogContactsOptCallback.microblogRefreshdataSucceedCompleted();
                } else {
                    Log.d("fragment", "数据刷新失败回调发送方-MicroblogContactsModelOpt");
                    mMicroblogContactsOptCallback.microblogRefreshdataFailedCompleted();
                }
            }
        });
    }

    //打开聊天
    public void openChat(int position) {
        String userName = mData.get(position).getUserName();
        Log.d("fragment", "聊天好友是：" + userName);
        MyApplication.getInstance().getMicroblogSingleChatCallback().microblogSingleChatCallback(userName);
    }

    //获取数据数量
    public int getCount() {
        return mData.size();
    }

    //获取数据成功回调
    //获取数据失败回调
    //刷新数据成功回调
    //刷新数据失败回调
    public interface MicroblogContactsOptCallback {
        void microblogGetdataSucceedCompleted();

        void microblogGetdataFailedCompleted();

        void microblogRefreshdataSucceedCompleted();

        void microblogRefreshdataFailedCompleted();
    }
}
