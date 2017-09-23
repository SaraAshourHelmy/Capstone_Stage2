package com.education.capstone_stage2.utils;

import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Context;

import com.education.capstone_stage2.R;
import com.education.capstone_stage2.view.widget.NewsAppWidget;

/**
 * Created by Sara on 7/19/2017.
 */

public class WidgetUtility {

    public static void updateWidget(Context context) {
        AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(context);
        int[] appWidgetIds = appWidgetManager.getAppWidgetIds(new ComponentName(context,
                NewsAppWidget.class));
        appWidgetManager.notifyAppWidgetViewDataChanged(appWidgetIds, R.id.lstV_news);
        NewsAppWidget.updateRecipeWidgets
                (context, appWidgetManager, appWidgetIds);
    }

}
