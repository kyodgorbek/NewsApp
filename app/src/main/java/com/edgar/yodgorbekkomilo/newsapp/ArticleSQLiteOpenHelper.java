package com.edgar.yodgorbekkomilo.newsapp;

import android.annotation.TargetApi;
import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.DefaultDatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;
import android.util.Log;

/**
 * Created by yodgorbekkomilov on 3/6/18.
 */

public class ArticleSQLiteOpenHelper  extends SQLiteOpenHelper{

    public static final String DATABASE_FILE_NAME = "article.db";
    // @formatter:off
    public static final String SQL_CREATE_TABLE_ARTICLE = "CREATE TABLE IF NOT EXISTS "
            + ArticleColumns.TABLE_NAME + " ( "
            + ArticleColumns._ID + " INTEGER PRIMARY KEY, "
            + ArticleColumns.TITLE + " TEXT NOT NULL, "
            + ArticleColumns.TITLE_DESCRIPTION + " TEXT, "
            + ArticleColumns.AUTHOR + " TEXT, "+
           ArticleColumns.IS_FAVOURITE + " TEXT" + " );";

    private static final String TAG = ArticleSQLiteOpenHelper.class.getSimpleName();
    private static final int DATABASE_VERSION = 1;
    private static ArticleSQLiteOpenHelper sInstance;
    private final Context mContext;
    private final ArticleSQLiteOpenHelperCallbacks mOpenHelperCallbacks;

    // @formatter:on

    private ArticleSQLiteOpenHelper(Context context) {
        super(context, DATABASE_FILE_NAME, null, DATABASE_VERSION);
        mContext = context;
        mOpenHelperCallbacks = new ArticleSQLiteOpenHelperCallbacks();
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    private ArticleSQLiteOpenHelper(Context context, DatabaseErrorHandler errorHandler) {
        super(context, DATABASE_FILE_NAME, null, DATABASE_VERSION, errorHandler);
        mContext = context;
        mOpenHelperCallbacks = new ArticleSQLiteOpenHelperCallbacks();
    }

    public static ArticleSQLiteOpenHelper getInstance(Context context) {
        // Use the application context, which will ensure that you
        // don't accidentally leak an Activity's context.
        // See this article for more information: http://bit.ly/6LRzfx
        if (sInstance == null) {
            sInstance = newInstance(context.getApplicationContext());
        }
        return sInstance;
    }

    private static ArticleSQLiteOpenHelper newInstance(Context context) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.HONEYCOMB) {
            return newInstancePreHoneycomb(context);
        }
        return newInstancePostHoneycomb(context);
    }

    /*
     * Pre Honeycomb.
     */
    private static ArticleSQLiteOpenHelper newInstancePreHoneycomb(Context context) {
        return new ArticleSQLiteOpenHelper(context);
    }

    /*
     * Post Honeycomb.
     */
    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    private static ArticleSQLiteOpenHelper newInstancePostHoneycomb(Context context) {
        return new ArticleSQLiteOpenHelper(context, new DefaultDatabaseErrorHandler());
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        if (BuildConfig.DEBUG) Log.d(TAG, "onCreate");
        mOpenHelperCallbacks.onPreCreate(mContext, db);
        db.execSQL(SQL_CREATE_TABLE_ARTICLE);

        mOpenHelperCallbacks.onPostCreate(mContext, db);
    }

    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);
        if (!db.isReadOnly()) {
            setForeignKeyConstraintsEnabled(db);
        }
        mOpenHelperCallbacks.onOpen(mContext, db);
    }

    private void setForeignKeyConstraintsEnabled(SQLiteDatabase db) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN) {
            setForeignKeyConstraintsEnabledPreJellyBean(db);
        } else {
            setForeignKeyConstraintsEnabledPostJellyBean(db);
        }
    }

    private void setForeignKeyConstraintsEnabledPreJellyBean(SQLiteDatabase db) {
        db.execSQL("PRAGMA foreign_keys=ON;");
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    private void setForeignKeyConstraintsEnabledPostJellyBean(SQLiteDatabase db) {
        db.setForeignKeyConstraintsEnabled(true);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        mOpenHelperCallbacks.onUpgrade(mContext, db, oldVersion, newVersion);
    }
}