package com.education.capstone_stage2.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Sara on 9/15/2017.
 */

public class NewsDBHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "news.db";
    public static final int DATABASE_VERSION = 1;

    public NewsDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String studentQuery = "CREATE TABLE " + NewsContract.NewsEntry.TABLE_NAME
                + " ( " +
                NewsContract.NewsEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + NewsContract.NewsEntry.COLUMN_NEWS_EN_TITLE + " STRING NOT NULL ,"
                + NewsContract.NewsEntry.COLUMN_NEWS_AR_TITLE + " STRING NOT NULL ,"
                + NewsContract.NewsEntry.COLUMN_NEWS_EN_SHORT_DESCRIPTION + " STRING NOT NULL ,"
                + NewsContract.NewsEntry.COLUMN_NEWS_AR_SHORT_DESCRIPTION + " STRING NOT NULL ,"
                + NewsContract.NewsEntry.COLUMN_NEWS_EN_LONG_DESCRIPTION + " STRING NOT NULL ,"
                + NewsContract.NewsEntry.COLUMN_NEWS_AR_LONG_DESCRIPTION + " STRING NOT NULL ,"
                + NewsContract.NewsEntry.COLUMN_NEWS_EN_DATE + " STRING NOT NULL ,"
                + NewsContract.NewsEntry.COLUMN_NEWS_AR_DATE + " STRING NOT NULL ,"
                + NewsContract.NewsEntry.COLUMN_NEWS_LATITUDE + " DOUBLE NOT NULL ,"
                + NewsContract.NewsEntry.COLUMN_NEWS_LONGITUDE + " DOUBLE NOT NULL ,"
                + NewsContract.NewsEntry.COLUMN_NEWS_IMAGE_URL + " STRING NOT NULL );";
        
        db.execSQL(studentQuery);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
