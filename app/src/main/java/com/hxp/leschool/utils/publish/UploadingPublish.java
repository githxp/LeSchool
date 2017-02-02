package com.hxp.leschool.utils.publish;

import com.hxp.leschool.model.bean.DownloadTaskModel;
import com.hxp.leschool.model.bean.UploadTaskModel;

import java.util.ArrayList;

/**
 * Created by hxp on 17-1-27.
 */

public class UploadingPublish {

    private static ArrayList<UploadTaskModel> mData = new ArrayList<>();

    public static void addUploadTask(UploadTaskModel uploadTaskModel) {
        mData.add(uploadTaskModel);
    }

    public static void removeUploadTask(UploadTaskModel uploadTaskModel) {
        mData.remove(uploadTaskModel);
    }

    public static ArrayList<UploadTaskModel> getUploadTask() {
        return mData;
    }

    public static int getUploadTaskCount() {
        return mData.size();
    }
}
