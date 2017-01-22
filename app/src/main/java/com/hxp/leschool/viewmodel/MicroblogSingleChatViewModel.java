package com.hxp.leschool.viewmodel;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.avos.avoscloud.AVUser;
import com.avos.avoscloud.im.v2.AVIMClient;
import com.avos.avoscloud.im.v2.AVIMConversation;
import com.avos.avoscloud.im.v2.AVIMException;
import com.avos.avoscloud.im.v2.callback.AVIMClientCallback;
import com.avos.avoscloud.im.v2.callback.AVIMConversationCallback;
import com.avos.avoscloud.im.v2.callback.AVIMConversationCreatedCallback;
import com.avos.avoscloud.im.v2.messages.AVIMTextMessage;
import com.hxp.leschool.adapter.MicroblogSingleChatAdapter;
import com.hxp.leschool.databinding.MicroblogsinglechatFmBinding;
import com.hxp.leschool.model.operate.MicroblogSingleChatModelOpt;
import com.hxp.leschool.view.fragment.MicroblogSingleChatFragment;
import com.hxp.leschool.model.operate.MicroblogSingleChatModelOpt.MicroblogSingleChatSetdataCallback;

import java.util.Arrays;

/**
 * Created by hxp on 17-1-22.
 */

public class MicroblogSingleChatViewModel implements MicroblogSingleChatSetdataCallback {

    public MicroblogSingleChatModelOpt mMicroblogSingleChatModelOpt;
    private MicroblogSingleChatFragment mMicroblogSingleChatFragment;
    private MicroblogsinglechatFmBinding mMicroblogsinglechatFmBinding;
    private MicroblogSingleChatAdapter mMicroblogSingleChatAdapter;
    private String mUserName;

    public MicroblogSingleChatViewModel(MicroblogSingleChatFragment microblogSingleChatFragment, MicroblogsinglechatFmBinding microblogsinglechatFmBinding, String userName) {

        Log.d("fragment", "MicroblogSingleChatViewModel创建");
        mMicroblogSingleChatFragment = microblogSingleChatFragment;
        mMicroblogsinglechatFmBinding = microblogsinglechatFmBinding;
        mUserName = userName;

        mMicroblogSingleChatModelOpt = new MicroblogSingleChatModelOpt(this);
        mMicroblogSingleChatAdapter = new MicroblogSingleChatAdapter(this);

        mMicroblogsinglechatFmBinding.setMMicroblogSingleChatViewModel(this);

        mMicroblogsinglechatFmBinding.rvMicroblogSingleChatMessage.setLayoutManager(new LinearLayoutManager(mMicroblogSingleChatFragment.getActivity(), LinearLayoutManager.VERTICAL, false));
        mMicroblogsinglechatFmBinding.rvMicroblogSingleChatMessage.setAdapter(mMicroblogSingleChatAdapter);

        mMicroblogSingleChatAdapter.setOnItemClickListener(new MicroblogSingleChatAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                //聊天消息列表点击事件
            }
        });
    }

    public void onMicroblogSingleChat_Layout_SendMessageClicked(View view) {
        final String message = mMicroblogsinglechatFmBinding.etMicroblogSingleChatMessage.getText().toString();
        if (!message.equals("")) {
            Toast.makeText(mMicroblogSingleChatFragment.getActivity(), "发送消息", Toast.LENGTH_SHORT).show();
            AVIMClient aVIMClient = AVIMClient.getInstance(AVUser.getCurrentUser().getUsername());
            aVIMClient.open(new AVIMClientCallback() {
                @Override
                public void done(AVIMClient client, AVIMException e) {
                    if (e == null) {
                        Log.d("fragment", "好友校验2：" + mUserName);
                        client.createConversation(Arrays.asList(mUserName), null, new AVIMConversationCreatedCallback() {
                            @Override
                            public void done(AVIMConversation conversation, AVIMException e) {
                                if (e == null) {
                                    AVIMTextMessage aVIMTextMessage = new AVIMTextMessage();
                                    aVIMTextMessage.setText(message);
                                    conversation.sendMessage(aVIMTextMessage, new AVIMConversationCallback() {
                                        @Override
                                        public void done(AVIMException e) {
                                            if (e == null) {
                                                mMicroblogSingleChatModelOpt.setData(message);
                                                Log.d("fragment", "发送成功！");
                                            } else {
                                                Toast.makeText(mMicroblogSingleChatFragment.getActivity(), "消息发送失败：" + e.getMessage(), Toast.LENGTH_SHORT).show();
                                            }
                                        }
                                    });
                                } else {
                                    Toast.makeText(mMicroblogSingleChatFragment.getActivity(), "创建对话错误：" + e.getMessage(), Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                    } else {
                        Toast.makeText(mMicroblogSingleChatFragment.getActivity(), "服务器链接错误：" + e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }

    @Override
    public void microblogSingleChatSetdataCompleted() {
        mMicroblogSingleChatAdapter.notifyDataSetChanged();
        Log.d("fragment", "MicroblogSingleChatModelOpt消息发送成功回调接收方");
    }
}
