package com.hsj86715.ingress.glyph.view

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Rect
import android.support.v7.widget.RecyclerView
import android.view.View
import com.hsj86715.ingress.glyph.BaseDecoration
import com.hsj86715.ingress.glyphres.view.DecorationCallback


/**
 * Created by hushujun on 2017/4/10.
 */
class PinnedSectionDecoration(context: Context, callback: DecorationCallback) : BaseDecoration() {
    var mCallBack: DecorationCallback = callback
    var paint: Paint = Paint()
    var topGap: Int = (context.resources.displayMetrics.density * 40).toInt()

    init {
        paint.setARGB(255, 8, 112, 117)
    }

    fun setDecorationCallback(callback: DecorationCallback) {
        mCallBack = callback
    }

    override fun onDraw(c: Canvas?, parent: RecyclerView?, state: RecyclerView.State?) {
        super.onDraw(c, parent, state)
        val childCount = parent!!.childCount
        var i = 0
        while (i < childCount) {
            if (mCallBack.isFirstInGroup(i)) {
                val view = parent.getChildAt(i)
                val outRect = getOutRect(i)
                var rect = Rect()
                rect.left = view.left
                rect.top = view.top - outRect.top
                rect.right = view.right
                rect.bottom = view.top
                c!!.drawRect(rect, paint)
            }
            i++
        }
    }

    override fun onDrawOver(c: Canvas?, parent: RecyclerView?, state: RecyclerView.State?) {
        super.onDrawOver(c, parent, state)
    }

    override fun getItemOffsets(outRect: Rect, view: View?, parent: RecyclerView, state: RecyclerView.State?) {
        val pos = parent.getChildAdapterPosition(view)
        outRect.set(getOutRect(pos))
    }

    fun drawHead(canvas: Canvas): Unit {

    }

    fun getOutRect(position: Int): Rect {
        var top = 0
        if (mCallBack.isFirstInGroup(position)) {
            top = 10
        }
        return Rect(0, top, 0, 0)
    }

//    fun isFirstInGroup(position: Int): Boolean {
//        if (position == 0) {
//            return true
//        } else {
//            var namePre: String = mCallBack.getGroupName(position - 1)
//            var nameCurr: String = mCallBack.getGroupName(position)
//            return !namePre.equals(nameCurr)
//        }
//    }
}