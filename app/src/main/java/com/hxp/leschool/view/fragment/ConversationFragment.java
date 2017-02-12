package com.hxp.leschool.view.fragment;


import android.app.Fragment;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hxp.leschool.R;
import com.hxp.leschool.databinding.ConversationFmBinding;
import com.hxp.leschool.utils.event.AddConversationEvent;
import com.hxp.leschool.utils.event.ChangeConversationEvent;
import com.hxp.leschool.utils.event.DelConversationEvent;
import com.hxp.leschool.viewmodel.ConversationViewModel;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;


/**
 * Created by hxp on 17-1-12.
 */


public class ConversationFragment extends Fragment {

    private ConversationViewModel mConversationViewModel;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ConversationFmBinding conversationFmBinding = DataBindingUtil.inflate(inflater, R.layout.conversation_fm, container, false);
        mConversationViewModel = new ConversationViewModel(this, conversationFmBinding);
        Log.d("Fragment生命周期管理", "onCreateView()触发-ConversationFragment");
        return conversationFmBinding.getRoot();
    }

    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
        Log.d("Fragment", "onPause()触发-ConversationFragment");
        Log.d("Fragment", "EventBus注册");
    }

    @Override
    public void onStop() {
        EventBus.getDefault().unregister(this);
        super.onStop();
        Log.d("Fragment", "onPause()触发-ConversationFragment");
        Log.d("Fragment", "EventBus解除注册");
    }


    @Override
    public void onPause() {
        super.onPause();
        Log.d("Fragment生命周期管理", "onPause()触发-ConversationFragment");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d("Fragment生命周期管理", "onResume()触发-ConversationFragment");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d("Fragment生命周期管理", "onDestroy()触发-ConversationFragment");
    }

    @Subscribe(sticky = true)
    public void handleAddConversationEvent(AddConversationEvent addConversationEvent) {
        Log.d("fragment", "新消息处理-添加" + "conversationEvent");
        mConversationViewModel.handleAddConversationEvent(addConversationEvent);
    }

    @Subscribe(sticky = true)
    public void handleChangeConversationEvent(ChangeConversationEvent changeConversationEvent) {
        Log.d("fragment", "新消息处理-修改" + "conversationEvent");
        mConversationViewModel.handleChangeConversationEvent(changeConversationEvent);
    }

    @Subscribe(sticky = true)
    public void handleDelConversationEvent(DelConversationEvent delConversationEvent) {
        Log.d("fragment", "消息处理-删除" + "conversationEvent");
        mConversationViewModel.handleDelConversationEvent(delConversationEvent);
    }
}
