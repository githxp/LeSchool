package com.hxp.leschool.utils.event;

/**
 * 即时消息
 * Created by hxp on 17-2-3.
 */

public class NewMsgEvent {

    private String msg;

    public NewMsgEvent() {
    }

    public NewMsgEvent(String msg) {
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
