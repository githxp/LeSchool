package com.hxp.leschool.model.bean;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import com.hxp.leschool.BR;


/**
 * 最近消息类
 * Created by hxp on 17-1-22.
 */


public class ConversationModel extends BaseObservable {

    private String userName;//对话创建者的用户名
    private String msg;//消息内容
    private int avatar;////对话创建者的头像
    private int type;//消息类型，1为添加好友请求，2为聊天消息

    public ConversationModel() {
    }

    public ConversationModel(String msg, int avatar) {
        this.msg = msg;
        this.avatar = avatar;
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
    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
        notifyPropertyChanged(BR.msg);
    }

    @Bindable
    public int getAvatar() {
        return avatar;
    }

    public void setAvatar(int avatar) {
        this.avatar = avatar;
        notifyPropertyChanged(BR.avatar);
    }

    @Bindable
    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
        notifyPropertyChanged(BR.type);
    }
}
