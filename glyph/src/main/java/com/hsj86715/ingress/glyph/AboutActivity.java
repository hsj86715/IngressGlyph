package com.hsj86715.ingress.glyph;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

/**
 * @author hushujun
 */
public class AboutActivity extends AppCompatActivity {
    private TextView mVersionTx;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        Toolbar toolBar = findViewById(R.id.about_toolbar);
        toolBar.setNavigationIcon(R.drawable.ic_arrow_back);
        setSupportActionBar(toolBar);
        toolBar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        mVersionTx = findViewById(R.id.about_version);
    }

    @Override
    protected void onResume() {
        super.onResume();
        try {
            PackageManager manager = this.getPackageManager();
            PackageInfo info = manager.getPackageInfo(this.getPackageName(), 0);
            String versionName = info.versionName;
            mVersionTx.setText("v" + versionName);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
    }
}
