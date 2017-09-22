package com.education.capstone_stage2.view.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.education.capstone_stage2.R;
import com.education.capstone_stage2.utils.ToolbarUtils;

public class SettingsActivity extends AppCompatActivity {

    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        ToolbarUtils.setToolbar(this, toolbar, true);
        toolbar.setTitle(getString(R.string.setting));
    }
}
