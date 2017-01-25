package com.hxp.leschool.model.operate;

import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.widget.Toast;

import com.hxp.leschool.R;
import com.hxp.leschool.model.bean.DownloadCompletedModel;
import com.hxp.leschool.utils.MIMEHelper;
import com.hxp.leschool.utils.MyApplication;
import com.hxp.leschool.utils.MyFileHelper;
import com.hxp.leschool.view.activity.DownloadActivity;
import com.hxp.leschool.view.fragment.DownloadCompletedFragment;
import com.hxp.leschool.viewmodel.DownloadCompletedViewModel;
import com.hxp.leschool.viewmodel.DownloadViewModel;

import java.io.File;
import java.util.ArrayList;

/**
 * Created by hxp on 17-1-15.
 */

public class DownloadCompletedModelOpt {

    public DownloadCompletedModel mDownloadCompletedModel;
    public ArrayList<DownloadCompletedModel> mData = new ArrayList<>();
    private DownloadCompletedOptCallback mDownloadCompletedOptCallback;
    private ArrayList<File> fileNameList;
    private DownloadCompletedFragment mDownloadCompletedFragment;

    public DownloadCompletedModelOpt(DownloadCompletedViewModel downloadCompletedViewModel, DownloadCompletedFragment downloadCompletedFragment) {
        mDownloadCompletedOptCallback = downloadCompletedViewModel;
        mDownloadCompletedFragment = downloadCompletedFragment;
    }

    //获取数据
    public void getData() {
        fileNameList = MyFileHelper.getFileNameList(MyApplication.getInstance().getExternalFilesDir("download").getAbsolutePath());
        Log.d("fragment", "扫描路径：" + MyApplication.getInstance().getExternalFilesDir("download").getAbsolutePath());
        mData.clear();
        for (int i = 0; i < fileNameList.size(); i++) {
            mDownloadCompletedModel = new DownloadCompletedModel();
            mDownloadCompletedModel.setPicture(R.mipmap.ic_launcher);
            mDownloadCompletedModel.setTitle(fileNameList.get(i).getName());
            mData.add(mDownloadCompletedModel);
        }
        Log.d("fragment", "DownloadModelOpt数据获取成功回调发送方");
        mDownloadCompletedOptCallback.downloadGetdataSucceedCompleted();
    }

    //刷新数据
    public void refreshData() {
        fileNameList = MyFileHelper.getFileNameList(MyApplication.getInstance().getExternalFilesDir("download").getAbsolutePath());
        Log.d("fragment", "扫描路径：" + MyApplication.getInstance().getExternalFilesDir("download").getAbsolutePath());
        mData.clear();
        for (int i = 0; i < fileNameList.size(); i++) {
            mDownloadCompletedModel = new DownloadCompletedModel();
            mDownloadCompletedModel.setPicture(R.mipmap.ic_launcher);
            mDownloadCompletedModel.setTitle(fileNameList.get(i).getName());
            mData.add(mDownloadCompletedModel);
        }
        Log.d("fragment", "DownloadModelOpt数据刷新成功回调发送方");
        mDownloadCompletedOptCallback.downloadRefreshdataSucceedCompleted();
    }

    //获取数据数量
    public int getCount() {
        return mData.size();
    }

    //打开文件
    public void openFile(int position) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        File file = fileNameList.get(position);
        intent.setDataAndType(Uri.fromFile(file), MIMEHelper.getMIMEType(file));
        Log.d("fragment", "获取mime：" + MIMEHelper.getMIMEType(file));
        if (intent.resolveActivity(MyApplication.getInstance().getPackageManager()) != null) {
            mDownloadCompletedFragment.getActivity().startActivity(intent);
        } else {
            Toast.makeText(mDownloadCompletedFragment.getActivity(), "未找到支持的应用", Toast.LENGTH_SHORT).show();
        }
    }

    //删除文件
    public void delFile(int position) {
        MyFileHelper.removeFileFromExternalStorage(fileNameList.get(position).getAbsolutePath());
        refreshData();
        Toast.makeText(mDownloadCompletedFragment.getActivity(), "删除文件", Toast.LENGTH_SHORT).show();
    }

    //获取数据成功回调
    //获取数据失败回调
    public interface DownloadCompletedOptCallback {
        void downloadGetdataSucceedCompleted();

        void downloadGetdataFailedCompleted();

        void downloadRefreshdataSucceedCompleted();

        void downloadRefreshdataFailedCompleted();
    }
}
