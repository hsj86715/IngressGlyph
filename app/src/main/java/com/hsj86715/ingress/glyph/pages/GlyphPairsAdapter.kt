package com.hsj86715.ingress.glyph.pages

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.hsj86715.ingress.glyph.R
import com.hsj86715.ingress.glyph.tools.Utils
import com.hsj86715.ingress.glyph.view.BaseRecyclerAdapter
import com.hsj86715.ingress.glyph.view.MultiGlyphView
import com.hsj86715.ingress.glyph.view.SequenceClickListener

/**
 * Created by hushujun on 2017/4/18.
 */
class GlyphPairsAdapter(glyphPairs: Array<Array<String>>) : BaseRecyclerAdapter<GlyphPairsAdapter.ViewHolder>() {
    var mListener: SequenceClickListener? = null
    var mGlyphPairs: Array<Array<String>> = glyphPairs
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is ViewHolder) {
            holder.glyphPreView.setSequences(mGlyphPairs[position])
            holder.itemView.setBackgroundColor(Utils.stringToColor(mGlyphPairs[position][0]))
            holder.glyphPreView.setHackSequenceListener(mListener)
        }
    }

    override fun setSequenceClickListener(listener: SequenceClickListener) {
        mListener = listener
    }

    override fun getItemCount(): Int {
        return mGlyphPairs.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val holder = ViewHolder(LayoutInflater.from(parent.context)
                .inflate(R.layout.item_glyph_base, parent, false))
        return holder
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var glyphPreView: MultiGlyphView = itemView.findViewById(R.id.item_glyph_pre) as MultiGlyphView
    }
}