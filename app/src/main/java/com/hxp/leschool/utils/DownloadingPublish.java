package com.hxp.leschool.utils;

import com.hxp.leschool.model.bean.DownloadTaskModel;

import java.util.ArrayList;

/**
 * Created by hxp on 17-1-27.
 */

public class DownloadingPublish {

    private static ArrayList<DownloadTaskModel> mData = new ArrayList<>();

    public static void addDownloadTask(DownloadTaskModel downloadTaskModel) {
        mData.add(downloadTaskModel);
    }

    public static void removeDownloadTask(DownloadTaskModel downloadTaskModel) {
        mData.remove(downloadTaskModel);
    }

    public static ArrayList<DownloadTaskModel> getDownloadTask() {
        return mData;
    }

    public static int getDownloadTaskCount() {
        return mData.size();
    }
}
