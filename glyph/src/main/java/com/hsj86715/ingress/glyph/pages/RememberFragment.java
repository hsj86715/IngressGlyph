package com.hsj86715.ingress.glyph.pages;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.analytics.FirebaseAnalytics;
import com.hsj86715.ingress.glyph.R;
import com.hsj86715.ingress.glyph.view.SimpleItemDecoration;
import com.hsj86715.ingress.glyphres.data.GlyphModel;
import com.hsj86715.ingress.glyphres.data.HackList;

import java.util.List;

/**
 * @author hushujun
 */
public class RememberFragment extends Fragment {
    private int mSequencesLength;
    private RecyclerView mRememberRv;
    private HackSequencesAdapter mSequenceAdapter;

    private FirebaseAnalytics mFirebaseAnalytics;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.frag_remember, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        mRememberRv = view.findViewById(R.id.remember_rv);
        mRememberRv.addItemDecoration(new SimpleItemDecoration());
        mRememberRv.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));

        mSequenceAdapter = new HackSequencesAdapter();
        if (savedInstanceState != null) {
            mSequencesLength = savedInstanceState.getInt("sequencesLength", 2);
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        mFirebaseAnalytics = FirebaseAnalytics.getInstance(activity);
        updateSequenceLength(mSequencesLength <= 2 ? 2 : mSequencesLength);
    }

    private void updateSequenceLength(int length) {
        if (length == mSequencesLength) {
            return;
        }
        new HackSequencesTask().execute(length);
        switch (length) {
            case 2:
                updateToolBarSubTitle(R.string.option_menu_hack2);
                break;
            case 3:
                updateToolBarSubTitle(R.string.option_menu_hack3);
                break;
            case 4:
                updateToolBarSubTitle(R.string.option_menu_hack4);
                break;
            case 5:
                updateToolBarSubTitle(R.string.option_menu_hack5);
                break;
            default:
                break;
        }
        mSequencesLength = length;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        Bundle bundle = new Bundle();
        bundle.putString(FirebaseAnalytics.Param.ITEM_ID, String.valueOf(id));
        bundle.putString(FirebaseAnalytics.Param.ITEM_NAME, item.getTitle().toString());
        bundle.putString(FirebaseAnalytics.Param.GROUP_ID, "OptionRemember");
        mFirebaseAnalytics.logEvent(FirebaseAnalytics.Event.SELECT_CONTENT, bundle);
        switch (id) {
            case R.id.remember_hack2:
                updateSequenceLength(2);
                break;
            case R.id.remember_hack3:
                updateSequenceLength(3);
                break;
            case R.id.remember_hack4:
                updateSequenceLength(4);
                break;
            case R.id.remember_hack5:
                updateSequenceLength(5);
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        if (outState == null) {
            outState = new Bundle();
        }
        outState.putInt("sequencesLength", mSequencesLength);
        super.onSaveInstanceState(outState);
    }

    private void updateToolBarSubTitle(int titleRes) {
        AppCompatActivity activity = (AppCompatActivity) getActivity();
        if (activity != null) {
            activity.getSupportActionBar().setSubtitle(titleRes);
        }
    }

    private class HackSequencesTask extends AsyncTask<Integer, Void, List<HackList>> {
        @Override
        protected List<HackList> doInBackground(Integer... integers) {
            return GlyphModel.getInstance(getActivity()).getHackList(integers[0]);
        }

        @Override
        protected void onPostExecute(List<HackList> hackLists) {
//            mGlyphAdapter.setSequenceClickListener(mSequenceListener);
            mSequenceAdapter.setHackSequences(hackLists);
            mRememberRv.setAdapter(mSequenceAdapter);
        }
    }
}
