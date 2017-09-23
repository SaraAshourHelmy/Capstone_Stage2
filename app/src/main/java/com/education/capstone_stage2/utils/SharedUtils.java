package com.education.capstone_stage2.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by sara on 9/23/2017.
 */

public class SharedUtils {

    private static final String SHARED_NAME = "app_config";
    private static final String SHARED_IS_FIRST = "isFirst";
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    public SharedUtils(Context context) {
        sharedPreferences = context.getSharedPreferences(SHARED_NAME, context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }

    public void setFirst(boolean isFirst) {

        editor.putBoolean(SHARED_IS_FIRST, isFirst);
        editor.commit();
    }

    public boolean getFirst() {
        return sharedPreferences.getBoolean(SHARED_IS_FIRST, true);
    }
}
