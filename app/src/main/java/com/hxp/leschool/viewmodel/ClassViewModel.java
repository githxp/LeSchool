package com.hxp.leschool.viewmodel;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.hxp.leschool.adapter.ClassAdapter;
import com.hxp.leschool.databinding.ClassFmBinding;
import com.hxp.leschool.model.operate.ClassModelOpt;
import com.hxp.leschool.model.operate.ClassModelOpt.GetdataCallback;
import com.hxp.leschool.model.operate.ClassModelOpt.RefreshdataCallback;
import com.hxp.leschool.view.fragment.ClassFragment;

/**
 * Created by hxp on 17-1-13.
 */

public class ClassViewModel implements GetdataCallback, RefreshdataCallback {

    public ClassModelOpt mClassModelOpt;
    private ClassFmBinding mClassFmBinding;
    private ClassFragment mClassFragment;
    public ClassAdapter mClassAdapter;

    public ClassViewModel(final ClassFragment classFragment, ClassFmBinding classFmBinding) {

        mClassFmBinding = classFmBinding;
        mClassFragment = classFragment;
        mClassModelOpt = new ClassModelOpt(this);
        mClassAdapter = new ClassAdapter(this);

        mClassFmBinding.setMClassViewModel(this);

        mClassModelOpt.getData();

        mClassFmBinding.swifreshClassContent.setProgressViewOffset(true, 0, 50);
        mClassFmBinding.swifreshClassContent.setColorSchemeResources(
                android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light);
        mClassFmBinding.swifreshClassContent.setOnRefreshListener(
                new SwipeRefreshLayout.OnRefreshListener() {
                    @Override
                    public void onRefresh() {
                        mClassModelOpt.refreshData();
                        Toast.makeText(mClassFragment.getActivity(), "刷新", Toast.LENGTH_SHORT).show();
                    }
                }
        );
    }

    @Override
    public void getdataCompleted() {
        mClassFmBinding.rvClassContent.setLayoutManager(new LinearLayoutManager(mClassFragment.getActivity(), LinearLayoutManager.VERTICAL, false));
        mClassFmBinding.rvClassContent.setAdapter(mClassAdapter);
        mClassAdapter.setOnItemClickListener(new ClassAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Toast.makeText(mClassFragment.getActivity(), "click" + position, Toast.LENGTH_SHORT).show();
            }
        });
        Log.d("fragment", "数据获取回调接收方");
    }

    @Override
    public void refreshdataCompleted() {
        mClassFmBinding.swifreshClassContent.setRefreshing(false);
        mClassAdapter.notifyDataSetChanged();
        Log.d("fragment", "数据刷新回调接收方");
    }
}
