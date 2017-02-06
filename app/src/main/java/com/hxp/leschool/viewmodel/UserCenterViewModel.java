package com.hxp.leschool.viewmodel;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.avos.avoscloud.AVUser;
import com.avos.avoscloud.im.v2.AVIMClient;
import com.avos.avoscloud.im.v2.AVIMException;
import com.avos.avoscloud.im.v2.callback.AVIMClientCallback;
import com.hxp.leschool.databinding.UsercenterAtBinding;
import com.hxp.leschool.utils.MyApplication;
import com.hxp.leschool.view.activity.LoginAndRegActivity;
import com.hxp.leschool.view.activity.UserCenterActivity;


/**
 * 用户中心
 * Created by hxp on 17-1-22.
 */


public class UserCenterViewModel {

    private UserCenterActivity mUserCenterActivity;
    private UsercenterAtBinding mUsercenterAtBinding;

    public UserCenterViewModel(UserCenterActivity userCenterActivity, UsercenterAtBinding usercenterAtBinding) {

        Log.d("fragment", "MicroblogSingleChatViewModel创建");
        mUserCenterActivity = userCenterActivity;
        mUsercenterAtBinding = usercenterAtBinding;

        mUsercenterAtBinding.setMUserCenterViewModel(this);
    }

    public void btn_UserCenter_LogoutClicked(View view) {
        AVUser.getCurrentUser().logOut();
        if (AVUser.getCurrentUser() == null) {
            Toast.makeText(mUserCenterActivity, "退出登陆成功", Toast.LENGTH_SHORT).show();
            MyApplication.getInstance().getAVIMClient().close(new AVIMClientCallback() {
                @Override
                public void done(AVIMClient avimClient, AVIMException e) {
                    Toast.makeText(mUserCenterActivity, "当前用户已与服务器断开连接", Toast.LENGTH_SHORT).show();
                }
            });
            mUserCenterActivity.startActivity(new Intent(mUserCenterActivity, LoginAndRegActivity.class));
            mUserCenterActivity.finish();
            MyApplication.getInstance().getMainActivity().finish();
        } else {
            Toast.makeText(mUserCenterActivity, "退出登陆失败", Toast.LENGTH_SHORT).show();
        }
    }
}
