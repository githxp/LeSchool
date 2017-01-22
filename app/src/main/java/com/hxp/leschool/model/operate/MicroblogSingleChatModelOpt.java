package com.hxp.leschool.model.operate;

import android.util.Log;

import com.avos.avoscloud.AVUser;
import com.avos.avoscloud.im.v2.AVIMClient;
import com.avos.avoscloud.im.v2.AVIMConversation;
import com.avos.avoscloud.im.v2.AVIMException;
import com.avos.avoscloud.im.v2.AVIMMessage;
import com.avos.avoscloud.im.v2.callback.AVIMClientCallback;
import com.avos.avoscloud.im.v2.callback.AVIMMessagesQueryCallback;
import com.hxp.leschool.model.bean.ClassModel;
import com.hxp.leschool.model.bean.MicroblogSingleChatModel;
import com.hxp.leschool.viewmodel.MicroblogSingleChatViewModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hxp on 17-1-22.
 */

public class MicroblogSingleChatModelOpt {

    public ArrayList<MicroblogSingleChatModel> mData = new ArrayList<>();
    private MicroblogSingleChatModel mMicroblogSingleChatModel;
    private MicroblogSingleChatSetdataCallback mMicroblogSingleChatSetdataCallback;

    public MicroblogSingleChatModelOpt(MicroblogSingleChatViewModel microblogSingleChatViewModel) {
        mMicroblogSingleChatSetdataCallback = microblogSingleChatViewModel;
    }

    public void setData(String message) {
        mMicroblogSingleChatModel = new MicroblogSingleChatModel();
        mMicroblogSingleChatModel.setMessage(message);
        mMicroblogSingleChatModel.setToSend(true);
        mData.add(mMicroblogSingleChatModel);
        Log.d("fragment", "MicroblogSingleChatModelOpt消息发送成功回调发送方");
        Log.d("fragment", "MicroblogSingleChatModelOpt消息发送数量为：" + mData.size());
        mMicroblogSingleChatSetdataCallback.microblogSingleChatSetdataCompleted();
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

    public interface MicroblogSingleChatSetdataCallback {
        void microblogSingleChatSetdataCompleted();
    }
}
