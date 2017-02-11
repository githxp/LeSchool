package com.hxp.leschool.model.server.object;

import cn.bmob.v3.BmobObject;

/**
 * Created by hxp on 17-1-29.
 */

public class MyClassObject extends BmobObject {

    private String userName;
    private String title;
    private String url;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
