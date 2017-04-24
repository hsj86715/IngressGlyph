package com.hsj86715.ingress.glyph.pages

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.hsj86715.ingress.glyph.R
import com.hsj86715.ingress.glyph.view.HackSequenceListener
import com.hsj86715.ingress.glyph.view.MultiGlyphView

/**
 * Created by hushujun on 2017/4/21.
 */
class HackSequencesAdapter : RecyclerView.Adapter<HackSequencesAdapter.ViewHolder>() {
    var mHackSequences: Map<String, Array<Array<String>>>? = null
    var mListener: HackSequenceListener? = null

    fun setHackSequences(hackSequences: Map<String, Array<Array<String>>>?) {
        mHackSequences = hackSequences
        notifyDataSetChanged()
    }

    fun setHackListener(listener: HackSequenceListener) {
        mListener = listener
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.glyphPreView.setSequences(getSequences(position))
        holder.glyphPreView.setHackSequenceListener(mListener)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val holder = ViewHolder(LayoutInflater.from(parent.context)
                .inflate(R.layout.item_glyph_base, parent, false))
        return holder
    }

    override fun getItemCount(): Int {
        val count = mHackSequences!!.keys.sumBy { mHackSequences!!.getValue(it).size }
        return count
    }

    fun getSequences(position: Int): Array<String> {
        val entryArray: Array<Map.Entry<String, Array<Array<String>>>> = mHackSequences!!.entries.toTypedArray()
        var idxOfMap = 0
        var idxInArray = 0
        var alreadyPosition = 0
        while (alreadyPosition < position) {
            var mapSize = entryArray[idxOfMap].value.size
            if (alreadyPosition + mapSize > position) {
                break
            }
            alreadyPosition += mapSize
            idxOfMap++
        }
        idxInArray = position - alreadyPosition
        return entryArray[idxOfMap].value[idxInArray]
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var glyphPreView: MultiGlyphView = itemView.findViewById(R.id.item_glyph_pre) as MultiGlyphView
    }
}