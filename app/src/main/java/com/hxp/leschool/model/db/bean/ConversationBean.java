package com.hxp.leschool.model.db.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Unique;

/**
 * 会话实体类
 * Created by hxp on 17-2-5.
 */

@Entity
public class ConversationBean {

    @Id(autoincrement = true)//ID自增长
    private Long id;
    @Property(nameInDb = "userName")//会话用户名
    @Unique
    private String userName;
    @Property(nameInDb = "avatar")//会话用户头像
    private String avatar;
    @Property(nameInDb = "msg")//会话最近一条消息
    private String msg;
    @Property(nameInDb = "lastTime")//会话最近一次时间
    private String lastTime;
    @Property(nameInDb = "num")//未读会话数量
    private int num;
    @Property(nameInDb = "type")//消息类型
    private int type;
    @Generated(hash = 877576375)
    public ConversationBean(Long id, String userName, String avatar, String msg,
            String lastTime, int num, int type) {
        this.id = id;
        this.userName = userName;
        this.avatar = avatar;
        this.msg = msg;
        this.lastTime = lastTime;
        this.num = num;
        this.type = type;
    }
    @Generated(hash = 1757341872)
    public ConversationBean() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getUserName() {
        return this.userName;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }
    public String getAvatar() {
        return this.avatar;
    }
    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }
    public String getMsg() {
        return this.msg;
    }
    public void setMsg(String msg) {
        this.msg = msg;
    }
    public String getLastTime() {
        return this.lastTime;
    }
    public void setLastTime(String lastTime) {
        this.lastTime = lastTime;
    }
    public int getNum() {
        return this.num;
    }
    public void setNum(int num) {
        this.num = num;
    }
    public int getType() {
        return this.type;
    }
    public void setType(int type) {
        this.type = type;
    }
}
