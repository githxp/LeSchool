package com.hxp.leschool.viewmodel;

import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.hxp.leschool.databinding.ClassdetailAtBinding;
import com.hxp.leschool.model.operate.ClassDetailModelOpt;
import com.hxp.leschool.model.operate.ClassDetailModelOpt.ClassDetailOptCallback;
import com.hxp.leschool.view.activity.ClassDetailActivity;

/**
 * Created by hxp on 17-1-13.
 */

public class ClassDetailViewModel implements ClassDetailOptCallback {

    public ClassDetailModelOpt mClassDetailModelOpt;
    private ClassDetailActivity mClassDetailActivity;
    private ClassdetailAtBinding mClassdetailAtBinding;

    public ClassDetailViewModel(ClassDetailActivity classDetailActivity, ClassdetailAtBinding classdetailAtBinding) {

        mClassDetailActivity = classDetailActivity;
        mClassdetailAtBinding = classdetailAtBinding;

        mClassDetailModelOpt = new ClassDetailModelOpt(mClassDetailActivity, this);

        mClassdetailAtBinding.setMClassDetailViewModel(this);

        mClassDetailModelOpt.getData();
    }

    public void onClassDetail_Btn_downloadClicked(View view){
        mClassDetailModelOpt.downloadData();
    }

    @Override
    public void classDetailGetdataSucceedCompleted() {
        Toast.makeText(mClassDetailActivity, "获取数据成功", Toast.LENGTH_SHORT).show();
        Log.d("fragment", "ClassDetailViewModelOpt数据获取成功回调接收方");
    }

    @Override
    public void classDetailGetdataFailedCompleted() {
        Toast.makeText(mClassDetailActivity, "获取数据失败", Toast.LENGTH_SHORT).show();
        Log.d("fragment", "ClassDetailViewModelOpt数据获取失败回调接收方");
    }

    @Override
    public void classDetailRefreshdataSucceedCompleted() {

        Toast.makeText(mClassDetailActivity, "刷新数据成功", Toast.LENGTH_SHORT).show();
        Log.d("fragment", "ClassDetailViewModelOpt数据刷新成功回调接收方");
    }

    @Override
    public void classDetailRefreshdataFailedCompleted() {
        Toast.makeText(mClassDetailActivity, "刷新数据失败", Toast.LENGTH_SHORT).show();
        Log.d("fragment", "ClassDetailViewModelOpt数据刷新失败回调接收方");
    }
}
