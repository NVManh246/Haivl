package com.nvmanh.haivl.utils;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

public abstract class EndLessScrollListener extends RecyclerView.OnScrollListener {
    private int mPreviousTotal = 0;
    private boolean mLoading = true;

    @Override
    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);
        int totalItemCount = recyclerView.getLayoutManager().getItemCount();            // tong so item
        int firstVisibleItem = ((LinearLayoutManager)recyclerView.getLayoutManager())   // so item da xem
                .findFirstVisibleItemPosition();
        Log.d("kiemtra", totalItemCount + " - " + firstVisibleItem);
        if(mLoading) {
            if(totalItemCount > mPreviousTotal) {
                mLoading = false;
                mPreviousTotal = totalItemCount;
            }
        }
        if(!mLoading && totalItemCount == firstVisibleItem + 2) {
            onLoadMore();
            mLoading = true;
        }
    }

    public abstract void onLoadMore();
}
