package com.hxp.leschool.adapter;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.hxp.leschool.BR;
import com.hxp.leschool.R;
import com.hxp.leschool.utils.MyApplication;
import com.hxp.leschool.viewmodel.ScoreViewModel;

/**
 * Created by hxp on 17-1-13.
 */

public class ScoreAdapter extends RecyclerView.Adapter<ScoreAdapter.ViewHolder> {

    private ScoreViewModel mScoreViewModel;
    private OnItemClickListener mOnItemClickListener;
    private String mMidScore;
    private String mEndScore;
    private String mFinalScore;

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

    public ScoreAdapter(ScoreViewModel scoreViewModel) {
        mScoreViewModel = scoreViewModel;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ViewDataBinding viewDataBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.score_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(viewDataBinding.getRoot());
        viewHolder.setBinding(viewDataBinding);
        Log.d("fragment", "onCreateViewHolder()");
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.getBinding().setVariable(BR.mScoreViewModel, mScoreViewModel);
        holder.getBinding().setVariable(BR.mPosition, position);
        holder.getBinding().executePendingBindings();

        mMidScore = mScoreViewModel.mScoreModelOpt.mData.get(position).getMidScore();
        if (mMidScore.equals("无")) {
            Log.d("fragment", "文字不参与:" + mMidScore);
            ((TextView) holder.itemView.findViewById(R.id.tv_Score_midScore)).setTextColor(MyApplication.getInstance().getResources().getColor(R.color.primaryTextColorGray, null));
        } else {
            double score = Double.valueOf(mMidScore).doubleValue();
            Log.d("fragment", "score:" + score);
            if (score >= 90) {
                ((TextView) holder.itemView.findViewById(R.id.tv_Score_midScore)).setTextColor(MyApplication.getInstance().getResources().getColor(R.color.scoreLessHundred, null));
                Log.d("fragment", ">=90");
            } else if (score >= 80 && score < 90) {
                ((TextView) holder.itemView.findViewById(R.id.tv_Score_midScore)).setTextColor(MyApplication.getInstance().getResources().getColor(R.color.scoreLessNinety, null));
                Log.d("fragment", ">=80");
            } else if (score >= 70 && score < 80) {
                ((TextView) holder.itemView.findViewById(R.id.tv_Score_midScore)).setTextColor(MyApplication.getInstance().getResources().getColor(R.color.scoreLessEighty, null));
                Log.d("fragment", ">=70");
            } else if (score >= 60 && score < 70) {
                ((TextView) holder.itemView.findViewById(R.id.tv_Score_midScore)).setTextColor(MyApplication.getInstance().getResources().getColor(R.color.scoreLessSeventy, null));
                Log.d("fragment", ">=60");
            } else if (score < 60) {
                ((TextView) holder.itemView.findViewById(R.id.tv_Score_midScore)).setTextColor(MyApplication.getInstance().getResources().getColor(R.color.scoreLessSixty, null));
                Log.d("fragment", "<60");
            }
        }

        mEndScore = mScoreViewModel.mScoreModelOpt.mData.get(position).getEndScore();
        if (mEndScore.equals("无")) {
            Log.d("fragment", "文字不参与:" + mEndScore);
            ((TextView) holder.itemView.findViewById(R.id.tv_Score_endScore)).setTextColor(MyApplication.getInstance().getResources().getColor(R.color.primaryTextColorGray, null));
        } else {
            double score = Double.valueOf(mEndScore).doubleValue();
            Log.d("fragment", "score:" + score);
            if (score >= 90) {
                ((TextView) holder.itemView.findViewById(R.id.tv_Score_endScore)).setTextColor(MyApplication.getInstance().getResources().getColor(R.color.scoreLessHundred, null));
                Log.d("fragment", ">=90");
            } else if (score >= 80 && score < 90) {
                ((TextView) holder.itemView.findViewById(R.id.tv_Score_endScore)).setTextColor(MyApplication.getInstance().getResources().getColor(R.color.scoreLessNinety, null));
                Log.d("fragment", ">=80");
            } else if (score >= 70 && score < 80) {
                ((TextView) holder.itemView.findViewById(R.id.tv_Score_endScore)).setTextColor(MyApplication.getInstance().getResources().getColor(R.color.scoreLessEighty, null));
                Log.d("fragment", ">=70");
            } else if (score >= 60 && score < 70) {
                ((TextView) holder.itemView.findViewById(R.id.tv_Score_endScore)).setTextColor(MyApplication.getInstance().getResources().getColor(R.color.scoreLessSeventy, null));
                Log.d("fragment", ">=60");
            } else if (score < 60) {
                ((TextView) holder.itemView.findViewById(R.id.tv_Score_endScore)).setTextColor(MyApplication.getInstance().getResources().getColor(R.color.scoreLessSixty, null));
                Log.d("fragment", "<60");
            }
        }

        mFinalScore = mScoreViewModel.mScoreModelOpt.mData.get(position).getFinScore();
        if (mFinalScore.equals("无")) {
            Log.d("fragment", "文字不参与:" + mFinalScore);
            ((TextView) holder.itemView.findViewById(R.id.tv_Score_finalScore)).setTextColor(MyApplication.getInstance().getResources().getColor(R.color.primaryTextColorGray, null));
        } else {
            double score = Double.valueOf(mFinalScore).doubleValue();
            Log.d("fragment", "score:" + score);
            if (score >= 90) {
                ((TextView) holder.itemView.findViewById(R.id.tv_Score_finalScore)).setTextColor(MyApplication.getInstance().getResources().getColor(R.color.scoreLessHundred, null));
                Log.d("fragment", ">=90");
            } else if (score >= 80 && score < 90) {
                ((TextView) holder.itemView.findViewById(R.id.tv_Score_finalScore)).setTextColor(MyApplication.getInstance().getResources().getColor(R.color.scoreLessNinety, null));
                Log.d("fragment", ">=80");
            } else if (score >= 70 && score < 80) {
                ((TextView) holder.itemView.findViewById(R.id.tv_Score_finalScore)).setTextColor(MyApplication.getInstance().getResources().getColor(R.color.scoreLessEighty, null));
                Log.d("fragment", ">=70");
            } else if (score >= 60 && score < 70) {
                ((TextView) holder.itemView.findViewById(R.id.tv_Score_finalScore)).setTextColor(MyApplication.getInstance().getResources().getColor(R.color.scoreLessSeventy, null));
                Log.d("fragment", ">=60");
            } else if (score < 60) {
                ((TextView) holder.itemView.findViewById(R.id.tv_Score_finalScore)).setTextColor(MyApplication.getInstance().getResources().getColor(R.color.scoreLessSixty, null));
                Log.d("fragment", "<60");
            }
        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mOnItemClickListener.onItemClick(holder.itemView, holder.getLayoutPosition());
            }
        });
        Log.d("fragment", "onBindViewHolder()");
    }

    @Override
    public int getItemCount() {
        int itemCount = mScoreViewModel.mScoreModelOpt.getCount();
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
