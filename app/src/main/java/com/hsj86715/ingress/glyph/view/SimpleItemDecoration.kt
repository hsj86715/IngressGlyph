package com.hsj86715.ingress.glyph.view

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect
import android.support.v7.widget.RecyclerView
import android.view.View
import com.hsj86715.ingress.glyph.BaseDecoration

/**
 * Created by hushujun on 2017/4/7.
 */
class SimpleItemDecoration : BaseDecoration() {

    val dividerPaint: Paint = Paint()

    init {
        dividerPaint.color = Color.GRAY
    }

    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State?) {
        val position = parent.getChildLayoutPosition(view)
        outRect.set(getOutRect(parent.layoutManager, position))
    }

    override fun onDraw(c: Canvas, parent: RecyclerView, state: RecyclerView.State?) {
        super.onDraw(c, parent, state)
        val childCount = parent.childCount
        val layoutManager = parent.layoutManager
        var i = 0
        while (i < childCount) {
            val view = parent.getChildAt(i)
            val outRect = getOutRect(layoutManager, i)
            //draw HORIZONTAL divider
            var rect = Rect()
            rect.left = view.left
            rect.top = view.bottom
            rect.right = view.right + outRect.right
            rect.bottom = view.bottom + outRect.bottom
            c.drawRect(rect, dividerPaint)
            //draw VERTICAL divider
            rect = Rect()
            rect.left = view.right
            rect.top = view.top
            rect.right = view.right + outRect.right
            rect.bottom = view.bottom + outRect.bottom
            c.drawRect(rect, dividerPaint)
            i++
        }
    }

    fun getOutRect(layoutManager: RecyclerView.LayoutManager, position: Int): Rect {
        var right = 1
        var bottom = 2
        if (isLastColumn(layoutManager, position)) {
            right = 0
        }
        if (isLastRow(layoutManager, position)) {
            bottom = 0
        }
        return Rect(0, 0, right, bottom)
    }
}