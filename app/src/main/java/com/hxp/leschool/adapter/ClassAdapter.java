package com.hxp.leschool.adapter;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.hxp.leschool.BR;
import com.hxp.leschool.R;
import com.hxp.leschool.utils.AvatarHelper;
import com.hxp.leschool.view.fragment.ClassFragment;
import com.hxp.leschool.viewmodel.ClassViewModel;

/**
 * Created by hxp on 17-1-13.
 */

public class ClassAdapter extends RecyclerView.Adapter<ClassAdapter.ViewHolder> {

    private ClassViewModel mClassViewModel;
    private ClassFragment mClassFragment;
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

    public ClassAdapter(ClassViewModel classViewModel, ClassFragment classFragment) {
        mClassViewModel = classViewModel;
        mClassFragment = classFragment;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ViewDataBinding viewDataBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.class_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(viewDataBinding.getRoot());
        viewHolder.setBinding(viewDataBinding);
        Log.d("fragment", "onCreateViewHolder()-ClassAdapter");
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.getBinding().setVariable(BR.mClassViewModel, mClassViewModel);
        holder.getBinding().setVariable(BR.mPosition, position);
        holder.getBinding().executePendingBindings();
        Log.d("fragment","mdata："+mClassViewModel.mClassModelOpt.mData.get(position).getAvatar());
        new AvatarHelper(mClassFragment.getContext(), (ImageView) holder.itemView.findViewById(R.id.img_Class_avatar), mClassViewModel.mClassModelOpt.mData.get(position).getAvatar());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mOnItemClickListener.onItemClick(holder.itemView, holder.getLayoutPosition());
            }
        });
        Log.d("fragment", "onBindViewHolder()-ClassAdapter");
    }

    @Override
    public int getItemCount() {
        int itemCount = mClassViewModel.mClassModelOpt.getCount();
        Log.d("fragment", "getItemCount()-ClassAdapter" + itemCount);
        return itemCount;
    }

    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        mOnItemClickListener = onItemClickListener;
    }
}
