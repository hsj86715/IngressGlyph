package com.hsj86715.ingress.glyph

import android.app.Fragment
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.TextView
import android.widget.Toast

import com.hsj86715.ingress.glyph.pages.GlyphSequencesFragment
import com.hsj86715.ingress.glyphres.data.BaseGlyphData
import com.hsj86715.ingress.glyphres.view.IngressGlyphView
import com.hsj86715.ingress.glyphres.view.SequenceClickListener

/**
 * Created by hushujun on 16/5/16.
 */
class GlyphMainActivity : AppCompatActivity(), SequenceClickListener {
    private var mGlyphContainer: View? = null
    private var mGlyphSequenceView: IngressGlyphView? = null
    private var nameTx: TextView? = null

    private var mCurrentPath: IntArray? = null
    private var mCurrentName: String? = null
    private var mSequenceFrag: Fragment? = null

    //    private FirebaseAnalytics mFirebaseAnalytics;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);

        mGlyphContainer = findViewById(R.id.glyph_container)
        mGlyphSequenceView = findViewById(R.id.glyph_view) as IngressGlyphView
        nameTx = findViewById(R.id.text_view) as TextView
        mSequenceFrag = GlyphSequencesFragment()
        fragmentManager.beginTransaction().add(R.id.frag_container, mSequenceFrag).commit()

        resumeSavedInstance(savedInstanceState)

        //        Bundle bundle = new Bundle();
        //        bundle.putString(FirebaseAnalytics.Param.START_DATE, new Date().toString());
        //        mFirebaseAnalytics.logEvent(FirebaseAnalytics.Event.APP_OPEN, bundle);
    }

    override fun onDestroy() {
        //        Bundle bundle = new Bundle();
        //        bundle.putString(FirebaseAnalytics.Param.END_DATE, new Date().toString());
        //        mFirebaseAnalytics.logEvent(FirebaseAnalytics.Event.BEGIN_CHECKOUT, bundle);
        super.onDestroy()
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        resumeSavedInstance(savedInstanceState)
    }

    private fun resumeSavedInstance(savedInstanceState: Bundle?) {
        if (savedInstanceState == null) {
            return
        }
        if (savedInstanceState.containsKey("path") && savedInstanceState.containsKey("name")) {
            mCurrentPath = savedInstanceState.getIntArray("path")
            mGlyphSequenceView!!.drawPath(mCurrentPath)
            mCurrentName = savedInstanceState.getString("name")
            nameTx!!.text = mCurrentName
            //            mGlyphContainer.setBackgroundColor(Utils.stringToColor(mCurrentName));
        }
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        var outState = outState
        if (outState == null) {
            outState = Bundle()
        }
        outState.putIntArray("path", mCurrentPath)
        outState.putString("name", mCurrentName)
        super.onSaveInstanceState(outState)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.action_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return mSequenceFrag!!.onOptionsItemSelected(item) or super.onOptionsItemSelected(item)
    }

    override fun onSequenceClicked(@BaseGlyphData.GlyphName sequenceName: String) {
        if (mGlyphSequenceView!!.isDrawing) {
            Toast.makeText(this, R.string.toast_last_is_drawing, Toast.LENGTH_SHORT).show()
        } else {
            mCurrentPath = BaseGlyphData.getInstance().getGlyphPath(sequenceName)
            mGlyphSequenceView!!.drawPath(mCurrentPath)
            mCurrentName = sequenceName
            nameTx!!.text = mCurrentName
            //            mGlyphContainer.setBackgroundColor(Utils.stringToColor(mCurrentName));

            //            Bundle bundle = new Bundle();
            //            bundle.putString(FirebaseAnalytics.Param.CONTENT, sequenceName);
            //            bundle.putString(FirebaseAnalytics.Param.CONTENT_TYPE, "Sequence");
            //            mFirebaseAnalytics.logEvent(FirebaseAnalytics.Event.VIEW_ITEM, bundle);
        }
    }

    override fun clearSequence() {
        //        mCurrentPath = null;
        //        mCurrentName = "";
        //        if (nameTx != null) {
        //            nameTx.setText(mCurrentName);
        //        }
        //        if (mGlyphSequenceView != null) {
        //            mGlyphSequenceView.clear();
        //        }
    }
}
