package com.hsj86715.ingress.glyph.pages

import com.hsj86715.ingress.glyph.tools.Utils
import com.hsj86715.ingress.glyph.view.BaseRecyclerAdapter

/**
 * Created by hushujun on 2017/4/18.
 */
class GlyphPairsAdapter(glyphPairs: Array<Array<String>>) : BaseRecyclerAdapter() {
    var mGlyphPairs: Array<Array<String>> = glyphPairs
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.glyphPreView.setSequences(mGlyphPairs[position])
        holder.itemView.setBackgroundColor(Utils.stringToColor(mGlyphPairs[position][0]))
        holder.glyphPreView.setHackSequenceListener(mClickedListener)
    }

    override fun getItemCount(): Int {
        return mGlyphPairs.size
    }
}