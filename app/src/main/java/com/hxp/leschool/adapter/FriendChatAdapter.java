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
import com.hxp.leschool.viewmodel.FriendChatViewModel;

/**
 * Created by hxp on 17-1-13.
 */

public class FriendChatAdapter extends RecyclerView.Adapter<FriendChatAdapter.ViewHolder> {

    private FriendChatViewModel mFriendChatViewModel;
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

    public FriendChatAdapter(FriendChatViewModel friendChatViewModel) {
        mFriendChatViewModel = friendChatViewModel;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ViewDataBinding viewDataBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.friendchat_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(viewDataBinding.getRoot());
        viewHolder.setBinding(viewDataBinding);
        Log.d("fragment", "onCreateViewHolder()-FriendChatAdapter");
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.getBinding().setVariable(BR.mFriendChatViewModel, mFriendChatViewModel);
        holder.getBinding().setVariable(BR.mPosition, position);
        holder.getBinding().executePendingBindings();
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mOnItemClickListener.onItemClick(holder.itemView, holder.getLayoutPosition());
            }
        });
        Log.d("fragment", "onBindViewHolder()-FriendChatAdapter");
    }

    @Override
    public int getItemCount() {
        int itemCount = mFriendChatViewModel.mFriendChatModelOpt.getCount();
        Log.d("fragment", "getItemCount()-FriendChatAdapter" + itemCount);
        return itemCount;
    }

    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        mOnItemClickListener = onItemClickListener;
    }
}
