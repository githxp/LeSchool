package com.hxp.leschool.utils.publish;

import com.hxp.leschool.utils.MyNormalMsgHandler.MyNormalMsgHandlerCallback;

/**
 * 聊天消息分发
 * Created by hxp on 17-2-2.
 */

public class ChatMsgPublish {

    private static MyNormalMsgHandlerCallback mMyNormalMsgHandlerCallback;

    public static void addMyNormalMsgHandlerCallback(MyNormalMsgHandlerCallback myNormalMsgHandlerCallback) {
        mMyNormalMsgHandlerCallback = myNormalMsgHandlerCallback;
    }

    public static void removeMyNormalMsgHandlerCallback() {
        mMyNormalMsgHandlerCallback = null;
    }

    public static MyNormalMsgHandlerCallback getMyNormalMsgHandlerCallback() {
        return mMyNormalMsgHandlerCallback;
    }
}
