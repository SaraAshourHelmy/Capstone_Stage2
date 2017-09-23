package com.education.capstone_stage2.view.widget;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.preference.PreferenceManager;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

import com.education.capstone_stage2.R;
import com.education.capstone_stage2.model.News;
import com.education.capstone_stage2.utils.MyApplication;

import java.util.ArrayList;

/**
 * Created by Sara on 7/18/2017.
 */

public class NewsWidgetFactory implements RemoteViewsService.RemoteViewsFactory {

    Context mContext;
    ArrayList<News> newsLst;

    public NewsWidgetFactory(Context appContext) {
        mContext = appContext;

    }

    @Override
    public void onCreate() {

    }

    @Override
    public void onDataSetChanged() {

        if (MyApplication.getRecentNews() == null)
            return;
        if (MyApplication.getRecentNews() != null || MyApplication.getRecentNews().size() > 0)
            newsLst = MyApplication.getRecentNews();
    }

    @Override
    public void onDestroy() {

    }

    @Override
    public int getCount() {
        if (newsLst == null)
            return 0;
        else
            return newsLst.size();
    }

    @Override
    public RemoteViews getViewAt(int position) {
        if (newsLst == null || newsLst.size() == 0)
            return null;

        RemoteViews views = new RemoteViews(mContext.getPackageName(), R.layout.adapter_widget);

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(mContext);
        boolean isEnglish = preferences.getBoolean(mContext.getString(R.string.pref_language_key), true);

        String newsBrief = "";
        if (isEnglish) {
            newsBrief = newsLst.get(position).getEnTitle() + "\n " +
                    newsLst.get(position).getEnShortDescription();
        } else {
            newsBrief = newsLst.get(position).getArTitle() + "\n " +
                    newsLst.get(position).getArShortDescription();
        }

        views.setTextViewText(R.id.tv_widgetNewsName, newsBrief);

        return views;
    }

    @Override
    public RemoteViews getLoadingView() {
        return null;
    }

    @Override
    public int getViewTypeCount() {
        return 1;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }
}
