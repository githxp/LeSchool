package com.hxp.leschool.model.operate;

import com.hxp.leschool.model.bean.ConversationModel;
import com.hxp.leschool.view.fragment.ConversationFragment;
import com.hxp.leschool.viewmodel.ConversationViewModel;

import java.util.ArrayList;


/**
 * 会话存储类
 * Created by hxp on 17-1-22.
 */


public class ConversationModelOpt {

    public static ConversationModel mConversationModel;
    public static ArrayList<ConversationModel> mData = new ArrayList<>();
    private static ConversationCallback mConversationCallback;
    private static ConversationFragment mConversationFragment;

    public ConversationModelOpt(ConversationViewModel conversationViewModel, ConversationFragment conversationFragment) {
        mConversationCallback = conversationViewModel;
        mConversationFragment = conversationFragment;
    }

    //添加消息
    public static void addConversation(ConversationModel conversationModel) {
        String userName = conversationModel.getUserName();
        boolean isExist = false;
        for (int i = 0; i < mData.size(); i++) {
            if (userName.equals(mData.get(i).getUserName())) {
                mData.get(i).setMsg(conversationModel.getMsg());
                isExist = true;
                break;
            }
        }
        if (!isExist) {
            mData.add(conversationModel);
        }
        refresh();
    }

    //删除消息
    public static void removeConversation(ConversationModel conversationModel) {

    }

    //获取数据
    public static void getData() {

    }

    //刷新消息
    public static void refresh() {
        mConversationCallback.refresh();
    }

    //打开消息
    public static void chat(int position) {

    }

    //获取数据数量
    public static int getCount() {
        return mData.size();
    }

    //刷新数据成功回调
    public interface ConversationCallback {
        void refresh();
    }
}
