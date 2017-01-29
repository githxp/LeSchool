/*
package com.hxp.leschool.adapter;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hxp.leschool.BR;
import com.hxp.leschool.R;
import com.hxp.leschool.viewmodel.DownloadingViewModel;
import com.hxp.leschool.viewmodel.UploadingViewModel;

*/
/**
 * Created by hxp on 17-1-13.
 *//*


public class UploadingAdapter extends RecyclerView.Adapter<UploadingAdapter.ViewHolder> {

    private UploadingViewModel mUploadingViewModel;
    private OnItemClickListener mOnItemClickListener;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private ViewDataBinding mViewDataBinding;

        public ViewHolder(View view) {
            super(view);
        }

        public void setBinding(ViewDataBinding viewDataBinding) {
            mViewDataBinding = viewDataBinding;
        }

        public ViewDataBinding getBinding() {
            return mViewDataBinding;
        }
    }

    public UploadingAdapter(UploadingViewModel uploadingViewModel) {
        mUploadingViewModel = uploadingViewModel;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ViewDataBinding viewDataBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.uploading_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(viewDataBinding.getRoot());
        viewHolder.setBinding(viewDataBinding);
        Log.d("fragment", "onCreateViewHolder()");
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.getBinding().setVariable(BR.mUploadingViewModel, mUploadingViewModel);
        holder.getBinding().setVariable(BR.mPosition, position);
        holder.getBinding().executePendingBindings();
        holder.itemView.findViewById(R.id.ll_downloading_pauseDownload).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mOnItemClickListener.onItemPauseUploadClick(holder.itemView, holder.getLayoutPosition());
            }
        });
        holder.itemView.findViewById(R.id.ll_downloading_delDownload).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mOnItemClickListener.onItemDelUploadClick(holder.itemView, holder.getLayoutPosition());
            }
        });
        Log.d("fragment", "onBindViewHolder()");
    }

    @Override
    public int getItemCount() {
        int itemCount = mUploadingViewModel.mUploadingModelOpt.getCount();
        Log.d("fragment", "getItemCount()" + itemCount);
        return itemCount;
    }

    public interface OnItemClickListener {
        void onItemPauseUploadClick(View view, int position);

        void onItemDelUploadClick(View view, int position);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        mOnItemClickListener = onItemClickListener;
    }
}
*/
