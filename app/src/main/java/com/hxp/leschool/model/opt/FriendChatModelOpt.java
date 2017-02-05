package com.hxp.leschool.model.opt;

import android.util.Log;

import com.hxp.leschool.model.bean.FriendChatModel;
import com.hxp.leschool.utils.event.ChatMsgEvent;
import com.hxp.leschool.viewmodel.FriendChatViewModel;

import java.util.ArrayList;


/**
 * Created by hxp on 17-1-22.
 */


public class FriendChatModelOpt{

    public ArrayList<FriendChatModel> mData = new ArrayList<>();
    private FriendChatModel mFriendChatModel;
    private ChatCallback mChatCallback;

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

    //获取聊天历史
    public void getData() {

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
        setMsg(chatMsgEvent.getMsg(),false);
    }
}
