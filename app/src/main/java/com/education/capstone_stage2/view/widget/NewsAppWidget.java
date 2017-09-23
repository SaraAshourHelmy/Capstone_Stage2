package com.education.capstone_stage2.view.widget;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.RemoteViews;

import com.education.capstone_stage2.R;
import com.education.capstone_stage2.utils.MyApplication;


/**
 * Implementation of App Widget functionality.
 */
public class NewsAppWidget extends AppWidgetProvider {


    static void updateAppWidget(Context context, AppWidgetManager appWidgetManager,
                                int appWidgetId) {


        RemoteViews views = getListData(context);

        appWidgetManager.updateAppWidget(appWidgetId, views);
    }

    public static void updateRecipeWidgets(Context context, AppWidgetManager appWidgetManager
            , int[] appWidgetIds) {
        for (int appWidgetId : appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId);
        }
    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {

        UpdateService.startService(context);
    }

    @Override
    public void onEnabled(Context context) {
        // Enter relevant functionality for when the first widget is created
    }

    @Override
    public void onDisabled(Context context) {
        // Enter relevant functionality for when the last widget is disabled
    }

    private static RemoteViews getListData(Context context) {

        RemoteViews views = new RemoteViews(context.getPackageName(),
                R.layout.news_app_widget);
        Intent intent = new Intent(context, NewsService.class);
        views.setRemoteAdapter(R.id.lstV_news, intent);

        //SharedUtils sharedUtils = new SharedUtils(context);

        if (MyApplication.getRecentNews() == null ||
                MyApplication.getRecentNews().size() == 0) {
            views.setViewVisibility(R.id.tv_noNews, View.VISIBLE);
        } else {
            views.setViewVisibility(R.id.tv_noNews, View.GONE);
        }
        views.setEmptyView(R.id.lstV_news, R.id.tv_noNews);
        return views;
    }
}

