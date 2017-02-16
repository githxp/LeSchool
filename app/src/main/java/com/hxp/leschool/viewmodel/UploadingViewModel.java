package com.hxp.leschool.viewmodel;

import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;

import com.hxp.leschool.adapter.UploadingAdapter;
import com.hxp.leschool.databinding.UploadingFmBinding;
import com.hxp.leschool.databinding.UploadingItemBinding;
import com.hxp.leschool.model.opt.UploadingModelOpt;
import com.hxp.leschool.model.opt.UploadingModelOpt.UploadCallback;
import com.hxp.leschool.utils.event.UploadEvent;
import com.hxp.leschool.view.fragment.UploadingFragment;
import com.hxp.leschool.widget.RecycleItemDivider;


/**
 * Created by hxp on 17-1-13.
 */


public class UploadingViewModel implements UploadCallback {

    public UploadingModelOpt mUploadingModelOpt;
    private UploadingFragment mUploadingFragment;
    private UploadingFmBinding mUploadingFmBinding;
    private UploadingItemBinding mUploadingItemBinding;
    private UploadingAdapter mUploadingAdapter;

    public UploadingViewModel(UploadingFragment uploadingFragment, UploadingFmBinding uploadingFmBinding, UploadingItemBinding uploadingItemBinding) {

        mUploadingFragment = uploadingFragment;
        mUploadingFmBinding = uploadingFmBinding;
        mUploadingItemBinding = uploadingItemBinding;

        mUploadingAdapter = new UploadingAdapter(this);
        mUploadingModelOpt = new UploadingModelOpt(this);

        mUploadingFmBinding.rvUploadingContent.setLayoutManager(new LinearLayoutManager(mUploadingFragment.getActivity(), LinearLayoutManager.VERTICAL, false));
        mUploadingFmBinding.rvUploadingContent.setAdapter(mUploadingAdapter);
        mUploadingFmBinding.rvUploadingContent.setItemAnimator(new DefaultItemAnimator());
        mUploadingFmBinding.rvUploadingContent.addItemDecoration(new RecycleItemDivider(mUploadingFragment.getActivity(), RecycleItemDivider.VERTICAL_LIST));

        mUploadingFmBinding.setMUploadingViewModel(this);
        mUploadingItemBinding.setMUploadingViewModel(this);
    }

    @Override
    public void refresh() {
        mUploadingAdapter.notifyDataSetChanged();
        Log.d("fragment", "UploadingModelOpt数据刷新成功回调接收方");
    }

    //处理上传事件
    public void handleUploadEvent(UploadEvent uploadEvent) {
        Log.d("fragment", "交给mUploadingModelOpt处理");
        mUploadingModelOpt.handleUploadEvent(uploadEvent);
    }
}
