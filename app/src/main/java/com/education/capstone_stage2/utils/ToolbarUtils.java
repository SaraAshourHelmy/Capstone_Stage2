package com.education.capstone_stage2.utils;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

/**
 * Created by Sara on 9/21/2017.
 */

public class ToolbarUtils {

    public static void setToolbar(AppCompatActivity activity, Toolbar toolbar,
                                  boolean backEnable) {
        activity.setSupportActionBar(toolbar);
        activity.getSupportActionBar().setDisplayHomeAsUpEnabled(backEnable);
    }
}
