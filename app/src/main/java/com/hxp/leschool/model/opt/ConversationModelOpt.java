package com.hxp.leschool.model.opt;

import android.util.Log;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVObject;
import com.avos.avoscloud.AVQuery;
import com.avos.avoscloud.AVUser;
import com.avos.avoscloud.FindCallback;
import com.hxp.leschool.R;
import com.hxp.leschool.model.bean.ConversationModel;
import com.hxp.leschool.model.db.bean.ConversationBean;
import com.hxp.leschool.model.db.bean.opt.ConversationBeanOpt;
import com.hxp.leschool.utils.event.AddConversationEvent;
import com.hxp.leschool.utils.event.ChangeConversationEvent;
import com.hxp.leschool.utils.event.DelConversationEvent;
import com.hxp.leschool.view.fragment.ConversationFragment;
import com.hxp.leschool.viewmodel.ConversationViewModel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 * 会话逻辑操作类
 * Created by hxp on 17-1-22.
 */


public class ConversationModelOpt {

    public ConversationModel mConversationModel;
    public ArrayList<ConversationModel> mData = new ArrayList<>();
    private ConversationCallback mConversationCallback;
    private ConversationFragment mConversationFragment;
    private AVQuery<AVObject> avQuery;
    private int mCount = 0;

    public ConversationModelOpt(ConversationViewModel conversationViewModel, ConversationFragment conversationFragment) {
        mConversationCallback = conversationViewModel;
        mConversationFragment = conversationFragment;
    }

    //获取数据
    public void get() {
        ArrayList<ConversationBean> data = ConversationBeanOpt.getInstance().get();
        mData.clear();
        for (int i = 0; i < data.size(); i++) {
            mConversationModel = new ConversationModel();
            mConversationModel.setUserName(data.get(i).getUserName());
            mConversationModel.setMsg(data.get(i).getMsg());
            mConversationModel.setType(data.get(i).getType());
            mConversationModel.setLastTime(data.get(i).getLastTime());
            mConversationModel.setNum(data.get(i).getNum());
            mData.add(mConversationModel);
        }
        getAvatar();
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
                                Log.d("fragment", "ConversationModellOpt数据获取成功回调发送方");
                                mConversationCallback.get();
                            }
                        } else {
                            Log.d("fragment", "头像获取数量异常");
                        }
                    } else {
                        Log.d("fragment", "头像获取失败");
                    }
                }
            });
        }
    }

    //刷新数据
    public void refresh() {
        ArrayList<ConversationBean> data = ConversationBeanOpt.getInstance().get();
        mData.clear();
        for (int i = 0; i < data.size(); i++) {
            mConversationModel = new ConversationModel();
            mConversationModel.setUserName(data.get(i).getUserName());
            mConversationModel.setMsg(data.get(i).getMsg());
            mConversationModel.setType(data.get(i).getType());
            mConversationModel.setLastTime(data.get(i).getLastTime());
            mConversationModel.setNum(data.get(i).getNum());
            mData.add(mConversationModel);
        }
        refreshAvatar();
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
                                Log.d("fragment", "ConversationModellOpt数据刷新成功回调发送方");
                                mConversationCallback.refresh();
                            }
                        } else {
                            Log.d("fragment", "头像刷新数量异常");
                        }
                    } else {
                        Log.d("fragment", "头像刷新失败");
                    }
                }
            });
        }
    }

    //获取数据数量
    public int getCount() {
        return mData.size();
    }

    //刷新数据成功回调
    public interface ConversationCallback {
        void get();

        void refresh();
    }

    public void handleAddConversationEvent(AddConversationEvent addConversationEvent) {
        Log.d("fragment", "ConversationModelOpt-添加 正在处理");
        get();
    }

    public void handleChangeConversationEvent(ChangeConversationEvent changeConversationEvent) {
        Log.d("fragment", "ConversationModelOpt-修改 正在处理");
        get();
    }

    public void handleDelConversationEvent(DelConversationEvent delConversationEvent) {
        Log.d("fragment", "ConversationModelOpt-删除 正在处理");
    }
}
