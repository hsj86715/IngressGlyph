package com.hsj86715.ingress.glyph.view

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.hsj86715.ingress.glyph.R
import com.hsj86715.ingress.glyphres.view.MultiGlyphView
import com.hsj86715.ingress.glyphres.view.SequenceClickListener

/**
 * Created by hushujun on 2017/4/24.
 */
abstract class BaseRecyclerAdapter : RecyclerView.Adapter<BaseRecyclerAdapter.ViewHolder>() {
    protected var mClickedListener: SequenceClickListener? = null

    fun setSequenceClickListener(listener: SequenceClickListener) {
        mClickedListener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent!!.context)
                .inflate(R.layout.item_glyph_base, parent, false))
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var glyphPreView: MultiGlyphView = itemView.findViewById(R.id.item_glyph_pre) as MultiGlyphView
    }

}