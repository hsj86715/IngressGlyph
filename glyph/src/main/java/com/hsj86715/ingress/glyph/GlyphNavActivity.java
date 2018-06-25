package com.hsj86715.ingress.glyph;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.StringDef;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.google.firebase.analytics.FirebaseAnalytics;
import com.hsj86715.ingress.glyph.pages.CheatFragment;
import com.hsj86715.ingress.glyph.pages.LearnFragment;
import com.hsj86715.ingress.glyph.pages.PractiseFragment;
import com.hsj86715.ingress.glyph.pages.RememberFragment;
import com.hsj86715.ingress.glyph.pages.StatisticalFragment;

import java.util.Date;

import cn.com.farmcode.utility.tools.Logger;

import static com.hsj86715.ingress.glyph.GlyphNavActivity.Function.CHEAT;
import static com.hsj86715.ingress.glyph.GlyphNavActivity.Function.LEARN;
import static com.hsj86715.ingress.glyph.GlyphNavActivity.Function.PRACTISE;
import static com.hsj86715.ingress.glyph.GlyphNavActivity.Function.REMEMBER;
import static com.hsj86715.ingress.glyph.GlyphNavActivity.Function.STATISTICAL;

/**
 * @author hushujun
 */
public class GlyphNavActivity extends AppCompatActivity implements
        NavigationView.OnNavigationItemSelectedListener {
    private String mFunction = LEARN;

    @StringDef({LEARN, REMEMBER, PRACTISE, STATISTICAL, CHEAT})
    @interface Function {
        String LEARN = "learn", REMEMBER = "remember", PRACTISE = "practise", STATISTICAL = "statistical",
                CHEAT = "information";
    }

    private Toolbar mToolbar;
    private DrawerLayout mDrawer;
    private Fragment mCurrentFrag;
    private FirebaseAnalytics mFirebaseAnalytics;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nav);
        mToolbar = findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);

        mDrawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, mDrawer, mToolbar, R.string.nav_drawer_open, R.string.nav_drawer_close);
        mDrawer.addDrawerListener(toggle);
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
        switch (mFunction) {
            case LEARN:
                menu.setGroupVisible(R.id.option_learn_group, true);
                menu.setGroupVisible(R.id.option_remember_group, false);
                menu.setGroupVisible(R.id.option_practise_group, false);
                menu.setGroupVisible(R.id.option_statistical_group, false);
                menu.setGroupVisible(R.id.option_cheat_group, false);
                break;
            case REMEMBER:
                menu.setGroupVisible(R.id.option_learn_group, false);
                menu.setGroupVisible(R.id.option_remember_group, true);
                menu.setGroupVisible(R.id.option_practise_group, false);
                menu.setGroupVisible(R.id.option_statistical_group, false);
                menu.setGroupVisible(R.id.option_cheat_group, false);
                break;
            case PRACTISE:
                menu.setGroupVisible(R.id.option_learn_group, false);
                menu.setGroupVisible(R.id.option_remember_group, true);
                menu.setGroupVisible(R.id.option_practise_group, true);
                menu.setGroupVisible(R.id.option_statistical_group, false);
                menu.setGroupVisible(R.id.option_cheat_group, false);
                break;
            case STATISTICAL:
                menu.setGroupVisible(R.id.option_learn_group, false);
                menu.setGroupVisible(R.id.option_remember_group, false);
                menu.setGroupVisible(R.id.option_practise_group, false);
                menu.setGroupVisible(R.id.option_statistical_group, true);
                menu.setGroupVisible(R.id.option_cheat_group, false);
                break;
            case CHEAT:
                menu.setGroupVisible(R.id.option_learn_group, false);
                menu.setGroupVisible(R.id.option_remember_group, false);
                menu.setGroupVisible(R.id.option_practise_group, false);
                menu.setGroupVisible(R.id.option_statistical_group, false);
                menu.setGroupVisible(R.id.option_cheat_group, true);
            default:
                break;
        }
        return super.onPrepareOptionsMenu(menu);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return mCurrentFrag.onOptionsItemSelected(item) | super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.nav_learn:
//                changedScreenOrientation(false);
                switch2Frag(LEARN);
                break;
            case R.id.nav_remember:
//                changedScreenOrientation(false);
                switch2Frag(REMEMBER);
                break;
            case R.id.nav_practise:
//                changedScreenOrientation(false);
                switch2Frag(PRACTISE);
                break;
//            case R.id.nav_statistical:
//                changedScreenOrientation(true);
//                switch2Frag(STATISTICAL);
//                break;
            case R.id.nav_cheat:
                switch2Frag(CHEAT);
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

    private void changedScreenOrientation(boolean toLandScape) {
        int orientation = getRequestedOrientation();
        if (toLandScape) {
            if (orientation == ActivityInfo.SCREEN_ORIENTATION_PORTRAIT) {
                setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
            }
        } else {
            if (orientation == ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE) {
                setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
            }
        }
    }

    private void switch2Frag(@Function String whichFrag) {
        Logger.i("switch2Frag >>> " + whichFrag);
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        Fragment fragment = fm.findFragmentByTag(whichFrag);
        if (fragment == null) {
            fragment = getFragment(whichFrag);
            ft.replace(R.id.content_container, fragment, whichFrag).commitAllowingStateLoss();
        } else {
            ft.show(fragment).commitAllowingStateLoss();
        }
        mCurrentFrag = fragment;
        mFunction = whichFrag;
    }

    private Fragment getFragment(@Function String whichFrag) {
        switch (whichFrag) {
            case LEARN:
                return new LearnFragment();
            case REMEMBER:
                return new RememberFragment();
            case PRACTISE:
                return new PractiseFragment();
            case STATISTICAL:
                return new StatisticalFragment();
            case CHEAT:
                return new CheatFragment();
            default:
                return null;
        }
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
