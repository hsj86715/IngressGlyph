package com.hsj86715.ingress.glyph.pages

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.hsj86715.ingress.glyph.R
import com.hsj86715.ingress.glyph.view.IngressGlyphView

/**
 * Created by hushujun on 2017/4/6.
 */
class GlyphPairsAdapter : RecyclerView.Adapter<GlyphPairsAdapter.ViewHolder> {
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getItemCount(): Int {
        return mGlyphPairs.size
    }

    var mGlyphPairs: List<Map<String, Array<Int>>>
    var mContext: Context;

    constructor(glyphPairs: List<Map<String, Array<Int>>>, context: Context) {
        mGlyphPairs = glyphPairs
        mContext = context;
    }

    fun setGlyPairs(glyphPairs: List<Map<String, Array<Int>>>): Unit {
        mGlyphPairs = glyphPairs
        notifyDataSetChanged()
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var sequencePre: IngressGlyphView
        var sequenceTx: TextView

        init {
            sequencePre = itemView.findViewById(R.id.item_sequence_pre) as IngressGlyphView
            sequenceTx = itemView.findViewById(R.id.item_sequence_name) as TextView
        }
    }
}