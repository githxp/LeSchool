package com.hxp.leschool.model.bean;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import com.hxp.leschool.BR;


/**
 * Created by hxp on 17-1-22.
 */


public class FriendReqModel extends BaseObservable {

    private String userName;//对话创建者的用户名
    private String content;//消息内容
    private int avatar;////对话创建者的头像

    public FriendReqModel() {
    }

    public FriendReqModel(String userName, int userPicture) {
        this.userName = userName;
        this.avatar = userPicture;
    }

    @Bindable
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
        notifyPropertyChanged(BR.userName);
    }

    @Bindable
    public int getAvatar() {
        return avatar;
    }

    public void setAvatar(int userPicture) {
        this.avatar = userPicture;
        notifyPropertyChanged(BR.userPicture);
    }

    @Bindable
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
        notifyPropertyChanged(BR.content);
    }
}
