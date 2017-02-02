package com.hxp.leschool.model.operate;

import android.content.Intent;
import android.util.Log;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVObject;
import com.avos.avoscloud.AVQuery;
import com.avos.avoscloud.AVUser;
import com.avos.avoscloud.FindCallback;
import com.hxp.leschool.R;
import com.hxp.leschool.model.bean.ConversationModel;
import com.hxp.leschool.model.bean.FriendModel;
import com.hxp.leschool.view.activity.FriendChatActivity;
import com.hxp.leschool.view.fragment.ConversationFragment;
import com.hxp.leschool.view.fragment.FriendFragment;
import com.hxp.leschool.viewmodel.ConversationViewModel;
import com.hxp.leschool.viewmodel.FriendViewModel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 * Created by hxp on 17-1-22.
 */


public class ConversationModelOpt {

    public static ConversationModel mConversationModel;
    public static ArrayList<ConversationModel> mData = new ArrayList<>();
    private static ConversationOptCallback mConversationOptCallback;
    private static ConversationFragment mConversationFragment;


    public ConversationModelOpt(ConversationViewModel conversationViewModel, ConversationFragment conversationFragment) {
        mConversationOptCallback = conversationViewModel;
        mConversationFragment = conversationFragment;
    }

    //添加消息
    public static void addConversation(ConversationModel conversationModel) {
        String userName = conversationModel.getUserName();
        boolean isExist = false;
        for (int i = 0; i < mData.size(); i++) {
            if (userName.equals(mData.get(i).getUserName())) {
                mData.get(i).setContent(conversationModel.getContent());
                isExist = true;
                break;
            }
        }
        if (!isExist){
            mData.add(conversationModel);
        }
        refreshData();
    }

    //删除消息
    public static void removeConversation(ConversationModel conversationModel) {

    }

    //获取数据
    public static void getData() {

    }

    //刷新消息
    public static void refreshData() {
        mConversationOptCallback.conversationRefreshdataSucceedCompleted();
    }

    //打开消息
    public static void chat(int position) {

    }

    //获取数据数量
    public static int getCount() {
        return mData.size();
    }

    //获取数据成功回调
    //获取数据失败回调
    //刷新数据成功回调
    //刷新数据失败回调
    public interface ConversationOptCallback {
        void conversationGetdataSucceedCompleted();

        void conversationGetdataFailedCompleted();

        void conversationRefreshdataSucceedCompleted();

        void conversationRefreshdataFailedCompleted();
    }
}
