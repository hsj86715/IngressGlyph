package com.hsj86715.ingress.glyph.pages;

import com.hsj86715.ingress.glyph.tools.Utils;
import com.hsj86715.ingress.glyph.view.BaseRecyclerAdapter;
import com.hsj86715.ingress.glyphres.view.DecorationCallback;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by hushujun on 2017/5/16.
 */

public class HackSequencesAdapter extends BaseRecyclerAdapter implements DecorationCallback {
    private Map<String, String[][]> mHackSequences;
    private List<String> mHackKeys;
    private int mSequencesLength = 2;

    public void setHackSequences(Map<String, String[][]> hackSequences, int length) {
        if (hackSequences == null) {
            mHackSequences = new HashMap<>();
        } else {
            mHackSequences = hackSequences;
        }
        mHackKeys = new ArrayList<>(mHackSequences.keySet());
        mSequencesLength = length;
        notifyDataSetChanged();
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        String[] sequence = getSequences(position);
        holder.glyphPreView.setSequences(sequence);
        holder.glyphPreView.setHackSequenceListener(mClickListener);
        holder.itemView.setBackgroundColor(Utils.stringToColor(sequence[0]));
    }

    @Override
    public int getItemCount() {
        int count = 0;
        for (String str : mHackKeys) {
            count += mHackSequences.get(str).length;
        }
        return count;
    }

    @Override
    public String getGroupName(int position) {
        return null;
    }

    @Override
    public boolean isFirstInGroup(int position) {
        return false;
    }

    public int getSequencesLength() {
        return mSequencesLength;
    }

    private String[] getSequences(int position) {
        int[] idxArray = findIdxInData(position);
        return mHackSequences.get(mHackKeys.get(idxArray[0]))[idxArray[1]];
    }

    private int[] findIdxInData(int position) {
        int idxOfGroup = 0;
        int idxInGroup = 0;
        int alreadyPosition = 0;
        while (alreadyPosition < position) {
            int mapSize = mHackSequences.get(mHackKeys.get(idxOfGroup)).length;
            if (alreadyPosition + mapSize > position) {
                break;
            }
            alreadyPosition += mapSize;
            idxOfGroup++;
        }
        idxInGroup = position - alreadyPosition;
        return new int[]{idxOfGroup, idxInGroup};
    }
}
