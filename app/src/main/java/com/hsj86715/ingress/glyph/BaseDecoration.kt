package com.hsj86715.ingress.glyph

import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.StaggeredGridLayoutManager
import android.util.Log

/**
 * Created by hushujun on 2017/4/10.
 */
open class BaseDecoration : RecyclerView.ItemDecoration() {
    val TAG: String = "BaseDecoration"

    fun isFirstColumn(layoutManager: RecyclerView.LayoutManager, position: Int): Boolean {
        val spanCount: Int
        val orientation: Int
        val itemCount: Int = layoutManager.itemCount
        if (layoutManager is GridLayoutManager) {
            spanCount = layoutManager.spanCount
            orientation = layoutManager.orientation
            if (orientation == GridLayoutManager.VERTICAL) {
                return position % spanCount == 0
            } else {
                val more = itemCount % spanCount
                if (more == 0) {
                    return position % spanCount == 0
                } else {
                    val row = position / spanCount
                    if (row < more) {
                        return position % spanCount == 0
                    } else {
                        return (position + row - more) % spanCount == 0
                    }
                }
            }
        } else if (layoutManager is LinearLayoutManager) {
            orientation = layoutManager.orientation
            if (orientation == LinearLayoutManager.VERTICAL) {
                return true
            } else {
                return position == 0
            }
        } else if (layoutManager is StaggeredGridLayoutManager) {
            Log.i(TAG, "isFirstColumn==>mLayoutManager is StaggeredGridLayoutManager")
            // TODO: 2017/4/17
        }
        return false
    }

    fun isLastColumn(layoutManager: RecyclerView.LayoutManager, position: Int): Boolean {
        val spanCount: Int
        val orientation: Int
        val itemCount: Int = layoutManager.itemCount
        if (layoutManager is GridLayoutManager) {
            spanCount = layoutManager.spanCount
            orientation = layoutManager.orientation
            if (orientation == GridLayoutManager.VERTICAL) {
                return position % spanCount == (spanCount - 1) || position == itemCount - 1
            } else {
                val more = itemCount % spanCount
                if (more == 0) {
                    return position % spanCount == spanCount - 1
                } else {
                    val row = position / spanCount
                    if (row < more) {
                        return position % spanCount == spanCount - 1
                    } else {
                        return (position + row - more) % spanCount == spanCount - 1
                    }
                }
            }
        } else if (layoutManager is LinearLayoutManager) {
            orientation = layoutManager.orientation
            if (orientation == LinearLayoutManager.VERTICAL) {
                return true
            } else {
                return position == itemCount - 1
            }
        } else if (layoutManager is StaggeredGridLayoutManager) {
            Log.i(TAG, "isLastColumn==>mLayoutManager is StaggeredGridLayoutManager")
            // TODO: 2017/4/17
//            spanCount = mLayoutManager.spanCount
//            orientation = mLayoutManager.orientation
//            if (orientation == StaggeredGridLayoutManager.VERTICAL) {
//
//            } else {
//
//            }
        }
        return false
    }

    fun isFirstRow(layoutManager: RecyclerView.LayoutManager, position: Int): Boolean {
        val spanCount: Int
        val orientation: Int
        val itemCount: Int = layoutManager.itemCount
        if (layoutManager is GridLayoutManager) {
            spanCount = layoutManager.spanCount
            orientation = layoutManager.orientation
            if (orientation == GridLayoutManager.VERTICAL) {
                return position < spanCount
            } else {
                val more = itemCount % spanCount
                if (more == 0) {
                    return position < itemCount / spanCount
                } else {
                    return position < itemCount / spanCount + 1
                }
            }
        } else if (layoutManager is LinearLayoutManager) {
            orientation = layoutManager.orientation
            if (orientation == LinearLayoutManager.VERTICAL) {
                return position == 0
            } else {
                return true
            }
        } else if (layoutManager is StaggeredGridLayoutManager) {
            Log.i(TAG, "isFirstRow==>mLayoutManager is StaggeredGridLayoutManager")
            // TODO: 2017/4/17
        }
        return false
    }

    fun isLastRow(layoutManager: RecyclerView.LayoutManager, position: Int): Boolean {
        val spanCount: Int
        val orientation: Int
        val itemCount: Int = layoutManager.itemCount
        if (layoutManager is GridLayoutManager) {
            spanCount = layoutManager.spanCount
            orientation = layoutManager.orientation
            if (orientation == GridLayoutManager.VERTICAL) {
                return position >= itemCount - spanCount
            } else {
                val more = itemCount % spanCount
                val row = position / spanCount + 1
                return row == spanCount || spanCount * more < position
            }
        } else if (layoutManager is LinearLayoutManager) {
            orientation = layoutManager.orientation
            if (orientation == LinearLayoutManager.VERTICAL) {
                return position == itemCount - 1
            } else {
                return true
            }
        } else if (layoutManager is StaggeredGridLayoutManager) {
            Log.i(TAG, "isLastRow==>mLayoutManager is StaggeredGridLayoutManager")
            // TODO: 2017/4/17
        }
        return false
    }
}