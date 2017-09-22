package com.education.capstone_stage2.db;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.Nullable;


/**
 * Created by Sara on 6/19/2017.
 */

public class NewsContentProvider extends ContentProvider {

    private NewsDBHelper dbHelper;
    public static final int NEWS = 100;
    public static final int NEWS_ID = 101;

    public UriMatcher matcher = buildUriMatcher();


    public static UriMatcher buildUriMatcher() {
        UriMatcher uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);

        // for directory
        uriMatcher.addURI(NewsContract.AUTHORITY, NewsContract.PATH_News, NEWS);

        //  for single row
        uriMatcher.addURI(NewsContract.AUTHORITY, NewsContract.PATH_News + "/#",
                NEWS_ID);

        return uriMatcher;

    }

    @Override
    public boolean onCreate() {

        // initialize data that we want to share it
        Context context = getContext();
        dbHelper = new NewsDBHelper(context);
        return true;
    }

    @Override
    public int bulkInsert(Uri uri, ContentValues[] values) {
        return super.bulkInsert(uri, values);
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        return 0;
    }

    @Nullable
    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {

        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor;
        int match = matcher.match(uri);
        switch (match) {
            case NEWS:
                cursor = NewsQueriesUtils.getAllNews(db, selection, selectionArgs);
                if (cursor.getCount() < 0)
                    throw new SQLException("error in retrieve this uri " + uri);
                break;
            case NEWS_ID:
                String id = uri.getPathSegments().get(1);
                cursor = NewsQueriesUtils.getNews(db, id);
                break;

            default:
                throw new UnsupportedOperationException("unknown this uri :" + uri);
        }

        cursor.setNotificationUri(getContext().getContentResolver(), uri);
        return cursor;
    }

    @Nullable
    @Override
    public String getType(Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(Uri uri, ContentValues values) {

        SQLiteDatabase db = dbHelper.getWritableDatabase();
        int match = matcher.match(uri);
        Uri result;
        switch (match) {
            case NEWS: {
                long id = db.insert(NewsContract.NewsEntry.TABLE_NAME, null, values);
                if (id > 0) {

                    result = ContentUris.withAppendedId(NewsContract.NewsEntry.CONTENT_URI
                            , id);

                } else {
                    throw new SQLException("cannot insert this uri:" + uri);
                }
            }
            break;
            default:
                throw new UnsupportedOperationException("Unknown uri :" + uri);
        }
        getContext().getContentResolver().notifyChange(uri, null);
        return result;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        return 0;
    }
}
