package com.nvmanh.haivl.screen.home;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

public class PostItemDecoration extends RecyclerView.ItemDecoration {

    private int mSpacing;

    public  PostItemDecoration(int spacing) {
        mSpacing = spacing;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        outRect.top = mSpacing;
        outRect.bottom = mSpacing;
    }
}
