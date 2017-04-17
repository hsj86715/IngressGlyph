package com.hsj86715.ingress.glyph.view

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Rect
import android.support.v7.widget.RecyclerView
import android.view.View
import com.hsj86715.ingress.glyph.BaseDecoration


/**
 * Created by hushujun on 2017/4/10.
 */
class PinnedSectionDecoration(context: Context, callback: DecorationCallback,
                              layoutManager: RecyclerView.LayoutManager) : BaseDecoration(layoutManager) {
    var mCallBack: DecorationCallback = callback
    var paint: Paint = Paint()
    var topGap: Int = context.resources.displayMetrics.densityDpi * 40

    interface DecorationCallback {
        abstract fun getGroupHeadPath(position: Int): IntArray

        abstract fun getGroupName(position: Int): String
    }

    override fun onDraw(c: Canvas?, parent: RecyclerView?, state: RecyclerView.State?) {
        super.onDraw(c, parent, state)
    }

    override fun onDrawOver(c: Canvas?, parent: RecyclerView?, state: RecyclerView.State?) {
        super.onDrawOver(c, parent, state)
    }

    override fun getItemOffsets(outRect: Rect, view: View?, parent: RecyclerView, state: RecyclerView.State?) {
        val pos = parent.getChildAdapterPosition(view)
        if (isFirstInGroup(pos)) {
            outRect.top = topGap + 5
        } else {
            outRect.top = 1
        }
    }

    fun drawHead(canvas: Canvas): Unit {

    }

    fun isFirstInGroup(position: Int): Boolean {
        if (position == 0) {
            return true
        } else {
            var namePre: String = mCallBack.getGroupName(position - 1)
            var nameCurr: String = mCallBack.getGroupName(position)
            return !namePre.equals(nameCurr)
        }
    }
}