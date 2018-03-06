package com.edgar.yodgorbekkomilo.newsapp.provider;

import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;
import android.provider.MediaStore;
import android.util.Log;

import com.edgar.yodgorbekkomilo.newsapp.ArticleColumns;
import com.edgar.yodgorbekkomilo.newsapp.ArticleSQLiteOpenHelper;
import com.edgar.yodgorbekkomilo.newsapp.Pojo.Article;

import java.util.Arrays;

import static com.edgar.yodgorbekkomilo.newsapp.BuildConfig.DEBUG;

/**
 * Created by yodgorbekkomilov on 3/1/18.
 */

public abstract class ArticleProvider extends BaseContentProvider {

    public static final String AUTHORITY = "com.edgar.yodgorbekkomilo.newsapp.provider";
    public static final String CONTENT_URI_BASE = "content://" + AUTHORITY;
    private static final String TAG = ArticleProvider.class.getSimpleName();
    private static final String TYPE_CURSOR_ITEM = "vnd.android.cursor.item/";
    private static final String TYPE_CURSOR_DIR = "vnd.android.cursor.dir/";
    private static final int URI_TYPE_ARTICLE = 0;
    private static final int URI_TYPE_ARTICLE_ID = 1;

    private static final UriMatcher URI_MATCHER = new UriMatcher(UriMatcher.NO_MATCH);

    {
        URI_MATCHER.addURI(AUTHORITY, ArticleColumns.TABLE_NAME, URI_TYPE_ARTICLE);
        URI_MATCHER.addURI(AUTHORITY, ArticleColumns.TABLE_NAME + "/#", URI_TYPE_ARTICLE_ID);


        Override
        protected SQLiteOpenHelper createSqLiteOpenHelper() {
            return ArticleSQLiteOpenHelper.getInstance(getContext());
        }

        @Override
        protected boolean hasDebug() {
            return DEBUG;
        }

        @Override
        public String getType(Uri uri){
            int match = URI_MATCHER.match(uri);
            switch (match) {
                case URI_TYPE_ARTICLE:
                    return TYPE_CURSOR_DIR + ArticleColumns.TABLE_NAME;
                case URI_TYPE_ARTICLE_ID:
                    return TYPE_CURSOR_ITEM + ArticleColumns.TABLE_NAME;

                default:

            }
        return null;
        }

        @Override
        public Uri insert(Uri uri, ContentValues values) {
            if (DEBUG) Log.d(TAG, "insert uri=" + uri + " values=" + values);
            return super.insert(uri, values);
        }

        @Override
        public int bulkInsert(Uri uri, ContentValues[] values) {
            if (DEBUG) Log.d(TAG, "bulkInsert uri=" + uri + " values.length=" + values.length);
            return super.bulkInsert(uri, values);
        }

        @Override
        public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
            if (DEBUG)
                Log.d(TAG, "update uri=" + uri + " values=" + values + " selection=" + selection + " selectionArgs=" + Arrays.toString(selectionArgs));
            return super.update(uri, values, selection, selectionArgs);
        }

        @Override
        public int delete(Uri uri, String selection, String[] selectionArgs) {
            if (DEBUG)
                Log.d(TAG, "delete uri=" + uri + " selection=" + selection + " selectionArgs=" + Arrays.toString(selectionArgs));
            return super.delete(uri, selection, selectionArgs);
        }

        @Override
        public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
            if (DEBUG)
                Log.d(TAG, "query uri=" + uri + " selection=" + selection + " selectionArgs=" + Arrays.toString(selectionArgs) + " sortOrder=" + sortOrder
                        + " groupBy=" + uri.getQueryParameter(QUERY_GROUP_BY) + " having=" + uri.getQueryParameter(QUERY_HAVING) + " limit=" + uri.getQueryParameter(QUERY_LIMIT));
            return super.query(uri, projection, selection, selectionArgs, sortOrder);
        }

        @Override
        protected QueryParams getQueryParams(Uri uri, String selection, String[] projection) {
            QueryParams res = new QueryParams();
            String id = null;
            int matchedId = URI_MATCHER.match(uri);
            switch (matchedId) {
                case URI_TYPE_ARTICLE:
                case URI_TYPE_ARTICLE_ID:
                    res.table = ArticleColumns.TABLE_NAME;
                    res.idColumn = ArticleColumns._ID;
                    res.tablesWithJoins = ArticleColumns.TABLE_NAME;
                    res.orderBy = ArticleColumns.DEFAULT_ORDER;
                    break;



                default:
                    throw new IllegalArgumentException("The uri '" + uri + "' is not supported by this ContentProvider");
            }

            switch (matchedId) {
                case URI_TYPE_ARTICLE_ID:

                    id = uri.getLastPathSegment();
            }
            if (id != null) {
                if (selection != null) {
                    res.selection = res.table + "." + res.idColumn + "=" + id + " and (" + selection + ")";
                } else {
                    res.selection = res.table + "." + res.idColumn + "=" + id;
                }
            } else {
                res.selection = selection;
            }

        }
    }


    }

