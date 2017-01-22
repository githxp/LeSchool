package com.hxp.leschool.view.fragment;

import android.app.Fragment;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.avos.avoscloud.im.v2.AVIMMessage;
import com.avos.avoscloud.im.v2.AVIMMessageHandler;
import com.avos.avoscloud.im.v2.AVIMMessageManager;
import com.hxp.leschool.R;
import com.hxp.leschool.databinding.MicroblogsinglechatFmBinding;
import com.hxp.leschool.viewmodel.MicroblogSingleChatViewModel;

/**
 * Created by hxp on 17-1-22.
 */

public class MicroblogSingleChatFragment extends Fragment {

    private String mUserName;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        MicroblogsinglechatFmBinding microblogsinglechatFmBinding = DataBindingUtil.inflate(inflater, R.layout.microblogsinglechat_fm, container, false);
        Log.d("fragment", "好友校验1：" + mUserName);
        new MicroblogSingleChatViewModel(this, microblogsinglechatFmBinding, mUserName);
        Log.d("Fragment生命周期管理", "onCreateView()触发-MicroblogSingleChat");
        return microblogsinglechatFmBinding.getRoot();
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.d("Fragment生命周期管理", "onPause()触发-MicroblogSingleChat");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d("Fragment生命周期管理", "onResume()触发-MicroblogSingleChat");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d("Fragment生命周期管理", "onDestroy()触发-MicroblogSingleChat");
    }

    public void setUserName(String userName) {
        mUserName = userName;
    }
}
