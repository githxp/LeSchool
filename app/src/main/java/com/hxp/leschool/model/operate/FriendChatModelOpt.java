package com.hxp.leschool.model.operate;

import android.util.Log;

import com.hxp.leschool.model.bean.FriendChatModel;
import com.hxp.leschool.viewmodel.FriendChatViewModel;

import java.util.ArrayList;


/**
 * Created by hxp on 17-1-22.
 */


public class FriendChatModelOpt {

    public ArrayList<FriendChatModel> mData = new ArrayList<>();
    private FriendChatModel mFriendChatModel;
    private FriendChatSendMsgCallback mFriendChatSendMsgCallback;

    public FriendChatModelOpt(FriendChatViewModel friendChatViewModel) {
        mFriendChatSendMsgCallback = friendChatViewModel;
    }

    public void setData(String message) {
        mFriendChatModel = new FriendChatModel();
        mFriendChatModel.setMessage(message);
        mFriendChatModel.setToSend(true);
        mData.add(mFriendChatModel);
        Log.d("fragment", "MicroblogSingleChatModelOpt消息发送成功回调发送方");
        Log.d("fragment", "MicroblogSingleChatModelOpt消息发送数量为：" + mData.size());
        mFriendChatSendMsgCallback.friendChatSendMsgCompleted();
    }

    //获取聊天历史
    public void getData() {

    }

    public int getCount() {
        return mData.size();
    }

    public interface FriendChatSendMsgCallback {
        void friendChatSendMsgCompleted();
    }
}
