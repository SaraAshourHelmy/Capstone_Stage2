package com.education.capstone_stage2.view.widget;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;

import com.education.capstone_stage2.utils.WidgetUtility;

/**
 * Created by Sara on 7/18/2017.
 */

public class UpdateService extends IntentService {

    public UpdateService() {
        super("UpdateService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {

        WidgetUtility.updateWidget(this);
    }

    public static void startService(Context context) {
        Intent intent = new Intent(context, UpdateService.class);
        context.startService(intent);
    }
}
