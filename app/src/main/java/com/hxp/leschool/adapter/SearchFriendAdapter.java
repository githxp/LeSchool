package com.hxp.leschool.adapter;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.hxp.leschool.BR;
import com.hxp.leschool.R;
import com.hxp.leschool.utils.AvatarHelper;
import com.hxp.leschool.view.activity.SearchFriendActivity;
import com.hxp.leschool.viewmodel.ClassViewModel;
import com.hxp.leschool.viewmodel.SearchFriendViewModel;

/**
 * Created by hxp on 17-1-13.
 */

public class SearchFriendAdapter extends RecyclerView.Adapter<SearchFriendAdapter.ViewHolder> {

    private SearchFriendViewModel mSearchFriendViewModel;
    private SearchFriendActivity mSearchFriendActivity;
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

    public SearchFriendAdapter(SearchFriendViewModel searchFriendViewModel, SearchFriendActivity searchFriendActivity) {
        mSearchFriendViewModel = searchFriendViewModel;
        mSearchFriendActivity = searchFriendActivity;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ViewDataBinding viewDataBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.searchfriend_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(viewDataBinding.getRoot());
        viewHolder.setBinding(viewDataBinding);
        Log.d("fragment", "onCreateViewHolder()");
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.getBinding().setVariable(BR.mSearchFriendViewModel, mSearchFriendViewModel);
        holder.getBinding().setVariable(BR.mPosition, position);
        holder.getBinding().executePendingBindings();
        Log.d("fragment", "mdata：" + mSearchFriendViewModel.mSearchFriendModelOpt.mData.get(position).getAvatar());
        new AvatarHelper(mSearchFriendActivity, (ImageView) holder.itemView.findViewById(R.id.img_SearchFriend_avatar), mSearchFriendViewModel.mSearchFriendModelOpt.mData.get(position).getAvatar());
        holder.itemView.findViewById(R.id.btn_searchFriend_addFriend).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mOnItemClickListener.onItemClick(holder.itemView, holder.getLayoutPosition());
            }
        });
        Log.d("fragment", "onBindViewHolder()");
    }

    @Override
    public int getItemCount() {
        int itemCount = mSearchFriendViewModel.mSearchFriendModelOpt.getCount();
        Log.d("fragment", "getItemCount()" + itemCount);
        return itemCount;
    }

    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        mOnItemClickListener = onItemClickListener;
    }
}
