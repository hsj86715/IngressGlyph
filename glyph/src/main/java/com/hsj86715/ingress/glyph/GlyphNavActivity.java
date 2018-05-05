package com.hsj86715.ingress.glyph;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.IntDef;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.google.firebase.analytics.FirebaseAnalytics;
import com.hsj86715.ingress.glyph.pages.LearnFragment;
import com.hsj86715.ingress.glyph.pages.RememberFragment;

import java.util.Date;

import static com.hsj86715.ingress.glyph.GlyphNavActivity.Function.LEARN;
import static com.hsj86715.ingress.glyph.GlyphNavActivity.Function.REMEMBER;

/**
 * @author hushujun
 */
public class GlyphNavActivity extends AppCompatActivity implements
        NavigationView.OnNavigationItemSelectedListener {
    private int mFunction = LEARN;

    @IntDef({LEARN, REMEMBER})
    @interface Function {
        int LEARN = 0, REMEMBER = 1;
    }

    private Toolbar mToolbar;
    private Fragment mCurrentFrag;
    private FirebaseAnalytics mFirebaseAnalytics;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nav);
        mToolbar = findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, mToolbar, R.string.nav_drawer_open, R.string.nav_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        mCurrentFrag = new LearnFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.content_container, mCurrentFrag).commit();

        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    protected void onDestroy() {
        Bundle bundle = new Bundle();
        bundle.putString(FirebaseAnalytics.Param.START_DATE, new Date().toString());
        mFirebaseAnalytics.logEvent(FirebaseAnalytics.Event.BEGIN_CHECKOUT, bundle);
        super.onDestroy();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.nav_options, menu);
        return true;
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        if (mFunction == LEARN) {
            menu.setGroupVisible(R.id.option_learn_group, true);
            menu.setGroupVisible(R.id.option_remember_group, false);
        } else if (mFunction == REMEMBER) {
            menu.setGroupVisible(R.id.option_learn_group, false);
            menu.setGroupVisible(R.id.option_remember_group, true);
        }
        return super.onPrepareOptionsMenu(menu);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return mCurrentFrag.onOptionsItemSelected(item) | super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.nav_learn:
                mFunction = LEARN;
                if (mCurrentFrag instanceof RememberFragment) {
                    mCurrentFrag = new LearnFragment();
                    getSupportFragmentManager().beginTransaction().replace(R.id.content_container, mCurrentFrag).commit();
                }
                break;
            case R.id.nav_remember:
                mFunction = REMEMBER;
                if (mCurrentFrag instanceof LearnFragment) {
                    mCurrentFrag = new RememberFragment();
                    getSupportFragmentManager().beginTransaction().replace(R.id.content_container, mCurrentFrag).commit();
                }
                break;
            case R.id.nav_share:
                shareTheApp();

                Bundle bundle = new Bundle();
                bundle.putString(FirebaseAnalytics.Param.ITEM_ID, String.valueOf(item.getItemId()));
                bundle.putString(FirebaseAnalytics.Param.ITEM_NAME, item.getTitle().toString());
                bundle.putString(FirebaseAnalytics.Param.GROUP_ID, String.valueOf(item.getGroupId()));
                mFirebaseAnalytics.logEvent(FirebaseAnalytics.Event.SHARE, bundle);
                break;
            case R.id.nav_feedback:
                jumpToPlayPage();
                break;
            case R.id.nav_about:
                startActivity(new Intent(this, AboutActivity.class));
                break;
            default:
                break;
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void shareTheApp() {
        try {
            Intent intent = new Intent(Intent.ACTION_SEND);
            intent.setType("text/plain");
            intent.putExtra(Intent.EXTRA_SUBJECT, getString(R.string.nav_menu_share));
            intent.putExtra(Intent.EXTRA_TEXT, getString(R.string.nav_share_text));
            startActivity(Intent.createChooser(intent, getTitle()));
        } catch (Exception e) {
            e.printStackTrace();
            Snackbar.make(mToolbar, R.string.toast_share_no_app, Snackbar.LENGTH_SHORT).show();
        }
    }

    private void jumpToPlayPage() {
        try {
            Intent intent = new Intent(Intent.ACTION_VIEW);
            Uri uri = Uri.parse("https://play.google.com/store/apps/details?id=cn.com.farmcode.ingress.sequence");
            intent.setData(uri);
            startActivity(intent);
        } catch (Exception e) {
            e.printStackTrace();
            Snackbar.make(mToolbar, R.string.toast_feedback_no_app, Snackbar.LENGTH_SHORT).show();
        }
    }
}
