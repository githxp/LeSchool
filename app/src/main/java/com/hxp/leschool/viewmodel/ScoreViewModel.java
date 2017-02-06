package com.hxp.leschool.viewmodel;

import android.util.Log;
import android.view.View;
import android.webkit.WebViewClient;

import com.bumptech.glide.Glide;
import com.hxp.leschool.databinding.ScoreAtBinding;
import com.hxp.leschool.view.activity.ScoreActivity;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;


/**
 * 用户中心
 * Created by hxp on 17-1-22.
 */


public class ScoreViewModel {

    private ScoreActivity mScoreActivity;
    private ScoreAtBinding mScoreAtBinding;

    public ScoreViewModel(ScoreActivity scoreActivity, ScoreAtBinding scoreAtBinding) {

        Log.d("fragment", "MicroblogSingleChatViewModel创建");
        mScoreActivity = scoreActivity;
        mScoreAtBinding = scoreAtBinding;

        mScoreAtBinding.setMScoreViewModel(this);

        mScoreAtBinding.wvScoreContent.getSettings().setJavaScriptEnabled(true);
        mScoreAtBinding.wvScoreContent.getSettings().setDisplayZoomControls(false);

        mScoreAtBinding.wvScoreContent.getSettings().setUseWideViewPort(true);
        mScoreAtBinding.wvScoreContent.getSettings().setLoadWithOverviewMode(true);

        mScoreAtBinding.wvScoreContent.setWebViewClient(new WebViewClient());
        mScoreAtBinding.wvScoreContent.loadUrl("http://58.20.60.38:8081/JWWEB/");

        Glide.with(mScoreActivity).load("http://58.20.60.38:8081/JWWEB/sys/ValidateCode.aspx").into(mScoreAtBinding.imgScoreVerImg);
    }

    public void btn_Score_login(View view) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Connection.Response loginFrom = Jsoup.connect("http://58.20.60.38:8081/JWWEB/").method(Connection.Method.GET).execute();
                    Log.d("webCatch", loginFrom.cookies().toString());
                    Document doc = Jsoup.connect("http://58.20.60.38:8081/JWWEB/_data/index_LOGIN.aspx")
                            .data("Sel_Type", "STU")
                            .data("UserID", mScoreAtBinding.etScoreUserName.getText().toString())
                            .data("PassWord", mScoreAtBinding.etScorePassword.getText().toString())
                            .data("cCode", mScoreAtBinding.etScoreVerCode.getText().toString())
                            .cookies(loginFrom.cookies())
                            .post();
                    Log.d("webCatch", doc.toString());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
