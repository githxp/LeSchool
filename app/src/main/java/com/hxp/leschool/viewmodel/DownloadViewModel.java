package com.hxp.leschool.viewmodel;

import android.util.Log;
import android.view.View;

import com.hxp.leschool.R;
import com.hxp.leschool.databinding.DownloadAtBinding;
import com.hxp.leschool.view.activity.DownloadActivity;
import com.hxp.leschool.view.fragment.DownloadCompletedFragment;
import com.hxp.leschool.view.fragment.DownloadingFragment;
import com.hxp.leschool.view.fragment.UploadingFragment;


/**
 * Created by hxp on 17-1-13.
 */


public class DownloadViewModel {

    private DownloadActivity mDownloadActivity;
    private DownloadAtBinding mDownloadAtBinding;
    private DownloadCompletedFragment mDownloadCompletedFragment;
    private DownloadingFragment mDownloadingFragment;
    private UploadingFragment mUploadingFragment;

    public DownloadViewModel(DownloadActivity downloadActivity, DownloadAtBinding downloadAtBinding) {

        mDownloadActivity = downloadActivity;
        mDownloadAtBinding = downloadAtBinding;

        mDownloadAtBinding.setMDownloadViewModel(this);
        if (mDownloadCompletedFragment == null) {
            mDownloadCompletedFragment = new DownloadCompletedFragment();
            Log.d("Fragment", "创建mDownloadCompletedFragment in1");
        }
        mDownloadActivity.getFragmentManager().beginTransaction().add(R.id.ll_download_content, mDownloadCompletedFragment).commit();
        Log.d("Fragment", "添加mDownloadCompletedFragment in1");
    }

    public void onDownload_Layout_DownloadCompletedClicked(View view) {
        if (mDownloadCompletedFragment == null) {
            mDownloadCompletedFragment = new DownloadCompletedFragment();
            Log.d("Fragment", "创建mDownloadCompletedFragment in2");
            mDownloadActivity.getFragmentManager().beginTransaction().add(R.id.ll_download_content, mDownloadCompletedFragment).commit();
            Log.d("Fragment", "添加mDownloadCompletedFragment in2");
            if (mDownloadingFragment != null) {
                mDownloadActivity.getFragmentManager().beginTransaction().hide(mDownloadingFragment).commit();
                Log.d("Fragment", "隐藏mDownloadingFragment in");
            }
            if (mUploadingFragment != null) {
                mDownloadActivity.getFragmentManager().beginTransaction().hide(mUploadingFragment).commit();
                Log.d("Fragment", "隐藏mUploadingFragment in");
            }
        } else if (!mDownloadCompletedFragment.isVisible()) {
            if (mDownloadingFragment != null) {
                mDownloadActivity.getFragmentManager().beginTransaction().hide(mDownloadingFragment).commit();
                Log.d("Fragment", "隐藏mDownloadingFragment on");
            }
            if (mUploadingFragment != null) {
                mDownloadActivity.getFragmentManager().beginTransaction().hide(mUploadingFragment).commit();
                Log.d("Fragment", "隐藏mUploadingFragment on");
            }
            mDownloadActivity.getFragmentManager().beginTransaction().show(mDownloadCompletedFragment).commit();
            Log.d("Fragment", "显示mDownloadCompletedFragment on");
        }
    }

    public void onDownload_Layout_DownloadingClicked(View view) {
        if (mDownloadingFragment == null) {
            mDownloadingFragment = new DownloadingFragment();
            Log.d("Fragment", "创建mDownloadingFragment in2");
            mDownloadActivity.getFragmentManager().beginTransaction().add(R.id.ll_download_content, mDownloadingFragment).commit();
            Log.d("Fragment", "添加mDownloadingFragment in2");
            if (mDownloadCompletedFragment != null) {
                mDownloadActivity.getFragmentManager().beginTransaction().hide(mDownloadCompletedFragment).commit();
                Log.d("Fragment", "隐藏mDownloadCompletedFragment in");
            }
            if (mUploadingFragment != null) {
                mDownloadActivity.getFragmentManager().beginTransaction().hide(mUploadingFragment).commit();
                Log.d("Fragment", "隐藏mUploadingFragment in");
            }
        } else if (!mDownloadingFragment.isVisible()) {
            if (mDownloadCompletedFragment != null) {
                mDownloadActivity.getFragmentManager().beginTransaction().hide(mDownloadCompletedFragment).commit();
                Log.d("Fragment", "隐藏mDownloadCompletedFragment on");
            }
            if (mUploadingFragment != null) {
                mDownloadActivity.getFragmentManager().beginTransaction().hide(mUploadingFragment).commit();
                Log.d("Fragment", "隐藏mUploadingFragment on");
            }
            mDownloadActivity.getFragmentManager().beginTransaction().show(mDownloadingFragment).commit();
            Log.d("Fragment", "显示mDownloadingFragment on");
        }
    }

    public void onDownload_Layout_UploadingClicked(View view) {
        if (mUploadingFragment == null) {
            mUploadingFragment = new UploadingFragment();
            Log.d("Fragment", "创建mUploadingFragment in2");
            mDownloadActivity.getFragmentManager().beginTransaction().add(R.id.ll_download_content, mUploadingFragment).commit();
            Log.d("Fragment", "添加mUploadingFragment in2");
            if (mDownloadCompletedFragment != null) {
                mDownloadActivity.getFragmentManager().beginTransaction().hide(mDownloadCompletedFragment).commit();
                Log.d("Fragment", "隐藏mDownloadCompletedFragment in");
            }
            if (mDownloadingFragment != null) {
                mDownloadActivity.getFragmentManager().beginTransaction().hide(mDownloadingFragment).commit();
                Log.d("Fragment", "隐藏mDownloadingFragment in");
            }
        } else if (!mUploadingFragment.isVisible()) {
            if (mDownloadCompletedFragment != null) {
                mDownloadActivity.getFragmentManager().beginTransaction().hide(mDownloadCompletedFragment).commit();
                Log.d("Fragment", "隐藏mDownloadCompletedFragment on");
            }
            if (mDownloadingFragment != null) {
                mDownloadActivity.getFragmentManager().beginTransaction().hide(mDownloadingFragment).commit();
                Log.d("Fragment", "隐藏mDownloadingFragment on");
            }
            mDownloadActivity.getFragmentManager().beginTransaction().show(mUploadingFragment).commit();
            Log.d("Fragment", "显示mUploadingFragment on");
        }
    }
}
