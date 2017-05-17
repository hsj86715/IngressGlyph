package com.hsj86715.ingress.glyph.pages

import com.hsj86715.ingress.glyph.tools.Utils
import com.hsj86715.ingress.glyph.view.BaseRecyclerAdapter
import com.hsj86715.ingress.glyphres.data.BaseGlyphData
import com.hsj86715.ingress.glyphres.data.BaseGlyphData.Category

/**
 * Created by hushujun on 16/5/17.
 */
class GlyphBaseAdapter : BaseRecyclerAdapter() {
    private var mGlyphs: Array<Map.Entry<String, IntArray>>? = null

    fun setGlyphCategory(@Category category: String) {
        mGlyphs = arrayOf<Map.Entry<String, IntArray>>()
        mGlyphs = BaseGlyphData.getInstance().getGlyphByCategory(category).entries.toTypedArray<Map.Entry<String, IntArray>>()
        notifyDataSetChanged()
    }

    //position must be final
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val holderPos = position
        holder.glyphPreView.setSequences(mGlyphs!![holderPos].key)
        holder.itemView.setBackgroundColor(Utils.stringToColor(mGlyphs!![holderPos].key))
        holder.itemView.setOnClickListener { mClickedListener!!.onSequenceClicked(mGlyphs!![holderPos].key) }
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getItemCount(): Int {
        return mGlyphs!!.size
    }
}
