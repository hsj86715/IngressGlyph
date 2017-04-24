package com.hsj86715.ingress.glyph.pages

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.hsj86715.ingress.glyph.R
import com.hsj86715.ingress.glyph.view.HackSequenceListener
import com.hsj86715.ingress.glyph.view.MultiGlyphView

/**
 * Created by hushujun on 2017/4/18.
 */
class GlyphPairsAdapter(glyphPairs: Array<Array<String>>)
    : RecyclerView.Adapter<GlyphPairsAdapter.ViewHolder>() {
    var mListener: HackSequenceListener? = null
    var mGlyphPairs: Array<Array<String>> = glyphPairs
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.glyphPreView.setSequences(mGlyphPairs[position])
        holder.glyphPreView.setHackSequenceListener(mListener)
    }

    fun setSequenceClickListener(listener: HackSequenceListener) {
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