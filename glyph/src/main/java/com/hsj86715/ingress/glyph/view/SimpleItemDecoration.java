package com.hsj86715.ingress.glyph.view;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.hsj86715.ingress.glyph.BaseDecoration;

/**
 * Created by hushujun on 2017/5/16.
 */

public class SimpleItemDecoration extends BaseDecoration {
    private final Paint mDividerPaint = new Paint();

    public SimpleItemDecoration() {
        mDividerPaint.setColor(Color.GRAY);
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        int position = parent.getChildLayoutPosition(view);
        outRect.set(getOutRect(parent.getLayoutManager(), position));
    }

    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDraw(c, parent, state);
        int childCount = parent.getChildCount();
        RecyclerView.LayoutManager layoutManager = parent.getLayoutManager();
        int i = 0;
        while (i < childCount) {
            View view = parent.getChildAt(i);
            Rect outRect = getOutRect(layoutManager, i);
            //draw HORIZONTAL divider
            Rect rect = new Rect();
            rect.left = view.getLeft();
            rect.top = view.getBottom();
            rect.right = view.getRight() + outRect.right;
            rect.bottom = view.getBottom() + outRect.bottom;
            c.drawRect(rect, mDividerPaint);
            //draw VERTICAL divider
            rect = new Rect();
            rect.left = view.getRight();
            rect.top = view.getTop();
            rect.right = view.getRight() + outRect.right;
            rect.bottom = view.getBottom() + outRect.bottom;
            c.drawRect(rect, mDividerPaint);
            i++;
        }
    }

    private Rect getOutRect(RecyclerView.LayoutManager layoutManager, int position) {
        int right = 1;
        int bottom = 2;
        if (isLastColumn(layoutManager, position)) {
            right = 0;
        }
        if (isLastRow(layoutManager, position)) {
            bottom = 0;
        }
        return new Rect(0, 0, right, bottom);
    }
}
