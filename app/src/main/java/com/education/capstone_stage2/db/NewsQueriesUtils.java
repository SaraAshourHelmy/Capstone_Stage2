package com.education.capstone_stage2.db;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.education.capstone_stage2.model.News;

/**
 * Created by Sara on 6/17/2017.
 */

public class NewsQueriesUtils {


    public static ContentValues getNewsContentValues(News news) {
        ContentValues cv = new ContentValues();
        cv.put(NewsContract.NewsEntry.COLUMN_NEWS_EN_TITLE, news.getEnTitle());
        cv.put(NewsContract.NewsEntry.COLUMN_NEWS_AR_TITLE, news.getArTitle());
        cv.put(NewsContract.NewsEntry.COLUMN_NEWS_EN_SHORT_DESCRIPTION, news.getEnShortDescription());
        cv.put(NewsContract.NewsEntry.COLUMN_NEWS_AR_SHORT_DESCRIPTION, news.getArShortDescription());
        cv.put(NewsContract.NewsEntry.COLUMN_NEWS_EN_LONG_DESCRIPTION, news.getEnLongDescription());
        cv.put(NewsContract.NewsEntry.COLUMN_NEWS_AR_LONG_DESCRIPTION, news.getArLongDescription());
        cv.put(NewsContract.NewsEntry.COLUMN_NEWS_EN_DATE, news.getEnDate());
        cv.put(NewsContract.NewsEntry.COLUMN_NEWS_AR_DATE, news.getArDate());
        cv.put(NewsContract.NewsEntry.COLUMN_NEWS_LATITUDE, news.getLatitude());
        cv.put(NewsContract.NewsEntry.COLUMN_NEWS_LONGITUDE, news.getLongitude());
        cv.put(NewsContract.NewsEntry.COLUMN_NEWS_IMAGE_URL, news.getImgURL());
        cv.put(NewsContract.NewsEntry.COLUMN_NEWS_TYPE, news.getType());

        return cv;
    }

    public static Cursor getAllNews(SQLiteDatabase db, String selection, String[] selectionArgs) {
        return db.query(NewsContract.NewsEntry.TABLE_NAME, null, selection, selectionArgs,
                null, null, null);
    }

    public static Cursor getNews(SQLiteDatabase db, String id) {
        Cursor cursor = db
                .query(NewsContract.NewsEntry.TABLE_NAME,
                        null, NewsContract.NewsEntry._ID + "=?",
                        new String[]{id},
                        null, null, null);
        return cursor;
    }
}
