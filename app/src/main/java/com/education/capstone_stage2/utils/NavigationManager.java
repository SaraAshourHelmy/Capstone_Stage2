package com.education.capstone_stage2.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import com.education.capstone_stage2.view.activity.HomeActivity;

/**
 * Created by Sara on 9/15/2017.
 */

public class NavigationManager {

    public static void navigateToHome(Activity activity) {
        Intent intent = new Intent(activity, HomeActivity.class);
        activity.startActivity(intent);
        activity.finish();
    }
}
