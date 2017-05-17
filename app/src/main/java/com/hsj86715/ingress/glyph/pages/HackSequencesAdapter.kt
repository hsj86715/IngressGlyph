package com.hsj86715.ingress.glyph.pages

import com.hsj86715.ingress.glyph.tools.Utils
import com.hsj86715.ingress.glyph.view.BaseRecyclerAdapter
import com.hsj86715.ingress.glyphres.view.DecorationCallback

/**
 * Created by hushujun on 2017/4/21.
 */
class HackSequencesAdapter : BaseRecyclerAdapter(), DecorationCallback {
    var mHackSequences: Array<Map.Entry<String, Array<Array<String>>>>? = null
    var mSequencesLength: Int = 2

    fun setHackSequences(hackSequences: Map<String, Array<Array<String>>>?, length: Int = 2) {
        mHackSequences = hackSequences!!.entries.toTypedArray()
        mSequencesLength = length
        notifyDataSetChanged()
    }

    fun getSequencesLength(): Int {
        return mSequencesLength
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val sequence = getSequences(position)
        holder.glyphPreView.setSequences(sequence)
        holder.glyphPreView.setHackSequenceListener(mClickedListener)
        holder.itemView.setBackgroundColor(Utils.stringToColor(sequence[0]))
    }

    override fun getItemCount(): Int {
//        val count = mHackSequences!!.keys.sumBy { mHackSequences!!.getValue(it).size }
        val count = mHackSequences!!.sumBy { it.value.size }
        return count
    }

    override fun getGroupName(position: Int): String {
        return getSequences(position)[0]
    }

    override fun isFirstInGroup(position: Int): Boolean {
        val idxArray = findIdxInData(position)
        return idxArray[1] == 0
    }

    fun getSequences(position: Int): Array<String> {
        val idxArray = findIdxInData(position)
        return mHackSequences!![idxArray[0]].value[idxArray[1]]
    }

    fun findIdxInData(position: Int): IntArray {
        var idxOfMap = 0
        var idxInArray = 0
        var alreadyPosition = 0
        while (alreadyPosition < position) {
            var mapSize = mHackSequences!![idxOfMap].value.size
            if (alreadyPosition + mapSize > position) {
                break
            }
            alreadyPosition += mapSize
            idxOfMap++
        }
        idxInArray = position - alreadyPosition
        return intArrayOf(idxOfMap, idxInArray)
    }
}