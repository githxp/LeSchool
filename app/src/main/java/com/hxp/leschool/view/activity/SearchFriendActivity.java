package com.hxp.leschool.view.activity;

import android.app.Activity;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.util.Log;

import com.hxp.leschool.R;
import com.hxp.leschool.databinding.DownloadAtBinding;
import com.hxp.leschool.databinding.SearchfriendAtBinding;
import com.hxp.leschool.viewmodel.DownloadViewModel;
import com.hxp.leschool.viewmodel.SearchFriendViewModel;


/**
 * Created by hxp on 17-1-24.
 */


public class SearchFriendActivity extends Activity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SearchfriendAtBinding searchfriendAtBinding = DataBindingUtil.setContentView(this, R.layout.searchfriend_at);
        Log.d("fragment", "创建了DownloadActivity");
        new SearchFriendViewModel(this, searchfriendAtBinding);
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.d("Fragment生命周期管理", "onPause()触发-DownloadActivity");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d("Fragment生命周期管理", "onResume()触发-DownloadActivity");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d("Fragment生命周期管理", "onDestroy()触发-DownloadActivity");
    }
}

