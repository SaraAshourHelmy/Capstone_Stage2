package com.education.capstone_stage2.utils;

import android.app.Application;

import com.education.capstone_stage2.model.News;

import java.util.ArrayList;

/**
 * Created by sara on 9/23/2017.
 */

public class MyApplication extends Application {

    static ArrayList<News> mRecentNews;

    public static void setRecentNews(ArrayList<News> recentNews) {
        mRecentNews = recentNews;
    }

    public static ArrayList<News> getRecentNews() {
        return mRecentNews;
    }
}
