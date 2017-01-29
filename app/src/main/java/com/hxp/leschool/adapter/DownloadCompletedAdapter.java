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
import com.hxp.leschool.viewmodel.DownloadCompletedViewModel;
import com.hxp.leschool.viewmodel.DownloadViewModel;


/**
 * Created by hxp on 17-1-13.
 */


public class DownloadCompletedAdapter extends RecyclerView.Adapter<DownloadCompletedAdapter.ViewHolder> {

    private DownloadCompletedViewModel mDownloadCompletedViewModel;
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

    public DownloadCompletedAdapter(DownloadCompletedViewModel downloadCompletedViewModel) {
        mDownloadCompletedViewModel = downloadCompletedViewModel;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ViewDataBinding viewDataBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.downloadcompleted_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(viewDataBinding.getRoot());
        viewHolder.setBinding(viewDataBinding);
        Log.d("fragment", "onCreateViewHolder()");
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.getBinding().setVariable(BR.mDownloadCompletedViewModel, mDownloadCompletedViewModel);
        holder.getBinding().setVariable(BR.mPosition, position);
        holder.getBinding().executePendingBindings();
        holder.itemView.findViewById(R.id.ll_downloadCompleted_openFile).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mOnItemClickListener.onItemOpenFileClick(holder.itemView, holder.getLayoutPosition());
            }
        });
        holder.itemView.findViewById(R.id.ll_downloadCompleted_delFile).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mOnItemClickListener.onItemDelFileClick(holder.itemView, holder.getLayoutPosition());
            }
        });
        Log.d("fragment", "onBindViewHolder()");
    }

    @Override
    public int getItemCount() {
        int itemCount = mDownloadCompletedViewModel.mDownloadCompletedModelOpt.getCount();
        Log.d("fragment", "getItemCount()" + itemCount);
        return itemCount;
    }

    public interface OnItemClickListener {
        void onItemOpenFileClick(View view, int position);

        void onItemDelFileClick(View view, int position);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        mOnItemClickListener = onItemClickListener;
    }
}
