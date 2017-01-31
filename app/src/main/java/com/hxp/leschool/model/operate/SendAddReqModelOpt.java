package com.hxp.leschool.model.operate;

import com.hxp.leschool.viewmodel.SendAddReqViewModel;

/**
 * Created by hxp on 17-1-15.
 */

public class SendAddReqModelOpt {

    private SendAddReqOptCallback mSendAddReqOptCallback;

    public SendAddReqModelOpt(SendAddReqViewModel sendAddReqViewModel) {
        mSendAddReqOptCallback = sendAddReqViewModel;
    }

    //发送请求
    public void sendReq(String req,String userName) {


    }

    //发送请求成功回调
    //发送请求失败回调
    public interface SendAddReqOptCallback {
        void sendAddReqSucceedCompleted();

        void sendAddReqFailedCompleted();
    }
}
