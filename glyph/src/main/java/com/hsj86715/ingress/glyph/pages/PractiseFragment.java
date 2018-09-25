package com.hsj86715.ingress.glyph.pages;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.analytics.FirebaseAnalytics;
import com.hsj86715.ingress.glyph.R;
import com.hsj86715.ingress.glyphres.data.GlyphInfo;
import com.hsj86715.ingress.glyphres.data.GlyphModel;
import com.hsj86715.ingress.glyphres.data.HackList;
import com.hsj86715.ingress.glyphres.view.MultiGlyphView;
import com.hsj86715.ingress.glyphres.view.PractiseView;

import java.util.List;
import java.util.Random;

import cn.com.farmcode.utility.tools.Logger;

/**
 * @author hushujun
 */
public class PractiseFragment extends Fragment implements PractiseView.Callback {
    private List<HackList> mHackLists;
    private PractiseView mPractiseView;
    private MultiGlyphView mHackResultView;

    private int mPractiseIdx = 0;
    private int mRandomCurrent = 0;
    private boolean isRandom = true;
    private Random mRandom = new Random();

    private FirebaseAnalytics mFirebaseAnalytics;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.frag_practise, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mPractiseView = view.findViewById(R.id.practice_view);
        mHackResultView = view.findViewById(R.id.practise_result);
        mPractiseView.setCallback(this);
        updateToolBarSubTitle(R.string.option_menu_random);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        mFirebaseAnalytics = FirebaseAnalytics.getInstance(activity);
        new HackListTask().execute(0);
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
                updateToolBarSubTitle(R.string.option_menu_hack2);
                break;
            case R.id.remember_hack3:
                length = 3;
                updateToolBarSubTitle(R.string.option_menu_hack3);
                break;
            case R.id.remember_hack4:
                length = 4;
                updateToolBarSubTitle(R.string.option_menu_hack4);
                break;
            case R.id.remember_hack5:
                length = 5;
                updateToolBarSubTitle(R.string.option_menu_hack5);
                break;
            case R.id.practise_random:
                length = -1;
                updateToolBarSubTitle(R.string.option_menu_random);
            default:
                break;
        }
        if (length < 2 || length > 5) {
            isRandom = true;
        } else {
            isRandom = false;
        }
        mPractiseIdx = 0;
        new HackListTask().execute(length);
        return super.onOptionsItemSelected(item);
    }

    private void updateToolBarSubTitle(int titleRes) {
        AppCompatActivity activity = (AppCompatActivity) getActivity();
        if (activity != null) {
            activity.getSupportActionBar().setSubtitle(titleRes);
        }
    }

    private void handleTaskResult(List<HackList> hackLists) {
        mHackLists = hackLists;
        if (mHackLists == null || mPractiseView == null) {
            return;
        }
        int index;
        if (isRandom) {
            mRandomCurrent = mRandom.nextInt(hackLists.size());
            index = mRandomCurrent;
        } else {
            index = mPractiseIdx;
        }
        mPractiseView.setHackList(mHackLists.get(index));
    }

    @Override
    public void onStepChanged(int step) {
        if (step == PractiseView.STEP_SHOW) {
            mHackResultView.clear();
        }
    }

    @Override
    public void onTryStepEnd(int hackIdx, long stepTime, boolean result) {
        int index;
        if (isRandom) {
            index = mRandomCurrent;
        } else {
            index = mPractiseIdx;
        }
        GlyphInfo glyphInfo = mHackLists.get(index).getSequences()[hackIdx];
        glyphInfo.setPractiseCount(glyphInfo.getPractiseCount() + 1);
        if (result) {
            glyphInfo.setPractiseCorrect(glyphInfo.getPractiseCorrect() + 1);
        }
        if (stepTime > 0 && glyphInfo.getPractiseBest() > stepTime) {
            glyphInfo.setPractiseBest(stepTime);
        }
        new UpdateGlyphTask().execute(glyphInfo);
    }

    @Override
    public void onPractiseEnd(long totalTime, long[] tryStepCosts, boolean[] results) {
        int index;
        if (isRandom) {
            index = mRandomCurrent;
        } else {
            index = mPractiseIdx;
        }
        mHackResultView.setSequenceResult(mHackLists.get(index).getSequences(), tryStepCosts, results);
        HackList hackList = mHackLists.get(index);
        hackList.setPractiseCount(hackList.getPractiseCount() + 1);
        boolean success = true;
        for (boolean result : results) {
            if (!result) {
                success = false;
                break;
            }
        }
        if (success) {
            hackList.setPractiseCorrect(hackList.getPractiseCorrect() + 1);
        }
        if (totalTime > 0 && totalTime < hackList.getPractiseBest()) {
            hackList.setPractiseBest(totalTime);
        }
        new UpdateHackListTask().execute(hackList);
    }

    @Override
    public void toNextHackList() {
        if (mHackLists == null || mHackLists.isEmpty()) {
            return;
        }

        Bundle bundle = new Bundle();
        bundle.putString(FirebaseAnalytics.Event.VIEW_ITEM, "Next");
        mFirebaseAnalytics.logEvent(FirebaseAnalytics.Event.SELECT_CONTENT, bundle);

        if (isRandom) {
            mRandomCurrent = mRandom.nextInt(mHackLists.size());
            mPractiseView.setHackList(mHackLists.get(mRandomCurrent));
        } else {
            if (mPractiseIdx >= mHackLists.size() - 1) {
                Snackbar.make(mPractiseView, R.string.toast_practise_list_end, Snackbar.LENGTH_SHORT).show();
            } else {
                mPractiseIdx++;
                mPractiseView.setHackList(mHackLists.get(mPractiseIdx));
            }
        }
    }

    @Override
    public void toPreviousHackList() {
        if (mHackLists == null || mHackLists.isEmpty()) {
            return;
        }

        Bundle bundle = new Bundle();
        bundle.putString(FirebaseAnalytics.Event.VIEW_ITEM, "Previous");
        mFirebaseAnalytics.logEvent(FirebaseAnalytics.Event.SELECT_CONTENT, bundle);

        if (isRandom) {
            mRandomCurrent = mRandom.nextInt(mHackLists.size());
            mPractiseView.setHackList(mHackLists.get(mRandomCurrent));
        } else {
            if (mPractiseIdx <= 0) {
                Snackbar.make(mPractiseView, R.string.toast_practise_list_first, Snackbar.LENGTH_SHORT).show();
            } else {
                mPractiseIdx--;
                mPractiseView.setHackList(mHackLists.get(mPractiseIdx));
            }
        }
    }

    @Override
    public void retryCurrentHackList() {
        if (mHackLists == null || mHackLists.isEmpty()) {
            return;
        }
        if (isRandom) {
            mPractiseView.setHackList(mHackLists.get(mRandomCurrent));
        } else {
            mPractiseView.setHackList(mHackLists.get(mPractiseIdx));
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

    private class UpdateGlyphTask extends AsyncTask<GlyphInfo, Void, Integer> {
        @Override
        protected Integer doInBackground(GlyphInfo... glyphInfos) {
            return GlyphModel.getInstance(getActivity()).updateGlyphLearnOrPractise(glyphInfos[0]);
        }

        @Override
        protected void onPostExecute(Integer integer) {
            Logger.i("UpdateGlyphTask#onPostExecute: " + integer);
            super.onPostExecute(integer);
        }
    }

    private class UpdateHackListTask extends AsyncTask<HackList, Void, Integer> {
        @Override
        protected Integer doInBackground(HackList... hackLists) {
            return GlyphModel.getInstance(getActivity()).updateHackListPractise(hackLists[0]);
        }

        @Override
        protected void onPostExecute(Integer integer) {
            Logger.i("UpdateHackListTask#onPostExecute: " + integer);
            super.onPostExecute(integer);
        }
    }
}
