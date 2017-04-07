package com.hsj86715.ingress.glyph.pages

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

import com.hsj86715.ingress.glyph.R
import com.hsj86715.ingress.glyph.data.BaseGlyphData
import com.hsj86715.ingress.glyph.data.BaseGlyphData.Category
import com.hsj86715.ingress.glyph.view.HackSequenceListener
import com.hsj86715.ingress.glyph.view.IngressGlyphView

/**
 * Created by hushujun on 16/5/17.
 */
class GlyphBaseAdapter : RecyclerView.Adapter<GlyphBaseAdapter.ViewHolder>() {
    private var mGlyphs: Array<Map.Entry<String, IntArray>>? = null
    private var mClickListener: HackSequenceListener? = null

    fun setGlyphCategory(@Category category: String) {
        mGlyphs = arrayOf<Map.Entry<String, IntArray>>()
        mGlyphs = BaseGlyphData.getInstance().getGlyphByCategory(category).entries.toTypedArray<Map.Entry<String, IntArray>>()
        notifyDataSetChanged()
    }

    fun setSequenceClickListener(listener: HackSequenceListener) {
        mClickListener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val holder = ViewHolder(LayoutInflater.from(parent.context)
                .inflate(R.layout.item_glyph_base, parent, false))
        return holder
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.nameTx.text = mGlyphs!![position].key
        holder.glyphPreView.drawPath(mGlyphs!![position].value)
        if (mClickListener != null) {
            holder.itemView.setOnClickListener { mClickListener!!.onSequenceClicked(mGlyphs!![position]) }
        }
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getItemCount(): Int {
        return mGlyphs!!.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var glyphPreView: IngressGlyphView
        var nameTx: TextView

        init {
            glyphPreView = itemView.findViewById(R.id.item_glyph_pre) as IngressGlyphView
            nameTx = itemView.findViewById(R.id.item_glyph_name) as TextView
        }
    }
}
