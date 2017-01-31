package com.hxp.leschool.viewmodel;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.hxp.leschool.databinding.MineuserinfoFmBinding;
import com.hxp.leschool.model.operate.MineUserInfoModelOpt;
import com.hxp.leschool.model.server.user.MyUser;
import com.hxp.leschool.utils.IMMLeaks;
import com.hxp.leschool.utils.MyApplication;
import com.hxp.leschool.utils.MyApplication.LoginAndRegCallback;
import com.hxp.leschool.utils.RefreshEvent;
import com.hxp.leschool.view.activity.LoginAndRegActivity;
import com.hxp.leschool.view.activity.MainActivity;
import com.hxp.leschool.view.fragment.MineUserInfoFragment;

import org.greenrobot.eventbus.EventBus;

import cn.bmob.newim.BmobIM;
import cn.bmob.newim.core.ConnectionStatus;
import cn.bmob.newim.listener.ConnectListener;
import cn.bmob.newim.listener.ConnectStatusChangeListener;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;


/**
 * Created by hxp on 17-1-13.
 */


public class MineUserInfoViewModel implements LoginAndRegCallback {

    public MineUserInfoModelOpt mMineUserInfoModelOpt;
    private MineUserInfoFragment mMineUserInfoFragment;
    private MineuserinfoFmBinding mMineuserinfoFmBinding;

    public MineUserInfoViewModel(MineUserInfoFragment mineUserInfoFragment, MineuserinfoFmBinding mineuserinfoFmBinding) {

        mMineUserInfoFragment = mineUserInfoFragment;
        mMineuserinfoFmBinding = mineuserinfoFmBinding;

        mMineUserInfoModelOpt = new MineUserInfoModelOpt();
        mMineUserInfoModelOpt.getData();
        Log.d("fragment", "用户信息数据opt获取数据");

        mMineuserinfoFmBinding.setMMineUserInfoViewModel(this);

        MyApplication.getInstance().setMineUserInfoViewModel(this);

        mMineuserinfoFmBinding.llMineUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mMineUserInfoFragment.getActivity(), LoginAndRegActivity.class);
                mMineUserInfoFragment.getActivity().startActivity(intent);
            }
        });
    }

    @Override
    public void loginSucceedCallback() {
        Log.d("fragment", "登陆成功回调接收方");
        Toast.makeText(mMineUserInfoFragment.getActivity(), "用户已登录", Toast.LENGTH_SHORT).show();
        mMineUserInfoModelOpt.getData();

        MyUser myUser = BmobUser.getCurrentUser(MyApplication.getInstance(), MyUser.class);
        BmobIM.connect(myUser.getObjectId(), new ConnectListener() {
            @Override
            public void done(String uid, BmobException e) {
                if (e == null) {
                    Log.d("fragment", "connect success");
                    //服务器连接成功就发送一个更新事件，同步更新会话及主页的小红点
                    EventBus.getDefault().post(new RefreshEvent());
                } else {
                    Log.d("fragment", e.getErrorCode() + "/" + e.getMessage());
                }
            }
        });
        //监听连接状态，也可通过BmobIM.getInstance().getCurrentStatus()来获取当前的长连接状态
        BmobIM.getInstance().setOnConnectStatusChangeListener(new ConnectStatusChangeListener() {
            @Override
            public void onChange(ConnectionStatus status) {
                Log.d("fragment", "" + status.getMsg());
            }
        });
        //解决leancanary提示InputMethodManager内存泄露的问题
        IMMLeaks.fixFocusedViewLeak(MyApplication.getInstance());
    }

    @Override
    public void logoutSucceedCallback() {
        Log.d("fragment", "注销成功回调接收方");
        Toast.makeText(mMineUserInfoFragment.getActivity(), "用户已注销", Toast.LENGTH_SHORT).show();
        mMineUserInfoModelOpt.getData();

        BmobIM.getInstance().disConnect();
        Log.d("fragment", "connect disconnect");
    }
}
