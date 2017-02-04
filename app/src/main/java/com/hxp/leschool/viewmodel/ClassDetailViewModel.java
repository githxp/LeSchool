package com.hxp.leschool.viewmodel;

import android.util.Log;
import android.view.View;

import com.hxp.leschool.R;
import com.hxp.leschool.databinding.ClassdetailAtBinding;
import com.hxp.leschool.utils.MyApplication;
import com.hxp.leschool.utils.event.DownloadEvent;
import com.hxp.leschool.view.activity.ClassDetailActivity;

import org.greenrobot.eventbus.EventBus;

import java.io.File;

import cn.bmob.v3.datatype.BmobFile;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.DownloadFileListener;

/**
 * Created by hxp on 17-1-13.
 */

public class ClassDetailViewModel {

    private ClassDetailActivity mClassDetailActivity;
    private ClassdetailAtBinding mClassdetailAtBinding;
    private String mClassDetailTitle;
    private String mClassDetailUrl;
    private int mFirstProcess = 0;

    public ClassDetailViewModel(ClassDetailActivity classDetailActivity, ClassdetailAtBinding classdetailAtBinding) {

        mClassDetailActivity = classDetailActivity;
        mClassdetailAtBinding = classdetailAtBinding;

        mClassDetailTitle = mClassDetailActivity.getIntent().getStringExtra("classDetailTitle");
        mClassDetailUrl = mClassDetailActivity.getIntent().getStringExtra("classDetailUrl");

        mClassdetailAtBinding.setMClassDetailViewModel(this);

        mClassdetailAtBinding.tvClassDetailTitle.setText(mClassDetailTitle);
    }

    public void btn_ClassDetail_download(View view) {
        BmobFile bmobFile = new BmobFile(mClassDetailTitle, "", mClassDetailUrl);
        bmobFile.download(new File(MyApplication.getInstance().getExternalFilesDir("download"), mClassDetailTitle), new DownloadFileListener() {
            @Override
            public void done(String s, BmobException e) {
                Log.d("fragment", "下载完成 下载路径：" + s);
            }

            @Override
            public void onProgress(Integer integer, long l) {
                if (integer - mFirstProcess >= 1) {
                    mFirstProcess = integer;
                    EventBus.getDefault().post(new DownloadEvent(mClassDetailTitle, R.mipmap.ic_launcher, integer));
                    Log.d("fragment", "发布下载进度更新");
                }
            }
        });
        Log.d("fragment", "下载的文件名：" + mClassDetailTitle);
    }
}
