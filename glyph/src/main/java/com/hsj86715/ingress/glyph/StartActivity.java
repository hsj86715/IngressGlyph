package com.hsj86715.ingress.glyph;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.widget.TextView;

import com.google.firebase.analytics.FirebaseAnalytics;
import com.hsj86715.ingress.glyphres.data.GlyphModel;

import java.util.Date;

/**
 * @author hushujun
 */
public class StartActivity extends Activity {
    private static final int MIN_WAIT_TIME = 2000;
    private static final int MSG_WAITING = 0;
    private static final int MSG_LOAD_END = 1;
    private static final int MSG_JUMP = 2;
    private LoadDefaultDataTask mLoadDataTask;
    private JumpHandler mHandler;
    private boolean mCancelJump = false;

    private FirebaseAnalytics mFirebaseAnalytics;

    private TextView mLoadingHint;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        mHandler = new JumpHandler();

        mLoadingHint = findViewById(R.id.loading_tv);

        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);
    }

    @Override
    protected void onResume() {
        Bundle bundle = new Bundle();
        bundle.putString(FirebaseAnalytics.Param.START_DATE, new Date().toString());
        mFirebaseAnalytics.logEvent(FirebaseAnalytics.Event.APP_OPEN, bundle);
        super.onResume();
        mLoadDataTask = new LoadDefaultDataTask();
        mLoadDataTask.execute();
        mHandler.sendEmptyMessageDelayed(MSG_WAITING, MIN_WAIT_TIME);
    }

    @Override
    public void onBackPressed() {
        mCancelJump = true;
        super.onBackPressed();

        Bundle bundle = new Bundle();
        bundle.putString(FirebaseAnalytics.Param.END_DATE, new Date().toString());
        mFirebaseAnalytics.logEvent(FirebaseAnalytics.Event.BEGIN_CHECKOUT, bundle);
    }

    private class JumpHandler extends Handler {
        private boolean isWaitingEnd = false;

        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case MSG_WAITING:
                    if (!mLoadDataTask.isLoading) {
                        sendEmptyMessage(MSG_JUMP);
                    } else {
                        mLoadingHint.setText(R.string.loading_first);
                    }
                    isWaitingEnd = true;
                    break;
                case MSG_LOAD_END:
                    if (isWaitingEnd) {
                        sendEmptyMessage(MSG_JUMP);
                    }
                    break;
                case MSG_JUMP:
                    if (!mCancelJump) {
                        startActivity(new Intent(StartActivity.this, GlyphNavActivity.class));
                        finish();
                    }
                    break;
                default:
                    break;
            }
        }
    }

    private class LoadDefaultDataTask extends AsyncTask<Void, Void, Void> {
        private boolean isLoading = true;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            isLoading = true;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            GlyphModel.getInstance(StartActivity.this).initBaseData();
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            isLoading = false;
            mHandler.sendEmptyMessage(MSG_LOAD_END);
        }
    }
}
