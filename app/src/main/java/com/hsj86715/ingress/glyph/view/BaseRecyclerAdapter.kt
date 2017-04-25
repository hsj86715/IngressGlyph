package com.hsj86715.ingress.glyph.view

import android.support.v7.widget.RecyclerView

/**
 * Created by hushujun on 2017/4/24.
 */
abstract class BaseRecyclerAdapter<T : RecyclerView.ViewHolder> : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    abstract fun setSequenceClickListener(listener: SequenceClickListener)

}