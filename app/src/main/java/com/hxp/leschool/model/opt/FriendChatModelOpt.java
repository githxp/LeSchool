package com.hxp.leschool.model.opt;

import android.util.Log;

import com.avos.avoscloud.AVUser;
import com.avos.avoscloud.im.v2.AVIMConversation;
import com.avos.avoscloud.im.v2.AVIMException;
import com.avos.avoscloud.im.v2.AVIMMessage;
import com.avos.avoscloud.im.v2.callback.AVIMMessagesQueryCallback;
import com.avos.avoscloud.im.v2.messages.AVIMTextMessage;
import com.hxp.leschool.model.bean.FriendChatModel;
import com.hxp.leschool.utils.event.ChatMsgEvent;
import com.hxp.leschool.viewmodel.FriendChatViewModel;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by hxp on 17-1-22.
 */


public class FriendChatModelOpt {

    public ArrayList<FriendChatModel> mData = new ArrayList<>();
    private FriendChatModel mFriendChatModel;
    private ChatCallback mChatCallback;
    private String mOldMsgID = "";
    private long mTimeStamp;

    public FriendChatModelOpt(FriendChatViewModel friendChatViewModel) {
        mChatCallback = friendChatViewModel;
        Log.d("fragment", "mMyNormalMsgHandlerCallback添加内存地址：" + this.toString());
    }

    public void setMsg(String msg, boolean isSend) {
        mFriendChatModel = new FriendChatModel();
        mFriendChatModel.setMsg(msg);
        mFriendChatModel.setSend(isSend);
        mData.add(mFriendChatModel);
        Log.d("fragment", "MicroblogSingleChatModelOpt消息发送成功回调发送方");
        Log.d("fragment", "MicroblogSingleChatModelOpt消息发送数量为：" + mData.size());
        mChatCallback.refresh();
    }

    public void setMsg(ArrayList<FriendChatModel> data) {
        mData.addAll(0, data);
        Log.d("fragment", "MicroblogSingleChatModelOpt消息发送成功回调发送方-list");
        Log.d("fragment", "MicroblogSingleChatModelOpt消息发送数量为-list：" + mData.size());
        mChatCallback.refresh();
    }

    //获取聊天记录
    public void get(AVIMConversation avimConversation) {
        if (mOldMsgID.equals("")) {
            avimConversation.queryMessages(10, new AVIMMessagesQueryCallback() {
                @Override
                public void done(List<AVIMMessage> list, AVIMException e) {
                    Log.d("fragment", "查找聊天记录num:" + list.size());
                    if (list.size() > 0) {
                        mOldMsgID = list.get(0).getMessageId();
                        mTimeStamp = list.get(0).getTimestamp();
                        for (int i = 0; i < list.size(); i++) {
                            if (list.get(i) instanceof AVIMTextMessage) {
                                if (list.get(i).getFrom().equals(AVUser.getCurrentUser().getUsername())) {
                                    setMsg(((AVIMTextMessage) list.get(i)).getText(), true);
                                    Log.d("fragment", "发送" + list.get(i).getFrom());
                                } else {
                                    setMsg(((AVIMTextMessage) list.get(i)).getText(), false);
                                    Log.d("fragment", "接收" + list.get(i).getFrom());
                                }
                            }
                        }
                    } else {
                        mChatCallback.refresh();
                        Log.d("fragment", "未找到聊天记录");
                    }
                }
            });
        } else {
            avimConversation.queryMessages(mOldMsgID, mTimeStamp, 5, new AVIMMessagesQueryCallback() {
                @Override
                public void done(List<AVIMMessage> list, AVIMException e) {
                    Log.d("fragment", "分页查找聊天记录num:" + list.size());
                    if (list.size() > 0) {
                        mOldMsgID = list.get(0).getMessageId();
                        mTimeStamp = list.get(0).getTimestamp();
                        ArrayList<FriendChatModel> data = new ArrayList<FriendChatModel>();
                        for (int i = 0; i < list.size(); i++) {
                            if (list.get(i) instanceof AVIMTextMessage) {
                                FriendChatModel friendChatModel = new FriendChatModel();
                                friendChatModel.setMsg(((AVIMTextMessage) list.get(i)).getText());
                                if (list.get(i).getFrom().equals(AVUser.getCurrentUser().getUsername())) {
                                    friendChatModel.setSend(true);
                                    Log.d("fragment", "分页发送" + list.get(i).getFrom());
                                } else {
                                    friendChatModel.setSend(false);
                                    Log.d("fragment", "分页接收" + list.get(i).getFrom());
                                }
                                data.add(friendChatModel);
                            }
                        }
                        setMsg(data);
                    } else {
                        mChatCallback.refresh();
                        Log.d("fragment", "未找到聊天记录");
                    }
                }
            });
        }
    }

    public int getCount() {
        return mData.size();
    }

    public interface ChatCallback {
        void refresh();
    }

    //处理新消息
    public void handleChatMsgEvent(ChatMsgEvent chatMsgEvent) {
        Log.d("fragment", "mFriendChatModelOpt正在处理" + chatMsgEvent.getMsg());
        setMsg(chatMsgEvent.getMsg(), false);
    }
}
