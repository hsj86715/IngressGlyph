package com.hsj86715.ingress.glyph.pages

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.hsj86715.ingress.glyph.R
import com.hsj86715.ingress.glyph.data.BaseGlyphData
import com.hsj86715.ingress.glyph.data.BaseGlyphData.Category
import com.hsj86715.ingress.glyph.tools.Utils
import com.hsj86715.ingress.glyph.view.BaseRecyclerAdapter
import com.hsj86715.ingress.glyph.view.MultiGlyphView
import com.hsj86715.ingress.glyph.view.SequenceClickListener

/**
 * Created by hushujun on 16/5/17.
 */
class GlyphBaseAdapter : BaseRecyclerAdapter<GlyphBaseAdapter.ViewHolder>() {
    private var mGlyphs: Array<Map.Entry<String, IntArray>>? = null
    private var mClickListener: SequenceClickListener? = null

    fun setGlyphCategory(@Category category: String) {
        mGlyphs = arrayOf<Map.Entry<String, IntArray>>()
        mGlyphs = BaseGlyphData.getInstance().getGlyphByCategory(category).entries.toTypedArray<Map.Entry<String, IntArray>>()
        notifyDataSetChanged()
    }

    override fun setSequenceClickListener(listener: SequenceClickListener) {
        mClickListener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val holder = ViewHolder(LayoutInflater.from(parent.context)
                .inflate(R.layout.item_glyph_base, parent, false))
        return holder
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is ViewHolder) {
            holder.glyphPreView.setSequences(mGlyphs!![position].key)
            holder.itemView.setBackgroundColor(Utils.stringToColor(mGlyphs!![position].key))
            if (mClickListener != null) {
                holder.itemView.setOnClickListener { mClickListener!!.onSequenceClicked(mGlyphs!![position].key) }
            }
        }
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getItemCount(): Int {
        return mGlyphs!!.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var glyphPreView: MultiGlyphView = itemView.findViewById(R.id.item_glyph_pre) as MultiGlyphView
    }
}
