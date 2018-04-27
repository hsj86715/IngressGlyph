package com.hsj86715.ingress.glyph.pages;

import com.hsj86715.ingress.glyph.view.BaseRecyclerAdapter;
import com.hsj86715.ingress.glyphres.data.HackList;
import com.hsj86715.ingress.glyphres.tools.Utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by hushujun on 2017/5/16.
 */

public class HackSequencesAdapter extends BaseRecyclerAdapter{
    private List<HackList> mHackSequences;

    public void setHackSequences(List<HackList> hackSequences) {
        if (hackSequences == null) {
            mHackSequences = new ArrayList<>();
        } else {
            mHackSequences = hackSequences;
        }
        Collections.sort(mHackSequences);
        notifyDataSetChanged();
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        HackList hackList = mHackSequences.get(position);
        holder.glyphPreView.setSequences(hackList.getSequences());
        holder.glyphPreView.setHackSequenceListener(mClickListener);
        holder.itemView.setBackgroundColor(Utils.stringToColor(hackList.getHead().getName()));
    }

    @Override
    public int getItemCount() {
        if (mHackSequences == null) {
            return 0;
        }
        return mHackSequences.size();
    }
}
