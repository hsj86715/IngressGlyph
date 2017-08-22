package com.hsj86715.ingress.glyph.pages

import android.app.Activity
import android.app.Fragment
import android.os.Bundle
import android.support.annotation.IdRes
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.HorizontalScrollView
import android.widget.RadioGroup
import com.google.firebase.analytics.FirebaseAnalytics
import com.hsj86715.ingress.glyph.R
import com.hsj86715.ingress.glyph.view.BaseRecyclerAdapter
import com.hsj86715.ingress.glyph.view.SimpleItemDecoration
import com.hsj86715.ingress.glyphres.data.BaseGlyphData
import com.hsj86715.ingress.glyphres.view.SequenceClickListener

/**
 * Created by hushujun on 2017/4/24.
 */

class GlyphSequencesFragment : Fragment(), RadioGroup.OnCheckedChangeListener {

    private var mSequenceListener: SequenceClickListener? = null

    private var mGlyphAdapter: BaseRecyclerAdapter? = null
    private var mCategoryContainer: HorizontalScrollView? = null
    private var mCategoryRG: RadioGroup? = null
    private var mRecyclerView: RecyclerView? = null

    private var mLayoutManager: RecyclerView.LayoutManager? = null
    //    private PinnedSectionDecoration mSectionDecoration;
    private var mFirebaseAnalytics: FirebaseAnalytics? = null

    private var mWhichPage = PAGE_BASE

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.frag_sequences, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mRecyclerView = view.findViewById(R.id.grid_base)
        mRecyclerView!!.addItemDecoration(SimpleItemDecoration())
        mCategoryContainer = view.findViewById(R.id.category_container)
        mCategoryRG = view.findViewById(R.id.categories)
        mCategoryRG!!.setOnCheckedChangeListener(this)

        if (savedInstanceState != null && savedInstanceState.containsKey("whichPage")) {
            mWhichPage = savedInstanceState.getString("whichPage")
            initPage(mWhichPage)
        } else {
            mCategoryContainer!!.visibility = View.VISIBLE
            mLayoutManager = GridLayoutManager(view.context, 3)
            mRecyclerView!!.layoutManager = mLayoutManager
            mGlyphAdapter = GlyphBaseAdapter()
            mGlyphAdapter!!.setSequenceClickListener(mSequenceListener!!)
            mRecyclerView!!.adapter = mGlyphAdapter
            (mGlyphAdapter as GlyphBaseAdapter).setGlyphCategory(BaseGlyphData.C_ALL)
        }
    }

    //    @GlyphPage
    private fun initPage(whichPage: String) {
        when (whichPage) {
            PAGE_PAIRS -> showPairsSequences()
            PAGE_2HACK -> updateSequences(2)
            PAGE_3HACK -> updateSequences(3)
            PAGE_4HACK -> updateSequences(4)
            PAGE_5HACK -> updateSequences(5)
            PAGE_BASE -> showBaseSequences()
            else -> showBaseSequences()
        }
    }

    override fun onAttach(activity: Activity) {
        super.onAttach(activity)
        if (activity is SequenceClickListener) {
            mSequenceListener = activity
        }
        mFirebaseAnalytics = FirebaseAnalytics.getInstance(activity)
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        var outState = outState
        if (outState == null) {
            outState = Bundle()
        }
        outState.putString("whichPage", mWhichPage)
        super.onSaveInstanceState(outState)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        var bundle = Bundle()
        bundle.putString(FirebaseAnalytics.Param.ITEM_ID, item.getItemId().toString())
        bundle.putString(FirebaseAnalytics.Param.ITEM_NAME, item.getTitle().toString())
        bundle.putString(FirebaseAnalytics.Param.GROUP_ID, "OptionMenu");
        mFirebaseAnalytics!!.logEvent(FirebaseAnalytics.Event.SELECT_CONTENT, bundle)

        when (item.itemId) {
            R.id.base -> {
                mWhichPage = PAGE_BASE
                showBaseSequences()
            }
            R.id.pairs -> {
                mWhichPage = PAGE_PAIRS
                showPairsSequences()
            }
            R.id.two -> {
                mWhichPage = PAGE_2HACK
                updateSequences(2)
            }
            R.id.three -> {
                mWhichPage = PAGE_3HACK
                updateSequences(3)
            }
            R.id.four -> {
                mWhichPage = PAGE_4HACK
                updateSequences(4)
            }
            R.id.five -> {
                mWhichPage = PAGE_5HACK
                updateSequences(5)
            }
            else -> {
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun showBaseSequences() {
        if (mGlyphAdapter != null && mGlyphAdapter is GlyphBaseAdapter) {
            return
        } else {
            //                    mRecyclerView.removeItemDecoration(mSectionDecoration);
            if (mSequenceListener != null) {
                mSequenceListener!!.clearSequence()
            }
            mCategoryContainer!!.visibility = View.VISIBLE
            if (mLayoutManager != null && mLayoutManager is GridLayoutManager) {
                (mLayoutManager as GridLayoutManager).spanCount = 3
            } else {
                mLayoutManager = GridLayoutManager(activity, 3)
                mRecyclerView!!.layoutManager = mLayoutManager
            }
            mGlyphAdapter = GlyphBaseAdapter()
            mGlyphAdapter!!.setSequenceClickListener(mSequenceListener!!)
            mRecyclerView!!.adapter = mGlyphAdapter
            val checkedId = mCategoryRG!!.checkedRadioButtonId
            onCheckedChanged(mCategoryRG!!, checkedId)
        }
    }

    private fun showPairsSequences() {
        if (mGlyphAdapter != null && mGlyphAdapter is GlyphPairsAdapter) {
            return
        } else {
            if (mSequenceListener != null) {
                mSequenceListener!!.clearSequence()
            }
            if (mGlyphAdapter != null && mGlyphAdapter is GlyphBaseAdapter) {
                mCategoryContainer!!.visibility = View.GONE
            }
            //                    mRecyclerView.removeItemDecoration(mSectionDecoration);
            if (mLayoutManager != null && mLayoutManager is GridLayoutManager) {
                (mLayoutManager as GridLayoutManager).spanCount = 2
            } else {
                mLayoutManager = GridLayoutManager(activity, 2)
                mRecyclerView!!.layoutManager = mLayoutManager
            }
            mGlyphAdapter = GlyphPairsAdapter(BaseGlyphData.getInstance().glyphPairs)
            mGlyphAdapter!!.setSequenceClickListener(mSequenceListener!!)
            mRecyclerView!!.adapter = mGlyphAdapter
        }
    }

    private fun updateSequences(sequenceLength: Int) {
        val data: Map<String, Array<Array<String>>>
        when (sequenceLength) {
            3 -> data = BaseGlyphData.getInstance().threeSequences
            4 -> data = BaseGlyphData.getInstance().fourSequences
            5 -> data = BaseGlyphData.getInstance().fiveSequences
            2 -> data = BaseGlyphData.getInstance().twoSequences
            else -> data = BaseGlyphData.getInstance().twoSequences
        }
        if (mLayoutManager == null || mLayoutManager is GridLayoutManager) {
            mLayoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
            mRecyclerView!!.layoutManager = mLayoutManager
        }
        if (mGlyphAdapter != null && mGlyphAdapter is HackSequencesAdapter) {
            if ((mGlyphAdapter as HackSequencesAdapter).getSequencesLength() == sequenceLength) {
                return
            } else {
                if (mSequenceListener != null) {
                    mSequenceListener!!.clearSequence()
                }
                (mGlyphAdapter as HackSequencesAdapter).setHackSequences(data, sequenceLength)
            }
        } else {
            if (mGlyphAdapter != null && mGlyphAdapter is GlyphBaseAdapter) {
                mCategoryContainer!!.visibility = View.GONE
            }
            if (mSequenceListener != null) {
                mSequenceListener!!.clearSequence()
            }
            mGlyphAdapter = HackSequencesAdapter()
            //            if (mSectionDecoration == null) {
            //                mSectionDecoration = new PinnedSectionDecoration(getContext(),
            //                        (PinnedSectionDecoration.DecorationCallback) mGlyphAdapter);
            //            } else {
            //                mSectionDecoration.setDecorationCallback((PinnedSectionDecoration.DecorationCallback) mGlyphAdapter);
            //            }
            //            mRecyclerView.addItemDecoration(mSectionDecoration);
            mGlyphAdapter!!.setSequenceClickListener(mSequenceListener!!)
            (mGlyphAdapter as HackSequencesAdapter).setHackSequences(data, sequenceLength)
            mRecyclerView!!.adapter = mGlyphAdapter
        }
    }

    override fun onCheckedChanged(group: RadioGroup, @IdRes checkedId: Int) {

        var category: String? = null
        when (checkedId) {
            R.id.category_human -> category = BaseGlyphData.C_HUMAN
            R.id.category_action -> category = BaseGlyphData.C_ACTION
            R.id.category_thought -> category = BaseGlyphData.C_THOUGHT
            R.id.category_fludire -> category = BaseGlyphData.C_FLU_DIRE
            R.id.category_ts -> category = BaseGlyphData.C_TIME_SPACE
            R.id.category_conenv -> category = BaseGlyphData.C_COND_ENV
            R.id.category_all -> category = BaseGlyphData.C_ALL
            else -> category = BaseGlyphData.C_ALL
        }
        if (mGlyphAdapter is GlyphBaseAdapter) {
            (mGlyphAdapter as GlyphBaseAdapter).setGlyphCategory(category)
        }

        var bundle = Bundle()
        bundle.putString(FirebaseAnalytics.Param.ITEM_ID, checkedId.toString())
        bundle.putString(FirebaseAnalytics.Param.ITEM_CATEGORY, category)
        mFirebaseAnalytics!!.logEvent(FirebaseAnalytics.Event.SELECT_CONTENT, bundle)
    }

//    @StringDef(PAGE_BASE, PAGE_PAIRS, PAGE_2HACK, PAGE_3HACK, PAGE_4HACK, PAGE_5HACK)
//    internal annotation class GlyphPage

    companion object {
        private val TAG = "GlyphSequencesFragment"
        private val PAGE_BASE = "Base"
        private val PAGE_PAIRS = "Pairs"
        private val PAGE_2HACK = "2Hacks"
        private val PAGE_3HACK = "3Hacks"
        private val PAGE_4HACK = "4Hacks"
        private val PAGE_5HACK = "5Hacks"
    }
}
