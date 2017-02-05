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
    private String lastTime;//最近一次会话时间
    private int num;//未读消息数量

    public ConversationModel() {
    }

    public ConversationModel(String userName, int avatar, String msg, String lastTime, int num, int type) {
        this.userName = userName;
        this.avatar = avatar;
        this.msg = msg;
        this.lastTime = lastTime;
        this.num = num;
        this.type = type;
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
    public String getLastTime() {
        return lastTime;
    }

    public void setLastTime(String lastTime) {
        this.lastTime = lastTime;
        notifyPropertyChanged(BR.lastTime);
    }

    @Bindable
    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
        notifyPropertyChanged(BR.num);
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
