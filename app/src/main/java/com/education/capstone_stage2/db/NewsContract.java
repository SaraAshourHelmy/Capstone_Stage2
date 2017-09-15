package com.education.capstone_stage2.db;

import android.net.Uri;
import android.provider.BaseColumns;

/**
 * Created by Sara on 9/15/2017.
 */

public final class NewsContract {

    public static final String SCHEMA = "content://";
    public static final String AUTHORITY = "com.education.capstone_stage2";
    public static final Uri BASE_CONTENT_URI = Uri.parse(SCHEMA + AUTHORITY);
    public static final String PATH_News = "news";

    public static class NewsEntry implements BaseColumns {

        public static final Uri CONTENT_URI = BASE_CONTENT_URI.buildUpon()
                .appendPath(PATH_News).build();


        public static final String TABLE_NAME = "news";
        public static final String COLUMN_NEWS_EN_TITLE = "enTitle";
        public static final String COLUMN_NEWS_AR_TITLE = "arTitle";
        public static final String COLUMN_NEWS_EN_SHORT_DESCRIPTION = "enShortDescription";
        public static final String COLUMN_NEWS_AR_SHORT_DESCRIPTION = "arShortDescription";
        public static final String COLUMN_NEWS_EN_LONG_DESCRIPTION = "enLongDescription";
        public static final String COLUMN_NEWS_AR_LONG_DESCRIPTION = "arLongDescription";
        public static final String COLUMN_NEWS_EN_DATE = "enDate";
        public static final String COLUMN_NEWS_AR_DATE = "arDate";
        public static final String COLUMN_NEWS_LATITUDE = "latitude";
        public static final String COLUMN_NEWS_LONGITUDE = "longitude";
        public static final String COLUMN_NEWS_IMAGE_URL = "imgURL";


    }
}
