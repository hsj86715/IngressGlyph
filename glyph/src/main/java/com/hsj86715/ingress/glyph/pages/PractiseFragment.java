package com.hsj86715.ingress.glyph.pages;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.analytics.FirebaseAnalytics;
import com.hsj86715.ingress.glyph.R;
import com.hsj86715.ingress.glyphres.data.GlyphModel;
import com.hsj86715.ingress.glyphres.data.HackList;
import com.hsj86715.ingress.glyphres.view.MultiGlyphView;
import com.hsj86715.ingress.glyphres.view.practise.PractiseView;

import java.util.List;
import java.util.Random;

/**
 * @author hushujun
 */
public class PractiseFragment extends Fragment implements PractiseView.Callback {
    private List<HackList> mHackLists;
    private PractiseView mPractiseView;
    private MultiGlyphView mHackResultView;

    private int mPractiseIdx = 0;
    private boolean isRandom = false;

    private FirebaseAnalytics mFirebaseAnalytics;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.frag_practise, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mPractiseView = view.findViewById(R.id.practice_view);
        mHackResultView = view.findViewById(R.id.practise_result);
        mPractiseView.setCallback(this);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        mFirebaseAnalytics = FirebaseAnalytics.getInstance(activity);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        Bundle bundle = new Bundle();
        bundle.putString(FirebaseAnalytics.Param.ITEM_ID, String.valueOf(id));
        bundle.putString(FirebaseAnalytics.Param.ITEM_NAME, item.getTitle().toString());
        bundle.putString(FirebaseAnalytics.Param.GROUP_ID, "OptionPractise");
        mFirebaseAnalytics.logEvent(FirebaseAnalytics.Event.SELECT_CONTENT, bundle);

        int length = 2;
        switch (id) {
            case R.id.remember_hack2:
                length = 2;
                break;
            case R.id.remember_hack3:
                length = 3;
                break;
            case R.id.remember_hack4:
                length = 4;
                break;
            case R.id.remember_hack5:
                length = 5;
                break;
            case R.id.practise_random:
                length = -1;
            default:
                break;
        }
        if (length < 2 || length > 5) {
            isRandom = true;
        } else {
            isRandom = false;
        }
        new HackListTask().execute(length);
        return super.onOptionsItemSelected(item);
    }

    private void handleTaskResult(List<HackList> hackLists) {
        mHackLists = hackLists;
        if (mHackLists != null) {
            updatePractiseHackList(0);
        }
    }

    @Override
    public void onStepChanged(int step) {
        if (step == PractiseView.STEP_SHOW) {
            mHackResultView.clear();
        }
    }

    @Override
    public void onTryStepEnd(int hackIdx, long stepTime) {

    }

    @Override
    public void onPractiseEnd(long totalTime, long[] tryStepCosts) {
        mHackResultView.setSequences(mHackLists.get(mPractiseIdx).getSequences(), tryStepCosts);
    }

    @Override
    public void onPractiseResult(boolean[] results) {
        mHackResultView.setSequenceResult(results);
    }

    @Override
    public void toNextHackList() {
        if (mHackLists == null || mHackLists.isEmpty()) {
            return;
        }
        updatePractiseHackList(1);
    }

    @Override
    public void toPreviousHackList() {
        if (mHackLists == null || mHackLists.isEmpty()) {
            return;
        }
        updatePractiseHackList(-1);
    }

    private void updatePractiseHackList(int move) {
        if (isRandom) {
            Random random = new Random();
            int idx = random.nextInt(mHackLists.size());
            if (mPractiseView != null) {
                mPractiseView.setHackList(mHackLists.get(idx));
            }
        } else {
            mPractiseIdx += move;
            if (mPractiseIdx >= mHackLists.size()) {
                Snackbar.make(mPractiseView, R.string.toast_practise_list_end, Snackbar.LENGTH_SHORT).show();
                return;
            } else if (mPractiseIdx < 0) {
                Snackbar.make(mPractiseView, R.string.toast_practise_list_first, Snackbar.LENGTH_SHORT).show();
                return;
            }
            if (mPractiseView != null) {
                mPractiseView.setHackList(mHackLists.get(mPractiseIdx));
            }
        }
    }

    private class HackListTask extends AsyncTask<Integer, Void, List<HackList>> {
        @Override
        protected List<HackList> doInBackground(Integer... integers) {
            return GlyphModel.getInstance(getActivity()).getHackList(integers[0]);
        }

        @Override
        protected void onPostExecute(List<HackList> hackLists) {
            handleTaskResult(hackLists);
        }
    }
}
