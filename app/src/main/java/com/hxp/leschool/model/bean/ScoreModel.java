package com.hxp.leschool.model.bean;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import com.hxp.leschool.BR;

/**
 * Created by hxp on 17-2-8.
 */

public class ScoreModel extends BaseObservable {

    private String userName;
    private String studentNum;
    private String title;
    private String midScore;
    private String endScore;
    private String finScore;
    private String majorOrMinor;

    public ScoreModel() {
    }

    public ScoreModel(String userName, String studentNum, String title, String midScore, String endScore, String finScore, String majorOrMinor) {
        this.userName = userName;
        this.studentNum = studentNum;
        this.title = title;
        this.midScore = midScore;
        this.endScore = endScore;
        this.finScore = finScore;
        this.majorOrMinor = majorOrMinor;
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
    public String getStudentNum() {
        return studentNum;
    }

    public void setStudentNum(String studentNum) {
        this.studentNum = studentNum;
        notifyPropertyChanged(BR.studentNum);
    }

    @Bindable
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
        notifyPropertyChanged(BR.title);
    }

    @Bindable
    public String getMidScore() {
        return midScore;
    }

    public void setMidScore(String midScore) {
        this.midScore = midScore;
        notifyPropertyChanged(BR.midScore);
    }

    @Bindable
    public String getEndScore() {
        return endScore;
    }

    public void setEndScore(String endScore) {
        this.endScore = endScore;
        notifyPropertyChanged(BR.endScore);
    }

    @Bindable
    public String getFinScore() {
        return finScore;
    }

    public void setFinScore(String finScore) {
        this.finScore = finScore;
        notifyPropertyChanged(BR.finScore);
    }

    @Bindable
    public String getMajorOrMinor() {
        return majorOrMinor;
    }

    public void setMajorOrMinor(String majorOrMinor) {
        this.majorOrMinor = majorOrMinor;
        notifyPropertyChanged(BR.majorOrMinor);
    }
}
