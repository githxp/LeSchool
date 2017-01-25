package com.hxp.leschool.viewmodel;

import android.util.Log;
import android.view.View;

import com.hxp.leschool.R;
import com.hxp.leschool.databinding.DownloadAtBinding;
import com.hxp.leschool.view.activity.DownloadActivity;
import com.hxp.leschool.view.fragment.DownloadCompletedFragment;


/**
 * Created by hxp on 17-1-13.
 */

public class DownloadViewModel {

    private DownloadActivity mDownloadActivity;
    private DownloadAtBinding mDownloadAtBinding;
    private DownloadCompletedFragment mDownloadCompletedFragment;

    public DownloadViewModel(DownloadActivity downloadActivity, DownloadAtBinding downloadAtBinding) {

        mDownloadActivity = downloadActivity;
        mDownloadAtBinding = downloadAtBinding;

        mDownloadAtBinding.setMDownloadViewModel(this);
        if (mDownloadCompletedFragment == null) {
            mDownloadCompletedFragment = new DownloadCompletedFragment();
            Log.d("Fragment生命周期管理", "创建DownloadCompletedFragment");
        }
        mDownloadActivity.getFragmentManager().beginTransaction().add(R.id.ll_download_content, mDownloadCompletedFragment).commit();
    }

    public void onDownload_Layout_DownloadCompletedClicked(View view) {

    }

    public void onDownload_Layout_DownloadingClicked(View view) {

    }

    public void onDownload_Layout_UploadCompletedClicked(View view) {

    }

    public void onDownload_Layout_UploadingClicked(View view) {

    }


}
