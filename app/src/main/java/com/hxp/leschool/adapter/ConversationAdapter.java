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
import com.hxp.leschool.view.fragment.ConversationFragment;
import com.hxp.leschool.viewmodel.ConversationViewModel;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuAdapter;


/**
 * Created by hxp on 17-1-13.
 */


public class ConversationAdapter extends SwipeMenuAdapter<ConversationAdapter.ViewHolder> {

    private ConversationViewModel mConversationViewModel;
    private ConversationFragment mConversationFragment;
    private OnItemClickListener mOnItemClickListener;
    private ViewDataBinding viewDataBinding;

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

    public ConversationAdapter(ConversationViewModel conversationViewModel, ConversationFragment conversationFragment) {
        mConversationViewModel = conversationViewModel;
        mConversationFragment = conversationFragment;
    }

    @Override
    public View onCreateContentView(ViewGroup parent, int viewType) {
        viewDataBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.conversation_item, parent, false);
        return viewDataBinding.getRoot();
    }

    @Override
    public ViewHolder onCompatCreateViewHolder(View realContentView, int viewType) {
        ViewHolder viewHolder = new ViewHolder(realContentView);
        viewHolder.setBinding(viewDataBinding);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.getBinding().setVariable(BR.mConversationViewModel, mConversationViewModel);
        holder.getBinding().setVariable(BR.mPosition, position);
        holder.getBinding().executePendingBindings();
        Log.d("fragment","mdata："+mConversationViewModel.mConversationModelOpt.mData.get(position).getAvatar());
        new AvatarHelper(mConversationFragment.getContext(), (ImageView) holder.itemView.findViewById(R.id.img_Conversation_avatar), mConversationViewModel.mConversationModelOpt.mData.get(position).getAvatar());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mOnItemClickListener.onItemClick(holder.itemView, holder.getLayoutPosition());
            }
        });
        Log.d("fragment", "onBindViewHolder()-ConversationAdapter");
    }

    @Override
    public int getItemCount() {
        int itemCount = mConversationViewModel.mConversationModelOpt.getCount();
        Log.d("fragment", "getItemCount()-ConversationAdapter" + itemCount);
        return itemCount;
    }

    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        mOnItemClickListener = onItemClickListener;
    }
}
