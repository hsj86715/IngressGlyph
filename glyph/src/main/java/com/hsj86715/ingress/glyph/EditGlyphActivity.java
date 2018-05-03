package com.hsj86715.ingress.glyph;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.hsj86715.ingress.glyph.pages.EditFragment;
import com.hsj86715.ingress.glyphres.data.GlyphInfo;

/**
 * @author hushujun
 */
public class EditGlyphActivity extends AppCompatActivity {
    private EditFragment mEditFragment;
    private Toolbar mToolBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_glyph);
        mToolBar = findViewById(R.id.edit_toolbar);
        mToolBar.setNavigationIcon(R.drawable.ic_arrow_back);
        setSupportActionBar(mToolBar);
        mToolBar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleExit();
            }
        });

        GlyphInfo glyphInfo = getIntent().getParcelableExtra("glyph");
        if (glyphInfo == null) {
            Snackbar.make(mToolBar, R.string.toast_edit_error_on_null, Snackbar.LENGTH_SHORT).show();
            finish();
        } else {
            mEditFragment = EditFragment.getInstance(glyphInfo);
            getSupportFragmentManager().beginTransaction().replace(R.id.edit_container, mEditFragment).commit();
        }
    }

    private void handleExit() {
        if (mEditFragment.isSaved()) {
            finish();
        } else {
            Snackbar.make(mToolBar, R.string.toast_edit_save_hint, Snackbar.LENGTH_LONG)
                    .setAction(R.string.toast_action_exit_yes, new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            finish();
                        }
                    }).show();
        }
    }

    @Override
    protected void onDestroy() {
        mEditFragment.saveEditTemp();
        super.onDestroy();
    }

    @Override
    public void onBackPressed() {
        handleExit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.edit_save, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.edit_menu_save) {
            mEditFragment.save();
        }
        return super.onOptionsItemSelected(item);
    }
}
