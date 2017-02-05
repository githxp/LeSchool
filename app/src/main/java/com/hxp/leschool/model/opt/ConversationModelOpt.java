package com.hxp.leschool.model.opt;

import android.util.Log;

import com.hxp.leschool.R;
import com.hxp.leschool.model.bean.ConversationModel;
import com.hxp.leschool.model.db.bean.ConversationBean;
import com.hxp.leschool.model.db.bean.opt.ConversationBeanOpt;
import com.hxp.leschool.utils.event.AddConversationEvent;
import com.hxp.leschool.utils.event.ChangeConversationEvent;
import com.hxp.leschool.view.fragment.ConversationFragment;
import com.hxp.leschool.viewmodel.ConversationViewModel;

import java.util.ArrayList;


/**
 * 会话逻辑操作类
 * Created by hxp on 17-1-22.
 */


public class ConversationModelOpt {

    public ConversationModel mConversationModel;
    public ArrayList<ConversationModel> mData = new ArrayList<>();
    private ConversationCallback mConversationCallback;
    private ConversationFragment mConversationFragment;

    public ConversationModelOpt(ConversationViewModel conversationViewModel, ConversationFragment conversationFragment) {
        mConversationCallback = conversationViewModel;
        mConversationFragment = conversationFragment;
    }

    //删除消息
    public void removeConversation(ConversationModel conversationModel) {

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
            mConversationModel.setAvatar(R.mipmap.ic_launcher);
            mConversationModel.setLastTime(data.get(i).getLastTime());
            mConversationModel.setNum(data.get(i).getNum());
            mData.add(mConversationModel);
        }
        mConversationCallback.get();
    }

    //获取数据数量
    public int getCount() {
        return mData.size();
    }

    //刷新数据成功回调
    public interface ConversationCallback {
        void get();
    }

    public void handleAddConversationEvent(AddConversationEvent addConversationEvent) {
        Log.d("fragment", "ConversationModelOpt正在处理");
        get();
    }

    public void handleChangeConversationEvent(ChangeConversationEvent changeConversationEvent) {
        Log.d("fragment", "ConversationModelOpt正在处理");
        get();
    }
}
