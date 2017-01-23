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
    private FriendChatModel mMicroblogSingleChatModel;
    private FriendChatSendMsgCallback mFriendChatSendMsgCallback;

    public FriendChatModelOpt(FriendChatViewModel friendChatViewModel) {
        mFriendChatSendMsgCallback = friendChatViewModel;
    }

    public void setData(String message) {
        mMicroblogSingleChatModel = new FriendChatModel();
        mMicroblogSingleChatModel.setMessage(message);
        mMicroblogSingleChatModel.setToSend(true);
        mData.add(mMicroblogSingleChatModel);
        Log.d("fragment", "MicroblogSingleChatModelOpt消息发送成功回调发送方");
        Log.d("fragment", "MicroblogSingleChatModelOpt消息发送数量为：" + mData.size());
        mFriendChatSendMsgCallback.friendChatSendMsgCompleted();
    }

    //获取聊天历史
    public void getData() {
//        AVIMClient aVIMClient = AVIMClient.getInstance(AVUser.getCurrentUser().getUsername());
//        aVIMClient.open(new AVIMClientCallback() {
//            @Override
//            public void done(AVIMClient client, AVIMException e) {
//                if (e == null) {
//                    AVIMConversation conv = client.getConversation("551260efe4b01608686c3e0f");
//                    conv.queryMessages(new AVIMMessagesQueryCallback() {
//                        @Override
//                        public void done(List<AVIMMessage> messages, AVIMException e) {
//                            if (e == null) {
//
//                            }
//                        }
//                    });
//                }
//            }
//        });
    }

    public int getCount() {
        return mData.size();
    }

    public interface FriendChatSendMsgCallback {
        void friendChatSendMsgCompleted();
    }
}
