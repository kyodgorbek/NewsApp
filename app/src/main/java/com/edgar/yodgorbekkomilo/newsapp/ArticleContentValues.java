package com.edgar.yodgorbekkomilo.newsapp;

import android.content.ContentResolver;
import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.edgar.yodgorbekkomilo.newsapp.Pojo.Article;

/**
 * Created by yodgorbekkomilov on 3/6/18.
 */

public class ArticleContentValues extends AbstractContentValues {

        @Override
        public Uri uri() {
            return ArticleColumns.CONTENT_URI;
        }

        /**
         * Update row(s) using the values stored by this object and the given selection.
         *
         * @param contentResolver The content resolver to use.
         * @param where           The selection to use (can be {@code null}).
         */
        public int update(ContentResolver contentResolver, @Nullable ArticleSelection where) {
            return contentResolver.update(uri(), values(), where == null ? null : where.sel(), where == null ? null : where.args());
        }

        /**
         * Update row(s) using the values stored by this object and the given selection.
         *
         * @param context The context to use.
         * @param where   The selection to use (can be {@code null}).
         */
        public int update(Context context, @Nullable AricleSelection where) {
            return context.getContentResolver().update(uri(), values(), where == null ? null : where.sel(), where == null ? null : where.args());
        }

        public ArticleContentValues putID(@NonNull long value) {
//        if (value == null) throw new IllegalArgumentException("title must not be null");
            mContentValues.put(ArticleColumns._ID, value);
            return this;
        }

        public ArticleContentValues putTitle(@NonNull String value) {
            if (value == null) throw new IllegalArgumentException("title must not be null");
            mContentValues.put(ArticleColumns.TITLE, value);
            return this;
        }


        public ArticleContentValues putTitleDescripton(@Nullable String value) {
            mContentValues.put(ArticleColumns.TITLE_DESCRIPTION, value);
            return this;
        }

        public ArticleContentValues putAuthor() {
            mContentValues.putNull(ArticleColumns.AUTHOR);
            return this;
        }

        public ArticleContentValues putISFavorite(@Nullable String value) {
            mContentValues.put(ArticleColumns.IS_FAVOURITE, value);
            return this;
        }


    }

