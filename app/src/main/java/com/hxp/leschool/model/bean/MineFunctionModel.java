package com.hxp.leschool.model.bean;

/**
 * Created by hxp on 17-1-13.
 */


public class MineFunctionModel {

    private String title;
    private int avatar;

    public MineFunctionModel(String title, int avatar) {
        this.title = title;
        this.avatar = avatar;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getAvatar() {
        return avatar;
    }

    public void setAvatar(int avatar) {
        this.avatar = avatar;
    }
}
